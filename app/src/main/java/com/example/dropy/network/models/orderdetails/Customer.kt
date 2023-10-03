package com.example.dropy.data.network.shops.shopsfront.dtos.orderdetails


import com.google.gson.annotations.SerializedName

data class Customer(
    @SerializedName("customer_phone")
    val customer_phone: String,
    @SerializedName("customer_name")
    val customer_name: String,
    @SerializedName("id")
    val id: Int
)