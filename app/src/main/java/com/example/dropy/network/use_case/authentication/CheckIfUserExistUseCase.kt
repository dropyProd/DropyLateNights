package com.example.dropy.network.use_case.authentication

import com.example.dropy.network.models.checkuser.CheckUserResponse
import com.example.dropy.network.models.directions.DirectionRespnse
import com.example.dropy.network.repositories.authentication.AuthenticationRepository
import com.example.dropy.network.repositories.commons.CommonRepository
import com.example.dropy.ui.utils.Resource
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

class CheckIfUserExistUseCase(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend fun checkIfUserExist(
        phone_number: String
    ): Flow<Resource<CheckUserResponse?>> {
        return authenticationRepository.checkIfUserExist(phone_number = phone_number)
    }
}