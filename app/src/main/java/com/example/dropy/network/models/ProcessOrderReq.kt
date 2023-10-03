package com.example.dropy.network.models

data class ProcessOrderReq(
    val customer: String?,
    val shop: String?,
    val is_placed: Boolean? = true,
    val is_pending: Boolean?= true,
    val is_accepted: Boolean?=false,
    val is_canceled: Boolean?= false,
    val reason_for_cancelling: String?="",
    val is_checked_out: Boolean?= false,
    val is_completed: Boolean?=false,
    val is_deleted: Boolean?=false
)