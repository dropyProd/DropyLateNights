package com.example.dropy.ui.components.shops.frontside.customer.orderprocess.customercart

data class CartItemDataClass(
    val productId:Int,
    var productName:String,
    val productImageUrl: String? = "" ,
    var cartItemsNumber:Int,
    var cartItemsPrice:Int,
    var cartItemTotal:Int
    )