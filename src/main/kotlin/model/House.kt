package org.example.model

import org.example.dataset.ProhibitedFeatures
import org.example.model.HouseFeature.HouseType
import org.example.model.HouseFeature.Location
import org.example.model.HouseFeature.NumberOfRooms

data class House(
    var numberOfRooms: NumberOfRooms,
    var location: Location,
    var type: HouseType,
){
    /**
     * This function will check if the house is acceptable for future purchase.
     * @author Ali Mansoura
     */
    fun containsProhibitedFeatures(): Boolean {
        val prohibitedTypes = ProhibitedFeatures.prohibitedTypes
        val prohibitedRooms = ProhibitedFeatures.prohibitedNumberOfRooms
        val prohibitedLocations = ProhibitedFeatures.prohibitedLocations

        return type in prohibitedTypes ||
                numberOfRooms in prohibitedRooms ||
                location in prohibitedLocations
    }
}
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
