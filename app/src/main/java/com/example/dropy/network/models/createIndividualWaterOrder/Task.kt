package com.example.dropy.network.models.createIndividualWaterOrder

data class Task(
    val allocated_quantity: Int,
    val allocation_timestamp: String,
    val content_type: ContentType,
    val delivery_status: String,
    val end_time: Any,
    val four_digit_code: String,
    val from_latitude: String,
    val from_longitude: String,
    val has_started: Boolean,
    val id: String,
    val is_accepted: Boolean,
    val is_complete: Boolean,
    val object_id: String,
    val payout_cost_settings: PayoutCostSettings,
    val start_time: Any,
    val truck: Truck
)