package com.example.dropy.network.use_case.createCollectionPointOrder

import com.example.dropy.network.models.collectionPointOrder.CollectionPointOrderReq
import com.example.dropy.network.models.collectionPointOrderRes.CollectionPointOrderRes
import com.example.dropy.network.repositories.waterpoint.WaterRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateCollectionPointOrderUseCase @Inject constructor(
    private val waterRepository: WaterRepository
) {
    suspend operator fun invoke(
        token: String,
        collectionPointOrderReq: CollectionPointOrderReq
    ): Flow<Resource<CollectionPointOrderRes?>> {
        return waterRepository.createCollectionPointOrder(
            token = token,
            collectionPointOrderReq = collectionPointOrderReq
        )
    }
}