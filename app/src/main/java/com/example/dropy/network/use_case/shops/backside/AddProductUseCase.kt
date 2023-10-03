package com.example.dropy.network.use_case.shops.backside

import android.content.Context
import android.net.Uri
import com.example.dropy.network.models.addproduct.AddProductRes
import com.example.dropy.network.models.addproduct.AddProductResNew
import com.example.dropy.network.models.addproductcategory.AddProductCategoryRes
import com.example.dropy.network.repositories.shop.back.ShopBackendRepository
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class AddProductUseCase(
    private val shopBackendRepository: ShopBackendRepository
) {
    suspend fun addProduct(
        type: String,
        token: String,
        shopId: String,
        productCategoryId: Int,
        productName: String,
        productDescription: String,
        productPrice: Int,
        productCoverPhoto: Uri?,
        //   dateAdded: String,
        numberInStack: Int,
        productId: Int,
        context: Context,
        appViewModel: AppViewModel
    ): Flow<Resource<AddProductResNew?>> {
        return shopBackendRepository.addProduct(
            type, token,shopId, productCategoryId,
            productName = productName,
            productDescription = productDescription,
            productPrice = productPrice,
            productCoverPhoto = productCoverPhoto,
            numberInStack = numberInStack,
            productId = productId,
            context = context,
            appViewModel = appViewModel
        )
    }
}