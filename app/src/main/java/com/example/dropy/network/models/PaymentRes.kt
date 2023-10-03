package com.example.dropy.network.models

data class PaymentRes(
    val resultCode: Int?,
    val message: String?,
    val transactionId: Int?,
)
