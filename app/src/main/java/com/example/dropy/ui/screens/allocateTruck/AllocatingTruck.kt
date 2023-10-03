package com.example.dropy.ui.screens.allocateTruck

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeViewModel
import com.example.dropy.ui.screens.waterOrderDetails.WaterOrderDetailsContent
import com.example.dropy.ui.screens.waterOrderDetails.WaterOrderDetailsViewModel

@Composable
fun AllocatingTruck(
    cartPageViewModel: CartPageViewModel,
    allocatingTruckViewModel: AllocatingTruckViewModel,
    tankerBoreholeViewModel: TankerBoreholeViewModel
) {

    val allocatingTruckUiState by allocatingTruckViewModel.allocatingTruckUiState.collectAsState()
    val tankerBoreholeUiState by tankerBoreholeViewModel.tankerBoreholeUiState.collectAsState()

    val appUiState = allocatingTruckViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    AppScaffold(
        content = {
            AllocatingTruckContent(
                navigate = allocatingTruckViewModel::navigateWaterThankYou,
                tankerBoreholeUiState = tankerBoreholeUiState
            )
        },
        pageLoading = allocatingTruckUiState.pageLoading,
        actionLoading = allocatingTruckUiState.actionLoading,
        errorList = allocatingTruckUiState.errorList,
        messageList = allocatingTruckUiState.messageList,
        onBackButtonClicked = { allocatingTruckViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { allocatingTruckViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { allocatingTruckViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { allocatingTruckViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { allocatingTruckViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = true
    )
}