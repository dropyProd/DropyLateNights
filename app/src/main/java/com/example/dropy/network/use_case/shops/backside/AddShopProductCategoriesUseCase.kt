package com.example.dropy.network.use_case.shops.backside

import com.example.dropy.network.models.getshopproductcategories.GetShopProductCategoriesResponse
import com.example.dropy.network.models.shopProductCategories.ShopProductCategoriesResponse
import com.example.dropy.network.repositories.shop.back.ShopBackendRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class AddShopProductCategoriesUseCase(
    private val shopBackendRepository: ShopBackendRepository
) {
    suspend fun addShopProductCategories(
        token: String,
        shopId: Int
    ): Flow<Resource<ShopProductCategoriesResponse?>> {
        return shopBackendRepository.addShopProductCategories(token = token, shopId = shopId)
    }
}