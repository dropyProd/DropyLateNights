package com.example.dropy.ui.components.shops.frontside.customer.orderprocess.deliveryinfomation.deliverymethod

import androidx.compose.ui.graphics.painter.Painter

data class DeliveryMethodDataClass(
    var type:String,
    var etaInMinutes: Int,
    var price :Int,
    var iconUrl : String
)
