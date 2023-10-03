package com.example.dropy.network.use_case.common

import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.directions.DirectionRespnse
import com.example.dropy.network.repositories.cart.CartRepository
import com.example.dropy.network.repositories.commons.CommonRepository
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.utils.Resource
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

class DirectionUseCase (
    private val commonRepository: CommonRepository
) {
    suspend fun direction(
        shopLatLng: LatLng, destination: String
    ): Flow<Resource<DirectionRespnse?>> {
        return commonRepository.direction(
            shopLatLng = shopLatLng, destination = destination
        )
    }
}