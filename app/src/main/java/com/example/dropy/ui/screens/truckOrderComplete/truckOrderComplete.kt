package com.example.dropy.ui.screens.truckOrderComplete

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.truckRouteWaterPoint.TruckRouteWaterPointContent
import com.example.dropy.ui.screens.truckRouteWaterPoint.TruckRouteWaterPointViewModel

@Composable
fun TruckOrderComplete(
    cartPageViewModel: CartPageViewModel,
    truckOrderCompleteViewModel: TruckOrderCompleteViewModel,
    truckRouteWaterPointViewModel: TruckRouteWaterPointViewModel
) {

    val truckOrderCompleteUiState by truckOrderCompleteViewModel.truckOrderCompleteUiState.collectAsState()

    val appUiState = truckOrderCompleteViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    AppScaffold(
        content = {
            TruckOrderCompleteContent(
                findMoreWorkClicked = {
                    truckRouteWaterPointViewModel.setPageState(false)
                    truckOrderCompleteViewModel.navigateFindMoreWork()
                }
            )
        },
        pageLoading = truckOrderCompleteUiState.pageLoading,
        actionLoading = truckOrderCompleteUiState.actionLoading,
        errorList = truckOrderCompleteUiState.errorList,
        messageList = truckOrderCompleteUiState.messageList,
        onBackButtonClicked = { truckOrderCompleteViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { truckOrderCompleteViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { truckOrderCompleteViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { truckOrderCompleteViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { truckOrderCompleteViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = false
    )
}