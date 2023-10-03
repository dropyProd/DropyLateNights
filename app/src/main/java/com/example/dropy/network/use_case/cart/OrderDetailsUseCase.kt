package com.example.dropy.network.use_case.cart

import com.example.dropy.data.network.shops.shopsfront.dtos.orderdetails.Order
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.repositories.cart.CartRepository
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class OrderDetailsUseCase (
    private val cartRepository: CartRepository
) {
    suspend fun orderDetails(
        orderId: Int
    ): Flow<Resource<Order?>> {
        return cartRepository.orderDetails(
            orderId = orderId
        )
    }
}