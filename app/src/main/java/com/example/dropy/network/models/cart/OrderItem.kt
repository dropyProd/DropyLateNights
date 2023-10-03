package com.example.dropy.network.models.cart


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrderItem(
    @Json(name = "availability")
    val availability: Boolean?,
    @Json(name = "item_total")
    val item_total: Int?,
    @Json(name = "product")
    val product: Product?,
    @Json(name = "quantity")
    val quantity: Int?
)