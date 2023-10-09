package com.example.dropy.network.repositories.authentication

import android.content.Context
import com.example.dropy.network.models.*
import com.example.dropy.network.models.checkuser.CheckUserResponse
import com.example.dropy.network.models.createUser.CreateUserRes
import com.example.dropy.network.models.createUser.CreateUserResponse
import com.example.dropy.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    suspend fun checkIfUserExist(phone_number: String): Flow<Resource<CheckUserResponse?>>
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
    ): Flow<Resource<createUserRes?>>

    suspend fun createApiToken(
        phone_number: String,
        password: String,
        context: Context
    ): Flow<Resource<CreateApiTokenResponse?>>

    suspend fun ApiLogin(
        phone_number: String,
        password: String,
        context: Context
    ): Flow<Resource<ApiLoginRes?>>
    suspend fun forgotPassword(
        token: String,
        forgotPasswordReq: ForgotPasswordReq,
        context: Context
    ): Flow<Resource<UserDetailRes?>>

     suspend fun getUserDetails(
        token: String
    ): Flow<Resource<UserDetailRes?>>
}