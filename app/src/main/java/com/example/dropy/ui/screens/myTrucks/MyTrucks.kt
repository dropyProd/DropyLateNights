package com.example.dropy.ui.screens.myTrucks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.myTruckEditDetails.MyTruckEditDetailsViewModel
import com.example.dropy.ui.screens.nearestTrucks.NearestTruckContent
import com.example.dropy.ui.screens.nearestTrucks.NearestTrucksViewmodel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeViewModel
import com.example.dropy.ui.screens.truckOrders.TruckOrdersViewModel
import com.example.dropy.ui.screens.waterOrderDetails.WaterOrderDetailsViewModel
import com.example.dropy.ui.screens.waterVendorDash.WaterVendorDashContent
import com.example.dropy.ui.screens.waterVendorDash.WaterVendorDashViewModel


@Composable
fun MyTrucks(
    cartPageViewModel: CartPageViewModel,
    myTrucksViewmodel: MyTrucksViewmodel,
    waterVendorDashViewModel: WaterVendorDashViewModel,
    truckOrdersViewModel: TruckOrdersViewModel,
    myTruckEditDetailsViewModel: MyTruckEditDetailsViewModel
) {

    val myTrucksUiState by myTrucksViewmodel.myTrucksUiState.collectAsState()
    val waterVendorDashUiState by waterVendorDashViewModel.waterVendorDashUiState.collectAsState()

    val context = LocalContext.current

    val appUiState = myTrucksViewmodel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()




    AppScaffold(
        content = {
            MyTrucksContent(waterVendorDashUiState = waterVendorDashUiState, navigate = {
                myTrucksViewmodel.navigateTruckOrders(
                    truckOrdersViewModel = truckOrdersViewModel,
                    getTrucksResItem = it
                )
            }, navigateEdit = {myTrucksViewmodel.navigateTruckEdit(myTruckEditDetailsViewModel, it)})
        },
        pageLoading = waterVendorDashUiState.pageLoading,
        actionLoading = waterVendorDashUiState.actionLoading,
        errorList = waterVendorDashUiState.errorList,
        messageList = waterVendorDashUiState.messageList,
        onBackButtonClicked = { waterVendorDashViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { waterVendorDashViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { waterVendorDashViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { waterVendorDashViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { waterVendorDashViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = false
    )
}