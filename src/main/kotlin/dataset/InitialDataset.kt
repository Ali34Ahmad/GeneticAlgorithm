package org.example.dataset

import org.example.model.HouseType
import org.example.model.Location
import org.example.model.NumberOfRooms

object InitialDataset {
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