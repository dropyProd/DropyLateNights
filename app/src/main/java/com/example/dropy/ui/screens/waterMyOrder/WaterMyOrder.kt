package com.example.dropy.ui.screens.waterMyOrder

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.waterOrderDetails.WaterOrderDetailsContent
import com.example.dropy.ui.screens.waterOrderDetails.WaterOrderDetailsViewModel
import com.example.dropy.ui.screens.waterTracking.WaterTrackingViewModel

@Composable
fun WaterMyOrder(
    cartPageViewModel: CartPageViewModel,
    waterMyOrderViewModel: WaterMyOrderViewModel,
    waterTrackingViewModel: WaterTrackingViewModel
) {

    val waterOrderDetailsUiState by waterMyOrderViewModel.waterMyOrderUiState.collectAsState()

    val appUiState = waterMyOrderViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    LaunchedEffect(key1 = true, block = {
        waterMyOrderViewModel.getTruckDrivers()
    })

    AppScaffold(
        content = {
            WaterMyOrderContent(
                navigate = {
                    waterMyOrderViewModel.navigateWaterTracking(
                        waterTrackingViewModel = waterTrackingViewModel,
                        assignedTruck = it
                    )
                }, navigateO = {
                    if (it != null) {
                        waterMyOrderViewModel.navigateWaterTracking(
                            waterTrackingViewModel = waterTrackingViewModel,
                            it
                        )
                    }
                },
                waterMyOrderUiState = waterOrderDetailsUiState
            )
        },
        pageLoading = waterOrderDetailsUiState.pageLoading,
        actionLoading = waterOrderDetailsUiState.actionLoading,
        errorList = waterOrderDetailsUiState.errorList,
        messageList = waterOrderDetailsUiState.messageList,
        onBackButtonClicked = { waterMyOrderViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { waterMyOrderViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { waterMyOrderViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { waterMyOrderViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { waterMyOrderViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = false
    )
}