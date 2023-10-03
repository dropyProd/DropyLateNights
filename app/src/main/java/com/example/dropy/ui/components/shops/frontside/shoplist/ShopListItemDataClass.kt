package com.example.dropy.ui.components.shops.frontside.shoplist

data class ShopListItemDataClass(
    var shopLogoUrl : String? = null,
    var shopName : String,
    var shopId : Int,
    var distanceInMeters: Int? = null
    )