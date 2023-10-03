package com.example.dropy.network.models

data class ShopsResponseNewItem(
    val holidays_open: Boolean?,
    val id: String?,
    val operating_all_day: Boolean?,
    val phone_number: String?,
    val phone_number_two: String?,
    val saturday_closing_time: String?,
    val saturday_open: Boolean?,
    val saturday_opening_time: String?,
    val shop_category: String?,
    val shop_cover_photo: String?,
    val shop_location: String?,
    val shop_logo: String?,
    val shop_name: String?,
    val shop_owner: String?,
    val shop_type: String?,
    val sunday_closing_time: String?,
    val sunday_open: Boolean?,
    val sunday_opening_time: String?,
    val weekday_closing_time: String?,
    val weekday_opening_time: String?
)