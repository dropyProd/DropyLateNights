package com.example.dropy.ui.screens.allocateTruck

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.network.models.createIndividualWaterOrder.AssignedTruck
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.screens.myTrucks.MyTrucksUiState
import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeUiState

@Composable
fun AllocatingTruckContent(
    navigate: () -> Unit,
    tankerBoreholeUiState: TankerBoreholeUiState,
    allocatingTruckUiState: AllocatingTruckUiState
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        ClippedHeader(
            title = "ALLOCATED TRUCKS",
            modifier = Modifier.padding(top = 22.dp),
            start = 30,
            end = 40
        )

        LazyColumn(modifier = Modifier
            .padding(top = 36.dp, start = 15.dp, end = 14.dp)
            /*   .verticalScroll(
                   rememberScrollState()
               )*/,
            verticalArrangement = Arrangement.spacedBy(11.dp), content = {
                itemsIndexed(tankerBoreholeUiState.createIndividualWaterOrderRes!!.assigned_trucks) { index, item ->
                    val backgroundColor = if (index % 2 == 0)
                        Color.Transparent
                    else Color(0xFFF5F5F5)
                    truckItem(color = backgroundColor, navigate = navigate, assignedTruck = item, allocatingTruckUiState = allocatingTruckUiState)
                }
            })
    }
}

@Composable
fun truckItem(
    color: Color, navigate: () -> Unit,
    assignedTruck: AssignedTruck,
    allocatingTruckUiState: AllocatingTruckUiState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = color, shape = RoundedCornerShape(12.dp))
            .border(width = 1.dp, color = Color(0xFFDEDEDE), shape = RoundedCornerShape(12.dp))
            .clickable { navigate() }
            .padding(end = 9.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row() {
            LoadImage(
                imageUrl = assignedTruck.image,
                modifier = Modifier
                    .padding(top = 21.dp, start = 18.dp)
                    .size(54.dp)
                    .clip(
                        CircleShape
                    ),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(top = 26.dp, start = 24.dp)) {
                Text(
                    text = getDriverName(allocatingTruckUiState, assignedTruck.id),
                    fontSize = 16.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    ),
                    letterSpacing = (-0.77).sp,
                    lineHeight = 29.sp,
                    color = Color.Black
                )

                Row(modifier = Modifier.padding(start = 2.dp)) {

                    Row(
                        modifier = Modifier
                            .padding(top = 14.dp)
                            .wrapContentWidth()
                            .height(15.dp)
                            .background(
                                color = Color.Black,
                                shape = RoundedCornerShape(80)
                            )
                            .padding(2.dp)
                    ) {
                        Text(
                            text = "5",
                            fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformaheavy)
                            ),
                            letterSpacing = (-0.48).sp,
                            lineHeight = 18.sp,
                            color = Color.White
                        )
                    }

                    Text(
                        text = "TRIPS",
                        fontSize = 8.sp,
//                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(
                            Font(R.font.axiformablack)
                        ),
                        letterSpacing = (-0.38).sp,
                        lineHeight = 15.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(top = 18.dp, start = 3.dp)
                            .width(36.dp)
                    )

                    Row(
                        modifier = Modifier
                            .padding(top = 14.dp, start = 6.dp)
                            .wrapContentWidth()
                            .height(15.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(26.dp)
                            ).padding(horizontal = 6.dp)
                    ) {
                        Text(
                            text = assignedTruck.license_plate.toUpperCase(),
                            fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformaheavy)
                            ),
                            letterSpacing = (-0.48).sp,
                            lineHeight = 18.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(
                                start = 8.dp,
                                end = 6.dp,
                                top = 3.dp,
                                bottom = 2.dp
                            )
                        )
                    }
                }
            }

        }

        Text(
            text = "${assignedTruck.capacity}LT",
            fontSize = 17.sp,
//                        fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(
                Font(R.font.axiformaheavy)
            ),
            letterSpacing = (-0.82).sp,
            lineHeight = 32.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(top = 39.dp)
        )

    }
}
fun getDriverName(allocatingTruckUiState: AllocatingTruckUiState, truckId: String): String {
    val text = mutableStateOf("")
    allocatingTruckUiState.truckDriverList.forEach {
        if (it.truck.id.equals(truckId))
            text.value = it.driver.first_name + " " + it.driver.last_name
    }
    return text.value
}

fun getDriverLogo(allocatingTruckUiState: AllocatingTruckUiState, truckId: String): String {
    val text = mutableStateOf("")
    allocatingTruckUiState.truckDriverList.forEach {
        if (it.truck.id.equals(truckId))
            text.value = ""
    }
    return text.value
}


@Preview
@Composable
fun demo() {
    AllocatingTruckContent(navigate = {}, tankerBoreholeUiState = TankerBoreholeUiState(), allocatingTruckUiState = AllocatingTruckUiState())
}