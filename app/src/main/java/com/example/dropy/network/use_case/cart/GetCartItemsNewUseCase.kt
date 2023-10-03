package com.example.dropy.network.use_case.cart

import com.example.dropy.network.models.AllProductsRes
import com.example.dropy.network.models.GetCartRes
import com.example.dropy.network.models.ShopsResponseNew
import com.example.dropy.network.models.shops.ShopsResponse
import com.example.dropy.network.repositories.cart.CartRepository
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartItemsNewUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(
        token: String,
        cartId: Int
    ): Flow<Resource<GetCartRes?>>{
        return cartRepository.getCartItemsNew(token, cartId)
    }
}