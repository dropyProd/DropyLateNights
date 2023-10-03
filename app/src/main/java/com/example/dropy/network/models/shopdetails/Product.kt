package com.example.dropy.network.models.shopdetails


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "image_url")
    val image_url: String?,
    @Json(name = "number_in_stack")
    val number_in_stack: Int?,
    @Json(name = "product_category")
    val product_category: ProductCategoryX?,
    @Json(name = "product_description")
    val product_description: String?,
    @Json(name = "product_name")
    val product_name: String?,
    @Json(name = "product_price")
    val product_price: Int?
)