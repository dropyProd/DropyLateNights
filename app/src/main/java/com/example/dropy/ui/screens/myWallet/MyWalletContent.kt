package com.example.dropy.ui.screens.myWallet

import com.example.dropy.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.ui.components.balance.Transactions
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader

@Composable
fun MyWalletContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ClippedHeader(title = "WALLET")


        Text(
            text = "18,750sh",
            fontSize = 30.sp,
//                        fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(
                Font(R.font.axiformaheavy)
            ),
            letterSpacing = (-1.44).sp,
            lineHeight = 24.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 38.dp, top = 31.dp)
        )

        Text(
            text = "CURRENT WALLET BALANCE",
            fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(
                Font(R.font.axiformaheavy)
            ),
            letterSpacing = (-.48).sp,
            lineHeight = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 40.dp, top = 21.dp)
        )


        Row(
            modifier = Modifier
                .padding(top = 27.dp, start = 46.dp, end = 46.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            moneyItem(
                text = "TOP UP",
                color = Color.Black,
                textColor = Color.White,
                borderColor = Color(0xFFDEDEDE),
                containerColor = Color.White
            )
            moneyItem(
                text = "WITHDRAW",
                color = Color.White,
                textColor = Color.Black,
                borderColor = Color(0xFFDEDEDE),
                borderColorIn = Color(0xFFDEDEDE),
                containerColor = Color(0xFFF5F5F5)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 42.dp, end = 29.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "TRANSACTIONS",
                fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformaheavy)
                ),
                letterSpacing = (-.48).sp,
                lineHeight = 18.sp,
                color = Color.Black
            )

            Row(
                modifier = Modifier
                    .width(35.dp)
                    .height(35.dp)
                    .background(color = Color.Black, shape = RoundedCornerShape(3.dp))
                    .padding(3.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.filternew),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = Color.White),
                    contentScale = ContentScale.FillWidth
                )
            }
        }

        Transactions()

    }
}


@Composable
fun moneyItem(
    text: String,
    color: Color,
    textColor: Color,
    borderColor: Color = Color.Transparent,
    borderColorIn: Color = Color.Transparent,
    containerColor: Color
) {

    Column(
        modifier = Modifier
            .width(130.dp)
            .height(140.dp)
            .background(color = containerColor)
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(14.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.walk),
            contentDescription = "",
            modifier = Modifier.size(64.dp)
        )

        BackgroundedText(
            background = color,
            textColor = textColor,
            text = text,
            font = Font(R.font.axiformaheavy),
            vertical = 5,
            borderColor = borderColorIn
        )
    }
}

@Preview
@Composable
fun demo() {
    MyWalletContent()
}