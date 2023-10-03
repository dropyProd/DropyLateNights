package com.example.dropy.network.models

data class ForgotPasswordReq(
    val phone_number: String,
    val otp: String
)