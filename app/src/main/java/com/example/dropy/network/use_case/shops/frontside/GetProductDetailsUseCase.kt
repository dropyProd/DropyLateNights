package com.example.dropy.network.use_case.shops.frontside

import com.example.dropy.network.models.productdetails.ProductDetailsResponse
import com.example.dropy.network.models.shops.ShopsResponse
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetProductDetailsUseCase(
    private val shopFrontendRepository: ShopFrontendRepository
) {
    suspend fun getProductDetails(token: String, productId: Int): Flow<Resource<ProductDetailsResponse?>> {
        return shopFrontendRepository.getProductDetails(token, productId = productId)
    }
}