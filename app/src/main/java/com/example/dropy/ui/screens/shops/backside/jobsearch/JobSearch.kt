package com.example.dropy.ui.screens.shops.backside.jobsearch

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dropy.R
import com.example.dropy.ui.app.RiderDestination
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation

import com.example.dropy.ui.screens.dropymainmodel.dropyMainHeader
import com.google.android.gms.maps.model.LatLng

@Composable
fun JobSearch(start: LatLng, navController: NavController) {
    Box(modifier = Modifier.fillMaxWidth()) {
        // MapsScreen(start = start, modifier = Modifier.fillMaxSize())
        MapComponent(float = 1f) {
            GoogleMapWrapper(
                cameraPosition = LatLng(0.0, 0.0)
            ) { mapUiSettings, mapProperties, cameraPositionState ->

                GoogleMapSelectLocation(
                    cameraPositionState = cameraPositionState,
                    mapUiSettings = mapUiSettings,
                    mapProperties = mapProperties,
                    locationSelected = { },
                    markerPosition = LatLng(0.0, 0.0),
                    markerInfoWindowClicked = {}
                )

                //MapsScreen(start = start)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent)
        ) {
            dropyMainHeader()

            Row(
                modifier = Modifier
                    .clickable { navController.navigate(RiderDestination.RIDERINCOMINGORDERS) }
                    .fillMaxWidth()
                    .height(95.dp)
                    .background(color = Color.White)
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column() {
                    Text(
                        text = "Hello Chirag,",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.axiformabold))
                        )
                    )
                    Text(
                        text = "Find work around your proximity",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily(Font(R.font.axiformaregular))
                        )
                    )
                }
                val checked = remember {
                    mutableStateOf(true)
                }

                Switch(
                    checked = checked.value,
                    onCheckedChange = { checked.value = !checked.value })
            }
        }
    }
}