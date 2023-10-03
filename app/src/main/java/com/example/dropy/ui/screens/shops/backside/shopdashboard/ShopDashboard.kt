package com.example.dropy.ui.screens.shops.backside.shopdashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.backside.dashboard.ShopDashboardContent
import com.example.dropy.ui.screens.shops.backside.shopincomingorders.ShopIncomingOrdersViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel

@Composable
fun ShopDashboard(
    shopDashboardViewModel: ShopDashboardViewModel,
    shopIncomingOrdersViewModel: ShopIncomingOrdersViewModel,
    shopHomePageViewModel: ShopHomePageViewModel,
    appViewModel: AppViewModel
) {

    LaunchedEffect(key1 = true, block = {
        appViewModel.appUiState.value.activeProfile?.id?.let {
            shopHomePageViewModel.getGetShopProducts(
                it.toString()
            )
        }
    })

    val context = LocalContext.current

    LaunchedEffect(key1 = true, block = {
     /*   appViewModel.appUiState.value.activeProfile?.id?.let {
            shopIncomingOrdersViewModel.getPendingOrders(it)
        }*/



        shopIncomingOrdersViewModel.getPendingOrders(context)


    })
    LaunchedEffect(key1 = true, block = {
        appViewModel.appUiState.value.activeProfile?.id?.let {
            shopIncomingOrdersViewModel.getCompletedOrders(it)
        }

    })
    val appUiState = shopDashboardViewModel.appViewModel!!.appUiState.collectAsState()

    val uiState by shopDashboardViewModel.shopDashboardUiState.collectAsState()
    val shopIncomingorderuiState by shopIncomingOrdersViewModel.shopIncomingOrdersUiState.collectAsState()
    val shopHomePageUiState by shopHomePageViewModel.shopHomePageUiState.collectAsState()

    AppScaffold(
        content = {
            ShopDashboardContent(
                uiState = uiState,
                goToIncomingOrders = shopDashboardViewModel::goToIncomingOrders,
                goToEarnings = shopDashboardViewModel::goToEarnings,
                goToOrderHistory = shopDashboardViewModel::goToOrderHistory,
                goToMessages = shopDashboardViewModel::goToMessages,
                goToWallet = shopDashboardViewModel::goToWallet,
                goToProducts = shopDashboardViewModel::goToProducts,
                goToCustomers = shopDashboardViewModel::goToCustomers,
                goToDisputes = shopDashboardViewModel::goToDisputes,
                goToCampaigns = shopDashboardViewModel::goToCampaigns,
                incomingorderuiState = shopIncomingorderuiState,
                shopHomePageUiState = shopHomePageUiState,
                goToDeliveries = shopDashboardViewModel::goToDeliveries,
                onCheckedChange = shopDashboardViewModel::setShopOpen
            )
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { shopDashboardViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { shopDashboardViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { shopDashboardViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { shopDashboardViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { shopDashboardViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        showimg = true
    )
}


@Preview(showBackground = true)
@Composable
fun ShopDashboardPreview() {
    Column(Modifier.fillMaxSize()) {
        // ShopDashboard(shopDashboardViewModel = ShopDashboardViewModel())
    }
}