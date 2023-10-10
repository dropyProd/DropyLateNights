package com.example.dropy.network.use_case.modifyApprovalRequest

import com.example.dropy.network.models.approvalRequests.ApprovalRequestsRes
import com.example.dropy.network.models.approvalRequests.ApprovalRequestsResItem
import com.example.dropy.network.models.modifyApprovalRequestRes.ModifyApprovalRequestRes
import com.example.dropy.network.repositories.waterpoint.WaterRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ModifyApprovalRequestsUseCase @Inject constructor(
    private val waterRepository: WaterRepository
) {
    suspend operator fun invoke(
        token: String,
        approvalRequestsResItem: ApprovalRequestsResItem
    ): Flow<Resource<ModifyApprovalRequestRes?>> {
        return waterRepository.modifyApprovalRequest(
            token = token,
            approvalRequestsResItem = approvalRequestsResItem
        )
    }
}