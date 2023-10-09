package com.example.dropy.ui.components.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.theme.DropyTheme

@Composable
fun OnBoardingScreenWater() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
//                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Column(
                modifier = Modifier
                    .offset(y = 24.dp)
                    .fillMaxWidth(0.6f),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logonewblack),
                    contentDescription = "Location iconv",
                    modifier = Modifier
                        .width(154.dp)
                        .height(89.dp)
                        .padding(start = 32.dp),
                    contentScale = ContentScale.FillWidth
                )
                BackgroundedText(
                    background = Color(0xFF02CBE3),
                    textColor = Color.Black,
                    text = "WATER",
                    vertical = 4,
                    modifier = Modifier
                        .offset(y = (-16).dp)
                        .padding(start = 90.dp),
                    font = Font(R.font.axiformablack)
                )

            }
            /*       Image(
                       painter = painterResource(id = R.drawable.location),
                       contentDescription = "Location icon",
                       modifier = Modifier
                           .size(width = 60.dp, height = 60.dp)
                   )*/
        }
        Text(
            lineHeight = 32.sp,
            text = "Water is life, we help you get clean water\neven in the most remote places ever.",
            fontSize = 16.sp,
            letterSpacing = (-0.77).sp,
            modifier = Modifier
                .padding(top = 94.dp, start = 39.dp),
            fontFamily = FontFamily(
                Font(R.font.axiformamedium)
            )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 40.dp),
            contentAlignment = Alignment.TopStart

        ) {
            Image(
                painter = painterResource(id = R.drawable.watertruck),
                contentDescription = "Water Truck",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        RoundedCornerShape(
                            topStart = 0.dp, topEnd = 0.dp,
                            bottomStart = 0.dp, bottomEnd = 40.dp
                        )
                    ),

                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingWaterPreview() {
    DropyTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            OnBoardingScreenWater()
        }
    }
}