package com.example.dropy.network.models.userdetails

data class Data(
    val customer_instance: CustomerInstance?,
    val dropy_user_instance: DropyUserInstance?,
    val shops_owned: List<ShopsOwned>?
)