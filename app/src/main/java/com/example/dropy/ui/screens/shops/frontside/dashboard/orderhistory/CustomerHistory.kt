package com.example.dropy.ui.screens.shops.frontside.dashboard.orderhistory

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.backside.dashboard.orders.orderlist.OrderHistoryContent
import com.example.dropy.ui.components.shops.frontside.customer.customerdashboard.orderhistory.CustomerOrderHistoryContent
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.shops.backside.shopincomingorders.ShopIncomingOrdersViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.trackyourorder.TrackYourOrderViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import com.example.dropy.ui.screens.waterMyOrder.WaterMyOrderViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomerOrderHistory(
    customerOrderHistoryViewModel: CustomerOrderHistoryViewModel,
    shopIncomingOrdersViewModel: ShopIncomingOrdersViewModel,
    trackYourOrderViewModel: TrackYourOrderViewModel,
    cartPageViewModel: CartPageViewModel,
    appHomePageViewModel: AppHomePageViewModel,
    shopHomePageViewModel: ShopHomePageViewModel,
    waterMyOrderViewModel: WaterMyOrderViewModel
) {

    LaunchedEffect(key1 = true, block = {
        customerOrderHistoryViewModel.getOrders(appHomePageViewModel)
    })
    val appUiState = customerOrderHistoryViewModel.appViewModel!!.appUiState.collectAsState()
    val uiState by customerOrderHistoryViewModel.customerHistoryUiState.collectAsState()
    val orderuiState by shopIncomingOrdersViewModel.shopIncomingOrdersUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

/*    LaunchedEffect(key1 = true, block = {
        customerOrderHistoryViewModel.getOrders(orderuiState.orderList)
    })*/

    LaunchedEffect(key1 = true, block = {
        customerOrderHistoryViewModel.getMyWaterOrders()
    })
    AppScaffold(
        content = {
            CustomerOrderHistoryContent(
                uiState = uiState,
                onViewOrderStatus = {
                    customerOrderHistoryViewModel.onViewOrderStatus(
                        it,
                        shopIncomingOrdersViewModel
                    )
                },
                navigate = {customerOrderHistoryViewModel.navigate(waterMyOrderViewModel, it)},
                onViewOrderReceipt = { customerOrderHistoryViewModel.onViewOrderReceipt(it) },
                onCompleteOrder = { customerOrderHistoryViewModel.onCompleteOrder(it) },
                appViewModel = customerOrderHistoryViewModel.appViewModel!!,
                trackYourOrderViewModel = trackYourOrderViewModel,
                shopHomePageViewModel = shopHomePageViewModel
            )
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { customerOrderHistoryViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { customerOrderHistoryViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { customerOrderHistoryViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { customerOrderHistoryViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { customerOrderHistoryViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = false
    )
}


@Preview(showBackground = true)
@Composable
fun CustomerOrderHistoryPreview() {


}