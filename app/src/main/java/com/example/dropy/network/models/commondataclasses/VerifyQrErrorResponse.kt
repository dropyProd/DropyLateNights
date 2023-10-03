package com.example.dropy.network.models.commondataclasses

data class VerifyQrErrorResponse(
    val error: String?,
    val status: Int?,
    val success: Boolean?
)