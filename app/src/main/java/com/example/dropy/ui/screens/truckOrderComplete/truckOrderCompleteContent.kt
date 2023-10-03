package com.example.dropy.ui.screens.truckOrderComplete

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R

@Composable
fun TruckOrderCompleteContent(
    findMoreWorkClicked: ()-> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .verticalScroll(rememberScrollState())
            .padding(top = 51.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.reviewbot),
            contentDescription = "QR code",
            modifier = Modifier
                .fillMaxWidth()
                .height(356.dp),
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = "HOORAAYY!",
            fontSize = 21.sp,
//                        fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(
                Font(R.font.axiformaheavy)
            ),
            letterSpacing = (-1.01).sp,
            lineHeight = 28.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 40.dp)
        )

        Text(
            text = "DELIVERY",
            fontSize = 18.sp,
//                        fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(
                Font(R.font.axiformaheavy)
            ),
            letterSpacing = (-0.86).sp,
            lineHeight = 41.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 44.dp)
        )
        Text(
            text = "COMPLETE",
            fontSize = 18.sp,
//                        fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(
                Font(R.font.axiformamedium)
            ),
            letterSpacing = (-0.86).sp,
            lineHeight = 41.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 22.dp)
        )


        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 44.dp)
                .width(154.dp)
                .height(43.dp)
                .background(color = Color.Black, RoundedCornerShape(27.dp))
                .border(width = 1.dp, color = Color(0x57707070), RoundedCornerShape(27.dp))
                .clickable { findMoreWorkClicked() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            androidx.compose.material.Text(
                text = "FIND MORE WORK",
                color = Color.White,
                fontSize = 10.sp,
//                        fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                letterSpacing = (-0.48).sp,
            )
        }
    }

}

@Preview
@Composable
fun demo(){
    TruckOrderCompleteContent(findMoreWorkClicked = {})
}