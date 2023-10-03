package com.example.dropy.network.models.directions

data class DirectionRespnse(
    val geocoded_waypoints: List<GeocodedWaypoint>?,
    val routes: List<Route>?,
    val status: String?
)