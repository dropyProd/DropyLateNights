package com.example.dropy.ui.components.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.dropy.ui.theme.DropyGray
import com.example.dropy.ui.theme.DropyTheme

@Composable
fun OnBoardingScreenFive() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
//                .weight(2f)
                .fillMaxWidth()
                .padding(top = 24.dp, start = 34.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "BODA.",
                fontSize = 58.sp,
                letterSpacing = (-2.78).sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black,
                fontFamily = FontFamily(
                    Font(R.font.axiformablack)
                )

            )
            Text(
                text = "BILA STRESS",
                fontSize = 30.sp,
                letterSpacing = (-1).sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.axiformablack)),
                color = DropyGray
            )

            Text(
                letterSpacing = (-1).sp,
                lineHeight = 32.sp,
                text = "Order a boda boda as fast as possible.\nYou can even order from your favorite\nrider. We are the best.",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 42.dp), fontFamily = FontFamily(
                    Font(R.font.axiformamedium)
                )

            )
        }
        Box(
            modifier = Modifier
                .padding(top = 83.dp)
                .fillMaxWidth()
                .height(310.dp)
//                .aspectRatio(4 / 3f)
                //     .weight(1f)
                .background(Color.White),
            contentAlignment = Alignment.BottomCenter

        ) {
            Image(
                painter = painterResource(id = R.drawable.boda),
                contentDescription = "Bodaa",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 104.dp, bottomEnd = 106.dp)),
                contentScale = ContentScale.FillWidth
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingFivePreview() {
    DropyTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            OnBoardingScreenFive()
        }
    }
}