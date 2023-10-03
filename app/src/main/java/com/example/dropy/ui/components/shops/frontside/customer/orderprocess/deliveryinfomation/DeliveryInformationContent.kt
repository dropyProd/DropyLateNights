package com.example.dropy.ui.components.shops.frontside.customer.orderprocess.deliveryinfomation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.network.models.deliverymethods.DeliveryMethodResponseItem
import com.example.dropy.ui.app.AppUiState
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.AddressDataClass
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation


import com.example.dropy.ui.components.shops.frontside.customer.orderprocess.deliveryinfomation.deliveryLocations.MyDeliveryLocations
import com.example.dropy.ui.components.shops.frontside.customer.orderprocess.deliveryinfomation.deliverymethod.DeliveryMethodDataClass
import com.example.dropy.ui.components.shops.frontside.customer.orderprocess.deliveryinfomation.deliverymethod.SelectDeliveryMethod
import com.example.dropy.ui.screens.shops.frontside.checkout.DeliveryInformationUiState
import com.example.dropy.ui.screens.shops.frontside.checkout.trackyourorder.TrackYourOrderViewModel
import com.example.dropy.ui.screens.shops.frontside.dashboard.addresses.singleaddress.SingleAddressUiState
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageUiState
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import com.example.dropy.ui.theme.DropyYellow
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun DeliveryInformationContent(
    uiState: DeliveryInformationUiState,
    singleAddressUiState: SingleAddressUiState,
    selectTaggedAddress: (address: AddressDataClass) -> Unit,
    editTaggedLocation: (address: AddressDataClass) -> Unit,
    onMyLocationClicked: () -> Unit,
    onEditDeliveryLocationClicked: () -> Unit,
    onSelectMethod: (DeliveryMethodResponseItem, Int) -> Unit,
    onNextClicked: () -> Unit,
    onPlaceNameChanged: (String) -> Unit,
    openSearchmapdialog: () -> Unit,
    suggestedLocales: List<AddressDataClass>,
    latLng: LatLng? = null,
    updatePrice: ((String, Int, Int, Boolean) -> Unit)? = null,
    shopname: String,
    distance: String,
    appUiState: AppUiState,
    shopHomePageUiState: ShopHomePageUiState,
    appViewModel: AppViewModel,
    shopHomePageViewModel: ShopHomePageViewModel,
    trackYourOrderViewModel: TrackYourOrderViewModel
) {

    val trackOrderUistate by trackYourOrderViewModel.trackYourOrderUiStateUiState.collectAsState()


    LaunchedEffect(key1 = true, block = {
        /* for (i in 0..3) {
             if (i == 2) {
                 val price =
                     (shopHomePageUiState.shopDistanceNum.toInt() / 1000) * uiState.deliveryMethodList[0].default_charge_per_km!!

                 onSelectMethod(uiState.deliveryMethodList[0], price)

             }

             delay(1000)
         }*/


    })


    ConstraintLayout(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        val (selectlocations, mylocations, map, favoptions, deliverymethods, btn) = createRefs()

        SelectDeliveryLocation(
            currentAddress = appUiState.myAddress,
            taggedAddresses = uiState.taggedAddresses,
            selectTaggedAddress = { selectTaggedAddress(it) },
            editTaggedLocation = { editTaggedLocation(it) },
            onMyLocationClicked = { onMyLocationClicked() },
            onEditDeliveryLocationClicked = { onEditDeliveryLocationClicked() },
            onPlaceNameChanged = onPlaceNameChanged,
            openSearchmapdialog = openSearchmapdialog,
            autompleteLocations = suggestedLocales,
            modifier = Modifier.constrainAs(selectlocations){
                top.linkTo(parent.top)
            }
        )
        MyDeliveryLocations(
            singleAddressUiState = singleAddressUiState,
            onPlaceNameChanged,
            choose = {
                appViewModel.addAddress(it)
                shopHomePageViewModel.getRouteDetails()
            },
            modifier = Modifier
                .constrainAs(mylocations) {
                    top.linkTo(selectlocations.bottom)
                }
                .zIndex(4f)
        )
        Box(
            modifier = Modifier
                .padding(vertical = 19.dp)
                .fillMaxWidth()
                .height(517.dp)
                .constrainAs(map) {
                    top.linkTo(mylocations.bottom, -20.dp)
                }
                .zIndex(1f)
        ) {

            MapComponent(float = 1f) {
                GoogleMapWrapper(
                    cameraPosition = if (shopHomePageUiState.polylines.isEmpty()) latLng else shopHomePageUiState.polylines[0],
                    appViewModel = appViewModel
                ) { mapUiSettings, mapProperties, cameraPositionState ->

                    GoogleMapSelectLocation(
                        cameraPositionState = cameraPositionState,
                        mapUiSettings = mapUiSettings,
                        mapProperties = mapProperties,
                        locationSelected = {/* selectLocationViewModel.setUserLatLong(it)*/ },
                        markerPosition = latLng,
                        markerInfoWindowClicked = {/*selectLocationViewModel.deleteMarker()*/ },
                        title = shopname,
                        snippet = distance,
                        polylines = if (shopHomePageUiState.polylines.isNotEmpty()) shopHomePageUiState.polylines else trackOrderUistate.polylines
                    )

                    //MapsScreen(start = start)
                }
            }

        }
        FavoriteDeliveryOptions(
            updatePrice = { string, int , num ->
                if (updatePrice != null) {
                    updatePrice(string, int, num,true)
                }
            },
            eta = if (shopHomePageUiState.polylines.isNotEmpty()) shopHomePageUiState.timetaken.toInt() else trackOrderUistate.seconds,
            modifier = Modifier.constrainAs(favoptions){
                top.linkTo(map.bottom, -20.dp)
            }
        )
        SelectDeliveryMethod(
            selectedMethod = { item, price ->
                onSelectMethod(item, price)
                if (updatePrice != null) {
                    updatePrice(item.method_name.toString(), price, Random.nextInt(11, 55), false)
                }
            },
            deliveryMethodList = uiState.deliveryMethodList,
            shopDistance = shopHomePageUiState.shopDistanceNum.toInt(),
            eta = if (shopHomePageUiState.polylines.isNotEmpty()) shopHomePageUiState.timetaken.toInt() else trackOrderUistate.seconds,
            modifier = Modifier.constrainAs(deliverymethods){
                top.linkTo(favoptions.bottom)
            }
        )

        Box(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
                .constrainAs(btn) {
                    top.linkTo(deliverymethods.bottom)
                },
            contentAlignment = Alignment.Center
        ) {
            TotallyRoundedButton(
                buttonText = "next",
                backgroundColor = DropyYellow,
                action = { onNextClicked() },
                widthFraction = 0.6,
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
fun DeliveryInformationContentPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        val taggedAddresses = mutableListOf(
            AddressDataClass(
                latitude = 0.000,
                longitude = 0.000,
                locationTag = "home"
            ),
            AddressDataClass(
                latitude = 0.000,
                longitude = 0.000,
                locationTag = "office"
            ),
            AddressDataClass(
                latitude = 0.000,
                longitude = 0.000,
                locationTag = "other"
            )
        )
        val deliveryOptions = mutableListOf(
            DeliveryMethodDataClass(
                type = "on foot",
                etaInMinutes = 10,
                price = 123,
                iconUrl = ""
            ),
            DeliveryMethodDataClass(
                type = "cyclist",
                etaInMinutes = 10,
                price = 123,
                iconUrl = ""
            ),
            DeliveryMethodDataClass(
                type = "rider",
                etaInMinutes = 10,
                price = 123,
                iconUrl = ""
            ),
        )
        DeliveryInformationContent(
            uiState = DeliveryInformationUiState(),
            selectTaggedAddress = {},
            editTaggedLocation = {},
            onEditDeliveryLocationClicked = {},
            onMyLocationClicked = {},
            onSelectMethod = { _, _ -> },
            onNextClicked = {},
            onPlaceNameChanged = {},
            openSearchmapdialog = {},
            suggestedLocales = listOf(),
            shopname = "",
            singleAddressUiState = SingleAddressUiState(),
            appUiState = AppUiState(),
            shopHomePageUiState = ShopHomePageUiState(),
            appViewModel = hiltViewModel(),
            shopHomePageViewModel = hiltViewModel(),
            distance = "",
            trackYourOrderViewModel = hiltViewModel()
        )
    }
}