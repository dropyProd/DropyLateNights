package com.example.dropy.ui.screens.addWaterVendor

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsBacksideNavigation
import com.example.dropy.ui.components.commons.AddressDataClass
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.commons.maps.selectlocation.SelectLocationViewModel
import com.example.dropy.ui.components.maps.selectlocation.SelectLocationContent
import com.example.dropy.ui.screens.shops.backside.addshop.AddShopViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AddWatervendorLocation(
    addWaterVendorViewModel: AddWaterVendorViewModel,
    start: LatLng,
    selectLocationViewModel: SelectLocationViewModel,
    navController: NavController? = null,
    onPlaceNameChanged: (String) -> Unit,
    suggestedLocales: List<AddressDataClass>,
    fetchLocaleDetails: (String) -> Unit,
    openSearchPlaces: () -> Unit,
    cartPageViewModel: CartPageViewModel
) {
    val appUiState = addWaterVendorViewModel.appViewModel!!.appUiState.collectAsState()

    val uiState by addWaterVendorViewModel.addWaterVendorLocationUiState.collectAsState()
    val selectlocationuiState by selectLocationViewModel.selectLocationUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    val scope = rememberCoroutineScope()

    AppScaffold(
        content = {
            AddWatervendorLocationContent(
                onConfirmClicked = { text ->
                    /*   //   addShopViewModel.onAddShopAddress(it)
                       val id: MutableState<String?> = mutableStateOf(null)
                       suggestedLocales.forEach {
                           if (it.placeName.equals(text)) id.value = it.placeId
                       }
                       id.value?.let { fetchLocaleDetails(it) }*/
                    addWaterVendorViewModel.onGoToShopUploads()
                },
                start = start,
                onTextChange = {
                    scope.launch {
                        try {
                            selectLocationViewModel.onChangeShopLocation(it)
                            if (it.isNotEmpty()) {
                                onPlaceNameChanged(it)
                                delay(500)
                                navController?.navigate(
                                    ShopsBacksideNavigation.ADD_SHOP_LOCATION
                                )
                            }
                        } catch (e: Exception) {

                        }
                    }
                },
                clearText = {
                    selectLocationViewModel.clearShopLocation()
                },
                suggestedLocales = suggestedLocales,
                selectLocationViewModel = selectLocationViewModel,
                openSearchPlaces = openSearchPlaces,
                addWaterVendorViewModel = addWaterVendorViewModel
            )
        },
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
        showCart = false,
        showLogo = false
        )
}