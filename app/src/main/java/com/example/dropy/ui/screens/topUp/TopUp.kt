package com.example.dropy.ui.screens.topUp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeViewModel
import com.example.dropy.ui.screens.water.waterHome.WaterHomeContent
import com.example.dropy.ui.screens.water.waterHome.WaterHomeViewModel

@Composable
fun TopUp(
    cartPageViewModel: CartPageViewModel,
    topUpViewModel: TopUpViewModel
) {

    val topUpUiState by topUpViewModel.topUpUiState.collectAsState()

    val appUiState = topUpViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    val context = LocalContext.current



    AppScaffold(
        content = {
            TopUpContent(
                topUpUiState = topUpUiState,
                onAmountChange = topUpViewModel::onAmountChange,
                topUp = {
                    if (topUpUiState.state.equals("TOP UP"))
                        topUpViewModel.topUpWallet()
                    else
                        topUpViewModel.withdrawWallet()
                })
        },
        pageLoading = topUpUiState.pageLoading,
        actionLoading = topUpUiState.actionLoading,
        errorList = topUpUiState.errorList,
        messageList = topUpUiState.messageList,
        onBackButtonClicked = { topUpViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { topUpViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { topUpViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { topUpViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { topUpViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = false
    )
}