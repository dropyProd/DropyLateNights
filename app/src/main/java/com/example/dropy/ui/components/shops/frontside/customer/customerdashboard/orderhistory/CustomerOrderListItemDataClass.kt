package com.example.dropy.ui.components.shops.frontside.customer.customerdashboard.orderhistory

import com.example.dropy.network.models.customerorders.Shop
import com.google.android.gms.maps.model.LatLng


data class CustomerOrderListItemDataClass (
    val orderNumber:String,
    val orderId:Int,
    val customerName:String,
    val customerProfilePhotoUrl: String? = null,
    val isProcessed:String,
    val numberOfItems:Int,
    val status:String,
    val cost:Int,
    val isPaid:Boolean,
    val shopLocale:LatLng,
    val customerLocale:LatLng,
    val customerLocalename:String,
    val orderPackedbyShop:Boolean = true,
    val orderPickedbyRider:Boolean = false,
    val customerReceiveOrder:Boolean = false,
    val timeString:String,
    val shopid:Int? = null,
    val shop: Shop? = null,
 )
