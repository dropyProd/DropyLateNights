package com.example.dropy.ui.components.productpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.theme.LightBlue

@Composable
fun QuantityButton(image: Int, click: () -> Unit) {
    Row(
        modifier = Modifier
            .background(color = LightBlue, shape = RoundedCornerShape(4.dp))
            .wrapContentSize()
            .padding(6.dp)
            .clickable { click() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            modifier = Modifier.size(12.dp),
            colorFilter = ColorFilter.tint(color = Color.White)
        )

    }
}