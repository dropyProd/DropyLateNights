package com.example.dropy.network.models.createIndividualWaterOrder

data class Truck(
    val capacity: Int,
    val current_latitude: String,
    val current_location: String,
    val current_longitude: String,
    val id: String,
    val image: Any,
    val is_active: Boolean,
    val is_available: Boolean,
    val license_plate: String,
    val model: Any,
    val name: String,
    val registered_latitude: String,
    val registered_longitude: String,
    val vendor: String,
    val year: Any
)