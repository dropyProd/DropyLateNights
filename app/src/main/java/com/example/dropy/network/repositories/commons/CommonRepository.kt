package com.example.dropy.network.repositories.commons

import com.example.dropy.network.models.deliverymethods.DeliveryMethodResponse
import com.example.dropy.network.models.directions.DirectionRespnse
import com.example.dropy.ui.utils.Resource
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface CommonRepository {
    suspend fun direction(shopLatLng: LatLng, destination: String): Flow<Resource<DirectionRespnse?>>
    suspend fun directionMine(shopLatLng: LatLng, myLatLng: LatLng): Flow<Resource<DirectionRespnse?>>
    suspend fun getDeliveryMethods(token: String): Flow<Resource<DeliveryMethodResponse?>>
}