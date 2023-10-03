package com.example.dropy.network.repositories.rider

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.dropy.domain.gerResponseErrorAcceptingJob
import com.example.dropy.domain.gerResponseErrorSRiderjobs
import com.example.dropy.domain.gerResponseErrorSVerifyQr
import com.example.dropy.network.mappers.rider.toDomain
import com.example.dropy.network.mappers.shops.frontside.toDomain
import com.example.dropy.network.models.acceptjob.AcceptJobResponse
import com.example.dropy.network.models.acceptjob.request.AcceptJobBody
import com.example.dropy.network.models.canceljob.CancelIncomingJobResponse
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.createrider.CreateRiderRequest
import com.example.dropy.network.models.customerqr.body.CustomerQrBody
import com.example.dropy.network.models.pools.RiderPoolsResponse
import com.example.dropy.network.models.reviewrider.ReviewRiderDataClass
import com.example.dropy.network.models.riderincomingjob.RiderIncomingJobResponse
import com.example.dropy.network.models.shopqr.qrbody.QrBody
import com.example.dropy.network.services.rider.RiderService
import com.example.dropy.network.services.users.UsersService
import com.example.dropy.ui.utils.Resource
import io.ktor.client.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class RiderRepositoryImpl(
    private val usersService: UsersService,
    private val client: HttpClient,
    private val riderService: RiderService,
) : RiderRepository {

    override suspend fun reviewRider(reviewRiderDataClass: ReviewRiderDataClass): Flow<Resource<ActionResultDataClass?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = usersService.reviewRider(reviewRiderDataClass)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }

    }

    override suspend fun getOngoingJobs(riderId: Int): Flow<Resource<RiderIncomingJobResponse?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = riderService.getAllRiderOngoingJobs(riderId)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }

    }

    override suspend fun verifyCustQr(
        customerQrBody: CustomerQrBody,
        context: Context
    ): Flow<Resource<ActionResultDataClass?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = riderService.verifyCustQr(customerQrBody)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
/*        val res: MutableState<ActionResultDataClass?> = mutableStateOf(null)

        val response = try {
            riderService.verifyCustQr(customerQrBody)
        } catch (e: Exception) {
            Log.d("TAG", "checkIfUserExist: Error fetching")
            return null
        }


        if (response.isSuccessful) {
            res.value = response.body()
            Log.d("ksojdij", "favouriteShop: ${response.body()}")

        } else {
            val errorMsg = response.errorBody()!!.string()
            response.errorBody()?.close()
            val errorResponse = gerResponseErrorSVerifyQr(errorMsg)

            Toast.makeText(context, "${errorResponse.error}", Toast.LENGTH_SHORT).show()

        }
        return res.value*/
    }

    override suspend fun verifyQr(qrBody: QrBody, context: Context): Flow<Resource<ActionResultDataClass?>>{
        return flow {
            emit(Resource.Loading())
            try {
                val response = riderService.verifyQr(qrBody)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
       /* val res: MutableState<ActionResultDataClass?> = mutableStateOf(null)

        val response = try {
            riderService.verifyQr(qrBody)
        } catch (e: Exception) {
            Log.d("TAG", "checkIfUserExist: Error fetching")
            return null
        }

        Log.d("huhttr", "verifyQr: ${response}")
        if (response.isSuccessful) {
            res.value = response.body()
            Log.d("ksojdij", "favouriteShop: ${response.body()}")

        } else {
            val errorMsg = response.errorBody()!!.string()
            response.errorBody()?.close()
            val errorResponse = gerResponseErrorSVerifyQr(errorMsg)

            Toast.makeText(context, "${errorResponse.error}", Toast.LENGTH_SHORT).show()

        }
        return res.value
*/
    }

    override suspend fun acceptIncomingJobs(
        riderId: Int,
        deliveryId: Int,
        acceptJobBody: AcceptJobBody,
        context: Context
    ):Flow<Resource<AcceptJobResponse?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = riderService.acceptIncomingJobs(riderId, deliveryId)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
       /* val res: MutableState<AcceptJobResponse?> = mutableStateOf(null)
        val response =
            riderService.acceptIncomingJobs(riderId, deliveryId)


        // Log.d("hiouoi", "checkIfUserExist:${response.errorBody()!!.toString()}")
        if (response.isSuccessful) {
            res.value = response.body()
        } else {
            val errorMsg = response.errorBody()!!.string()
            response.errorBody()?.close()
            val errorResponse = gerResponseErrorAcceptingJob(errorMsg)

            Toast.makeText(context, "${errorResponse.error}", Toast.LENGTH_SHORT).show()

        }

        return res.value*/
    }

    override suspend fun cancelIncomingJob(
        deliveryPersonId: Int,
        deliveryId: Int
    ): Flow<Resource<CancelIncomingJobResponse?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response =  riderService.cancelIncomingJob(deliveryPersonId, deliveryId)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
     /*   val res: MutableState<CancelIncomingJobResponse?> = mutableStateOf(null)
        val response = try {
            riderService.cancelIncomingJob(deliveryPersonId, deliveryId)
        } catch (e: Exception) {
            Log.d("TAG", "checkIfUserExist: Error fetching")
            return null
        }
        if (response.isSuccessful) {
            res.value = response.body()
        }
        return res.value*/
    }

    override suspend fun getAllRiderPools(): Flow<Resource<RiderPoolsResponse?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = usersService.getRiderPools()
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
       /* val res: MutableState<RiderPoolsResponse?> = mutableStateOf(null)

        val response = try {
            usersService.getRiderPools()
        } catch (e: Exception) {
            Log.d("TAG", "checkIfUserExist: Error fetching")
            return null
        }
        if (response.isSuccessful) {
            res.value = response.body()
            Log.d("ksojdij", "favouriteShop: ${response.body()}")

        }
        return res.value*/
    }

    override suspend fun createRider(
        createRiderRequest: CreateRiderRequest,
        context: Context
    ):  Flow<Resource<ActionResultDataClass?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response =  usersService.createRider(createRiderRequest)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
        /*val res: MutableState<ActionResultDataClass?> = mutableStateOf(null)

        val response = try {
            usersService.createRider(createRiderRequest)
        } catch (e: Exception) {
            Log.d("TAG", "checkIfUserExist: Error fetching")
            return null
        }
        //Log.d("ksojdij", "favouriteShop: ${response.body()!!}")
        *//*  Log.d("ksojdij", "favouriteShop: ${response.errorBody().toString()}")*//*

        if (response.isSuccessful) {
            res.value = response.body()
            Log.d("ksojdij", "favouriteShop: ${response.body()}")
            Toast.makeText(context, "${res.value?.message}", Toast.LENGTH_SHORT).show()

        } else {
            val errorMsg = response.errorBody()?.string()
            response.errorBody()?.close()
            val errorResponse = errorMsg?.let { gerResponseErrorAcceptingJob(it) }

            Toast.makeText(context, "${errorResponse?.error}", Toast.LENGTH_SHORT).show()

        }
        return res.value*/
    }

    override suspend fun getIncomingJobs(
        riderId: Int,
        context: Context
    ):  Flow<Resource<RiderIncomingJobResponse?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response =  riderService.getAllRiderIncomingJobs(riderId)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
       /* val res: MutableState<RiderIncomingJobResponse?> = mutableStateOf(null)
        val response = try {
            riderService.getAllRiderIncomingJobs(riderId)
        } catch (e: Exception) {
            Log.d("TAG", "checkIfUserExist: Error fetching")
            return null
        }

        Log.d("huh", "getIncomingJobs: ${response.body().toString()}")
        if (response.isSuccessful) {
            res.value = response.body()
        } else {
            val errorMsg = response.errorBody()!!.string()
            response.errorBody()?.close()
            val errorResponse = gerResponseErrorSRiderjobs(errorMsg)

            Toast.makeText(context, "${errorResponse.error}", Toast.LENGTH_SHORT).show()

        }

        *//*    val response: HttpResponse = try {
                client.get("https://api.dropy.co.ke/rider/incoming-work/${riderId}/")
            } catch (e: Exception) {
                Log.d("TAG", "getAllShopCategories: Error")
                return null
            }

            if (response.status == HttpStatusCode.OK) {
                val raw = response.readText()
                val result = Gson().fromJson(raw, RiderIncomingJobResponse::class.java)
                Log.d("TASSG", "onStart: $result")
                res.value = result
            }*//*

        return res.value
*/
    }


}