package com.example.dropy.ui.screens.shops.frontside.shopsbycategory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.frontside.ShopsByCategoryContent

@Composable
fun ShopsByCategory(
    shopsByCategoryViewModel: ShopsByCategoryViewModel
){

    val appUiState = shopsByCategoryViewModel.appViewModel!!.appUiState.collectAsState()
    val uiState by shopsByCategoryViewModel.shopsByCategoryPageUiState.collectAsState()
    AppScaffold(
        content = {
            ShopsByCategoryContent(
                uiState = uiState,
                onShopSelected = {id, shop->
                    shopsByCategoryViewModel.onShopSelected(id)},
                onShopReview = {shopsByCategoryViewModel.onShopReview()},
                likeClicked = {_,_ ->}
            )},

        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = {shopsByCategoryViewModel.appViewModel?.onBackButtonClicked()},
        onDashboardButtonClicked = {shopsByCategoryViewModel.appViewModel?.onDashboardButtonClicked()},
        onCartButtonClicked = {shopsByCategoryViewModel.appViewModel?.onCartButtonClicked()},
        navigateTo = {shopsByCategoryViewModel.appViewModel?.navigate(it)},
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = {shopsByCategoryViewModel.appViewModel?.onSelectProfile(it)},
        activeProfile = appUiState.value.activeProfile,
    )
}

@Preview(showBackground = true)
@Composable
fun ShopsByCategoryPreview(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ShopsByCategory(ShopsByCategoryViewModel())
    }
}