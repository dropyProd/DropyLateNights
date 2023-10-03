package com.example.dropy.network.use_case.rider

import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.reviewrider.ReviewRiderDataClass
import com.example.dropy.network.models.riderincomingjob.RiderIncomingJobResponse
import com.example.dropy.network.repositories.rider.RiderRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetOngoingJobsUseCase(
    private val riderRepository: RiderRepository
) {
    suspend fun getOngoingJobs(
        riderId: Int
    ): Flow<Resource<RiderIncomingJobResponse?>> {
        return riderRepository.getOngoingJobs(
            riderId = riderId
        )
    }
}