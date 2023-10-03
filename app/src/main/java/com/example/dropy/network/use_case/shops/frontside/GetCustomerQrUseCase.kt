package com.example.dropy.network.use_case.shops.frontside

import android.content.Context
import com.example.dropy.network.models.customerqr.CustomerQrResponse
import com.example.dropy.network.models.favouriteshop.favouriteShopRes.FavouriteShopResponse
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetCustomerQrUseCase (
    private val shopFrontendRepository: ShopFrontendRepository
) {
    suspend fun getCustomerQr(order_id : Int, context: Context, show: Boolean, token: String): Flow<Resource<CustomerQrResponse?>> {
        return shopFrontendRepository.getCustomerQr(order_id = order_id, context = context, show = show, token = token)
    }
}