package com.example.dropy.ui.screens.shops.frontside.searchresults.shopssearchresult

import androidx.lifecycle.ViewModel
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.maps.MapMarkerDataClass
import com.example.dropy.ui.components.shops.frontside.searchresults.shopssearchresult.ShopsSearchResultListItemDataClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class ShopsSearchResultPageUiState(
    val markersList:List<MapMarkerDataClass> = emptyList(),
    val shopsSearchResults:List<ShopsSearchResultListItemDataClass> = emptyList(),
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

class ShopsSearchResultPageViewModel:ViewModel() {

    private val uiState = MutableStateFlow(ShopsSearchResultPageUiState())

    val shopsSearchResultPageUiState : StateFlow<ShopsSearchResultPageUiState> = uiState.asStateFlow()

    var appViewModel:AppViewModel? = null

    init {
        getSearchResults()
        getMarkerList()
    }

    private fun getSearchResults(){
        val shopsSearchResultList = listOf(
            ShopsSearchResultListItemDataClass(
                shopLogoUrl = "",
                shopName = "shop name",
                shopLocation = "some shop location",
                timeInMinutes = 67,
                distanceInMeters = 234,
                rating = 4.7
            ),
            ShopsSearchResultListItemDataClass(
                shopLogoUrl = "",
                shopName = "shop name",
                shopLocation = "some shop location",
                timeInMinutes = 67,
                distanceInMeters = 234,
                rating = 4.7
            ),
            ShopsSearchResultListItemDataClass(
                shopLogoUrl = "",
                shopName = "shop name",
                shopLocation = "some shop location",
                timeInMinutes = 67,
                distanceInMeters = 234,
                rating = 4.7
            ),
        )

        uiState.update { it.copy(shopsSearchResults = shopsSearchResultList) }
    }

    private fun getMarkerList(){

    }

    fun onShopSelected (){

    }

}