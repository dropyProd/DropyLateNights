package com.example.dropy.ui.screens.nearestWaterPoint

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.R
import com.example.dropy.network.models.getWaterPoints.GetWaterPointsResItem
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation
import com.example.dropy.ui.components.order.BackgroundedImageText
import com.example.dropy.ui.screens.truckIncomingWork.TruckIncomingWorkUiState
import com.google.android.gms.maps.model.LatLng

@Composable
fun NearestWaterPointContent(
    appViewModel: AppViewModel? = null,
    navigate: () -> Unit,
    waterpointClicked: (GetWaterPointsResItem) -> Unit,
    truckIncomingWorkUiState: TruckIncomingWorkUiState,
    nearestWaterPointUiState: NearestWaterPointUiState
) {

    Box(modifier = Modifier.fillMaxSize()) {
//        val latLng: LatLng = LatLng(1.29281, 36.8219)

        MapComponent(float = 1f, modifier = Modifier.fillMaxSize()) {
            GoogleMapWrapper(
                cameraPosition = LatLng(
                    nearestWaterPointUiState.selectedWaterPoint?.latitude.toString().toDouble(),
                    nearestWaterPointUiState.selectedWaterPoint?.longitude.toString().toDouble()
                ),
                appViewModel = appViewModel
            ) { mapUiSettings, mapProperties, cameraPositionState ->

                GoogleMapSelectLocation(
                    cameraPositionState = cameraPositionState,
                    mapUiSettings = mapUiSettings,
                    mapProperties = mapProperties,
                    locationSelected = {/* selectLocationViewModel.setUserLatLong(it)*/ },
                    markerInfoWindowClicked = {/*selectLocationViewModel.deleteMarker()*/ },
                    title = "",
                    snippet = "",
                    waterpointMarkerInfoWindowClicked = waterpointClicked,
                    waterpointsMarkerPositionList = nearestWaterPointUiState.allWaterPoints
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
                text = "NEARBY WATER POINTS",
                vertical = 4,
                horizontal = 10,
                textSize = 8,
                font = Font(R.font.axiformablack),
                modifier = Modifier.padding(top = 39.dp, start = 18.dp)
            )


            Box {
                Row(modifier = Modifier
                    .padding(start = 10.dp, end = 13.dp, top = 320.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(12.dp))
                    .border(
                        width = 1.dp,
                        color = Color(0xFFDEDEDE),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable { navigate() }
                ) {

                    LoadImage(
                        imageUrl = nearestWaterPointUiState.selectedWaterPoint?.logo,
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
                            text = nearestWaterPointUiState.selectedWaterPoint?.name.toString(),
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
                            text = nearestWaterPointUiState.selectedWaterPoint?.street.toString(),
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
                            text = "5 mins away.",
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

                        Row() {

                            Row(
                                modifier = Modifier
                                    .padding(start = 23.dp, top = 7.dp)
                                    .width(17.dp)
                                    .height(17.dp)
                                    .background(
                                        color = Color(0xFFAFF5FE),
                                        shape = CircleShape
                                    )
                                    .border(
                                        width = 1.dp,
                                        color = Color.Black,
                                        shape = CircleShape
                                    )
                            ) {
                                Text(
                                    text = "6",
                                    fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                    fontFamily = FontFamily(
                                        Font(R.font.axiformaheavy)
                                    ),
                                    letterSpacing = (-0.48).sp,
                                    lineHeight = 19.sp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(
                                        start = 5.dp,
                                        end = 5.dp,
                                        top = 4.dp,
                                        bottom = 3.dp
                                    )
                                )
                            }
                            Text(
                                text = "trucks in queue.",
                                fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily(
                                    Font(R.font.axiformamediumitalic)
                                ),
                                letterSpacing = (-0.48).sp,
                                lineHeight = 18.sp,
                                color = Color(0xFF74728A),
                                modifier = Modifier.padding(top = 11.dp, start = 5.dp)
                            )

                        }
                    }

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Row(
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
                        }


                        Text(
                            text = "${truckIncomingWorkUiState.truckDetails?.capacity} LT",
                            fontSize = 17.sp,
//                        fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformaheavy)
                            ),
                            letterSpacing = (-0.67).sp,
                            lineHeight = 27.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(end = 5.dp, top = 21.dp)
                        )

                        BackgroundedText(
                            background = Color.Black,
                            textColor = Color.White,
                            text = "BOOK",
                            horizontal = 12,
                            vertical = 7,
                            modifier = Modifier.padding(end = 5.dp, top = 7.dp, bottom = 23.dp)
                        )
                    }
                }
                Selected(modifier = Modifier.padding(start = 26.dp, top = 451.dp))
            }


        }
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
    NearestWaterPointContent(
        navigate = {},
        truckIncomingWorkUiState = TruckIncomingWorkUiState(),
        nearestWaterPointUiState = NearestWaterPointUiState(),
        waterpointClicked = {})
}