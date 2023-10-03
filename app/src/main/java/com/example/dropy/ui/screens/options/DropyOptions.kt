package com.example.dropy.ui.screens.options

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.navOptions

import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.R
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation
import com.google.android.gms.maps.model.LatLng

@Composable
fun DropyOptions(navController: NavController,startLocale: LatLng? = null, latLng: LatLng? = LatLng(0.0, 0.0)) {
    Column(modifier = Modifier.fillMaxSize()) {
    /*    if (startLocale != null) {
            MapsScreen(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.65f),
                start = startLocale
            )
        }*/
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.65f)
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
                .fillMaxHeight()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {

            BackgroundedText(
                background = Color(0xFFFCD313),
                textColor = Color.Black,
                text = "79KM TO DESTINATION"
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                approximateItem(color = Color(0xFFCCC9FC), show = true, text = "EXPRESS RIDER")
                approximateItem(
                    color = Color(0xFFFFF9DB),
                    show = false,
                    text = "COURIER",
                    arrangement = Arrangement.Center
                )
            }

            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White,
                ),
                onClick = {
                    navController.navigate(ParcelDestination.PARCEL_EXPRESS){
                        navOptions { // Use the Kotlin DSL for building NavOptions
                            anim {
                                enter = android.R.animator.fade_in
                                exit = android.R.animator.fade_out
                            }
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(23.dp)
            ) {
                Text(
                    text = "CONTINUE",
                    style = TextStyle(fontWeight = FontWeight.ExtraBold, color = Color.White)
                )

            }

        }
    }
}

@Composable
fun approximateItem(
    color: Color,
    show: Boolean,
    text: String,
    arrangement: Arrangement.HorizontalOrVertical = Arrangement.SpaceBetween
) {
    Row(
        modifier = Modifier
            .background(color, shape = RoundedCornerShape(13.dp))
            .border(width = 1.dp, color = Color(0xFFCCC9FC), shape = RoundedCornerShape(13.dp))
            .widthIn(min = 150.dp, max = 230.dp)
            .height(100.dp),
        horizontalArrangement = arrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.email),
                contentDescription = "",
                modifier = Modifier.size(25.dp)
            )

            Text(
                text = text,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }

        if (show) {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "APPROX DT",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )

                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .background(Color(0xFFFCD313), shape = RoundedCornerShape(20.dp))
                        .padding(horizontal = 10.dp, vertical = 7.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.email),
                        contentDescription = "",
                        modifier = Modifier.size(13.dp)
                    )

                    Text(
                        text = "105 mins",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                }

                BackgroundedText(
                    background = Color(0xFF584AFF),
                    textColor = Color.White,
                    text = "SELECT"
                )
            }
        }
    }

}