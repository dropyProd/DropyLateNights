package com.example.dropy.ui.screens.shops.backside.shopdashboard

import androidx.lifecycle.ViewModel
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsBacksideNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


data class ShopDashboardUiState(
    val shopOpen: Boolean = false,
    val incomingOrders: Int = 0,
    val totalEarnings: Int = 0,
    val totalOrders: Int = 0,
    val totalMessages: Int = 0,
    val totalBalance: Int = 0,
    val totalProducts: Int = 0,
    val averageRating: Double = 0.0,
    val totalCustomers: Int = 0,
    val disputes: Int = 0,
    val activeCampaigns: Int = 0,
    val totalDeliveries: Int = 0,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class ShopDashboardViewModel @Inject constructor():ViewModel() {

    private val uiState = MutableStateFlow(ShopDashboardUiState())
    val shopDashboardUiState:StateFlow<ShopDashboardUiState> = uiState.asStateFlow()

    var appViewModel:AppViewModel? = null


    fun goToIncomingOrders(){
        appViewModel?.navigate(ShopsBacksideNavigation.INCOMING_ORDERS)
    }
    fun goToEarnings(){

    }
    fun goToOrderHistory(){
        appViewModel?.navigate(ShopsBacksideNavigation.ORDER_HISTORY)
    }
    fun setShopOpen(boolean: Boolean){
        uiState.update {
            it.copy(shopOpen = boolean)
        }
    }
    fun goToMessages(){

    }
    fun goToWallet(){

    }
    fun goToProducts(){
        appViewModel?.navigate(ShopsBacksideNavigation.SHOP_PRODUCTS)
    }
    fun goToCustomers(){

    }
    fun goToDisputes(){

    }
    fun goToCampaigns(){

    }
    fun goToDeliveries(){

    }

}