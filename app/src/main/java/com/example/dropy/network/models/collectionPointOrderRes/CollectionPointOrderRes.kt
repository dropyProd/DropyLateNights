package com.example.dropy.network.models.collectionPointOrderRes

data class CollectionPointOrderRes(
    val collection_point: CollectionPoint,
    val collection_point_is_paid: Boolean,
    val completed_timestamp: Any,
    val id: String,
    val order_timestamp: String,
    val payment_status: String,
    val quantity: Int,
    val status: String,
    val total_cost: String,
    val truck: Truck,
    val water_type: String
)