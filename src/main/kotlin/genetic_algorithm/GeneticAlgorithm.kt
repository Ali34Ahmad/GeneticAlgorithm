package org.example.genetic_algorithm

import org.example.dataset.HouseFitness
import org.example.model.House
import org.example.model.HouseFeature
import kotlin.random.Random

class GeneticAlgorithm {
    companion object{

        // The number of individuals in the generation
        const val   POPULATION_SIZE = 100
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
        fun createGenome() {
            TODO("create one genome randomly")
        }

        /**
         * This function will create the initial generation of  individuals.
         * @author Ali Ahmad
         */
        fun createInitialGeneration() {
            TODO("create the initial generation of  individuals.")
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

        fun runAlgorithm(): List<Individual>{
            /*
            get the needed information from the GeneticAlgorithm class : like population size initial generation etc.
             */
            TODO("run Genetic Algorithm and return the first accepted generation")
        }
    }
}