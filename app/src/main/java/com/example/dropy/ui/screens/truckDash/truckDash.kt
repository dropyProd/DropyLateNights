package com.example.dropy.ui.screens.truckDash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.water.waterHome.WaterHomeContent
import com.example.dropy.ui.screens.water.waterHome.WaterHomeViewModel

@Composable
fun TruckDash(
    cartPageViewModel: CartPageViewModel,
    truckDashViewModel: TruckDashViewModel
) {

    val truckDashUiState by truckDashViewModel.truckDashUiState.collectAsState()

    val appUiState = truckDashViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    LaunchedEffect(key1 = true, block = {
        truckDashViewModel.appViewModel!!.getIndividualOrders()
        truckDashViewModel.getCustomers()
    })

    AppScaffold(
        content = {
            TruckDashContent(navigateFindJobs = truckDashViewModel::navigateFindJobs, truckDashUiState = truckDashUiState)
        },
        pageLoading = truckDashUiState.pageLoading,
        actionLoading = truckDashUiState.actionLoading,
        errorList = truckDashUiState.errorList,
        messageList = truckDashUiState.messageList,
        onBackButtonClicked = { truckDashViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { truckDashViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { truckDashViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { truckDashViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { truckDashViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = true
    )
}