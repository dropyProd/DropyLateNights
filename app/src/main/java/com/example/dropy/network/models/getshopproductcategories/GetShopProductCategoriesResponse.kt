package com.example.dropy.network.models.getshopproductcategories


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetShopProductCategoriesResponse(
    @Json(name = "product_categories")
    val product_categories: List<ProductCategory>?
)