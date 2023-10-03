package com.example.dropy.ui.screens.shops.backside.addshop

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.backside.addshop.AddShopDetailsContent
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel

@Composable
fun AddShop(
    addShopViewModel: AddShopViewModel,
    cartPageViewModel: CartPageViewModel
) {

    //val addShopViewModel: AddShopViewModel = hiltViewModel()

    val appUiState = addShopViewModel.appViewModel!!.appUiState.collectAsState()
    val appHomePageViewModel: AppHomePageViewModel = hiltViewModel()
    val uiState by addShopViewModel.addShopUiState.collectAsState()

    val context = LocalContext.current
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    LaunchedEffect(key1 = true, block ={
        addShopViewModel.getShopCategories(appHomePageViewModel)
    } )

    AppScaffold(
        content = {
            AddShopDetailsContent(

                uiState = uiState,
                onShopNameChanged = { addShopViewModel.onShopNameChanged(it) },
                //     onChangeShopLocation = { addShopViewModel.onChangeShopLocation()},
                onShopPhoneOneChanged = { addShopViewModel.onShopPhoneOneChanged(it) },
                     onShopPhoneTwoChanged = { addShopViewModel.onShopPhoneTwoChanged(it) },
                onCategorySelected = { name, index ->
                    Log.d("TASSG", "AddShop: $name")
                    addShopViewModel.onCategorySelected(name, index)
                },
                //onAddShop = { addShopViewModel.onGoToShopUploads() }
                onAddShopLocation = {
                    addShopViewModel.onGoToShopLocations(context)

                //        addShopViewModel.getShopCategories(appHomePageViewModel)

                },
                onBranchManagerChanged = addShopViewModel::onBranchManagerNameChanged,
                changeBranchManTextState = addShopViewModel::changeTextFState,
                addBranchName = addShopViewModel::addBranchName,
                changeSaturdayState =addShopViewModel::changeSaturdayState ,
                changeSundayState = addShopViewModel::changeSundayState,
                changeHolidayState = addShopViewModel::changeHolidayState,
                changeOperationHrState = addShopViewModel::changeOperationHrState,
                changeHolidayOpeningTime = addShopViewModel::changeHolidayOpeningTime,
                changeHolidayClosingTime = addShopViewModel::changeHolidayClosingTime,
                changeSaturdayOpeningTime = addShopViewModel::changeSaturdayOpeningTime,
                changeSaturdayClosingTime = addShopViewModel::changeSaturdayClosingTime,
                changeSundayOpeningTime = addShopViewModel::changeSundayOpeningTime,
                changeSundayClosingTime = addShopViewModel::changeSundayClosingTime,
                changeWeekdayOpeningTime = addShopViewModel::changeWeekdayOpeningTime,
                changeWeekdayClosingTime = addShopViewModel::changeWeekdayClosingTime,
                changeShopType = addShopViewModel::changeShopType
            )
        },
        frontSide = false,
        showLogo = false,
        showLogoBackside = false,
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { addShopViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { addShopViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { addShopViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { addShopViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { addShopViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size
    )
}


@Preview(showBackground = true)
@Composable
fun AddShopPreview() {

}