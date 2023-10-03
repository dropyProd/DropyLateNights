package com.example.dropy.network.use_case.cart

import com.example.dropy.network.models.PlaceOrderReq
import com.example.dropy.network.models.PlaceOrderResponse
import com.example.dropy.network.models.PostCartResponse.PostCartResponse
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.repositories.cart.CartRepository
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class PlaceOrderUseCase (
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(
        placeOrderReq: PlaceOrderReq,
        appViewModel: AppViewModel
    ): Flow<Resource<PlaceOrderResponse?>> {
        return cartRepository.placeOrder(
            placeOrderReq = placeOrderReq, appViewModel = appViewModel
        )
    }
}