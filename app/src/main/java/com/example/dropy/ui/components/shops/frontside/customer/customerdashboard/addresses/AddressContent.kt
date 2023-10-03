package com.example.dropy.ui.components.shops.frontside.customer.customerdashboard.addresses

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation
import com.example.dropy.ui.screens.shops.frontside.dashboard.addresses.singleaddress.SingleAddressUiState
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue
import com.google.android.gms.maps.model.LatLng

@Composable
fun AddressContent(
    uiState: SingleAddressUiState,
    onAddressChanged: (String) -> Unit,
    onLocationChanged: (String) -> Unit,
    onFloorChanged: (String) -> Unit,
    onAdditionalInformationChanged: (String) -> Unit,
    onLocationTagChanged: (String) -> Unit,
    startLocale: LatLng? = null,
    updateLocationTag: () -> Unit,
    onChangeLocationClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Box(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
        ) {

                MapComponent(float = 1f) {
                    GoogleMapWrapper(
                        cameraPosition = startLocale
                    ) { mapUiSettings, mapProperties, cameraPositionState ->

                        GoogleMapSelectLocation(
                            cameraPositionState = cameraPositionState,
                            mapUiSettings = mapUiSettings,
                            mapProperties = mapProperties,
                            locationSelected = {/* selectLocationViewModel.setUserLatLong(it)*/ },
                            markerPosition = startLocale,
                            markerInfoWindowClicked = {/*selectLocationViewModel.deleteMarker()*/ },
                            title = uiState.placeName
                        )

                        //MapsScreen(start = start)
                    }
                }


        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SimpleText(
                        text = "Enter Delivery Address",
                        isExtraBold = true,
                        fontWeight = FontWeight.ExtraBold,
                        textSize = 14
                    )
                    SimpleText(
                        text = "gps enabled location",
                        isUppercase = true,
                        fontWeight = FontWeight.Light,
                        textSize = 9
                    )
                }
                TextField(
                    value = if (uiState.yourAddress != null) {
                        if (!uiState.yourAddress.placeName.equals("Place name")) {
                            uiState.yourAddress.placeName
                        } else ""
                    } else "",
                    onValueChange = onAddressChanged,
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        unfocusedIndicatorColor = Color.LightGray,
                        backgroundColor = Color.Transparent
                    ), readOnly = true,
                    trailingIcon = {

                        Box(
                            modifier = Modifier
                                .clickable { onChangeLocationClicked() }

                        ) {
                            StyledText(
                                backgroundColor = LightBlue,
                                textColor = Color.White,
                                textSize = 9,
                                text = "change",
                                isUppercase = true
                            )
                        }
                    }
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SimpleText(
                        text = "Your Location",
                        isExtraBold = true,
                        fontWeight = FontWeight.ExtraBold,
                        textSize = 14
                    )
                    SimpleText(
                        text = "HOUSE NUMBER / APARTMENT NAME / BUILDING NAME",
                        isUppercase = true,
                        fontWeight = FontWeight.Light,
                        textSize = 9
                    )
                }
                TextField(
                    value = /*uiState.region*/ if (uiState.yourAddress != null) {
                        uiState.yourAddress.buildingName
                    } else "",
                    onValueChange = onLocationChanged,
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        unfocusedIndicatorColor = Color.LightGray,
                        backgroundColor = Color.Transparent
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SimpleText(
                        text = "Floor",
                        isExtraBold = true,
                        fontWeight = FontWeight.ExtraBold,
                        textSize = 14
                    )
                    SimpleText(
                        text = "FLOOR NUMBER (OPTIONAL)",
                        isUppercase = true,
                        fontWeight = FontWeight.Light,
                        textSize = 9
                    )
                }
                TextField(
                    value = if (uiState.yourAddress != null) {
                        uiState.yourAddress.floorNumber
                    }else "",
                    onValueChange = onFloorChanged,
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        unfocusedIndicatorColor = Color.LightGray,
                        backgroundColor = Color.Transparent
                    ),
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                val other = remember {
                    mutableStateOf(false)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SimpleText(
                        text = "Tag location for later",
                        isExtraBold = true,
                        fontWeight = FontWeight.ExtraBold,
                        textSize = 14
                    )
                }
                val locationTags = uiState.locationTagList
                val selectedTag = remember { mutableStateOf(0) }
                if (other.value) {
                    TextField(
                        value = if (!uiState.locationTag.equals("OTHER")) {
                            uiState.locationTag ?: ""
                        } else {
                            ""
                        },
                        onValueChange = { onLocationTagChanged(it) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            unfocusedIndicatorColor = Color.LightGray,
                            backgroundColor = Color.Transparent
                        ),
                    )
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    locationTags.forEachIndexed { index, tag ->
                        Box(
                            modifier = Modifier
                                .clickable {
                                    selectedTag.value = index
                                    if (tag.locationTag.equals("OTHER")) {
                                        other.value = true
                                    }
                                    tag.customTag?.let { onLocationTagChanged(it) }
                                }
                        ) {
                            tag.customTag?.let {
                                StyledText(
                                    backgroundColor = if (selectedTag.value == index) {
                                        Color(88, 74, 255)
                                    } else {
                                        Color.Transparent
                                    },
                                    textColor = if (selectedTag.value == index) {
                                        Color.White
                                    } else {
                                        Color.Black
                                    },
                                    textSize = 9,
                                    text = it,
                                    isUppercase = true
                                )
                            }
                        }
                    }
                }
            }
        }

        val context = LocalContext.current
        Box(
            /*      modifier = Modifier
                      .padding(vertical = 8.dp)
                     *//* .fillMaxWidth()*//*,*/
            contentAlignment = Alignment.Center
        ) {
            TotallyRoundedButton(
                buttonText = "UPDATE",
                backgroundColor = Color.Black,
                textColor = Color.White,
                action = {
                    if (!uiState.locationTag.equals("OTHER")) {
                        updateLocationTag()
                    } else {
                        Toast.makeText(context, "location tag is empty", Toast.LENGTH_SHORT).show()
                    }
                },
                widthFraction = 0.6,
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddressContentPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AddressContent(
            uiState = SingleAddressUiState(),
            onAdditionalInformationChanged = {},
            onLocationChanged = {},
            onFloorChanged = {},
            onAddressChanged = {},
            onLocationTagChanged = {},
            updateLocationTag = {},
            onChangeLocationClicked = {}
        )
    }
}