package com.example.dropy.network.models.canceljob


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CancelIncomingJobResponse(
    @Json(name = "message")
    val message: String?,
    @Json(name = "status")
    val status: Int?
)