package com.example.dropy.ui.components.parcels.frontside.expressdeliverymethod


import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation
import com.example.dropy.ui.theme.LightBlue
import com.google.android.gms.maps.model.LatLng

@Composable
fun ExpressDeliveryContent(navController: NavController? = null, startLocale: LatLng? = null, latLng: LatLng? = LatLng(0.0, 0.0)) {
    ConstraintLayout(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .fillMaxHeight(.85f)
//            .verticalScroll(rememberScrollState())
    ) {

        val (map, col) = createRefs()

    /*    if (startLocale != null) {
            MapsScreen(modifier = Modifier
                .fillMaxHeight(.6f)
                .constrainAs(map) {
                    top.linkTo(parent.top)
                }, start = startLocale)
        }*/

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.6f)
                .constrainAs(map) {
                    top.linkTo(parent.top)
                }
                .zIndex(.2f)
        ) {
            if (latLng != null) {
                MapComponent(float = 1f) {
                    GoogleMapWrapper(
                        cameraPosition = latLng
                    ) { mapUiSettings, mapProperties, cameraPositionState ->

                        GoogleMapSelectLocation(
                            cameraPositionState = cameraPositionState,
                            mapUiSettings = mapUiSettings,
                            mapProperties = mapProperties,
                            locationSelected = {/* selectLocationViewModel.setUserLatLong(it)*/ },
                            markerPosition = latLng,
                            markerInfoWindowClicked = {/*selectLocationViewModel.deleteMarker()*/ },
                            title = ""
                        )

                        //MapsScreen(start = start)
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(col) {
                    top.linkTo(map.bottom, 17.dp)
                }
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var deliveryMode: ExpressDeliveryMethodListItemDataClass
            ExpressDeliveryMethodList(selectDeliveryMode = {
                deliveryMode = it
            })
            Box(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .clickable { }
            ) {
                TotallyRoundedButton(
                    icon = null,
                    buttonText = "continue",
                    textFontSize = 12,
                    textColor = Color.White,
                    backgroundColor = Color.Black,
                    widthFraction = 0.5,
                    action = {
                        navController?.navigate(ParcelDestination.PARCEL_NEARME){
                            navOptions { // Use the Kotlin DSL for building NavOptions
                                anim {
                                    enter = R.animator.fade_in
                                    exit = R.animator.fade_out
                                }
                            }
                        }
                    }
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun ExpressDeliveryContentPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        ExpressDeliveryContent()
    }
}