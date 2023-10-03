package com.example.dropy.network.use_case.shops.backside

import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.shopProductCategories.ShopProductCategoriesResponse
import com.example.dropy.network.repositories.shop.back.ShopBackendRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class DeleteProductUseCase (
    private val shopBackendRepository: ShopBackendRepository
) {
    suspend fun deleteProduct(token: String, productId: Int): Flow<Resource<ActionResultDataClass?>> {
        return shopBackendRepository.deleteProduct(token, productId = productId)
    }
}