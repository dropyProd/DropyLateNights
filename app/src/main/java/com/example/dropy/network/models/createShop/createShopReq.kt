package com.example.dropy.network.models.createShop

data class createShopReq(
    val shop_cover_photo: String? =null,
    val shop_logo: String?= null,
    val holidays_open: Boolean?,
    val managers: List<Int>?,
    val operating_all_day: Boolean?,
    val phone_number: String?,
    val phone_number_two: String?,
    val saturday_closing_time: String?,
    val saturday_open: Boolean?,
    val saturday_opening_time: String?,
    val shop_category: Int?,
    val shop_location: Int?,
    val shop_name: String?,
    val shop_owner: String?,
    val shop_type: String?,
    val sunday_closing_time: String?,
    val sunday_open: Boolean?,
    val sunday_opening_time: String?,
    val weekday_closing_time: String?,
    val weekday_opening_time: String?
)