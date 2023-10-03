package com.example.dropy.network.models.pendingOrders

data class DeliveryLocation(
    val lat: Double?,
    val long: Double?,
    val place_name: String?,
    val error: String? = null
)