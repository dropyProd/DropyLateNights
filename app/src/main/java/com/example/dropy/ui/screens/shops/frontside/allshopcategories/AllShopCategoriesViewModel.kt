package com.example.dropy.ui.screens.shops.frontside.allshopcategories

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.network.models.cart.GetCartResponseItem
import com.example.dropy.network.models.getshopproductcategories.GetShopProductCategoriesResponse
import com.example.dropy.network.models.productdetails.ProductDetailsResponse
import com.example.dropy.network.models.shopCategories.ShopCategoriesResponse
import com.example.dropy.network.repositories.shop.ShopRepository
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.shops.frontside.shopcategories.ShopsCategoryListItemDataClass
import com.example.dropy.ui.components.shops.shopscommons.billboards.ShopBillboardDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AllShopCategoriesPageUiState(
    val billBoardItems:List<ShopBillboardDataClass> = emptyList(),
    val categoryList:List<ShopsCategoryListItemDataClass> = emptyList(),
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class AllShopCategoriesViewModel  @Inject constructor() :ViewModel(){

    private val _uiState  = MutableStateFlow(AllShopCategoriesPageUiState())

    val allShopCategoryPageUiState: StateFlow<AllShopCategoriesPageUiState> = _uiState.asStateFlow()

    var appViewModel:AppViewModel? = null

    init {
        getBillBoardItems()
        getAllShopCategories()
    }

    private fun getBillBoardItems(){

        val billBoardItems = emptyList<ShopBillboardDataClass>()

        _uiState.update { it.copy(billBoardItems = billBoardItems) }

    }



    private fun getAllShopCategories(){
        val categoryList = mutableListOf(
            ShopsCategoryListItemDataClass(
                categoryName = "category name"
            ),
            ShopsCategoryListItemDataClass(
                categoryName = "category name"
            ),
            ShopsCategoryListItemDataClass(
                categoryName = "category name"
            ),
            ShopsCategoryListItemDataClass(
                categoryName = "category name"
            ),
            ShopsCategoryListItemDataClass(
                categoryName = "category name"
            )
        )

        _uiState.update { it.copy(categoryList = categoryList) }

    }

    fun onCategorySelected(){
        appViewModel?.navigate(ShopsFrontDestination.SINGLE_CATEGORY)
    }


}
