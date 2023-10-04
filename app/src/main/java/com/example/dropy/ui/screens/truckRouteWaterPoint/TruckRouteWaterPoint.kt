package com.example.dropy.ui.screens.truckRouteWaterPoint

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
fun TruckRouteWaterPoint(
    cartPageViewModel: CartPageViewModel,
    truckRouteWaterPointViewModel: TruckRouteWaterPointViewModel,
    nearestWaterPointViewModel: NearestWaterPointViewModel,
    truckStartTripViewModel: TruckStartTripViewModel,
    truckIncomingWorkViewModel: TruckIncomingWorkViewModel,
    scanQRWaterViewModel: ScanQRWaterViewModel
) {

    val truckRouteWaterPointUiState by truckRouteWaterPointViewModel.truckRouteWaterPointUiState.collectAsState()
    val truckIncomingWorkUiState by truckIncomingWorkViewModel.truckIncomingWorkUiState.collectAsState()
    val nearestWaterPointUiState by nearestWaterPointViewModel.nearestWaterPointUiState.collectAsState()

    val appUiState = truckRouteWaterPointViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(key1 = true, block = {
        truckRouteWaterPointViewModel.getMyLocale(context)
    })

    AppScaffold(
        content = {
            TruckRouteWaterPointContent(
                scanQRClicked = {
                    scanQRWaterViewModel.changeState(false)
                    scanQRWaterViewModel.generateQr(nearestWaterPointUiState.collectionPointOrderRes?.id.toString(), "gt")
                    truckRouteWaterPointViewModel.navigateTruckStartTrip(
                        truckStartTripViewModel,
                        nearestWaterPointUiState,
                        truckIncomingWorkUiState = truckIncomingWorkUiState
                    )
                },
                appViewModel = truckRouteWaterPointViewModel.appViewModel!!,
                truckRouteWaterPointUiState = truckRouteWaterPointUiState,
                nearestWaterPointUiState = nearestWaterPointUiState,
                appUiState = appUiState.value
            )
        },
        pageLoading = truckRouteWaterPointUiState.pageLoading,
        actionLoading = truckRouteWaterPointUiState.actionLoading,
        errorList = truckRouteWaterPointUiState.errorList,
        messageList = truckRouteWaterPointUiState.messageList,
        onBackButtonClicked = { truckRouteWaterPointViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { truckRouteWaterPointViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { truckRouteWaterPointViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { truckRouteWaterPointViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { truckRouteWaterPointViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = false
    )
}