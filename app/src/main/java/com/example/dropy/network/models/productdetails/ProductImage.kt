package com.example.dropy.network.models.productdetails


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductImage(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "image_url")
    val image_url: String?
)