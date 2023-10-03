package com.example.dropy.network.use_case.cart

import com.example.dropy.network.models.PaymentReq
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.repositories.cart.CartRepository
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutDataClass
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class OrderCheckOutUseCase(
    private val cartRepository: CartRepository
) {
    suspend fun orderCheckOut(
        paymentReq: PaymentReq
    ): Flow<Resource<ActionResultDataClass?>> {
        return cartRepository.orderCheckOut(
            paymentReq = paymentReq
        )
    }
}