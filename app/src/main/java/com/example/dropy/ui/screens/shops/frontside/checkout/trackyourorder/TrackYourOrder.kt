package com.example.dropy.ui.screens.shops.frontside.checkout.trackyourorder

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dropy.R
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation

import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.components.shops.frontside.dropdownRounded
import com.example.dropy.ui.screens.dropymainmodel.dropyMainHeader
import com.example.dropy.ui.screens.locale.personItem
import com.example.dropy.ui.screens.shops.backside.shopincomingorders.ShopIncomingOrdersViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import com.example.dropy.ui.screens.tracking.orderStatus
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue
import com.google.android.gms.maps.model.LatLng

@Composable
fun TrackYourOrder(
    navController: NavController? = null,
    startLocale: LatLng? = null,
    shopIncomingOrdersViewModel: ShopIncomingOrdersViewModel,
    trackYourOrderViewModel: TrackYourOrderViewModel,
    shopHomePageViewModel: ShopHomePageViewModel,
    state: String,
    appViewModel: AppViewModel
) {

    val uiState by trackYourOrderViewModel.trackYourOrderUiStateUiState.collectAsState()
    val shopHomeuiState by shopHomePageViewModel.shopHomePageUiState.collectAsState()
    val context = LocalContext.current


    LaunchedEffect(key1 = true, block = {
        trackYourOrderViewModel.iterateList(shopIncomingOrdersViewModel)
    })

    Column(verticalArrangement = Arrangement.spacedBy(13.dp), modifier = Modifier.fillMaxSize()) {
        /*dropyMainHeader()*/
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.dp, end = 6.dp, bottom = 10.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text(
                        text = "TRACK YOUR ORDER",
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                        letterSpacing = (-0.5).sp,
                        fontSize = 21.sp
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "ORDER",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformaheavy)
                            ),
                            letterSpacing = (-0.5).sp
                        )

                        dropdownRounded(
                            text = "${shopHomeuiState.shopName}",
                            color = Color(0xFFFCD313),
                            contentColor = Color.Black
                        )
                    }

                    Text(
                        text = "Realtime order tracking of your package",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily(
                            Font(R.font.axiformamedium)
                        ),
                        letterSpacing = (-0.5).sp,
                    )

                }

                Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text(
                        text = "ETA",
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                        letterSpacing = (-0.5).sp,
                        fontSize = 21.sp
                    )

                    Text(
                        text = "Estimated Time\nOf Arrival",
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily(Font(R.font.axiformamediumitalic)),
                        letterSpacing = (-0.5).sp,
                        fontSize = 9.sp,
                        fontStyle = FontStyle.Italic
                    )

                    val timeString: String = if (uiState.polylines.isNotEmpty()) {
                        if (uiState.timetaken >= 3600) {
                            val hrs: Int = uiState.timetaken / 3600
                            val min: Int = uiState.timetaken % 3600 / 60
                            // val seconds: Int = input % 3600 % 60
                            // val hrs = timeInMin / 60
                            //  val min = timeInMin % 60
                            "$hrs HOURS $min MINUTES"
                        } else {
                            val min = uiState.timetaken / 60
                            "$min MINUTES"
                        }
                    } else {
                        if (shopHomeuiState.timetaken >= 3600) {
                            val hrs: Int = shopHomeuiState.timetaken / 3600
                            val min: Int = shopHomeuiState.timetaken % 3600 / 60
                            // val seconds: Int = input % 3600 % 60
                            // val hrs = timeInMin / 60
                            //  val min = timeInMin % 60
                            "$hrs HOURS $min MINUTES"
                        } else {
                            val min = shopHomeuiState.timetaken / 60
                            "$min MINUTES"
                        }
                    }

                    BackgroundedText(
                        background = LightBlue,
                        textColor = Color.White,
                        text = timeString,
                        textSize = 10,
                        vertical = 3
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(217.dp)
            ) {
                /*  if (startLocale != null) {
                      MapsScreen(modifier = Modifier.fillMaxSize(), start = startLocale)
                  }*/


                MapComponent(float = 1f) {
                    GoogleMapWrapper(
                        cameraPosition = uiState.shopLatLng, cameraZoom = 12f
                    ) { mapUiSettings, mapProperties, cameraPositionState ->

                        GoogleMapSelectLocation(
                            cameraPositionState = cameraPositionState,
                            mapUiSettings = mapUiSettings,
                            mapProperties = mapProperties,
                            locationSelected = { },
                            markerPosition = uiState.shopLatLng ?: shopHomeuiState.shopLatLng,
                            markerInfoWindowClicked = {},
                            title = shopHomeuiState.shopName,
                            snippet = shopHomeuiState.shopDistance,
                            polylines = if (uiState.polylines.isNotEmpty()) uiState.polylines else shopHomeuiState.polylines
                        )

                        //MapsScreen(start = start)
                    }
                }
                BackgroundedText(
                    background = LightBlue,
                    textColor = Color.White,
                    text = "REALTIME",
                    textSize = 10,
                    vertical = 3,
                    modifier = Modifier
                        .align(
                            Alignment.TopStart
                        )
                        .padding(top = 13.dp, start = 12.dp)
                )
            }

            orderStatus(
                shopStatus = uiState.shopStatus,
                riderStatus = uiState.riderStatus,
                customerStatus = uiState.customerStatus,
                type = "order"
            )
            personItem(clicked = {

            }, appViewModel = appViewModel)

            Box(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                TotallyRoundedButton(
                    buttonText = "next",
                    backgroundColor = DropyYellow,
                    action = {
                        if (state.equals("user")) {

                            appViewModel.let { it1 ->
                                trackYourOrderViewModel.getCustomerQr(
                                    it1,
                                    context,
                                    show = false
                                )
                            }
                        } else {
                            navController?.navigate(AppDestinations.REVIEWRIDER)
                        }
                    },
                    widthFraction = 0.6,
                )

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun screeen() {
    //TrackYourOrder()
}