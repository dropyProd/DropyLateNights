package com.example.dropy.network.models.getTruckDrivers

data class GetTruckDriversResItem(
    val driver: Driver,
    val id: String,
    val is_active: Boolean,
    val is_approved: Boolean,
    val is_seen_by_vendor: Boolean,
    val license_number: String,
    val truck: Truck
)