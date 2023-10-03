package com.example.dropy.ui.screens.shops.backside.shoporderhistory


import androidx.lifecycle.ViewModel
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsBacksideNavigation
import com.example.dropy.ui.components.shops.backside.dashboard.orders.orderlist.OrderListItemDataClass
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class ShopOrderHistoryUiState(
    val orderList: List<OrderListItemDataClass> = emptyList(),
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)


class ShopOrderHistoryViewModel :ViewModel() {

    private val uiState = MutableStateFlow(ShopOrderHistoryUiState())

    val shopOrderHistoryUiState:StateFlow<ShopOrderHistoryUiState> = uiState.asStateFlow()

    var appViewModel:AppViewModel? = null

    init {
      //  getPendingOrders()
    }

    private fun getPendingOrders(){
/*        val orders = mutableListOf(
            OrderListItemDataClass(
                orderId = 1,
                customerName = "name",
                orderNumber = "1234",
                isProcessed = true,
                numberOfItems = 4,
                cost = 4321,
                isPaid = true,
                timeString = "13:03",
                status = "picked",
                shopLocale = LatLng(0.0, 0.0)
            ),
            OrderListItemDataClass(
                orderId = 1,
                customerName = "name",
                orderNumber = "1234",
                isProcessed = false,
                numberOfItems = 4,
                cost = 4321,
                isPaid = false,
                timeString = "13:03",
                status = "cancelled",
                shopLocale = LatLng(0.0, 0.0)
            )
        )*/
        uiState.update { it.copy(orderList = listOf()) }
    }


    fun onOrderSelected(orderId:Int){
        appViewModel?.navigate(ShopsBacksideNavigation.ORDER_DETAILS)
    }

}