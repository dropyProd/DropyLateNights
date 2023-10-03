package com.example.dropy.ui.screens.dropymainmodel

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.R
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.commons.AddressDataClass
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation
import com.example.dropy.ui.components.textfield.sample.AutoCompleteObjectSample
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutViewModel
import com.example.dropy.ui.theme.DropyYellow
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DropyMainParcels(
    navController: NavController? = null,
    startLocale: LatLng? = null,
    checkoutViewModel: CheckoutViewModel,
    onPlaceNameChanged: (String) -> Unit,
    suggestedLocales: List<AddressDataClass>,
     mainParcelViewModel: MainParcelViewModel,
    latLng: LatLng? = LatLng(0.0, 0.0)
) {

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (map, colu, popup, head) = createRefs()

/*        dropyMainHeader(modifier = Modifier
            .constrainAs(head) {
                top.linkTo(parent.top)
            }
            .zIndex(.3f))*/


  /*      if (startLocale != null) {
            MapsScreen(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.75f)
                .constrainAs(map) {
                    top.linkTo(parent.top)
                }
                .zIndex(.2f),
                start = startLocale
            )
        }*/
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.7f)
                .constrainAs(map) {
                    top.linkTo(parent.top)
                }
                .zIndex(.2f)
        ){
            if (latLng != null){
                MapComponent(float = 1f) {
                    GoogleMapWrapper(
                        cameraPosition = latLng
                    ) {mapUiSettings,mapProperties,cameraPositionState ->

                        GoogleMapSelectLocation(
                            cameraPositionState = cameraPositionState,
                            mapUiSettings = mapUiSettings,
                            mapProperties = mapProperties,
                            locationSelected = {/* selectLocationViewModel.setUserLatLong(it)*/ },
                            markerPosition = latLng,
                            markerInfoWindowClicked = {/*selectLocationViewModel.deleteMarker()*/},
                            title = ""
                        )

                        //MapsScreen(start = start)
                    }
                }
            }
        }

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .constrainAs(colu) {
                    top.linkTo(map.bottom)
                }
        ) {

            val btn = createRef()

            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White,
                ),
                onClick = {
                    navController?.navigate(ParcelDestination.PARCEL_RECIEVE)
                },
                modifier = Modifier.constrainAs(btn) {
                    top.linkTo(parent.top, 150.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(
                    text = "CONTINUE",
                    style = TextStyle(fontWeight = FontWeight.ExtraBold, color = Color.White)
                )

            }
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(220.dp)
                .background(color = Color.White, shape = RoundedCornerShape(15.dp))
                .border(width = 1.dp, color = Color(0xFFCCC9FC), shape = RoundedCornerShape(15.dp))
                .padding(horizontal = 13.dp, vertical = 14.dp)
                .constrainAs(popup) {
                    top.linkTo(parent.top, 370.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .zIndex(.5f),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Text(
                text = "SEND A PACKAGE",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )



            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.range),
                    contentDescription = "",
                    modifier = Modifier
                        .width(40.dp)
                        .height(120.dp),
                    colorFilter = ColorFilter.tint(color = Color.Black)
                )
                Column(verticalArrangement = Arrangement.SpaceBetween) {


                    val scope = rememberCoroutineScope()

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
                            text = "PICK UP LOCATION",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                    }

                    AutoCompleteObjectSample(
                        locations = suggestedLocales,
                        value = mainParcelViewModel.pickup.value,
                        onValueChange = {
                            scope.launch {
                                try {

                                    mainParcelViewModel.onPickupChange(it)
                                    if (it.length > 0) {
                                        onPlaceNameChanged(it)
                                        delay(500)
                                        checkoutViewModel.appViewModel!!.navigate(
                                            ParcelDestination.PARCEL_HOME
                                        )
                                    }
                                } catch (e: Exception) {

                                }
                            }
                        },
                        onClear = { mainParcelViewModel.clearPickupChange() },
                        label = "Westlands Lane, RD 26",
                        showdelivery = false
                    )
                    /* TextField(
                         value = pickup.value,
                         onValueChange = {
                             pickup.value = it
                         },
                         modifier = Modifier.fillMaxWidth(),
                         colors = TextFieldDefaults.textFieldColors(
                             focusedIndicatorColor = Color(0xFF584AFF),
                             unfocusedIndicatorColor = Color.Transparent,
                             cursorColor = Color(0xFF584AFF),
                             focusedLabelColor = Color.LightGray,
                             unfocusedLabelColor = Color.LightGray.copy(.7f),
                             backgroundColor = Color.Transparent
                         ),
                         placeholder = {
                             Text(text = "Westlands Kivulini Lane, RD 26")
                         },
                         singleLine = true
                     )*/

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
                            text = "DESTINATION",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                    }
                    AutoCompleteObjectSample(
                        locations = suggestedLocales,
                        value = mainParcelViewModel.destination.value,
                        onValueChange = {
                            scope.launch {
                                try {

                                    mainParcelViewModel.onDestinationChange(it)
                                    if (it.length > 0) {
                                        onPlaceNameChanged(it)
                                        delay(500)
                                        checkoutViewModel.appViewModel!!.navigate(
                                            ParcelDestination.PARCEL_HOME
                                        )
                                    }
                                } catch (e: Exception) {

                                }
                            }
                        },
                        onClear = { mainParcelViewModel.clearDestinationChange() },
                        label = "Kisumu, Moi Avenue Street",
                        showdelivery = false
                    )
                   /* TextField(
                        value = destination.value,
                        onValueChange = {
                            destination.value = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color(0xFF584AFF),
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color(0xFF584AFF),
                            focusedLabelColor = Color.LightGray,
                            unfocusedLabelColor = Color.LightGray.copy(.7f),
                            backgroundColor = Color.Transparent
                        ),
                        placeholder = {
                            Text(text = "Kisumu, Moi Avenue Street")
                        },
                        singleLine = true,
                        trailingIcon = {

                        }
                    )*/

                }

            }


        }
    }
}

@Composable
fun dropyMainHeader(modifier: Modifier = Modifier, start: Int = 24) {
    Row(
        modifier = modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.White.copy(.4f),
                        Color.White.copy(.9f)
                    )
                )
            )
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
        ) {
            Row(
                // onClick = { /*onBackButtonClicked() */ },
                modifier = Modifier
                    .padding(start = start.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
                    .padding(4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Image(
                    imageVector = Icons.Filled.ChevronLeft,
                    contentDescription = "back button",
                    colorFilter = ColorFilter.tint(color = DropyYellow),
                    modifier = Modifier
                        .size(32.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .weight(2f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.dropylogo),
                contentDescription = "Dropy logo",
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )
        }
        Row(
            modifier = Modifier
                .padding(6.dp)
                .clip(CircleShape),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.imgone),
                contentDescription = "profile pic",
                modifier = Modifier
                    .size(56.dp)
                    .clickable {
                        /* onDashboardButtonClicked()*/
                    }
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

        }
    }
}


@Preview
@Composable
fun sampleScreen() {
    dropyMainHeader()
}