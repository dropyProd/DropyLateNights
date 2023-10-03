package com.example.dropy.network.use_case.addWaterPoint

import android.content.Context
import android.net.Uri
import com.example.dropy.network.models.AllProductsRes
import com.example.dropy.network.models.CreateWaterPointRes
import com.example.dropy.network.models.ShopsResponseNew
import com.example.dropy.network.models.createShop.createShopReq
import com.example.dropy.network.models.shops.ShopsResponse
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.network.repositories.waterpoint.WaterRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddWaterPointUseCase @Inject constructor(
    private val waterRepository: WaterRepository
) {
    suspend operator fun invoke(
        token: String,
        owner: String,
        name: String,
        capacity: Int?,
        latitude: String,
        longitude: String,
        email: String,
        description: String,
        phone_number: String,
        operating_all_day: Boolean,
        holidays_open: Boolean,
        saturday_open: Boolean,
        sunday_open: Boolean,
        weekday_opening_time: String,
        weekday_closing_time: String,
        saturday_opening_time: String,
        saturday_closing_time: String,
        sunday_opening_time: String,
        sunday_closing_time: String,
        is_active: Boolean,
        county: String,
        sub_county: String,
        city: String,
        street: String,
        bank_account: String,
        mpesa_paybill: String,
        price_per_litre: String,
        total_earnings: String,
        shop_cover_photo: Uri?,
        logo: Uri?,
        context: Context
    ): Flow<Resource<CreateWaterPointRes?>> {
        return waterRepository.addWaterpoint(
            token = token,
            owner = owner,
            name = name,
            capacity = capacity,
            latitude = latitude,
            longitude = longitude,
            email = email,
            description = description,
            phone_number = phone_number,
            operating_all_day = operating_all_day,
            holidays_open = holidays_open,
            saturday_open = saturday_open,
            sunday_open = sunday_open,
            weekday_opening_time = weekday_opening_time,
            weekday_closing_time = weekday_closing_time,
            saturday_opening_time = saturday_opening_time,
            saturday_closing_time = saturday_closing_time,
            sunday_opening_time = sunday_opening_time,
            sunday_closing_time = sunday_closing_time,
            is_active = is_active,
            county = county,
            sub_county = sub_county,
            city = city,
            street = street,
            bank_account = bank_account,
            mpesa_paybill = mpesa_paybill,
            price_per_litre = price_per_litre,
            total_earnings = total_earnings,
            shop_cover_photo = shop_cover_photo,
            logo = logo,
            context = context
        )
    }
}