package com.example.dropy.network.repositories.shop.front

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.dropy.domain.gerResponseErrorShopQr
import com.example.dropy.network.mappers.shops.frontside.toDomain
import com.example.dropy.network.models.AddShopFavRes
import com.example.dropy.network.models.AllProductsRes
import com.example.dropy.network.models.FakeProduct
import com.example.dropy.network.models.ShopsResponseNew
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.customerorders.CustomerOrdersResponse
import com.example.dropy.network.models.customerqr.CustomerQrResponse
import com.example.dropy.network.models.favouriteshop.FavShopDataClass
import com.example.dropy.network.models.favouriteshop.favouriteShopRes.FavouriteShopResponse
import com.example.dropy.network.models.payment.PaymentPojo
import com.example.dropy.network.models.productdetails.ProductDetailsResponse
import com.example.dropy.network.models.shopCategories.ShopCategoriesResponse
import com.example.dropy.network.models.shopdetails.ShopDetailsResponse
import com.example.dropy.network.models.shops.ShopsResponse
import com.example.dropy.network.services.payment.PaymentService
import com.example.dropy.network.services.shops.ShopsFrontService
import com.example.dropy.ui.utils.Resource
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ShopFrontendRepositoryImpl(
    private val shopsFrontService: ShopsFrontService,
    private val paymentService: PaymentService,
    private val client: HttpClient
) : ShopFrontendRepository {

    private val BASE_URL = "http://4.168.233.233:8000/"

    override suspend fun getAllShops(token: String): Flow<Resource<ShopsResponseNew?>> {

        /* try {*/
        /*  val response = try {
              shopsFrontService.getAllShops()
          } catch (e: Exception) {
              Log.d("TAG", "checkIfUserExist: Error fetching")
              return null
          }

          if (response.isSuccessful) {
              Log.d("shopsresponse", "getAllShops: ${response.body()}")
              res.value = response.body()

          }
  */
        //  return res.value
        return flow {
            emit(Resource.Loading())
            try {
                val response = shopsFrontService.getAllShops(token)
                emit(Resource.Success(/*response.toDomain()*/response))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
    }

    override suspend fun getAllShopCategories(token: String): Flow<Resource<ShopCategoriesResponse?>> {


        /*val response: HttpResponse = try {
            client.get("${BASE_URL}shops/shopcategories")
        } catch (e: Exception) {
            Log.d("TAG", "getAllShopCategories: Error")
            return null
        }

        if (response.status == HttpStatusCode.OK) {
            val raw = response.readText()
            val result = Gson().fromJson(raw, ShopCategoriesResponse::class.java)
            Log.d("TASSG", "onStart: $result")
            res.value = result
        }

        return res.value*/
        return flow {
            emit(Resource.Loading())
            //   try {
            val response = shopsFrontService.getAllShopCategories("Token " + token)
            emit(Resource.Success(response.toDomain()))
            /*   } catch (e: IOException) {
                   emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
               } catch (e: HttpException) {
                   emit(Resource.Error(message = "Oops, something went wrong!"))
               }*/
        }
    }

    override suspend fun getProductDetails(
        token: String,
        productId: Int
    ): Flow<Resource<ProductDetailsResponse?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = shopsFrontService.getProductDetails(token, productId = productId)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }
        }
    }

    override suspend fun getShopDetails(
        token: String,
        shopId: String
    ): Flow<Resource<ShopDetailsResponse?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = shopsFrontService.getShopDetails(token, shopId = shopId)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
    }

    override suspend fun getShopProducts(shop: String): Flow<Resource<AllProductsRes?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = shopsFrontService.getShopProducts(shop)
                emit(Resource.Success(response))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
    }

    override suspend fun getAllProducts(): Flow<Resource<AllProductsRes?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = shopsFrontService.getAllProducts()
                emit(Resource.Success(response))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
    }

    override suspend fun favouriteShop(
        token: String,
        favShopDataClass: FavShopDataClass
    ): Flow<Resource<AddShopFavRes?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = shopsFrontService.
                favouriteShop("Token " + token, favShopDataClass)
                emit(Resource.Success(response/*.toDomain()*/))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
    }

    override suspend fun getfavouriteShops(
        token: String,
        userId: String
    ): Flow<Resource<FavouriteShopResponse?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = shopsFrontService.getFavouriteShops(token, userId)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }


        }
    }

    override suspend fun getCustomerQr(
        order_id: Int,
        context: Context,
        show: Boolean,
        token: String
    ): Flow<Resource<CustomerQrResponse?>> {

        Log.d("ksojdij", "favouriteShop: ${order_id}")
        return flow {
            emit(Resource.Loading())
            try {
                val response = shopsFrontService.getCustomerQr(token, order_id)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }

        }
    }

    override suspend fun getCustomerOrders(
        token: String,
        customer_id: String
    ): Flow<Resource<CustomerOrdersResponse?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = shopsFrontService.getCustomerOrders(token, customer_id)
                emit(Resource.Success(response.toDomain()))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Could not reach the server, please check your internet connection!"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Oops, something went wrong!"))
            }
        }
    }

    override suspend fun pay(
        firebase_uid: String,
        amount: Int,
        phone_number: String
    ): PaymentPojo? {

        val res: MutableState<PaymentPojo?> = mutableStateOf(null)

        val response = try {
            paymentService.pay(
                firebase_uid = firebase_uid,
                amount = amount,
                phone_number = phone_number
            )
        } catch (e: Exception) {
            Log.d("TAG", "registerUser: Error fetching")
            return null
        }

        if (response.isSuccessful) {
            Log.d("TASSG", "registerUser: ${response.body()}")
            res.value = response.body()
        }

        return res.value
    }

}