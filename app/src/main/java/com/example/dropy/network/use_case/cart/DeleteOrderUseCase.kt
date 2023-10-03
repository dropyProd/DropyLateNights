package com.example.dropy.network.use_case.cart

import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.repositories.cart.CartRepository
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class DeleteOrderUseCase (
    private val cartRepository: CartRepository
) {
    suspend fun deleteOrder(
        orderId: Int, appViewModel: AppViewModel
    ): Flow<Resource<ActionResultDataClass?>> {
        return cartRepository.deleteOrder(
            orderId = orderId, appViewModel = appViewModel
        )
    }
}