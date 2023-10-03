package com.example.dropy.ui.components.balance

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R

@Composable
fun ActionBtn(text: String, textcolor: Color = Color.Black, color: Color, modifier: Modifier = Modifier, clicked: () -> Unit) {

    Row(
        modifier = Modifier
            .clickable {
                clicked()
            }
            .width(155.dp)
            .height(height = 58.dp)
            .background(color = color, shape = RoundedCornerShape(7.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Text(
            text = text,
            style = TextStyle(
                color = textcolor,
                fontSize = 17.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.axiformaextrabold))
            )
        )
    }
}