package com.example.dropy.network.services.payment

import com.example.dropy.network.models.payment.PaymentPojo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PaymentService {
    @FormUrlEncoded
    @POST("transactions/topupwallet")
  suspend fun pay(
        @Field("firebase_uid") firebase_uid: String,
        @Field("amount") amount: Int,
        @Field("phone_number") phone_number: String,
    ): Response<PaymentPojo>
}