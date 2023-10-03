package com.example.dropy.network.services.register

import com.example.dropy.network.models.*
import com.example.dropy.network.models.checkuser.CheckUserResponse
import com.example.dropy.network.models.createUser.CreateUserRes
import com.example.dropy.network.models.createUser.CreateUserResponse
import com.example.dropy.network.models.payment.PaymentPojo
import com.example.dropy.network.models.userdetails.ApiLoginReq
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RegisterService {
    @GET("dropyusers/checkifuserexist")
    suspend fun checkIfUserExist(
        @Query("phone_number") phonenumber: String,
    ): CheckUserResponse

    /*@FormUrlEncoded
    @POST("dropyusers/createnewuser")
    suspend fun registerUser(
        @Field("firebase_uid") firebase_uid: String,
        @Field("first_name") first_name: String,
        @Field("last_name") last_name: String,
        @Field("phone_number") phone_number: String,
        @Field("email") email: String,
    ): CreateUserResponse
    */
  //  @FormUrlEncoded
    @POST("dropyusers/createnewuser")
   suspend fun registerUser(
        @Body registerBody: RegisterBody
    ): /*Call<*/createUserRes//>

    //@FormUrlEncoded
    @POST("api/token/")
    suspend fun createApiToken(
        @Body apiTokenBody: ApiTokenBody
    ): CreateApiTokenResponse

    @POST("dj-rest-auth/login/")
    suspend fun apiLogin(
        @Body apiLoginReq: ApiLoginReq
    ): ApiLoginRes

    @GET("dj-rest-auth/user/")
    suspend fun userDetails(
        @Header("Authorization") token: String
    ): UserDetailRes

    @POST("dropyusers/forgot_password/")
    suspend fun forgotPassword(
        @Header("Authorization") token: String,
        @Body forgotPasswordReq: ForgotPasswordReq
    ): UserDetailRes
}