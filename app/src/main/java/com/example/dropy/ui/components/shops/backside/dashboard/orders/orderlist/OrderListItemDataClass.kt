package com.example.dropy.ui.components.shops.backside.dashboard.orders.orderlist

import com.example.dropy.network.models.PlaceOrderResponse
import com.example.dropy.network.models.ShopOrdersResponseItem
import com.example.dropy.network.models.customerorders.Shop
import com.google.android.gms.maps.model.LatLng


data class OrderListItemDataClass (
    val orderNumber:String,
    val orderId:Int,
    val customerName:String,
    val customerProfilePhotoUrl: String? = null,
    val isProcessed:Boolean = false,
    val numberOfItems:Int,
    val status:String,
    val cost:Int,
    val isPaid:Boolean = false,
    val orderPackedbyShop:Boolean = true,
    val orderPickedbyRider:Boolean = false,
    val customerReceiveOrder:Boolean = false,
    val timeString:String = "",
    val shopLocale: LatLng? = null,
    val deliveryLocale: LatLng? = null,
    val deliveryplacename: String = "",
    val shopId: String = "",
    val customerId: String = "",
    val shopOrdersResponseItem: ShopOrdersResponseItem? = null

 )
