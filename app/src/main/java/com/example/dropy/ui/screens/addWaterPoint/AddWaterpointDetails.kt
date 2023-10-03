package com.example.dropy.ui.screens.addWaterPoint

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
fun AddWaterpointDetails(
    addWaterpointViewmodel: AddWaterpointViewmodel,
    cartPageViewModel: CartPageViewModel
) {

    //val addShopViewModel: AddShopViewModel = hiltViewModel()

    val appUiState = addWaterpointViewmodel.appViewModel!!.appUiState.collectAsState()
    val appHomePageViewModel: AppHomePageViewModel = hiltViewModel()
    val uiState by addWaterpointViewmodel.addWaterpointUiState.collectAsState()

    val context = LocalContext.current
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()



    AppScaffold(
        content = {
            AddWaterPointDetailsContent(

                uiState = uiState,
                onShopNameChanged = { addWaterpointViewmodel.onShopNameChanged(it) },
                //     onChangeShopLocation = { addShopViewModel.onChangeShopLocation()},
                onShopPhoneOneChanged = { addWaterpointViewmodel.onShopPhoneOneChanged(it) },
                     onShopPhoneTwoChanged = { addWaterpointViewmodel.onShopPhoneTwoChanged(it) },
                onCategorySelected = { name, index ->
                    Log.d("TASSG", "AddShop: $name")
                    addWaterpointViewmodel.onCategorySelected(name, index)
                },
                //onAddShop = { addShopViewModel.onGoToShopUploads() }
                onAddShopLocation = {
                    addWaterpointViewmodel.onGoToShopLocations(context)

                //        addShopViewModel.getShopCategories(appHomePageViewModel)

                },
                onBranchManagerChanged = addWaterpointViewmodel::onBranchManagerNameChanged,
                changeBranchManTextState = addWaterpointViewmodel::changeTextFState,
                addBranchName = addWaterpointViewmodel::addBranchName,
                changeSaturdayState =addWaterpointViewmodel::changeSaturdayState ,
                changeSundayState = addWaterpointViewmodel::changeSundayState,
                changeHolidayState = addWaterpointViewmodel::changeHolidayState,
                changeOperationHrState = addWaterpointViewmodel::changeOperationHrState,
                changeHolidayOpeningTime = addWaterpointViewmodel::changeHolidayOpeningTime,
                changeHolidayClosingTime = addWaterpointViewmodel::changeHolidayClosingTime,
                changeSaturdayOpeningTime = addWaterpointViewmodel::changeSaturdayOpeningTime,
                changeSaturdayClosingTime = addWaterpointViewmodel::changeSaturdayClosingTime,
                changeSundayOpeningTime = addWaterpointViewmodel::changeSundayOpeningTime,
                changeSundayClosingTime = addWaterpointViewmodel::changeSundayClosingTime,
                changeWeekdayOpeningTime = addWaterpointViewmodel::changeWeekdayOpeningTime,
                changeWeekdayClosingTime = addWaterpointViewmodel::changeWeekdayClosingTime,
                changeShopType = addWaterpointViewmodel::changeShopType,
                onPricePerLitreChanged = addWaterpointViewmodel::onPricePerLitreChanged,
                onBankAccountChanged = addWaterpointViewmodel::onBankAccountChanged,
                onMpesaPaybillChanged = addWaterpointViewmodel::onMpesaPaybillChanged,
                onEmailChanged = addWaterpointViewmodel::onEmailChanged
            )
        },
        frontSide = false,
        showLogo = false,
        showLogoBackside = false,
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { addWaterpointViewmodel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { addWaterpointViewmodel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { addWaterpointViewmodel.appViewModel?.onCartButtonClicked() },
        navigateTo = { addWaterpointViewmodel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { addWaterpointViewmodel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size
    )
}


@Preview(showBackground = true)
@Composable
fun AddShopPreview() {

}