package com.example.dropy.ui.screens.nearestWaterPoint

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.allocateTruck.AllocatingTruckContent
import com.example.dropy.ui.screens.allocateTruck.AllocatingTruckViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.truckIncomingWork.TruckIncomingWorkViewModel
import com.example.dropy.ui.screens.truckRouteCustomer.TruckRouteCustomerViewModel
import com.example.dropy.ui.screens.truckRouteWaterPoint.TruckRouteWaterPointViewModel

@Composable
fun NearestWaterPoint(
    cartPageViewModel: CartPageViewModel,
    nearestWaterPointViewModel: NearestWaterPointViewModel,
    truckRouteWaterPointViewModel: TruckRouteWaterPointViewModel,
    truckIncomingWorkViewModel: TruckIncomingWorkViewModel
) {

    val nearestWaterPointUiState by nearestWaterPointViewModel.nearestWaterPointUiState.collectAsState()
    val truckIncomingWorkUiState by truckIncomingWorkViewModel.truckIncomingWorkUiState.collectAsState()
    val context = LocalContext.current

    val appUiState = nearestWaterPointViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()


    LaunchedEffect(key1 = true, block = {
        nearestWaterPointViewModel.getMyLocale(context)
    })

    LaunchedEffect(key1 = true, block = {
        if (nearestWaterPointUiState.selectedWaterPoint?.equals(null) == true)
            nearestWaterPointViewModel.setSelectedWaterPoint(nearestWaterPointUiState.allWaterPoints[0])
    })

    AppScaffold(
        content = {
            NearestWaterPointContent(
                navigate = {
                    truckRouteWaterPointViewModel.setPageState(false)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        nearestWaterPointViewModel.createWaterpointOrder(
                            truckIncomingWorkUiState = truckIncomingWorkUiState,
                            context = context,
                            truckRouteWaterPointViewModel = truckRouteWaterPointViewModel
                        )
                    }
                    /* nearestWaterPointViewModel.navigateTruckRouteWaterPoint(
                         truckRouteWaterPointViewModel = truckRouteWaterPointViewModel,
                         truckIncomingWorkUiState = truckIncomingWorkUiState
                     )*/
                },
                truckIncomingWorkUiState = truckIncomingWorkUiState,
                nearestWaterPointUiState = nearestWaterPointUiState,
                waterpointClicked = nearestWaterPointViewModel::setSelectedWaterPoint
            )
        },
        pageLoading = nearestWaterPointUiState.pageLoading,
        actionLoading = nearestWaterPointUiState.actionLoading,
        errorList = nearestWaterPointUiState.errorList,
        messageList = nearestWaterPointUiState.messageList,
        onBackButtonClicked = { nearestWaterPointViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { nearestWaterPointViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { nearestWaterPointViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { nearestWaterPointViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { nearestWaterPointViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = false
    )
}