package com.example.dropy.ui.screens.waterpointNewOrder

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.waterpointOrders.WaterpointOrdersContent

@Composable
fun WaterpointNewOrder(
    waterpointNewOrderViewModel: WaterpointNewOrderViewModel,
    cartPageViewModel: CartPageViewModel
) {

    val waterPointNewOrderUiState by waterpointNewOrderViewModel.waterPointNewOrderUiState.collectAsState()

    val appUiState = waterpointNewOrderViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()
    val context = LocalContext.current


    AppScaffold(
        content = {
           WaterpointNewOrderContent(uiState = waterPointNewOrderUiState, onLicensePlateChanged = waterpointNewOrderViewModel::onLicensePlateChange, selectedCapacity = waterpointNewOrderViewModel::setCapacity, createOrder = {
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                   waterpointNewOrderViewModel.createOrder(context = context)
               }
           } )
        },
        pageLoading = waterPointNewOrderUiState.pageLoading,
        actionLoading = waterPointNewOrderUiState.actionLoading,
        errorList = waterPointNewOrderUiState.errorList,
        messageList = waterPointNewOrderUiState.messageList,
        onBackButtonClicked = { waterpointNewOrderViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { waterpointNewOrderViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { waterpointNewOrderViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { waterpointNewOrderViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { waterpointNewOrderViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = true
    )
}