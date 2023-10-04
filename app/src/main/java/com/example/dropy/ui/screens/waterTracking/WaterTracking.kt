package com.example.dropy.ui.screens.waterTracking

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.scanQRWater.ScanQRWaterViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.waterMyOrder.WaterMyOrderContent
import com.example.dropy.ui.screens.waterMyOrder.WaterMyOrderViewModel

@Composable
fun WaterTracking(
    cartPageViewModel: CartPageViewModel,
    waterTrackingViewModel: WaterTrackingViewModel,
    waterMyOrderViewModel: WaterMyOrderViewModel,
    scanQrWaterViewModel: ScanQRWaterViewModel
) {

    val waterTrackingUiState by waterTrackingViewModel.waterTrackingUiState.collectAsState()
    val waterMyOrderUiState by waterMyOrderViewModel.waterMyOrderUiState.collectAsState()

    val appUiState = waterTrackingViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    AppScaffold(
        content = {
            WaterTrackingContent(
                appViewModel = waterTrackingViewModel.appViewModel,
                navigate = { code, taskid ->
                    waterTrackingViewModel.navigateScanQrWater(scanQrWaterViewModel, code, taskid) },
                waterTrackingUiState = waterTrackingUiState,
                waterMyOrderUiState = waterMyOrderUiState,
                appUiState = appUiState.value
            )
        },
        pageLoading = waterTrackingUiState.pageLoading,
        actionLoading = waterTrackingUiState.actionLoading,
        errorList = waterTrackingUiState.errorList,
        messageList = waterTrackingUiState.messageList,
        onBackButtonClicked = { waterTrackingViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { waterTrackingViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { waterTrackingViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { waterTrackingViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { waterTrackingViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = false
    )
}