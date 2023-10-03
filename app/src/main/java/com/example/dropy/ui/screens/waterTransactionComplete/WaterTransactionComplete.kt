package com.example.dropy.ui.screens.waterTransactionComplete

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.allocateTruck.AllocatingTruckContent
import com.example.dropy.ui.screens.allocateTruck.AllocatingTruckViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeViewModel
import com.example.dropy.ui.screens.waterMyOrder.WaterMyOrderViewModel

@Composable
fun WaterTransactionComplete(
    cartPageViewModel: CartPageViewModel,
    waterTransactionCompleteViewModel: WaterTransactionCompleteViewModel,
    tankerBoreholeViewModel: TankerBoreholeViewModel,
    waterMyOrderViewModel: WaterMyOrderViewModel
) {

    val waterTransactionCompleteUiState by waterTransactionCompleteViewModel.waterTransactionCompleteUiState.collectAsState()
    val tankerBoreholeUiState by tankerBoreholeViewModel.tankerBoreholeUiState.collectAsState()

    val appUiState = waterTransactionCompleteViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    AppScaffold(
        content = {
          WaterTransactionCompleteContent(navigateTrack = {
              tankerBoreholeUiState.createIndividualWaterOrderRes?.let {
                  waterMyOrderViewModel.setOrder(
                      it
                  )
              }
              waterTransactionCompleteViewModel.navigateTrackOrder() })
        },
        pageLoading = waterTransactionCompleteUiState.pageLoading,
        actionLoading = waterTransactionCompleteUiState.actionLoading,
        errorList = waterTransactionCompleteUiState.errorList,
        messageList = waterTransactionCompleteUiState.messageList,
        onBackButtonClicked = { waterTransactionCompleteViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { waterTransactionCompleteViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { waterTransactionCompleteViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { waterTransactionCompleteViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { waterTransactionCompleteViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = true
    )
}