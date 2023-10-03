package com.example.dropy.ui.components.parcels.frontside.expressdeliveryperson

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.Time
import com.example.dropy.ui.theme.LightBlue


@Composable
fun DeliveryPerson(
    userProfile : Painter?,
    name:String,
    etaInMinutes: Int,
    isSelected : Boolean
){
    Column(
        modifier = Modifier
            .sizeIn(maxWidth = 120.dp)
            .padding(4.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .border(
                    3.dp, shape = CircleShape, color = if (isSelected) {
                        LightBlue
                    } else {
                        Color.Transparent
                    }
                )
            ,
            contentAlignment = Alignment.Center
        ) {
            LoadImage()
        }
        Text(
            text = name.uppercase(),
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
        )
        Time(timeInMin = etaInMinutes.toString())
    }
}

@Preview(showBackground = true)
@Composable
fun DeliveryPersonPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        DeliveryPerson(userProfile = null, name = "name", etaInMinutes = 34, isSelected = true )
    }
}