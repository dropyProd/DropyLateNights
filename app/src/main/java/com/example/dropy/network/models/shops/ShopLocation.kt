package com.example.dropy.network.models.shops


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShopLocation(
    @Json(name = "latitude")
    val latitude: Double?,
    @Json(name = "longitude")
    val longitude: Double?,
    @Json(name = "place_id")
    val place_id: String?,
    @Json(name = "place_name")
    val place_name: String?
)