package com.example.dropy.network.models.productdetails


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDetailsResponse(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "product_category")
    val product_category: ProductCategory?,
    @Json(name = "product_description")
    val product_description: String?,
    @Json(name = "product_images")
    val product_images: List<ProductImage>?,
    @Json(name = "product_main image")
    val productMainImage: String?,
    @Json(name = "product_name")
    val product_name: String?,
    @Json(name = "product_price")
    val product_price: Int?,
    @Json(name = "shop_id")
    val shopshop_idId: Int?
)