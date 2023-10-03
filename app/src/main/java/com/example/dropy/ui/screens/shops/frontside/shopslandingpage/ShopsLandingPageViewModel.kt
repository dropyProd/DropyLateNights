package com.example.dropy.ui.screens.shops.frontside.shopslandingpage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.shopCategories.ShopCategoriesResponseItem
import com.example.dropy.network.models.shops.Shop
import com.example.dropy.network.repositories.shop.ShopRepository
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.network.use_case.shops.frontside.GetAllShopCategoriesUseCase
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.shops.backside.addshop.AddShopViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import com.example.dropy.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


data class ShopsLandingPageUiState(
    val shopCategoryList: List<ShopCategoriesResponseItem> = emptyList(),
    val popularShops: List<Shop> = emptyList(),
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class ShopsLandingPageViewModel @Inject constructor(
    private val shopFrontendRepository: ShopFrontendRepository,
    private val getAllShopCategoriesUseCase: GetAllShopCategoriesUseCase,
    private val app: DropyApp
) : ViewModel() {

    val _uiState = MutableStateFlow(ShopsLandingPageUiState())

    val shopLandingPageUiState: StateFlow<ShopsLandingPageUiState> = _uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    val current: MutableList<Shop> = mutableListOf()
    fun getPopularShops(item: Shop) {
        current.add(item)

        _uiState.update { it.copy(popularShops = current) }
    }

    fun getShopCategories(
        appHomePageViewModel: AppHomePageViewModel,
        addShopViewModel: AddShopViewModel
    ) {

        viewModelScope.launch {

            getAllShopCategoriesUseCase.shopCategories(app.token.value).flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception

                }
                .collect { result ->
                    Log.d("uopopi", "getAllShops: $result")
                    when (result) {
                        is Resource.Success -> {
                            val current = mutableListOf<ShopCategoriesResponseItem>()

                            if (result.data != null) {
                                if (!result.data.isEmpty()) {
                                    result.data.forEach { one ->
                                        if (!current.contains(one)) {
                                            current.add(one)
                                        }
                                        _uiState.update {
                                            it.copy(
                                                shopCategoryList = current
                                            )
                                        }
                                        Log.d("FFTAG", "getShopCategories: $current")
                                        addShopViewModel._addShopUiState.update {
                                            it.copy(
                                                shopCategoryList = current
                                            )
                                        }

                                    }
                                    appHomePageViewModel.uiState.update {
                                        it.copy(pageLoading = false)
                                    }
                                }
                            }
                        }
                        is Resource.Loading -> {
                            appHomePageViewModel.uiState.update {
                                it.copy(pageLoading = true)
                            }
                        }
                        is Resource.Error -> {
                            result.message?.let { message ->
                                appHomePageViewModel.uiState.update {
                                    it.copy(pageLoading = false, errorList = listOf(message))
                                }
                            }

                        }
                    }

                }
            /*    val res = shopFrontendRepository.getAllShopCategories()
    */

        }

    }


/*    val currentone: MutableList<ShopCategoriesResponseItem> = mutableListOf()
    fun getShopCategories(item: ShopCategoriesResponseItem) {
        currentone.add(item)

        _uiState.update { it.copy(shopCategoryList = currentone) }
    }*/

    fun onCategorySelected() {
        appViewModel?.navigate(ShopsFrontDestination.SHOPS_MAP)
    }

    fun onShopReviewSelected() {
        appViewModel?.navigate(AppDestinations.REVIEWSHOP)
    }


    fun onAllCategorySelected() {
        appViewModel?.navigate(ShopsFrontDestination.SHOP_CATEGORIES)
    }

    fun onShopSelected(
        shopId: String,
        //appHomePageViewmodel: AppHomePageViewModel,
        shopHomePageViewModel: ShopHomePageViewModel
    ) {
        viewModelScope.launch {
            for (i in 1..10) {

                shopHomePageViewModel.getGetShopProducts(shopId)
                if (i == 3) {
                    appViewModel?.navigate(ShopsFrontDestination.SINGLE_SHOP)
                }
            }
        }

    }

}