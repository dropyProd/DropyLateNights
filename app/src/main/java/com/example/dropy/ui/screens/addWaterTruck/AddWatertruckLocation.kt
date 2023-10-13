package com.example.dropy.ui.screens.addWaterTruck

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsBacksideNavigation
import com.example.dropy.ui.components.commons.AddressDataClass
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.commons.maps.selectlocation.SelectLocationViewModel
import com.example.dropy.ui.components.maps.selectlocation.SelectLocationContent
import com.example.dropy.ui.screens.myTruckEditDetails.MyTruckEditDetailsViewModel
import com.example.dropy.ui.screens.shops.backside.addshop.AddShopViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AddWatertruckLocation(
    addWaterTruckViewmodel: AddWaterTruckViewmodel,
    start: LatLng,
    selectLocationViewModel: SelectLocationViewModel,
    navController: NavController? = null,
    onPlaceNameChanged: (String) -> Unit,
    suggestedLocales: List<AddressDataClass>,
    fetchLocaleDetails: (String) -> Unit,
    openSearchPlaces: () -> Unit,
    cartPageViewModel: CartPageViewModel,
    myTruckEditDetailsViewModel: MyTruckEditDetailsViewModel
) {
    val appUiState = addWaterTruckViewmodel.appViewModel!!.appUiState.collectAsState()

    val uiState by addWaterTruckViewmodel.addWaterTruckLocationUiState.collectAsState()
    val selectlocationuiState by selectLocationViewModel.selectLocationUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    AppScaffold(
        content = {
            AddWatertruckLocationContent(
                onConfirmClicked = { text ->
                    /*   //   addShopViewModel.onAddShopAddress(it)
                       val id: MutableState<String?> = mutableStateOf(null)
                       suggestedLocales.forEach {
                           if (it.placeName.equals(text)) id.value = it.placeId
                       }
                       id.value?.let { fetchLocaleDetails(it) }*/
                    if (uiState.state.equals("EditTruck"))
                        myTruckEditDetailsViewModel.modifyTruck(context = context)
                    else
                        addWaterTruckViewmodel.onGoToShopUploads()
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
                addWaterTruckViewmodel = addWaterTruckViewmodel,
                myTruckEditDetailsViewModel = myTruckEditDetailsViewModel
            )
        },
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
        showLogo = false
    )
}