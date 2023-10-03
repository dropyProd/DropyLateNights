package com.example.dropy.ui.screens.truckFindJob

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.app.AppUiState
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation
import com.example.dropy.ui.components.order.BackgroundedText
import com.google.android.gms.maps.model.LatLng

@Composable
fun TruckFindJobContent(
    appViewModel: AppViewModel? = null,
    navigateNewJob: () -> Unit,
    appUiState: AppUiState,
    navigateOrders: () -> Unit,
    navigateFindJobs: () -> Unit,
    truckFindJobUiState: TruckFindJobUiState
) {

    Box(modifier = Modifier.fillMaxSize()) {

        MapComponent(float = 1f, modifier = Modifier.fillMaxSize()) {
            GoogleMapWrapper(
                cameraPosition = truckFindJobUiState.myAddress,
                appViewModel = appViewModel
            ) { mapUiSettings, mapProperties, cameraPositionState ->

                GoogleMapSelectLocation(
                    cameraPositionState = cameraPositionState,
                    mapUiSettings = mapUiSettings,
                    mapProperties = mapProperties,
                    locationSelected = {/* selectLocationViewModel.setUserLatLong(it)*/ },
                    markerPosition = truckFindJobUiState.myAddress,
                    markerInfoWindowClicked = {/*selectLocationViewModel.deleteMarker()*/ },
                    title = "",
                    snippet = ""
//                    polylines = if (shopHomePageUiState.polylines.isNotEmpty()) shopHomePageUiState.polylines else trackOrderUistate.polylines
                )

                //MapsScreen(start = start)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 19.dp)
                    .fillMaxWidth()
                    .height(95.dp)
                    .background(color = Color.White)
                    .padding(top = 22.dp, start = 40.dp)
            ) {

                Column() {


                    Text(
                        text = "Hello ${appUiState.activeProfile?.name},",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp,
//                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.axiformabold)),
                            letterSpacing = (-0.96).sp,
                            lineHeight = 37.sp
                        )
                    )


                    Text(
                        text = "Find work around your proximity",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 12.sp,
//                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily(Font(R.font.axiformaregular)),
                            letterSpacing = (-0.58).sp,
                            lineHeight = 21.sp
                        ),
                        modifier = Modifier.padding(top = 18.dp)
                    )
                }

                Switch(
                    checked = true,
                    onCheckedChange = { /*TODO*/ },
                    modifier = Modifier.padding(top = 32.dp, start = 52.dp),
                    colors = SwitchDefaults.colors(
                        checkedTrackColor = Color.Black,
                        checkedThumbColor = Color.White
                    )
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 321.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = Color.Transparent)
            ) {

                Box(modifier = Modifier.clickable { navigateNewJob() }) {
                    Row(
                        modifier = Modifier
                            .padding(start = 33.dp, top = 14.dp)
                            .width(85.dp)
                            .height(77.dp)
                            .background(Color.Black, shape = RoundedCornerShape(9.dp)),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "New Job",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 10.sp,
                                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                                lineHeight = 19.sp,
                                letterSpacing = (-0.48).sp
                            )
                        )
                    }

                    Row(
                        modifier = Modifier
                            .padding(start = 92.dp)
                            .width(34.dp)
                            .height(34.dp)
                            .background(Color(0xFF02CBE3), shape = CircleShape),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "1",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                                lineHeight = 23.sp,
                                letterSpacing = (-0.58).sp
                            )/*,
                            modifier = Modifier.padding(
                                start = 14.dp,
                                end = 11.dp,
                                top = 13.dp,
                                bottom = 11.dp
                            )*/
                        )
                    }
                }
                Box(modifier = Modifier.clickable { navigateOrders() }) {
                    Row(
                        modifier = Modifier
                            .padding(start = 44.dp, top = 14.dp)
                            .width(85.dp)
                            .height(77.dp)
                            .background(Color.White, shape = RoundedCornerShape(9.dp))
                            .border(1.dp, Color.Black, shape = RoundedCornerShape(9.dp)),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Previous Jobs",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 10.sp,
                                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                                lineHeight = 19.sp,
                                letterSpacing = (-0.48).sp
                            )
                        )
                    }

                    Row(
                        modifier = Modifier
                            .padding(start = 103.dp)
                            .width(34.dp)
                            .height(34.dp)
                            .background(Color(0xFFC2F8FF), shape = CircleShape),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "1",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                                lineHeight = 23.sp,
                                letterSpacing = (-0.58).sp
                            )/*,
                            modifier = Modifier.padding(
                                start = 14.dp,
                                end = 11.dp,
                                top = 13.dp,
                                bottom = 11.dp
                            )*/
                        )
                    }
                }

                Box(modifier = Modifier.clickable { navigateFindJobs() }) {
                    Row(
                        modifier = Modifier
                            .padding(start = 44.dp, top = 14.dp)
                            .width(85.dp)
                            .height(77.dp)
                            .background(Color.White, shape = RoundedCornerShape(9.dp))
                            .border(1.dp, Color.Black, shape = RoundedCornerShape(9.dp)),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Work\nDiary",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 10.sp,
                                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                                lineHeight = 19.sp,
                                letterSpacing = (-0.48).sp
                            )
                        )
                    }

                    Row(
                        modifier = Modifier
                            .padding(start = 103.dp)
                            .width(34.dp)
                            .height(34.dp)
                            .background(Color(0xFFC2F8FF), shape = CircleShape),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "1",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                                lineHeight = 23.sp,
                                letterSpacing = (-0.58).sp
                            )/*,
                            modifier = Modifier.padding(
                                start = 14.dp,
                                end = 11.dp,
                                top = 13.dp,
                                bottom = 11.dp
                            )*/
                        )
                    }
                }

            }
        }
    }

}

@Preview
@Composable
fun demo() {
    TruckFindJobContent(
        navigateNewJob = {},
        navigateOrders = {},
        appUiState = AppUiState(),
        navigateFindJobs = {}, truckFindJobUiState = TruckFindJobUiState())
}