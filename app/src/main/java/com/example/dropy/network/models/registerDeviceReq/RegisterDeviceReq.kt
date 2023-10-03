package com.example.dropy.network.models.registerDeviceReq

data class RegisterDeviceReq(
    val active: Boolean,
    val device_id: String,
    val name: String,
    val registration_id: String,
    val type: String
)