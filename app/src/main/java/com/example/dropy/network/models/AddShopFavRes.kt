package com.example.dropy.network.models

import com.google.gson.annotations.SerializedName

data class AddShopFavRes(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)
