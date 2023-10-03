package com.example.dropy.network.models.cart


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Shop(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "shop_name")
    val shop_name: String?
)