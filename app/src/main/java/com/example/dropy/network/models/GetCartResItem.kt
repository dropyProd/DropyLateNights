package com.example.dropy.network.models

data class GetCartResItem(
    val cart: Int?,
    val created_at: String?,
    val id: Int?,
    val product: Product?,
    val quantity: Int?,
    val updated_at: String?
)