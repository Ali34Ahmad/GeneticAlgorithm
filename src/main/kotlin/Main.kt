package org.example

import org.example.genetic_algorithm.GeneticAlgorithm
import org.example.model.House
import org.example.model.HouseType
import org.example.model.Location
import org.example.model.NumberOfRooms


fun main() {
   val house = House(
       numberOfRooms = NumberOfRooms.R7,
       location = Location.CITY_CENTER,
       type = HouseType.VILLA
   )
}