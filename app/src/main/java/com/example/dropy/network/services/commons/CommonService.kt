package com.example.dropy.network.services.commons

import com.example.dropy.network.models.deliverymethods.DeliveryMethodResponse
import com.example.dropy.network.models.pools.RiderPoolsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface CommonService {

    @GET("delivery-method/")
    suspend fun getDeliveryMethods(@Header("Authorization") token: String): DeliveryMethodResponse
}