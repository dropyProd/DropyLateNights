package com.example.dropy.network.use_case.shops.frontside

import com.example.dropy.network.models.favouriteshop.favouriteShopRes.FavouriteShopResponse
import com.example.dropy.network.models.shops.ShopsResponse
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetFavouriteShopsUseCase (
    private val shopFrontendRepository: ShopFrontendRepository
) {
    suspend fun getFavouriteShops(token: String, userId: String): Flow<Resource<FavouriteShopResponse?>> {
        return shopFrontendRepository.getfavouriteShops(token = token, userId = userId)
    }
}