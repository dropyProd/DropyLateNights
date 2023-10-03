package com.example.dropy.network.models.checkoutPojo

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ChargesPojo(
    val chargeName: String,
    val chargeAmount: Int
)
