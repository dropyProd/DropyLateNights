package com.example.dropy.ui.screens.shops.frontside.searchresults.productssearchresult

import androidx.lifecycle.ViewModel
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.maps.MapMarkerDataClass
import com.example.dropy.ui.components.shops.frontside.searchresults.productssearchresult.ProductsSearchResultListItemDataClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ProductsSearchResultPageUiState(
    val markersList:List<MapMarkerDataClass> = emptyList(),
    val productsSearchResults:List<ProductsSearchResultListItemDataClass> = emptyList(),
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

class ProductsSearchResultPageViewModel:ViewModel() {

    private val uiState = MutableStateFlow(ProductsSearchResultPageUiState())

    val productsSearchResultPageUiState : StateFlow<ProductsSearchResultPageUiState> = uiState.asStateFlow()

    var appViewModel:AppViewModel? = null

    init {
        getSearchResults()
        getMarkerList()
    }

    private fun getSearchResults(){
        val productsSearchResultList = listOf(
            ProductsSearchResultListItemDataClass(
                productPhotoUrl = "",
                productName = "mama otis fish ",
                shopName = "mama otis kiosk",
                timeInMinutes = 67,
                distanceInMeters = 234,
                rating = 4.7,
                price = 1293,
            ),
            ProductsSearchResultListItemDataClass(
                productPhotoUrl = "",
                productName = "mama otis fish ",
                shopName = "mama otis kiosk",
                timeInMinutes = 67,
                distanceInMeters = 234,
                rating = 4.7,
                price = 1293,
            ),
            ProductsSearchResultListItemDataClass(
                productPhotoUrl = "",
                productName = "mama otis fish ",
                shopName = "mama otis kiosk",
                timeInMinutes = 67,
                distanceInMeters = 234,
                rating = 4.7,
                price = 1293,
            ),
        )

        uiState.update { it.copy(productsSearchResults = productsSearchResultList) }
    }

    private fun getMarkerList(){

    }

    fun onAddProductClicked (){
        TODO()
    }
    fun onProductSelected (){
        TODO()
    }

}