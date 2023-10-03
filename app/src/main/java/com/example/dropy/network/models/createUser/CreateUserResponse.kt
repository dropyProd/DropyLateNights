package com.example.dropy.network.models.createUser


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class CreateUserResponse(
    val message: String?,
    val resultCode: Int?
)