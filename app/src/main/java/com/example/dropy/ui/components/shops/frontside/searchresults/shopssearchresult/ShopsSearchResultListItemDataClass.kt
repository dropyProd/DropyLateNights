package com.example.dropy.ui.components.shops.frontside.searchresults.shopssearchresult

data class ShopsSearchResultListItemDataClass(
    val shopLogoUrl : String?,
    val shopName : String,
    val shopLocation :String,
    val distanceInMeters : Int,
    val timeInMinutes : Int,
    val rating :Double,
)
