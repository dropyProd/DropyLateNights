package com.example.dropy.network.models.customerorders

data class Response(
    val cust_location: CustLocation?,
    val customer: Int?,
    val date_added: String?,
    val id: Int?,
    val is_paid: Boolean?,
    val number_of_items: Int?,
    val order_number: String?,
    val order_status: String?,
    val shop: Shop?,
    val total_price: Int?
)