package com.example.dropy.ui.components.commons

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation

import com.example.dropy.ui.components.order.BackgroundedText
import com.google.android.gms.maps.model.LatLng

@Composable
fun NearMe(
    navController: NavController, startLocale: LatLng, latLng: LatLng? = LatLng(0.0, 0.0)
) {


    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (btn, map) = createRefs()

        /* MapsScreen(
             modifier = Modifier
                 .fillMaxSize()
                 .constrainAs(map) {
                     top.linkTo(parent.top)
                 }, showNearbyRiders = true, start = startLocale
         )*/

        Box(
            modifier = Modifier
                .fillMaxSize()
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


        BackgroundedText(
            background = Color.Black,
            textColor = Color.White,
            text = "6 FOUND",
            modifier = Modifier
                .constrainAs(btn) {
                    bottom.linkTo(parent.bottom, 15.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }  .zIndex(.3f)
                .clickable {
                    navController.navigate(ParcelDestination.PARCEL_EXPRESS_SINGLE) {
                        navOptions { // Use the Kotlin DSL for building NavOptions
                            anim {
                                enter = R.animator.fade_in
                                exit = R.animator.fade_out
                            }
                        }
                    }
                })

    }
}