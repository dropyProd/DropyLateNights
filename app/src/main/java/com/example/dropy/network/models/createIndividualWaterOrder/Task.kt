package com.example.dropy.network.models.createIndividualWaterOrder

import com.example.dropy.network.models.GetIndividualOrders.ContentType
import com.example.dropy.network.models.GetIndividualOrders.Truck

data class Task(
    val allocated_quantity: Int,
    val allocation_timestamp: String,
    val content_type: ContentType,
    val delivery_status: String,
    val end_time: Any,
    val four_digit_code: String?,
    val from_latitude: Any,
    val from_longitude: Any,
    val has_started: Boolean,
    val id: String,
    val is_accepted: Boolean,
    val is_complete: Boolean,
    val object_id: String,
    val payout_cost_settings: Any,
    val start_time: Any,
    val truck: Truck
)