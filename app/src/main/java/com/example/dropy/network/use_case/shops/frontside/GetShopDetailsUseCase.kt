package com.example.dropy.network.use_case.shops.frontside

import com.example.dropy.network.models.productdetails.ProductDetailsResponse
import com.example.dropy.network.models.shopdetails.ShopDetailsResponse
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetShopDetailsUseCase (
    private val shopFrontendRepository: ShopFrontendRepository
) {
    suspend fun getShopDetails(token: String, shopId: String): Flow<Resource<ShopDetailsResponse?>> {
        return shopFrontendRepository.getShopDetails(token,shopId = shopId)
    }
}