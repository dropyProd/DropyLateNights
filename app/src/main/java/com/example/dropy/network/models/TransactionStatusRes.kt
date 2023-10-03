package com.example.dropy.network.models

data class TransactionStatusRes(
    val resultCode: Int?,
    val transaction_date: Any?,
    val transaction_result_code: Int?,
    val transaction_result_description: String?
)