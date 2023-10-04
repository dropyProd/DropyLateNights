package com.example.dropy.ui.screens.waterTracking

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.app.AppUiState
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.CustLatLng
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation
import com.example.dropy.ui.components.shops.frontside.dropdownRounded
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.screens.locale.BackgroundedImage
import com.example.dropy.ui.screens.waterMyOrder.WaterMyOrderUiState

@Composable
fun WaterTrackingContent(
    appViewModel: AppViewModel? = null,
    navigate: (code:String, taskId: String) -> Unit,
    waterTrackingUiState: WaterTrackingUiState,
    waterMyOrderUiState: WaterMyOrderUiState,
    appUiState: AppUiState
) {
    Box(modifier = Modifier.fillMaxSize()) {

        MapComponent(float = 1f, modifier = Modifier.fillMaxSize()) {
            GoogleMapWrapper(
                cameraPosition = waterTrackingUiState.path[0],
                appViewModel = appViewModel,
                cameraZoom = 14f
            ) { mapUiSettings, mapProperties, cameraPositionState ->

                GoogleMapSelectLocation(
                    cameraPositionState = cameraPositionState,
                    mapUiSettings = mapUiSettings,
                    mapProperties = mapProperties,
                    title = "",
                    snippet = "",
                    truckRouteMarkerInfoWindowClicked = {},
                    trucksRouteMarkerPositionList = listOf(
                        CustLatLng(name = if (waterMyOrderUiState.selectedTruck != null) waterMyOrderUiState.selectedTruck?.license_plate.toString() else waterMyOrderUiState.selectedTruckO?.license_plate.toString(), waterTrackingUiState.path[0]),
                        CustLatLng(name = appUiState.activeProfile?.name.toString(), waterTrackingUiState.path[(waterTrackingUiState.path.size - 1)])
                    ),
                    polylines = waterTrackingUiState.path
                )

                //MapsScreen(start = start)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = Color.White)
                    .padding(bottom = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    ClippedHeader(
                        title = "TRACK YOUR ORDER",
                        modifier = Modifier.padding(top = 22.dp)
                    )

                    Row() {
                        Text(
                            text = "ORDER",
                            fontSize = 14.sp,
//                        fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformaheavy)
                            ),
                            letterSpacing = (-0.67).sp,
                            lineHeight = 27.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 23.dp, top = 31.dp)
                        )

                        var mExpanded by remember { mutableStateOf(false) }
                        var text by remember {
                            mutableStateOf("")
                        }


                        val list: MutableList<String> = mutableListOf(

                        )

                        if (!list.isEmpty())
                            text = list[0]
                        else text = ""

                        dropdownRounded(
                            text = "#${if (waterMyOrderUiState.createIndividualWaterOrderRes != null) waterMyOrderUiState.createIndividualWaterOrderRes.tracking_id else waterMyOrderUiState.getIndividualOrdersResItem?.tracking_id.toString()}",
                            clicked = {
                                mExpanded = true
                            },
                            color = Color(0xFFC2F8FF),
                            bordercolor = Color.Transparent,
                            contentColor = Color.Black,
                            spacearound = 12,
                            modifier = Modifier.padding(start = 8.dp, top = 27.dp)
                        )
                        DropdownMenu(
                            expanded = mExpanded,
                            onDismissRequest = { mExpanded = false },
                            modifier = Modifier
                                .wrapContentWidth()
                                .height(200.dp)
                            /*   .verticalScroll(rememberScrollState())*/
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
                                        Text(
                                            text = (it).toUpperCase(),
                                            fontSize = 9.sp,
                                            modifier = Modifier.padding(bottom = 7.dp),
                                            fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                                            fontWeight = FontWeight.Black
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Column() {
                    Text(
                        text = "ETA",
                        fontSize = 23.sp,
//                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(
                            Font(R.font.axiformablack)
                        ),
                        letterSpacing = (-1.1).sp,
                        lineHeight = 28.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(end = 9.dp, top = 27.dp)
                    )

                    Row(
                        modifier = Modifier
                            .padding(top = 6.dp, end = 9.dp)
                            .width(88.dp)
                            .height(27.dp)
                            .background(
                                color = Color.Black,
                                shape = RoundedCornerShape(11.dp)
                            )
                        /*.border(
                            width = 1.dp,
                            color = Color(0xFFD1D1D1),
                            shape = RoundedCornerShape(7.dp)
                        )*/
                    ) {
                        Text(
                            text = /*"72 MINUTES"*/waterTrackingUiState.duration,
                            fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformaextrabold)
                            ),
                            letterSpacing = (-0.48).sp,
                            lineHeight = 13.sp,
                            color = Color.White,
                            modifier = Modifier.padding(
                                start = 16.dp,
                                end = 11.dp,
                                top = 7.dp,
                                bottom = 7.dp
                            )
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding(top = 240.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column() {
                        Row(
                            modifier = Modifier
                                .padding(top = 14.dp, start = 28.dp)
                                .width(68.dp)
                                .height(18.dp)
                                .background(
                                    color = Color(0xFF02CBE3),
                                    shape = RoundedCornerShape(12.dp)
                                )
                            /*.border(
                                width = 1.dp,
                                color = Color(0xFFD1D1D1),
                                shape = RoundedCornerShape(7.dp)
                            )*/
                        ) {
                            Text(
                                text = if (waterMyOrderUiState.getIndividualOrdersResItem != null) {
                                    if (waterMyOrderUiState.getIndividualOrdersResItem.delivery_status.equals(
                                            "P"
                                        )
                                    ) "NOT STARTED" else if (waterMyOrderUiState.getIndividualOrdersResItem.delivery_status.equals(
                                            "S"
                                        )
                                    ) "ON THE WAY" else "DELIVERED"
                                } else {
                                    if (waterMyOrderUiState.createIndividualWaterOrderRes?.delivery_status.equals(
                                            "P"
                                        )
                                    ) "NOT STARTED" else if (waterMyOrderUiState.createIndividualWaterOrderRes?.delivery_status.equals(
                                            "S"
                                        )
                                    ) "ON THE WAY" else "DELIVERED"
                                },
                                fontSize = 8.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily(
                                    Font(R.font.axiformaextrabold)
                                ),
                                letterSpacing = (-0.38).sp,
                                lineHeight = 15.sp,
                                color = Color.White,
                                modifier = Modifier.padding(
                                    start = 10.dp,
                                    end = 2.dp,
                                    top = 6.dp,
                                )
                            )
                        }

                        Text(
                            text = "Remaining Time",
                            fontSize = 12.sp,
//                        fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformabold)
                            ),
                            letterSpacing = (-0.58).sp,
                            lineHeight = 22.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 31.dp, top = 19.dp)
                        )

                    }

                    Text(
                        text = /*"1 h 12 min"*/waterTrackingUiState.duration,
                        fontSize = 19.sp,
//                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(
                            Font(R.font.axiformaheavy)
                        ),
                        letterSpacing = (-0.91).sp,
                        lineHeight = 37.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(end = 8.dp, top = 44.dp)
                    )

                }

                truckPerson(
                    modifier = Modifier.padding(top = 31.dp, start = 8.dp, end = 8.dp),
                    navigate = navigate,
                    waterMyOrderUiState = waterMyOrderUiState
                )
            }
        }
    }
}

@Composable
fun truckPerson(
    modifier: Modifier = Modifier,
    navigate: (code:String, taskId: String) -> Unit,
    waterMyOrderUiState: WaterMyOrderUiState
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(68.dp)
            .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(40.dp))
            .border(width = 1.dp, color = Color(0xFFDEDEDE), shape = RoundedCornerShape(40.dp))
            .clickable {
                if (waterMyOrderUiState.createIndividualWaterOrderRes != null) {
                    waterMyOrderUiState.createIndividualWaterOrderRes.tasks.forEach {
                        if (waterMyOrderUiState.selectedTruck != null) {
                            if (it.truck.id.equals( waterMyOrderUiState.selectedTruck.id)) {
                                if (!it.id.equals("")){
                                    navigate(it.four_digit_code.toString(),it.id)
                                }
                            }
                        }
                    }
                }
                if (waterMyOrderUiState.getIndividualOrdersResItem != null) {
                    waterMyOrderUiState.getIndividualOrdersResItem.tasks.forEach {
                        if (waterMyOrderUiState.selectedTruckO != null) {
                            if (it.truck.id.equals( waterMyOrderUiState.selectedTruckO.id)) {
                                if (!it.id.equals("")){
                                    navigate(it.four_digit_code.toString(),it.id)
                                }
                            }
                        }
                    }
                }
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row() {
            LoadImage(
                if (waterMyOrderUiState.selectedTruck != null) waterMyOrderUiState.selectedTruck?.image.toString() else waterMyOrderUiState.selectedTruckO?.image.toString(),
                modifier = Modifier
                    .padding(top = 11.dp, start = 17.dp)
                    .size(42.dp)
                    .clip(
                        CircleShape
                    ),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(top = 16.dp, start = 20.dp)) {
                Text(
                    text = if (waterMyOrderUiState.selectedTruck != null) waterMyOrderUiState.selectedTruck?.license_plate.toString() else waterMyOrderUiState.selectedTruckO?.license_plate.toString(),
                    fontSize = 12.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    ),
                    letterSpacing = (-0.58).sp,
                    lineHeight = 22.sp,
                    color = Color.Black
                )
                Text(
                    text = "Truck Driver",
                    fontSize = 12.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformasemibold)
                    ),
                    letterSpacing = (-0.58).sp,
                    lineHeight = 22.sp,
                    color = Color.Black
                )


            }

        }
        Row() {
            BackgroundedImage(
                background = Color(0xFF1BFC13),
                image = R.drawable.phone,
                modifier = Modifier.padding(top = 13.dp)
            )
            BackgroundedImage(
                background = Color(0xFFFCD313),
                image = R.drawable.email,
                modifier = Modifier.padding(top = 13.dp, start = 12.dp, end = 15.dp)
            )
        }

    }
}

@Preview
@Composable
fun demo() {
    WaterTrackingContent(
        navigate = {_,_ ->},
        waterTrackingUiState = WaterTrackingUiState(),
        waterMyOrderUiState = WaterMyOrderUiState(),
        appUiState = AppUiState()
    )
//    truckPerson()
}