package com.example.dropy.ui.components.shops.frontside.singleshop.shophome

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.StyledText

@Composable
fun ProductListItem(
    productImageUrl: String,
    productName: String,
    price: Int,
    onAddToCart: () -> Unit,
    onProductSelected: () -> Unit,
) {

    Column(
        modifier = Modifier
            .width(120.dp)
            .padding(bottom = 10.dp)

            .clickable { onProductSelected() }
    ) {
        Box {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(top = 8.dp, end = 8.dp)
            ) {
                /*Image(
                    painter = painterResource(id = R.drawable.imgtwo),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop
                )*/
                LoadImage(
                    imageUrl = productImageUrl,
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.LightGray.copy(.25f),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .clip(RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 4.dp)
            ) {
                StyledText(

                    backgroundColor = Color.Black,

                    textColor = Color.White,
                    textSize = 15,
                    text = "${price}/-",
                    fontFamily = R.font.axiformaextrabold




                )
            }
            IconButton(
                onClick = { onAddToCart() },
                modifier = Modifier
                    .offset(x = 8.dp, y = (-15).dp)
                    .align( Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "add item",
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Color(253, 211, 19))
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(end = 10.dp, top = 7.dp),
            contentAlignment = Alignment.Center
        ) {
            SimpleText(
                text = productName,
                isBold = true,
                padding = 4
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductListItemPreview() {
    Column(Modifier.fillMaxSize()) {
        ProductListItem(
            productImageUrl = "",
            productName = "product",
            price = 233,
            onAddToCart = {
            },
            onProductSelected = {}
        )
    }
}