package com.example.dropy.ui.components.balance

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R

@Composable
fun BalanceHead(clicked: () -> Unit) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {

        Column(
            modifier = Modifier
                .width(220.dp)
                .height(130.dp)
                .border(width = 1.dp, color = Color(0xFFCCC9FC), shape = RoundedCornerShape(18.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color(0x26505050),
                            Color.White
                        )
                    ), shape = RoundedCornerShape(18.dp)
                )

                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "BALANCE",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                ),
                modifier = Modifier.padding(top =17.dp )
            )
            Row(
                modifier = Modifier
                    .padding(top = 35.dp)
                    .align(Alignment.End)
                    .wrapContentSize()
                    .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 11.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "14,200/-",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                    )
                )
            }

        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            ActionBtn(text = "WITHDRAW", color = Color(0xFFFCD313), clicked = clicked)
            ActionBtn(text = "TOP UP", color = Color.Black, clicked = clicked, textcolor = Color.White)
        }
    }
}

@Preview
@Composable
fun demo(){
    BalanceHead {

    }
}