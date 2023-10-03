package com.example.dropy.network.use_case.updateTruckLocation

import android.content.Context
import android.net.Uri
import com.example.dropy.network.models.AllProductsRes
import com.example.dropy.network.models.CreateWaterPointRes
import com.example.dropy.network.models.ShopsResponseNew
import com.example.dropy.network.models.createShop.createShopReq
import com.example.dropy.network.models.getWaterPoints.GetWaterPointsRes
import com.example.dropy.network.models.getWaterVendors.GetWaterVendorsRes
import com.example.dropy.network.models.shops.ShopsResponse
import com.example.dropy.network.models.topUp.TopUpReq
import com.example.dropy.network.models.updateTruckLocationReq.UpdateTruckLocationReq
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.network.repositories.waterpoint.WaterRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateTruckLocationUseCase @Inject constructor(
    private val waterRepository: WaterRepository
) {
    suspend operator fun invoke(
        token: String,
        updateTruckLocationReq: UpdateTruckLocationReq
    ): Flow<Resource<String?>> {
        return waterRepository.updateTruckLocation(
            token = token, updateTruckLocationReq = updateTruckLocationReq
        )
    }
}