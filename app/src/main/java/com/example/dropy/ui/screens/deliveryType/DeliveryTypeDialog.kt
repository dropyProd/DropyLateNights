package com.example.dropy.ui.screens.deliveryType

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.dropy.R

@Composable
fun DeliveryTypeDialog(
    onDismissRequest: () -> Unit,
    navigate: (String) -> Unit,
    show: Boolean
) {
    if (show) {
        Dialog(onDismissRequest = { onDismissRequest() }) {
            Column(
                modifier = Modifier
                    .padding(start = 15.dp, end = 14.dp)
                    .fillMaxWidth()
                    .height(299.dp)
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(31.dp))
                    .border(width = 1.dp, color = Color(0xFFDEDEDE), RoundedCornerShape(31.dp))
            ) {

                Text(
                    text = "CHOOSE DELIVERY TYPE",
                    color = Color.Black,
                    fontSize = 14.sp,
//            fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                    letterSpacing = (-0.67).sp,
                    modifier = Modifier
                        .padding(top = 45.dp)
                        .align(Alignment.CenterHorizontally)
                )


                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(top = 79.dp)
                        .fillMaxWidth()
                        .padding(end = 17.dp, start = 17.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { navigate("EXPRESS") }) {
                        Column(
                            modifier = Modifier
                                .width(73.dp)
                                .height(86.dp)
                                .background(color = Color(0xFFFFFFFF), RoundedCornerShape(6.dp))
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFDEDEDE),
                                    shape = RoundedCornerShape(6.dp)
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "EXPRESS",
                                color = Color.Black,
                                fontSize = 10.sp,
//            fontWeight = FontWeight.SemiBold,
                                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                                letterSpacing = (-0.48).sp,
                                modifier = Modifier.padding(top = 19.dp),
                                lineHeight = 19.sp
                            )
                            Row(
                                modifier = Modifier
                                    .padding(top = 28.dp)
                                    .width(60.dp)
                                    .height(21.dp)
                                    .background(color = Color(0xFFFFFFFF), RoundedCornerShape(17.dp))
                                    .border(
                                        width = 1.dp,
                                        color = Color(0xFFDEDEDE),
                                        shape = RoundedCornerShape(17.dp)
                                    ),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "2 - 4 Hours",
                                    color = Color.Black,
                                    fontSize = 9.sp,
//            fontWeight = FontWeight.SemiBold,
                                    fontFamily = FontFamily(Font(R.font.axiformabold)),
                                    letterSpacing = (-0.43).sp,
                                    lineHeight = 17.sp
                                )
                            }
                        }

                        /*Text(
                            text = "2 - 6 Hours",
                            color = Color(0xFF02CBE3),
                            fontSize = 10.sp,
//            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                            letterSpacing = (-0.48).sp,
                            style = TextStyle(color = Color(0xFF02CBE3)),
                            modifier = Modifier.padding(top = 10.dp)
                        )*/
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { navigate("SCHEDULED") }) {
                        Column(
                            modifier = Modifier
                                .width(73.dp)
                                .height(86.dp)
                                .background(color = Color(0xFFAFF5FE), RoundedCornerShape(6.dp))
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFDEDEDE),
                                    shape = RoundedCornerShape(6.dp)
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "SCHEDULED",
                                color = Color.Black,
                                fontSize = 10.sp,
//            fontWeight = FontWeight.SemiBold,
                                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                                letterSpacing = (-0.48).sp,
                                modifier = Modifier.padding(top = 19.dp),
                                lineHeight = 19.sp
                            )
                            Row(
                                modifier = Modifier
                                    .padding(top = 28.dp)
                                    .width(60.dp)
                                    .height(21.dp)
                                    .background(color = Color(0xFFFFFFFF), RoundedCornerShape(17.dp))
                                    .border(
                                        width = 1.dp,
                                        color = Color(0xFFDEDEDE),
                                        shape = RoundedCornerShape(17.dp)
                                    ),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "set date",
                                    color = Color.Black,
                                    fontSize = 9.sp,
//            fontWeight = FontWeight.SemiBold,
                                    fontFamily = FontFamily(Font(R.font.axiformabold)),
                                    letterSpacing = (-0.43).sp,
                                    lineHeight = 17.sp
                                )
                            }
                        }


                        Text(
                            text = "10% CHEAPER",
                            color = Color(0xFF02CBE3),
                            fontSize = 10.sp,
//            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                            letterSpacing = (-0.48).sp,
//                    style = TextStyle(color = Color(0xFF02CBE3))
                            modifier = Modifier.padding(top = 15.dp)
                        )

                      /*  Text(
                            text = "choose your date",
                            color = Color.Black,
                            fontSize = 10.sp,
//            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                            letterSpacing = (-0.48).sp,
//                    style = TextStyle(color = Color(0xFF02CBE3))
                            modifier = Modifier.padding(top = 11.dp)
                        )*/
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { navigate("STANDARD") }) {
                        Column(
                            modifier = Modifier
                                .width(73.dp)
                                .height(86.dp)
                                .background(color = Color(0xFFFFFFFF), RoundedCornerShape(6.dp))
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFDEDEDE),
                                    shape = RoundedCornerShape(6.dp)
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "STANDARD",
                                color = Color.Black,
                                fontSize = 10.sp,
//            fontWeight = FontWeight.SemiBold,
                                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                                letterSpacing = (-0.48).sp,
                                modifier = Modifier.padding(top = 19.dp),
                                lineHeight = 19.sp
                            )
                            Row(
                                modifier = Modifier
                                    .padding(top = 28.dp)
                                    .width(60.dp)
                                    .height(21.dp)
                                    .background(color = Color(0xFFFFFFFF), RoundedCornerShape(17.dp))
                                    .border(
                                        width = 1.dp,
                                        color = Color(0xFFDEDEDE),
                                        shape = RoundedCornerShape(17.dp)
                                    ),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "6+ Hours",
                                    color = Color.Black,
                                    fontSize = 9.sp,
//            fontWeight = FontWeight.SemiBold,
                                    fontFamily = FontFamily(Font(R.font.axiformabold)),
                                    letterSpacing = (-0.43).sp,
                                    lineHeight = 17.sp
                                )
                            }
                        }

                        Text(
                            text = "SAME DAY",
                            color = Color.Black,
                            fontSize = 10.sp,
//            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                            letterSpacing = (-0.48).sp,
                            style = TextStyle(color = Color(0xFF02CBE3)),
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }


                }


            }
        }

    }
}

@Preview
@Composable
fun demo() {
    DeliveryTypeDialog(
        onDismissRequest = {},
        show = true,
        navigate = {}
    )
}