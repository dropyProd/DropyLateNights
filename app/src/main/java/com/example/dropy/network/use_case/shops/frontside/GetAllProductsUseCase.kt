package com.example.dropy.network.use_case.shops.frontside

import com.example.dropy.network.models.AllProductsRes
import com.example.dropy.network.models.ShopsResponseNew
import com.example.dropy.network.models.shops.ShopsResponse
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetAllProductsUseCase(
    private val shopFrontendRepository: ShopFrontendRepository
) {
    suspend operator fun invoke(): Flow<Resource<AllProductsRes?>>{
        return shopFrontendRepository.getAllProducts()
    }
}