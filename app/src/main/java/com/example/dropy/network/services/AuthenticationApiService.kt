package com.example.dropy.network.services

import com.example.dropy.network.models.UserDetailsBody
import com.example.dropy.network.models.userdetails.DropyUserDetails
import retrofit2.Response
import retrofit2.http.*

interface AuthenticationApiService {
/*    @GET("dropyusers/checkifuserexist")
    suspend fun checkIfUserExists(@Query("phone_number") phoneNumber:String): Response<CheckUserExist>

    @FormUrlEncoded
    @POST("dropyusers/createnewuser")
    suspend fun createNewUser(
        @Field("first_name") firstName:String,
        @Field("last_name") lastName:String,
        @Field("phone_number") phoneNumber:String,
        @Field("email") email:String,
        @Field("firebase_uid") firebaseUid:String,
    ):Response<ActionResultDataClass>*/

    //  @GET("dropyusers/getuserdetails")
    @POST("dropyusers/getuserdetails")
    suspend fun getUserDetails(
        //    @Query("firebase_uid") firebaseUid:String
        @Header("Authorization") token: String,
        @Body userDetailsBody: UserDetailsBody
    ): Response<DropyUserDetails>
}