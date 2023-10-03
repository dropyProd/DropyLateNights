package com.example.dropy.network.models

data class createUserRes(
    val first_name: String?,
    val id: String?,
    val last_name: String?,
    val password: String?,
    val phone_number: Int?,
    val profile_image: Any?,
    val username: String?
)