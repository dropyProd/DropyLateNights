package com.example.dropy.network.use_case.authentication

import android.content.Context
import com.example.dropy.network.models.ApiLoginRes
import com.example.dropy.network.models.CreateApiTokenResponse
import com.example.dropy.network.models.UserDetailRes
import com.example.dropy.network.models.checkuser.CheckUserResponse
import com.example.dropy.network.models.createUser.CreateUserRes
import com.example.dropy.network.models.createUser.CreateUserResponse
import com.example.dropy.network.models.createUserRes
import com.example.dropy.network.repositories.authentication.AuthenticationRepository
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

class RegisteruserUseCase(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend fun registerUser(
        firebase_uid: String,
        first_name: String,
        last_name: String,
        phone_number: String,
        email: String,
        password1: String,
        password2: String,
        dropy_role: String,
        context: Context
    ): Flow<Resource<createUserRes?>> {
        return authenticationRepository.registerUser(
            firebase_uid = firebase_uid,
            first_name = first_name,
            last_name = last_name,
            phone_number = phone_number,
            email = email,
            password1 = password1,
            password2 = password2,
            dropy_role = dropy_role,
            context = context
        )
    }
    suspend fun createApiToken(
        phone_number: String,
        password: String,
        context: Context
    ): Flow<Resource<CreateApiTokenResponse?>> {
        return authenticationRepository.createApiToken(
            phone_number = phone_number,
            password = password,
            context = context
        )
    }

    suspend fun apiLogin(
        phone_number: String,
        password: String,
        context: Context
    ): Flow<Resource<ApiLoginRes?>> {
        return authenticationRepository.ApiLogin(
            phone_number = phone_number,
            password = password,
            context = context
        )
    }

     suspend fun getUserDetails(
        token: String
    ): Flow<Resource<UserDetailRes?>> {
        return authenticationRepository.getUserDetails(token)
    }
}