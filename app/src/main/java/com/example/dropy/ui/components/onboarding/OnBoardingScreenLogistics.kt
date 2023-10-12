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
import com.example.dropy.ui.theme.DropyGray
import com.example.dropy.ui.theme.DropyTheme

@Composable
fun OnBoardingScreenLogistics() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(top = 50.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 34.dp, end = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "LOGISTICS",
//                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 43.sp,
                    letterSpacing = (-2.06).sp,
                    fontFamily = FontFamily(
                        Font(R.font.axiformaheavy)
                    ),
                    color = Color.Black,
                    lineHeight = 49.sp
                )
                Text(
                    text = "MOVE ANYTHING",
//                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 27.sp,
                    letterSpacing = (-1.3).sp,
                    color = DropyGray,
                    fontFamily = FontFamily(
                        Font(R.font.axiformaheavy)
                    ),
                    modifier = Modifier.padding(top = 15.dp),
                    lineHeight = 49.sp
                )

            }

        }
        Text(
            lineHeight = 32.sp,
            text = "We help you move anything from\nanywhere to any destination of your\nchoice at an affordable rate.",
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top = 57.dp, start = 34.dp, end = 24.dp),
            fontFamily = FontFamily(
                Font(R.font.axiformamedium)
            ),
            letterSpacing = (-.77).sp
        )
        Box(
            modifier = Modifier
                .padding(top = 76.dp)
                .fillMaxWidth()
                .weight(1f)
                .aspectRatio(4 / 3f)
                .clip(
                    RoundedCornerShape(
                        topStart = 40.dp, bottomEnd = 40.dp
                    )
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.truck),
                contentDescription = "Online shopping",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        RoundedCornerShape(
                            topStart = 60.dp, bottomEnd = 70.dp
                        )
                    ),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingLogisticsPreview() {
    DropyTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            OnBoardingScreenLogistics()
        }
    }
}