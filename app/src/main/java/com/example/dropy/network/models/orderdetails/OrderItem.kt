package com.example.dropy.data.network.shops.shopsfront.dtos.orderdetails


import com.google.gson.annotations.SerializedName

data class OrderItem(
    @SerializedName("availability")
    val availability: Boolean,
    @SerializedName("item_total")
    val item_total: Int,
    @SerializedName("product")
    val product: Product,
    @SerializedName("quantity")
    val quantity: Int
)