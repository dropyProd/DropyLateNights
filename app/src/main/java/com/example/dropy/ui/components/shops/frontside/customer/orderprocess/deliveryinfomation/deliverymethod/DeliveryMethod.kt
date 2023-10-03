package com.example.dropy.ui.components.shops.frontside.customer.orderprocess.deliveryinfomation.deliverymethod

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.TimeWithIcon

@Composable
fun DeliveryMethod(
    type: String,
    etaInMinutes: Int,
    price: Int,
    deliveryMethodIconUrl: String,
    onSelectClicked: () -> Unit
) {
    Column(
        modifier = Modifier.clickable {
            onSelectClicked()
        }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(9.dp)
    ) {
        Box(
            modifier = Modifier
                .width(64.dp)
                .fillMaxWidth()
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            /* LoadImage(imageUrl = deliveryMethodIconUrl)*/
            Image(
                painter = when {
                    type.equals("motobike") -> painterResource(id = R.drawable.motorbike)
                    type.equals("on_foot") -> painterResource(id = R.drawable.manwalk)
                    else -> painterResource(id = R.drawable.mountainbike)
                },
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
        }
        Text(
            text = type.uppercase(),
            letterSpacing = (-0.5).sp,
            fontFamily = FontFamily(Font(R.font.axiformablack)),
            fontSize = 14.sp
        )
        TimeWithIcon(
            timeInMin = when {
                type.equals("motobike") -> etaInMinutes
                type.equals("on_foot") -> etaInMinutes + 10
                else -> etaInMinutes + 7
            },
            fontWeight = FontWeight.ExtraBold,
            font = Font(R.font.axiformaextrabold)
        )
        SimpleText(
            text = "$price /-",
            textSize = 15,
            isBold = true,
            font = Font(R.font.axiformaheavy)
        )
        Button(
            onClick = {   onSelectClicked() },
            colors = ButtonDefaults.buttonColors(Color(88, 74, 255)),
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .height(32.dp),

            ) {
            Text(
                text = "select".uppercase(),
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier,
                letterSpacing = (-0.5).sp,
                fontFamily = FontFamily(Font(R.font.axiformaextrabold))
            )
        }
    }
}