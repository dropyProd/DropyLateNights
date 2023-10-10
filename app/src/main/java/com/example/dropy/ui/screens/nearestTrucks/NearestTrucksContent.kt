package com.example.dropy.ui.screens.nearestTrucks

import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeUiState


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.R
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation
import com.google.android.gms.maps.model.LatLng

@Composable
fun NearestTruckContent(
    appViewModel: AppViewModel? = null,
    navigate: () -> Unit,
    truckClicked: (com.example.dropy.network.models.createIndividualWaterOrder.AssignedTruck) -> Unit,
    tankerBoreholeUiState: TankerBoreholeUiState,
    nearestTrucksUiState: NearestTrucksUiState
) {

    Box(modifier = Modifier.fillMaxSize()) {
//        val latLng: LatLng = LatLng(1.29281, 36.8219)

        MapComponent(float = 1f, modifier = Modifier.fillMaxSize()) {
            GoogleMapWrapper(
                cameraPosition = LatLng(
                    nearestTrucksUiState.selectedTruck?.current_latitude.toString().toDouble(),
                    nearestTrucksUiState.selectedTruck?.current_longitude.toString().toDouble()
                ),
                appViewModel = appViewModel
            ) { mapUiSettings, mapProperties, cameraPositionState ->

                GoogleMapSelectLocation(
                    cameraPositionState = cameraPositionState,
                    mapUiSettings = mapUiSettings,
                    mapProperties = mapProperties,
                    title = "",
                    snippet = "",
                    truckMarkerInfoWindowClicked = {truckClicked(it)},
                    trucksMarkerPositionList = tankerBoreholeUiState.createIndividualWaterOrderRes?.assigned_trucks!!
//                    polylines = if (shopHomePageUiState.polylines.isNotEmpty()) shopHomePageUiState.polylines else trackOrderUistate.polylines
                )

                //MapsScreen(start = start)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent)
        ) {
            BackgroundedText(
                background = Color.Black,
                textColor = Color.White,
                text = "NEARBY WATER TRUCKS",
                vertical = 4,
                horizontal = 10,
                textSize = 8,
                font = Font(R.font.axiformablack),
                modifier = Modifier.padding(top = 39.dp, start = 18.dp)
            )

            LazyRow(content = {
                itemsIndexed(tankerBoreholeUiState.createIndividualWaterOrderRes?.assigned_trucks!!) { index, item ->
                    truckItem(assignedTruck = item, navigate = truckClicked)
                }
            })

        }
    }
}


@Composable
fun truckItem(
    assignedTruck: com.example.dropy.network.models.createIndividualWaterOrder.AssignedTruck,
    navigate: (com.example.dropy.network.models.createIndividualWaterOrder.AssignedTruck) -> Unit
) {
    Box {
        Row(modifier = Modifier
            .padding(start = 10.dp, end = 13.dp, top = 440.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = Color(0xFFDEDEDE),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { navigate(assignedTruck) }
        ) {

            LoadImage(
                imageUrl = if (assignedTruck.image != null) assignedTruck.image.toString() else assignedTruc.image.toString(),
                modifier = Modifier
                    .padding(top = 11.dp, start = 17.dp)
                    .border(width = 1.dp, color = Color(0xFFDEDEDE), shape = CircleShape)
                    .size(96.dp)
                    .clip(
                        CircleShape
                    ),
                contentScale = ContentScale.Crop
            )

            Column() {
                Text(
                    text = "TIMOTHY KAMAU",
                    fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    ),
                    letterSpacing = (-0.48).sp,
                    lineHeight = 19.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 34.dp, start = 26.dp)
                )

                Text(
                    text = assignedTruck.license_plate.toUpperCase(),
                    fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    ),
                    letterSpacing = (-0.48).sp,
                    lineHeight = 19.sp,
                    color = Color(0xFF02CBE3),
                    modifier = Modifier.padding(top = 8.dp, start = 26.dp)
                )

                Text(
                    text = "213 TRIPS",
                    fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    ),
                    letterSpacing = (-0.48).sp,
                    lineHeight = 19.sp,
                    color = Color(0xFF02CBE3),
                    modifier = Modifier.padding(top = 8.dp, start = 26.dp)
                )
                Text(
                    text = "15,456 km travelled",
                    fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformamediumitalic)
                    ),
                    letterSpacing = (-0.48).sp,
                    lineHeight = 18.sp,
                    color = Color(0xFF74728A),
                    modifier = Modifier.padding(top = 8.dp, start = 26.dp)
                )

            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                /*Row(
                    modifier = Modifier.padding(end = 5.dp, top = 34.dp)
                ) {
                    BackgroundedImageText(
                        background = Color(0xFFC2F8FF),
                        textColor = Color.Black,
                        text = "65 min",
                        image = R.drawable.clock,
                        horizontal = 7,
                        vertical = 3,
                    )
                }*/

                BackgroundedText(
                    background = Color.Transparent,
                    borderColor = Color.Black,
                    textColor = Color.Black,
                    text = assignedTruck.license_plate.toUpperCase(),
                    horizontal = 12,
                    vertical = 7,
                    modifier = Modifier.padding(end = 5.dp, top = 34.dp)
                )

                Text(
                    text = "${assignedTruck.capacity}LT",
                    fontSize = 17.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformaheavy)
                    ),
                    letterSpacing = (-0.67).sp,
                    lineHeight = 27.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(end = 5.dp, top = 10.dp, bottom = 16.dp)
                )


            }
        }
        Selected(modifier = Modifier.padding(start = 26.dp, top = 313.dp))
    }
}

@Composable
fun Selected(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(10.dp))
            .border(width = 1.dp, color = Color(0xFFDEDEDE), shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 10.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier
                .size(7.dp)
                .background(color = Color(0xFF25E900), shape = CircleShape)
                .border(width = 1.dp, color = Color(0xFF707070), shape = CircleShape)
        ) {

        }

        Text(
            text = "SELECTED",
            style = TextStyle(
                color = Color.Black,
                fontSize = 9.sp,
//                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.axiformaextrabold)),
                lineHeight = 17.sp,
                letterSpacing = (-0.43).sp
            )
        )
    }
}

@Preview
@Composable
fun demo() {
    NearestTruckContent(
        navigate = {},
        tankerBoreholeUiState = TankerBoreholeUiState(),
        truckClicked = {},
        nearestTrucksUiState = NearestTrucksUiState()
    )
}