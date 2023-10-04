package com.example.dropy.network.services.water

import com.example.dropy.network.models.GetIndividualOrders.GetIndividualOrdersRes
import com.example.dropy.network.models.VerifyDeliveryCodeReq
import com.example.dropy.network.models.addWaterDriver.AddWaterDriverReq
import com.example.dropy.network.models.addWaterDriversRes.AddWaterDriverRes
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
import retrofit2.http.*

interface WaterService {
    @GET("water/collection-points")
    suspend fun getWaterCollectionPoints(
        @Header("Authorization") token: String,
    ): GetWaterPointsRes

    @POST("transactions/topupwallet")
    suspend fun topUpWallet(
//        @Header("Authorization") token: String,
        @Body topUpReq: TopUpReq
    ): GetWaterVendorsRes

    @POST("transactions/withdrawwallet")
    suspend fun withdrawWallet(
//        @Header("Authorization") token: String,
        @Body topUpReq: TopUpReq
    ): GetWaterVendorsRes

    @GET("water/water-vendors")
    suspend fun getWaterVendors(
        @Header("Authorization") token: String,
    ): GetWaterVendorsRes

    @GET("water/water-trucks")
    suspend fun getWaterTrucks(
        @Header("Authorization") token: String,
    ): GetTrucksRes

    @GET("water/individual-water-orders/")
    suspend fun getIndividualWaterOrders(
        @Header("Authorization") token: String,
    ): GetIndividualOrdersRes

    @POST("water/truck-drivers/")
    suspend fun createDivers(
        @Header("Authorization") token: String,
        @Body addWaterDriverReq: AddWaterDriverReq
    ): AddWaterDriverRes

    @POST("water/generate-delivery-code/{taskId}/")
    suspend fun generateDeliveryCode(
        @Header("Authorization") token: String,
        @Path("taskId") taskId: String
    ): AddWaterDriverRes

    @POST("water/verify-delivery-code/{taskId}/")
    suspend fun verifyDeliveryCode(
        @Header("Authorization") token: String,
        @Path("taskId") taskId: String,
        @Body verifyDeliveryCodeReq: VerifyDeliveryCodeReq
    ): AddWaterDriverRes

    @POST("dropyusers/devices/")
    suspend fun registerDevice(
        @Header("Authorization") token: String,
        @Body registerDeviceReq: RegisterDeviceReq
    ): RegisterDeviceRes

    @POST("water/update-truck-location/")
    suspend fun updateTruckLocation(
        @Header("Authorization") token: String,
        @Body updateTruckLocationReq: UpdateTruckLocationReq
    ): String

    @GET("water/approval-requests/")
    suspend fun approvalRequests(
        @Header("Authorization") token: String
    ): ApprovalRequestsRes

    @POST("water/individual-water-orders/")
    suspend fun createIndividualWaterOrder(
        @Header("Authorization") token: String,
        @Body individualWaterOrderReq: IndividualWaterOrderReq,
    ): CreateIndividualWaterOrderRes

    @POST("water/collection-point-order/")
    suspend fun createCollectionPointOrder(
        @Header("Authorization") token: String,
        @Body collectionPointOrderReq: CollectionPointOrderReq,
    ): CollectionPointOrderRes

    @GET("water/collection-point-order/")
    suspend fun getCollectionPointOrder(
        @Header("Authorization") token: String,
    ): GetWaterPointOrdersRes

}