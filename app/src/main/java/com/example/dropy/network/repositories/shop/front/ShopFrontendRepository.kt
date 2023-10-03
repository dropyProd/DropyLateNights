package com.example.dropy.network.repositories.shop.front

import android.content.Context
import com.example.dropy.network.models.AddShopFavRes
import com.example.dropy.network.models.AllProductsRes
import com.example.dropy.network.models.FakeProduct
import com.example.dropy.network.models.ShopsResponseNew
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.customerorders.CustomerOrdersResponse
import com.example.dropy.network.models.customerqr.CustomerQrResponse
import com.example.dropy.network.models.favouriteshop.FavShopDataClass
import com.example.dropy.network.models.favouriteshop.favouriteShopRes.FavouriteShopResponse
import com.example.dropy.network.models.payment.PaymentPojo
import com.example.dropy.network.models.productdetails.ProductDetailsResponse
import com.example.dropy.network.models.shopCategories.ShopCategoriesResponse
import com.example.dropy.network.models.shopdetails.ShopDetailsResponse
import com.example.dropy.network.models.shops.ShopsResponse
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ShopFrontendRepository {
    suspend fun getAllShops(token: String): Flow<Resource</*ShopsResponse*/ShopsResponseNew?>>
    //suspend fun getAllShops(): Flow<FakeProduct?>
    suspend fun getAllProducts(): Flow<Resource<AllProductsRes?>>
    suspend fun getShopProducts(shop: String): Flow<Resource<AllProductsRes?>>
    suspend fun getAllShopCategories(token: String): Flow<Resource<ShopCategoriesResponse?>>
    suspend fun getProductDetails(token: String, productId: Int): Flow<Resource<ProductDetailsResponse?>>
    suspend fun getShopDetails(token: String,shopId: String): Flow<Resource<ShopDetailsResponse?>>
    suspend fun favouriteShop(token: String, favShopDataClass: FavShopDataClass): Flow<Resource<AddShopFavRes?>>
    suspend fun getfavouriteShops(token: String,userId: String): Flow<Resource<FavouriteShopResponse?>>
    suspend fun getCustomerQr(order_id : Int, context: Context, show: Boolean,token: String): Flow<Resource<CustomerQrResponse?>>
    suspend fun getCustomerOrders(token: String, customer_id: String): Flow<Resource<CustomerOrdersResponse?>>
    suspend fun pay(
        firebase_uid: String,
        amount: Int,
        phone_number: String
    ): PaymentPojo?

}