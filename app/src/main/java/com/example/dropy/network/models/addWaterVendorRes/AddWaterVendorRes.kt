package com.example.dropy.network.models.addWaterVendorRes

data class AddWaterVendorRes(
    val bank_account: String,
    val city: String,
    val county: String,
    val description: String,
    val holidays_open: Boolean,
    val id: String,
    val is_active: Boolean,
    val logo: String,
    val mpesa_paybill: String,
    val name: String,
    val operating_all_day: Boolean,
    val owner: Owner,
    val phone_number: String,
    val saturday_closing_time: String,
    val saturday_open: Boolean,
    val saturday_opening_time: String,
    val street: String,
    val sub_county: String,
    val sunday_closing_time: String,
    val sunday_open: Boolean,
    val sunday_opening_time: String,
    val total_earnings: String,
    val weekday_closing_time: String,
    val weekday_opening_time: String
)