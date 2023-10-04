package com.example.dropy.network.use_case.verifyDeliveryCode

import android.content.Context
import android.net.Uri
import com.example.dropy.network.models.AllProductsRes
import com.example.dropy.network.models.CreateWaterPointRes
import com.example.dropy.network.models.ShopsResponseNew
import com.example.dropy.network.models.VerifyDeliveryCodeReq
import com.example.dropy.network.models.createIndividualWaterOrder.CreateIndividualWaterOrderRes
import com.example.dropy.network.models.createShop.createShopReq
import com.example.dropy.network.models.getWaterPoints.GetWaterPointsRes
import com.example.dropy.network.models.individualWaterOrder.IndividualWaterOrderReq
import com.example.dropy.network.models.shops.ShopsResponse
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
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