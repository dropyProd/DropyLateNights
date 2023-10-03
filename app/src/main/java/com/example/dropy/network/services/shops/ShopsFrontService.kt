package com.example.dropy.network.services.shops

import com.example.dropy.network.models.*
import com.example.dropy.network.models.cart.GetCartResponse
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.customerorders.CustomerOrdersResponse
import com.example.dropy.network.models.customerqr.CustomerQrResponse
import com.example.dropy.network.models.favouriteshop.FavShopDataClass
import com.example.dropy.network.models.favouriteshop.favouriteShopRes.FavouriteShopResponse
import com.example.dropy.network.models.productdetails.ProductDetailsResponse
import com.example.dropy.network.models.shopCategories.ShopCategoriesResponse
import com.example.dropy.network.models.shopdetails.ShopDetailsResponse
import com.example.dropy.network.models.shops.ShopsResponse
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutDataClass
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.*

interface ShopsFrontService {
//    @GET("shop")
    @GET("shop/shop/")
    suspend fun getAllShops(@Header("Authorization") token: String): /*ShopsResponse*/ShopsResponseNew

    @GET("shop/shopcategories")
    suspend fun getAllShopCategories( @Header("Authorization") token: String): ShopCategoriesResponse

    @GET("shop/products")
    suspend fun getAllProducts(): AllProductsRes

    @GET("shop/products/?")
    suspend fun getShopProducts(@Query("shop")shop: String): AllProductsRes

    @GET("shop/products/{productId}")
    suspend fun getProductDetails(@Header("Authorization") token: String, @Path("productId") productId: Int): ProductDetailsResponse

    @GET("shop/shops/{shopId}")
    suspend fun getShopDetails(@Header("Authorization") token: String, @Path("shopId") shopId: String): ShopDetailsResponse
/*@GET("products")
suspend fun getAllShops(): FakeProduct*/

    @GET("shop/customer-qr/{order_id}")
    suspend fun getCustomerQr(@Header("Authorization") token: String, @Path("order_id") order_id: Int): CustomerQrResponse

    @GET("shop/customer-order/{customer_id}")
    suspend fun getCustomerOrders(@Header("Authorization") token: String, @Path("customer_id") customer_id: String): CustomerOrdersResponse

    @FormUrlEncoded
    @POST("shop/orders/placeorder")
    suspend fun placeOrder(@Field("order_id") orderId: Int): Response<ActionResultDataClass>

    @FormUrlEncoded
    @POST("shop/orders/deleteorder")
    suspend fun deleteOrder(@Header("Authorization") token: String, @Field("order_id") orderId: Int): Response<ActionResultDataClass>

    @POST("shop/orders/{orderId}/checkoutorder")
    suspend fun orderCheckOut(
        @Path("orderId") orderId: Int,
        @Body checkOut: CheckoutDataClass
    ): Response<ActionResultDataClass>

    @GET("shop/fav-shops/{userId}")
    suspend fun getFavouriteShops(@Header("Authorization") token: String, @Path("userId") userid: String): FavouriteShopResponse

    @POST("shop/add-fav")
    suspend fun favouriteShop(@Header("Authorization") token: String, @Body favShopDataClass: FavShopDataClass): AddShopFavRes

    @GET("shop/customers/{customerId}/cart")
    suspend fun getCartItems(@Path("customerId") customerId: Int/* @Body cartRequest: CartRequest*/): Response<GetCartResponse>

    @FormUrlEncoded
    @POST("shop/orders/cancelorder")
    suspend fun cancelOrder(
        @Header("Authorization") token: String,
        @Field("order_id") orderId: Int,
        @Field("reason_for_cancelling") reasonForCanceling: String? = null
    ): Response<ActionResultDataClass>

}