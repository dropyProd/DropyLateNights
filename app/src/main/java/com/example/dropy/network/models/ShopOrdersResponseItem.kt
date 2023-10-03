package com.example.dropy.network.models

data class ShopOrdersResponseItem(
    val customer: String?,
    val date_added: String?,
    val id: Int?,
    val is_accepted: Boolean?,
    val is_canceled: Boolean?,
    val is_checked_out: Boolean?,
    val is_completed: Boolean?,
    val is_deleted: Boolean?,
    val is_pending: Boolean?,
    val is_placed: Boolean?,
    val order_number: String?,
    val reason_for_cancelling: String?,
    val shop: String?
)