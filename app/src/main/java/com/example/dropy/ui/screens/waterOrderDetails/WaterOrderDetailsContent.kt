package com.example.dropy.ui.screens.waterOrderDetails

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeUiState

@Composable
fun WaterOrderDetailsContent(
    nextClicked: () -> Unit,
    tankerBoreholeUiState: TankerBoreholeUiState,
    date: String = "",
    time: String = "",
    waterOrderDetailsUiState: WaterOrderDetailsUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 17.dp, end = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(top = 33.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(604.dp)
                        .background(color = Color(0xFFF5F5F5), RoundedCornerShape(20.dp))
                        .border(width = 1.dp, color = Color(0xFFDEDEDE), RoundedCornerShape(20.dp))
                ) {

                    Row() {
                        Row(
                            modifier = Modifier
                                .padding(top = 26.dp, start = 35.dp)
                                .width(102.dp)
                                .height(96.dp)
                                .background(
                                    color = Color(0xFFF5F5F5),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFDEDEDE),
                                    shape = RoundedCornerShape(12.dp)
                                ),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.truckwater),
                                contentDescription = "",
                                modifier = Modifier
                                    .width(45.dp)
                                    .height(45.dp),
                                contentScale = ContentScale.FillWidth
                            )
                        }

                        Column(modifier = Modifier.padding(start = 78.dp, top = 29.dp)) {
                            Text(
                                text = "ORDER TYPE",
                                fontSize = 9.sp,
//                            fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily(
                                    Font(R.font.axiformablack)
                                ),
                                letterSpacing = (-0.43).sp,
                                lineHeight = 17.sp,
                                color = Color.Black,
                                modifier = Modifier.padding()
                            )

                            Text(
                                text = if (tankerBoreholeUiState.createIndividualWaterOrderRes?.water_type.equals(
                                        "C"
                                    )
                                ) "Clean Water" else "Treated Water",
                                fontSize = 9.sp,
//                            fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily(
                                    Font(R.font.axiformablack)
                                ),
                                letterSpacing = (-0.43).sp,
                                lineHeight = 17.sp,
                                color = Color(0xFF74728A),
                                modifier = Modifier.padding(top = 7.dp)
                            )

                            Text(
                                text = "DELIVERY MODE",
                                fontSize = 9.sp,
//                            fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily(
                                    Font(R.font.axiformablack)
                                ),
                                letterSpacing = (-0.43).sp,
                                lineHeight = 17.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 36.dp)
                            )

                            Text(
                                text = if (tankerBoreholeUiState.createIndividualWaterOrderRes?.delivery_type.equals(
                                        "E"
                                    )
                                ) "Express Delivery" else if (tankerBoreholeUiState.createIndividualWaterOrderRes?.delivery_type.equals(
                                        "S"
                                    )
                                ) "Scheduled Delivery" else "Standard Delivery",
                                fontSize = 9.sp,
//                            fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily(
                                    Font(R.font.axiformablack)
                                ),
                                letterSpacing = (-0.43).sp,
                                lineHeight = 17.sp,
                                color = Color(0xFF74728A),
                                modifier = Modifier.padding(top = 9.dp)
                            )
                        }


                    }

                    Row(
                        modifier = Modifier
                            .padding(start = 27.dp, end = 20.dp, top = 20.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(color = Color(0xFFDEDEDE))
                    ) {

                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier.padding(top = 24.dp, start = 30.dp)
                        ) {
                            Text(
                                text = "WATER VOLUME ORDERED",
                                fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily(
                                    Font(R.font.axiformablack)
                                ),
                                letterSpacing = (-0.43).sp,
                                lineHeight = 17.sp,
                                color = Color.Black,
                            )
                            Text(
                                text = "DELIVERY STATUS",
                                fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily(
                                    Font(R.font.axiformablack)
                                ),
                                letterSpacing = (-0.43).sp,
                                lineHeight = 17.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 19.dp)
                            )

                            Text(
                                text = "Order Date",
                                fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily(
                                    Font(R.font.axiformablack)
                                ),
                                letterSpacing = (-0.43).sp,
                                lineHeight = 17.sp,
                                color = Color(0xFF74728A),
                                modifier = Modifier.padding(top = 18.dp)
                            )

                            Text(
                                text = "Order Time",
                                fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily(
                                    Font(R.font.axiformablack)
                                ),
                                letterSpacing = (-0.43).sp,
                                lineHeight = 17.sp,
                                color = Color(0xFF74728A),
                                modifier = Modifier.padding(top = 14.dp)
                            )

                        }

                        Column(modifier = Modifier.padding(top = 25.dp, end = 8.dp)) {
                            Text(
                                text = "${tankerBoreholeUiState.createIndividualWaterOrderRes?.quantity}LITERS",
                                fontSize = 12.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily(
                                    Font(R.font.axiformaheavy)
                                ),
                                letterSpacing = (-0.58).sp,
                                lineHeight = 23.sp,
                                color = Color.Black,
                            )

                            Row(
                                modifier = Modifier
                                    .padding(top = 5.dp)
                                    .width(65.dp)
                                    .height(14.dp)
                                    .background(
                                        color = Color(0xFF979797),
                                        shape = RoundedCornerShape(7.dp)
                                    )
                                    .border(
                                        width = 1.dp,
                                        color = Color(0xFFD1D1D1),
                                        shape = RoundedCornerShape(7.dp)
                                    )
                            ) {
                                Text(
                                    text = if (tankerBoreholeUiState.createIndividualWaterOrderRes?.delivery_status.equals(
                                            "P"
                                        )
                                    ) "NOT STARTED" else if (tankerBoreholeUiState.createIndividualWaterOrderRes?.delivery_status.equals(
                                            "S"
                                        )
                                    ) "ON THE WAY" else "DELIVERED",
                                    fontSize = 7.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                    fontFamily = FontFamily(
                                        Font(R.font.axiformablack)
                                    ),
                                    letterSpacing = (-0.34).sp,
                                    lineHeight = 13.sp,
                                    color = Color.White,
                                    modifier = Modifier.padding(
                                        start = 10.dp,
                                        end = 5.dp,
                                        top = 4.dp,
                                        bottom = 1.dp
                                    )
                                )
                            }
                            Text(
                                text = date.uppercase(),
                                fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily(
                                    Font(R.font.axiformablack)
                                ),
                                letterSpacing = (-0.43).sp,
                                lineHeight = 17.sp,
                                color = Color(0xFF74728A),
                                modifier = Modifier.padding(top = 15.dp)
                            )

                            Text(
                                text = time.uppercase(),
                                fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily(
                                    Font(R.font.axiformablack)
                                ),
                                letterSpacing = (-0.43).sp,
                                lineHeight = 17.sp,
                                color = Color(0xFF74728A),
                                modifier = Modifier.padding(top = 18.dp)
                            )
                        }


                    }

                    Row(
                        modifier = Modifier
                            .padding(start = 27.dp, end = 20.dp, top = 19.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(color = Color(0xFFDEDEDE))
                    ) {

                    }

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(top = 21.dp, start = 29.dp)) {
                            Text(
                                text = "LOCATION DETAILS",
                                fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily(
                                    Font(R.font.axiformablack)
                                ),
                                letterSpacing = (-0.43).sp,
                                lineHeight = 17.sp,
                                color = Color(0xFF74728A),
                            )

                            Text(
                                text = "Delivery Location",
                                fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily(
                                    Font(R.font.axiformablack)
                                ),
                                letterSpacing = (-0.43).sp,
                                lineHeight = 17.sp,
                                color = Color(0xFF74728A),
                                modifier = Modifier.padding(top = 18.dp)
                            )
                        }

                        Text(
                            text = tankerBoreholeUiState.selectedAddress?.placeName.toString(),
                            fontSize = 8.sp,
//                        fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformamedium)
                            ),
                            letterSpacing = (-0.38).sp,
                            lineHeight = 15.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 49.dp, start = 100.dp)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .padding(start = 27.dp, end = 20.dp, top = 37.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(color = Color(0xFFDEDEDE))
                    ) {

                    }

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "DISTANCE",
                            fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformablack)
                            ),
                            letterSpacing = (-0.43).sp,
                            lineHeight = 17.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 25.dp, start = 31.dp)
                        )
                        Text(
                            text = "79 KILOMETERS",
                            fontSize = 12.sp,
//                        fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformaheavy)
                            ),
                            letterSpacing = (-0.58).sp,
                            lineHeight = 23.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 22.dp, start = 128.dp)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .padding(start = 73.dp, end = 74.dp, top = 24.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(color = Color(0xFFDEDEDE))
                    ) {

                    }

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "ALLOCATED TRUCKS",
                            fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformablack)
                            ),
                            letterSpacing = (-0.43).sp,
                            lineHeight = 17.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 24.dp, start = 51.dp)
                        )

                        Column(
                            modifier = Modifier
                                .padding(top = 21.dp)
                                .fillMaxWidth()
                                .padding(end = 8.dp), horizontalAlignment = Alignment.End
                        ) {
                            Column() {
                                Row() {
                                    Row(
                                        modifier = Modifier
                                            .width(17.dp)
                                            .height(17.dp)
                                            .background(
                                                color = Color(0xFF979797),
                                                shape = CircleShape
                                            )
                                            .border(
                                                width = 1.dp,
                                                color = Color(0xFFD1D1D1),
                                                shape = CircleShape
                                            )
                                    ) {
                                        Text(
                                            text = waterOrderDetailsUiState.tenTrucks.size.toString(),
                                            fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                            fontFamily = FontFamily(
                                                Font(R.font.axiformaheavy)
                                            ),
                                            letterSpacing = (-0.48).sp,
                                            lineHeight = 19.sp,
                                            color = Color.White,
                                            modifier = Modifier.padding(
                                                start = 5.dp,
                                                end = 5.dp,
                                                top = 4.dp,
                                                bottom = 3.dp
                                            )
                                        )
                                    }

                                    Text(
                                        text = "(10,000 LT)",
                                        fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                        fontFamily = FontFamily(
                                            Font(R.font.axiformaheavy)
                                        ),
                                        letterSpacing = (-0.48).sp,
                                        lineHeight = 10.sp,
                                        color = Color.Black,
                                        modifier = Modifier.padding(top = 4.dp, start = 13.dp)
                                    )
                                }
                                Row(modifier = Modifier.padding(top = 7.dp)) {
                                    Row(
                                        modifier = Modifier
                                            .width(17.dp)
                                            .height(17.dp)
                                            .background(
                                                color = Color(0xFF979797),
                                                shape = CircleShape
                                            )
                                            .border(
                                                width = 1.dp,
                                                color = Color(0xFFD1D1D1),
                                                shape = CircleShape
                                            )
                                    ) {
                                        Text(
                                            text = waterOrderDetailsUiState.fiveTrucks.size.toString(),
                                            fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                            fontFamily = FontFamily(
                                                Font(R.font.axiformaheavy)
                                            ),
                                            letterSpacing = (-0.48).sp,
                                            lineHeight = 19.sp,
                                            color = Color.White,
                                            modifier = Modifier.padding(
                                                start = 5.dp,
                                                end = 5.dp,
                                                top = 4.dp,
                                                bottom = 3.dp
                                            )
                                        )
                                    }

                                    Text(
                                        text = "(5,000 LT)",
                                        fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                        fontFamily = FontFamily(
                                            Font(R.font.axiformaheavy)
                                        ),
                                        letterSpacing = (-0.48).sp,
                                        lineHeight = 10.sp,
                                        color = Color.Black,
                                        modifier = Modifier.padding(top = 4.dp, start = 13.dp)
                                    )
                                }
                            }
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "TOTAL VOLUME ALLOCATED",
                            fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformablack)
                            ),
                            letterSpacing = (-0.43).sp,
                            lineHeight = 17.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 36.dp, start = 30.dp)
                        )

                        Text(
                            text = "${tankerBoreholeUiState.createIndividualWaterOrderRes?.quantity}LITERS",
                            fontSize = 12.sp,
//                        fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformaheavy)
                            ),
                            letterSpacing = (-0.58).sp,
                            lineHeight = 23.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 37.dp)
                        )
                    }

                    /*Text(
                        text = "2,000 Liters was not allocated to any truck as it does not meet the minimum viable\nvolume for transportation. You can update your order accordingly.",
                        fontSize = 8.sp,
//                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(
                            Font(R.font.axiformamedium)
                        ),
                        letterSpacing = (-0.38).sp,
                        lineHeight = 15.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 25.dp, start = 37.dp, end = 20.dp),
                        textAlign = TextAlign.Center
                    )*/
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 18.dp)
                        .width(156.dp)
                        .height(49.dp)
                        .background(color = Color(0xFF02CBE3), RoundedCornerShape(42.dp))
                        .border(width = 1.dp, color = Color(0x57707070), RoundedCornerShape(42.dp))
                        .clickable { nextClicked() },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    androidx.compose.material.Text(
                        text = "Next",
                        color = Color.Black,
                        fontSize = 10.sp,
//                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                        letterSpacing = (-0.48).sp,
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(top = 25.dp, start = 29.dp)
                    .width(113.dp)
                    .height(17.dp)
                    .background(
                        color = Color.Black,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Text(
                    text = "ORDER DETAILS",
                    fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    ),
                    letterSpacing = (-0.48).sp,
                    lineHeight = 19.sp,
                    color = Color.White,
                    modifier = Modifier.padding(
                        start = 15.dp,
                        end = 17.dp,
                        top = 4.dp,
                        bottom = 4.dp
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun demo() {
    WaterOrderDetailsContent(
        nextClicked = {},
        tankerBoreholeUiState = TankerBoreholeUiState(),
        waterOrderDetailsUiState = WaterOrderDetailsUiState()
    )
}