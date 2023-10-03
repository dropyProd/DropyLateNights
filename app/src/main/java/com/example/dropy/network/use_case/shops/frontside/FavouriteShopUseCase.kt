package com.example.dropy.network.use_case.shops.frontside

import com.example.dropy.network.models.AddShopFavRes
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.favouriteshop.FavShopDataClass
import com.example.dropy.network.models.shopdetails.ShopDetailsResponse
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class FavouriteShopUseCase (
    private val shopFrontendRepository: ShopFrontendRepository
) {
    suspend fun favouriteShop(token: String,favShopDataClass: FavShopDataClass): Flow<Resource<AddShopFavRes?>> {
        return shopFrontendRepository.favouriteShop(token = token, favShopDataClass = favShopDataClass)
    }
}