package com.example.dropy.data.network.shops.shopsfront.dtos.orderdetails


import com.google.gson.annotations.SerializedName

data class OrderDetails(
    @SerializedName("customer")
    val customer: Customer,
    @SerializedName("get_order_total")
    val get_order_total: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_accepted")
    val is_accepted: Boolean,
    @SerializedName("is_canceled")
    val is_canceled: Boolean,
    @SerializedName("is_checked_out")
    val is_checked_out: Boolean,
    @SerializedName("is_pending")
    val is_pending: Boolean,
    @SerializedName("is_placed")
    val is_placed: Boolean,
    @SerializedName("number_of_order_items")
    val number_of_order_items: Int,
    @SerializedName("order_items")
    val order_items: List<OrderItem>,
    @SerializedName("order_number")
    val order_number: String,
    @SerializedName("shop")
    val shop: Shop
)