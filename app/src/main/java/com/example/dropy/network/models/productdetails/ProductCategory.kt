package com.example.dropy.network.models.productdetails


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductCategory(
    @Json(name = "category_name")
    val category_name: String?,
    @Json(name = "id")
    val id: Int?
)