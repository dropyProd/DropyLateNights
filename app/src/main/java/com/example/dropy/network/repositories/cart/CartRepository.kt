package com.example.dropy.network.repositories.cart

import com.example.dropy.data.network.shops.shopsfront.dtos.orderdetails.Order
import com.example.dropy.network.models.*
import com.example.dropy.network.models.PostCartResponse.PostCartResponse
import com.example.dropy.network.models.cart.GetCartResponse
import com.example.dropy.network.models.cart.cartequest.CartRequest
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.shops.backside.dashboard.orders.orderdetails.OrderItemListItemDataClass
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutDataClass
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CartRepository {


    suspend fun getCartItems(
        customerId: Int,
        cartRequest: CartRequest,
        appViewModel: AppViewModel
    ): Flow<Resource<GetCartResponse?>>

    suspend fun getCartItemsNew(
        token: String,
        cartId: Int
    ): Flow<Resource<GetCartRes?>>

    suspend fun createCart(
        token: String,
        createCartReq: CreateCartReq
    ): Flow<Resource<CreateCartRes?>>

    suspend fun postCartItems(
        customerId: String,
        productId: Int,
        action: String,
        appViewModel: AppViewModel
    ): Flow<Resource<PostCartResponse?>>

    suspend fun addCartItems(
        token: String,
        addCartReq: AddCartReq
    ): Flow<Resource<PostCartRes?>>


    suspend fun placeOrder(
        placeOrderReq: PlaceOrderReq,
        appViewModel: AppViewModel
    ): Flow<Resource<PlaceOrderResponse?>>

    suspend fun cancelOrder(
        orderId: Int,
        reasonForCanceling: String?,
        appViewModel: AppViewModel
    ): Flow<Resource<ActionResultDataClass?>>


    suspend fun deleteOrder(
        orderId: Int,
        appViewModel: AppViewModel
    ): Flow<Resource<ActionResultDataClass?>>

    suspend fun orderDetails(orderId: Int): Flow<Resource<Order?>>

    suspend fun processOrder(
        placeOrderReq: ProcessOrderReq,
        id: Int,
    ): Flow<Resource<PlaceOrderResponse?>>

    suspend fun orderCheckOut(
        paymentReq: PaymentReq
    ): Flow<Resource<ActionResultDataClass?>>

    suspend fun getTransactionStatus(
        transactionId: Int
    ): Flow<Resource<TransactionStatusRes?>>

}