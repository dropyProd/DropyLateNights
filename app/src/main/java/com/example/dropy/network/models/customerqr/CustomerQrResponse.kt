package com.example.dropy.network.models.customerqr

data class CustomerQrResponse(
    val encoded_customer_id: String?,
    val encoded_order_number: String?,
    val statuscode: Int?
)