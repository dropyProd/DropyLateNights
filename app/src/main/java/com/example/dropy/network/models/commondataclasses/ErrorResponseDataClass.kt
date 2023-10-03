package com.example.dropy.network.models.commondataclasses


import com.google.gson.annotations.SerializedName

data class ErrorResponseDataClass(
    @SerializedName("errors")
    val errors: List<String>,
    @SerializedName("resultCode")
    val resultCode: Int
)