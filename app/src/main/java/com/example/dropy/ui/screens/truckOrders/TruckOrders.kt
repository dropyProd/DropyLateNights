package com.example.dropy.ui.screens.truckOrders

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.truckOrderComplete.TruckOrderCompleteContent
import com.example.dropy.ui.screens.truckOrderComplete.TruckOrderCompleteViewModel
import com.example.dropy.ui.screens.truckRouteWaterPoint.TruckRouteWaterPointViewModel

@Composable
fun TruckOrders(
    cartPageViewModel: CartPageViewModel,
    truckOrdersViewModel: TruckOrdersViewModel
) {

    val truckOrdersUiState by truckOrdersViewModel.truckOrdersUiState.collectAsState()

    val appUiState = truckOrdersViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    LaunchedEffect(key1 = true, block = {
        if (!truckOrdersUiState.state) {
            truckOrdersViewModel.appViewModel!!.getIndividualOrders()
            truckOrdersViewModel.getOrders()
        }
    })



    AppScaffold(
        content = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                TruckOrdersContent(
                    navigate = truckOrdersViewModel::navigateWaterOrderSingle,
                    truckOrdersUiState = truckOrdersUiState
                )
            }
        },
        pageLoading = truckOrdersUiState.pageLoading,
        actionLoading = truckOrdersUiState.actionLoading,
        errorList = truckOrdersUiState.errorList,
        messageList = truckOrdersUiState.messageList,
        onBackButtonClicked = { truckOrdersViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { truckOrdersViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { truckOrdersViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { truckOrdersViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { truckOrdersViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = false
    )
}