package com.example.dropy.network.models.collectionPointOrder

data class CollectionPointOrderReq(
    val collection_point: String,
    val completed_timestamp: String,
    val quantity: Int,
    val total_cost: String,
    val truck: String,
    val water_type: String
)