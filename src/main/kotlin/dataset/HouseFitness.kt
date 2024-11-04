package org.example.dataset

import org.example.model.HouseType
import org.example.model.Location
import org.example.model.NumberOfRooms

/**
 * @author Ali Mansoura
 * This class will show the current statistics about house types,
 * locations and number of rooms by house purchase
 */
object HouseFitness {
    val types : MutableMap<HouseType, Int> = InitialDataset.houseTypes
            as MutableMap<HouseType, Int>

    val locations : MutableMap<Location, Int> = InitialDataset.locations
            as MutableMap<Location, Int>

    val numberOfRooms: MutableMap<NumberOfRooms, Int> = InitialDataset.numberOfRooms
            as MutableMap<NumberOfRooms, Int>
}

