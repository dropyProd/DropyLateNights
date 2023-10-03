package com.example.dropy.network.repositories.rider

import android.content.Context
import com.example.dropy.network.models.acceptjob.AcceptJobResponse
import com.example.dropy.network.models.acceptjob.request.AcceptJobBody
import com.example.dropy.network.models.canceljob.CancelIncomingJobResponse
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.createrider.CreateRiderRequest
import com.example.dropy.network.models.customerqr.body.CustomerQrBody
import com.example.dropy.network.models.pools.RiderPoolsResponse
import com.example.dropy.network.models.reviewrider.ReviewRiderDataClass
import com.example.dropy.network.models.riderincomingjob.RiderIncomingJobResponse
import com.example.dropy.network.models.shopqr.qrbody.QrBody
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

interface RiderRepository {
    suspend fun reviewRider(reviewRiderDataClass: ReviewRiderDataClass): Flow<Resource<ActionResultDataClass?>>
    suspend fun getOngoingJobs(riderId: Int): Flow<Resource<RiderIncomingJobResponse?>>
    suspend fun verifyCustQr(
        customerQrBody: CustomerQrBody,
        context: Context
    ): Flow<Resource<ActionResultDataClass?>>

    suspend fun verifyQr(qrBody: QrBody, context: Context): Flow<Resource<ActionResultDataClass?>>
    suspend fun acceptIncomingJobs(
        riderId: Int,
        deliveryId: Int,
        acceptJobBody: AcceptJobBody,
        context: Context
    ): Flow<Resource<AcceptJobResponse?>>

    suspend fun cancelIncomingJob(
        deliveryPersonId: Int,
        deliveryId: Int
    ): Flow<Resource<CancelIncomingJobResponse?>>

    suspend fun getAllRiderPools(): Flow<Resource<RiderPoolsResponse?>>

    suspend fun createRider(createRiderRequest: CreateRiderRequest, context: Context): Flow<Resource<ActionResultDataClass?>>
    suspend fun getIncomingJobs(riderId: Int, context: Context): Flow<Resource<RiderIncomingJobResponse?>>
}