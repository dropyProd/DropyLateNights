package com.example.dropy.ui.screens.addWaterTruck

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel

@Composable
fun AddWatertruckDetails(
    addWaterTruckViewmodel: AddWaterTruckViewmodel,
    cartPageViewModel: CartPageViewModel
) {

    //val addShopViewModel: AddShopViewModel = hiltViewModel()

    val appUiState = addWaterTruckViewmodel.appViewModel!!.appUiState.collectAsState()
    val appHomePageViewModel: AppHomePageViewModel = hiltViewModel()
    val uiState by addWaterTruckViewmodel.addWaterTruckUiState.collectAsState()

    val context = LocalContext.current
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()



    AppScaffold(
        content = {
            AddWaterTruckDetailsContent(

                uiState = uiState,
                onShopNameChanged = { addWaterTruckViewmodel.onShopNameChanged(it) },
                //     onChangeShopLocation = { addShopViewModel.onChangeShopLocation()},
                onShopPhoneOneChanged = { addWaterTruckViewmodel.onShopPhoneOneChanged(it) },
                onShopPhoneTwoChanged = { addWaterTruckViewmodel.onShopPhoneTwoChanged(it) },
                //onAddShop = { addShopViewModel.onGoToShopUploads() }
                onAddShopLocation = {
                    addWaterTruckViewmodel.onGoToShopLocations(context)

                    //        addShopViewModel.getShopCategories(appHomePageViewModel)

                },
                selectedTruckCapacity = addWaterTruckViewmodel::setTruckCapacity,
                onModelChanged = addWaterTruckViewmodel::onModelChanged,
                onYearChanged = addWaterTruckViewmodel::onYearChanged
            )
        },
        frontSide = false,
        showLogo = false,
        showLogoBackside = false,
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { addWaterTruckViewmodel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { addWaterTruckViewmodel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { addWaterTruckViewmodel.appViewModel?.onCartButtonClicked() },
        navigateTo = { addWaterTruckViewmodel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { addWaterTruckViewmodel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showCart = false,
        showImageRight = false,
        showimg = false
    )
}


@Preview(showBackground = true)
@Composable
fun AddShopPreview() {

}