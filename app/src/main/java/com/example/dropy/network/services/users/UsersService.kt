package com.example.dropy.network.services.users

import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.createrider.CreateRiderRequest
import com.example.dropy.network.models.pools.RiderPoolsResponse
import com.example.dropy.network.models.reviewrider.ReviewRiderDataClass
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutDataClass
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsersService
{
    @POST("review-rider")
    suspend fun reviewRider(@Body reviewRiderDataClass: ReviewRiderDataClass): ActionResultDataClass

    @GET("pool")
    suspend fun getRiderPools(): RiderPoolsResponse

    @POST("create-rider")
    suspend fun createRider( @Body createRiderRequest: CreateRiderRequest): ActionResultDataClass

}