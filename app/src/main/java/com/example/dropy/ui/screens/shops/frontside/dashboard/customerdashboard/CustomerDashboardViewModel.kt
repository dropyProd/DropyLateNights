package com.example.dropy.ui.screens.shops.frontside.dashboard.customerdashboard

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.shops.frontside.shoplist.ShopListItemDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class CustomerDashboardUiState(
    val customerName:String = "customer name",
    val noOfOrders:Int = 0,
    val noOfRides:Int = 0,
    val noOfParcels:Int = 0,
    val coverPhoto: ImageBitmap? = null,
    val coverPhotoUri: Uri? = null,
    val noOfTransactions:Int = 0,
    val noOfAddresses:Int = 0,
    val favouriteShops:List<ShopListItemDataClass> = emptyList(),
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class CustomerDashboardViewModel @Inject constructor(
    private val shopFrontendRepository: ShopFrontendRepository
):ViewModel() {

    private val uiState = MutableStateFlow(CustomerDashboardUiState())

    val customerDashboardUiState:StateFlow<CustomerDashboardUiState> = uiState.asStateFlow()

    var appViewModel:AppViewModel? = null

    init {
        getFavouriteShops()
    }

    private fun getFavouriteShops(){
        val favShops = listOf(
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
            )
        )
        uiState.update { it.copy(favouriteShops = favShops) }
    }

    fun onOrderHistoryClicked(){
      //  appViewModel?.navigate(ShopsBacksideNavigation.ORDER_HISTORY)
        appViewModel?.navigate(ShopsFrontDestination.CUSTOMER_ORDER_HISTORY)
    }
    fun onMyWalletClicked(){
        appViewModel!!.navigate(AppDestinations.MY_WALLET)
    }
    fun onParcelsHistoryClicked(){

    }
    fun onRidesHistoryClicked(){

    }
    fun onMyAddressesClicked(){
        appViewModel?.navigate(ShopsFrontDestination.CUSTOMER_ADDRESS_BOOK)
    }
    fun onTransactionHistoryClicked(){

    }
    fun setValues(uri: Uri, bitmap: Bitmap){
        uiState.update {
            it.copy(
                coverPhotoUri = uri,
                coverPhoto = bitmap.asImageBitmap()
            )
        }
    }

}