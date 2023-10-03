package com.example.dropy.ui.screens.rider

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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation

import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.screens.dropymainmodel.dropyMainHeader
import com.google.android.gms.maps.model.LatLng

@Composable
fun RideOptions(
    navController: NavController? = null,
    changeExpressRoute: () -> Unit,
    startLocale: LatLng? = null,
    latLng: LatLng? = LatLng(0.0, 0.0)
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (map, body) = createRefs()
        /*      if (startLocale != null) {
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
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(body) {
                    top.linkTo(parent.top)
                }, verticalArrangement = Arrangement.SpaceBetween
        ) {
            dropyMainHeader()
            rideLocationTextFields()
            BackgroundedText(
                background = Color(0xFFFCD313),
                textColor = Color.Black,
                text = "Distance 4.87 KM",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                vertical = 3
            )
            rideOptionsSquare(
                navController = navController,
                changeExpressRoute = changeExpressRoute
            )
        }

    }
}


@Composable
fun rideOptionsSquare(navController: NavController? = null, changeExpressRoute: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .background(color = Color.White, shape = RoundedCornerShape(30.dp))
            .border(width = 1.dp, color = Color(0xFFCCC9FC), shape = RoundedCornerShape(30.dp))
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            optionItem(
                name = "VIP",
                image = R.drawable.bicycle,
                eta = "10 mins",
                price = "150/-",
                color = Color.Black,
                selected = true
            )

            optionItem(
                name = "NORMAL",
                image = R.drawable.bicycle,
                eta = "15 mins",
                price = "100/-",
                color = Color.Black,
                selected = false
            )
        }

        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                contentColor = Color.White,
            ),
            onClick = {
                changeExpressRoute()
                navController?.navigate(ParcelDestination.PARCEL_EXPRESS_SINGLE) {
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
                text = "CONTINUE",
                style = TextStyle(fontWeight = FontWeight.ExtraBold, color = Color.White)
            )

        }
    }
}

@Composable
fun optionItem(
    name: String,
    image: Int,
    eta: String,
    price: String,
    color: Color,
    selected: Boolean
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val fontweight = if (selected) FontWeight.Black else FontWeight.SemiBold
        val fontt = if (selected) R.font.axiformablack else R.font.axiformasemibold
        Text(
            text = name,
            style = TextStyle(
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = fontweight,
                fontFamily = FontFamily(Font(fontt))
            )
        )
        Column(
            modifier = Modifier
                .width(108.dp)
                .background(
                    Color.Transparent,
                    shape = RoundedCornerShape(15.dp)
                )
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(15.dp))
                .padding(bottom = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = color, shape = RoundedCornerShape(15.dp))
                    .padding(vertical = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "",
                    modifier = Modifier.size(60.dp)
                )
            }

            Text(
                text = eta,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily(Font(R.font.axiformaregular))
                )
            )

            Text(
                text = price,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily(Font(R.font.axiformablack))
                )
            )

        }
    }
}

@Preview
@Composable
fun demmo() {
    RideOptions(changeExpressRoute = {})
}