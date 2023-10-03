package com.example.dropy.network.use_case.getCollectionPointOrder

import com.example.dropy.network.models.getWaterPointOrders.GetWaterPointOrdersRes
import com.example.dropy.network.repositories.waterpoint.WaterRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCollectionPointOrdersUseCase @Inject constructor(
    private val waterRepository: WaterRepository
) {
    suspend operator fun invoke(
        token: String
    ): Flow<Resource<GetWaterPointOrdersRes?>> {
        return waterRepository.getCollectionPointOrder(
            token = token
        )
    }
}