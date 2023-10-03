package com.example.dropy.ui.components.parcels.frontside.expressdeliveryperson

import androidx.compose.ui.graphics.painter.Painter

data class DeliveryPersonDataClass(
    val userProfile : Painter?,
    val firstName:String,
    val lastName:String,
    val vehicleDescription:String,
    val totalRides: Int,
    val totalKilometres: Int,
    val averageRating: Double,
    val etaInMinutes: Int,
)
