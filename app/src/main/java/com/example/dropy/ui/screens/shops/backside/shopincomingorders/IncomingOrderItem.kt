package com.example.dropy.ui.screens.shops.backside.shopincomingorders

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import  com.example.dropy.R
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.screens.pickCustomer.backgroundedIcon
import com.example.dropy.ui.theme.LightBlue

@Composable
fun IncomingOrderItem(type: String) {
    val bgColor = if (type.equals("collect")) Color(0xFFFFF9DB) else Color.Transparent
    val borderColor = if (type.equals("collect")) Color(0x66707070) else Color(0xFFA8A1FF)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(bgColor, shape = RoundedCornerShape(7.dp))
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(7.dp))
            .height(100.dp)
            .padding(vertical = 12.dp, horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.imgone),
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .clip(
                        CircleShape
                    ),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "CHIRAG",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontFamily = FontFamily(Font(R.font.axiformablack)),
                    color = Color.Black
                )
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = "ORDER #567897",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontFamily = FontFamily(Font(R.font.axiformablack)),
                    color = Color.Black
                )
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BackgroundedText(
                    background = Color(0xFF8A8A8A),
                    textColor = Color.White,
                    text = "UNPROCESSED",
                    textSize = 10,
                    vertical = 5,
                    font = Font(R.font.axiformabold)
                )
                BackgroundedText(
                    background = Color.Black,
                    textColor = Color.White,
                    text = "DELIVERY",
                    textSize = 10,
                    vertical = 5,
                    font = Font(R.font.axiformabold)
                )

            }

            BackgroundedText(
                background = Color.Transparent,
                textColor = Color.Black,
                text = "2 ITEMS",
                textSize = 10,
                vertical = 3,
                borderColor = Color.Black
            )
        }

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(start = 29.dp).fillMaxHeight()
        ) {
            Text(
                text = "2,250/-",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontFamily = FontFamily(Font(R.font.montserratbold)),
                    color = Color.Black
                )
            )

            BackgroundedText(
                background = Color(0XFFFCD313),
                textColor = Color.Black,
                text = "PAID",
                textSize = 12,
                vertical = 3,
                font = Font(R.font.axiformaextrabold),
            )

            Text(
                text = "03:15PM",
                style = TextStyle(
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.axiformabold)),
                    color = Color(0xFF505050)
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun demoo() {
    IncomingOrderItem(type = "collect")
}