package org.example.genetic_algorithm

import org.example.dataset.HouseFitness
import org.example.model.House
import org.example.model.HouseFeature
import kotlin.random.Random

class GeneticAlgorithm {
    companion object {

        // The number of individuals in the generation
        const val POPULATION_SIZE = 100


        /**
         * This function return the fitness of a given house
         * according to the total fitness of its attributes.
         * @author Ali Mansoura.
         */
        fun calculateFitness(house: House): Int {

            val numberOfRoomsFitness = HouseFitness.numberOfRooms[house.numberOfRooms] ?: 0

            val houseTypeFitness = HouseFitness.types[house.type] ?: 0

            val houseLocationFitness = HouseFitness.locations[house.location] ?: 0


            return numberOfRoomsFitness + houseLocationFitness + houseTypeFitness
        }

        /**
         * This function will return the sum of the maximum fitness of each house's feature.
         *  It is considered as the goal fitness that will stop the Algorithm from working.
         * @author Ali Mansoura.
         */
        private fun getTheTargetFitness(): Int {

            val maxTypesFitness = HouseFitness.types.maxOf { it.value }
            val maxLocationFitness = HouseFitness.locations.maxOf { it.value }
            val maxNumberOfRoomsFitness = HouseFitness.numberOfRooms.maxOf { it.value }

            return maxTypesFitness + maxLocationFitness + maxNumberOfRoomsFitness
        }

        /**
         * This function accepts a house feature like and mutate it
         * like location = TOWN then the function will return any random value of another location.
         * @author Ali Mansoura
         */
        fun mutateGene(feature: HouseFeature): HouseFeature {
            when (feature) {
                is HouseFeature.NumberOfRooms -> {
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
                    val randomIndex = Random.nextInt(0, HouseFeature.HouseType.entries.size)
                    val selectedFeature = HouseFeature.HouseType.entries[randomIndex]
                    return selectedFeature
                }
            }

        }

        /**
         * This function will create one genome randomly.
         * @author Ali Ahmad
         */
        private fun createGenome(): Individual {
            val location = HouseFeature.Location.entries.random()
            val type = HouseFeature.HouseType.entries.random()
            val numberOfRooms = HouseFeature.NumberOfRooms.entries.random()

            return Individual(House(location = location, type = type, numberOfRooms = numberOfRooms))
        }

        /**
         * This function will create the initial generation of  individuals.
         * @author Ali Ahmad
         */
        private fun createInitialGeneration(): List<Individual> {
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
        private fun selection(generation: List<Individual>, percentage: Float = 0.1f): List<Individual> {
            val size: Int = (generation.size * percentage).toInt()
            return generation.subList(0, size)
        }

        fun runAlgorithm(): List<Individual> {
            // Create the initial generation
            val currentGeneration = createInitialGeneration().toMutableList()
            val newGeneration = createInitialGeneration().toMutableList()

            // Define termination condition (target fitness or maximum generations)
            val targetFitness = getTheTargetFitness()
            var generationCount = 0
            var bestFitness = 0

            // Run the algorithm loop
            while (bestFitness < targetFitness) {
                generationCount++

                // Selection: Choose the fittest individuals for mating
                currentGeneration.sort()
                val selectedParents = selection(currentGeneration)

                //Add the fittest 10% to the newGeneration
                newGeneration.addAll(selectedParents)

                // Crossover: Create new individuals by combining parents' genes
                val s = (90 * POPULATION_SIZE) / 100
                val offspring = mutableListOf<Individual>()
                for (i in 0 until s) {
                    val randomParent1 = Random.nextInt(currentGeneration.size / 2)
                    val randomParent2 = Random.nextInt(currentGeneration.size / 2)
                    val parent1 = currentGeneration[randomParent1]
                    val parent2 = currentGeneration[randomParent2]
                    offspring.add(parent1.mate(parent2)) // Mating both parents
                }

                // Update the new generation
                newGeneration.addAll(offspring)

                // Update best fitness
                bestFitness = currentGeneration.maxOf { it.fitness }

                println("GenerationNumber: $generationCount\t, Best Fitness: $bestFitness TargetFitness: $targetFitness")
                println("Generation: $currentGeneration")
                currentGeneration.forEach {
                    print("${it.fitness}   \n")
                }
            }

            return currentGeneration
        }
    }
}