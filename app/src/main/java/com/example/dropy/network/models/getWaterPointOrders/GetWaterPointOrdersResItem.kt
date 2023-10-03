package com.example.dropy.network.models.getWaterPointOrders

data class GetWaterPointOrdersResItem(
    val collection_point: CollectionPoint,
    val collection_point_is_paid: Boolean,
    val completed_timestamp: String,
    val id: String,
    val order_timestamp: String,
    val payment_status: String,
    val quantity: Int,
    val status: String,
    val total_cost: String,
    val truck: Truck,
    val water_type: String
)