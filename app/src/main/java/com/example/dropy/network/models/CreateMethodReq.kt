package com.example.dropy.network.models

data class CreateMethodReq(
    val method_name: String,
    val default_charge_per_km: Int
)
