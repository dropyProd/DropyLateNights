package com.example.dropy.ui.screens.shops.frontside.allshopcategories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.frontside.AllShopCategoriesContent

@Composable
fun AllShopCategories(allShopCategoriesViewModel: AllShopCategoriesViewModel){

    val appUiState = allShopCategoriesViewModel.appViewModel!!.appUiState.collectAsState()
    val uiState by allShopCategoriesViewModel.allShopCategoryPageUiState.collectAsState()

    AppScaffold(
        content = {
            AllShopCategoriesContent(
                uiState = uiState,
                onCategorySelected = {allShopCategoriesViewModel.onCategorySelected()}
            )
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = {allShopCategoriesViewModel.appViewModel?.onBackButtonClicked()},
        onDashboardButtonClicked = {allShopCategoriesViewModel.appViewModel?.onDashboardButtonClicked()},
        onCartButtonClicked = {allShopCategoriesViewModel.appViewModel?.onCartButtonClicked()},
        navigateTo = {allShopCategoriesViewModel.appViewModel?.navigate(it)},
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = {allShopCategoriesViewModel.appViewModel?.onSelectProfile(it)},
        activeProfile = appUiState.value.activeProfile,
    )
}

@Preview(showBackground = true)
@Composable
fun AllShopCategoriesPreview(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AllShopCategories(allShopCategoriesViewModel = AllShopCategoriesViewModel())
    }
}