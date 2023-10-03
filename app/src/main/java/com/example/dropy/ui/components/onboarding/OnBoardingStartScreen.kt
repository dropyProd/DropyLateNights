package com.example.dropy.ui.components.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.theme.DropyTheme

@Composable
fun OnBoardingStartScreen(){
    Column(
        modifier = Modifier
            .fillMaxHeight(0.85f)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f),
            contentAlignment = Alignment.Center,

        ) {
            Image(
                painter = painterResource(id = R.drawable.dropylogo),
                contentDescription = "Dropy logo",
                modifier = Modifier
                    .fillMaxSize(0.5f)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                    ,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .border(1.dp, Color(227, 226, 217), RoundedCornerShape(10.dp))
                            .width(182.dp)
                            .height(188.dp)
                        ,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.shopsicon),
                            contentDescription = "shops icon",
                            modifier = Modifier
//                            .fillMaxWidth()
//                            .fillMaxHeight(0.8f)
                                .height(111.dp)
                                .width(142.dp)
                        )
                        Text(
                            text = "SHOPS",
                            fontSize = 14.sp,
                            letterSpacing  = (-1).sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier
                                .padding(8.dp),
                        )
                    }

                }


                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                    ,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .border(1.dp, Color(227, 226, 217), RoundedCornerShape(10.dp))
                            .width(182.dp)
                            .height(188.dp)
                        ,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.parcelsicon),
                            contentDescription = "parcels icon",
                            modifier = Modifier
//                            .fillMaxWidth()
//                            .fillMaxHeight(0.8f)
                                .height(111.dp)
                                .width(152.dp)
                        )
                        Text(
                            text = "PARCELS",
                            fontSize = 14.sp,
                            letterSpacing  = (-1).sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier
                                .padding(8.dp)
                        )
                    }
                }

            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .border(1.dp, Color(227, 226, 217), RoundedCornerShape(10.dp))
                        .width(381.dp)
                        .height(199.dp)
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Image(
                        painter = painterResource(id = R.drawable.ridesicon),
                        contentDescription = "rides icon",
                        modifier = Modifier
//                        .fillMaxWidth()
//                        .fillMaxHeight(0.8f)
                            .height(151.dp)
                            .width(308.dp)
                    )
                    Text(
                        text = "RIDES",
                        fontSize = 14.sp,
                        letterSpacing  = (-1).sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }


            }
        }

    }
}
@Preview(showBackground = true)
@Composable
fun OnBoardingStartPreview() {
    DropyTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            OnBoardingStartScreen()
        }
    }
}