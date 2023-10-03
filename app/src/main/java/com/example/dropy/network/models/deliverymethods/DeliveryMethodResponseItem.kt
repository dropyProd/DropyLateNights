package com.example.dropy.network.models.deliverymethods

data class DeliveryMethodResponseItem(
    val default_charge_per_km: Int?,
    val icon: String?,
    val id: Int?,
    val method_name: String?
)