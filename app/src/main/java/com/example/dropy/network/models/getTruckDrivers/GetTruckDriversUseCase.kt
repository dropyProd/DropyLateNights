package com.example.dropy.network.models.getTruckDrivers

import com.example.dropy.network.repositories.waterpoint.WaterRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTruckDriversUseCase @Inject constructor(
    private val waterRepository: WaterRepository
) {
    suspend operator fun invoke(
        token: String
    ): Flow<Resource<GetTruckDriversRes?>> {
        return waterRepository.getTruckDrivers(
            token = token
        )
    }
}