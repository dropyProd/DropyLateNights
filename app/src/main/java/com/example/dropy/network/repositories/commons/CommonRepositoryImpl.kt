package com.example.dropy.network.repositories.commons

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.dropy.network.mappers.shared.toDomain
import com.example.dropy.network.mappers.shops.frontside.toDomain
import com.example.dropy.network.models.deliverymethods.DeliveryMethodResponse
import com.example.dropy.network.models.directions.DirectionRespnse
import com.example.dropy.network.services.commons.CommonService
import com.example.dropy.ui.utils.Resource
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CommonRepositoryImpl(
    private val client: HttpClient,
    private val commonService: CommonService
) : CommonRepository {
    override suspend fun directionMine(
        shopLatLng: LatLng,
        myLatLng: LatLng
    ): Flow<Resource<DirectionRespnse?>> {
        return flow {
            emit(Resource.Loading())
            try {

                Log.d("deded", "direction: $shopLatLng  $myLatLng")
                val response: HttpResponse =
                    client.get("https://maps.googleapis.com/maps/api/directions/json?origin=${shopLatLng.latitude},${shopLatLng.longitude}&destination=${myLatLng.latitude},${myLatLng.longitude}&mode=TRANSIT&key=AIzaSyCnrF8f8v1z1s3NVpPp7Dw-dksvOlQs9XI")


                /* api key   AIzaSyB8xwiiVpO-fa3CSjjQvSd-vXT-IlcU60o*/
                if (response.status == HttpStatusCode.OK) {
                    val raw = response.readText()
                    Log.d("jijijoi", "direction: $raw")
                    val result = Gson().fromJson(raw, DirectionRespnse::class.java)
                    Log.d("TASSG", "onStart: $result")
                    emit(Resource.Success(result.toDomain()))
                }

            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }


    }

    override suspend fun direction(
        shopLatLng: LatLng,
        destination: String
    ): Flow<Resource<DirectionRespnse?>> {
        return flow {
            emit(Resource.Loading())
            try {

                Log.d("deded", "direction: $shopLatLng  $destination")
                val response: HttpResponse =
                    client.get("https://maps.googleapis.com/maps/api/directions/json?origin=${shopLatLng.latitude},${shopLatLng.longitude}&destination=${destination}&mode=driving&key=AIzaSyCnrF8f8v1z1s3NVpPp7Dw-dksvOlQs9XI")


                /* api key   AIzaSyB8xwiiVpO-fa3CSjjQvSd-vXT-IlcU60o*/
                if (response.status == HttpStatusCode.OK) {
                    val raw = response.readText()
                    Log.d("jijijoi", "direction: $raw")
                    val result = Gson().fromJson(raw, DirectionRespnse::class.java)
                    Log.d("TASSG", "onStart: $result")
                    emit(Resource.Success(result.toDomain()))
                }

            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
    }

    override suspend fun getDeliveryMethods(token: String): Flow<Resource<DeliveryMethodResponse?>> {
        return flow {
            emit(Resource.Loading())
//            try {
                val response = commonService.getDeliveryMethods(token)
                emit(Resource.Success(response))
         /*   } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }*/

        }

    }

}