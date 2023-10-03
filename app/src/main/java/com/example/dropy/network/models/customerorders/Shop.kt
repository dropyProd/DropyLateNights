package com.example.dropy.network.models.customerorders

data class Shop(
    val id: Int?,
    val phone_number: String?,
    val shop_location: ShopLocation?,
    val shop_logo: String?,
    val shop_name: String?
)