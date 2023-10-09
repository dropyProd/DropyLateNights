package com.example.dropy.network.models

data class createUserRes(
    val first_name: String?,
//    val id: String?,
    val last_name: String?,
    val password1: String?,
    val password2: String?,
    val phone_number: Int?,
    val dropy_role: String?
)