package com.example.dropy.network.models.shops


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Shop(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "shop_category")
    val shop_category: ShopCategory?,
    @Json(name = "shop_cover_photo_url")
    val shop_cover_photo_url: String?,
    @Json(name = "shop_location")
    val shop_location: ShopLocation?,
    @Json(name = "shop_logo_url")
    val shop_logo_url: String?,
    @Json(name = "shop_name")
    val shop_name: String?,
    @Json(name = "shop_owner")
    val shop_owner: String?,
    @Json(name = "phone_number")
    val phone_number: String?,
    @Json(name = "phone_number_two")
    val phone_number_two: String?,
    @Json(name = "shop_type")
    val shop_type: String?,
    @Json(name = "operating_all_day")
    val operating_all_day: Boolean?,
    @Json(name = "holidays_open")
    val holidays_open: Boolean?,
    @Json(name = "saturday_open")
    val saturday_open: Boolean?,
    @Json(name = "sunday_open")
    val sunday_open: Boolean?,
    @Json(name = "weekday_opening_time")
    val weekday_opening_time: String?,
    @Json(name = "weekday_closing_time")
    val weekday_closing_time: String?,
    @Json(name = "saturday_opening_time")
    val saturday_opening_time: String?,
    @Json(name = "saturday_closing_time")
    val saturday_closing_time: String?,
    @Json(name = "sunday_closing_time")
    val sunday_closing_time: String?,
    @Json(name = "sunday_opening_time")
    val sunday_opening_time: String?
)