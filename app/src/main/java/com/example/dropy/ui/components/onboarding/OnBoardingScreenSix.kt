package com.example.dropy.ui.components.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.theme.DropyTheme

@Composable
fun OnBoardingScreenSix() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .background(Color.White),
            contentAlignment = Alignment.TopStart

        ) {
            Image(
                painter = painterResource(id = R.drawable.destination),
                contentDescription = "Location icon",
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "RIDE",
                letterSpacing = (-1).sp,
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )
            Text(
                text = "ANYWHERE",
                letterSpacing = (-1).sp,
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(252, 211, 19),
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                fontFamily = FontFamily(
                    Font(R.font.axiformablack)
                )
            )
            Text(
                text = "FAST & EASY",
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
                letterSpacing = (-1).sp,
                lineHeight = 32.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth(0.8f),
                text = "If you need to get to somewhere faster and easily, just use Dropy. Thousands of riders available at your request!",
                fontSize = 16.sp,
                fontFamily = FontFamily(
                    Font(R.font.axiformaregular)
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingSixPreview() {
    DropyTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            OnBoardingScreenSix()
        }
    }
}