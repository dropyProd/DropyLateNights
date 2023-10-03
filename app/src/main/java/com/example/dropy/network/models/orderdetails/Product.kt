package com.example.dropy.data.network.shops.shopsfront.dtos.orderdetails


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("product_id")
    val product_id: Int,
    @SerializedName("product_image_url")
    val product_image_url: String,
    @SerializedName("product_name")
    val product_name: String,
    @SerializedName("product_price")
    val product_price: Int
)