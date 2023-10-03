package com.example.dropy.ui.screens.shops.backside.addshop.addshopimages

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
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel

import com.example.dropy.ui.screens.shops.backside.addshop.AddShopViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel

@Composable
fun AddShopImages(
    addShopViewModel: AddShopViewModel,
    choosePhoto: (String) -> Unit,
    cartPageViewModel: CartPageViewModel
) {
    val appUiState = addShopViewModel.appViewModel!!.appUiState.collectAsState()

    val uiState by addShopViewModel.addShopImagesUiState.collectAsState()
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
            AddShopImagesContent(
                uiState = uiState,
                onAddShopLogo = { choosePhoto("logo") },
                onAddShopCoverPhoto = { choosePhoto("cover") },
                onAddShop = {
                    addShopViewModel.onAddShop(context)
                },
                addShopViewModel = addShopViewModel
            )
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { addShopViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { addShopViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { addShopViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { addShopViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { addShopViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size
    )
}

@Preview(showBackground = true)
@Composable
fun ShopUploadsPreview() {

}