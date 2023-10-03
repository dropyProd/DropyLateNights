package com.example.dropy.ui.screens.tracking

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.navOptions

import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.screens.dropymainmodel.dropyMainHeader
import com.example.dropy.R
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation
import com.google.android.gms.maps.model.LatLng

@Composable
fun TrackParcel(navController: NavController? = null, startLocale: LatLng? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.63f)
        ) {

            val (map, head) = createRefs()
            /*  if (startLocale != null) {
                  MapsScreen(modifier = Modifier.constrainAs(head) {
                      top.linkTo(parent.top)
                  }, start = startLocale)
              }*/
            MapComponent(modifier = Modifier
                .constrainAs(head) {
                    top.linkTo(parent.top)
                }
                .zIndex(.2f)) {
                GoogleMapWrapper(
                    cameraPosition = startLocale
                ) { mapUiSettings, mapProperties, cameraPositionState ->

                    GoogleMapSelectLocation(
                        cameraPositionState = cameraPositionState,
                        mapUiSettings = mapUiSettings,
                        mapProperties = mapProperties,
                        locationSelected = { },
                        markerPosition = startLocale,
                        markerInfoWindowClicked = {}
                    )

                    //MapsScreen(start = start)
                }
            }

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            BackgroundedText(
                background = Color(0xFFFCD313),
                textColor = Color.Black,
                text = "SHIPMENT STATUS",
                vertical = 3
            )

            orderStatus()

            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White,
                ),
                onClick = {
                    navController?.navigate(AppDestinations.REVIEWRIDER) {
                        navOptions { // Use the Kotlin DSL for building NavOptions
                            anim {
                                enter = android.R.animator.fade_in
                                exit = android.R.animator.fade_out
                            }
                        }
                    }
                },
                modifier = Modifier
                    .width(150.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(
                    text = "REVIEW",
                    style = TextStyle(fontWeight = FontWeight.ExtraBold, color = Color.White)
                )

            }

        }
    }

}

@Composable
fun orderStatus(
    shopStatus: String = "active",
    riderStatus: String = "inactive",
    customerStatus: String = "inactive",
    type: String = "parcel"
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 13.dp)
            .background(color = Color.Transparent)
            .fillMaxWidth()
            .height(156.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Column() {
                dots(status = shopStatus)
                dots(status = riderStatus)
                dots(status = customerStatus)
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 6.dp), verticalArrangement = Arrangement.SpaceBetween
            ) {
                statusItem(
                    status = shopStatus,
                    textone = "The delivery guy has already picked up your ${type}.",
                    textTwo = "${(type).toUpperCase()} COLLECED"
                )
                statusItem(
                    status = riderStatus,
                    textone = "Your delivery guy picked up your ${type}",
                    textTwo = "${(type).toUpperCase()} PICKED"
                )

                statusItem(
                    status = customerStatus,
                    textone = "Your delivery guy has arrived",
                    textTwo = "${(type).toUpperCase()} HAS ARRIVED"
                )
            }

        }


    }
}

@Composable
fun statusItem(status: String, textone: String, textTwo: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            /*       modifier = Modifier.padding(top = 5.dp)*/
        ) {
            Text(
                text = textone,
                style = TextStyle(
                    color = if (status.equals("active")) Color.Black else Color(0xFFABABAB),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily(Font(R.font.axiformamedium)),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.width(213.dp)
            )

            BackgroundedText(
                background = if (status.equals("active")) Color(0xFF059A00) else Color(0xFFABABAB),
                textColor = Color.White,
                text = textTwo,
                textSize = 9,
                vertical = 3
            )
        }

        Column(
            modifier = Modifier.padding(top = 5.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "02:30 PM",
                style = TextStyle(
                    color = if (status.equals("active")) Color.Black else Color(0xFFABABAB),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily(Font(R.font.axiformablack)),
                )
            )
            Text(
                text = "2 FEB 2022",
                style = TextStyle(
                    color = if (status.equals("active")) Color.Black else Color(0xFFABABAB),
                    fontSize = 7.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily(Font(R.font.axiformablack)),
                ),
                modifier = Modifier.align(Alignment.End)
            )
        }

    }
}

@Composable
fun dots(status: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(1.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .size(15.dp)
                .background(
                    color = if (status.equals("active")) Color(0xFF059A00) else Color.Transparent,
                    shape = CircleShape
                )
                .border(
                    width = 1.dp,
                    shape = CircleShape,
                    color = if (status.equals("active")) Color.Transparent else Color(0xFFABABAB)
                )
                .padding(2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (status.equals("active")) {
                Image(
                    Icons.Filled.Done,
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = Color.White)
                )
            } else if (status.equals("next")) {
                Image(
                    Icons.Filled.MyLocation,
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = Color(0xFFABABAB))
                )
            }

        }

        if (!status.equals("last")) {
            ConstraintLayout() {
                val (one, two, three, four, five, six, seven, eight, nine, ten) = createRefs()
                textDot(
                    color = if (status.equals("active")) Color(0xFF059A00) else Color(0xFFABABAB),
                    modifier = Modifier.constrainAs(one) {
                        top.linkTo(parent.top, -10.dp)
                    })
                textDot(
                    color = if (status.equals("active")) Color(0xFF059A00) else Color(0xFFABABAB),
                    modifier = Modifier.constrainAs(two) {
                        top.linkTo(one.bottom, -12.dp)
                    })
                textDot(
                    color = if (status.equals("active")) Color(0xFF059A00) else Color(0xFFABABAB),
                    modifier = Modifier.constrainAs(three) {
                        top.linkTo(two.bottom, -12.dp)
                    })
                textDot(
                    color = if (status.equals("active")) Color(0xFF059A00) else Color(0xFFABABAB),
                    modifier = Modifier.constrainAs(four) {
                        top.linkTo(three.bottom, -12.dp)
                    })
                textDot(
                    color = if (status.equals("active")) Color(0xFF059A00) else Color(0xFFABABAB),
                    modifier = Modifier.constrainAs(five) {
                        top.linkTo(four.bottom, -12.dp)
                    })
                textDot(
                    color = if (status.equals("active")) Color(0xFF059A00) else Color(0xFFABABAB),
                    modifier = Modifier.constrainAs(six) {
                        top.linkTo(five.bottom, -12.dp)
                    })
                textDot(
                    color = if (status.equals("active")) Color(0xFF059A00) else Color(0xFFABABAB),
                    modifier = Modifier.constrainAs(seven) {
                        top.linkTo(six.bottom, -12.dp)
                    })
                textDot(
                    color = if (status.equals("active")) Color(0xFF059A00) else Color(0xFFABABAB),
                    modifier = Modifier.constrainAs(eight) {
                        top.linkTo(seven.bottom, -12.dp)
                    })
                textDot(
                    color = if (status.equals("active")) Color(0xFF059A00) else Color(0xFFABABAB),
                    modifier = Modifier.constrainAs(nine) {
                        top.linkTo(eight.bottom, -12.dp)
                    })
                textDot(
                    color = if (status.equals("active")) Color(0xFF059A00) else Color(0xFFABABAB),
                    modifier = Modifier.constrainAs(ten) {
                        top.linkTo(nine.bottom, -12.dp)
                    })

            }
        }

        /*     Column() {
                 textDot(color = Color(0xFF059A00), modifier = Modifier.padding(top = -20.dp))
                 textDot(color = Color(0xFF059A00), modifier = Modifier.padding(top = -10.dp))
                 textDot(color = Color(0xFF059A00), modifier = Modifier.padding(top = -5.dp))
             }*/
    }
}

@Composable
fun textDot(color: Color, modifier: Modifier = Modifier) {
    Text(
        text = ".",
        style = TextStyle(
            color = color,
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily(Font(R.font.axiformamediumitalic))
        ), modifier = modifier
    )

}

@Preview
@Composable
fun demo() {
    TrackParcel()
}