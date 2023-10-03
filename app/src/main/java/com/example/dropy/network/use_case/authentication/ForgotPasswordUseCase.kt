package com.example.dropy.network.use_case.authentication

import android.content.Context
import com.example.dropy.network.models.AllProductsRes
import com.example.dropy.network.models.ForgotPasswordReq
import com.example.dropy.network.models.ShopsResponseNew
import com.example.dropy.network.models.UserDetailRes
import com.example.dropy.network.models.shops.ShopsResponse
import com.example.dropy.network.repositories.authentication.AuthenticationRepository
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(
        token: String,
        forgotPasswordReq: ForgotPasswordReq,
        context: Context
    ): Flow<Resource<UserDetailRes?>> {
        return authenticationRepository.forgotPassword(token, forgotPasswordReq, context)
    }
}