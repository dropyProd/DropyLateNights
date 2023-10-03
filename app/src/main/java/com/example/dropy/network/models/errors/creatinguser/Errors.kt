package com.example.dropy.network.models.errors.creatinguser


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Errors(
    @Json(name = "email")
    val email: List<String>?,
    @Json(name = "phone_number")
    val phoneNumber: List<String>?
)