package com.example.dropy.network.repositories.waterpoint

import android.content.Context
import android.net.Uri
import com.example.dropy.network.models.CreateWaterPointRes
import com.example.dropy.network.models.GetIndividualOrders.GetIndividualOrdersRes
import com.example.dropy.network.models.VerifyDeliveryCodeReq
import com.example.dropy.network.models.addWaterDriver.AddWaterDriverReq
import com.example.dropy.network.models.addWaterDriversRes.AddWaterDriverRes
import com.example.dropy.network.models.addWaterTruckRes.AddWaterTruckRes
import com.example.dropy.network.models.addWaterVendorRes.AddWaterVendorRes
import com.example.dropy.network.models.approvalRequests.ApprovalRequestsRes
import com.example.dropy.network.models.collectionPointOrder.CollectionPointOrderReq
import com.example.dropy.network.models.collectionPointOrderRes.CollectionPointOrderRes
import com.example.dropy.network.models.createIndividualWaterOrder.CreateIndividualWaterOrderRes
import com.example.dropy.network.models.getWaterPointOrders.GetWaterPointOrdersRes
import com.example.dropy.network.models.getWaterPoints.GetWaterPointsRes
import com.example.dropy.network.models.getWaterTrucks.GetTrucksRes
import com.example.dropy.network.models.getWaterVendors.GetWaterVendorsRes
import com.example.dropy.network.models.individualWaterOrder.IndividualWaterOrderReq
import com.example.dropy.network.models.registerDeviceReq.RegisterDeviceReq
import com.example.dropy.network.models.registerDeviceRes.RegisterDeviceRes
import com.example.dropy.network.models.topUp.TopUpReq
import com.example.dropy.network.models.updateTruckLocationReq.UpdateTruckLocationReq
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

data class LoginDetails(
    val phone_number: String,
    val password: String
)

interface WaterRepository {

    suspend fun approvalRequests(token: String): Flow<Resource<ApprovalRequestsRes?>>
    suspend fun topUpWallet(topUpReq: TopUpReq): Flow<Resource<String?>>
    suspend fun updateTruckLocation(token: String,updateTruckLocationReq: UpdateTruckLocationReq): Flow<Resource<String?>>
    suspend fun withdrawWallet(topUpReq: TopUpReq): Flow<Resource<String?>>
    suspend fun generateDeliveryCode(
        token: String,
        taskId: String
    ): Flow<Resource<String?>>
    suspend fun verifyDeliveryCode(
        token: String,
        taskId: String,
        verifyDeliveryCodeReq: VerifyDeliveryCodeReq
    ): Flow<Resource<String?>>

    suspend fun registerDevice(
        token: String,
        registerDeviceReq: RegisterDeviceReq
    ): Flow<Resource<RegisterDeviceRes?>>

    suspend fun saveLoginInfo(
        phone_number: String,
        password: String,
        context: Context
    ): Flow<Resource<String?>>

    suspend fun getLoginInfo(
        context: Context
    ): Flow<Resource<LoginDetails?>>

    suspend fun addWaterpoint(
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
    ): Flow<Resource<CreateWaterPointRes?>>

    suspend fun addWatervendor(
        token: String,
        owner: String,
        name: String,
//        capacity: Int?,
        latitude: String,
        longitude: String,
//        email: String,
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
//        price_per_litre: String,
        total_earnings: String,
        shop_cover_photo: Uri?,
        logo: Uri?,
        context: Context
    ): Flow<Resource<AddWaterVendorRes?>>

    suspend fun addWaterTruck(
        token: String,
        vendor : String,
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
    ): Flow<Resource<AddWaterTruckRes?>>



    fun getWaterpoints(token: String): Flow<Resource<GetWaterPointsRes?>>
    fun getWatervendors(token: String): Flow<Resource<GetWaterVendorsRes?>>
    fun getWaterTrucks(token: String): Flow<Resource<GetTrucksRes?>>

    fun createIndividualWaterOrder(
        token: String,
        individualWaterOrderReq: IndividualWaterOrderReq
    ): Flow<Resource<CreateIndividualWaterOrderRes?>>
    fun createCollectionPointOrder(
        token: String,
        collectionPointOrderReq: CollectionPointOrderReq
    ): Flow<Resource<CollectionPointOrderRes?>>
    fun getCollectionPointOrder(
        token: String,
    ): Flow<Resource<GetWaterPointOrdersRes?>>

     fun getIndividualWaterOrders(
        token: String,
    ): Flow<Resource<GetIndividualOrdersRes?>>

     fun createDriver(
        token: String,
        addWaterDriverReq: AddWaterDriverReq
    ): Flow<Resource<AddWaterDriverRes?>>


    fun createCorporateWaterOrders(
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
    ): Flow<Resource<AddWaterTruckRes?>>
}