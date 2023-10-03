package com.example.dropy.network.models.acceptjob


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AcceptJobResponseItem(
    @Json(name = "delivery_person")
    val delivery_person: DeliveryPerson?,
    @Json(name = "is_assigned_rider")
    val is_assigned_rider: Boolean?,
    @Json(name = "is_complete")
    val is_complete: Boolean?,
    @Json(name = "is_picked")
    val is_picked: Boolean?
)