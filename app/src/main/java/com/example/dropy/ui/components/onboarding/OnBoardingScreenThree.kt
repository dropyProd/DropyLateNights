package com.example.dropy.ui.components.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.example.dropy.ui.theme.DropyTheme
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.R
import com.example.dropy.ui.theme.DropyGray

@Composable
fun OnBoardingScreenThree() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 38.dp, end = 38.dp, top = 51.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "SHOP.",
//                fontWeight = FontWeight.ExtraBold,
                fontSize = 58.sp,
                letterSpacing = (-2.78).sp,
                color = Color.Black,
                fontFamily = FontFamily(
                    Font(R.font.axiformaheavy)
                ),
                lineHeight = 49.sp
            )
            Text(
                text = "WE DELIVER",
//                fontWeight = FontWeight.ExtraBold,
                fontSize = 27.sp,
                letterSpacing = (-1.3).sp,
                fontFamily = FontFamily(
                    Font(R.font.axiformablack)
                ),
                color = DropyGray,
                modifier = Modifier.padding(top = 9.dp),
                lineHeight = 49.sp
            )
            Text(
                lineHeight = 32.sp,
                letterSpacing = (-.78).sp,
                text = "Dropy has integrated thousands of\nshops countrywide to help you shop\ndirectly from your favorite sellers. ",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(top = 52.dp),
                fontFamily = FontFamily(
                    Font(R.font.axiformamedium)
                )
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 54.dp)
                .width(466.dp)
                .height(349.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 40.dp, bottomEnd = 40.dp
                    )
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.onboardingthreelogo),
                contentDescription = "Online shopping",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        RoundedCornerShape(
                            topStart = 104.dp, bottomEnd = 110.dp
                        )
                    ),
                contentScale = ContentScale.FillWidth
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingThreePreview() {
    DropyTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            OnBoardingScreenThree()
        }
    }
}