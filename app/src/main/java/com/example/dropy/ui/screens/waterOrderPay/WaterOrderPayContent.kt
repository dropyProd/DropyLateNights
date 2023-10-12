package com.example.dropy.ui.screens.waterOrderPay

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeUiState
import com.example.dropy.ui.screens.waterOrderDetails.WaterOrderDetailsUiState

@Composable
fun WaterOrderPayContent(
    payClicked: () -> Unit,
    tankerBoreholeUiState: TankerBoreholeUiState,
    waterOrderDetailsUiState: WaterOrderDetailsUiState
) {

   Box(modifier = Modifier.fillMaxSize().background(Color.White)){
       Column(
           modifier = Modifier
               .fillMaxSize()
               .background(Color.White)
               .padding(start = 17.dp, end = 16.dp)
               .verticalScroll(rememberScrollState())
       ) {
           Box(modifier = Modifier.fillMaxWidth()) {
               Column() {
                   Column(modifier = Modifier.padding(top = 33.dp)) {
                       Column(
                           modifier = Modifier
                               .fillMaxWidth()
                               .wrapContentHeight()
                               .background(color = Color(0xFFF5F5F5), RoundedCornerShape(20.dp))
                               .border(
                                   width = 1.dp,
                                   color = Color(0xFFDEDEDE),
                                   RoundedCornerShape(20.dp)
                               )
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
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(end = 25.dp),
                               horizontalArrangement = Arrangement.SpaceBetween
                           ) {
                               Text(
                                   text = "TOTAL ALLOCATED TRUCKS",
                                   fontSize = 9.sp,
//                            fontWeight = FontWeight.ExtraBold,
                                   fontFamily = FontFamily(
                                       Font(R.font.axiformablack)
                                   ),
                                   letterSpacing = (-0.43).sp,
                                   lineHeight = 17.sp,
                                   color = Color.Black,
                                   modifier = Modifier.padding(top = 34.dp, start = 35.dp)
                               )

                               Row(
                                   modifier = Modifier
                                       .padding(top = 31.dp)
                                       .width(17.dp)
                                       .height(17.dp)
                                       .background(
                                           color = Color(0xFF02CBE3),
                                           shape = CircleShape
                                       )
                                       .border(
                                           width = 1.dp,
                                           color = Color(0xFFDEDEDE),
                                           shape = CircleShape
                                       )
                               ) {
                                   Text(
                                       text = (waterOrderDetailsUiState.tenTrucks.size + waterOrderDetailsUiState.fiveTrucks.size).toString(),
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
                           }

                           Row(
                               modifier = Modifier
                                   .padding(start = 83.dp, end = 64.dp, top = 32.dp)
                                   .fillMaxWidth()
                                   .height(1.dp)
                                   .background(color = Color(0xFFDEDEDE))
                           ) {

                           }

                           Text(
                               text = "COST",
                               fontSize = 9.sp,
//                            fontWeight = FontWeight.ExtraBold,
                               fontFamily = FontFamily(
                                   Font(R.font.axiformablack)
                               ),
                               letterSpacing = (-0.43).sp,
                               lineHeight = 17.sp,
                               color = Color.Black,
                               modifier = Modifier.padding(top = 17.dp, start = 34.dp)
                           )

                           Row(
                               modifier = Modifier.fillMaxWidth(),
                               verticalAlignment = Alignment.CenterVertically,
                               horizontalArrangement = Arrangement.SpaceBetween
                           ) {
                               Column(modifier = Modifier.padding(top = 27.dp)) {
                                   Row(modifier = Modifier.padding(start = 34.dp)) {
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

                                   Row(modifier = Modifier.padding(top = 7.dp, start = 34.dp)) {
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
                                   Text(
                                       text = "DELIVERY COST",
                                       fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                       fontFamily = FontFamily(
                                           Font(R.font.axiformablack)
                                       ),
                                       letterSpacing = (-0.43).sp,
                                       lineHeight = 17.sp,
                                       color = Color.Black,
                                       modifier = Modifier.padding(top = 31.dp, start = 35.dp)
                                   )

                                   Text(
                                       text = "TOTAL COST",
                                       fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                       fontFamily = FontFamily(
                                           Font(R.font.axiformaheavy)
                                       ),
                                       letterSpacing = (-0.48).sp,
                                       lineHeight = 19.sp,
                                       color = Color.Black,
                                       modifier = Modifier.padding(top = 39.dp, start = 36.dp)
                                   )

                               }

                               Column(
                                   modifier = Modifier
                                       .padding(top = 31.dp, end = 12.dp)
                               ) {
                                   Text(
                                       text = "${waterOrderDetailsUiState.tenTrucks.size * 4000}/-",
                                       fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                       fontFamily = FontFamily(
                                           Font(R.font.axiformablack)
                                       ),
                                       letterSpacing = (-0.48).sp,
                                       lineHeight = 19.sp,
                                       color = Color(0xFF74728A)
                                   )

                                   Text(
                                       text = "${waterOrderDetailsUiState.fiveTrucks.size * 2000}/-",
                                       fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                       fontFamily = FontFamily(
                                           Font(R.font.axiformablack)
                                       ),
                                       letterSpacing = (-0.48).sp,
                                       lineHeight = 19.sp,
                                       color = Color(0xFF74728A),
                                       modifier = Modifier.padding(top = 19.dp)
                                   )

                                   Text(
                                       text = "1,200/-",
                                       fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                       fontFamily = FontFamily(
                                           Font(R.font.axiformablack)
                                       ),
                                       letterSpacing = (-0.48).sp,
                                       lineHeight = 19.sp,
                                       color = Color(0xFF74728A),
                                       modifier = Modifier.padding(top = 31.dp)
                                   )


                                   Row(
                                       modifier = Modifier
                                           .padding(top = 33.dp)
                                           .width(57.dp)
                                           .height(17.dp)
                                           .background(
                                               color = Color.Black,
                                               shape = RoundedCornerShape(7.dp)
                                           ),
                                       horizontalArrangement = Arrangement.Center,
                                       verticalAlignment = Alignment.CenterVertically
                                   ) {
                                       Text(
                                           text = "${(waterOrderDetailsUiState.tenTrucks.size * 4000) + (waterOrderDetailsUiState.fiveTrucks.size * 2000)}/-",
                                           fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                                           fontFamily = FontFamily(
                                               Font(R.font.axiformablack)
                                           ),
                                           letterSpacing = (-0.48).sp,
                                           lineHeight = 19.sp,
                                           color = Color.White,
                                           /*modifier = Modifier.padding(
                                               start = 10.dp,
                                               end = 5.dp,
                                               top = 4.dp,
                                               bottom = 1.dp
                                           )*/
                                       )
                                   }
                               }

                           }

                           Row(
                               modifier = Modifier
                                   .padding(start = 73.dp, end = 74.dp, top = 34.dp)
                                   .fillMaxWidth()
                                   .height(1.dp)
                                   .background(color = Color(0xFFDEDEDE))
                           ) {

                           }

                           Text(
                               text = "LEAVE A NOTE",
                               fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                               fontFamily = FontFamily(
                                   Font(R.font.axiformaheavy)
                               ),
                               letterSpacing = (-0.48).sp,
                               lineHeight = 19.sp,
                               color = Color.Black,
                               modifier = Modifier.padding(top = 44.dp, start = 37.dp)
                           )

                           Row(
                               modifier = Modifier
                                   .padding(top = 6.dp, start = 58.dp, bottom = 50.dp)
                                   .clickable { }
                                   .padding(6.dp)
                                   .wrapContentSize()
                                   .background(Color.Black, shape = CircleShape),
                               verticalAlignment = Alignment.CenterVertically,
                               horizontalArrangement = Arrangement.Center

                           ) {
                               Icon(Icons.Filled.Add, contentDescription = "", tint = Color.White)
                           }

                       }
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
                       text = "ORDER PAYMENT",
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
                           end = 13.dp,
                           top = 4.dp,
                           bottom = 4.dp
                       )
                   )
               }
           }
       }

       Button(
           onClick = {
               payClicked()
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
               text = "pay",
               fontWeight = FontWeight.W900,
               textColor = Color.White

           )
       }
     /*  Row(
           modifier = Modifier
               .align(Alignment.BottomEnd)
               .padding(top = 45.dp)
               .width(156.dp)
               .height(49.dp)
               .background(color = Color(0xFF02CBE3), RoundedCornerShape(42.dp))
               .border(width = 1.dp, color = Color(0x57707070), RoundedCornerShape(42.dp))
               .clickable { payClicked() },
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.Center
       ) {
           androidx.compose.material.Text(
               text = "PAY",
               color = Color.Black,
               fontSize = 10.sp,
//                        fontWeight = FontWeight.SemiBold,
               fontFamily = FontFamily(Font(R.font.axiformaheavy)),
               letterSpacing = (-0.48).sp,
           )
       }*/
   }
}

@Preview
@Composable
fun demo() {
    WaterOrderPayContent(
        payClicked = {},
        tankerBoreholeUiState = TankerBoreholeUiState(),
        waterOrderDetailsUiState = WaterOrderDetailsUiState()
    )
}