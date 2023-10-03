package com.example.dropy.network.use_case.shops.backside

import com.example.dropy.network.models.completedorders.CompletedOrdersResponse
import com.example.dropy.network.models.pendingOrders.ShopPendingOrders
import com.example.dropy.network.repositories.shop.back.ShopBackendRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetShopCompletedOrdersUseCase (
    private val shopBackendRepository: ShopBackendRepository
) {
    suspend fun getShopCompletedOrders(token: String,shopId: String): Flow<Resource<CompletedOrdersResponse?>> {
        return shopBackendRepository.getShopCompletedOrders(token,shopId = shopId)
    }
}