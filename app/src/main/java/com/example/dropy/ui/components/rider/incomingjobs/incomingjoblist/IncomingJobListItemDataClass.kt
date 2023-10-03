package com.example.dropy.ui.components.rider.incomingjobs.incomingjoblist

data class IncomingJobListItemDataClass(
    val profilePicUrl:String? = null,
    val customerFirstName:String? =null,
    val customerLastName:String? = null,
    val addressName:String ?= null,
    val cost:Int?= null,
    val jobType:String?= null,
    val timeInMin:Int?= null,
    val orderId: Int?= null,
    val deliveryId: Int?= null,

)