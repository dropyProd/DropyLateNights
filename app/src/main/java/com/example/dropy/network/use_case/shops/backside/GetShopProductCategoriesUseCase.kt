package com.example.dropy.network.use_case.shops.backside

import android.content.Context
import com.example.dropy.network.models.getshopproductcategories.GetShopProductCategoriesResponse
import com.example.dropy.network.models.shopqr.ShopQrResponse
import com.example.dropy.network.repositories.shop.back.ShopBackendRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetShopProductCategoriesUseCase(
    private val shopBackendRepository: ShopBackendRepository
) {
    suspend fun getShopProductCategories(
        token: String,
        shopId: String
    ): Flow<Resource<GetShopProductCategoriesResponse?>> {
        return shopBackendRepository.getShopProductCategories(token = token, shopId = shopId)
    }
}