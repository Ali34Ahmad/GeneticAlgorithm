package org.example.model

data class House(
    val numberOfRooms: NumberOfRooms,
    val location: Location,
    val type: HouseType,
)
enum class HouseType {
    APARTMENT,DETACHED,DELOX,VILLA
}
enum class Location {
    CITY_CENTER, CITY_EDGES, TOWN, SUBURB,RURAL,
}
enum class NumberOfRooms {
    R1,R2,R3,R4,R5,R6,R7,R8
}