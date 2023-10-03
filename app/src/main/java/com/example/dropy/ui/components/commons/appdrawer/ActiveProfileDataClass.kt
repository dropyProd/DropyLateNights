package com.example.dropy.ui.components.commons.appdrawer


object ProfileTypes{
    const val CUSTOMER = "customer"
    const val WATER_POINT = "water point"
    const val WATER_VENDOR = "truck company"
    const val WATER_TRUCK = "truck driver"
    const val SHOP = "shop"
    const val DELIVERY_PERSON = "delivery person"
    const val RIDER = "rider"
    const val LOGISTICSTREXPRESS = "Logistics Truck Express"
    const val TUKTUKRIDER = "TukTuk Rider"
    const val RIDEREXPRESS = "Riders Express"
    const val COURIEREXPRESS = "Couriers Express"
    const val LOGISTICTRDRIVERS = "Logistics Truck Drivers"
}

data class ActiveProfileDataClass(
    val type:String="",
    val name:String="",
    val id:String="",
    val idTemp:String= "",
/*    val dropyuserid:Int,*/
)
