package com.example.dropy.network.models.shops


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShopsResponse(
    @Json(name = "shops")
    val shops: List<Shop>?
)