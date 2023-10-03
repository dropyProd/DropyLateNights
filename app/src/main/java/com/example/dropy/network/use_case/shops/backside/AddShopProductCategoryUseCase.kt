package com.example.dropy.network.use_case.shops.backside

import com.example.dropy.network.models.addproductcategory.AddProductCategoryRes
import com.example.dropy.network.models.productCategoryReq
import com.example.dropy.network.models.shopProductCategories.ShopProductCategoriesResponse
import com.example.dropy.network.repositories.shop.back.ShopBackendRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class AddShopProductCategoryUseCase (
    private val shopBackendRepository: ShopBackendRepository
) {
    suspend operator fun invoke( productCategoryReq: productCategoryReq,
                                 token: String): Flow<Resource<AddProductCategoryRes?>> {
        return shopBackendRepository.addShopProductCategory(productCategoryReq, token)
    }
}