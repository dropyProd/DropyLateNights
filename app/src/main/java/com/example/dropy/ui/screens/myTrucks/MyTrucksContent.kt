package com.example.dropy.ui.screens.myTrucks

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.network.models.getWaterTrucks.GetTrucksResItem
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.screens.locale.BackgroundedImage
import com.example.dropy.ui.screens.myTruckEditDetails.MyTruckEditDetailsUiState
import com.example.dropy.ui.screens.waterVendorDash.WaterVendorDashUiState

@Composable
fun MyTrucksContent(
    waterVendorDashUiState: WaterVendorDashUiState,
    navigate: (GetTrucksResItem) -> Unit,
    navigateEdit: (GetTrucksResItem) -> Unit,
    myTrucksUiState: MyTrucksUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        ClippedHeader(
            title = "MY TRUCKS",
            modifier = Modifier.padding(top = 22.dp)
        )


        LazyColumn(modifier = Modifier
            .padding(top = 36.dp, start = 15.dp, end = 14.dp),
            verticalArrangement = Arrangement.spacedBy(11.dp), content = {
                itemsIndexed(waterVendorDashUiState.truckList) { index, item ->
                    val backgroundColor = if (index % 2 == 0)
                        Color.Transparent
                    else Color(0xFFF5F5F5)
                    waterOrderItem(
                        navigate = navigate,
                        color = backgroundColor,
                        assignedTruck = item,
                        navigateEdit = navigateEdit,
                        myTrucksUiState = myTrucksUiState
                    )
                }
            })
    }
}

fun getDriverName(myTrucksUiState: MyTrucksUiState, truckId: String): String {
    val text = mutableStateOf("")
    myTrucksUiState.truckDriverList.forEach {
        if (it.truck.id.equals(truckId))
            text.value = it.driver.first_name + " " + it.driver.last_name
    }
    return text.value
}

fun getDriverLogo(myTrucksUiState: MyTrucksUiState, truckId: String): String {
    val text = mutableStateOf("")
    myTrucksUiState.truckDriverList.forEach {
        if (it.truck.id.equals(truckId))
            text.value = ""
    }
    return text.value
}

@Composable
fun waterOrderItem(
    navigate: (GetTrucksResItem) -> Unit,
    navigateEdit: (GetTrucksResItem) -> Unit,
    color: Color,
    assignedTruck: GetTrucksResItem? = null,
    myTrucksUiState: MyTrucksUiState
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = color, shape = RoundedCornerShape(12.dp))
            .border(width = 1.dp, color = Color(0xFFDEDEDE), shape = RoundedCornerShape(12.dp))
            .clickable {
                if (assignedTruck != null) {
                    navigate(assignedTruck)
                }
            }
        ) {

            Column(modifier = Modifier.padding(top = 9.dp)) {
                LoadImage(
                    imageUrl = assignedTruck?.image,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .width(50.dp)
                        .height(50.dp)
                        .clip(CircleShape)
                )
            }

            Column(modifier = Modifier.padding(top = 16.dp)) {
                Text(
                    text = getDriverName(myTrucksUiState, assignedTruck?.id.toString()),
                    fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    ),
                    letterSpacing = (-0.43).sp,
                    lineHeight = 17.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 18.dp)
                )
                Text(
                    text = assignedTruck?.license_plate.toString(),
                    fontSize = 11.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    ),
                    letterSpacing = (-0.53).sp,
                    lineHeight = 21.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 18.dp, top = 13.dp)
                )

                /* Row(
                     modifier = Modifier
                         .padding(top = 1.dp, start = 18.dp)
                         .width(89.dp)
                         .height(18.dp)
                         .background(
                             color = Color(0xFF979797),
                             shape = RoundedCornerShape(9.dp)
                         )
                     *//*.border(
                    width = 1.dp,
                    color = Color(0xFFD1D1D1),
                    shape = RoundedCornerShape(7.dp)
                )*//*
            ) {
                Text(
                    text = if (getIndividualOrdersResItem != null) {
                        if (getIndividualOrdersResItem?.delivery_status.equals(
                                "P"
                            )
                        ) "NOT PROCESSED" else if (getIndividualOrdersResItem?.delivery_status.equals(
                                "S"
                            )
                        ) "PROCESSED" else "DELIVERED"
                    } else {
                        if (createIndividualWaterOrderRes?.delivery_status.equals(
                                "P"
                            )
                        ) "NOT PROCESSED" else if (createIndividualWaterOrderRes?.delivery_status.equals(
                                "S"
                            )
                        ) "PROCESSED" else "DELIVERED"
                    },
                    fontSize = 7.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    ),
                    letterSpacing = (-0.34).sp,
                    lineHeight = 13.sp,
                    color = Color.White,
                    modifier = Modifier.padding(
                        start = 8.dp,
                        end = 9.dp,
                        top = 5.dp
//                        bottom = 1.dp
                    )
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 8.dp, start = 18.dp)
                    .width(124.dp)
                    .height(18.dp)
                    .background(
                        color = Color(0xFFC2F8FF),
                        shape = RoundedCornerShape(9.dp)
                    )
                *//*.border(
                    width = 1.dp,
                    color = Color(0xFFD1D1D1),
                    shape = RoundedCornerShape(7.dp)
                )*//*
            ) {
                Text(
                    text = if (getIndividualOrdersResItem != null) {
                        if (getIndividualOrdersResItem?.delivery_status.equals(
                                "P"
                            )
                        ) "NOT STARTED" else if (getIndividualOrdersResItem?.delivery_status.equals(
                                "S"
                            )
                        ) "ON THE WAY" else "DELIVERED"
                    } else {
                        if (createIndividualWaterOrderRes?.delivery_status.equals(
                                "P"
                            )
                        ) "NOT STARTED" else if (createIndividualWaterOrderRes?.delivery_status.equals(
                                "S"
                            )
                        ) "ON THE WAY" else "DELIVERED"
                    },
                    fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformaextrabold)
                    ),
                    letterSpacing = (-0.43).sp,
                    lineHeight = 17.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(
                        start = 9.dp,
                        end = 5.dp,
                        top = 5.dp,
                        bottom = 4.dp
                    )
                )
            }*/
            }

            Column(
                modifier = Modifier
                    .padding(top = 27.dp)
                    .fillMaxWidth(), horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "${assignedTruck?.capacity}LT",
                    fontSize = 17.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformaheavy)
                    ),
                    letterSpacing = (-0.82).sp,
                    lineHeight = 32.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(end = 8.dp)
                )

                /* Row(
                     modifier = Modifier
                         .padding(top = 17.dp, end = 8.dp)
                         .wrapContentWidth()
                         .height(18.dp)
                         .background(
                             color = Color(0xFF02CBE3),
                             shape = RoundedCornerShape(12.dp)
                         )
                         .padding(horizontal = 4.dp),
                     horizontalArrangement = Arrangement.Center,
                     verticalAlignment = Alignment.CenterVertically
                     *//*.border(
                    width = 1.dp,
                    color = Color(0xFFD1D1D1),
                    shape = RoundedCornerShape(7.dp)
                )*//*
            ) {
                Text(
                    text = "ETA 12 HRS",
                    fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformaextrabold)
                    ),
                    letterSpacing = (-0.48).sp,
                    lineHeight = 19.sp,
                    color = Color.White
                )
            }*/
            }

        }
        BackgroundedImage(
            background = Color.Black,
            image = R.drawable.pencil,
            imageColor = Color.White,
            modifier = Modifier
                .align(
                    Alignment.TopEnd
                )
                .offset(y = (-20).dp, x = 20.dp)
                .clickable {
                    if (assignedTruck != null) {
                        navigateEdit(assignedTruck)
                    }
                },
            shape = RoundedCornerShape(50.dp)
        )
        BackgroundedImage(
            background = Color.Red,
            image = R.drawable.bin,
            imageColor = Color.Black,
            modifier = Modifier
                .align(
                    Alignment.BottomEnd
                )
                .offset(y = 20.dp, x = 20.dp)
                .clickable {
                    if (assignedTruck != null) {
                        navigateEdit(assignedTruck)
                    }
                },
            shape = RoundedCornerShape(50.dp)
        )
    }
}