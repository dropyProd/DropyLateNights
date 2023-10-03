package com.example.dropy.network.models.shopdetails


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductCategoryX(
    @Json(name = "category_name")
    val category_name: String?,
    @Json(name = "id")
    val id: Int?
)