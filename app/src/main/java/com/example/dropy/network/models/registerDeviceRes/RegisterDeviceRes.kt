package com.example.dropy.network.models.registerDeviceRes

data class RegisterDeviceRes(
    val active: Boolean,
    val date_created: String,
    val device_id: String,
    val id: Int,
    val name: String,
    val registration_id: String,
    val type: String
)