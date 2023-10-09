package com.example.dropy.ui.screens.waterTransactionComplete

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R

@Composable
fun WaterTransactionCompleteContent(
    navigateTrack: ()-> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .padding(top = 17.dp)
                .width(159.dp)
                .height(17.dp)
                .background(
                    color = Color(0xFF02CBE3),
                    shape = RoundedCornerShape(8.dp)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "TRANSACTION COMPLETED",
                fontSize = 7.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformablack)
                ),
                letterSpacing = (-0.34).sp,
                lineHeight = 13.sp,
                color = Color.Black,
                modifier = Modifier.padding(
                    start = 14.dp,
                    end = 4.dp
                )
            )
        }

        Text(
            text = "THANK YOU",
            fontSize = 21.sp,
//                        fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(
                Font(R.font.axiformaheavy)
            ),
            letterSpacing = (-1.01).sp,
            lineHeight = 37.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 32.dp)
        )

        Text(
            text = "WATER IS ON THE WAY",
            fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(
                Font(R.font.axiformablack)
            ),
            letterSpacing = (-0.48).sp,
            lineHeight = 37.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 6.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.traileeer),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 25.dp)
                .fillMaxWidth()
                .height(421.dp),
            contentScale = ContentScale.FillWidth
        )

        Row(
            modifier = Modifier
                .padding(top = 40.dp)
                .width(133.dp)
                .height(36.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(24.dp)
                )
        ) {
            Text(
                text = "RECIEPT",
                fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformablack)
                ),
                letterSpacing = (-0.48).sp,
                lineHeight = 19.sp,
                color = Color.Black,
                modifier = Modifier.padding(
                    start = 40.dp,
                    end = 40.dp,
                    top = 13.dp,
                    bottom = 13.dp
                )
            )
        }

        Row(
            modifier = Modifier
                .padding(top = 25.dp)
                .width(133.dp)
                .height(36.dp)
                .background(
                    color = Color(0xFF02CBE3),
                    shape = RoundedCornerShape(24.dp)
                )
                .clickable { navigateTrack() }
        ) {
            Text(
                text = "TRACK ORDER",
                fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformablack)
                ),
                letterSpacing = (-0.48).sp,
                lineHeight = 19.sp,
                color = Color.Black,
                modifier = Modifier.padding(
                    start = 31.dp,
                    end = 18.dp,
                    top = 13.dp,
                    bottom = 13.dp
                )
            )
        }



    }
}

@Preview
@Composable
fun demo(){
    WaterTransactionCompleteContent(navigateTrack = {})
}