package com.example.dropy.network.models.shopdetails


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShopDetailsResponse(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "number_0f_followers")
    val number_0f_followers: Int?,
    @Json(name = "number_of_orders")
    val number_of_orders: Int?,
    @Json(name = "phone_number")
    val phone_number: String?,
    @Json(name = "product_categories")
    val product_categories: List<ProductCategory>?,
    @Json(name = "products")
    val products: List<Product>?,
    @Json(name = "shop_cover_photo_url")
    val shop_cover_photo_url: String?,
    @Json(name = "shop_location")
    val shop_location: ShopLocation?,
    @Json(name = "shop_logo_url")
    val shop_logo_url: String?,
    @Json(name = "shop_name")
    val shop_name: String?
)