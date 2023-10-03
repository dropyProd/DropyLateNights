package com.example.dropy.network.use_case.getIndividualOrders

import com.example.dropy.network.models.GetIndividualOrders.GetIndividualOrdersRes
import com.example.dropy.network.repositories.waterpoint.WaterRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIndividualOrdersUseCase @Inject constructor(
    private val waterRepository: WaterRepository
) {
    suspend operator fun invoke(
        token: String
    ): Flow<Resource<GetIndividualOrdersRes?>> {
        return waterRepository.getIndividualWaterOrders(
            token = token
        )
    }
}