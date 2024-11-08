package org.example.genetic_algorithm

import org.example.model.House
import kotlin.random.Random

/**
 * This class represents an individual of the Genetic Algorithm
 * @author Ali Mansoura.
 * @param house :it is the house.
 * @property fitness :it is the calculated fitness according to the house's attributes.
 */
data class Individual(
    var house: House
) : Comparable<Individual> {

    val fitness = GeneticAlgorithm.calculateFitness(house)

    //We can now compare two individuals by their fitness.
    override fun compareTo(other: Individual): Int {
        return this.fitness.compareTo(other.fitness)
    }

    /**
     * This function will mate two individuals and returns an individual
     * @author Ali Ahmad
     */
    fun mate(secondParent: Individual): Individual {
        val crossoverPoint = Random.nextInt(1, 3) // Random crossover point (1 or 2)
        val location = if (Random.nextBoolean()) this.house.location else secondParent.house.location
        val type = if (crossoverPoint >= 2) this.house.type else secondParent.house.type
        val numberOfRooms = if (crossoverPoint >= 1) this.house.numberOfRooms else secondParent.house.numberOfRooms
        return Individual(House(location = location, type = type, numberOfRooms = numberOfRooms))
    }
}
