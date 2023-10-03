package com.example.dropy.ui.screens.rider

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
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

import com.example.dropy.ui.screens.dropymainmodel.dropyMainHeader
import com.example.dropy.R
import com.example.dropy.ui.app.RiderDestination
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation
import com.example.dropy.ui.components.order.BackgroundedImageText
import com.google.android.gms.maps.model.LatLng

@Composable
fun MainRide(navController: NavController? = null, startLocale: LatLng? = null,  latLng: LatLng? = LatLng(0.0, 0.0)) {

    ConstraintLayout {
        val (map, body) = createRefs()
       /* if (startLocale != null) {
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(horizontal = 24.dp, vertical = 8.dp)
            ) {

                Text(
                    text = "Welcome Chirag,",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.axiformabold))
                    )
                )
                Text(
                    text = "Heading somewhere?",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily(Font(R.font.axiformaregular))
                    )
                )
            }

            rideLocationTextFields()
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White,
                ),
                onClick = {
                    navController?.navigate(RiderDestination.RIDEOPTIONS){
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
}


@Composable
fun mainRideTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .width(296.dp)
            .height(56.dp)
            .background(color = Color(0xADFFF9DB), shape = RoundedCornerShape(bottomEnd = 45.dp))
            .border(
                width = 1.dp,
                color = Color(0xFFCCC9FC),
                shape = RoundedCornerShape(bottomEnd = 45.dp, topEnd = 4.dp)
            )
            .padding(horizontal = 24.dp, vertical = 1.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        TextField(
            value = value, onValueChange = onValueChange/*, cursorBrush = Brush.linearGradient(
                colors = listOf(
                    Color.Black,
                    Color.Black
                )
            )*/,
            textStyle = TextStyle(
                color = Color.Black, fontSize = 12.sp, fontFamily = FontFamily(
                    Font(R.font.axiformaregular)
                ), fontWeight = FontWeight.Normal
            ),
            placeholder = { Text(text = label) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

@Preview
@Composable
fun demoi(){
    mainRideTextField(value = "", onValueChange = {}, label = "Choose Destination")
}

@Composable
fun rideLocationTextFields() {
    Column(verticalArrangement = Arrangement.spacedBy(60.dp)) {
        val pickup = remember {
            mutableStateOf("")
        }
        val destination = remember {
            mutableStateOf("")
        }

        val list = mutableListOf<String>(
            "Home", "Work", "Mamas"
        )
        Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {

            mainRideTextField(value = pickup.value, onValueChange = {
                pickup.value = it
            }, label = "Enter pickup location")

            Row(
                horizontalArrangement = Arrangement.spacedBy(13.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 24.dp)
            ) {
                list.forEach {
                    BackgroundedImageText(
                        background = Color(0xFF584AFF),
                        textColor = Color.White,
                        text = it,
                        image = R.drawable.bicycle
                    )
                }
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {

            mainRideTextField(value = destination.value, onValueChange = {
                destination.value = it
            }, label = "Choose Destination")

            Row(
                horizontalArrangement = Arrangement.spacedBy(13.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 24.dp)
            ) {
                list.forEach {
                    BackgroundedImageText(
                        background = Color(0xFF584AFF),
                        textColor = Color.White,
                        text = it,
                        image = R.drawable.bicycle
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun demo() {
    MainRide()
}