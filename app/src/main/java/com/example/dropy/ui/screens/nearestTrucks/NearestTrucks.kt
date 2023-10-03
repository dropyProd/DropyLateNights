package com.example.dropy.ui.screens.nearestTrucks

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
import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeViewModel
import com.example.dropy.ui.screens.truckIncomingWork.TruckIncomingWorkViewModel
import com.example.dropy.ui.screens.truckRouteWaterPoint.TruckRouteWaterPointViewModel
import com.example.dropy.ui.screens.waterOrderDetails.WaterOrderDetailsViewModel

@Composable
fun NearestTrucks(
    cartPageViewModel: CartPageViewModel,
    tankerBoreholeViewModel: TankerBoreholeViewModel,
    nearestTrucksViewmodel: NearestTrucksViewmodel,
    waterOrderDetailsViewModel: WaterOrderDetailsViewModel
) {

    val nearestTruckUiState by nearestTrucksViewmodel.nearestTrucksUiState.collectAsState()
    val tankerBoreholeUiState by tankerBoreholeViewModel.tankerBoreholeUiState.collectAsState()

    val context = LocalContext.current

    val appUiState = nearestTrucksViewmodel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()




    AppScaffold(
        content = {
            NearestTruckContent(
                navigate = { /*TODO*/ },
                truckClicked = {
                    nearestTrucksViewmodel.setSelectedTruck(
                        it,
                        waterOrderDetailsViewModel = waterOrderDetailsViewModel,
                        tankerBoreholeUiState = tankerBoreholeUiState
                    )
                },
                tankerBoreholeUiState = tankerBoreholeUiState,
                nearestTrucksUiState = nearestTruckUiState
            )
        },
        pageLoading = nearestTruckUiState.pageLoading,
        actionLoading = nearestTruckUiState.actionLoading,
        errorList = nearestTruckUiState.errorList,
        messageList = nearestTruckUiState.messageList,
        onBackButtonClicked = { nearestTrucksViewmodel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { nearestTrucksViewmodel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { nearestTrucksViewmodel.appViewModel?.onCartButtonClicked() },
        navigateTo = { nearestTrucksViewmodel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { nearestTrucksViewmodel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = false
    )
}