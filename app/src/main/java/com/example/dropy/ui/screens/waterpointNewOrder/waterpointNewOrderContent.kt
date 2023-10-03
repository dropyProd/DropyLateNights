package com.example.dropy.ui.screens.waterpointNewOrder

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader

@Composable
fun WaterpointNewOrderContent(
    uiState: WaterPointNewOrderUiState,
    onLicensePlateChanged: (String) -> Unit,
    selectedCapacity: (String) -> Unit,
    createOrder: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ClippedHeader(title = "NEW ORDER", modifier = Modifier.padding(top = 22.dp))

        Row(
            modifier = Modifier
                .padding(top = 51.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "CHOOSE TRUCK SIZE",
                fontSize = 14.sp,
//                            fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformaheavy)
                ),
                letterSpacing = (-0.67).sp,
                lineHeight = 27.sp,
                color = Color.Black,
                modifier = Modifier.padding()
            )
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(55.dp),
                modifier = Modifier.padding(top = 63.dp)
            ) {
                itemWater(
                    image = R.drawable.truckcopy,
                    text = "5,000LT",
                    color = Color(0xFFAFF5FE), borderColor = Color(0xFFDEDEDE),
                    clickable = {
                        selectedCapacity("5,000LT")
                    }
                )
                itemWater(
                    image = R.drawable.truckwater,
                    text = "10,000LT",
                    color = Color(0xFFF5F5F5), borderColor = Color(0xFFDEDEDE),
                    clickable = {
                        selectedCapacity("10,000LT")
                    }
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(end = 14.dp, top = 30.dp, start = 15.dp)
                .fillMaxWidth(),
        ) {
            SimpleText(
                textSize = 10,
                text = "License plate",
                isExtraBold = true,
                font = Font(R.font.axiformaextrabold)
            )

            OutlinedTextField(
                value = uiState.licensePlate,
                onValueChange = { onLicensePlateChanged(it) },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .height(48.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Black,
                    focusedBorderColor = Color.Black,
                ),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.axiformaregular))
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 15.dp)
                .clickable { createOrder() },
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BackgroundedText(
                background = Color.Black,
                textColor = Color.White,
                text = "CREATE",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                vertical = 6
            )
        }
    }
}

@Composable
fun itemWater(
    image: Int,
    text: String,
    modifier: Modifier = Modifier,
    color: Color,
    borderColor: Color,
    clickable: () -> Unit
) {
    Column(
        modifier = modifier
            .width(130.dp)
            .height(140.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(13.dp)
            )
            .border(width = 1.dp, shape = RoundedCornerShape(13.dp), color = borderColor)
            .clickable {
                clickable()
            },
        verticalArrangement = Arrangement.spacedBy(46.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(44.dp)
                .height(44.dp)
                .padding(top = 9.dp),
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = text,
            fontSize = 17.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(
                Font(R.font.axiformablack)
            ),
            letterSpacing = (-0.82).sp,
            lineHeight = 32.sp
        )
    }
}

@Preview
@Composable
fun demo() {
    WaterpointNewOrderContent(
        uiState = WaterPointNewOrderUiState(),
        onLicensePlateChanged = {},
        createOrder = {},
        selectedCapacity = {})
}