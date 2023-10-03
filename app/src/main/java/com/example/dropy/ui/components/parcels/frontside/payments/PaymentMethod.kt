package com.example.dropymain.presentation.components.parcels.frontside.payments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.ui.theme.LightBlue

@Composable
fun PaymentMethod(
    image: Painter,
    myIndex:Int,
    add:(Int)->Unit
){
    Box(
        modifier = Modifier
            .width(120.dp)
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(3 / 2f)
                .padding(top = 8.dp, end = 8.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(2.dp, Color.LightGray, RoundedCornerShape(8.dp))
            ,
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = image,
                contentDescription = "payment option logo",
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(8.dp))
                ,
                contentScale = ContentScale.FillWidth
            )
        }
        IconButton(
            onClick = {
                      add(myIndex)
            },
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(LightBlue)
                .align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "",
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentMethodPreview(){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        PaymentMethod(
            image = painterResource(id = R.drawable.mpesa),
            myIndex = 1,
            add = {}
        )
    }
}