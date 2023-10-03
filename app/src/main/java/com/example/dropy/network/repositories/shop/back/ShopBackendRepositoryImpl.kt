package com.example.dropy.network.repositories.shop.back

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.dropy.domain.addErrorMessage
import com.example.dropy.domain.gerResponseError
import com.example.dropy.domain.gerResponseErrorShopQr
import com.example.dropy.network.mappers.shops.backside.BacksideDtoToDomain.toDomain
import com.example.dropy.network.mappers.shops.frontside.toDomain
import com.example.dropy.network.models.CreateMethodReq
import com.example.dropy.network.models.CreateMethodRes
import com.example.dropy.network.models.ShopOrdersResponse
import com.example.dropy.network.models.addproduct.AddProductRes
import com.example.dropy.network.models.addproduct.AddProductResNew
import com.example.dropy.network.models.addproductcategory.AddProductCategoryRes
import com.example.dropy.network.models.addshop.AddShopResponse
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.completedorders.CompletedOrdersResponse
import com.example.dropy.network.models.createShop.createShopReq
import com.example.dropy.network.models.deleteproductcategory.DeleteProductCategoryResponse
import com.example.dropy.network.models.getshopproductcategories.GetShopProductCategoriesResponse
import com.example.dropy.network.models.pendingOrders.ShopPendingOrders
import com.example.dropy.network.models.productCategoryReq
import com.example.dropy.network.models.shopProductCategories.ShopProductCategoriesResponse
import com.example.dropy.network.models.shopqr.ShopQrResponse
import com.example.dropy.network.services.shops.ShopsBackendService
import com.example.dropy.network.services.shops.ShopsFrontService
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsBacksideNavigation
import com.example.dropy.ui.components.commons.AddressDataClass
import com.example.dropy.ui.components.shops.backside.dashboard.orders.orderdetails.OrderItemListItemDataClass
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

class ShopBackendRepositoryImpl(
    private val shopsBackendService: ShopsBackendService,
    private val client: HttpClient
) : ShopBackendRepository {
//        private val BASE_URL = "https://api.dropy.ke/"
    private val BASE_URL = "http://20.164.41.50:8000/"

    override suspend fun getShopQr(
        token: String,
        order_id: Int,
        context: Context
    ): Flow<Resource<ShopQrResponse?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = shopsBackendService.getShopQr(token, order_id)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }


    }

    override suspend fun getShopProductCategories(
        token: String,
        shopId: String
    ): Flow<Resource<GetShopProductCategoriesResponse?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = shopsBackendService.getShopProductCategories(token, shopId)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }


    }

    override suspend fun createDeliveryMethod(
        token: String,
        createMethodReq: CreateMethodReq
    ): Flow<Resource<CreateMethodRes?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = shopsBackendService.createDeliveryMethod(token, createMethodReq)
                emit(Resource.Success(response))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
    }

    override suspend fun addShopProductCategories(
        token: String,
        shopId: Int
    ): Flow<Resource<ShopProductCategoriesResponse?>> {

        return flow {
            emit(Resource.Loading())
            try {
                val response = shopsBackendService.addShopProductCategories(token, shopId)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }

    }

    override suspend fun addShopProductCategory(
        productCategoryReq: productCategoryReq,
        token: String
    ): Flow<Resource<AddProductCategoryRes?>> {

        return flow {
            emit(Resource.Loading())
            try {
                val response =
                    shopsBackendService.addShopProductCategories(token, productCategoryReq)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }


        }


    }

    override suspend fun addShop(
        token: String,
        shopOwner: String,
        shopname: String,
        shopLocation: AddressDataClass?,
        shopPhoneOne: String,
        firebase_uid: String,
        shop_cover_photo: Uri?,
        shop_logo: Uri?,
        shopCategory: Int,
        createShopReq: createShopReq,
        context: Context
    ): Flow<Resource<createShopReq?>> {
        Log.d("TAGDDDD", "addShop: $shopname  $shopPhoneOne")
        val inputStream = shop_logo?.let { context.contentResolver.openInputStream(it) }
        val imageByteArray = inputStream?.readBytes()
        val inputStreamCover = shop_cover_photo?.let { context.contentResolver.openInputStream(it) }
        val imageByteArrayCover = inputStreamCover?.readBytes()



        return flow {
            emit(Resource.Loading())
            // try {
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
            )*//*.post("${BASE_URL}shop/")*/.post("${BASE_URL}shop/shop/") {

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
                                append("shop_cover_photo", imageByteArrayCover, Headers.build {
                                    append(HttpHeaders.ContentType, "image/png")
                                    append(
                                        HttpHeaders.ContentDisposition,
                                        "filename=\"ktor_logo.png\""
                                    )
                                })
                                append("shop_logo", imageByteArray, Headers.build {
                                    append(HttpHeaders.ContentType, "image/png")
                                    append(
                                        HttpHeaders.ContentDisposition,
                                        "filename=\"ktor_logo.png\""
                                    )
                                })
                                //    append("shop_category", shopCategory)
                                append("shop_name", shopname)
                                //   append("shop_location", shopLocation.placeName)
                                append("shop_owner", shopOwner)
                                append("shop_type", createShopReq.shop_type!!.toUpperCase())
                                append(
                                    "operating_all_day",
                                    createShopReq.operating_all_day!!.toString()
                                )
                                append("holidays_open", createShopReq.holidays_open!!.toString())
                                append("saturday_open", createShopReq.saturday_open!!.toString())
                                append("sunday_open", createShopReq.sunday_open!!.toString())
                                append(
                                    "weekday_opening_time",
                                    createShopReq.weekday_opening_time!!.toString()
                                )
                                append(
                                    "weekday_closing_time",
                                    createShopReq.weekday_closing_time!!.toString()
                                )
                                append(
                                    "saturday_opening_time",
                                    createShopReq.saturday_opening_time!!.toString()
                                )
                                append(
                                    "saturday_closing_time",
                                    createShopReq.saturday_closing_time!!.toString()
                                )
                                append(
                                    "sunday_closing_time",
                                    createShopReq.sunday_closing_time!!.toString()
                                )
                                append(
                                    "sunday_opening_time",
                                    createShopReq.sunday_opening_time!!.toString()
                                )
                                /*     append("shop_lat", shopLocation.latitude)
                                     append("shop_long", shopLocation.longitude)
                                     append("shop_place_id", shopLocation.placeId)
                                     append("shop_place_name", shopLocation.placeName)*/
                                append("phone_number", shopPhoneOne)
                            },
                            //  boundary = "WebAppBoundary"
                        )


                    }

                if (response.status == HttpStatusCode./*OK*/Created) {
                    val raw = response.readText()
                    val result = Gson().fromJson(
                        raw,
                        com.example.dropy.network.models.createShop.createShopReq::class.java
                    )
                    Log.d("eeerre", "onStart: $result")
                    //  Toast.makeText(context, "Shop created success", Toast.LENGTH_SHORT).show()
                    emit(Resource.Success(result/*.toDomain()*/))

                }

                /*   } catch (e: Exception) {

                       Log.d("TAG", "addShop: error")
                   }*/

            }

            /*  } catch (e: IOException) {
                  emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
              } catch (e: HttpException) {
                  emit(Resource.Error(message = "Oops, something went wrong!"))
              }*/

        }
    }


    override suspend fun addProduct(
        type: String,
        token: String,
        shopId: String,
        productCategoryId: Int,
        productName: String,
        productDescription: String,
        productPrice: Int,
        productCoverPhoto: Uri?,
        //   dateAdded: String,
        numberInStack: Int,
        productId: Int,
        context: Context,
        appViewModel: AppViewModel
    ): Flow<Resource<AddProductResNew?>> {

        return flow {
            emit(Resource.Loading())
            Log.d("gyui", "addProduct: frtyy")
            //    try {
            if (type.equals("addproduct") /*|| appViewModel.appUiState.value.activeProfile?.type.equals(
                ProfileTypes.CUSTOMER
            )*/
            ) {
                val inputStream =
                    productCoverPhoto?.let { context.contentResolver.openInputStream(it) }
                val imageByteArray = inputStream?.readBytes()

                Log.d("b85a5", "addProduct: reach one")
                if (imageByteArray != null) {
                    /*  try {*/
                    Log.d("b85a5", "addProduct: reach two")
                    val response: HttpResponse =
                        client.post("${BASE_URL}shop/products/") {
//                            client.post("${BASE_URL}shop/shop/products/") {
                            headers {
                                //  append(HttpHeaders.Accept, "text/html")
                                append(HttpHeaders.Authorization, "Token " + token)
                                // append(HttpHeaders.UserAgent, "ktor client")
                            }
                            val totalbytes: MutableState<Long?> = mutableStateOf(null)
                            onUpload { bytesSentTotal, contentLength ->
                                println("Sent $bytesSentTotal bytes from $contentLength")
                                totalbytes.value = contentLength

                                if (totalbytes.value!! > 550000) {
                                    Toast.makeText(
                                        context,
                                        "choose a smaller image",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            body = MultiPartFormDataContent(
                                formData {
                                    append("shop", shopId)
                                    /*        append(
                                                "product_category",
                                                *//*productCategoryId*//*0
                                        )*/
                                    append("product_name", productName)
                                    append("product_description", productDescription)
                                    append("product_price", productPrice)
                                    append(
                                        "product_cover_photo",
                                        imageByteArray,
                                        Headers.build {
                                            append(HttpHeaders.ContentType, "image/png")
                                            append(
                                                HttpHeaders.ContentDisposition,
                                                "filename=\"ktor_logo.png\""
                                            )
                                        })
                                    //     append("date_added", dateAdded)
                                    append("number_in_stack", numberInStack)
                                },
                                //  boundary = "WebAppBoundary"
                            )

                            onUpload { bytesSentTotal, contentLength ->
                                println("Sent $bytesSentTotal bytes from $contentLength")
                            }


                        }
                    Log.d("b85a5", "addProduct: reach three")

                    //if (response.status == HttpStatusCode.OK) {
                    val raw = response.readText()
                    Log.d("vcnb", "onStart: $raw")
                    val result = Gson().fromJson(raw, AddProductResNew::class.java)
                    Log.d("vcnb", "onStart: $result")
                    emit(Resource.Success(result))
                    // }
/*
            } catch (e: Exception) {
                addProductResponse.value =
                    AddProductRes(message = "Error adding shop", resultCode = 7)
                // Log.d("TAG", "addShop: error")
            }*/

                }
            } else {
                val inputStream =
                    productCoverPhoto?.let { context.contentResolver.openInputStream(it) }
                val imageByteArray = inputStream?.readBytes()


                /*  try {*/
                val response: HttpResponse =
                    client.put("${BASE_URL}shops/products/${productId}") {
                        body = MultiPartFormDataContent(
                            formData {
                                append("shop", shopId)
                                append(
                                    "product_category",
                                    productCategoryId
                                )
                                append("product_name", productName)
                                append("product_description", productDescription)
                                append("product_price", productPrice)
                                imageByteArray?.let {
                                    append("product_cover_photo", it, Headers.build {
                                        append(HttpHeaders.ContentType, "image/png")
                                        append(
                                            HttpHeaders.ContentDisposition,
                                            "filename=\"ktor_logo.png\""
                                        )
                                    })
                                }
                                //     append("date_added", dateAdded)
                                append("number_in_stack", numberInStack)
                            },
                            //  boundary = "WebAppBoundary"
                        )

                        onUpload { bytesSentTotal, contentLength ->
                            println("Sent $bytesSentTotal bytes from $contentLength")
                        }


                    }


                if (response.status == HttpStatusCode.OK) {
                    val raw = response.readText()
                    val result = Gson().fromJson(raw, AddProductResNew::class.java)
                    Log.d("eeerre", "onStart: $result")
                    emit(Resource.Success(result))
                }
/*
            } catch (e: Exception) {
                addProductResponse.value =
                    AddProductRes(message = "Error adding shop", resultCode = 7)
                // Log.d("TAG", "addShop: error")
            }*/


            }

            /*     } catch (e: IOException) {
                     emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
                 } catch (e: HttpException) {
                     emit(Resource.Error(message = "Oops, something went wrong!"))
                 }*/

        }

    }


    override suspend fun deleteProduct(
        token: String,
        productId: Int
    ): Flow<Resource<ActionResultDataClass?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = shopsBackendService.deleteProduct(token = token, productId)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }
        }
    }

    override suspend fun deleteProductCategory(
        token: String,
        productId: Int
    ): Flow<Resource<DeleteProductCategoryResponse?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = shopsBackendService.deleteProductCategory(token, productId)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }
        }

    }

    override suspend fun processOrder(
        token: String,
        list: List<OrderItemListItemDataClass>,
        orderId: Int,
        appViewModel: AppViewModel
    ): Flow<Resource<ActionResultDataClass?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = shopsBackendService.processOrder(token, orderId, list)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }
        }

    }

    override suspend fun getShopPendingOrders(
        token: String,
        shopId: String
    ): Flow<Resource<ShopOrdersResponse?>> {
        return flow {
            emit(Resource.Loading())
//            try {
                val response = shopsBackendService.getShopPendingOrders(
                    /*token,*/ shopId = shopId
                )

                emit(Resource.Success(response/*.toDomain()*/))
         /*   } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }*/
        }

        /*   val res: MutableState<ShopPendingOrders?> = mutableStateOf(null)

           *//*    val response: HttpResponse =
                client.get("https://api.dropy.co.ke/shops/shops/3/pendingorders")

            Log.d("kikiui", "getShopPendingOrders: ${response.readText()}")*//*

        val response = try {
            shopsBackendService.getShopPendingOrders(
                shopId = shopId,
            )
        } catch (e: Exception) {
            Log.d("TAGGG", "getCartItems: Error fetching cart items")
            return null
        }

        Log.d("TAGGG", "getCartItems: ${response}")
        if (response.isSuccessful) {

            val responseBody = response.body()!!
            res.value = responseBody
            //  uiState.update { it.copy(orderList = orders) }
        } else {
            val errorMsg = response.errorBody()!!.string()
            response.errorBody()?.close()
            val errorResponse = gerResponseError(errorMsg)

            var errorMessages = emptyList<String>()
            for (err in errorResponse.errors) {
                errorMessages = addErrorMessage(errorList = errorMessages, errorMessage = err)
            }
            *//*   uiState.update {
                   it.copy(
                       errorList = errorMessages
                   )
               }*//*
        }

        return res.value*/
    }

    override suspend fun getShopCompletedOrders(
        token: String,
        shopId: String
    ): Flow<Resource<CompletedOrdersResponse?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = shopsBackendService.getShopCompletedOrders(
                    token, shopId = shopId
                )

                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }
        }
        /*       val res: MutableState<CompletedOrdersResponse?> = mutableStateOf(null)

               *//*    val response: HttpResponse =
                client.get("https://api.dropy.co.ke/shops/shops/3/pendingorders")

            Log.d("kikiui", "getShopPendingOrders: ${response.readText()}")*//*

        val response = try {
            shopsBackendService.getShopCompletedOrders(
                shopId = shopId,
            )
        } catch (e: Exception) {
            Log.d("TAGGG", "getCartItems: Error fetching cart items")
            return null
        }

        Log.d("TAGGG", "getCartItems: ${response}")
        if (response.isSuccessful) {

            val responseBody = response.body()!!
            res.value = responseBody
            //  uiState.update { it.copy(orderList = orders) }
        } else {
            val errorMsg = response.errorBody()!!.string()
            response.errorBody()?.close()
            val errorResponse = gerResponseError(errorMsg)

            var errorMessages = emptyList<String>()
            for (err in errorResponse.errors) {
                errorMessages = addErrorMessage(errorList = errorMessages, errorMessage = err)
            }
            *//*   uiState.update {
                   it.copy(
                       errorList = errorMessages
                   )
               }*//*
        }

        return res.value*/
    }

}