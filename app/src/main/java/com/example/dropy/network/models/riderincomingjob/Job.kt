package com.example.dropy.network.models.riderincomingjob

data class Job(
    val charge: Double?,
    val check_out: CheckOut?,
    val delivery_location: DeliveryLocation?,
    val delivery_method: DeliveryMethod?,
    val distance: Double?,
    val id: Int?
)