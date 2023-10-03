package com.example.dropy.network.models.pendingOrders

data class PendingOrder(
    val customer_name: String?,
    val delivery_location: DeliveryLocation?,
    val id: Int?,
    val is_paid: Boolean?,
    val order_number: String?,
    val order_status: String?,
    val order_total: Int?,
    val pending_processing: Boolean?,
    val shop_lat: Double?,
    val shop_lon: Double?
)