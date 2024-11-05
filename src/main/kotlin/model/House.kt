package org.example.model

import org.example.model.HouseFeature.HouseType
import org.example.model.HouseFeature.Location
import org.example.model.HouseFeature.NumberOfRooms

data class House(
    val numberOfRooms: NumberOfRooms,
    val location: Location,
    val type: HouseType,
)
sealed interface HouseFeature{
    enum class HouseType: HouseFeature {
        APARTMENT,DETACHED,DELOX,VILLA
    }
    enum class Location: HouseFeature {
        CITY_CENTER, CITY_EDGES, TOWN, SUBURB,RURAL,
    }
    enum class NumberOfRooms: HouseFeature {
        R1,R2,R3,R4,R5,R6,R7,R8
    }
}
