package com.example.dropy.network.services.shops

import com.example.dropy.data.network.shops.shopsfront.dtos.orderdetails.Order
import com.example.dropy.network.models.CreateMethodReq
import com.example.dropy.network.models.CreateMethodRes
import com.example.dropy.network.models.ShopOrdersResponse
import com.example.dropy.network.models.addproductcategory.AddProductCategoryRes
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.completedorders.CompletedOrdersResponse
import com.example.dropy.network.models.createShop.createShopReq
import com.example.dropy.network.models.deleteproductcategory.DeleteProductCategoryResponse
import com.example.dropy.network.models.getshopproductcategories.GetShopProductCategoriesResponse
import com.example.dropy.network.models.pendingOrders.ShopPendingOrders
import com.example.dropy.network.models.productCategoryReq
import com.example.dropy.network.models.shopCategories.ShopCategoriesResponse
import com.example.dropy.network.models.shopProductCategories.ShopProductCategoriesResponse
import com.example.dropy.network.models.shopqr.ShopQrResponse
import com.example.dropy.ui.components.shops.backside.dashboard.orders.orderdetails.OrderItemListItemDataClass
import retrofit2.Response
import retrofit2.http.*

interface ShopsBackendService {
    //backend
    @GET("shops/shops/{shopId}/completedorders")
    suspend fun getShopCompletedOrders(
        @Header("Authorization") token: String,
        @Path("shopId") shopId: String
    ): CompletedOrdersResponse

    @GET("shop/orders/")
    suspend fun getShopPendingOrders(
//        @Header("Authorization") token: String,
        @Query("shop") shopId: String
    ): ShopOrdersResponse

    @DELETE("shops/products/{product_id}")
    suspend fun deleteProduct(
        @Header("Authorization") token: String,
        @Path("product_id") productId: Int
    ): ActionResultDataClass

    @GET("shops/orders/{orderId}")
    suspend fun orderDetails(
        @Header("Authorization") token: String,
        @Path("orderId") orderId: Int
    ): Response<Order>

    @POST("shops/orders/{orderId}/processorder")
    suspend fun processOrder(
        @Header("Authorization") token: String,
        @Path("orderId") orderId: Int,
        @Query("list") list: List<OrderItemListItemDataClass>
    ): ActionResultDataClass

    @GET("shops/pickup-qr/{order_id}")
    suspend fun getShopQr(
        @Header("Authorization") token: String,
        @Path("order_id") order_id: Int
    ): ShopQrResponse

    @GET("shops/shops/{shopId}/productcategories")
    suspend fun getShopProductCategories(
        @Header("Authorization") token: String,
        @Path("shopId") shopId: String
    ): GetShopProductCategoriesResponse

    @POST("commons/delivery-method/")
    suspend fun createDeliveryMethod(
        @Header("Authorization") token: String,
        @Body createMethodReq: CreateMethodReq
    ): CreateMethodRes

    @POST("shop/product-category")
    suspend fun addShopProductCategories(
        @Header("Authorization") token: String,
        @Body productCategoryReq: productCategoryReq
    ): AddProductCategoryRes

    @GET("shops/shops/{shopId}/productcategories")
    suspend fun addShopProductCategories(
        @Header("Authorization") token: String,
        @Path("shopId") shopId: Int
    ): ShopProductCategoriesResponse

    @GET("shops/deleteshopproductcategory/{productId}")
    suspend fun deleteProductCategory(
        @Header("Authorization") token: String,
        @Path("productId") productId: Int
    ): DeleteProductCategoryResponse

    @GET("shops/addshop")
    suspend fun addShop(@Header("Authorization") token: String): createShopReq
}