package com.example.dropy.ui.screens.shops.backside.productdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.backside.dashboard.product.productdetails.ProductDetailsContent

@Composable
fun ProductDetails(
    productDetailsViewModel: ProductDetailsViewModel
){
    val appUiState = productDetailsViewModel.appViewModel!!.appUiState.collectAsState()

    val uiState by productDetailsViewModel.productDetailsUiState.collectAsState()

    AppScaffold(
        content = {
                  ProductDetailsContent(
                      uiState = uiState,
                      onEditCoverPhoto = { productDetailsViewModel.onEditCoverPhoto() },
                      onDeleteImage = { productDetailsViewModel.onDeleteImage(it) },
                      onAddImage = { productDetailsViewModel.onAddImage() },
                      onProductNameChanged = { productDetailsViewModel.onProductNameChanged(it) },
                      onProductDescriptionChanged = { productDetailsViewModel.onProductDescriptionChanged(it) },
                      onProductPriceChanged = { productDetailsViewModel.onProductPriceChanged(it) },
                      onProductUnitsChanged = { productDetailsViewModel.onProductUnitsChanged(it) },
                      onAddToStack = { productDetailsViewModel.onAddToStack() },
                      onSubtractFromStack = { productDetailsViewModel.onSubtractFromStack() },
                      onChangeAvailability = { productDetailsViewModel.onChangeAvailability(it) },
                      onSaveClicked = { productDetailsViewModel.onSaveClicked() },
                      onDeleteClicked = { productDetailsViewModel.onDeleteClicked() }
                  )
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = {productDetailsViewModel.appViewModel?.onBackButtonClicked()},
        onDashboardButtonClicked = {productDetailsViewModel.appViewModel?.onDashboardButtonClicked()},
        onCartButtonClicked = {productDetailsViewModel.appViewModel?.onCartButtonClicked()},
        navigateTo = {productDetailsViewModel.appViewModel?.navigate(it)},
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = {productDetailsViewModel.appViewModel?.onSelectProfile(it)},
        activeProfile = appUiState.value.activeProfile
    )

}

@Preview(showBackground = true)
@Composable
fun ProductDetailsPreview(){
    Column(Modifier.fillMaxSize()) {
        ProductDetails(hiltViewModel())
    }
}