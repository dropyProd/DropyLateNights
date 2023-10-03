package com.example.dropy.network.models.GetIndividualOrders

data class Truck(
    val capacity: Int,
    val current_latitude: String,
    val current_location: String,
    val current_longitude: String,
    val id: String,
    val image: String,
    val is_active: Boolean,
    val is_available: Boolean,
    val license_plate: String,
    val model: String,
    val name: String,
    val registered_latitude: String,
    val registered_longitude: String,
    val vendor: String,
    val year: Int
)