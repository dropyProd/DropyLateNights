package com.example.dropy.ui.screens.waterpointOrders

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.water.waterHome.WaterHomeContent

@Composable
fun WaterpointOrders(
    waterpointOrdersViewModel: WaterpointOrdersViewModel,
    cartPageViewModel: CartPageViewModel,
    scanQr: () -> Unit
) {

    val waterPointOrderUiState by waterpointOrdersViewModel.waterPointOrderUiState.collectAsState()

    val appUiState = waterpointOrdersViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    LaunchedEffect(key1 = true, block = {
        waterpointOrdersViewModel.getWaterpointOrders()
    })

    AppScaffold(
        content = {
           WaterpointOrdersContent(navigate = {}, waterPointOrderUiState = waterPointOrderUiState, navigateNewOrder = {
               waterpointOrdersViewModel.navigateNewOrder()
           }, scanQr = scanQr)
        },
        pageLoading = waterPointOrderUiState.pageLoading,
        actionLoading = waterPointOrderUiState.actionLoading,
        errorList = waterPointOrderUiState.errorList,
        messageList = waterPointOrderUiState.messageList,
        onBackButtonClicked = { waterpointOrdersViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { waterpointOrdersViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { waterpointOrdersViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { waterpointOrdersViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { waterpointOrdersViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = true
    )
}