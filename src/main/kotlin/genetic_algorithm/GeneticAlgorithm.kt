package org.example.genetic_algorithm

import org.example.dataset.HouseFitness
import org.example.model.House
import org.example.model.HouseFeature
import kotlin.random.Random

class GeneticAlgorithm {
    companion object{

        // The number of individuals in the generation
        const val   POPULATION_SIZE = 100

        val genes: Map<String, List<Any>> = mapOf(
            "location" to listOf(HouseFeature.Location.CITY_CENTER, HouseFeature.Location.SUBURB, HouseFeature.Location.RURAL),
            "type" to listOf(HouseFeature.HouseType.VILLA, HouseFeature.HouseType.APARTMENT, HouseFeature.HouseType.DELOX),
            "numberOfRooms" to listOf(HouseFeature.NumberOfRooms.R1, HouseFeature.NumberOfRooms.R3, HouseFeature.NumberOfRooms.R6)
        )


        /**
         * This function return the fitness of a given house
         * according to the total fitness of its attributes.
         * @author Ali Mansoura.
         */
        fun calculateFitness(house: House): Int{

            val numberOfRoomsFitness = HouseFitness.numberOfRooms[house.numberOfRooms]?:0

            val houseTypeFitness = HouseFitness.types[house.type]?:0

            val houseLocationFitness = HouseFitness.locations[house.location]?:0


            return numberOfRoomsFitness + houseLocationFitness + houseTypeFitness
        }

        /**
         * This function will return the sum of the maximum fitness of each house's feature.
         *  It is considered as the goal fitness that will stop the Algorithm from working.
         * @author Ali Mansoura.
         */
        fun getTheTargetFitness(): Int {

            val maxTypesFitness = HouseFitness.types.maxOf { it.value }
            val maxLocationFitness = HouseFitness.locations.maxOf { it.value }
            val maxNumberOfRoomsFitness = HouseFitness.numberOfRooms.maxOf { it.value }

            return maxTypesFitness+maxLocationFitness+maxNumberOfRoomsFitness
        }

        /**
         * This function accepts a house feature like and mutate it
         * like location = TOWN then the function will return any random value of another location.
         * @author Ali Mansoura
         */
        fun mutateGene(feature: HouseFeature): HouseFeature {
            when(feature){
                is HouseFeature.NumberOfRooms ->{
                    val randomIndex = Random.nextInt(0, HouseFeature.NumberOfRooms.entries.size)
                    val selectedFeature = HouseFeature.NumberOfRooms.entries[randomIndex]
                    return selectedFeature
                }
                is HouseFeature.Location -> {
                    val randomIndex = Random.nextInt(0, HouseFeature.Location.entries.size)
                    val selectedFeature = HouseFeature.Location.entries[randomIndex]
                    return selectedFeature

                }
                is HouseFeature.HouseType -> {
                    val randomIndex = Random.nextInt(0,HouseFeature.HouseType.entries.size)
                    val selectedFeature = HouseFeature.HouseType.entries[randomIndex]
                    return selectedFeature
                }
            }

        }
        /**
         * This function will create one genome randomly.
         * @author Ali Ahmad
         */
        fun createGenome():Individual {
            val location = genes["location"]?.random() as HouseFeature.Location
            val type = genes["type"]?.random() as HouseFeature.HouseType
            val numberOfRooms = genes["numberOfRooms"]?.random() as HouseFeature.NumberOfRooms

            return Individual(House(location = location, type =  type, numberOfRooms = numberOfRooms))        }

        /**
         * This function will create the initial generation of  individuals.
         * @author Ali Ahmad
         */
        fun createInitialGeneration():List<Individual> {
            val population = mutableListOf<Individual>()
            for (i in 0 until POPULATION_SIZE) {
                val individual = createGenome()
                population.add(individual)
            }
            return population
        }

        /**
         * This function will select the best individuals by their fitness.
         * @param generation this is the ordered list of individuals by their fitness.
         * @param percentage this is a  float value: 0.1 => 10%.
         * @author Ali Mansoura
         */
        fun selection(generation: List<Individual>,percentage: Float = 0.1f): List<Individual>{
            val size: Int = (generation.size * percentage).toInt()
            return generation.subList(0,size)
        }

        fun runAlgorithm(): List<Individual> {
            // Create the initial generation
            val currentGeneration = createInitialGeneration().toMutableList()

            // Define termination condition (target fitness or maximum generations)
            val targetFitness = getTheTargetFitness()
            var generationCount = 0
            var bestFitness = 0

            // Run the algorithm loop
            while (bestFitness < targetFitness && generationCount < 100) { // Max 100 generations
                generationCount++

                // Selection: Choose the fittest individuals for mating
                val selectedParents = selection(currentGeneration)

                // Crossover: Create new individuals by combining parents' genes
                val offspring = mutableListOf<Individual>()
                for (i in 0 until POPULATION_SIZE step 2) {
                    val parent1 = selectedParents[Random.nextInt(selectedParents.size)]
                    val parent2 = selectedParents[Random.nextInt(selectedParents.size)]
                    offspring.addAll(listOf(parent1.mate(parent2), parent2.mate(parent1))) // Mating with both parents
                }

                // Mutation: Randomly modify some genes of individuals
                for (individual in offspring) {
                    if (Random.nextDouble() < 0.1) { // Mutation rate of 10%
                        val randomFeature = individual.house.getRandomFeature()
                        when(val mutatedHouseFeature = mutateGene(randomFeature)){
                            is HouseFeature.HouseType->individual.house.type=mutatedHouseFeature
                            is HouseFeature.Location->individual.house.location=mutatedHouseFeature
                            is HouseFeature.NumberOfRooms->individual.house.numberOfRooms=mutatedHouseFeature
                        }
                    }
                }

                // Update the current generation
                currentGeneration.clear()
                currentGeneration.addAll(offspring)

                // Update best fitness
                bestFitness = currentGeneration.maxByOrNull { it.fitness }?.fitness ?: 0

                println("Generation: $generationCount, Best Fitness: $bestFitness")
            }

            return currentGeneration
        }

        private fun House.getRandomFeature(): HouseFeature {
            val features = listOf(HouseFeature.Location::class, HouseFeature.HouseType::class, HouseFeature.NumberOfRooms::class)
            val randomIndex = Random.nextInt(features.size)
            val randomFeatureClass = features[randomIndex]

            // Use reflection to get the actual feature value
            return when (randomFeatureClass) {
                HouseFeature.Location::class -> this.location
                HouseFeature.HouseType::class -> this.type
                HouseFeature.NumberOfRooms::class -> this.numberOfRooms
                else -> throw IllegalStateException("Unexpected feature class")
            }
        }


    }
}