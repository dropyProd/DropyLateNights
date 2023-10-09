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
fun OnboardingScanToPay() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(top = 49.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
//                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(start = 44.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "SCAN.",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 58.sp,
                    letterSpacing = (-1).sp,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    )
                )
                Text(
                    text = "TO PAY",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 27.sp,
                    letterSpacing = (-1).sp,
                    color = DropyGray,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    ),
                    modifier = Modifier.padding(top = 10.dp)
                )

            }

        }
        Text(
            lineHeight = 32.sp,
            text = "Dropy is introducing one of the most\neasiest and convenient way to pay for\nyour goods & services.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(top = 51.dp, start = 44.dp),
            fontFamily = FontFamily(
                Font(R.font.axiformamedium)
            ),
            letterSpacing = (-.77).sp
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(top = 61.dp),
            contentAlignment = Alignment.TopStart

        ) {
            Image(
                painter = painterResource(id = R.drawable.scan),
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
fun OnBoardingScanPreview() {
    DropyTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            OnboardingScanToPay()
        }
    }
}