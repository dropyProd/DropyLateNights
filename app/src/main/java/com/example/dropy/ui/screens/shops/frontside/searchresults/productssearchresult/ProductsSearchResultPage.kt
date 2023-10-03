package com.example.dropy.ui.screens.shops.frontside.searchresults.productssearchresult

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.frontside.searchresults.productssearchresult.ProductsSearchResultPageContent

@Composable
fun ProductsSearchResultPage(
    productsSearchResultPageViewModel: ProductsSearchResultPageViewModel
){

    val appUiState = productsSearchResultPageViewModel.appViewModel!!.appUiState.collectAsState()
    val uiState by productsSearchResultPageViewModel.productsSearchResultPageUiState.collectAsState()

    AppScaffold(
        content = {
            ProductsSearchResultPageContent(
                uiState = uiState,
                onAddProductClicked = {productsSearchResultPageViewModel.onAddProductClicked()},
                onProductSelected = {productsSearchResultPageViewModel.onProductSelected()}
            )},
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = {productsSearchResultPageViewModel.appViewModel?.onBackButtonClicked()},
        onDashboardButtonClicked = {productsSearchResultPageViewModel.appViewModel?.onDashboardButtonClicked()},
        onCartButtonClicked = {productsSearchResultPageViewModel.appViewModel?.onCartButtonClicked()},
        navigateTo = {productsSearchResultPageViewModel.appViewModel?.navigate(it)},
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = {productsSearchResultPageViewModel.appViewModel?.onSelectProfile(it)},
        activeProfile = appUiState.value.activeProfile,
    )
}

@Preview(showBackground = true)
@Composable
fun ProductsSearchResultPagePreview(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ProductsSearchResultPage(productsSearchResultPageViewModel = ProductsSearchResultPageViewModel())
    }
}