package com.example.dropy.ui.screens.rider

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
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
import com.example.dropy.ui.app.RiderDestination
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation

import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.screens.dropymainmodel.dropyMainHeader
import com.example.dropy.ui.screens.locale.BackgroundedImage
import com.example.dropy.ui.screens.locale.personItem
import com.google.android.gms.maps.model.LatLng

@Composable
fun RiderPickup(
    navController: NavController? = null,
    startLocale: LatLng? = null,
    latLng: LatLng? = LatLng(0.0, 0.0)
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (map, body, head) = createRefs()
        /*    if (startLocale != null) {
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

        dropyMainHeader(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(head) {
                top.linkTo(parent.top)
            })

        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                )
                .fillMaxWidth()
                .height(250.dp)
                .constrainAs(body) {
                    bottom.linkTo(parent.bottom)
                }
                .clickable {
                    navController?.navigate(RiderDestination.DROPYHERE) {
                        navOptions { // Use the Kotlin DSL for building NavOptions
                            anim {
                                enter = android.R.animator.fade_in
                                exit = android.R.animator.fade_out
                            }
                        }
                    }
                }
                .padding(top = 13.dp), verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            BackgroundedText(
                background = Color(0xFFFCD313),
                textColor = Color.Black,
                text = "RIDER ON THE WAY",
                modifier = Modifier.padding(start = 17.dp),
                vertical = 3,
                textSize = 9
            )
            personItemRiderPickup()

            Column(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .background(Color(0xFF584AFF))
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "4 MINUTES TO PICKUP",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.axiformasemibold))
                    )
                )
            }
        }
    }
}

@Composable
fun personItemRiderPickup() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 17.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.imgone),
                contentDescription = "",
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .border(width = 1.dp, color = Color(0xFF584AFF), shape = CircleShape),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.wrapContentWidth(),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "RAYMOND KASTEMIL",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily(Font(R.font.axiformablack))
                    )
                )

                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    BackgroundedText(
                        background = Color(0xFFFCD313),
                        textColor = Color.Black,
                        text = "3,120 RIDES",
                        textSize = 9,
                        vertical = 3
                    )
                    BackgroundedText(
                        background = Color(0xFFFCD313),
                        textColor = Color.Black,
                        text = "RATING 4.89",
                        textSize = 9,
                        vertical = 3
                    )
                }

            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            BackgroundedImage(background = Color(0xFF1BFC13), image = R.drawable.phone)
            BackgroundedImage(background = Color(0xFFFCD313), image = R.drawable.email)
        }
    }
}

@Preview
@Composable
fun demosc() {
    RiderPickup()
}
