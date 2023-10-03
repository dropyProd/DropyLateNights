package com.example.dropy.ui.components.shops.frontside.customer.customerdashboard.orderhistory.orderreciept

import com.example.dropy.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.network.models.GetCartResItem
import com.example.dropy.network.models.cart.GetCartResponseItem
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageUiState
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import com.example.dropy.ui.screens.tracking.textDot
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue


@Composable
fun OrderReceiptContent(
    cartPageViewModel: CartPageViewModel,
    shopHomePageViewModel: ShopHomePageViewModel,
    checkoutViewModel: CheckoutViewModel
) {
    val list = cartPageViewModel.checkoutcurrent
    val uiState by shopHomePageViewModel.shopHomePageUiState.collectAsState()
    val orderuiState by checkoutViewModel.orderPaymentPageUiState.collectAsState()


    /*LaunchedEffect(key1 = true, block ={
        list.forEach {
            val et = it.copy(deliveryPrice = orderuiState.deliveryCost)
            current.add(et)
        }
    } )*/


    cartPageViewModel.checkoutcurrent.forEach { item ->
        Column(
            modifier = Modifier
                .padding(bottom = 48.dp)
                .fillMaxHeight()
                .fillMaxWidth()

                .verticalScroll(rememberScrollState()),
/*            verticalArrangement = Arrangement.SpaceEvenly*/
        ) {
            ReceiptOrderDetails(getCartResponseItem = item, uiState = uiState)
            OrderReceiptList(getCartResponseItem = item)
            OrderTotals(getCartResponseItem = item)
        }
    }
}

@Composable
private fun ReceiptOrderDetails(
    getCartResponseItem: GetCartResItem,
    uiState: ShopHomePageUiState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(22.dp),

            ) {
            Text(
                text = "receipt".uppercase(),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 21.sp,
                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                color = Color.Black,
                modifier = Modifier.padding(start = 8.dp)
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp))
                    .background(DropyYellow)
            ) {
                Text(
                    text = "order #${getCartResponseItem.id}".uppercase(),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
                    fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                    color = colorResource(id = R.color.blacklight)
                )
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "date: 26/09/2022".uppercase(),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                color = Color.Black,
                modifier = Modifier.padding(start = 8.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(11.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp))
                        .background(LightBlue)
                ) {
                    Text(
                        text = "{getCartResponseItem.shop?.shop_name}".uppercase(),
                        color = Color.White,
                        fontWeight = FontWeight.Black,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
                        fontFamily = FontFamily(Font(R.font.axiformablack))
                    )
                }
                Text(
                    text = uiState.shopLocation,
                    fontFamily = FontFamily(Font(R.font.axiformasemibold)),
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.blacklight),
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    }

}


@Composable
fun OrderReceiptList(getCartResponseItem: GetCartResItem) {
    Column(
        modifier = Modifier
            .padding(top = 50.dp)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp, start = 8.dp, end = 8.dp)
        ) {
            Text(
                text = "QTY",
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .fillMaxWidth(0.2f),
                fontFamily = FontFamily(Font(R.font.axiformaheavy))
            )
            Text(
                text = "Product Name",
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .weight(1f),
                fontFamily = FontFamily(Font(R.font.axiformaheavy))
            )
            Text(
                text = "Price",
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp),
                fontFamily = FontFamily(Font(R.font.axiformaheavy))
            )
        }
   /*     getCartResponseItem.order_items?.forEach { item ->
            item.quantity?.let {
                item.product?.product_price?.let { it1 ->
                    OrderReceiptListItem(
                        numberOfItems = it,
                        itemName = item.product.product_name.toString(),
                        price = it1
                    )
                }
            }
        }*/

        Box(
            modifier = Modifier
                .padding(top = 26.dp)
                .clip(RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp))
                .background(Color(255, 204, 0))
        ) {
            Text(
                text = "{getCartResponseItem.order_items?.size} items",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
                color = colorResource(id = R.color.blacklight),
                fontFamily = FontFamily(Font(R.font.axiformaheavy))
            )
        }
    }
}

@Composable
fun OrderTotals(getCartResponseItem: GetCartResItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, bottom = 26.dp, start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "subtotal".uppercase(),
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                fontSize = 18.sp,
                color = colorResource(id = R.color.blacklight),
            )

            Row() {
                for (i in 1..45) {
                    textDot(color = Color.Black)
                }
            }

            SimpleText(
                text = "{getCartResponseItem.get_order_total} /-",
                textSize = 18,
                font = Font(R.font.axiformablack),
                fontWeight = FontWeight.Black
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 26.dp, start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "tax".uppercase(),
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                fontSize = 18.sp,
                color = colorResource(id = R.color.blacklight),
            )

            Row() {
                for (i in 1..57) {
                    textDot(color = Color.Black)
                }
            }

            SimpleText(
                text = "0 /-", textSize = 18,
                font = Font(R.font.axiformablack),
                fontWeight = FontWeight.Black
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 76.dp, start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "delivery".uppercase(),
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                fontSize = 18.sp,
                color = colorResource(id = R.color.blacklight),
            )

            Row() {
                for (i in 1..55) {
                    textDot(color = Color.Black)
                }
            }

            SimpleText(
                text = "{getCartResponseItem.deliveryPrice} /-", textSize = 18,
                font = Font(R.font.axiformablack),
                fontWeight = FontWeight.Black
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp, start = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "total".uppercase(),
                fontWeight = FontWeight.Black,
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.axiformablack)),
                color = colorResource(id = R.color.blacklight),
            )
            Row() {
                for (i in 1..40) {
                    textDot(color = Color.Black)
                }
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp))
                    .background(DropyYellow)
            ) {
                Text(
                    text = "{getCartResponseItem.get_order_total}/-",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 21.sp,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
                    fontFamily = FontFamily(Font(R.font.axiformablack)),
                    color = colorResource(id = R.color.blacklight),
                )
            }
        }
    }
}

@Composable
fun OrderReceiptListItem(
    numberOfItems: Int,
    itemName: String,
    price: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp, start = 8.dp, end = 8.dp)
    ) {
        Text(
            text = "$numberOfItems",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth(0.2f)
                .padding(start = 9.dp),
            fontFamily = FontFamily(Font(R.font.axiformabold)),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.blacklight)
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = itemName,
                modifier = Modifier.widthIn(5.dp, 120.dp),
                //.weight(1f),
                fontFamily = FontFamily(Font(R.font.axiformamedium)),
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.blacklight)
            )
            Row() {
                for (i in 1..15) {
                    textDot(color = Color.Black)
                }
            }
            SimpleText(
                text = "$price/-",
                fontWeight = FontWeight.Black,
                font = Font(R.font.axiformablack),
                textColor = colorResource(id = R.color.blacklight),
                textSize = 15
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun OrderReceiptContentPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        // OrderReceiptContent()
    }
}