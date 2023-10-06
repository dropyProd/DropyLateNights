package com.example.dropy.ui.screens.addWaterVendor

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.addWaterTruck.AddWaterTruckDetailsContent
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel

@Composable
fun AddWaterVendorDetails(
    addWaterVendorViewModel: AddWaterVendorViewModel,
    cartPageViewModel: CartPageViewModel
) {

    //val addShopViewModel: AddShopViewModel = hiltViewModel()

    val appUiState = addWaterVendorViewModel.appViewModel!!.appUiState.collectAsState()
    val appHomePageViewModel: AppHomePageViewModel = hiltViewModel()
    val uiState by addWaterVendorViewModel.addWaterVendorUiState.collectAsState()

    val context = LocalContext.current
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()



    AppScaffold(
        content = {
            AddWaterVendorDetailsContent(

                uiState = uiState,
                onShopNameChanged = { addWaterVendorViewModel.onShopNameChanged(it) },
                //     onChangeShopLocation = { addShopViewModel.onChangeShopLocation()},
                onShopPhoneOneChanged = { addWaterVendorViewModel.onShopPhoneOneChanged(it) },
                     onShopPhoneTwoChanged = { addWaterVendorViewModel.onShopPhoneTwoChanged(it) },
                onCategorySelected = { name, index ->
                    Log.d("TASSG", "AddShop: $name")
                    addWaterVendorViewModel.onCategorySelected(name, index)
                },
                //onAddShop = { addShopViewModel.onGoToShopUploads() }
                onAddShopLocation = {
                    addWaterVendorViewModel.onGoToShopLocations(context)

                //        addShopViewModel.getShopCategories(appHomePageViewModel)

                },
                onBranchManagerChanged = addWaterVendorViewModel::onBranchManagerNameChanged,
                changeBranchManTextState = addWaterVendorViewModel::changeTextFState,
                addBranchName = addWaterVendorViewModel::addBranchName,
                changeSaturdayState =addWaterVendorViewModel::changeSaturdayState ,
                changeSundayState = addWaterVendorViewModel::changeSundayState,
                changeHolidayState = addWaterVendorViewModel::changeHolidayState,
                changeOperationHrState = addWaterVendorViewModel::changeOperationHrState,
                changeHolidayOpeningTime = addWaterVendorViewModel::changeHolidayOpeningTime,
                changeHolidayClosingTime = addWaterVendorViewModel::changeHolidayClosingTime,
                changeSaturdayOpeningTime = addWaterVendorViewModel::changeSaturdayOpeningTime,
                changeSaturdayClosingTime = addWaterVendorViewModel::changeSaturdayClosingTime,
                changeSundayOpeningTime = addWaterVendorViewModel::changeSundayOpeningTime,
                changeSundayClosingTime = addWaterVendorViewModel::changeSundayClosingTime,
                changeWeekdayOpeningTime = addWaterVendorViewModel::changeWeekdayOpeningTime,
                changeWeekdayClosingTime = addWaterVendorViewModel::changeWeekdayClosingTime,
                changeShopType = addWaterVendorViewModel::changeShopType,
                onDescriptionChanged = addWaterVendorViewModel::onDescriptionChanged
            )
        },
        frontSide = false,
        showLogo = false,
        showLogoBackside = false,
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { addWaterVendorViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { addWaterVendorViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { addWaterVendorViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { addWaterVendorViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { addWaterVendorViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showCart = false
    )
}


@Preview(showBackground = true)
@Composable
fun AddShopPreview() {

}