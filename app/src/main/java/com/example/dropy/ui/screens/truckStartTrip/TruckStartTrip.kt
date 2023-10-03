package com.example.dropy.ui.screens.truckStartTrip

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

@Composable
fun TruckStartTrip(
    cartPageViewModel: CartPageViewModel,
    truckStartTripViewModel: TruckStartTripViewModel,
    nearestWaterPointViewModel: NearestWaterPointViewModel,
    truckIncomingWorkViewModel: TruckIncomingWorkViewModel,
    scanQRWaterViewModel: ScanQRWaterViewModel
) {

    val truckStartTripUiState by truckStartTripViewModel.truckStartTripUiState.collectAsState()
    val nearestWaterPointUiState by nearestWaterPointViewModel.nearestWaterPointUiState.collectAsState()
    val truckIncomingWorkUiState by truckIncomingWorkViewModel.truckIncomingWorkUiState.collectAsState()
    val appUiState = truckStartTripViewModel.appViewModel!!.appUiState.collectAsState()

    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true, block = {
        truckStartTripViewModel.getMyLocale(context)
    })

    AppScaffold(
        content = {
            TruckStartTripContent(
                scanQRClicked = {
                    truckStartTripViewModel.navigatewaterScanQr(
                        scanQRWaterViewModel = scanQRWaterViewModel,
                        truckIncomingWorkUiState = truckIncomingWorkUiState
                    )
                },
                truckStartTripUiState = truckStartTripUiState,
                appUiState = appUiState.value,
                nearestWaterPointUiState = nearestWaterPointUiState,
                truckIncomingWorkUiState = truckIncomingWorkUiState
            )
        },
        pageLoading = truckStartTripUiState.pageLoading,
        actionLoading = truckStartTripUiState.actionLoading,
        errorList = truckStartTripUiState.errorList,
        messageList = truckStartTripUiState.messageList,
        onBackButtonClicked = { truckStartTripViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { truckStartTripViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { truckStartTripViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { truckStartTripViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { truckStartTripViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = false
    )
}