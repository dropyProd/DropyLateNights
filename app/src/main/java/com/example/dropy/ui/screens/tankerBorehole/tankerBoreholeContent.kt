package com.example.dropy.ui.screens.tankerBorehole

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation
import com.example.dropy.ui.components.shops.frontside.dropdownRounded
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.screens.water.waterHome.WaterUiState
import com.google.android.gms.maps.model.LatLng

@Composable
fun TankerBoreholeContent(
    appViewModel: AppViewModel? = null,
    continueClicked: () -> Unit,
    onVolumeChanged: (String) -> Unit,
    onLocationChanged: () -> Unit,
    pickImage: () -> Unit,
    tankerBoreholeUiState: TankerBoreholeUiState,
    waterUiState: WaterUiState
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

//        val latLng: LatLng = LatLng(1.29281, 36.8219)

        MapComponent(float = 1f, modifier = Modifier.fillMaxSize()) {
            GoogleMapWrapper(
                cameraPosition = if (tankerBoreholeUiState.selectedAddress != null) LatLng(
                    tankerBoreholeUiState.selectedAddress.latitude,
                    tankerBoreholeUiState.selectedAddress.longitude
                ) else tankerBoreholeUiState.myAddress,
                cameraZoom = 14f,
                appViewModel = appViewModel
            ) { mapUiSettings, mapProperties, cameraPositionState ->

                GoogleMapSelectLocation(
                    cameraPositionState = cameraPositionState,
                    mapUiSettings = mapUiSettings,
                    mapProperties = mapProperties,
                    locationSelected = {/* selectLocationViewModel.setUserLatLong(it)*/ },
                    markerPosition = if (tankerBoreholeUiState.selectedAddress != null) LatLng(
                        tankerBoreholeUiState.selectedAddress.latitude,
                        tankerBoreholeUiState.selectedAddress.longitude
                    ) else tankerBoreholeUiState.myAddress,
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
 /*           ClippedHeader(title = if (waterUiState.selectedType.equals("CLEAN WATER")) "CLEAN WATER" else if (waterUiState.selectedType.equals(
                    "TREATED WATER"
                )
            ) " TREATED WATER" else "P", modifier = Modifier.offset(x = (-18).dp))*/

            Row(
                modifier = Modifier
                    .padding(top = 25.dp)
                    .widthIn(min = 150.dp, max = 400.dp )
                    .clip(RoundedCornerShape(bottomEnd = 20.dp))
                    .background(Color.White)
                    .clickable { onLocationChanged() }
                    .padding(top = 17.dp, bottom = 15.dp, start = 24.dp,end = 45.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (tankerBoreholeUiState.selectedAddress == null) "ENTER DELIVERY LOCATION" else tankerBoreholeUiState.selectedAddress.placeName,
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.axiformasemibold)),
                    letterSpacing = (-0.48).sp
                )

              /*  var mExpanded by remember { mutableStateOf(false) }
                var text by remember {
                    mutableStateOf("")
                }

                val list: MutableList<String> = mutableListOf(
                    "NOW", "LATER", "LATER"
                )

                if (!list.isEmpty())
                    text = list[0]
                else text = ""

                dropdownRounded(text = text!!.toUpperCase(), clicked = {
                    mExpanded = true
                }, color = Color.White, bordercolor = Color.Black, contentColor = Color.Black)
                DropdownMenu(
                    expanded = mExpanded,
                    onDismissRequest = { mExpanded = false },
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(200.dp)
                    *//*   .verticalScroll(rememberScrollState())*//*
                ) {
                    list.forEach { label ->
                        DropdownMenuItem(
                            onClick = {
                                text = label
                                mExpanded = false
                            }, modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                        ) {
                            label.let {
                                androidx.compose.material3.Text(
                                    text = (it).toUpperCase(),
                                    fontSize = 9.sp,
                                    modifier = Modifier.padding(bottom = 7.dp),
                                    fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                                    fontWeight = FontWeight.Black
                                )
                            }
                        }
                    }
                }*/

            }

         /*   Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 24.dp, top = 15.dp)
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .background(Color.Black, RoundedCornerShape(13.dp))
                        .clickable { pickImage() }
                        .padding(horizontal = 7.dp, vertical = 3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = "",
                        modifier = Modifier
                            .width(11.dp)
                            .height(9.dp),
                        tint = Color.White
                    )

                    Text(
                        text = "HOME",
                        color = Color.White,
                        fontSize = 9.sp,
//                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.axiformablack)),
                        letterSpacing = (-0.48).sp
                    )

                }
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .background(Color.Black, RoundedCornerShape(13.dp))
                        .padding(horizontal = 7.dp, vertical = 3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.PinDrop,
                        contentDescription = "",
                        modifier = Modifier
                            .width(11.dp)
                            .height(9.dp),
                        tint = Color.White
                    )

                    Text(
                        text = "WORK",
                        color = Color.White,
                        fontSize = 9.sp,
//                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.axiformablack)),
                        letterSpacing = (-0.48).sp
                    )

                }
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .background(Color.Black, RoundedCornerShape(13.dp))
                        .padding(horizontal = 7.dp, vertical = 3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.PinDrop,
                        contentDescription = "",
                        modifier = Modifier
                            .width(11.dp)
                            .height(9.dp),
                        tint = Color.White
                    )

                    Text(
                        text = "MAMA",
                        color = Color.White,
                        fontSize = 9.sp,
//                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.axiformablack)),
                        letterSpacing = (-0.48).sp
                    )

                }
            }*/

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.4f)
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .fillMaxWidth()
                    .height(288.dp)
                    .background(color = Color(0xFFFFFFFF), RoundedCornerShape(25.dp))
                    .border(width = 1.dp, color = Color(0xFFDEDEDE), RoundedCornerShape(25.dp))
                    .padding(start = 24.dp, top = 26.dp)
            ) {
                Text(
                    text = "How much water do you need?",
                    color = Color.Black,
                    fontSize = 12.sp,
//                        fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.axiformablack)),
                    letterSpacing = (-0.48).sp
                )


                Text(
                    text = "WATER VOLUME IN LITERS",
                    color = Color.Black,
                    fontSize = 9.sp,
//                        fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                    letterSpacing = (-0.48).sp,
                    modifier = Modifier
                        .padding(top = 45.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 6.dp)
                        .width(186.dp)
                        .wrapContentHeight()
                        .background(color = Color.White, RoundedCornerShape(8.dp))
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFFDEDEDE)
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextField(
                        value = tankerBoreholeUiState.volume,
                        onValueChange = onVolumeChanged,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            cursorColor = Color.Black
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 30.sp,
//                        fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                            letterSpacing = (-1.44).sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 46.dp)
                        .width(133.dp)
                        .height(36.dp)
                        .background(color = Color(0xFF02CBE3), RoundedCornerShape(24.dp))
                        .border(width = 1.dp, color = Color(0x57707070), RoundedCornerShape(24.dp))
                        .clickable { continueClicked() },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "CONTINUE",
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
}

@Preview
@Composable
fun tankerPreview() {
    TankerBoreholeContent(
        continueClicked = {},
        tankerBoreholeUiState = TankerBoreholeUiState(),
        onLocationChanged = {},
        onVolumeChanged = {}, pickImage = {}, waterUiState = WaterUiState())
}