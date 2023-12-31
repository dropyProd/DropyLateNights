package com.example.dropy.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable
import retrofit2.http.Field

@JsonClass(generateAdapter = true)
data class RegisterBody(

    @field:Json(name = "first_name")
    val first_name: String,
    @field:Json(name = "last_name")
    val last_name: String,
    @field:Json(name = "phone_number")
    val phone_number: String,
    @field:Json(name = "password1")
    val password1: String,
    @field:Json(name = "password2")
    val password2: String,
    @field:Json(name = "dropy_role")
    val dropy_role: String

)