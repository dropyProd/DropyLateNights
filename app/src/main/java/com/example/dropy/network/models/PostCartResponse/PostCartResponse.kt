package com.example.dropy.network.models.PostCartResponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostCartResponse(
    @Json(name = "message")
    val message: String?,
    @Json(name = "resultCode")
    val resultCode: Int?
)