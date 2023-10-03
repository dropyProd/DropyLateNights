package com.example.dropy.ui.screens.addWaterDriver

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.water.waterHome.WaterHomeContent
import com.example.dropy.ui.screens.water.waterHome.WaterHomeViewModel

@Composable
fun AddWaterDriver(
    cartPageViewModel: CartPageViewModel,
    addWaterDriverViewModel: AddWaterDriverViewModel
) {

    val truckDriverUiState by addWaterDriverViewModel.truckDriverUiState.collectAsState()

    val appUiState = addWaterDriverViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()
    val context = LocalContext.current

    AppScaffold(
        content = {
            AddWaterDriverContent(
                uiState = truckDriverUiState,
                onLicensePlateChanged = addWaterDriverViewModel::setLicensePlate,
                onAddTruckDriver = {
                    addWaterDriverViewModel.addWaterDriver(context)
                }
            )
        },
        pageLoading = truckDriverUiState.pageLoading,
        actionLoading = truckDriverUiState.actionLoading,
        errorList = truckDriverUiState.errorList,
        messageList = truckDriverUiState.messageList,
        onBackButtonClicked = { addWaterDriverViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { addWaterDriverViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { addWaterDriverViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { addWaterDriverViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { addWaterDriverViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = true
    )
}