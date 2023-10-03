package com.example.dropy.ui.screens.scanQRWater

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader

@Composable
fun ScanQRWaterContent(
    navigate: () -> Unit,
    useCode: () -> Unit,
    scanQRWaterUiState: ScanQRWaterUiState
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = Color.White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ClippedHeader(
            title = "QR ORDER SCAN", modifier = Modifier
                .padding(top = 28.dp)
                .align(Alignment.Start)
        )
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = 32.dp)
        ) {
            Text(
                text = "Scan QR",
                fontSize = 21.sp,
                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                color = Color.Black,
                lineHeight = 27.sp,
                letterSpacing = (-0.56).sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "To Complete Order",
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.axiformamedium)),
                color = Color.Black,
                lineHeight = 17.sp,
                letterSpacing = (-0.46).sp,
                modifier = Modifier.padding(top = 17.dp)
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(0.7f)
                .aspectRatio(1f)
                .clickable { navigate() }
        ) {
            Image(
                bitmap = scanQRWaterUiState.bitmap!!.asImageBitmap(),
                contentDescription = "QR code",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }

        Column(
            modifier = Modifier
                .padding(top = 26.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 5.dp, end = 8.dp)
                        .background(Color.LightGray)
                        .fillMaxWidth()
                        .weight(1f)
                ) {

                }
                androidx.compose.material3.Text(
                    text = "OR",
                    fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    ),
                    letterSpacing = (-0.43).sp,
                    lineHeight = 17.sp,
                    color = Color.Black,
                )
                Row(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 6.dp)
                        .background(Color.LightGray)
                        .fillMaxWidth()
                        .weight(1f)
                ) {

                }

            }



            Row(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .width(156.dp)
                    .height(49.dp)
                    .background(color = Color.Black, RoundedCornerShape(42.dp))
                    .border(width = 1.dp, color = Color(0x57707070), RoundedCornerShape(42.dp))
                    .clickable { useCode() },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {


                androidx.compose.material.Text(
                    text = "USE CODE",
                    color = Color.White,
                    fontSize = 10.sp,
//                        fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                    letterSpacing = (-0.48).sp,
                )
            }


            Text(
                text = "Let the DELIVERY PERSON scan this code\n" +
                        "to complete the order you can\n" +
                        "also give the SMS DELIVERY CODE\n" +
                        "to complete the order",
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.axiformaregular)),
                color = Color.Black,
                lineHeight = 17.sp,
                letterSpacing = (-0.48).sp,
                modifier = Modifier.padding(20.dp)
            )
            Text(
                text = "thank you".uppercase(),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 21.sp,
                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                color = Color.Black,
                lineHeight = 28.sp,
                letterSpacing = (-1.01).sp,
                modifier = Modifier
                    .padding(top = 36.dp)
            )
        }


    }
}

@Preview
@Composable
fun demo() {
    ScanQRWaterContent(navigate = {}, scanQRWaterUiState = ScanQRWaterUiState(), useCode = {})
}