package com.example.dropy.ui.screens.scanQRWater

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.authentication.AuthenticationViewModel
import com.example.dropy.ui.screens.nearestWaterPoint.NearestWaterPointViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.truckIncomingWork.TruckIncomingWorkViewModel
import com.example.dropy.ui.screens.truckRouteWaterPoint.TruckRouteWaterPointViewModel
import com.example.dropy.ui.screens.truckStartTrip.TruckStartTripViewModel
import com.example.dropy.ui.screens.waterTracking.WaterTrackingContent
import com.example.dropy.ui.screens.waterTracking.WaterTrackingViewModel

@Composable
fun ScanQRWater(
    cartPageViewModel: CartPageViewModel,
    scanQRWaterViewModel: ScanQRWaterViewModel,
    truckRouteWaterPointViewModel: TruckRouteWaterPointViewModel,
    authenticationViewModel: AuthenticationViewModel,
    truckStartTripViewModel: TruckStartTripViewModel,
    nearestWaterPointViewModel: NearestWaterPointViewModel,
    truckIncomingWorkViewModel: TruckIncomingWorkViewModel,
    scanQr: () -> Unit
) {

    val scanQRWaterUiState by scanQRWaterViewModel.scanQRWaterUiState.collectAsState()
    val nearestWaterPointUiState by nearestWaterPointViewModel.nearestWaterPointUiState.collectAsState()
    val truckIncomingWorkUiState by truckIncomingWorkViewModel.truckIncomingWorkUiState.collectAsState()

    val appUiState = scanQRWaterViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    LaunchedEffect(key1 = true, block = {
        scanQRWaterViewModel.setTruckStartTripViewModel(truckStartTripViewModel)
        scanQRWaterViewModel.setNearestWaterPointUiState(nearestWaterPointUiState)
        scanQRWaterViewModel.setTruckStartTripViewModel(truckStartTripViewModel)
    })

    AppScaffold(
        content = {
            ScanQRWaterContent(navigate = {
//                scanQRWaterViewModel.navigateOrderComplete()
               /* if (truckRouteWaterPointViewModel.truckRouteWaterPointUiState.value.isCustomerRoute){
                    scanQRWaterViewModel.navigateTrackOrderComplete()
                }
                else {
                    truckRouteWaterPointViewModel.setPageState(true)
                    scanQRWaterViewModel.navigateTrackRouteCustomer()
                }*/
                scanQRWaterViewModel.navigateOrderComplete(
                    truckStartTripViewModel = truckStartTripViewModel,
                    nearestWaterPointUiState = nearestWaterPointUiState,
                    truckIncomingWorkUiState = truckIncomingWorkUiState
                )
            }, scanQRWaterUiState = scanQRWaterUiState, useCode ={
                authenticationViewModel.setLoggedInState(true)
                scanQRWaterViewModel.navigateOtp()
            }, appUiState = appUiState.value, scanQr = scanQr  )
        },
        pageLoading = scanQRWaterUiState.pageLoading,
        actionLoading = scanQRWaterUiState.actionLoading,
        errorList = scanQRWaterUiState.errorList,
        messageList = scanQRWaterUiState.messageList,
        onBackButtonClicked = {
            truckRouteWaterPointViewModel.setPageState(false)

            scanQRWaterViewModel.appViewModel?.onBackButtonClicked()
        },
        onDashboardButtonClicked = { scanQRWaterViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { scanQRWaterViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { scanQRWaterViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { scanQRWaterViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = false
    )
}