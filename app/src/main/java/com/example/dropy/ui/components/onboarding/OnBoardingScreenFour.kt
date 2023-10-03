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
import com.example.dropy.ui.theme.DropyTheme
import com.example.dropy.ui.theme.DropyYellow

@Composable
fun OnBoardingScreenFour(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
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
                    text = "MAKE",
//                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    )
                )
                Text(
                    text = "MONEY",
//                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp,
                    color = Color(252 ,211,19),
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    )
                )
                Text(
                    text = "TODAY",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp,
                    color = Color.White,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    )
                )
            }
            Image(
                painter = painterResource(id = R.drawable.moneyicon),
                contentDescription = "Location icon",
                modifier = Modifier.size(width = 80.dp, height = 80.dp)
            )


        }
        Text(
            lineHeight = 24.sp,
            letterSpacing  = (-1).sp,
            text = "Create your online shop today and start selling to the millions on Dropy",
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top = 24.dp, bottom = 24.dp),
            fontFamily = FontFamily(
                Font(R.font.axiformaregular)
            )
        )

        Column(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .fillMaxHeight()
            ,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .weight(1f)
                        .fillMaxHeight()
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(40.dp)
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                            .background(Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "FASHION",
                            color = DropyYellow,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformablack)
                            )
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.clothes),
                        contentDescription = "clothes",
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .clip(RoundedCornerShape(bottomEnd = 24.dp))
                        ,
                        contentScale = ContentScale.FillWidth
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(40.dp)
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                            .background(Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "FOOD",
                            color = DropyYellow,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformablack)
                            )
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.food),
                        contentDescription = "Food",
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .clip(RoundedCornerShape(bottomEnd = 24.dp))
                        ,
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
                        .padding(end = 8.dp)
                        .weight(1f)
                        .fillMaxHeight()
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(40.dp)
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                            .background(Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "GROCERIES",
                            color = DropyYellow,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformablack)
                            )
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.groceries),
                        contentDescription = "groceries",
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .clip(RoundedCornerShape(bottomEnd = 24.dp))
                        ,
                        contentScale = ContentScale.FillWidth
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(40.dp)
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                            .background(Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "ELECTRONICS",
                            color = DropyYellow,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformablack)
                            )
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.computer),
                        contentDescription = "computer",
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .clip(RoundedCornerShape(bottomEnd = 24.dp))
                        ,
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun OnBoardingFourPreview() {
    DropyTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            OnBoardingScreenFour()
        }
    }
}