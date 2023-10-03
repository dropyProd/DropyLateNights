package com.example.dropy.ui.screens.shops.backside.shopincomingorders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.backside.dashboard.orders.orderlist.PendingOrdersContent
import com.example.dropy.ui.screens.shops.backside.orderdetails.OrderDetailsViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.trackyourorder.TrackYourOrderViewModel


@Composable
fun ShopIncomingOrders(
    shopIncomingOrdersViewModel: ShopIncomingOrdersViewModel,
    orderDetailsViewModel: OrderDetailsViewModel,
    trackYourOrderViewModel: TrackYourOrderViewModel
){
    val appUiState = shopIncomingOrdersViewModel.appViewModel!!.appUiState.collectAsState()
    val uiState by shopIncomingOrdersViewModel.shopIncomingOrdersUiState.collectAsState()

    AppScaffold(
        content = {
                  PendingOrdersContent(
                      uiState = uiState,
                      onOrderSelected = {
                          id, shopId, customerId, order ->
                          if (order != null) {
                              orderDetailsViewModel.setOrderId(/*it*/id, shopId, customerId,order)
                          }
                      },
                      onOrderStatusSelected = {
                         // shopIncomingOrdersViewModel.appViewModel!!.navigate(ShopsFrontDestination.TRACK_YOUR_ORDER)
                      },
                      orderDetailsViewModel = orderDetailsViewModel,
                      appViewModel = shopIncomingOrdersViewModel.appViewModel!!,
                      trackYourOrderViewModel = trackYourOrderViewModel
                  )
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { shopIncomingOrdersViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { shopIncomingOrdersViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { shopIncomingOrdersViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = {shopIncomingOrdersViewModel.appViewModel?.navigate(it)},
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { shopIncomingOrdersViewModel.appViewModel?.onSelectProfile(it)},
        activeProfile = appUiState.value.activeProfile,
    )


}

@Preview(showBackground = true)
@Composable
fun ShopIncomingOrdersPreview(){
    Column(Modifier.fillMaxSize()) {
      //  ShopIncomingOrders(ShopIncomingOrdersViewModel())
    }
}