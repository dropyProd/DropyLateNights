package com.example.dropy.ui.components.shops.backside.dashboard.orders.orderdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue
import com.example.dropy.ui.theme.LightContainerBackground

@Composable
fun OrderItem(
    productImageUrl: String,
    productName:String,
    numberOfUnits:Int,
    price:Int,
    isAvailable:Boolean,
    markAsAvailable:()->Unit,
    markAsUnavailable:()->Unit
){
    Box {
        Row(
            modifier = Modifier
                .padding(bottom = 6.dp, top = 10.dp, end = 10.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(LightContainerBackground)
                .border(1.dp, Color.LightGray.copy(.2f), RoundedCornerShape(8.dp))
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .width(80.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Color.LightGray.copy(.3f), RoundedCornerShape(8.dp))
                    .background(Color.White)
            ) {
                LoadImage(imageUrl = productImageUrl)
            }
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f)
                ,
                verticalArrangement = Arrangement.Center
            ) {
                SimpleText(textSize = 12, text = productName, font = Font(R.font.axiformaregular))
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                ){
                    StyledText(
                        textColor = Color.White,
                        backgroundColor = LightBlue,
                        textSize = 10,
                        text = "$numberOfUnits units",
                        isUppercase = true,
                        isBold = true,
                        fontFamily = R.font.axiformaextrabold
                    )
                }

            }
            Column(
                modifier = Modifier
                    .padding(4.dp)
                ,
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StyledText(
                    textColor = Color.Black,
                    backgroundColor = DropyYellow,
                    textSize = 15,
                    text = "${price}/-",
                    isUppercase = true,
                    isExtraBold = true,
                    fontFamily = R.font.axiformaextrabold
                )
                Row(
                    modifier = Modifier.clickable { markAsAvailable() }
                        .size(24.dp)
                        .clip(CircleShape)
                        .border(1.dp, LightBlue, CircleShape)
                        .background(if (isAvailable){
                            LightBlue}else{
                            Color.White
                        }),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isAvailable){
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "item available",
                            tint = Color.White,
                            modifier = Modifier
                                .size(13.dp)

                        )
                    }
                }
            }
        }
        IconButton(
            onClick = { markAsUnavailable() },
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Filled.Cancel,
                contentDescription = "cancel icon"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OrderItemPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        OrderItem(
            productImageUrl = "",
            productName = "Some other very long product name",
            numberOfUnits = 2,
            price = 4321,
            isAvailable = true,
            markAsAvailable = {},
            markAsUnavailable = {}
        )
        OrderItem(
            productImageUrl = "",
            productName = "Some other very long product name",
            numberOfUnits = 2,
            price = 4321,
            isAvailable = false,
            markAsAvailable = {},
            markAsUnavailable = {}
        )
    }
}