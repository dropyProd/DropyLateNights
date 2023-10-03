package com.example.dropy.network.use_case.cart

import com.example.dropy.network.models.cart.GetCartResponse
import com.example.dropy.network.models.cart.cartequest.CartRequest
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.repositories.cart.CartRepository
import com.example.dropy.network.repositories.shop.back.ShopBackendRepository
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetCartItemsUseCase(
    private val cartRepository: CartRepository
) {
    suspend fun getCartItems(
        customerId: Int,
        cartRequest: CartRequest,
        appViewModel: AppViewModel
    ): Flow<Resource<GetCartResponse?>> {
        return cartRepository.getCartItems(
            customerId = customerId,
            cartRequest = cartRequest,
            appViewModel = appViewModel
        )
    }
}