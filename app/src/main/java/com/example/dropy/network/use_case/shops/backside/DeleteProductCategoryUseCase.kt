package com.example.dropy.network.use_case.shops.backside

import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.deleteproductcategory.DeleteProductCategoryResponse
import com.example.dropy.network.repositories.shop.back.ShopBackendRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class DeleteProductCategoryUseCase(
    private val shopBackendRepository: ShopBackendRepository
) {
    suspend fun deleteProductCategory(
        token: String,
        productId: Int
    ): Flow<Resource<DeleteProductCategoryResponse?>> {
        return shopBackendRepository.deleteProductCategory(token = token, productId = productId)
    }
}