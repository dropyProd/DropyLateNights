package com.example.dropy.network.models.updateTruckLocationReq

data class UpdateTruckLocationReq(
    val latitude: Double,
    val longitude: Double,
    val truck_id: String
)