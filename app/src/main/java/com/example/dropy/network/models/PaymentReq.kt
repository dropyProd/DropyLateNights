package com.example.dropy.network.models

data class PaymentReq(
   val user_id : String,
   val amount : Int,
   val shop_id : String,
   val order_id : String,
   val cart_id : String,
)
