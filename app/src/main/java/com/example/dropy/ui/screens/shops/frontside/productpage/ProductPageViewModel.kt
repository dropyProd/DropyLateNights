package com.example.dropy.ui.screens.shops.frontside.productpage

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.productdetails.ProductDetailsResponse
import com.example.dropy.network.repositories.shop.ShopRepository
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.network.use_case.shops.frontside.GetProductDetailsUseCase
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import com.example.dropy.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class ProductPageUiState(
    val productName: String = "Product page",
    val productImageUrls: List<String> = emptyList(),
    val productPrice: Int = 0,
    val productDescription: String = "Product description",
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class ProductPageViewModel @Inject constructor(
//    private val dropyRepository: DropyRepository
    private val shopFrontendRepository: ShopFrontendRepository,
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val app: DropyApp

) : ViewModel() {

    val uiState = MutableStateFlow(ProductPageUiState())

    val productPageUiState: StateFlow<ProductPageUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    suspend fun getProductDetails(
        productId: Int,
        shopHomePageViewModel: ShopHomePageViewModel
    ): ProductDetailsResponse? {
        val res: MutableState<ProductDetailsResponse?> = mutableStateOf(null)

        Log.d("ddiuedw", "getProductDetails: $productId")

        getProductDetailsUseCase.getProductDetails(app.token.value,productId = productId).flowOn(Dispatchers.IO)
            .catch { e ->
                // handle exception
                uiState.update { it.copy(pageLoading = false) }
            }
            .collect { result ->
                // list of users from the network
                Log.d("uopopi", "getAllShops: $result")
                when (result) {
                    is Resource.Success -> {
                        if (result.data != null) {

                            Log.d("productdetails", "shopsFrontNavGraph:${result.data} ")
                            setValues(result.data, shopHomePageViewModel)
                            res.value = result.data

                            uiState.update { it.copy(pageLoading = false) }

                        }
                    }
                    is Resource.Loading -> {
                        uiState.update { it.copy(pageLoading = true) }
                    }
                    is Resource.Error -> {
                        result.message?.let { message ->
                            uiState.update {
                                it.copy(
                                    pageLoading = false,
                                    errorList = listOf(message)
                                )
                            }
                        }

                    }
                }

            }

        return res.value
    }

    fun setValues(item: ProductDetailsResponse, shopHomePageViewModel: ShopHomePageViewModel) {
        val list: MutableList<String> = mutableListOf()
        Log.d("jdieee", "setValues: $item")
        shopHomePageViewModel.shopHomePageUiState.value.shopProducts.forEach {
            if (it.product_name.equals(item.product_name)) {
                list.add(it.product_cover_photo.toString())
            }
        }
        /*   item.product_images!!.forEach {
               list.add(it.image_url.toString())
           }*/
        uiState.update {
            item.product_price?.let { it1 ->
                it.copy(
                    productName = item.product_name.toString(),
                    productDescription = item.product_description.toString(),
                    productPrice = it1,
                    productImageUrls = list
                )
            }!!
        }
    }

    fun onAddToCart() {
        appViewModel?.navigate(ShopsFrontDestination.CART)
    }

}