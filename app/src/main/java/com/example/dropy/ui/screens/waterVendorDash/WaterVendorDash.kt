package com.example.dropy.ui.screens.waterVendorDash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.truckOrders.TruckOrdersViewModel
import com.example.dropy.ui.screens.water.waterHome.WaterHomeContent
import com.example.dropy.ui.screens.water.waterHome.WaterHomeViewModel

@Composable
fun WaterVendorDash(
    cartPageViewModel: CartPageViewModel,
    waterVendorDashViewModel: WaterVendorDashViewModel,
    truckOrdersViewModel: TruckOrdersViewModel
) {

    val waterVendorDashUiState by waterVendorDashViewModel.waterVendorDashUiState.collectAsState()

    val appUiState = waterVendorDashViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()


    LaunchedEffect(key1 = true, block = {
        waterVendorDashViewModel.getWaterTrucks()
    })


    AppScaffold(
        content = {
            WaterVendorDashContent(waterVendorDashUiState = waterVendorDashUiState, navigateTrucks = waterVendorDashViewModel::navigateMyTrucks, truckClicked = { waterVendorDashViewModel.navigateTruckOrders(truckOrdersViewModel, it) })
        },
        pageLoading = waterVendorDashUiState.pageLoading,
        actionLoading = waterVendorDashUiState.actionLoading,
        errorList = waterVendorDashUiState.errorList,
        messageList = waterVendorDashUiState.messageList,
        onBackButtonClicked = { waterVendorDashViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { waterVendorDashViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { waterVendorDashViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { waterVendorDashViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { waterVendorDashViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = true
    )
}