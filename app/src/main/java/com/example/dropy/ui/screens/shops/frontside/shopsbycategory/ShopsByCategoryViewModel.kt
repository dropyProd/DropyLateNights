package com.example.dropy.ui.screens.shops.frontside.shopsbycategory

import androidx.lifecycle.ViewModel
import com.example.dropy.network.models.ShopsResponseNewItem
import com.example.dropy.network.models.shops.Shop
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.shops.frontside.shoplist.ShopListItemDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class ShopsByCategoryPageUiState(
    val categoryName:String = "",
    val shopList:List<ShopsResponseNewItem> = emptyList(),
    val errors:List<String> = emptyList(),
    val messages:List<String> = emptyList(),
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class ShopsByCategoryViewModel @Inject constructor() :ViewModel(){

    private val uiState = MutableStateFlow(ShopsByCategoryPageUiState())
    val shopsByCategoryPageUiState:StateFlow<ShopsByCategoryPageUiState> = uiState.asStateFlow()
    var appViewModel:AppViewModel? = null

    init {
        getShopByCategory("food")
    }


    private fun getShopByCategory(categoryName:String){
        val shopList = mutableListOf(
            ShopListItemDataClass(
                shopName = "shop 1",
                shopId = 1
            ),
            ShopListItemDataClass(
                shopName = "shop 2",
                shopId = 2
            ),
            ShopListItemDataClass(
                shopName = "shop 3",
                shopId = 3
            ),
            ShopListItemDataClass(
                shopName = "shop 4",
                shopId = 4
            ),
            ShopListItemDataClass(
                shopName = "shop 1",
                shopId = 5
            ),
        )

        uiState.update { it.copy(shopList = listOf(), categoryName = categoryName) }
    }

    fun onShopSelected(shopId:String){
        appViewModel?.navigate(ShopsFrontDestination.SINGLE_SHOP)
    }
    fun onShopReview(){
        appViewModel?.navigate(AppDestinations.REVIEWSHOP)
    }
}