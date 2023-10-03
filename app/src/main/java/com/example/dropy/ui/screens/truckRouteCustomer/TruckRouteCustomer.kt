package com.example.dropy.ui.screens.truckRouteCustomer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.nearestWaterPoint.NearestWaterPointContent
import com.example.dropy.ui.screens.nearestWaterPoint.NearestWaterPointViewModel
import com.example.dropy.ui.screens.scanQRWater.ScanQRWaterViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.truckIncomingWork.TruckIncomingWorkViewModel
import com.example.dropy.ui.screens.truckStartTrip.TruckStartTripViewModel

@Composable
fun TruckRouteCustomer(
    cartPageViewModel: CartPageViewModel,
    truckRouteCustomerViewModel: TruckRouteCustomerViewModel,
    truckIncomingWorkViewModel: TruckIncomingWorkViewModel,
    truckStartTripViewModel: TruckStartTripViewModel,
    scanQRWaterViewModel: ScanQRWaterViewModel
) {

    val truckRouteCustomerUiState by truckRouteCustomerViewModel.truckRouteCustomerUiState.collectAsState()
    val truckIncomingWorkUiState by truckIncomingWorkViewModel.truckIncomingWorkUiState.collectAsState()

    val appUiState = truckRouteCustomerViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true, block ={
        truckRouteCustomerViewModel.getMyLocale(context)
    } )

    AppScaffold(
        content = {
            TruckRouteCustomerContent(
                scanQRClicked = {
                    truckRouteCustomerViewModel.navigatewaterScanQr(
                        truckStartTripViewModel = truckStartTripViewModel,
                        truckIncomingWorkUiState = truckIncomingWorkUiState,
                        scanQRWaterViewModel = scanQRWaterViewModel
                    )
                },
                appViewModel = truckRouteCustomerViewModel.appViewModel!!,
                truckRouteCustomerUiState  = truckRouteCustomerUiState,
                truckIncomingWorkUiState = truckIncomingWorkUiState,
                appUiState = appUiState.value
            )
        },
        pageLoading = truckRouteCustomerUiState.pageLoading,
        actionLoading = truckRouteCustomerUiState.actionLoading,
        errorList = truckRouteCustomerUiState.errorList,
        messageList = truckRouteCustomerUiState.messageList,
        onBackButtonClicked = { truckRouteCustomerViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { truckRouteCustomerViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { truckRouteCustomerViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { truckRouteCustomerViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { truckRouteCustomerViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = false
    )
}