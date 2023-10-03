package com.example.dropy.network.use_case.cart

import com.example.dropy.network.models.PostCartResponse.PostCartResponse
import com.example.dropy.network.models.cart.GetCartResponse
import com.example.dropy.network.models.cart.cartequest.CartRequest
import com.example.dropy.network.repositories.cart.CartRepository
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class PostCartItemsUseCase(
    private val cartRepository: CartRepository
) {
    suspend fun postCartItems(
        customerId: String,
        productId: Int,
        action: String,
        appViewModel: AppViewModel
    ): Flow<Resource<PostCartResponse?>> {
        return cartRepository.postCartItems(
            customerId = customerId, productId = productId, action = action, appViewModel = appViewModel
        )
    }
}