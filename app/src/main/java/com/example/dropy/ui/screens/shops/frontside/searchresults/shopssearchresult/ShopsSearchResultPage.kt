package com.example.dropy.ui.screens.shops.frontside.searchresults.shopssearchresult

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.frontside.searchresults.shopssearchresult.ShopsSearchResultPageContent

@Composable
fun ShopsSearchResultPage(
    shopsSearchResultPageViewModel: ShopsSearchResultPageViewModel
){

    val appUiState = shopsSearchResultPageViewModel.appViewModel!!.appUiState.collectAsState()
    val uiState by shopsSearchResultPageViewModel.shopsSearchResultPageUiState.collectAsState()

    AppScaffold(
        content = {
            ShopsSearchResultPageContent(
                uiState = uiState,
                onShopSelected = {shopsSearchResultPageViewModel.onShopSelected()}
            )},
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = {shopsSearchResultPageViewModel.appViewModel?.onBackButtonClicked()},
        onDashboardButtonClicked = {shopsSearchResultPageViewModel.appViewModel?.onDashboardButtonClicked()},
        onCartButtonClicked = {shopsSearchResultPageViewModel.appViewModel?.onCartButtonClicked()},
        navigateTo = {shopsSearchResultPageViewModel.appViewModel?.navigate(it)},
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = {shopsSearchResultPageViewModel.appViewModel?.onSelectProfile(it)},
        activeProfile = appUiState.value.activeProfile,
    )
}

@Preview(showBackground = true)
@Composable
fun ProductsSearchResultPagePreview(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ShopsSearchResultPage(shopsSearchResultPageViewModel = ShopsSearchResultPageViewModel())
    }
}