package com.example.dropy.ui.screens.rider

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.dropy.R
import com.example.dropy.ui.app.RiderDestination
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation

import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.screens.dropymainmodel.dropyMainHeader
import com.google.android.gms.maps.model.LatLng

@Composable
fun DropyHere(
    navController: NavController? = null,
    startLocale: LatLng? = null,
    latLng: LatLng? = LatLng(0.0, 0.0)
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (map, body, head) = createRefs()
        /*     if (startLocale != null) {
                 MapsScreen(modifier = Modifier
                     .fillMaxSize()
                     .blur(13.dp)
                     .constrainAs(map) {
                         top.linkTo(parent.top)
                     }, start = startLocale)
             }*/
        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(13.dp)
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
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(head) {
                    top.linkTo(parent.top)
                }
        ) {

            dropyMainHeader()
            Column(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .background(Color.Black)
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "YOUR RIDE IS HERE",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.axiformasemibold))
                    )
                )
            }

        }
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                )
                .clickable {
                    navController?.navigate(RiderDestination.ARRIVEDPAY) {
                        navOptions { // Use the Kotlin DSL for building NavOptions
                            anim {
                                enter = android.R.animator.fade_in
                                exit = android.R.animator.fade_out
                            }
                        }
                    }
                }
                .fillMaxWidth()
                .height(250.dp)
                .constrainAs(body) {
                    bottom.linkTo(parent.bottom)
                }
                .padding(top = 15.dp), verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            BackgroundedText(
                background = Color(0xFFFCD313),
                textColor = Color.Black,
                text = "RIDER ON THE WAY",
                modifier = Modifier.padding(start = 17.dp),
                vertical = 3,
                textSize = 9
            )

            Text(
                text = "15 MINUTES TO",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.axiformabold))
                )
            )

            Text(
                text = "DESTINATION",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(Font(R.font.axiformaheavy))
                )
            )
        }
    }
}

@Preview
@Composable
fun demoo() {
    DropyHere()
}