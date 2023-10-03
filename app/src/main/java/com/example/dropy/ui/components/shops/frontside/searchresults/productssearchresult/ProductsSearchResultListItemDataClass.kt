package com.example.dropy.ui.components.shops.frontside.searchresults.productssearchresult

data class ProductsSearchResultListItemDataClass(
    val productPhotoUrl : String?,
    val productName :String,
    val shopName : String,
    val rating :Double,
    val distanceInMeters : Int,
    val timeInMinutes : Int,
    val price : Int,
)
