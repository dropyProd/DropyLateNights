package com.example.dropy.network.use_case.shops.backside

import android.content.Context
import android.net.Uri
import com.example.dropy.network.models.addshop.AddShopResponse
import com.example.dropy.network.models.createShop.createShopReq
import com.example.dropy.network.models.shopqr.ShopQrResponse
import com.example.dropy.network.repositories.shop.back.ShopBackendRepository
import com.example.dropy.ui.components.commons.AddressDataClass
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class AddShopUseCase  (
    private val shopBackendRepository: ShopBackendRepository
) {
    suspend fun addShop(
        token: String,
        shopOwner: String,
        shopname: String,
                        shopLocation: AddressDataClass?,
                        shopPhoneOne: String,
                        firebase_uid: String,
                        shop_cover_photo: Uri?,
                        shop_logo: Uri?,
                        createShopReq: createShopReq,
                        shopCategory: Int,
                        context: Context): Flow<Resource<createShopReq?>> {
        return shopBackendRepository.addShop(
            token = token,
            shopOwner = shopOwner,
            shopname = shopname,
            shopLocation = shopLocation,
            shopPhoneOne = shopPhoneOne,
            firebase_uid = firebase_uid,
            shop_cover_photo = shop_cover_photo,
            shop_logo = shop_logo,
            shopCategory = shopCategory,
            createShopReq= createShopReq,
            context = context
        )
    }
}