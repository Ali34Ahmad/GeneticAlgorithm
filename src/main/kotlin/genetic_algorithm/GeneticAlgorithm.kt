package org.example.genetic_algorithm

import org.example.dataset.HouseFitness
import org.example.model.House

class GeneticAlgorithm {

    companion object{
        /**
         * This function return the fitness of a given house
         * according to the total fitness of its attributes.
         */
        fun fitness(house: House): Int{

            val numberOfRoomsFitness = HouseFitness.numberOfRooms[house.numberOfRooms]?:0

            val houseTypeFitness = HouseFitness.types[house.type]?:0

            val houseLocationFitness = HouseFitness.locations[house.location]?:0


            return numberOfRoomsFitness + houseLocationFitness + houseTypeFitness
        }
    }
}