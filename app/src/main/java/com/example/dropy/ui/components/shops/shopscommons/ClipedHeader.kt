package com.example.dropy.ui.components.shops.shopscommons

import com.example.dropy.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.ui.theme.LightBlue

@Composable
fun ClippedHeader(title: String, modifier: Modifier = Modifier, start: Int = 18, end: Int = 63) {
    Box(
        modifier = modifier
            .wrapContentWidth()
            .clip(RoundedCornerShape(bottomEnd = 48.dp))
            .background(Color.Black)
            .padding(start = start.dp, end = end.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = title.uppercase(),
            color = Color.White,
            modifier = Modifier
                .padding(/*start = 16.dp,*/ top = 8.dp, bottom = 8.dp/*, end = 8.dp*/),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.axiformabold)),
            letterSpacing = (-0.91).sp
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ClippedHeaderPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        ClippedHeader("some very long header")
    }
}