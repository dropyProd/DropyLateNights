package com.example.dropy.network.models.cart


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(
    @Json(name = "product_id")
    val product_id: Int?,
    @Json(name = "product_image_url")
    val product_image_url: String?,
    @Json(name = "product_name")
    val product_name: String?,
    @Json(name = "product_price")
    val product_price: Int?
)