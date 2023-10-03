package com.example.dropy.network.use_case.common

import com.example.dropy.network.models.directions.DirectionRespnse
import com.example.dropy.network.repositories.commons.CommonRepository
import com.example.dropy.ui.utils.Resource
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

class DirectionMineUseCase (
    private val commonRepository: CommonRepository
) {
    suspend fun directionMine(
        shopLatLng: LatLng, myLatLng: LatLng
    ): Flow<Resource<DirectionRespnse?>> {
        return commonRepository.directionMine(
            shopLatLng = shopLatLng, myLatLng = myLatLng
        )
    }
}