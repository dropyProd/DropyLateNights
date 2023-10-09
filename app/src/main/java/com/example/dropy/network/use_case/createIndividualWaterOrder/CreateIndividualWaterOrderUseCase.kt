package com.example.dropy.network.use_case.createIndividualWaterOrder

import com.example.dropy.network.models.createIndividualWaterOrder.CreateIndividualWaterOrderRes
import com.example.dropy.network.models.individualWaterOrder.IndividualWaterOrderReq
import com.example.dropy.network.repositories.waterpoint.WaterRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateIndividualWaterOrderUseCase @Inject constructor(
    private val waterRepository: WaterRepository
) {
    suspend operator fun invoke(
        token: String,
        individualWaterOrderReq: IndividualWaterOrderReq
    ): Flow<Resource<CreateIndividualWaterOrderRes?>> {
        return waterRepository.createIndividualWaterOrder(
            token = token,
            individualWaterOrderReq = individualWaterOrderReq
        )
    }
}