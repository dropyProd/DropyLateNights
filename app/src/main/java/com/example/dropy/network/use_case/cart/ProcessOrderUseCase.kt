package com.example.dropy.network.use_case.cart

import com.example.dropy.network.models.PlaceOrderReq
import com.example.dropy.network.models.PlaceOrderResponse
import com.example.dropy.network.models.ProcessOrderReq
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.repositories.cart.CartRepository
import com.example.dropy.network.repositories.shop.back.ShopBackendRepository
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.shops.backside.dashboard.orders.orderdetails.OrderItemListItemDataClass
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class ProcessOrderUseCase(
    private val cartRepository: CartRepository
) {
    suspend fun processOrder(
        processOrderReq: ProcessOrderReq,
        id: Int,
    ): Flow<Resource<PlaceOrderResponse?>> {
        return cartRepository.processOrder(
            placeOrderReq = processOrderReq,
            id=id,
        )
    }
}