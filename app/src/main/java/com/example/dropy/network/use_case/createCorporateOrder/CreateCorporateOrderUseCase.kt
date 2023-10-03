package com.example.dropy.network.use_case.createCorporateOrder

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

class CreateCorporateOrderUseCase @Inject constructor(
    private val waterRepository: WaterRepository
) {
    suspend operator fun invoke(
        quantity: Int,
        note: String,
        token: String,
        status: String,
        delivery_status: String,
        payment_status: String,
        water_type: String,
        total_payment: String,
        delivery_latitude: String,
        delivery_longitude: String,
        delivery_type: String,
        cheque: Uri,
        tracking_id: String,
        delivery_place_name: String,
        scheduled_time: String,
        context: Context
    ): Flow<Resource<AddWaterTruckRes?>> {
        return waterRepository.createCorporateWaterOrders(
            quantity = quantity,
            note = note,
            token = token,
            status = status,
            delivery_status = delivery_status,
            payment_status = payment_status,
            water_type = water_type,
            total_payment = total_payment,
            delivery_latitude = delivery_latitude,
            delivery_longitude = delivery_longitude,
            delivery_type = delivery_type,
            cheque = cheque,
            tracking_id = tracking_id,
            delivery_place_name = delivery_place_name,
            scheduled_time = scheduled_time,
            context = context
        )
    }
}