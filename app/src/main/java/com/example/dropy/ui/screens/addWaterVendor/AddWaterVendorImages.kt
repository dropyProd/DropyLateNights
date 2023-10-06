package com.example.dropy.ui.screens.addWaterVendor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.backside.addshop.AddShopImagesContent
import com.example.dropy.ui.screens.addWaterPoint.AddWaterpointImagesContent
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel

import com.example.dropy.ui.screens.shops.backside.addshop.AddShopViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.waterVendorDash.WaterVendorDashViewModel

@Composable
fun AddWaterVendorImages(
    addWaterVendorViewModel: AddWaterVendorViewModel,
    choosePhoto: (String) -> Unit,
    cartPageViewModel: CartPageViewModel,
    waterVendorDashViewModel: WaterVendorDashViewModel
) {
    val appUiState = addWaterVendorViewModel.appViewModel!!.appUiState.collectAsState()

    val uiState by addWaterVendorViewModel.addWaterVendorImagesUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    val context = LocalContext.current
    val appViewModel: AppViewModel = hiltViewModel()
    val appHomePageViewModel: AppHomePageViewModel = hiltViewModel()
    if (appHomePageViewModel.appViewModel == null) {
        appHomePageViewModel.appViewModel = appViewModel
    }
    val scope = rememberCoroutineScope()

    AppScaffold(
        content = {
            AddWatervendorImagesContent(
                uiState = uiState,
                onAddShopLogo = { choosePhoto("logo") },
                onAddShopCoverPhoto = { choosePhoto("cover") },
                onAddShop = {
//                    addWaterpointViewmodel.onAddShop(context)
                    addWaterVendorViewModel.createWaterVendor(
                        context,
                        waterVendorDashViewModel = waterVendorDashViewModel
                    )
                },
                addWaterVendorViewModel = addWaterVendorViewModel
            )
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { addWaterVendorViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { addWaterVendorViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { addWaterVendorViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { addWaterVendorViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { addWaterVendorViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size, showLogo = false,
        showCart = false
    )
}

@Preview(showBackground = true)
@Composable
fun ShopUploadsPreview() {

}