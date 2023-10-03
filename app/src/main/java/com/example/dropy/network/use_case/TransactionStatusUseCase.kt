package com.example.dropy.network.use_case

import com.example.dropy.network.models.PlaceOrderReq
import com.example.dropy.network.models.PlaceOrderResponse
import com.example.dropy.network.models.PostCartResponse.PostCartResponse
import com.example.dropy.network.models.TransactionStatusRes
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.repositories.cart.CartRepository
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionStatusUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(
        transactionId: Int
    ): Flow<Resource<TransactionStatusRes?>> {
        return cartRepository.getTransactionStatus(
            transactionId = transactionId
        )
    }
}