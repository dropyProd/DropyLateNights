package com.example.dropy.network.models.commondataclasses

import com.google.gson.annotations.SerializedName

data class ErrorMpesaResponse(

@SerializedName("errors")
val errors: String,
@SerializedName("resultCode")
val resultCode: Int

)