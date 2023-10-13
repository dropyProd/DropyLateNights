package com.example.dropy.ui.screens.addWaterTruck

import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.R
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.components.commons.maps.AddressDataClass
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.autocopletepredictions.GoogleMapsAutoComplete
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation
import com.example.dropy.ui.components.commons.maps.selectlocation.SelectLocationViewModel
import com.example.dropy.ui.components.textfield.sample.AutoCompleteObjectSample
import com.example.dropy.ui.screens.myTruckEditDetails.MyTruckEditDetailsViewModel
import com.example.dropy.ui.screens.shops.backside.addshop.AddShopViewModel
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue
import com.google.android.gms.maps.model.LatLng
import io.andronicus.buupass.ui.home.textfield.autocomplete.AutoCompleteBox
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AddWatertruckLocationContent(
    selectLocationViewModel: SelectLocationViewModel,
    //onConfirmClicked:(AddressDataClass)->Unit,
    onConfirmClicked: (String) -> Unit,
    onCancelClicked: (() -> Unit)? = null,
    showCancelButton: Boolean = false,
    start: LatLng,
    clearText: () -> Unit,
    onTextChange: (String) -> Unit,
    suggestedLocales: List<com.example.dropy.ui.components.commons.AddressDataClass>,
    addWaterTruckViewmodel: AddWaterTruckViewmodel,
    openSearchPlaces: () -> Unit,
    myTruckEditDetailsViewModel: MyTruckEditDetailsViewModel
) {
//    val uiState = selectLocationViewModel.selectLocationUiState.collectAsState()
    val locationuiState by addWaterTruckViewmodel.addWaterTruckLocationUiState.collectAsState()
    val myTruckEditDetailsUiState by myTruckEditDetailsViewModel.myTruckEditDetailsUiState.collectAsState()
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
                        cameraPosition = if (locationuiState.state.equals("EditTruck")) {
                            if (locationuiState.shopAddress != null) {
                                locationuiState.shopAddress?.latitude?.let {
                                    locationuiState.shopAddress?.longitude?.let { it1 ->
                                        LatLng(
                                            it, it1
                                        )
                                    }
                                }
                            } else {
                                LatLng(
                                    myTruckEditDetailsUiState.selectedTruck?.registered_latitude.toString().toDouble(),
                                    myTruckEditDetailsUiState.selectedTruck?.registered_longitude.toString().toDouble()
                                )
                            }
                        }else {
                            locationuiState.shopAddress?.latitude?.let {
                                locationuiState.shopAddress?.longitude?.let { it1 ->
                                    LatLng(
                                        it, it1
                                    )
                                }
                            }
                        }
                    ) { mapUiSettings, mapProperties, cameraPositionState ->

                        GoogleMapSelectLocation(
                            cameraPositionState = cameraPositionState,
                            mapUiSettings = mapUiSettings,
                            mapProperties = mapProperties,
                            locationSelected = { selectLocationViewModel.setUserLatLong(it) },
                            markerPosition = if (locationuiState.state.equals("EditTruck")) {
                                if (locationuiState.shopAddress != null) {
                                    locationuiState.shopAddress?.latitude?.let {
                                        locationuiState.shopAddress?.longitude?.let { it1 ->
                                            LatLng(
                                                it, it1
                                            )
                                        }
                                    }
                                } else {
                                    LatLng(
                                        myTruckEditDetailsUiState.selectedTruck?.registered_latitude.toString().toDouble(),
                                        myTruckEditDetailsUiState.selectedTruck?.registered_longitude.toString().toDouble()
                                    )
                                }
                            }else {
                                locationuiState.shopAddress?.latitude?.let {
                                    locationuiState.shopAddress?.longitude?.let { it1 ->
                                        LatLng(
                                            it, it1
                                        )
                                    }
                                }
                            },
                            markerInfoWindowClicked = { selectLocationViewModel.deleteMarker() }
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
                        text = "Select your operating location",
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
                            text = locationuiState.shopAddress?.placeName
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
                                textColor = Color.White,
                                buttonText = "confirm",
                                backgroundColor = Color.Black,
                                action = {
                                    // if (locationuiState.shopAddress?.placeName?.isEmpty() == true) {
                                    /*       if (uiState.value.address!!.userLatitude == null || uiState.value.address!!.userLongitude == null ){
                                               Toast
                                                   .makeText(context,"Please long click on the map to mark your exact location",Toast.LENGTH_LONG)
                                                   .show()
                                           }*/
                                    onConfirmClicked(locationuiState.shopAddress?.placeName.toString())
                                    /*  } else {
                                          Toast
                                              .makeText(
                                                  context,
                                                  "Please select a valid location",
                                                  Toast.LENGTH_LONG
                                              )
                                              .show()
                                      }*/
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

@Preview(showBackground = true)
@Composable
fun SelectLocationPreview() {
    Column(Modifier.fillMaxSize()) {
        AddWatertruckLocationContent(
            onConfirmClicked = {},
            start = LatLng(0.0, 0.0),
            clearText = { /*TODO*/ },
            onTextChange = {

            },
            suggestedLocales = listOf(),
            selectLocationViewModel = SelectLocationViewModel(),
            openSearchPlaces = {},
            addWaterTruckViewmodel = hiltViewModel(),
            myTruckEditDetailsViewModel = hiltViewModel()
        )
    }
}