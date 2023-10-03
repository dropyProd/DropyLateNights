package com.example.dropy.network.models.cart


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetCartResponseItem(
    @Json(name = "get_order_total")
    val get_order_total: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "is_accepted")
    val is_accepted: Boolean?,
    @Json(name = "is_canceled")
    val is_canceled: Boolean?,
    @Json(name = "is_checked_out")
    val is_checked_out: Boolean?,
    @Json(name = "is_pending")
    val is_pending: Boolean?,
    @Json(name = "is_placed")
    val is_placed: Boolean?,
    @Json(name = "number_of_order_items")
    val number_of_order_items: Int?,
    @Json(name = "order_items")
    val order_items: List<OrderItem>?,
    @Json(name = "order_number")
    val order_number: String?,
    @Json(name = "shop")
    val shop: Shop?,
    @Json(name = "deliveryprice")
    val deliveryPrice: Int = 0
)