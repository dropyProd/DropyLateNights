package com.example.dropy.network.use_case.rider

import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.directions.DirectionRespnse
import com.example.dropy.network.models.reviewrider.ReviewRiderDataClass
import com.example.dropy.network.repositories.commons.CommonRepository
import com.example.dropy.network.repositories.rider.RiderRepository
import com.example.dropy.ui.utils.Resource
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

class ReviewRiderUseCase(
    private val riderRepository: RiderRepository
) {
    suspend fun reviewRider(
        reviewRiderDataClass: ReviewRiderDataClass
    ): Flow<Resource<ActionResultDataClass?>> {
        return riderRepository.reviewRider(
            reviewRiderDataClass = reviewRiderDataClass
        )
    }
}