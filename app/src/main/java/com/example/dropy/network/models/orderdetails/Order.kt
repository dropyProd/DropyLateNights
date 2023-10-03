package com.example.dropy.data.network.shops.shopsfront.dtos.orderdetails


import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("order_details")
    val order_details: OrderDetails
)