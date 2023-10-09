package com.example.dropy.network.repositories.authentication

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.dropy.domain.gerResponseErrorCreatingUser
import com.example.dropy.network.mappers.authentication.toDomain
import com.example.dropy.network.mappers.shops.frontside.toDomain
import com.example.dropy.network.models.*
import com.example.dropy.network.models.checkuser.CheckUserResponse
import com.example.dropy.network.models.createUser.CreateUserRes
import com.example.dropy.network.models.createUser.CreateUserResponse
import com.example.dropy.network.models.userdetails.ApiLoginReq
import com.example.dropy.network.services.register.RegisterService
import com.example.dropy.ui.utils.Resource
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class AuthenticationRepositoryImpl(
    private val registerService: RegisterService
) : AuthenticationRepository {

    private val BASE_URL = "https://api.dropy.ke/"

    override suspend fun registerUser(
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

        return flow {
            try {
                emit(Resource.Loading())

                val response = registerService.registerUser(
                    RegisterBody(
                        first_name = first_name,
                        last_name = last_name,
                        phone_number = phone_number,
                        //    password1 =  password1,
                        //    password2 = password2
                        password1 = password1,
                        password2 = password2,
                        dropy_role = dropy_role
                    )
                )
                emit(Resource.Success(response))



            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }/*catch (e: Exception){
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }*/
            /*} catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }*/
            /* val client = HttpClient(CIO) {
                 // Logging
                 install(Logging) {
                     level = LogLevel.ALL
                 }
                 // JSON
                 install(JsonFeature) {
                     serializer = KotlinxSerializer()
                     //or serializer = KotlinxSerializer()
                 }
                 // Timeout
                 install(HttpTimeout) {
                     requestTimeoutMillis = 15000L
                     connectTimeoutMillis = 15000L
                     socketTimeoutMillis = 15000L
                 }
             }
             val response: HttpResponse = client.post("https://api.dropy.co.ke/dropyusers/createnewuser") {
                 contentType(ContentType.Application.Json)
                 body = RegisterBody(
                     user_name = first_name + last_name,
                     first_name = first_name,
                     last_name = last_name,
                     phone_number = phone_number,
                     email = email,
                     password = password
                 )
             }

             Log.d("opid", "registerUser: "+response.readText())
             Log.d("opid", "registerUser: "+response.status)*/
            //  emit(Resource.Success(CreateUserResponse("random", 200).toDomain()))
            /*  } catch (e: IOException) {
                  emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
              } catch (e: HttpException) {
                  emit(Resource.Error(message = "Oops, something went wrong!"))
              }
  */
        }
        /*   val res: MutableState<CreateUserResponse?> = mutableStateOf(null)


           val response = try {
               registerService.registerUser(
                   firebase_uid = firebase_uid,
                   first_name = first_name,
                   last_name = last_name,
                   phone_number = phone_number,
                   email = email
               )
           } catch (e: Exception) {
               Log.d("TAG", "registerUser: Error fetching")
               return null
           }

           if (response.isSuccessful) {
               Log.d("TASSG", "registerUser: ${response.body()}")
               res.value = response.body()
           } else {
               val errorMsg = response.errorBody()!!.string()
               response.errorBody()?.close()
               val errorResponse = gerResponseErrorCreatingUser(errorMsg)
               Toast.makeText(context, "${errorResponse.errors}", Toast.LENGTH_SHORT).show()
           }

           return res.value*/
    }

    override suspend fun createApiToken(
        phone_number: String,
        password: String,
        context: Context
    ): Flow<Resource<CreateApiTokenResponse?>> {
        return flow {
            try{
            emit(Resource.Loading())
//            try {
            /*val response =   registerService.registerUser(
                firebase_uid = firebase_uid,
                first_name = first_name,
                last_name = last_name,
                phone_number = phone_number,
                email = email
            )*/
            val response = registerService.createApiToken(
                ApiTokenBody(
                    phone_number = phone_number,
                    password = password
                )
            )
            emit(Resource.Success(response))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
    }

    override suspend fun ApiLogin(
        phone_number: String,
        password: String,
        context: Context
    ): Flow<Resource<ApiLoginRes?>> {
        return flow {
            try{
            emit(Resource.Loading())
//            try {
            /*val response =   registerService.registerUser(
                firebase_uid = firebase_uid,
                first_name = first_name,
                last_name = last_name,
                phone_number = phone_number,
                email = email
            )*/
            val response = registerService.apiLogin(
                ApiLoginReq(
                    phone_number = phone_number,
                    password = password
                )
            )
            emit(Resource.Success(response))
              } catch (e: IOException) {
                  emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
              } catch (e: HttpException) {
                  emit(Resource.Error(message = "Oops, something went wrong!"))
              }
        }
    }

    override suspend fun forgotPassword(
        token: String,
        forgotPasswordReq: ForgotPasswordReq,
        context: Context
    ): Flow<Resource<UserDetailRes?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = registerService.forgotPassword(
                    token, forgotPasswordReq
                )
                emit(Resource.Success(response))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
    }


    override suspend fun getUserDetails(
        token: String
    ): Flow<Resource<UserDetailRes?>> {
        return flow {
            emit(Resource.Loading())
            try {
                /*val response =   registerService.registerUser(
                    firebase_uid = firebase_uid,
                    first_name = first_name,
                    last_name = last_name,
                    phone_number = phone_number,
                    email = email
                )*/
                val response = registerService.userDetails(
                    token = token
                )
                emit(Resource.Success(response))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
    }


    override suspend fun checkIfUserExist(phone_number: String): Flow<Resource<CheckUserResponse?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = registerService.checkIfUserExist(phonenumber = phone_number)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }

    }
}