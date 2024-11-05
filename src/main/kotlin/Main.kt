package org.example

import org.example.genetic_algorithm.GeneticAlgorithm
import org.example.model.HouseFeature


fun main() {
    for (i in 0 .. 100)
        println(GeneticAlgorithm.mutateGene(HouseFeature.NumberOfRooms.R7))
}