package com.example.dropy.ui.components.shops.frontside.customer.orderprocess.customercart

data class OrderDetailsDataClass(
    val orderId:Int,
    val shopName:String,
    val isOrderPlaced:Boolean = false,
    val isOrderAccepted:Boolean = false,
    val isOrderDeclined:Boolean = false,
    val reasonForDecline:String? = null,
    val isOrderCheckedOut:Boolean = false,
    val cartItems:List<CartItemDataClass>,
    val discount:Int = 0,
    val subTotal:Int,
    val netTotal:Int,
)
