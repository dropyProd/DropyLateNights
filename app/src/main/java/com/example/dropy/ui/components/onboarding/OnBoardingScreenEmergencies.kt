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
import com.example.dropy.ui.theme.DropyYellow

@Composable
fun OnBoardingScreenEmergencies() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 40.dp, end = 42.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.7f),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "EMERGENCIES",
//                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = (-1.92).sp,
                    fontSize = 30.sp,
                    color = DropyGray,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    )
                )
                Spacer(modifier = Modifier.size(24.dp))

                Text(
                    text = "SAVING LIVES",
//                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp,
                    color = Color.White,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    )
                )

            }

        }
        Text(
            lineHeight = 24.sp,
            letterSpacing = (-0.77).sp,
            text = "Dropy connects you to the nearest\nemergency responders in your proximity",
            color = Color.White,
            //colout to be chaged later, used white to render durig designs
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top = 57.dp),
            fontFamily = FontFamily(
                Font(R.font.axiformaregular)
            )
        )
        Column(
            modifier = Modifier
                .padding(top = 71.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .width(144.dp)
                        .height(151.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                            .background(Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "AMBULANCE",
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformablack)
                            )
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.robot),
                        contentDescription = "clothes",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(bottomEnd = 24.dp)),
                        contentScale = ContentScale.FillWidth
                    )
                }
                Column(
                    modifier = Modifier
                        .width(144.dp)
                        .height(151.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                            .background(Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "BREAKDOWN",
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformablack)
                            )
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.robot),
                        contentDescription = "Food",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(bottomEnd = 24.dp)),
                        contentScale = ContentScale.FillWidth
                    )
                }

            }
            Spacer(modifier = Modifier.size(24.dp))
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier
                        .padding(end = 8.dp, top = 51.dp)
                        .width(144.dp)
                        .height(151.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                            .background(Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "ACCIDENT",
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformablack)
                            )
                        )
                        /*     Text(
                                 text = "ACCIDENT",
                                 color = Color.White,
                                 fontSize = 13.sp,
                                 fontWeight = FontWeight.Bold,
                                 fontFamily = FontFamily(
                                     Font(R.font.axiformablack)
                                 )
                             )*/
                    }

                    Image(
                        painter = painterResource(id = R.drawable.robot),
                        contentDescription = "groceries",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(bottomEnd = 24.dp)),
                        contentScale = ContentScale.FillWidth
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    /*  Box(
                          modifier = Modifier
                              .fillMaxWidth(0.8f)
                              .height(40.dp)
                              .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                              .background(Color.Black),
                          contentAlignment = Alignment.Center
                      ) {
                          Text(
                              text = "HEALTH RISK",
                              color = Color.White,
                              fontSize = 13.sp,
                              fontWeight = FontWeight.Bold,
                              fontFamily = FontFamily(
                                  Font(R.font.axiformablack)
                              )
                          )
                      }*/
/*
                    Image(
                        painter = painterResource(id = R.drawable.computer),
                        contentDescription = "computer",
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .clip(RoundedCornerShape(bottomEnd = 24.dp))
                        ,
                        contentScale = ContentScale.FillWidth
                    )*/
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingEmergenciesPreview() {
    DropyTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            OnBoardingScreenEmergencies()
        }
    }
}