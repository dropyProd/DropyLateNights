package com.example.dropy.ui.screens.truckFindJob

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.scanQRWater.ScanQRWaterContent
import com.example.dropy.ui.screens.scanQRWater.ScanQRWaterViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel

@Composable
fun TruckFindJob(
    cartPageViewModel: CartPageViewModel,
    truckFindJobViewModel: TruckFindJobViewModel
) {

    val truckFindJobUiState by truckFindJobViewModel.truckFindJobUiState.collectAsState()

    val appUiState = truckFindJobViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true, block = {
        truckFindJobViewModel.getMyLocale(context)
    })

    AppScaffold(
        content = {
            TruckFindJobContent(
                navigateNewJob = truckFindJobViewModel::navigateIncomingjob,
                navigateOrders = truckFindJobViewModel::navigateOrders,
                appUiState = appUiState.value,
                navigateFindJobs = truckFindJobViewModel::navigateWorkDiary,
                truckFindJobUiState = truckFindJobUiState
            )
        },
        pageLoading = truckFindJobUiState.pageLoading,
        actionLoading = truckFindJobUiState.actionLoading,
        errorList = truckFindJobUiState.errorList,
        messageList = truckFindJobUiState.messageList,
        onBackButtonClicked = { truckFindJobViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { truckFindJobViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { truckFindJobViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { truckFindJobViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { truckFindJobViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = false
    )
}