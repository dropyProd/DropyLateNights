package com.example.dropy.network.use_case.approvalRequests

import com.example.dropy.network.models.approvalRequests.ApprovalRequestsRes
import com.example.dropy.network.repositories.waterpoint.WaterRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApprovalRequestsUseCase @Inject constructor(
    private val waterRepository: WaterRepository
) {
    suspend operator fun invoke(
        token: String
    ): Flow<Resource<ApprovalRequestsRes?>> {
        return waterRepository.approvalRequests(
            token = token
        )
    }
}