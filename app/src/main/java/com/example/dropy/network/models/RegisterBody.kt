package com.example.dropy.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable
import retrofit2.http.Field

@JsonClass(generateAdapter = true)
data class RegisterBody(
    @field:Json(name = "user_name")
    val user_name: String,
    @field:Json(name = "first_name")
    val first_name: String,
    @field:Json(name = "last_name")
    val last_name: String,
    @field:Json(name = "phone_number")
    val phone_number: String,
    @field:Json(name = "email")
    val email: String,
    @field:Json(name = "password")
    val password: String,
)