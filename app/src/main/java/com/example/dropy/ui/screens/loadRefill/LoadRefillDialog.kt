package com.example.dropy.ui.screens.loadRefill

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.dropy.R
import com.example.dropy.ui.screens.deliveryType.DeliveryTypeDialog

@Composable
fun LoadRefillDialog(onDismissRequest: () -> Unit,
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
                    text = "ARE YOU LOADED OR\nNEED A REFILL?",
                    color = Color.Black,
                    fontSize = 14.sp,
//            fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                    letterSpacing = (-0.67).sp,
                    modifier = Modifier
                        .padding(top = 45.dp)
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center
                )


                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(top = 79.dp)
                        .fillMaxWidth()
                        .padding(end = 45.dp, start = 45.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { navigate("LOADED") }) {
                        Row(
                            modifier = Modifier
                                .size(73.dp)
                                .background(color = Color(0xFFFFFFFF), RoundedCornerShape(6.dp))
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFDEDEDE),
                                    shape = RoundedCornerShape(6.dp)
                                ),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "LOADED",
                                color = Color.Black,
                                fontSize = 10.sp,
//            fontWeight = FontWeight.SemiBold,
                                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                                letterSpacing = (-0.48).sp
                            )
                        }

                        Text(
                            text = "my tanker is full",
                            color = Color(0xFF02CBE3),
                            fontSize = 10.sp,
//            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                            letterSpacing = (-0.48).sp,
                            style = TextStyle(color = Color(0xFF02CBE3)),
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { navigate("REFILL") }) {
                        Row(
                            modifier = Modifier
                                .size(73.dp)
                                .background(color = Color(0xFFAFF5FE), RoundedCornerShape(6.dp))
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFDEDEDE),
                                    shape = RoundedCornerShape(6.dp)
                                ),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "REFILL",
                                color = Color.Black,
                                fontSize = 10.sp,
//            fontWeight = FontWeight.SemiBold,
                                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                                letterSpacing = (-0.48).sp
                            )
                        }


                        Text(
                            text = "Tanker needs a\nrefill",
                            color = Color(0xFF02CBE3),
                            fontSize = 10.sp,
//            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                            letterSpacing = (-0.48).sp,
//                    style = TextStyle(color = Color(0xFF02CBE3))
                            modifier = Modifier.padding(top = 18.dp),
                            textAlign = TextAlign.Center
                        )
/*
                        Text(
                            text = "12 - 48 Hours",
                            color = Color(0xFF02CBE3),
                            fontSize = 10.sp,
//            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                            letterSpacing = (-0.48).sp,
//                    style = TextStyle(color = Color(0xFF02CBE3))
                            modifier = Modifier.padding(top = 11.dp)
                        )*/
                    }
                }


            }
        }

    }
}

@Preview
@Composable
fun demo() {
    LoadRefillDialog(
        onDismissRequest = {},
        show = true,
        navigate = {}
    )
}

