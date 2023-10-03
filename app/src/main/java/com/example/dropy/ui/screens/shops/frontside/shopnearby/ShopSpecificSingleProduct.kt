package com.example.dropy.ui.screens.shops.frontside.shopnearby

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation

import com.example.dropy.ui.components.shops.frontside.shopnearby.ShopNearbyHeader
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.apphome.SearchUiState
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageUiState
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import com.google.android.gms.maps.model.LatLng

@Composable
fun ShopSpecificSingleProduct(
    navController: NavController,
    startLocale: LatLng? = null,
    appHomePageViewModel: AppHomePageViewModel,
    shopHomePageViewModel: ShopHomePageViewModel,
    cartPageViewModel: CartPageViewModel,
    searchUiState: SearchUiState,
    cartUiState: CartPageUiState,
    appViewModel: AppViewModel
) {



    LaunchedEffect(key1 = true, block = {
        cartPageViewModel.getCustomerCart()
    })

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (header, mapp, body) = createRefs()
/*
        if (startLocale != null) {
            MapsScreen(modifier = Modifier.fillMaxSize().constrainAs(mapp) {
                top.linkTo(parent.top)
            }, start = startLocale)
        }*/

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .constrainAs(mapp) {
                    top.linkTo(parent.top)
                }
        ) {
            MapComponent(float = 1f) {
                GoogleMapWrapper(
                    // cameraPosition = latLng
                    cameraZoom = 10f
                ) { mapUiSettings, mapProperties, cameraPositionState ->

                    GoogleMapSelectLocation(
                        cameraPositionState = cameraPositionState,
                        mapUiSettings = mapUiSettings,
                        mapProperties = mapProperties,
                        locationSelected = {/* selectLocationViewModel.setUserLatLong(it)*/ },
                        markerPositionList = if (searchUiState.filter.equals("shops")) searchUiState.shoplist else searchUiState.productlist,
                        markerInfoWindowClicked = {/*selectLocationViewModel.deleteMarker()*/ },
                        title = ""
                    )

                    //MapsScreen(start = start)
                }
            }

        }



        ShopsNearYou(
            modifier = Modifier.constrainAs(body) {
                bottom.linkTo(parent.bottom)
            },
            navController = navController,
            searchUiState = searchUiState,
            shopHomePageViewModel = shopHomePageViewModel,
            cartPageViewModel = cartPageViewModel,
            appViewModel = appViewModel,
            appHomePageViewModel = appHomePageViewModel,
        )
    } // STOPSHIP: 
}