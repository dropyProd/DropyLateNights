package com.example.dropy.network.models

data class PlaceOrderReq(
    val is_accepted: Boolean?=false,
    val is_canceled: Boolean?= false,
    val is_checked_out: Boolean?= false,
    val is_completed: Boolean?=false,
    val is_deleted: Boolean?=false,
    val is_pending: Boolean?= true,
    val is_placed: Boolean? = true,
    val order_number: String?,
    val shop: String?,
    val customer: String?,
    val reason_for_cancelling: String?=""
)