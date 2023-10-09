package com.example.dropy.ui.screens.shops.frontside.dashboard.customerdashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.commons.appdrawer.ProfileTypes
import com.example.dropy.ui.components.shops.frontside.customer.customerdashboard.CustomerDashboardContent
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.shops.backside.shopincomingorders.ShopIncomingOrdersViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.dashboard.orderhistory.CustomerOrderHistoryViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomerDashboard(
    customerDashboardViewModel: CustomerDashboardViewModel,
    appViewModel: AppViewModel,
    shopIncomingOrdersViewModel: ShopIncomingOrdersViewModel,
    customerOrderHistoryViewModel: CustomerOrderHistoryViewModel,
    cartPageViewModel: CartPageViewModel,
    appHomePageViewModel: AppHomePageViewModel,
    shopHomePageViewModel: ShopHomePageViewModel,
    onAddProfileLogo: () -> Unit
) {
    val appUiState = customerDashboardViewModel.appViewModel!!.appUiState.collectAsState()
    val shopIncomingorderuiState by shopIncomingOrdersViewModel.shopIncomingOrdersUiState.collectAsState()

    val uiState by customerDashboardViewModel.customerDashboardUiState.collectAsState()
    val customerOrdersHistoryUiState by customerOrderHistoryViewModel.customerHistoryUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    LaunchedEffect(key1 = true, block = {
        customerOrderHistoryViewModel.clearList()
        customerOrderHistoryViewModel.getOrders(appHomePageViewModel)
    })

/*    LaunchedEffect(key1 = true, block = {
        appViewModel.appUiState.value.activeProfile?.id?.let {
            shopIncomingOrdersViewModel.getPendingOrders(it)
        }
    })*/

    AppScaffold(
        content = {
            appUiState.value.activeProfile?.name?.let {
                CustomerDashboardContent(
                    uiState = uiState,
                    customerName = it,
                    shopIncomingOrderUistate = shopIncomingorderuiState,
                    onOrderHistoryClicked = { customerDashboardViewModel.onOrderHistoryClicked() },
                    onParcelsHistoryClicked = { customerDashboardViewModel.onParcelsHistoryClicked() },
                    onRidesHistoryClicked = { customerDashboardViewModel.onRidesHistoryClicked() },
                    onMyAddressesClicked = { customerDashboardViewModel.onMyAddressesClicked() },
                    onTransactionHistoryClicked = { customerDashboardViewModel.onTransactionHistoryClicked() },
                    customerOrdersHistoryUiState = customerOrdersHistoryUiState,
                    appHomePageViewModel = appHomePageViewModel,
                    onShopSelected = { id, shop ->
                        if (appViewModel.appUiState.value.activeProfile?.type.equals(ProfileTypes.CUSTOMER)) {
                            appHomePageViewModel.onShopSelected(
                                id, shop,
                                shopHomePageViewModel,
                                appHomePageViewModel = appHomePageViewModel
                            )
                        }
                    },
                    onAddProfileLogo = onAddProfileLogo
                )
            }
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { customerDashboardViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { customerDashboardViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { customerDashboardViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { customerDashboardViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { customerDashboardViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false
    )

}


@Preview(showBackground = true)
@Composable
fun CustomerDashboardPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        // viewModel.appViewModel= AppViewModel()
        //  CustomerDashboard(viewModel)
    }
}