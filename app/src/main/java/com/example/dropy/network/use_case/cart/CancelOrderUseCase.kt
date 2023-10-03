package com.example.dropy.network.use_case.cart

import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.repositories.cart.CartRepository
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class CancelOrderUseCase(
    private val cartRepository: CartRepository
) {
    suspend fun cancelOrder(
        orderId: Int,
        reasonForCanceling: String?,
        appViewModel: AppViewModel
    ): Flow<Resource<ActionResultDataClass?>> {
        return cartRepository.cancelOrder(
            orderId = orderId, reasonForCanceling = reasonForCanceling, appViewModel = appViewModel
        )
    }
}