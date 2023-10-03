package com.example.dropy.network.models.checkoutPojo

import kotlinx.serialization.Serializable

@Serializable
data class DeliveryLocation(
    val addressName: String,
    val apiLongitude: Double,
    val apiLatitude: Double,
    val userLongitude: Double,
    val userLatitude: Double,
    val placeId: String,
    val locationTag: String,
)
