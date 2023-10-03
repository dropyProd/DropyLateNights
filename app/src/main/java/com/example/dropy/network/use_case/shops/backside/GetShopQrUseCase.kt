package com.example.dropy.network.use_case.shops.backside

import android.content.Context
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.favouriteshop.FavShopDataClass
import com.example.dropy.network.models.shopqr.ShopQrResponse
import com.example.dropy.network.repositories.shop.back.ShopBackendRepository
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetShopQrUseCase(
    private val shopBackendRepository: ShopBackendRepository
) {
    suspend fun getShopQr(
        token: String,
        order_id: Int,
        context: Context
    ): Flow<Resource<ShopQrResponse?>> {
        return shopBackendRepository.getShopQr(
            token = token,
            order_id = order_id,
            context = context
        )
    }
}