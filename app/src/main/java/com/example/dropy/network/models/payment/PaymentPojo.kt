package com.example.dropy.network.models.payment

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaymentPojo(
    @Json(name = "message")
    val message: String?,
    @Json(name = "resultCode")
    val resultCode: Int?,
    @Json(name = "transactionId")
    val transactionId: Int?
)
