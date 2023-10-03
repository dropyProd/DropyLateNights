package com.example.dropy.network.use_case.shops.frontside

import com.example.dropy.network.models.customerorders.CustomerOrdersResponse
import com.example.dropy.network.models.shops.ShopsResponse
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetCustomerOrdersUseCase (
    private val shopFrontendRepository: ShopFrontendRepository
) {
    suspend fun getCustomerOrders(token: String,customer_id: String): Flow<Resource<CustomerOrdersResponse?>> {
        return shopFrontendRepository.getCustomerOrders(token = token, customer_id = customer_id)
    }
}