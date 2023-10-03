package com.example.dropy.network.services.cart

import com.example.dropy.data.network.shops.shopsfront.dtos.orderdetails.Order
import com.example.dropy.network.models.*
import com.example.dropy.network.models.cart.GetCartResponse
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.favouriteshop.FavShopDataClass
import com.example.dropy.ui.components.shops.backside.dashboard.orders.orderdetails.OrderItemListItemDataClass
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutDataClass
import retrofit2.Response
import retrofit2.http.*

interface CartService {
    @GET("shops/customers/{customerId}/cart")
    suspend fun getCartItems(@Path("customerId") customerId: Int/* @Body cartRequest: CartRequest*/): GetCartResponse

    @GET("shop/cartitems/")
    suspend fun getCartItemsNew(
        @Header("Authorization") token: String,
        @Query("cart_id") cartNum: Int
    ): GetCartRes

    @POST("shop/shoppingcarts/")
    suspend fun createCartItems(
        @Header("Authorization") token: String,
        @Body createCartReq: CreateCartReq
    ): CreateCartRes

    @POST("shop/cartitems/")
    suspend fun postCartItems(
        @Header("Authorization") token: String,
        @Body addCartReq: AddCartReq
    ): PostCartRes


    @FormUrlEncoded
    @POST("shops/orders/cancelorder")
    suspend fun cancelOrder(
        @Field("order_id") orderId: Int,
        @Field("reason_for_cancelling") reasonForCanceling: String? = null
    ): ActionResultDataClass

    @FormUrlEncoded
    @POST("shops/orders/deleteorder")
    suspend fun deleteOrder(@Field("order_id") orderId: Int): ActionResultDataClass


    @POST("shop/orders/")
    suspend fun placeOrder(@Body placeOrderReq: PlaceOrderReq): PlaceOrderResponse

    @GET("shops/orders/{orderId}")
    suspend fun orderDetails(@Path("orderId") orderId: Int): Order

    @PUT("shop/orders/{orderId}/")
    suspend fun processOrder(
        @Body processOrderReq: ProcessOrderReq,
        @Path("orderId") orderId: Int
    ): PlaceOrderResponse

    @POST("transactions/payment")
    suspend fun orderCheckOut(
        @Body paymentReq: PaymentReq
    ): Response<PaymentRes>

    @GET("transactions/getstktransactionstatus/{transactionId}")
    suspend fun transactionStatus(
        @Path("transactionId") transactionId: Int
    ): TransactionStatusRes
}