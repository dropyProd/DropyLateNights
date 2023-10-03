package com.example.dropy.ui.components.shops.frontside.customer.orderprocess.customercart

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue


@Composable
fun CartItem(
    backgroundColor: Color = Color.White,
    productImageUrl: String?,
    productName: String,
    cartItemsNumber: Int,
    cartItemPrice: Int,
    cartItemTotal: Int,
    onAddClicked: (quantity: Int, price: Int) -> Unit,
    onRemoveClicked: (quantity: Int, price: Int) -> Unit,
    onDeleteClicked: (quantity: Int, price: Int) -> Unit,
) {

    val counter = remember {
        mutableStateOf(cartItemsNumber)
    }
    val total = remember {
        mutableStateOf(cartItemTotal)
    }


    Box {
        Row(
            modifier = Modifier
                .padding(top = 10.dp, end = 10.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(backgroundColor)
                .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .size(64.dp)
                    .clip(CircleShape)
            ) {
                LoadImage(imageUrl = productImageUrl, contentScale = ContentScale.FillBounds)
            }
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                SimpleText(
                    text = productName,
                    font = Font(R.font.axiformamedium),
                    fontWeight = FontWeight.Medium,
                    textSize = 11
                )
                Row(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .clip(RoundedCornerShape(50))
                        .border(1.dp, LightBlue, RoundedCornerShape(50)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    IconButton(
                        onClick = {
                            if (counter.value in 2..29) {
                                counter.value -= 1
                                val price = counter.value * cartItemPrice
                                Log.d(
                                    "ETAG",
                                    "CartItem: $cartItemTotal  ${counter.value} $price ${total.value}"
                                )
                                total.value = price
                                onRemoveClicked(counter.value, total.value)
                            }
                        },
                        modifier = Modifier
                            .padding(4.dp)
                            .size(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Remove,
                            contentDescription = "subtract icon",
                        )
                    }
                    SimpleText(
                        text = counter.value.toString(), font = Font(R.font.axiformabold),
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(
                        onClick = {
                            if (counter.value in 1..29) {
                                counter.value += 1
                                val price = counter.value * cartItemPrice
                                Log.d(
                                    "ETAG",
                                    "CartItem: $cartItemTotal  ${counter.value} $price ${total.value}"
                                )
                                total.value = price
                                onAddClicked(counter.value, total.value)
                            }
                        },
                        modifier = Modifier
                            .padding(4.dp)
                            .size(16.dp),

                        ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "add icon",
                        )
                    }
                }
            }
            StyledText(
                backgroundColor = DropyYellow,
                textSize = 18,
                text = "${cartItemPrice}/-",
                isBold = true,
                fontFamily = R.font.axiformaheavy,
                fontWeight = FontWeight.ExtraBold
            )
        }
        IconButton(
            onClick = { onDeleteClicked(counter.value, total.value) },
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
fun CartItemPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CartItem(
            productName = "Some product name",
            productImageUrl = "",
            cartItemsNumber = 2,
            cartItemTotal = 123,
            onAddClicked = { quantity: Int, price: Int ->
            },
            onRemoveClicked = { quantity: Int, price: Int ->
            },
            onDeleteClicked = { quantity: Int, price: Int ->
            },
            cartItemPrice = 123
        )
    }
}