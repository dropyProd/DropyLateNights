package com.example.dropy.ui.screens.myWallet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeViewModel
import com.example.dropy.ui.screens.topUp.TopUpViewModel
import com.example.dropy.ui.screens.water.waterHome.WaterHomeContent
import com.example.dropy.ui.screens.water.waterHome.WaterHomeViewModel

@Composable
fun MyWallet(
    cartPageViewModel: CartPageViewModel,
    myWalletViewModel: MyWalletViewModel,
    topUpViewModel: TopUpViewModel
) {

    val myWalletUiState by myWalletViewModel.myWalletUiState.collectAsState()

    val appUiState = myWalletViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    val context = LocalContext.current



    AppScaffold(
        content = {
            MyWalletContent(navigate = {
                myWalletViewModel.navigateTopUp(topUpViewModel, it)
            })
        },
        pageLoading = myWalletUiState.pageLoading,
        actionLoading = myWalletUiState.actionLoading,
        errorList = myWalletUiState.errorList,
        messageList = myWalletUiState.messageList,
        onBackButtonClicked = { myWalletViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { myWalletViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { myWalletViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { myWalletViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { myWalletViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = false
    )
}