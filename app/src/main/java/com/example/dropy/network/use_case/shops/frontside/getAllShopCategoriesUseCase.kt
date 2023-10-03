package com.example.dropy.network.use_case.shops.frontside

import com.example.dropy.network.models.shopCategories.ShopCategoriesResponse
import com.example.dropy.network.models.shops.ShopsResponse
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetAllShopCategoriesUseCase(
    private val shopFrontendRepository: ShopFrontendRepository
) {
    suspend  fun shopCategories(token: String): Flow<Resource<ShopCategoriesResponse?>> {
        return shopFrontendRepository.getAllShopCategories(token)
    }
}