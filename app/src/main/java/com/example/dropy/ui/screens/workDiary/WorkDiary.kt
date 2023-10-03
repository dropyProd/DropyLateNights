package com.example.dropy.ui.screens.workDiary

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.truckOrders.TruckOrdersContent
import com.example.dropy.ui.screens.truckOrders.TruckOrdersViewModel

@Composable
fun WorkDiary(
    cartPageViewModel: CartPageViewModel,
    workDiaryViewModel: WorkDiaryViewModel
) {
    val workDiaryUiState by workDiaryViewModel.workDiaryUiState.collectAsState()

    val appUiState = workDiaryViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    LaunchedEffect(key1 = true, block = {
//        if (!truckOrdersUiState.state) {
            workDiaryViewModel.appViewModel!!.getIndividualOrders()
            workDiaryViewModel.getOrders()
//        }
    })



    AppScaffold(
        content = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                WorkDiaryContent(workDiaryUiState = workDiaryUiState, navigate = {},  appUiState = appUiState.value)
            }
        },
        pageLoading = workDiaryUiState.pageLoading,
        actionLoading = workDiaryUiState.actionLoading,
        errorList = workDiaryUiState.errorList,
        messageList = workDiaryUiState.messageList,
        onBackButtonClicked = { workDiaryViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { workDiaryViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { workDiaryViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { workDiaryViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { workDiaryViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = false
    )
}