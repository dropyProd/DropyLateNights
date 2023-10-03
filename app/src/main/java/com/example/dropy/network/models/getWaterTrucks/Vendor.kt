package com.example.dropy.network.models.getWaterTrucks

data class Vendor(
    val bank_account: Any,
    val city: Any,
    val county: Any,
    val description: String,
    val holidays_open: Boolean,
    val id: String,
    val is_active: Boolean,
    val logo: Any,
    val mpesa_paybill: Any,
    val name: String,
    val operating_all_day: Boolean,
    val owner: String,
    val phone_number: Any,
    val saturday_closing_time: String,
    val saturday_open: Boolean,
    val saturday_opening_time: String,
    val street: Any,
    val sub_county: Any,
    val sunday_closing_time: String,
    val sunday_open: Boolean,
    val sunday_opening_time: String,
    val total_earnings: String,
    val weekday_closing_time: String,
    val weekday_opening_time: String
)