package com.example.dropy.network.models.individualWaterOrder

data class IndividualWaterOrderReq(
    val delivery_latitude: String,
    val delivery_longitude: String,
    val delivery_type: String,
    val quantity: Int,
    val water_type: String,
   /* val scheduled_time: String,*/
    val delivery_place_name: String
)