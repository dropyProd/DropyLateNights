package com.example.dropy.ui.screens.addWaterPoint

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
import com.example.dropy.ui.screens.watepointDash.WaterPointDashViewModel

@Composable
fun AddWaterpointImages(
    addWaterpointViewmodel: AddWaterpointViewmodel,
    choosePhoto: (String) -> Unit,
    cartPageViewModel: CartPageViewModel,
    waterPointDashViewModel: WaterPointDashViewModel
) {
    val appUiState = addWaterpointViewmodel.appViewModel!!.appUiState.collectAsState()

    val uiState by addWaterpointViewmodel.addWaterpointImagesUiState.collectAsState()
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
            AddWaterpointImagesContent(
                uiState = uiState,
                onAddShopLogo = { choosePhoto("logo") },
                onAddShopCoverPhoto = { choosePhoto("cover") },
                onAddShop = {
                    addWaterpointViewmodel.addWaterPoint(
                        context,
                        waterPointDashViewModel = waterPointDashViewModel
                    )
//                    addWaterpointViewmodel.onAddShop(context)
                },
                addWaterpointViewmodel = addWaterpointViewmodel
            )
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { addWaterpointViewmodel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { addWaterpointViewmodel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { addWaterpointViewmodel.appViewModel?.onCartButtonClicked() },
        navigateTo = { addWaterpointViewmodel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { addWaterpointViewmodel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size, showLogo = false
    )
}

@Preview(showBackground = true)
@Composable
fun ShopUploadsPreview() {

}