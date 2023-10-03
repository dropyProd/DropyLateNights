package com.example.dropy.network.models.acceptjob


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeliveryPerson(
    @Json(name = "dropyuser")
    val dropyuser: Dropyuser?
)