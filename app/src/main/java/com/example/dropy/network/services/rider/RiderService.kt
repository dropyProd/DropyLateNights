package com.example.dropy.network.services.rider

import android.content.Context
import com.example.dropy.network.models.acceptjob.AcceptJobResponse
import com.example.dropy.network.models.acceptjob.request.AcceptJobBody
import com.example.dropy.network.models.canceljob.CancelIncomingJobResponse
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.customerqr.body.CustomerQrBody
import com.example.dropy.network.models.favouriteshop.FavShopDataClass
import com.example.dropy.network.models.ongoingjobs.OngoingJobsResponse
import com.example.dropy.network.models.riderincomingjob.RiderIncomingJobResponse
import com.example.dropy.network.models.shopqr.qrbody.QrBody
import com.example.dropy.network.models.shops.ShopsResponse
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutDataClass
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RiderService {

    @GET("jobs/{riderId}")
    suspend fun getAllRiderIncomingJobs(@Path("riderId") riderId: Int): RiderIncomingJobResponse

    @GET("ongoing-jobs/{riderId}")
    suspend fun getAllRiderOngoingJobs(@Path("riderId") riderId: Int): RiderIncomingJobResponse

    @GET("accept-job/{riderId}/{deliveryId}")
    suspend fun acceptIncomingJobs(
        @Path("riderId") riderId: Int,
        @Path("deliveryId") deliveryId: Int
    ): AcceptJobResponse

    @POST("verify-qr")
    suspend fun verifyQr(@Body qrBody: QrBody): ActionResultDataClass

    @POST("verify-cust-qr")
    suspend fun verifyCustQr(@Body customerQrBody: CustomerQrBody): ActionResultDataClass


    @POST("cancel-job/{deliveryPersonId}/{deliveryId}")
    suspend fun cancelIncomingJob(
        @Path("deliveryPersonId") deliveryPersonId: Int,
        @Path("deliveryId") deliveryId: Int
    ): CancelIncomingJobResponse

    suspend fun getIncomingJobs(riderId: Int, context: Context): RiderIncomingJobResponse?
}