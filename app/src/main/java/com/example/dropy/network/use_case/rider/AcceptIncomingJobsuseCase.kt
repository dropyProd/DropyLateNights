package com.example.dropy.network.use_case.rider

import android.content.Context
import com.example.dropy.network.models.acceptjob.AcceptJobResponse
import com.example.dropy.network.models.acceptjob.request.AcceptJobBody
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.reviewrider.ReviewRiderDataClass
import com.example.dropy.network.repositories.rider.RiderRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class AcceptIncomingJobsuseCase (
    private val riderRepository: RiderRepository
) {
    suspend fun acceptIncomingJobs(
        riderId: Int,
        deliveryId: Int,
        acceptJobBody: AcceptJobBody,
        context: Context
    ): Flow<Resource<AcceptJobResponse?>> {
        return riderRepository.acceptIncomingJobs(
            riderId = riderId, deliveryId = deliveryId, acceptJobBody = acceptJobBody, context = context
        )
    }

}