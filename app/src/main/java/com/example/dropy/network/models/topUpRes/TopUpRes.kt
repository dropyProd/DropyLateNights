package com.example.dropy.network.models.topUpRes

data class TopUpRes(
    val message: String,
    val resultCode: Int,
    val transactionId: Int
)