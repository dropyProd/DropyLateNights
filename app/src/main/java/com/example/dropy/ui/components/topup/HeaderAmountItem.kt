package com.example.dropy.ui.components.topup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R


@Composable
fun HeaderAmountItem(price: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            Row(
                modifier = Modifier
                    .width(100.dp)
                    .height(60.dp)
                    .border(1.dp, color = Color(0xAD39B54A), shape = RoundedCornerShape(10.dp))
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.mpesaa),
                    contentDescription = "",
                    modifier = Modifier.size(50.dp)
                )
            }

            Icon(
                Icons.Filled.ArrowDropDown,
                contentDescription = "",
                modifier = Modifier.size(42.dp),
                tint = Color(0xFF584AFF)
            )
        }


        Column(
            modifier = Modifier
                .width(120.dp)
                .height(60.dp)
                .background(color = Color(0xFFFFF9DB))
                .border(1.dp, color = Color(0xFFA8A1FF), shape = RoundedCornerShape(10.dp))
                .padding(5.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "WALLET BALANCE",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Text(
                text = "${price}/-",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}