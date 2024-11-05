package org.example.genetic_algorithm

import org.example.model.House

/**
 * This class represents an individual of the Genetic Algorithm
 * @author Ali Mansoura.
 * @param house :it is the house.
 * @property fitness :it is the calculated fitness according to the house's attributes.
 */
data class Individual(
    val house: House
): Comparable<Individual>{

    val fitness = GeneticAlgorithm.calculateFitness(house)

    //We can now compare two individuals by their fitness.
    override fun compareTo(other: Individual): Int {
        return this.fitness.compareTo(other.fitness)
    }

    /**
     * This function will mate two individuals and returns an individual
     * @author Ali Ahmad
     */
    fun mate(secondParent: Individual): Individual{
        TODO("do the mate operation")
    }
}
