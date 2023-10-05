package com.example.dropy.network.repositories.waterpoint

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.dropy.network.models.CreateWaterPointRes
import com.example.dropy.network.models.GetIndividualOrders.GetIndividualOrdersRes
import com.example.dropy.network.models.VerifyDeliveryCodeReq
import com.example.dropy.network.models.addWaterDriver.AddWaterDriverReq
import com.example.dropy.network.models.addWaterDriversRes.AddWaterDriverRes
import com.example.dropy.network.models.addWaterTruckRes.AddWaterTruckRes
import com.example.dropy.network.models.addWaterVendorRes.AddWaterVendorRes
import com.example.dropy.network.models.approvalRequests.ApprovalRequestsRes
import com.example.dropy.network.models.collectionPointOrder.CollectionPointOrderReq
import com.example.dropy.network.models.collectionPointOrderRes.CollectionPointOrderRes
import com.example.dropy.network.models.createIndividualWaterOrder.CreateIndividualWaterOrderRes
import com.example.dropy.network.models.getWaterPointOrders.GetWaterPointOrdersRes
import com.example.dropy.network.models.getWaterPoints.GetWaterPointsRes
import com.example.dropy.network.models.getWaterTrucks.GetTrucksRes
import com.example.dropy.network.models.getWaterVendors.GetWaterVendorsRes
import com.example.dropy.network.models.individualWaterOrder.IndividualWaterOrderReq
import com.example.dropy.network.models.registerDeviceReq.RegisterDeviceReq
import com.example.dropy.network.models.registerDeviceRes.RegisterDeviceRes
import com.example.dropy.network.models.topUp.TopUpReq
import com.example.dropy.network.models.updateTruckLocationReq.UpdateTruckLocationReq
import com.example.dropy.network.services.water.WaterService
import com.example.dropy.ui.utils.Resource
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


class WaterRepositoryImpl(
    private val client: HttpClient,
    private val waterService: WaterService
) : WaterRepository {
//    private val BASE_URL = "http://20.164.41.50:8000/"
    private val BASE_URL = "https://api.dropy.ke/"
    override suspend fun approvalRequests(token: String): Flow<Resource<ApprovalRequestsRes?>> {
        return flow {
            waterService.approvalRequests(token)
        }
    }

    override suspend fun topUpWallet(topUpReq: TopUpReq): Flow<Resource<String?>> {
        return flow {
            waterService.topUpWallet(topUpReq)
        }
    }

    override suspend fun updateTruckLocation(
        token: String,
        updateTruckLocationReq: UpdateTruckLocationReq
    ): Flow<Resource<String?>> {
        return flow {
            waterService.updateTruckLocation(token, updateTruckLocationReq)
        }
    }

    override suspend fun withdrawWallet(topUpReq: TopUpReq): Flow<Resource<String?>> {
        return flow {
            waterService.withdrawWallet(topUpReq)
        }
    }

    override suspend fun generateDeliveryCode(
        token: String,
        taskId: String
    ): Flow<Resource<String?>> {
        return flow {
            waterService.generateDeliveryCode(token = token, taskId = taskId)
        }
    }

    override suspend fun verifyDeliveryCode(
        token: String,
        taskId: String,
        verifyDeliveryCodeReq: VerifyDeliveryCodeReq
    ): Flow<Resource<String?>> {
        return flow {
            waterService.verifyDeliveryCode(
                token = token,
                taskId = taskId,
                verifyDeliveryCodeReq = verifyDeliveryCodeReq
            )
        }
    }

    override suspend fun registerDevice(
        token: String,
        registerDeviceReq: RegisterDeviceReq
    ): Flow<Resource<RegisterDeviceRes?>> {
        return flow {
            waterService.registerDevice(token = token, registerDeviceReq = registerDeviceReq)
        }
    }

    override suspend fun saveLoginInfo(
        phone_number: String,
        password: String,
        context: Context
    ): Flow<Resource<String?>> {
        return flow {
            // Storing data into SharedPreferences
            // Storing data into SharedPreferences
            try {
                val sharedPreferences: SharedPreferences =
                    context.getSharedPreferences("MySharedPref", MODE_PRIVATE)

// Creating an Editor object to edit(write to the file)

// Creating an Editor object to edit(write to the file)
                val myEdit = sharedPreferences.edit()

// Storing the key and its value as the data fetched from edittext

// Storing the key and its value as the data fetched from edittext
                myEdit.putString("phonenumber", phone_number)
                myEdit.putString("password", password)

// Once the changes have been made, we need to commit to apply those changes made,
// otherwise, it will throw an error

// Once the changes have been made, we need to commit to apply those changes made,
// otherwise, it will throw an error
                myEdit.commit()
                emit(Resource.Success("Saved success"))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }
        }
    }

    override suspend fun getLoginInfo(
        context: Context
    ): Flow<Resource<LoginDetails?>> {
        return flow {
            // Retrieving the value using its keys the file name

// The value will be default as empty string because for
//            the very
// first time when the app is opened, there is nothing to show
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("MySharedPref", MODE_PRIVATE)

            val phone: String? = sharedPreferences.getString("phonenumber", "");
            val pass: String? = sharedPreferences.getString("password", "");
            emit(
                Resource.Success(
                    LoginDetails(
                        phone_number = phone.toString(),
                        password = pass.toString()
                    )
                )
            )

        }
    }
//    private val BASE_URL = "https://api.dropy.ke/"

    override suspend fun addWaterpoint(
        token: String,
        owner: String,
        name: String,
        capacity: Int?,
        latitude: String,
        longitude: String,
        email: String,
        description: String,
        phone_number: String,
        operating_all_day: Boolean,
        holidays_open: Boolean,
        saturday_open: Boolean,
        sunday_open: Boolean,
        weekday_opening_time: String,
        weekday_closing_time: String,
        saturday_opening_time: String,
        saturday_closing_time: String,
        sunday_opening_time: String,
        sunday_closing_time: String,
        is_active: Boolean,
        county: String,
        sub_county: String,
        city: String,
        street: String,
        bank_account: String,
        mpesa_paybill: String,
        price_per_litre: String,
        total_earnings: String,
        shop_cover_photo: Uri?,
        logo: Uri?,
        context: Context
    ): Flow<Resource<CreateWaterPointRes?>> {
        val inputStream = logo?.let { context.contentResolver.openInputStream(it) }
        val imageByteArray = inputStream?.readBytes()
        val inputStreamCover = shop_cover_photo?.let { context.contentResolver.openInputStream(it) }
        val imageByteArrayCover = inputStreamCover?.readBytes()



        return flow {
            emit(Resource.Loading())
            try {
                if (imageByteArray != null && imageByteArrayCover != null) {


/*            try {*/
                    val response: HttpResponse =
                        client/*.submitForm(
                url = "${BASE_URL}shops/addshop",
                formParameters = Parameters.build {
                    append("firebase_uid", firebase_uid)
               //     append("shop_cover_photo", imageByteArrayCover, Headers.build {
                        append(HttpHeaders.ContentType, "image/png")
                        append(HttpHeaders.ContentDisposition, "filename=\"ktor_logo.png\"")
                    })
                    append("shop_logo", imageByteArray, Headers.build {
                        append(HttpHeaders.ContentType, "image/png")
                        append(HttpHeaders.ContentDisposition, "filename=\"ktor_logo.png\"")
                    })//
                    append("shop_category", shopCategory)
                    append("shop_name", shopname)
                    append("shop_lat", shopLocation.toString())
                    append("shop_long", shopLocation.toString())
                    append("shop_place_id", "4")
                    append("shop_place_name", "009090")
                }
            )*//*.post("${BASE_URL}shop/")*/.post("${BASE_URL}water/collection-points/") {

                            headers {
                                //  append(HttpHeaders.Accept, "text/html")
                                append(HttpHeaders.Authorization, "Token " + token)
                                // append(HttpHeaders.UserAgent, "ktor client")
                            }
                            val totalbytes: MutableState<Long?> = mutableStateOf(null)
                            onUpload { bytesSentTotal, contentLength ->
                                println("Sent $bytesSentTotal bytes from $contentLength")
//                        totalbytes.value = contentLength
//
//                      if (totalbytes.value!! > 550000){
//                          Toast.makeText(context, "choose a smaller image", Toast.LENGTH_SHORT).show()
//                      }
                            }
                            body = MultiPartFormDataContent(
                                formData {
                                    //  append("firebase_uid", firebase_uid)
                                    /*    append("shop_cover_photo", imageByteArrayCover, Headers.build {
                                            append(HttpHeaders.ContentType, "image/png")
                                            append(
                                                HttpHeaders.ContentDisposition,
                                                "filename=\"ktor_logo.png\""
                                            )
                                        })*/
                                    append("logo", imageByteArray, Headers.build {
                                        append(HttpHeaders.ContentType, "image/png")
                                        append(
                                            HttpHeaders.ContentDisposition,
                                            "filename=\"ktor_logo.png\""
                                        )
                                    })
                                    //    append("shop_category", shopCategory)
                                    append("owner", owner)
                                    //   append("shop_location", shopLocation.placeName)
                                    append("name", name)
                                    append("capacity", capacity.toString())
                                    append("latitude", latitude)
                                    append("longitude", longitude)
                                    append("email", email)
                                    append("description", description)
                                    append("phone_number", phone_number)
                                    append("operating_all_day", operating_all_day.toString())
                                    append("holidays_open", holidays_open.toString())
                                    append("saturday_open", saturday_open.toString())
                                    append("sunday_open", sunday_open.toString())
                                    append(
                                        "weekday_opening_time",
                                        weekday_opening_time
                                    )
                                    append(
                                        "weekday_closing_time",
                                        weekday_closing_time
                                    )
                                    append(
                                        "saturday_opening_time",
                                        saturday_opening_time
                                    )
                                    append(
                                        "saturday_closing_time",
                                        saturday_closing_time
                                    )
                                    append(
                                        "sunday_closing_time",
                                        sunday_closing_time
                                    )
                                    append(
                                        "sunday_opening_time",
                                        sunday_opening_time
                                    )
                                    /*     append("shop_lat", shopLocation.latitude)
                                         append("shop_long", shopLocation.longitude)
                                         append("shop_place_id", shopLocation.placeId)
                                         append("shop_place_name", shopLocation.placeName)*/
                                    append("is_active", is_active.toString())
                                    append("county", county)
                                    append("sub_county", sub_county)
                                    append("city", city)
                                    append("street", street)
                                    append("bank_account", bank_account)
                                    append("mpesa_paybill", mpesa_paybill)
                                    append("price_per_litre", price_per_litre)
                                    append("total_earnings", total_earnings)
                                },
                                //  boundary = "WebAppBoundary"
                            )


                        }

                    if (response.status == HttpStatusCode./*OK*/Created) {
                        val raw = response.readText()
                        val result = Gson().fromJson(
                            raw,
                            CreateWaterPointRes::class.java
                        )
                        Log.d("eeerre", "onStart: $raw")
                        //  Toast.makeText(context, "Shop created success", Toast.LENGTH_SHORT).show()
                        emit(Resource.Success(result/*.toDomain()*/))

                    }

                    /*   } catch (e: Exception) {

                           Log.d("TAG", "addShop: error")
                       }*/

                }

            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
    }

    override suspend fun addWatervendor(
        token: String,
        owner: String,
        name: String,
//        capacity: Int?,
        latitude: String,
        longitude: String,
//        email: String,
        description: String,
        phone_number: String,
        operating_all_day: Boolean,
        holidays_open: Boolean,
        saturday_open: Boolean,
        sunday_open: Boolean,
        weekday_opening_time: String,
        weekday_closing_time: String,
        saturday_opening_time: String,
        saturday_closing_time: String,
        sunday_opening_time: String,
        sunday_closing_time: String,
        is_active: Boolean,
        county: String,
        sub_county: String,
        city: String,
        street: String,
        bank_account: String,
        mpesa_paybill: String,
//        price_per_litre: String,
        total_earnings: String,
        shop_cover_photo: Uri?,
        logo: Uri?,
        context: Context
    ): Flow<Resource<AddWaterVendorRes?>> {
        val inputStream = logo?.let { context.contentResolver.openInputStream(it) }
        val imageByteArray = inputStream?.readBytes()
        val inputStreamCover = shop_cover_photo?.let { context.contentResolver.openInputStream(it) }
        val imageByteArrayCover = inputStreamCover?.readBytes()

        Log.d("gty", "addWatervendor: $token")

        return flow {
            emit(Resource.Loading())
            try {
                if (imageByteArray != null && imageByteArrayCover != null) {


/*            try {*/
                    val response: HttpResponse =
                        client/*.submitForm(
                url = "${BASE_URL}shops/addshop",
                formParameters = Parameters.build {
                    append("firebase_uid", firebase_uid)
               //     append("shop_cover_photo", imageByteArrayCover, Headers.build {
                        append(HttpHeaders.ContentType, "image/png")
                        append(HttpHeaders.ContentDisposition, "filename=\"ktor_logo.png\"")
                    })
                    append("shop_logo", imageByteArray, Headers.build {
                        append(HttpHeaders.ContentType, "image/png")
                        append(HttpHeaders.ContentDisposition, "filename=\"ktor_logo.png\"")
                    })//
                    append("shop_category", shopCategory)
                    append("shop_name", shopname)
                    append("shop_lat", shopLocation.toString())
                    append("shop_long", shopLocation.toString())
                    append("shop_place_id", "4")
                    append("shop_place_name", "009090")
                }
            )*//*.post("${BASE_URL}shop/")*/.post("${BASE_URL}water/water-vendors/") {

                            headers {
                                //  append(HttpHeaders.Accept, "text/html")
                                append(HttpHeaders.Authorization, "Token " + token)
                                // append(HttpHeaders.UserAgent, "ktor client")
                            }
                            val totalbytes: MutableState<Long?> = mutableStateOf(null)
                            onUpload { bytesSentTotal, contentLength ->
                                println("Sent $bytesSentTotal bytes from $contentLength")
//                        totalbytes.value = contentLength
//
//                      if (totalbytes.value!! > 550000){
//                          Toast.makeText(context, "choose a smaller image", Toast.LENGTH_SHORT).show()
//                      }
                            }
                            body = MultiPartFormDataContent(
                                formData {
                                    //  append("firebase_uid", firebase_uid)
                                    /*    append("shop_cover_photo", imageByteArrayCover, Headers.build {
                                            append(HttpHeaders.ContentType, "image/png")
                                            append(
                                                HttpHeaders.ContentDisposition,
                                                "filename=\"ktor_logo.png\""
                                            )
                                        })*/
                                    append("logo", imageByteArray, Headers.build {
                                        append(HttpHeaders.ContentType, "image/png")
                                        append(
                                            HttpHeaders.ContentDisposition,
                                            "filename=\"ktor_logo.png\""
                                        )
                                    })
                                    //    append("shop_category", shopCategory)
                                    append("owner", owner)
                                    //   append("shop_location", shopLocation.placeName)
                                    append("name", name)
//                                append("capacity", capacity.toString() )
//                                append("latitude",latitude)
//                                append("longitude",longitude)
//                                append("email",email)
                                    append("description", description)
                                    append("phone_number", phone_number)
                                    append("operating_all_day", operating_all_day.toString())
                                    append("holidays_open", holidays_open.toString())
                                    append("saturday_open", saturday_open.toString())
                                    append("sunday_open", sunday_open.toString())
                                    append(
                                        "weekday_opening_time",
                                        weekday_opening_time
                                    )
                                    append(
                                        "weekday_closing_time",
                                        weekday_closing_time
                                    )
                                    append(
                                        "saturday_opening_time",
                                        saturday_opening_time
                                    )
                                    append(
                                        "saturday_closing_time",
                                        saturday_closing_time
                                    )
                                    append(
                                        "sunday_closing_time",
                                        sunday_closing_time
                                    )
                                    append(
                                        "sunday_opening_time",
                                        sunday_opening_time
                                    )
                                    /*     append("shop_lat", shopLocation.latitude)
                                         append("shop_long", shopLocation.longitude)
                                         append("shop_place_id", shopLocation.placeId)
                                         append("shop_place_name", shopLocation.placeName)*/
                                    append("is_active", is_active.toString())
                                    append("county", county)
                                    append("sub_county", sub_county)
                                    append("city", city)
                                    append("street", street)
                                    append("bank_account", bank_account)
                                    append("mpesa_paybill", mpesa_paybill)
//                                append("price_per_litre", price_per_litre)
                                    append("total_earnings", total_earnings)
                                },
                                //  boundary = "WebAppBoundary"
                            )


                        }

                    if (response.status == HttpStatusCode./*OK*/Created) {
                        val raw = response.readText()
                        val result = Gson().fromJson(
                            raw,
                            AddWaterVendorRes::class.java
                        )
                        Log.d("eeerre", "onStart: $raw")
                        //  Toast.makeText(context, "Shop created success", Toast.LENGTH_SHORT).show()
                        emit(Resource.Success(result/*.toDomain()*/))

                    }

                    /*   } catch (e: Exception) {

                           Log.d("TAG", "addShop: error")
                       }*/

                }

            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
    }

    override suspend fun addWaterTruck(
        token: String,
        vendor: String,
        license_plate: String,
        capacity: Int?,
        current_location: String,
        name: String,
        model: String,
        year: Int,
        is_active: Boolean,
        is_available: Boolean,
        registered_latitude: String,
        registered_longitude: String,
        current_latitude: String,
        current_longitude: String,
        shop_cover_photo: Uri?,
        image: Uri?,
        context: Context
    ): Flow<Resource<AddWaterTruckRes?>> {
        val inputStream = image?.let { context.contentResolver.openInputStream(it) }
        val imageByteArray = inputStream?.readBytes()
        val inputStreamCover = shop_cover_photo?.let { context.contentResolver.openInputStream(it) }
        val imageByteArrayCover = inputStreamCover?.readBytes()


        return flow {
            emit(Resource.Loading())
            try {
                if (imageByteArray != null && imageByteArrayCover != null) {


/*            try {*/
                    val response: HttpResponse =
                        client/*.submitForm(
                url = "${BASE_URL}shops/addshop",
                formParameters = Parameters.build {
                    append("firebase_uid", firebase_uid)
               //     append("shop_cover_photo", imageByteArrayCover, Headers.build {
                        append(HttpHeaders.ContentType, "image/png")
                        append(HttpHeaders.ContentDisposition, "filename=\"ktor_logo.png\"")
                    })
                    append("shop_logo", imageByteArray, Headers.build {
                        append(HttpHeaders.ContentType, "image/png")
                        append(HttpHeaders.ContentDisposition, "filename=\"ktor_logo.png\"")
                    })//
                    append("shop_category", shopCategory)
                    append("shop_name", shopname)
                    append("shop_lat", shopLocation.toString())
                    append("shop_long", shopLocation.toString())
                    append("shop_place_id", "4")
                    append("shop_place_name", "009090")
                }
            )*//*.post("${BASE_URL}shop/")*/.post("${BASE_URL}water/water-trucks/") {

                            headers {
                                //  append(HttpHeaders.Accept, "text/html")
                                append(HttpHeaders.Authorization, "Token " + token)
                                // append(HttpHeaders.UserAgent, "ktor client")
                            }
                            val totalbytes: MutableState<Long?> = mutableStateOf(null)
                            onUpload { bytesSentTotal, contentLength ->
                                println("Sent $bytesSentTotal bytes from $contentLength")
//                        totalbytes.value = contentLength
//
//                      if (totalbytes.value!! > 550000){
//                          Toast.makeText(context, "choose a smaller image", Toast.LENGTH_SHORT).show()
//                      }
                            }
                            body = MultiPartFormDataContent(
                                formData {
                                    //  append("firebase_uid", firebase_uid)
                                    /*    append("shop_cover_photo", imageByteArrayCover, Headers.build {
                                            append(HttpHeaders.ContentType, "image/png")
                                            append(
                                                HttpHeaders.ContentDisposition,
                                                "filename=\"ktor_logo.png\""
                                            )
                                        })*/
                                    append("image", imageByteArray, Headers.build {
                                        append(HttpHeaders.ContentType, "image/png")
                                        append(
                                            HttpHeaders.ContentDisposition,
                                            "filename=\"ktor_logo.png\""
                                        )
                                    })
                                    //    append("shop_category", shopCategory)
                                    append("license_plate", license_plate.toString())
                                    append("capacity", capacity.toString())
                                    //   append("shop_location", shopLocation.placeName)
                                    append("current_location", current_location)
                                    append("is_active", is_active.toString())
                                    append("is_available", is_available.toString())
                                    append("name", name)
                                    append("model", model)
                                    append("year", year.toString())
                                    append("registered_latitude", registered_latitude)
                                    append("registered_longitude", registered_longitude)
                                    append("current_latitude", current_latitude)
                                    append("current_longitude", current_longitude)
                                    append("vendor ", vendor)
                                },
                                //  boundary = "WebAppBoundary"
                            )


                        }

                    if (response.status == HttpStatusCode./*OK*/Created) {
                        val raw = response.readText()
                        val result = Gson().fromJson(
                            raw,
                            AddWaterTruckRes::class.java
                        )
                        Log.d("eeerre", "onStart: $raw")
                        //  Toast.makeText(context, "Shop created success", Toast.LENGTH_SHORT).show()
                        emit(Resource.Success(result/*.toDomain()*/))
                    }

                    /*   } catch (e: Exception) {

                           Log.d("TAG", "addShop: error")
                       }*/

                }

            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
    }

    override fun getWaterpoints(token: String): Flow<Resource<GetWaterPointsRes?>> {
        return flow {
            try {
                val result = waterService.getWaterCollectionPoints(token)
                emit(Resource.Success(result/*.toDomain()*/))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }
        }
    }

    override fun getWatervendors(token: String): Flow<Resource<GetWaterVendorsRes?>> {
        return flow {
            try {
                val result = waterService.getWaterVendors(token)
                emit(Resource.Success(result/*.toDomain()*/))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }
        }
    }

    override fun getWaterTrucks(token: String): Flow<Resource<GetTrucksRes?>> {
        return flow {
            try {
                val result = waterService.getWaterTrucks(token)
                emit(Resource.Success(result/*.toDomain()*/))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }
        }
    }

    override fun createIndividualWaterOrder(
        token: String,
        individualWaterOrderReq: IndividualWaterOrderReq
    ): Flow<Resource<CreateIndividualWaterOrderRes?>> {
        return flow {
            try {
                Log.d("devdf", "createIndividualWaterOrder: " + token)
                Log.d("devdf", "createIndividualWaterOrder: " + individualWaterOrderReq)
                val result = waterService.createIndividualWaterOrder(token, individualWaterOrderReq)
                emit(Resource.Success(result/*.toDomain()*/))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }
        }
    }

    override fun createCollectionPointOrder(
        token: String,
        collectionPointOrderReq: CollectionPointOrderReq
    ): Flow<Resource<CollectionPointOrderRes?>> {

        return flow {
            try {
                val result = waterService.createCollectionPointOrder(token, collectionPointOrderReq)
                emit(Resource.Success(result/*.toDomain()*/))

            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }
        }
    }

    override fun getCollectionPointOrder(token: String): Flow<Resource<GetWaterPointOrdersRes?>> {
        return flow {
            val result = waterService.getCollectionPointOrder(token)
            emit(Resource.Success(result/*.toDomain()*/))
        }
    }

    override fun getIndividualWaterOrders(token: String): Flow<Resource<GetIndividualOrdersRes?>> {
        return flow {
            try {
                val result = waterService.getIndividualWaterOrders(token)
                emit(Resource.Success(result/*.toDomain()*/))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }
        }
    }

    override fun createDriver(
        token: String,
        addWaterDriverReq: AddWaterDriverReq
    ): Flow<Resource<AddWaterDriverRes?>> {
        return flow {
            try {
                val result = waterService.createDivers(token, addWaterDriverReq)
                emit(Resource.Success(result/*.toDomain()*/))

            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }
        }
    }

    override fun createCorporateWaterOrders(
        quantity: Int,
        note: String,
        token: String,
        status: String,
        delivery_status: String,
        payment_status: String,
        water_type: String,
        total_payment: String,
        delivery_latitude: String,
        delivery_longitude: String,
        delivery_type: String,
        cheque: Uri,
        tracking_id: String,
        delivery_place_name: String,
        scheduled_time: String,
        context: Context
    ): Flow<Resource<AddWaterTruckRes?>> {
        val inputStream = cheque?.let { context.contentResolver.openInputStream(it) }
        val imageByteArray = inputStream?.readBytes()
        /*val inputStreamCover = shop_cover_photo?.let { context.contentResolver.openInputStream(it) }
        val imageByteArrayCover = inputStreamCover?.readBytes()
*/
        Log.d("gty", "addWatervendor: $token")

        return flow {
            emit(Resource.Loading())
            try {
                if (imageByteArray != null /*&& imageByteArrayCover != null*/) {


/*            try {*/
                    val response: HttpResponse =
                        client/*.submitForm(
                url = "${BASE_URL}shops/addshop",
                formParameters = Parameters.build {
                    append("firebase_uid", firebase_uid)
               //     append("shop_cover_photo", imageByteArrayCover, Headers.build {
                        append(HttpHeaders.ContentType, "image/png")
                        append(HttpHeaders.ContentDisposition, "filename=\"ktor_logo.png\"")
                    })
                    append("shop_logo", imageByteArray, Headers.build {
                        append(HttpHeaders.ContentType, "image/png")
                        append(HttpHeaders.ContentDisposition, "filename=\"ktor_logo.png\"")
                    })//
                    append("shop_category", shopCategory)
                    append("shop_name", shopname)
                    append("shop_lat", shopLocation.toString())
                    append("shop_long", shopLocation.toString())
                    append("shop_place_id", "4")
                    append("shop_place_name", "009090")
                }
            )*//*.post("${BASE_URL}shop/")*/.post("${BASE_URL}water/corporate-water-orders/") {

                            headers {
                                //  append(HttpHeaders.Accept, "text/html")
                                append(HttpHeaders.Authorization, "Token " + token)
                                // append(HttpHeaders.UserAgent, "ktor client")
                            }
                            val totalbytes: MutableState<Long?> = mutableStateOf(null)
                            onUpload { bytesSentTotal, contentLength ->
                                println("Sent $bytesSentTotal bytes from $contentLength")
//                        totalbytes.value = contentLength
//
//                      if (totalbytes.value!! > 550000){
//                          Toast.makeText(context, "choose a smaller image", Toast.LENGTH_SHORT).show()
//                      }
                            }
                            body = MultiPartFormDataContent(
                                formData {
                                    //  append("firebase_uid", firebase_uid)
                                    /*    append("shop_cover_photo", imageByteArrayCover, Headers.build {
                                            append(HttpHeaders.ContentType, "image/png")
                                            append(
                                                HttpHeaders.ContentDisposition,
                                                "filename=\"ktor_logo.png\""
                                            )
                                        })*/
                                    append("cheque", imageByteArray, Headers.build {
                                        append(HttpHeaders.ContentType, "image/png")
                                        append(
                                            HttpHeaders.ContentDisposition,
                                            "filename=\"ktor_logo.png\""
                                        )
                                    })
                                    //    append("shop_category", shopCategory)
                                    append("quantity", quantity)
                                    //   append("shop_location", shopLocation.placeName)
                                    append("note", note)
//                                append("capacity", capacity.toString() )
//                                append("latitude",latitude)
//                                append("longitude",longitude)
//                                append("email",email)
                                    append("status", status)
                                    append("delivery_status", delivery_status)
                                    append("payment_status", payment_status.toString())
                                    append("water_type", water_type.toString())
                                    append("total_payment", total_payment.toString())
                                    append("delivery_latitude", delivery_latitude.toString())
                                    append(
                                        "delivery_longitude",
                                        delivery_longitude
                                    )
                                    append(
                                        "delivery_type",
                                        delivery_type
                                    )
                                    append(
                                        "tracking_id",
                                        tracking_id
                                    )
                                    append(
                                        "delivery_place_name",
                                        delivery_place_name
                                    )
                                    append(
                                        "scheduled_time",
                                        scheduled_time
                                    )

                                    /*     append("shop_lat", shopLocation.latitude)
                                         append("shop_long", shopLocation.longitude)
                                         append("shop_place_id", shopLocation.placeId)
                                         append("shop_place_name", shopLocation.placeName)*/
                                },
                                //  boundary = "WebAppBoundary"
                            )


                        }

                    if (response.status == HttpStatusCode./*OK*/Created) {
                        val raw = response.readText()
                        /*val result = Gson().fromJson(
                            raw,
                            AddWaterVendorRes::class.java
                        )*/
                        Log.d("eeerre", "onStart: $raw")
                        //  Toast.makeText(context, "Shop created success", Toast.LENGTH_SHORT).show()
//                    emit(Resource.Success(result/*.toDomain()*/))

                    }

                    /*   } catch (e: Exception) {

                           Log.d("TAG", "addShop: error")
                       }*/

                }

            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
    }


}