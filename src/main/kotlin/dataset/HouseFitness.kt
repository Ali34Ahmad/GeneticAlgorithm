package org.example.dataset

import org.example.model.HouseFeature
import org.example.model.HouseFeature.HouseType
import org.example.model.HouseFeature.Location
import org.example.model.HouseFeature.NumberOfRooms
import kotlin.reflect.KClass
import kotlin.to

/**
 * This class will show the current statistics about house types,
 * locations and number of rooms by house purchase.
 * @author Ali Mansoura
 */
object HouseFitness {
    val types : MutableMap<HouseType, Int> = InitialDataset.houseTypes
            as MutableMap<HouseType, Int>

    val locations : MutableMap<Location, Int> = InitialDataset.locations
            as MutableMap<Location, Int>

    val numberOfRooms: MutableMap<NumberOfRooms, Int> = InitialDataset.numberOfRooms
            as MutableMap<NumberOfRooms, Int>
}



