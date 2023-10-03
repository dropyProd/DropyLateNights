package com.example.dropy.ui.screens.rider.becomerider.riderLocation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation
import com.example.dropy.ui.screens.rider.becomerider.AddRiderUiState
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue
import com.google.android.gms.maps.model.LatLng

@Composable
fun AddRiderLocationContent(
    uiState: AddRiderUiState,
    openSearchPlaces: () -> Unit,
    onConfirmClicked: (String) -> Unit,
    onCancelClicked: (() -> Unit)? = null,
    showCancelButton: Boolean = false,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var openAutocompleteSuggestions by remember { mutableStateOf(false) }
        val context = LocalContext.current
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column {
                /*  Box(modifier = Modifier
                      .fillMaxHeight(0.6f)
                  ){

                  }*/
                MapComponent {
                    GoogleMapWrapper(
                        cameraPosition = uiState.riderLocation?.latitude?.let {
                            uiState.riderLocation?.longitude?.let { it1 ->
                                LatLng(
                                    it, it1
                                )
                            }
                        }
                    ) { mapUiSettings, mapProperties, cameraPositionState ->

                        GoogleMapSelectLocation(
                            cameraPositionState = cameraPositionState,
                            mapUiSettings = mapUiSettings,
                            mapProperties = mapProperties,
                            locationSelected = {/* selectLocationViewModel.setUserLatLong(it) */ },
                            markerPosition = uiState.riderLocation?.latitude?.let {
                                uiState.riderLocation?.longitude?.let { it1 ->
                                    LatLng(
                                        it, it1
                                    )
                                }
                            },
                            markerInfoWindowClicked = { /*selectLocationViewModel.deleteMarker()*/ }
                        )


                        //MapsScreen(start = start)
                    }
                }
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    SimpleText(
                        text = "Select your location",
                        textSize = 18,
                        isExtraBold = true,
                        verticalPadding = 8,
                        font = Font(R.font.axiformaextrabold)
                    )

                    Column(
                        modifier = Modifier
                            .padding(top = 9.dp)
                            .fillMaxWidth()
                            .clickable {
                                openSearchPlaces()
                            },
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        SimpleText(
                            text = uiState.riderLocation?.placeName
                                ?: "Click to Select location",
                            fontWeight = FontWeight.Normal, textSize = 14, horizontalPadding = 7,
                            font = Font(R.font.axiformaregular)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.LightGray)
                                .height(1.dp)
                        ) {

                        }
                    }
                    /*              AutoCompleteObjectSample(
                                      locations = suggestedLocales,
                                      value = selectLocationViewModel.addressname.value,
                                      onValueChange =onTextChange,
                                      onClear = clearText,
                                      label = "Westlands Lane, RD 26",
                                      showdelivery = false
                                  )*/
                    /*TextField(
                        value = uiState.value.selectedAddressName?:"",
                        onValueChange = {},
                        trailingIcon = {
                            Text(
                                text = "Edit",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .clickable { openAutocompleteSuggestions = true }
                            )
                        },
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent
                        ),
                        readOnly = true
                    )*/
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            TotallyRoundedButton(
                                buttonText = "confirm",
                                backgroundColor = DropyYellow,
                                action = {
                                    if (uiState.riderLocation?.placeName?.isEmpty() == false) {
                                        /*       if (uiState.value.address!!.userLatitude == null || uiState.value.address!!.userLongitude == null ){
                                                   Toast
                                                       .makeText(context,"Please long click on the map to mark your exact location",Toast.LENGTH_LONG)
                                                       .show()
                                               }*/
                                        onConfirmClicked(uiState.riderLocation?.placeName.toString())
                                    } else {
                                        Toast
                                            .makeText(
                                                context,
                                                "Please select a valid location",
                                                Toast.LENGTH_LONG
                                            )
                                            .show()
                                    }
                                },

                                )
                        }
                        if (showCancelButton) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                            ) {
                                if (onCancelClicked != null) {
                                    TotallyRoundedButton(
                                        buttonText = "cancel",
                                        backgroundColor = LightBlue,
                                        textColor = Color.White,
                                        action = { onCancelClicked() }
                                    )
                                }
                            }
                        }
                    }
                }
            }

            /*  if (openAutocompleteSuggestions){
                  Column(modifier = Modifier
                      .fillMaxSize()
                      .background(Color.White)
                      ,
                      horizontalAlignment = Alignment.CenterHorizontally
                  ){
                      GoogleMapsAutoComplete(
                          selectedLocation = {
                              selectLocationViewModel.geocoding(it)
                              openAutocompleteSuggestions = false
                          },
                          selectMyLocation = {},
                          closeAutocompleteDialog = { openAutocompleteSuggestions = false }
                      )
                  }
              }*/
        }
    }

}