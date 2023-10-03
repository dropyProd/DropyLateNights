package com.example.dropy.network.repositories.cart

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.dropy.data.network.shops.shopsfront.dtos.orderdetails.Order
import com.example.dropy.domain.addErrorMessage
import com.example.dropy.domain.gerResponseError
import com.example.dropy.network.mappers.cart.toDomain
import com.example.dropy.network.mappers.shops.frontside.toDomain
import com.example.dropy.network.models.*
import com.example.dropy.network.models.PostCartResponse.PostCartResponse
import com.example.dropy.network.models.cart.GetCartResponse
import com.example.dropy.network.models.cart.cartequest.CartRequest
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.services.cart.CartService
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsBacksideNavigation
import com.example.dropy.ui.components.shops.backside.dashboard.orders.orderdetails.OrderItemListItemDataClass
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutDataClass
import com.example.dropy.ui.utils.Resource
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.http.Body
import java.io.IOException

class CartRepositoryImpl(
    private val cartService: CartService,
    private val client: HttpClient
) : CartRepository {

    //    private val BASE_URL = "https://api.dropy.co.ke/"
    private val BASE_URL = "http://20.164.41.50:8000/"


    override suspend fun getCartItems(
        customerId: Int,
        cartRequest: CartRequest,
        appViewModel: AppViewModel
    ): Flow<Resource<GetCartResponse?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = cartService.getCartItems(customerId/*cartRequest*/)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }


        /*     val response: HttpResponse = *//*try {*//*
            client.get("${BASE_URL}shops/customers/${customerId}/cart")
       *//* } catch (e: Exception) {
            Log.d("TAGGG", "getCartItems: Error fetching cart items")
            return null
        }*//*

        Log.d("jiji", "getCartItems: ${response.readText()}")

        if (response.status == HttpStatusCode.OK) {
            val raw = response.readText()
            val result = Gson().fromJson(raw, GetCartResponse::class.java)
            Log.d("TASSG", "onStart: $result")

            if (!result.isEmpty()) {
                res.value = result
                // appViewModel.navigate(ShopsFrontDestination.CART)
            }

        }*/

    }

    override suspend fun getCartItemsNew(
        token: String,
        cartId: Int
    ): Flow<Resource<GetCartRes?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response: HttpResponse =
                    client.get("${BASE_URL}shop/cartitems/?cart_id=$cartId") {
                        headers {
//                        append(HttpHeaders.Accept, "text/html")
                            append(HttpHeaders.Authorization, token)
//                        append(HttpHeaders.UserAgent, "ktor client")
                        }
                    }
                if (response.status == HttpStatusCode.OK) {
                    val raw = response.readText()
                    val result = Gson().fromJson(
                        raw,
                        GetCartRes::class.java
                    )
                    Log.d("eeerre", "onStart: $result")
                    //  Toast.makeText(context, "Shop created success", Toast.LENGTH_SHORT).show()
                    emit(Resource.Success(result/*.toDomain()*/))

                }
//                val response = cartService.getCartItemsNew(token,cartId)
                // emit(Resource.Success(response))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
    }

    override suspend fun createCart(
        token: String,
        createCartReq: CreateCartReq
    ): Flow<Resource<CreateCartRes?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = cartService.createCartItems(
                    token = token,
                    createCartReq = createCartReq
                )
                emit(Resource.Success(response))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }

    }

    override suspend fun postCartItems(
        customerId: String,
        productId: Int,
        action: String,
        appViewModel: AppViewModel
    ): Flow<Resource<PostCartResponse?>> {
        return flow {
            emit(Resource.Loading())
            //   try {
            val res: MutableState<PostCartResponse?> = mutableStateOf(null)
            val response: HttpResponse =
                client.post("${BASE_URL}shop/customers/${customerId}/cart") {
                    body = MultiPartFormDataContent(
                        formData {
                            append("product_id", productId)
                            append("action", action)
                        },
                        //  boundary = "WebAppBoundary"
                    )
                }


            /*response.readText()*/

            // Log.d("lokij", "postCartItems: ${response.body()}")
            if (response.status == HttpStatusCode.OK) {
                val raw = response.readText()
                val result = Gson().fromJson(raw, PostCartResponse::class.java)
                Log.d("eeerre", "onStart: $result")
                res.value = result
                //   getCartItems(customerId, appViewModel = appViewModel)
                emit(Resource.Success(result.toDomain()))
            }


            /*     } catch (e: IOException) {
                     emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
                 } catch (e: HttpException) {
                     emit(Resource.Error(message = "Oops, something went wrong!"))
                 }*/

        }

        Log.d("KKTAG", "placeOrder: $productId  $customerId  $action")


    }

    override suspend fun addCartItems(
        token: String,
        addCartReq: AddCartReq,
    ): Flow<Resource<PostCartRes?>> {
        return flow {
            emit(Resource.Loading())
//            try {
            val response = cartService.postCartItems(token, addCartReq)
            emit(Resource.Success(response))
            /*    } catch (e: IOException) {
                    emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
                } catch (e: HttpException) {
                    emit(Resource.Error(message = "Oops, something went wrong!"))
                }
    */
        }
    }

    override suspend fun cancelOrder(
        orderId: Int,
        reasonForCanceling: String?,
        appViewModel: AppViewModel
    ): Flow<Resource<ActionResultDataClass?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = cartService.cancelOrder(orderId = orderId)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
    }


    override suspend fun deleteOrder(
        orderId: Int,
        appViewModel: AppViewModel
    ): Flow<Resource<ActionResultDataClass?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = cartService.deleteOrder(orderId = orderId)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
        /*val res: MutableState<ActionResultDataClass?> = mutableStateOf(null)
        val response = try {
            cartService.deleteOrder(orderId = orderId)
        } catch (e: Exception) {
            Log.d("TAGGG", "getCartItems: Error fetching cart items")
            return null
        }

        if (response.isSuccessful) {
            res.value = response.body()
            Log.d("FTsfu", "placeOrder: ${res.value}")

            //  getCustomerCart()
        } else {
            val errorMsg = response.errorBody()!!.string()
            response.errorBody()?.close()
            val errorResponse = gerResponseError(errorMsg)

            var errorMessages = emptyList<String>()
            for (err in errorResponse.errors) {
                errorMessages = addErrorMessage(errorList = errorMessages, errorMessage = err)
            }
            *//*  uiState.update {
                  it.copy(
                      errorList = errorMessages
                  )
              }*//*
        }

        return res.value*/
    }

    override suspend fun placeOrder(
        placeOrderReq: PlaceOrderReq,
        appViewModel: AppViewModel
    ): Flow<Resource<PlaceOrderResponse?>> {
        return flow {
            emit(Resource.Loading())
//            try {
            Log.d("lko", "placeOrder: $placeOrderReq")
            val response = cartService.placeOrder(placeOrderReq = placeOrderReq)
            emit(Resource.Success(response/*.toDomain()*/))
            /*} catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }*/

        }
/*        //   uiState.update { it.copy(actionLoading = true) }

        Log.d("KKTAG", "placeOrder: $orderId")
        val res: MutableState<ActionResultDataClass?> = mutableStateOf(null)
        val response = try {
            *//*try {*//*
            cartService.placeOrder(orderId = orderId)
            *//* }catch (e: IOException){
                 Log.e("ERROR FETCHING","io error")
     *//**//*            uiState.update {
                it.copy(
                    errorList = addErrorMessage(errorList = uiState.value.errorList,
                        CommonErrors.LOADING_ERROR),
                    pageLoading = false
                )
            }
            return@launch*//**//*
        }catch (e: HttpException){
            Log.e("ERROR HTTP","http error")
           *//**//* uiState.update {
                it.copy(
                    errorList = addErrorMessage(errorList = uiState.value.errorList,
                        CommonErrors.HTTP_ERROR),
                    pageLoading = false
                )
            }
            return@launch*//**//*
        }*//*
        } catch (e: Exception) {
            Log.d("TAGGG", "getCartItems: Error fetching cart items")
            return null
        }


        if (response.isSuccessful) {
            res.value = response.body()
            Log.d("FTsfu", "placeOrder: ${res.value}")
            //  getCustomerCart()
        } else {
            val errorMsg = response.errorBody()!!.string()
            response.errorBody()?.close()
            val errorResponse = gerResponseError(errorMsg)

            var errorMessages = emptyList<String>()
            for (err in errorResponse.errors) {
                errorMessages = addErrorMessage(errorList = errorMessages, errorMessage = err)
            }
            *//*  uiState.update {
                  it.copy(
                      errorList = errorMessages
                  )
              }*//*
        }

        return res.value*/
    }

    override suspend fun orderDetails(orderId: Int): Flow<Resource<Order?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = cartService.orderDetails(orderId = orderId)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }

    }

    override suspend fun processOrder(
        orderReq: ProcessOrderReq,
        id:Int,
    ): Flow<Resource<PlaceOrderResponse?>> {
        return flow {
            emit(Resource.Loading())
//            try {
            val response = cartService.processOrder(
                processOrderReq = orderReq,
                orderId = id!!.toInt()
            )
            emit(Resource.Success(response))
            /* } catch (e: IOException) {
                 emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
             } catch (e: HttpException) {
                 emit(Resource.Error(message = "Oops, something went wrong!"))
             }*/

        }

    }

    override suspend fun orderCheckOut(
        paymentReq: PaymentReq
    ): Flow<Resource<ActionResultDataClass?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = cartService.orderCheckOut(
                    paymentReq = paymentReq
                )
                //emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }

    }

    override suspend fun getTransactionStatus(
        transactionId: Int
    ): Flow<Resource<TransactionStatusRes?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = cartService.transactionStatus(
                    transactionId
                )
                emit(Resource.Success(response))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }

    }


}