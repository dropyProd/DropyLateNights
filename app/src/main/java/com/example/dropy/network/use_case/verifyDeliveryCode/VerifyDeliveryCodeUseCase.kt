package com.example.dropy.network.use_case.verifyDeliveryCode

import com.example.dropy.network.models.VerifyDeliveryCodeReq
import com.example.dropy.network.repositories.waterpoint.WaterRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VerifyDeliveryCodeUseCase @Inject constructor(
    private val waterRepository: WaterRepository
) {
    suspend operator fun invoke(
        token: String,
        taskId: String,
        verifyDeliveryCodeReq: VerifyDeliveryCodeReq
    ): Flow<Resource<String?>> {
        return waterRepository.verifyDeliveryCode(
            token = token,
            taskId = taskId,
            verifyDeliveryCodeReq = verifyDeliveryCodeReq
        )
    }
}