package com.example.dropy.network.models.customerqr.body

data class CustomerQrBody(
    val unique_customer_id: String,
    val unique_delivery_number: String,
    val rider_id: Int
)
