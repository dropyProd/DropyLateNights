package com.example.dropy.ui.screens.reciever

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
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

import com.google.android.gms.maps.model.LatLng


@Composable
fun DropyReciever(
    navController: NavController, startLocale: LatLng? = null, latLng: LatLng? = LatLng(0.0, 0.0)
) {
    Column() {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.65f)
        ) {

            val (map, bdy) = createRefs()

            /*        if (startLocale != null) {
                        MapsScreen(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .constrainAs(map) {
                                    top.linkTo(parent.top)
                                }, start = startLocale
                        )
                    }*/

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
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
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = Color.White, shape = RoundedCornerShape(15.dp))
                    .border(
                        width = 1.dp,
                        color = Color(0xFFCCC9FC),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(horizontal = 13.dp, vertical = 14.dp)
                    .constrainAs(bdy) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .zIndex(.4f),//higher zindex brings item infront/on top of other views
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Text(
                    text = "RECIEVER DETAILS",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )


                val recieverId = remember {
                    mutableStateOf("")
                }

                val recieverPhone = remember {
                    mutableStateOf("")
                }
                val firstName = remember {
                    mutableStateOf("")
                }
                val secondName = remember {
                    mutableStateOf("")
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(width = 1.dp, Color(0xFFCCC9FC), shape = RoundedCornerShape(8.dp))
                        .background(color = Color(0xFFCCC9FC), shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 1.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.fav),
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                    )


                    TextField(
                        value = recieverId.value,
                        onValueChange = {
                            if (it.equals("RECIEVER ID")) recieverId.value = ""
                            recieverId.value = it
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(
                                text = "RECIEVER ID",
                                fontFamily = FontFamily(Font(R.font.axiformaextrabold)),
                                fontSize = 10.sp
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            cursorColor = Color.Black,
                            backgroundColor = Color.Transparent
                        )
                    )
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(width = 1.dp, Color(0xFFCCC9FC), shape = RoundedCornerShape(8.dp))
                        .background(color = Color(0xFFFFF9DB), shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 1.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.fav),
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                    )


                    TextField(
                        value = recieverPhone.value,
                        onValueChange = {
                            if (it.equals("RECIEVER PHONE NUMBER")) recieverPhone.value = ""
                            recieverPhone.value = it
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(
                                text = "RECIEVER PHONE NUMBER",
                                fontFamily = FontFamily(Font(R.font.axiformaextrabold)),
                                fontSize = 10.sp
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            cursorColor = Color.Black,
                            backgroundColor = Color.Transparent
                        )
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier
                            .width(140.dp)
                            .border(
                                width = 1.dp,
                                Color(0xFFCCC9FC),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(color = Color(0xFFFFF9DB), shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = 10.dp, vertical = 1.dp),
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.fav),
                            contentDescription = "",
                            modifier = Modifier
                                .size(20.dp)
                        )


                        TextField(
                            value = firstName.value,
                            onValueChange = {
                                if (it.equals("FIRST NAME")) firstName.value = ""
                                firstName.value = it
                            },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(
                                    text = "FIRST NAME",
                                    fontFamily = FontFamily(Font(R.font.axiformaextrabold)),
                                    fontSize = 10.sp
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                cursorColor = Color.Black,
                                backgroundColor = Color.Transparent
                            )
                        )
                    }

                    Row(
                        modifier = Modifier
                            .width(140.dp)
                            .border(
                                width = 1.dp,
                                Color(0xFFCCC9FC),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(color = Color(0xFFFFF9DB), shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = 10.dp, vertical = 1.dp),
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        /* Image(
                             painter = painterResource(id = R.drawable.fav),
                             contentDescription = "",
                             modifier = Modifier
                                 .size(20.dp)
                         )*/


                        TextField(
                            value = secondName.value,
                            onValueChange = {
                                if (it.equals("SECOND NAME")) secondName.value = ""
                                secondName.value = it
                            },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(
                                    text = "SECOND NAME",
                                    fontFamily = FontFamily(Font(R.font.axiformaextrabold)),
                                    fontSize = 10.sp
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                cursorColor = Color.Black,
                                backgroundColor = Color.Transparent
                            )
                        )
                    }
                }


            }

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 15.dp)
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .background(Color(0xFFFCD313), shape = RoundedCornerShape(20.dp))
                    .padding(horizontal = 4.dp, vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "CHOOSE PACKAGE",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PackageItem(selected = false, textOne = "SMALL", textTwo = "DOCUMENT")
                PackageItem(selected = false, textOne = "MEDIUM", textTwo = "1KG-3KG")
                PackageItem(selected = true, textOne = "LARGE", textTwo = "4KG-10KG")
                PackageItem(selected = false, textOne = "EXTRA LARGE", textTwo = "10KG PLUS")

            }

            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White,
                ),
                onClick = {
                    navController.navigate(ParcelDestination.PARCEL_OPTION) {
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
fun PackageItem(selected: Boolean, textOne: String, textTwo: String) {
    Column(
        modifier = Modifier
            .widthIn(min = 80.dp, max = 120.dp)
            .height(150.dp)
            .background(
                color = if (selected) Color(0x57FCD313) else Color.Transparent,
                shape = RoundedCornerShape(14.dp)
            )
            .padding(horizontal = 4.dp, vertical = 15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = textOne,
            style = TextStyle(
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold
            )
        )

        ConstraintLayout(
            modifier = Modifier
                .widthIn(min = 80.dp, max = 120.dp)
                .height(80.dp)
                .background(
                    color = if (selected) Color(0xFFCCC9FC) else Color(0xFFFFF9DB),
                    shape = RoundedCornerShape(14.dp)
                )
                .border(width = 1.dp, color = Color(0xFFCCC9FC), shape = RoundedCornerShape(43.dp))
        ) {


            val (img, txt) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.parcel),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
                    .constrainAs(img) {
                        top.linkTo(parent.top, 10.dp)
                        end.linkTo(parent.end, 15.dp)
                    }
            )

            Text(
                text = textTwo,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier
                    .constrainAs(txt) {
                        bottom.linkTo(parent.bottom, 10.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }

    }
}