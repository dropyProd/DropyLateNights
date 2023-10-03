package com.example.dropy.network.use_case.rider

import android.content.Context
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.reviewrider.ReviewRiderDataClass
import com.example.dropy.network.models.shopqr.qrbody.QrBody
import com.example.dropy.network.repositories.rider.RiderRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class VerifyQrUseCase (
    private val riderRepository: RiderRepository
) {
    suspend fun verifyQr(
        qrBody: QrBody, context: Context
    ): Flow<Resource<ActionResultDataClass?>> {
        return riderRepository.verifyQr(
            qrBody = qrBody, context = context
        )
    }
}