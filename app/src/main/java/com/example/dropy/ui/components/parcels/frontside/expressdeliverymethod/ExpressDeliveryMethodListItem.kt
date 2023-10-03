package com.example.dropy.ui.components.parcels.frontside.expressdeliverymethod

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.Time


@Composable
fun ExpressDeliveryMethodListItem(
    type:String,
    etaInMinutes: Int,
    price :Int,
    image : Painter,
    select : (index:Int)->Unit,
    myIndex: Int
){
    Column(
        modifier = Modifier
            .padding(8.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(64.dp)
                .fillMaxWidth()
                .aspectRatio(1f)
            ,
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = image,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                ,
                contentScale = ContentScale.FillWidth
            )
        }
        Text(text = type.uppercase())
        Time(timeInMin = etaInMinutes.toString())
        SimpleText(textSize = 21, text = "${price}/-", isBold = true)
        Button(
            onClick = {
                      select(myIndex)
            },
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .height(32.dp)
            ,

            ) {
            Text(
                text = "select".uppercase(),
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier
                ,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DeliveryModePreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        ExpressDeliveryMethodListItem(
            type = "cyclist",
            etaInMinutes = 23,
            price = 123,
            image = painterResource(id = R.drawable.bicycle),
            myIndex = 2,
            select = {}
        )
    }
}