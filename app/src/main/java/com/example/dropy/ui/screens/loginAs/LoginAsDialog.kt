package com.example.dropy.ui.screens.loginAs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Person
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
fun LoginAsDialog(
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
                    text = "LOGIN PROFILE",
                    color = Color.Black,
                    fontSize = 17.sp,
//            fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                    letterSpacing = (-0.82).sp,
                    lineHeight = 32.sp,
                    modifier = Modifier
                        .padding(top = 45.dp)
                        .align(Alignment.CenterHorizontally)
                )


                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(top = 48.dp)
                        .fillMaxWidth()
                        .padding(end = 17.dp, start = 17.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { navigate("CUSTOMER") }) {
                        Column(
                            modifier = Modifier
                                .width(108.dp)
                                .height(103.dp)
                                .background(color = Color.Transparent, RoundedCornerShape(6.dp))
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFDEDEDE),
                                    shape = RoundedCornerShape(6.dp)
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Icon(
                                Icons.Filled.Person,
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(top = 19.dp)
                                    .size(39.dp)
                            )
                            Text(
                                text = "CUSTOMER",
                                color = Color.Black,
                                fontSize = 10.sp,
//            fontWeight = FontWeight.SemiBold,
                                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                                letterSpacing = (-0.48).sp,
                                modifier = Modifier.padding(top = 13.dp),
                                lineHeight = 19.sp
                            )

                        }


                        Text(
                            text = "normal dropy customer",
                            color = Color.Black,
                            fontSize = 9.sp,
//            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily(Font(R.font.axiformabold)),
                            letterSpacing = (-0.43).sp,
                            lineHeight = 17.sp,
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
                        modifier = Modifier.clickable { navigate("SERVICE PROVIDER") }) {
                        Column(
                            modifier = Modifier
                                .width(108.dp)
                                .height(103.dp)
                                .background(color = Color.Black, RoundedCornerShape(6.dp))
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFDEDEDE),
                                    shape = RoundedCornerShape(6.dp)
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Icon(
                                Icons.Filled.Casino,
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(top = 19.dp)
                                    .size(39.dp),
                                tint = Color.White
                            )
                            Text(
                                text = "SERVICE\nPROVIDER",
                                color = Color.White,
                                fontSize = 10.sp,
//            fontWeight = FontWeight.SemiBold,
                                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                                letterSpacing = (-0.48).sp,
                                modifier = Modifier.padding(top = 13.dp),
                                lineHeight = 19.sp
                            )

                        }


                        Text(
                            text = "service provider profile",
                            color = Color.Black,
                            fontSize = 9.sp,
//            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily(Font(R.font.axiformabold)),
                            letterSpacing = (-0.43).sp,
                            lineHeight = 17.sp,
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

                }


            }
        }

    }
}

@Preview
@Composable
fun demo() {
    LoginAsDialog(
        onDismissRequest = {},
        show = true,
        navigate = {}
    )
}