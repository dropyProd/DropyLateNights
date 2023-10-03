package com.example.dropy.network.models.acceptjob


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Dropyuser(
    @Json(name = "first_name")
    val first_name: String?,
    @Json(name = "last_name")
    val last_name: String?,
    @Json(name = "phone_number")
    val phone_number: String?
)