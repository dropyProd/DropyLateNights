package com.example.dropy.ui.components.commons.maps

data class AddressDataClass(
    val addressName:String? = null,
    val apiLongitude:Double? = null,
    val apiLatitude:Double? = null,
    var userLongitude:Double? = null,
    var userLatitude:Double? = null,
    val placeId:String? = null,
    val locationTag:String? = null
)
