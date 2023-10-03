package com.example.dropy.network.models.commondataclasses


import com.google.gson.annotations.SerializedName

data class ActionResultDataClass(
    @SerializedName("message")
    val message: String,
    @SerializedName("resultCode")
    val resultCode: Int
)