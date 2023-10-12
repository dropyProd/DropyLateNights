package com.example.dropy.ui.screens.watepointDash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.truckDash.TruckDashContent
import com.example.dropy.ui.screens.truckDash.TruckDashViewModel

@Composable
fun WaterPointDash(
    cartPageViewModel: CartPageViewModel,
    waterPointDashViewModel: WaterPointDashViewModel
) {
    val waterPointDashUiState by waterPointDashViewModel.waterPointDashUiState.collectAsState()

    val appUiState = waterPointDashViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    LaunchedEffect(key1 = true, block = {
        try {
            waterPointDashViewModel.getWaterpoint()
            waterPointDashViewModel.getWaterpointOrders()
        }catch (e: Exception){

        }
    })

    AppScaffold(
        content = {
            WaterPointDashContent(waterPointDashUiState = waterPointDashUiState, navigateOrders = waterPointDashViewModel::navigateOrders)
        },
        pageLoading = waterPointDashUiState.pageLoading,
        actionLoading = waterPointDashUiState.actionLoading,
        errorList = waterPointDashUiState.errorList,
        messageList = waterPointDashUiState.messageList,
        onBackButtonClicked = { waterPointDashViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { waterPointDashViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { waterPointDashViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { waterPointDashViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { waterPointDashViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = true
    )
}

