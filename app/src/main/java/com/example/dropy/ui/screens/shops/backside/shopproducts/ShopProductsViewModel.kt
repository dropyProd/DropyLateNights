package com.example.dropy.ui.screens.shops.backside.shopproducts

import androidx.lifecycle.ViewModel
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsBacksideNavigation
import com.example.dropy.ui.components.shops.backside.dashboard.product.productslist.ProductsListItemDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class ShopProductsUiState(
    val productList: List<ProductsListItemDataClass> = emptyList(),
    val productCategoryList:List<String> = emptyList(),
    val searchParameter:String = "",
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class ShopProductsViewModel @Inject constructor():ViewModel() {

    val uiState = MutableStateFlow(ShopProductsUiState())
    val shopProductsUiState:StateFlow<ShopProductsUiState> = uiState.asStateFlow()

    var appViewModel:AppViewModel? =null

    init {
        getShopProducts()
    }


    private fun getShopProducts(){
        val productsList = listOf(
            ProductsListItemDataClass(
                productId = 1,
                productImageUrl = "",
                productName = "Some other very long product name",
                numberOfUnits = 2,
                price = 4321,
                alwaysAvailable = true
            ),
            ProductsListItemDataClass(
                productId = 1,
                productImageUrl = "",
                productName = "Some other very long product name",
                numberOfUnits = 2,
                price = 4321,
                alwaysAvailable = true
            ),
            ProductsListItemDataClass(
                productId = 1,
                productImageUrl = "",
                productName = "Some other very long product name",
                numberOfUnits = 2,
                price = 4321,
                alwaysAvailable = true
            )
        )
        uiState.update { it.copy(productList = productsList) }
    }


    fun onSearchParameterChanged(productName:String){
        uiState.update { it.copy(searchParameter = productName) }
    }
    fun onAddProductClicked(){
        appViewModel?.navigate(ShopsBacksideNavigation.ADD_PRODUCT)
    }
    fun onSearchButtonClicked(){

    }
    fun onCategorySelected(categoryName:String){

    }
    fun onProductSelected(productId:Int){
        appViewModel?.navigate(ShopsBacksideNavigation.PRODUCT_DETAILS)
    }
}