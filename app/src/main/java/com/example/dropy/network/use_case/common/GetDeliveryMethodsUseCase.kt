package com.example.dropy.network.use_case.common

import com.example.dropy.network.models.deliverymethods.DeliveryMethodResponse
import com.example.dropy.network.models.directions.DirectionRespnse
import com.example.dropy.network.repositories.commons.CommonRepository
import com.example.dropy.ui.utils.Resource
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

class GetDeliveryMethodsUseCase(
    private val commonRepository: CommonRepository
) {
    suspend fun getDeliveryMethods(token: String): Flow<Resource<DeliveryMethodResponse?>> {
        return commonRepository.getDeliveryMethods(token)
    }
}