package com.example.dropy.ui.screens.shops.backside.addshop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.backside.addshop.ShopUploadsContent

@Composable
fun ShopUploads(
    addShopViewModel: AddShopViewModel
){
    val appUiState = addShopViewModel.appViewModel!!.appUiState.collectAsState()

    val uiState by addShopViewModel.shopUploadsUiState.collectAsState()

    AppScaffold(
        content = {
            ShopUploadsContent(
                uiState = uiState,
                onAddShopLogo = { },
                onAddShopCoverPhoto = {  },
                onAddShop = { /*addShopViewModel.onAddShop(co)*/ }
            )
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = {addShopViewModel.appViewModel?.onBackButtonClicked()},
        onDashboardButtonClicked = {addShopViewModel.appViewModel?.onDashboardButtonClicked()},
        onCartButtonClicked = {addShopViewModel.appViewModel?.onCartButtonClicked()},
        navigateTo = {addShopViewModel.appViewModel?.navigate(it)},
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = {addShopViewModel.appViewModel?.onSelectProfile(it)},
        activeProfile = appUiState.value.activeProfile
    )


}

@Preview(showBackground = true)
@Composable
fun ShopUploadsPreview(){

}