package com.example.dropy.ui.components.shops.frontside.customer.orderprocess.deliveryinfomation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.components.parcel.favoriteItem
import com.example.dropy.ui.components.parcel.favoriteRiderPojo
import com.example.dropy.ui.theme.LightBlue

@Composable
fun FavoriteDeliveryOptions(
    updatePrice: ((String, Int, Int) -> Unit)? = null,
    eta: Int,
    modifier: Modifier = Modifier
) {
    val ridersLIst = remember {
        mutableListOf(
            favoriteRiderPojo(image = R.drawable.shop1, text = "RAY", eta = eta, price = 200),
            favoriteRiderPojo(image = R.drawable.shop1, text = "KAMA", eta = eta, price = 764),
            favoriteRiderPojo(image = R.drawable.shop1, text = "TOM", eta = eta, price = 865),

            )
    }

/*
    LaunchedEffect(key1 = true, block = {
        updatePrice?.let { it(ridersLIst[0].text, ridersLIst[0].price, ridersLIst[0].eta) }
    })
*/


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            text = "Favorite Delivery Options",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth(),
            letterSpacing = (-0.58).sp,
            fontFamily = FontFamily(Font(R.font.axiformabold)),
            fontSize = 12.sp
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val indexSelected = remember {
                mutableStateOf(0)
            }

            val price = remember {
                mutableStateOf(ridersLIst[0].price)
            }

            LazyRow(modifier = Modifier.wrapContentWidth()) {

                itemsIndexed(items = ridersLIst) { index, item ->

                    favoriteItem(item, selectedindex = indexSelected.value, index = index, click = {
                        indexSelected.value = index
                        price.value = item.price
                        if (updatePrice != null) {
                            updatePrice(item.text, price.value, item.eta)
                        }
                    }, size = 38, horizontal = 4)
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Delivery Cost",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp,
                    fontFamily = FontFamily(Font(R.font.axiformasemibold)),
                    letterSpacing = (-0.38).sp
                )
                Text(
                    text = "${price.value}/-",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                    letterSpacing = (0.5).sp
                )

                BackgroundedText(
                    background = LightBlue,
                    textColor = Color.White,
                    text = "DELIVER",
                    textSize = 10,
                    vertical = 4
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun demop() {
    FavoriteDeliveryOptions(eta = 0)
}