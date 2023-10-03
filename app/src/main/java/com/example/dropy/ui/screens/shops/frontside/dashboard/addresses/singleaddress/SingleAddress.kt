package com.example.dropy.ui.screens.shops.frontside.dashboard.addresses.singleaddress

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.frontside.customer.customerdashboard.addresses.AddressContent
import com.example.dropy.ui.screens.shops.backside.addshop.AddShopViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.google.android.gms.maps.model.LatLng

@Composable
fun SingleAddress(
    singleAddressViewModel: SingleAddressViewModel,
    onChangeLocationClicked:() -> Unit,
    cartPageViewModel: CartPageViewModel,
    addShopViewModel: AddShopViewModel
){

    val appUiState = singleAddressViewModel.appViewModel!!.appUiState.collectAsState()
    val locationuiState by addShopViewModel.addShopLocationUiState.collectAsState()
    val uiState by singleAddressViewModel.singleAddressUiState.collectAsState()
    val cartuiState by cartPageViewModel.cartPageUiState.collectAsState()

    AppScaffold(
        content = {
                  AddressContent(
                      uiState = uiState,
                      onAddressChanged = { singleAddressViewModel.onAddressChanged(it) },
                      onLocationChanged = singleAddressViewModel::onLocationChanged,
                      onFloorChanged = singleAddressViewModel::onFloorChanged,
                      onAdditionalInformationChanged = { singleAddressViewModel.onAdditionalInformationChanged(it) },
                      onLocationTagChanged = { singleAddressViewModel.onLocationTagChanged(it) },
                      updateLocationTag = {singleAddressViewModel.updateLocationTag()},
                      onChangeLocationClicked = onChangeLocationClicked,
                      startLocale = locationuiState.shopAddress?.latitude?.let {
                          locationuiState.shopAddress?.longitude?.let { it1 ->
                              LatLng(
                                  it, it1
                              )
                          }
                      }
                  )
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { singleAddressViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { singleAddressViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { singleAddressViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { singleAddressViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { singleAddressViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartuiState.orderList.size
    )

}


@Preview(showBackground = true)
@Composable
fun SingleAddressPreview(){
    Column(Modifier.fillMaxSize()) {
        SingleAddress(singleAddressViewModel = SingleAddressViewModel(), onChangeLocationClicked = {}, cartPageViewModel = hiltViewModel(), addShopViewModel = hiltViewModel())
    }
}