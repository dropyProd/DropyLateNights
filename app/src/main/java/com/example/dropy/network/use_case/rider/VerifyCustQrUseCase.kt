package com.example.dropy.network.use_case.rider

import android.content.Context
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.customerqr.body.CustomerQrBody
import com.example.dropy.network.models.reviewrider.ReviewRiderDataClass
import com.example.dropy.network.repositories.rider.RiderRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class VerifyCustQrUseCase (
    private val riderRepository: RiderRepository
) {
    suspend fun verifyCustQr(
        customerQrBody: CustomerQrBody,
        context: Context
    ): Flow<Resource<ActionResultDataClass?>> {
        return riderRepository.verifyCustQr(
            customerQrBody = customerQrBody, context = context
        )
    }
}