package com.example.dropy.network.models.createIndividualWaterOrder

data class CreateIndividualWaterOrderRes(
    val assigned_trucks: List<AssignedTruck>,
    val customer: Customer,
    val delivery_cost_settings: Int,
    val delivery_latitude: String,
    val delivery_longitude: String,
    val delivery_place_name: Any,
    val delivery_status: String,
    val delivery_type: String,
    val express_delivery: Boolean,
    val id: String,
    val note: String,
    val payment_status: String,
    val quantity: Int,
    val scheduled_time: Any,
    val status: String,
    val tasks: List<Task>,
    val timestamp: String,
    val total_payment: String,
    val tracking_id: String,
    val water_cost_settings: Int,
    val water_type: String
)