package com.example.dropy.network.use_case.addWaterTruckUseCase

import android.content.Context
import android.net.Uri
import com.example.dropy.network.models.AllProductsRes
import com.example.dropy.network.models.CreateWaterPointRes
import com.example.dropy.network.models.ShopsResponseNew
import com.example.dropy.network.models.addWaterTruckRes.AddWaterTruckRes
import com.example.dropy.network.models.createShop.createShopReq
import com.example.dropy.network.models.shops.ShopsResponse
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.network.repositories.waterpoint.WaterRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddWaterTruckUseCase @Inject constructor(
    private val waterRepository: WaterRepository
) {
    suspend operator fun invoke(
        token: String,
        vendor: String,
        license_plate: String,
        capacity: Int?,
        current_location: String,
        name: String,
        model: String,
        year: Int,
        is_active: Boolean,
        is_available: Boolean,
        registered_latitude: String,
        registered_longitude: String,
        current_latitude: String,
        current_longitude: String,
        shop_cover_photo: Uri?,
        image: Uri?,
        context: Context
    ): Flow<Resource<AddWaterTruckRes?>>  {
        return waterRepository.addWaterTruck(
            token = token,
            vendor = vendor,
            license_plate = license_plate,
            capacity = capacity,
            current_location = current_location,
            name = name,
            model = model,
            year = year,
            is_active = is_active,
            is_available = is_available,
            registered_latitude = registered_latitude,
            registered_longitude = registered_longitude,
            current_latitude = current_latitude,
            current_longitude = current_longitude,
            shop_cover_photo = shop_cover_photo,
            image = image,
            context = context
        )
    }
}