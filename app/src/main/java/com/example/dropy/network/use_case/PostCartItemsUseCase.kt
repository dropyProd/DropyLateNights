package com.example.dropy.network.use_case

import com.example.dropy.network.models.AddCartReq
import com.example.dropy.network.models.PostCartRes
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.favouriteshop.FavShopDataClass
import com.example.dropy.network.repositories.cart.CartRepository
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostCartItemsUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(
        token: String,
       addCartReq: AddCartReq
    ): Flow<Resource<PostCartRes?>> {
        return cartRepository.addCartItems(
           token, addCartReq
        )
    }
}