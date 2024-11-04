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


private  object InitialDataset {
    val houseTypes :Map<HouseType, Int> = mapOf(
        HouseType.DETACHED to 20,
        HouseType.APARTMENT to 4,
        HouseType.DELOX to 45,
        HouseType.VILLA to 10,
    )
    val locations : Map<Location, Int> = mapOf(
        Location.CITY_EDGES to 43,
        Location.CITY_CENTER to 45,
        Location.SUBURB to 52,
        Location.TOWN to 12,
        Location.RURAL to 53,
    )
    val numberOfRooms: Map<NumberOfRooms, Int> = mapOf(
        NumberOfRooms.R1 to 20,
        NumberOfRooms.R2 to 30,
        NumberOfRooms.R3 to 50,
        NumberOfRooms.R4 to 60,
        NumberOfRooms.R5 to 20,
        NumberOfRooms.R6 to 30,
        NumberOfRooms.R7 to 10,
        NumberOfRooms.R8 to 3,
    )
}

