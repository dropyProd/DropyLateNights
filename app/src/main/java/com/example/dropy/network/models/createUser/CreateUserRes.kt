package com.example.dropy.network.models.createUser

data class CreateUserRes(
    val dropyuser: Dropyuser?,
    val otp: Any?,
    val phone_number_verified: Boolean?
)