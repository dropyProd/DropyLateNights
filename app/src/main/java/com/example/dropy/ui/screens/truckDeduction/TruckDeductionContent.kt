package com.example.dropy.ui.screens.truckDeduction

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R

@Composable
fun TruckDeductionContent(
    navigate:(String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Transparent)
            .padding(top = 26.dp, bottom = 90.dp)
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .height(36.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "200/- WILL BE DEDUCTED FROM WALLET",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 10.sp,
//                            fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.axiformabold)),
                    letterSpacing = (-0.48).sp,
                    lineHeight = 18.sp
                )
            )

        }

        Row(
            modifier = Modifier
                .padding(top = 46.dp)
                .fillMaxWidth()
                .background(color = Color.Transparent)
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 156.dp)
                    .width(41.dp)
                    .height(41.dp)
                    .background(Color.Black, shape = CircleShape)
                    .clickable { navigate("YES") },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "YES",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 10.sp,
//                            fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.axiformabold)),
                        letterSpacing = (-0.48).sp,
                        lineHeight = 18.sp
                    )
                )

            }
            Row(
                modifier = Modifier
                    .padding(start = 28.dp)
                    .width(41.dp)
                    .height(41.dp)
                    .background(Color.Red, shape = CircleShape).clickable { navigate("NO") },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "NO",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 10.sp,
//                            fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.axiformabold)),
                        letterSpacing = (-0.48).sp,
                        lineHeight = 18.sp
                    )
                )

            }
        }
    }
}

@Preview
@Composable
fun demo(){
    TruckDeductionContent(navigate = {})
}