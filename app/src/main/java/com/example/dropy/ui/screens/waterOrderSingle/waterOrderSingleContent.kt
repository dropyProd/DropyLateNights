package com.example.dropy.ui.screens.waterOrderSingle

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.screens.locale.BackgroundedImage
import com.example.dropy.ui.screens.truckIncomingWork.TruckIncomingWorkUiState
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WaterOrderSingleContent(
    nextClicked: () -> Unit,
    truckIncomingWorkUiState: TruckIncomingWorkUiState,
    date: String = "",
    time: String = "",
    waterOrderSingleUiState: WaterOrderSingleUiState
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = 23.dp)
                .verticalScroll(rememberScrollState())
        ) {
            ClippedHeader(title = "ORDER #${truckIncomingWorkUiState.selectedOrder?.tracking_id}")

            Text(
                text = "ORDER DETAILS",
                fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformablack)
                ),
                letterSpacing = (-0.48).sp,
                lineHeight = 17.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp, start = 17.dp)
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(top = 30.dp, start = 39.dp)
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

                Column(modifier = Modifier.padding(top = 25.dp, start = 121.dp)) {
                    Text(
                        text = "${truckIncomingWorkUiState.truckDetails?.capacity} LITERS",
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
                            text = "NOT STARTED",
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
                        text = date,
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
                        text = time,
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


                Row(
                    modifier = Modifier
                        .padding(top = 49.dp, start = 135.dp)
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
                        text = "NAIROBI WEST",
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
                    text = "${waterOrderSingleUiState.distance} KILOMETERS",
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
            if (!truckIncomingWorkUiState.selectedOrder?.note.toString().equals("")) {
                Text(
                    text = "DELIVERY NOTE",
                    fontSize = 12.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformaheavy)
                    ),
                    letterSpacing = (-0.58).sp,
                    lineHeight = 23.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 27.dp, start = 39.dp)
                )

                Row(modifier = Modifier
                    .padding(start = 39.dp, end = 43.dp, top = 22.dp)
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(12.dp))
                    .border(
                        width = 1.dp,
                        color = Color(0xFFDEDEDE),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable { }
                ) {
                    Text(
                        text = truckIncomingWorkUiState.selectedOrder?.note.toString(),
                        fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(
                            Font(R.font.axiformabold)
                        ),
                        letterSpacing = (-0.43).sp,
                        lineHeight = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 18.dp, start = 39.dp)
                    )
                }
            }

            Text(
                text = "CUSTOMER",
                fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformaheavy)
                ),
                letterSpacing = (-0.48).sp,
                lineHeight = 19.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 30.dp, start = 32.dp)
            )

            truckPerson(
                modifier = Modifier.padding(start = 20.dp, end = 13.dp, top = 22.dp),
                truckIncomingWorkUiState = truckIncomingWorkUiState
            )


        }

        Button(
            onClick = {
                nextClicked()
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 18.dp, bottom = 13.dp)
                .size(68.dp)
                .clip(CircleShape),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black
            )
        ) {
            SimpleText(
                textSize = 16,
                text = "next",
                fontWeight = FontWeight.W900,
                textColor = Color.White

            )
        }
    }
}


@Composable
fun truckPerson(
    modifier: Modifier = Modifier,
    navigate: (() -> Unit)? = null,
    truckIncomingWorkUiState: TruckIncomingWorkUiState
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(83.dp)
            .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(14.dp))
            .border(width = 1.dp, color = Color(0xFFDEDEDE), shape = RoundedCornerShape(14.dp))
            .clickable {
                if (navigate != null) {
                    navigate()
                }
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Image(
            painter = painterResource(id = R.drawable.shop1),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 11.dp, start = 17.dp)
                .size(42.dp)
                .clip(
                    CircleShape
                ),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.padding(top = 16.dp)) {
            Text(
                text = truckIncomingWorkUiState.selectedOrder?.customer?.first_name.toString()
                    .toUpperCase() + " " + truckIncomingWorkUiState.selectedOrder?.customer?.last_name.toString()
                    .toUpperCase(),
                fontSize = 12.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformablack)
                ),
                letterSpacing = (-0.58).sp,
                lineHeight = 22.sp,
                color = Color.Black
            )
            /*Text(
                text = "Truck Driver",
                fontSize = 12.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformasemibold)
                ),
                letterSpacing = (-0.58).sp,
                lineHeight = 22.sp,
                color = Color.Black
            )*/


        }

       Row() {
           BackgroundedImage(
               background = Color(0xFF1BFC13),
               image = R.drawable.phone,
               modifier = Modifier.padding(top = 13.dp)
           )
           BackgroundedImage(
               background = Color.White,
               image = R.drawable.email,
               modifier = Modifier.padding(top = 13.dp, start = 12.dp, end = 6.dp)
           )
       }

    }
}

@Composable
@Preview
fun demo() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        WaterOrderSingleContent(
            nextClicked = {},
            truckIncomingWorkUiState = TruckIncomingWorkUiState(),
            waterOrderSingleUiState = WaterOrderSingleUiState()
        )
    }
}