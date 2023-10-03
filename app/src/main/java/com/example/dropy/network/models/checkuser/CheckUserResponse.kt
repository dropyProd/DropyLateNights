package com.example.dropy.network.models.checkuser


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CheckUserResponse(
    @Json(name = "resultCode")
    val resultCode: Int?,
    @Json(name = "userExists")
    val userExists: Boolean?
)