package com.example.dropy.ui.screens.shops.frontside.checkout

import com.example.dropy.network.models.checkoutPojo.ChargesPojo
import com.example.dropy.network.models.checkoutPojo.DeliveryLocation
import com.example.dropy.ui.components.commons.maps.AddressDataClass
import com.example.dropy.ui.components.commons.payments.paymentpage.chargesheet.ChargeDataClass

data class CheckoutDataClass(
    val deliveryLocation:DeliveryLocation,
    val deliveryMethod:String,
    val charges:List<ChargesPojo> = emptyList(),
    val paymentMethod: String,
    val paymentPhoneNumber:String,
   /* val delivery_person:Int*/
)
