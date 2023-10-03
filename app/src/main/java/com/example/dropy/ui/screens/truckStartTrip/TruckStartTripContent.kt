package com.example.dropy.ui.screens.truckStartTrip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.app.AppUiState
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.CustLatLng
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.screens.nearestWaterPoint.NearestWaterPointUiState
import com.example.dropy.ui.screens.truckIncomingWork.TruckIncomingWorkUiState
import com.google.android.gms.maps.model.LatLng

@Composable
fun TruckStartTripContent(
    scanQRClicked: () -> Unit,
    appViewModel: AppViewModel? = null,
    truckStartTripUiState: TruckStartTripUiState? = null,
    appUiState: AppUiState,
    truckIncomingWorkUiState: TruckIncomingWorkUiState,
    nearestWaterPointUiState: NearestWaterPointUiState
) {
    Box(modifier = Modifier.fillMaxSize()) {

        MapComponent(float = 1f, modifier = Modifier.fillMaxSize()) {
            GoogleMapWrapper(
                cameraPosition = if (truckStartTripUiState!!.isCustomerRoute) truckStartTripUiState!!.pathToCustomer[0] else truckStartTripUiState!!.pathToWaterPoint[0],
                appViewModel = appViewModel,
                cameraZoom = 11f
            ) { mapUiSettings, mapProperties, cameraPositionState ->

                GoogleMapSelectLocation(
                    cameraPositionState = cameraPositionState,
                    mapUiSettings = mapUiSettings,
                    mapProperties = mapProperties,
                    locationSelected = {/* selectLocationViewModel.setUserLatLong(it)*/ },
                    title = "",
                    snippet = "",
                    polylines = if (truckStartTripUiState!!.isCustomerRoute) truckStartTripUiState!!.pathToCustomer else truckStartTripUiState!!.pathToWaterPoint,
                    trucksRouteMarkerPositionList = if (truckStartTripUiState!!.isCustomerRoute) listOf(
                        CustLatLng(
                            name = appUiState.activeProfile?.name.toString(),
                            locale = truckStartTripUiState!!.pathToCustomer[0]
                        ),
                        CustLatLng(
                            name = truckIncomingWorkUiState.selectedOrder?.customer?.first_name + " " + truckIncomingWorkUiState.selectedOrder?.customer?.last_name,
                            locale = truckStartTripUiState!!.pathToCustomer[(truckStartTripUiState!!.pathToCustomer.size - 1)]
                        )
                    ) else listOf(
                        CustLatLng(
                            name = appUiState.activeProfile?.name.toString(),
                            locale = truckStartTripUiState!!.pathToWaterPoint[0]
                        ),
                        CustLatLng(
                            name = nearestWaterPointUiState.selectedWaterPoint?.name.toString(),
                            locale = truckStartTripUiState!!.pathToWaterPoint[(truckStartTripUiState!!.pathToWaterPoint.size - 1)]
                        ),
                        CustLatLng(
                            name = truckIncomingWorkUiState.selectedOrder?.customer?.first_name + " " + truckIncomingWorkUiState.selectedOrder?.customer?.last_name,
                            locale = truckStartTripUiState!!.pathToCustomer[(truckStartTripUiState!!.pathToCustomer.size - 1)]
                        )
                    ),
                    truckRouteMarkerInfoWindowClicked = {},
                    polylinesCustomer = if (truckStartTripUiState!!.isCustomerRoute) listOf() else truckStartTripUiState!!.pathToCustomer
                )

                //MapsScreen(start = start)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent)
        ) {
            /*   Row(modifier = Modifier
                   .padding(top = 16.dp, start = 28.dp, end = 25.dp)
                   .fillMaxWidth()
                   .height(99.dp)
                   .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(12.dp))
                   .border(
                       width = 1.dp,
                       color = Color(0xFFDEDEDE),
                       shape = RoundedCornerShape(12.dp)
                   )
                   .clickable { *//*navigate()*//* },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Row() {
                        Text(
                            text = "15KM",
                            fontSize = 21.sp,
//                        fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformaheavy)
                            ),
                            letterSpacing = (-1.01).sp,
                            lineHeight = 28.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 24.dp, start = 30.dp)
                        )

                        val ext =
                            if (truckRouteCustomerUiState?.isCustomerRoute == true) "To customer"
                            else "To ${truckIncomingWorkUiState!!.selectedOrder?.customer?.first_name} ${truckIncomingWorkUiState!!.selectedOrder!!.customer.first_name}"

                        Text(
                            text = "To ${truckIncomingWorkUiState!!.selectedOrder?.customer?.first_name} ${truckIncomingWorkUiState!!.selectedOrder!!.customer.last_name}",
                            fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformabold)
                            ),
                            letterSpacing = (-0.48).sp,
                            lineHeight = 18.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 32.dp, start = 4.dp)
                        )
                    }
                    Text(
                        text = "Approx Arrival Time",
                        fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(
                            Font(R.font.axiformabold)
                        ),
                        letterSpacing = (-0.48).sp,
                        lineHeight = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 16.dp, start = 30.dp)
                    )
                }

                Column() {
                    BackgroundedText(
                        background = Color.Black,
                        textColor = Color.White,
                        text = "25 min",
                        vertical = 3,
                        horizontal = 4,
                        modifier = Modifier.padding(start = 8.dp, top = 23.dp)
                    )

                    Text(
                        text = "3:45PM",
                        fontSize = 19.sp,
//                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(
                            Font(R.font.axiformaheavy)
                        ),
                        letterSpacing = (-0.91).sp,
                        lineHeight = 37.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 20.dp, start = 8.dp)
                    )
                }

            }*/

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 350.dp)
                    .width(156.dp)
                    .height(49.dp)
                    .background(color = Color(0xFF02CBE3), RoundedCornerShape(42.dp))
                    .border(width = 1.dp, color = Color(0x57707070), RoundedCornerShape(42.dp))
                    .clickable { scanQRClicked() },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                /*      val exte = if (truckRouteCustomerUiState?.isCustomerRoute == true) "START TRIP"
                      else "SCAN QR"*/
                androidx.compose.material.Text(
                    text = "SCAN QR",
                    color = Color.Black,
                    fontSize = 10.sp,
//                        fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                    letterSpacing = (-0.48).sp,
                )
            }
        }

    }
}

@Preview
@Composable
fun demo() {
    TruckStartTripContent(scanQRClicked = {}, appUiState = AppUiState(), nearestWaterPointUiState = NearestWaterPointUiState(), truckIncomingWorkUiState = TruckIncomingWorkUiState())
}