package com.example.dropy.network.use_case.generateDeliveryCode

import com.example.dropy.network.repositories.waterpoint.WaterRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenerateDeliveryCodeUseCase @Inject constructor(
    private val waterRepository: WaterRepository
) {
    suspend operator fun invoke(
        token: String,
        taskId: String
    ): Flow<Resource<String?>>{
        return waterRepository.generateDeliveryCode(
            token = token,
            taskId = taskId
        )
    }
}