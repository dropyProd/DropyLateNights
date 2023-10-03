package com.example.dropy.ui.components.parcels.frontside.expressdeliverymethod

import androidx.compose.ui.graphics.painter.Painter

data class ExpressDeliveryMethodListItemDataClass(
    var type:String,
    var etaInMinutes: Int,
    var price :Int,
    var image : Painter
)
