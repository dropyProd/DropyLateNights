package com.example.dropy.ui.screens.shops.backside.shoporderhistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.backside.dashboard.orders.orderlist.OrderHistoryContent
import com.example.dropy.ui.screens.shops.backside.orderdetails.OrderDetailsViewModel
import com.example.dropy.ui.screens.shops.backside.shopincomingorders.ShopIncomingOrdersViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.trackyourorder.TrackYourOrderViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel

@Composable
fun ShopOrderHistory(
    shopOrderHistoryViewModel: ShopOrderHistoryViewModel,
    shopIncomingOrdersViewModel: ShopIncomingOrdersViewModel,
    orderDetailsViewModel: OrderDetailsViewModel,
    trackYourOrderViewModel: TrackYourOrderViewModel,
    changeType: () -> Unit,
    shopHomePageViewModel: ShopHomePageViewModel
){
    val appUiState = shopOrderHistoryViewModel.appViewModel!!.appUiState.collectAsState()
    val uiState by shopOrderHistoryViewModel.shopOrderHistoryUiState.collectAsState()
    val orderuiState by shopIncomingOrdersViewModel.shopIncomingOrdersUiState.collectAsState()


    AppScaffold(
        content = {
            OrderHistoryContent(
                uiState = uiState,
                onOrderSelected = {
//                    orderDetailsViewModel.setOrderId(it)
                                  /*shopOrderHistoryViewModel.onOrderSelected(it) */},
                orderuiState = orderuiState,
                appViewModel =  shopOrderHistoryViewModel.appViewModel!!,
                changeType = changeType,
                trackYourOrderViewModel = trackYourOrderViewModel ,
                shopHomePageViewModel = shopHomePageViewModel
            )
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { shopOrderHistoryViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { shopOrderHistoryViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { shopOrderHistoryViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = {shopOrderHistoryViewModel.appViewModel?.navigate(it)},
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { shopOrderHistoryViewModel.appViewModel?.onSelectProfile(it)},
        activeProfile = appUiState.value.activeProfile,
    )
}

@Preview(showBackground = true)
@Composable
fun ShopOrderHistoryPreview(){
    Column(Modifier.fillMaxSize()) {
     //   ShopOrderHistory(hiltViewModel())
    }
}