package com.example.dropy.ui.screens.truckIncomingWork

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.network.models.GetIndividualOrders.GetIndividualOrdersResItem
import com.example.dropy.ui.app.AppUiState
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation
import java.util.*

@Composable
fun TruckIncomingWorkContent(
    uiState: TruckIncomingWorkUiState,
    appViewModel: AppViewModel,
    buttonClicked: (String, GetIndividualOrdersResItem) -> Unit,
    appUiState: AppUiState
) {
    Box(modifier = Modifier.fillMaxSize()) {

        MapComponent(float = 1f, modifier = Modifier.fillMaxSize()) {
            GoogleMapWrapper(
                cameraPosition = uiState.myAddress,
                appViewModel = appViewModel
            ) { mapUiSettings, mapProperties, cameraPositionState ->

                GoogleMapSelectLocation(
                    cameraPositionState = cameraPositionState,
                    mapUiSettings = mapUiSettings,
                    mapProperties = mapProperties,
                    locationSelected = {/* selectLocationViewModel.setUserLatLong(it)*/ },
                    markerPosition = uiState.myAddress,
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
            Row(
                modifier = Modifier
                    .padding(top = 19.dp, bottom = 68.dp)
                    .clickable { }
                    .fillMaxWidth()
                    .height(95.dp)
                    .background(color = Color.White)
                    .padding(top = 22.dp, start = 40.dp)
            ) {

                Column() {

                    val exten = if (uiState.hasongoingJob) {
                        "ONGOING"
                    } else {
                        "INCOMING"
                    }
                    Text(
                        text = "${exten} WORKS",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp,
//                        fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.axiformabold)),
                            letterSpacing = (-0.96).sp,
                            lineHeight = 37.sp
                        )
                    )

                    val extension = if (uiState.hasongoingJob) {
                        "ongoing"
                    } else {
                        "found some"
                    }

                    Text(
                        text = "You have ${extension} work. Please proceed",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily(Font(R.font.axiformaregular)),
                            letterSpacing = (-0.58).sp,
                            lineHeight = 21.sp
                        ),
                        modifier = Modifier.padding(top = 18.dp)
                    )
                }
            }


            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(6.dp), content = {
                    itemsIndexed(uiState.orders) { index, item ->
                        item.tasks.forEach {
                            if (it.truck.id.equals(appUiState.activeProfile?.id)) {
                                if (it.four_digit_code.equals(null))
                                    workItem(
                                        buttonClicked = buttonClicked,
                                        getIndividualOrdersResItem = item,
                                        truckIncomingWorkUiState = uiState
                                    )
                            }
                        }

                    }
                })
        }
    }
}


@Composable
fun workItem(
    timeInMin: Int = 4200,
    hasongoingJob: Boolean = false,
    buttonClicked: (String, GetIndividualOrdersResItem) -> Unit,
    truckIncomingWorkUiState: TruckIncomingWorkUiState,
    getIndividualOrdersResItem: GetIndividualOrdersResItem
) {
    val timeString: String = if (timeInMin >= 3600) {
        val hrs: Int = timeInMin / 3600
        val min: Int = timeInMin % 3600 / 60
        // val seconds: Int = input % 3600 % 60
        // val hrs = timeInMin / 60
        //  val min = timeInMin % 60
        "$hrs HRS $min MINS"
    } else {
        val min = timeInMin / 60
        "$min MINS"
    }

    Row(
        modifier = Modifier
            .padding(bottom = 8.dp, start = 5.dp, end = 4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White, RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFFDEDEDE), RoundedCornerShape(8.dp))
            .clickable {
                /*               if (hasOngoingJob) {
                    onAccept()
                }
 */
            },
    ) {
        Box(
            modifier = Modifier
                .padding(top = 25.dp, start = 3.dp)
                .size(80.dp)
                .clip(CircleShape)
        ) {
//            LoadImage(imageUrl = profilePicUrl)
            Image(
                painter = painterResource(id = R.drawable.shop1),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 5.dp, top = 21.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                ) {
                    SimpleText(
                        text = "${truckIncomingWorkUiState.truckDetails?.capacity}LT CLEAN WATER".replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        },
                        isBold = true,
                        isUppercase = true,
                        font = Font(R.font.axiformabold),
                        textSize = 11
                    )
                    SimpleText(
                        text = "To: ${getIndividualOrdersResItem.customer.first_name} ${getIndividualOrdersResItem.customer.last_name} ",
                        font = Font(R.font.axiformamedium),
                        textSize = 10,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
               Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                   SimpleText(
                       text = "${getIndividualOrdersResItem.total_payment.toDouble().toInt().toString()}/-",
                       textSize = 21,
                       isExtraBold = true,
                       horizontalPadding = 8,
                       font = Font(R.font.axiformablack),
                       modifier = Modifier.padding(top = 1.dp, end = 5.dp)
                   )
               }
            }
            Row(
                modifier = Modifier
                    .padding(top = 13.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    StyledText(
                        backgroundColor = Color(0xFF979797)/*when (jobType) {
                        "delivery" -> {
                            LightBlue
                        }
                        "ride" -> {
                            Color.Black
                        }
                        else -> {
                            LightBlue
                        }
                    }*/,
                        text = "NAIROBI WEST".replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        },
                        textColor = Color.White,
                        fontFamily = R.font.axiformabold,
                        textSize = 8
                    )
                    Text(
                        text = "3.5 KM",
                        style = TextStyle(
                            color = Color(0xFF505050),
                            fontSize = 8.sp,
//                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.axiformabold)),
                            letterSpacing = (-0.38).sp,
                            lineHeight = 15.sp
                        ),
                        modifier = Modifier.padding(start = 4.dp)
                    )

                }

                if (hasongoingJob.equals(false)) {
                    Row(
                        modifier = Modifier
                            .padding(start = 9.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .clickable { buttonClicked("Accept", getIndividualOrdersResItem) }
                        ) {
                            StyledText(
                                backgroundColor = Color(0xFF02CBE3),
                                text = "Accept",
                                textColor = Color.Black
                            )
                        }

                        Box(
                            modifier = Modifier.clickable { buttonClicked("Decline", getIndividualOrdersResItem) }
                        ) {
                            StyledText(
                                backgroundColor = Color.Transparent,
                                textColor = Color.Black,
                                text = "Decline"
                            )
                        }


                    }
                }

            }
        }
    }
}