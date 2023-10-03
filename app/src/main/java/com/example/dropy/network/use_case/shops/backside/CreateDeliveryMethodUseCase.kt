package com.example.dropy.network.use_case.shops.backside

import com.example.dropy.network.models.AllProductsRes
import com.example.dropy.network.models.CreateMethodReq
import com.example.dropy.network.models.CreateMethodRes
import com.example.dropy.network.models.ShopsResponseNew
import com.example.dropy.network.models.shops.ShopsResponse
import com.example.dropy.network.repositories.shop.back.ShopBackendRepository
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class CreateDeliveryMethodUseCase(
    private val shopBackendRepository: ShopBackendRepository
) {
    suspend operator fun invoke(
        token: String,
        createMethodReq: CreateMethodReq
    ): Flow<Resource<CreateMethodRes?>>{
        return shopBackendRepository.createDeliveryMethod(token, createMethodReq)
    }
}