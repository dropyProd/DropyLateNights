package com.example.dropy.network.models.errors.creatinguser


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorCreatingUserResponse(
    @Json(name = "errors")
    val errors: Errors?,
    @Json(name = "resultCode")
    val resultCode: Int?
)
