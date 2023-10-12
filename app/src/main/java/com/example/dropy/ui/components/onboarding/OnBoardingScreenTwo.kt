package com.example.dropy.ui.components.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.dropy.ui.theme.DropyGray
import com.example.dropy.ui.theme.DropyTheme

@Composable
fun OnBoardingScreenTwo() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(top = 50.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 31.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "REALTIME.",
//                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 43.sp,
                    letterSpacing = (-2.06).sp,
                    fontFamily = FontFamily(
                        Font(R.font.axiformaheavy)
                    ),
                    lineHeight = 51.sp
                )
                /*Text(
                    text = "ORDER",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp,
                    letterSpacing = (-1).sp,
                    color = Color.Black,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    ),
                    modifier = Modifier.padding(top = 15.dp)
                )*/
                Text(
                    text = "TRACKING",
//                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp,
                    letterSpacing = (-1.44).sp,
                    fontFamily = FontFamily(
                        Font(R.font.axiformaheavy)
                    ),
                    color = DropyGray,
                    modifier = Modifier.padding(top = 15.dp),
                    lineHeight = 51.sp
                )
            }

        }
        Text(
            lineHeight = 32.sp,
            text = "Dropy allows you to track all your orders\nusing state of the art AI tracking\nalgorithms",
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top = 40.dp, start = 40.dp),
            fontFamily = FontFamily(
                Font(R.font.axiformamedium)
            ),
            letterSpacing = (-.77).sp
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 40.dp),
            contentAlignment = Alignment.TopStart

        ) {
            Image(
                painter = painterResource(id = R.drawable.map),
                contentDescription = "Location icon",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingTwoPreview() {
    DropyTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            OnBoardingScreenTwo()
        }
    }
}