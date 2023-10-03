package com.example.dropy.network.use_case.shops.backside

import com.example.dropy.network.models.ShopOrdersResponse
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.pendingOrders.ShopPendingOrders
import com.example.dropy.network.repositories.shop.back.ShopBackendRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetShopPendingOrdersUseCase (
    private val shopBackendRepository: ShopBackendRepository
) {
    suspend fun getShopPendingOrders(token: String, shopId: String): Flow<Resource<ShopOrdersResponse?>> {
        return shopBackendRepository.getShopPendingOrders(token, shopId = shopId)
    }
}