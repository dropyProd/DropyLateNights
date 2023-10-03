package com.example.dropy.ui.components.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.theme.DropyTheme

@Composable
fun OnBoardingScreenOne() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight().offset(y = 180.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = Color.Black.copy(alpha = 0.6f),
            modifier = Modifier
                .width(357.dp)
                .height(380.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomEnd = 125.dp,
                        bottomStart = 20.dp
                    )
                )

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 54.dp , start = 39.dp),
            ) {
                Text(
                    text = "SEND",
                    letterSpacing = (-1).sp,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    )
                )
                Text(
                    text = "ANYTHING",
                    letterSpacing = (-1).sp,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(252, 211, 19),
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .fillMaxWidth(0.8f),
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    )
                )
                Text(
                    text = "ANYWHERE",
                    letterSpacing = (-1).sp,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .fillMaxWidth(0.8f),
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    )
                )

                Text(
                    fontSize = 16.sp,
                    color = Color.White,
                    lineHeight = 32.sp,
                    letterSpacing = (-.5).sp,
                    modifier = Modifier
                        .padding(top = 80.dp, bottom = 47.dp)
                        .fillMaxWidth(),
                    text = "Dropy allows you to send anything as\nfast as possible. We are available\ncountrywide.",
                    fontFamily = FontFamily(
                        Font(R.font.axiformaregular)
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingOnePreview() {
    DropyTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            OnBoardingScreenOne()
        }
    }
}