package com.example.dropy.network.repositories.shop.back

import android.content.Context
import android.net.Uri
import com.example.dropy.network.models.CreateMethodReq
import com.example.dropy.network.models.CreateMethodRes
import com.example.dropy.network.models.ShopOrdersResponse
import com.example.dropy.network.models.addproduct.AddProductRes
import com.example.dropy.network.models.addproduct.AddProductResNew
import com.example.dropy.network.models.addproductcategory.AddProductCategoryRes
import com.example.dropy.network.models.addshop.AddShopResponse
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.completedorders.CompletedOrdersResponse
import com.example.dropy.network.models.createShop.createShopReq
import com.example.dropy.network.models.deleteproductcategory.DeleteProductCategoryResponse
import com.example.dropy.network.models.favouriteshop.favouriteShopRes.FavouriteShopResponse
import com.example.dropy.network.models.getshopproductcategories.GetShopProductCategoriesResponse
import com.example.dropy.network.models.pendingOrders.ShopPendingOrders
import com.example.dropy.network.models.productCategoryReq
import com.example.dropy.network.models.shopProductCategories.ShopProductCategoriesResponse
import com.example.dropy.network.models.shopqr.ShopQrResponse
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.AddressDataClass
import com.example.dropy.ui.components.shops.backside.dashboard.orders.orderdetails.OrderItemListItemDataClass
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ShopBackendRepository {

    suspend fun getShopQr(
        token: String,
        order_id: Int,
        context: Context
    ): Flow<Resource<ShopQrResponse?>>

    suspend fun getShopProductCategories(
        token: String,
        shopId: String
    ): Flow<Resource<GetShopProductCategoriesResponse?>>

    suspend fun createDeliveryMethod(
        token: String,
        createMethodReq: CreateMethodReq
    ): Flow<Resource<CreateMethodRes?>>

    suspend fun addShopProductCategories(
        token: String,
        shopId: Int
    ): Flow<Resource<ShopProductCategoriesResponse?>>

    suspend fun addShopProductCategory(
        productCategoryReq: productCategoryReq,
        token: String
    ): Flow<Resource<AddProductCategoryRes?>>

    suspend fun addShop(
        token: String,
        shopOwner: String,
        shopname: String,
        shopLocation: AddressDataClass?,
        shopPhoneOne: String,
        firebase_uid: String,
        shop_cover_photo: Uri?,
        shop_logo: Uri?,
        shopCategory: Int,
        createShopReq: createShopReq,
        context: Context
    ): Flow<Resource</*AddShopResponse*/createShopReq?>>


    suspend fun addProduct(
        type: String,
        token: String,
        shopId: String,
        productCategoryId: Int,
        productName: String,
        productDescription: String,
        productPrice: Int,
        productCoverPhoto: Uri?,
        //   dateAdded: String,
        numberInStack: Int,
        productId: Int,
        context: Context,
        appViewModel: AppViewModel
    ): Flow<Resource<AddProductResNew?>>


    suspend fun deleteProduct(token: String, productId: Int): Flow<Resource<ActionResultDataClass?>>

    suspend fun deleteProductCategory(token: String,productId: Int): Flow<Resource<DeleteProductCategoryResponse?>>

    suspend fun processOrder(
        token: String,
        list: List<OrderItemListItemDataClass>,
        orderId: Int,
        appViewModel: AppViewModel
    ): Flow<Resource<ActionResultDataClass?>>

    suspend fun getShopPendingOrders(token: String, shopId: String): Flow<Resource<ShopOrdersResponse?>>
    suspend fun getShopCompletedOrders(
        token: String,
        shopId: String
    ): Flow<Resource<CompletedOrdersResponse?>>
}