package org.example.genetic_algorithm

import org.example.model.House

data class Individual(
    val house: House
){
    val fitness = GeneticAlgorithm.calculateFitness(house)
}
