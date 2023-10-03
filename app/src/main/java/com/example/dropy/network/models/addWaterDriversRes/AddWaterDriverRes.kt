package com.example.dropy.network.models.addWaterDriversRes

data class AddWaterDriverRes(
    val driver: Driver,
    val id: String,
    val is_active: Boolean,
    val license_number: String,
    val truck: Truck
)