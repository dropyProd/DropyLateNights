package com.example.dropy.ui.screens.searchScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.apphome.AppHomePageContent
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.commons.appdrawer.ProfileTypes
import com.example.dropy.ui.screens.apphome.AppHomePageUiState
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    appViewModel: AppViewModel,
    appHomePageViewModel: AppHomePageViewModel,
    cartPageViewModel: CartPageViewModel,
    uiState: AppHomePageUiState,
    shopHomePageViewModel: ShopHomePageViewModel
) {

    val appUiState = appHomePageViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()


    AppScaffold(
        content = {
            SearchScreenContent(searchItem = {
                appHomePageViewModel.onSearchItem(
                    it,
                    shopHomePageViewModel,
                    appHomePageViewModel = appHomePageViewModel
                )
            })

        },
        showBackButton = false,
        pageLoading = uiState.pageLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { appHomePageViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { appHomePageViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { appHomePageViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = {
            appHomePageViewModel.appViewModel?.navigate(it)
        },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { appHomePageViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size
    )
}