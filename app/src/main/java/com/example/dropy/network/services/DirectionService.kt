package com.example.dropy.network.services

import com.example.dropy.network.models.shops.ShopsResponse
import retrofit2.Response
import retrofit2.http.GET

interface DirectionService {

    @GET("shops/shops")
    suspend fun getRouteInfo(): Response<ShopsResponse>
}