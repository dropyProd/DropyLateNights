package com.example.dropy.data.network.shops.shopsfront.dtos.orderdetails


import com.google.gson.annotations.SerializedName

data class Shop(
    @SerializedName("id")
    val id: Int,
    @SerializedName("shop_name")
    val shop_name: String
)