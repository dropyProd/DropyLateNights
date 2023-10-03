package com.example.dropy.network.models

data class RoutesPojo(
    val image: Int,
    val name: String,
    val route: () -> Unit
)
