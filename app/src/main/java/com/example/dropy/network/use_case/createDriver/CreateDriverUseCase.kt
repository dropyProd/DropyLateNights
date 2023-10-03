package com.example.dropy.network.use_case.createDriver

import com.example.dropy.network.models.addWaterDriver.AddWaterDriverReq
import com.example.dropy.network.models.addWaterDriversRes.AddWaterDriverRes
import com.example.dropy.network.repositories.waterpoint.WaterRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateDriverUseCase @Inject constructor(
    private val waterRepository: WaterRepository
) {
    suspend operator fun invoke(
        token: String,
        addWaterDriverReq: AddWaterDriverReq
    ): Flow<Resource<AddWaterDriverRes?>> {
        return waterRepository.createDriver(
            token = token, addWaterDriverReq = addWaterDriverReq
        )
    }
}