package com.example.dropy.ui.screens.water.waterHome

import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.frontside.ShopsLandingPageContent
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.shopslandingpage.ShopsLandingPageViewModel
import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeViewModel
import kotlinx.coroutines.launch

@Composable
fun WaterHome(
    cartPageViewModel: CartPageViewModel,
    waterHomeViewModel: WaterHomeViewModel,
    tankerBoreholeViewModel: TankerBoreholeViewModel
) {

    val waterUiState by waterHomeViewModel.waterHomeUiState.collectAsState()

    val appUiState = waterHomeViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(key1 = true, block = {
        tankerBoreholeViewModel.getMyLocale(context)
    })

    AppScaffold(
        content = {
            WaterHomeContent(
                navigateTankBoreHole = waterHomeViewModel::setSelectedType
            )
        },
        pageLoading = waterUiState.pageLoading,
        actionLoading = waterUiState.actionLoading,
        errorList = waterUiState.errorList,
        messageList = waterUiState.messageList,
        onBackButtonClicked = { waterHomeViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { waterHomeViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { waterHomeViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { waterHomeViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { waterHomeViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = false
    )
}