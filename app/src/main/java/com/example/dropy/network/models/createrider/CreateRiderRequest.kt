package com.example.dropy.network.models.createrider

data class CreateRiderRequest(
    val plate_number: String = "",
    val bike_model: String = "",
    val county_qr_id_number: Int = 0,
    val description: String = "",
    val dropyuser_id: Int = 0,
    val delivery_method_id: Int = 0,
    val pool_id: String = "",
    val national_id: Int = 0,
    val place_name: String = "",
)
