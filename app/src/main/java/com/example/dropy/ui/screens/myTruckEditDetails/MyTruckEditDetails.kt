package com.example.dropy.ui.screens.myTruckEditDetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.addWaterTruck.AddWaterTruckViewmodel
import com.example.dropy.ui.screens.myTrucks.MyTrucksViewmodel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeViewModel
import com.example.dropy.ui.screens.water.waterHome.WaterHomeContent
import com.example.dropy.ui.screens.water.waterHome.WaterHomeViewModel

@Composable
fun MyTruckEditDetails(cartPageViewModel: CartPageViewModel,
                      myTruckEditDetailsViewModel: MyTruckEditDetailsViewModel,
                       myTrucksViewmodel: MyTrucksViewmodel,
                       choosePhoto: (String) -> Unit,
                       addWaterTruckViewmodel: AddWaterTruckViewmodel

) {

    val myTruckEditDetailsUiState by myTruckEditDetailsViewModel.myTruckEditDetailsUiState.collectAsState()
    val myTrucksUiState by myTrucksViewmodel.myTrucksUiState.collectAsState()

    val appUiState = myTruckEditDetailsViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(key1 = true, block = {myTruckEditDetailsViewModel.getTruckDrivers()})

    AppScaffold(
        content = {
            MyTruckEditDetailsContent(
                myTrucksUiState = myTrucksUiState,
                myTruckEditDetailsUiState = myTruckEditDetailsUiState,
                changeActiveState = myTruckEditDetailsViewModel::changeActiveState,
                selectedTruckCapacity = myTruckEditDetailsViewModel::setTruckCapacity,
                onAddShopCoverPhoto = {choosePhoto("cover")},
                onModelChanged = myTruckEditDetailsViewModel::onModelChange,
                onYearChanged = myTruckEditDetailsViewModel::onYearChange,
                onLicensePlateChanged = myTruckEditDetailsViewModel::onLicensePlateChange,
                changeTruck = myTruckEditDetailsViewModel::setSelectedTruckId,
                nextClicked = {myTruckEditDetailsViewModel.navigateLocation(addWaterTruckViewmodel = addWaterTruckViewmodel)}
            )
        },
        pageLoading = myTruckEditDetailsUiState.pageLoading,
        actionLoading = myTruckEditDetailsUiState.actionLoading,
        errorList = myTruckEditDetailsUiState.errorList,
        messageList = myTruckEditDetailsUiState.messageList,
        onBackButtonClicked = { myTruckEditDetailsViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { myTruckEditDetailsViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { myTruckEditDetailsViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { myTruckEditDetailsViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { myTruckEditDetailsViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = false
    )
}