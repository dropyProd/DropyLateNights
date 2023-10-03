package com.example.dropy.ui.components.shops.frontside.customer.orderprocess.customercart

import androidx.compose.foundation.background
import com.example.dropy.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.network.models.GetCartResItem
import com.example.dropy.network.models.ShopOrdersResponseItem
import com.example.dropy.network.models.cart.GetCartResponseItem
import com.example.dropy.ui.components.commons.DotsRow
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue


@Composable
fun CartSegment(
    order: MutableList<ShopOrdersResponseItem>,
    orderDetails: GetCartResItem,
    ordersList: List<GetCartResItem>,
    onAddCartItemClicked: (productId: Int) -> Unit,
    onRemoveCartItemClicked: (productId: Int) -> Unit,
    onDeleteCartItemClicked: (productId: Int) -> Unit,
    placeOrder: () -> Unit,
    checkoutOrder: (orderId: Int) -> Unit,
    collectOrder: (orderId: Int) -> Unit,
    cancelOrder: (orderId: Int) -> Unit,
    deleteOrder: (orderId: Int) -> Unit,
    subTotal: Int,
    netTotal: Int,
) {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        /*    orderDetails.shop?.shop_name?.uppercase()?.let {
                SimpleText(
                    text = it,
                    textSize = 10,
                    isExtraBold = true,
                    font = Font(R.font.axiformaextrabold)
                )
            }*/
//        orderDetails.order_items?.let {
        CartItemsList(
            cartItems = ordersList,
            onAddCartItemClicked = onAddCartItemClicked,
            onRemoveCartItemClicked = onRemoveCartItemClicked,
            onDeleteCartItemClicked = onDeleteCartItemClicked
        )
//        }

        Spacer(modifier = Modifier.size(16.dp))

        SimpleText(
            text = "${ordersList?.size}  items".uppercase(),
            textSize = 12,
            font = Font(R.font.axiformabold)
        )

        Column(
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp, end = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SimpleText(
                    textSize = 11,
                    text = "Subtotal",
                    font = Font(R.font.axiformamedium),
                    fontWeight = FontWeight.Medium
                )
                SimpleText(
                    textSize = 14,
                    text = "${subTotal}/-",
                    isBold = true,
                    font = Font(R.font.axiformaheavy),
                    fontWeight = FontWeight.ExtraBold
                )
            }

            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp, end = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SimpleText(
                    textSize = 11,
                    text = "Discount amount",
                    font = Font(R.font.axiformamedium),
                    fontWeight = FontWeight.Medium
                )
                SimpleText(
                    textSize = 14,
                    text = "0/-",
                    isExtraBold = true,
                    font = Font(R.font.axiformaheavy),
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp, end = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                SimpleText(
                    textSize = 18,
                    text = "Total",
                    isExtraBold = true,
                    font = Font(R.font.axiformaheavy),
                    fontWeight = FontWeight.ExtraBold
                )
                Box(modifier = Modifier.weight(1f)) {
                    DotsRow(color = Color.Black)
                }

                SimpleText(
                    textSize = 25,
                    text = "${netTotal}/-",
                    isBold = true,
                    font = Font(R.font.axiformablack),
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (order.isNotEmpty()) {

                val pos = order.size - 1

                if (order[pos].is_placed == true) {
                    if (order[pos].is_checked_out == false) {

                        if (order[pos].is_accepted == true && order[pos].is_canceled == false) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Button(
                                    onClick = { order[0].id?.let { checkoutOrder(it) } },
                                    modifier = Modifier
                                        .wrapContentWidth()
                                        .height(48.dp)
                                        .padding(horizontal = 4.dp),
                                    colors = ButtonDefaults.buttonColors(DropyYellow),
                                    shape = RoundedCornerShape(8.dp),
                                    enabled = true// orderDetails.is_accepted
                                ) {
                                    SimpleText(
                                        text = "deliver",
                                        isUppercase = true,
                                        textSize = 15,
                                        isExtraBold = true,
                                        font = Font(R.font.axiformaheavy),
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                }

                                Button(
                                    onClick = { order[pos].id?.let { collectOrder(it) } },
                                    modifier = Modifier
                                        .wrapContentWidth()
                                        .height(48.dp)
                                        .padding(horizontal = 4.dp),
                                    colors = ButtonDefaults.buttonColors(LightBlue),
                                    shape = RoundedCornerShape(8.dp),
                                    enabled = true// orderDetails.is_accepted
                                ) {
                                    SimpleText(
                                        text = "collect",
                                        isUppercase = true,
                                        textSize = 15,
                                        isExtraBold = true,
                                        font = Font(R.font.axiformaheavy),
                                        fontWeight = FontWeight.ExtraBold,
                                        textColor = Color.White
                                    )
                                }

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    IconButton(
                                        onClick = { order[pos].id?.let { cancelOrder(it) } },
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .background(Color.Red)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Cancel,
                                            contentDescription = "cancel icon",
                                            tint = Color.White
                                        )
                                    }
                                    SimpleText(
                                        text = "cancel order",
                                        isBold = true,
                                        isUppercase = true
                                    )
                                }
                            }
                        }


                        if (order[pos].is_canceled == true && order[pos].is_accepted == false) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                SimpleText(text = "Your order was declined")
                                //  orderDetails.reasonForDecline?.let { SimpleText(text = it) }
                                Button(
                                    onClick = { order[pos].id?.let { deleteOrder(it) } },
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                        .fillMaxWidth(0.6f)
                                        .height(48.dp),
                                    colors = ButtonDefaults.buttonColors(Color.Red),
                                    shape = RoundedCornerShape(8.dp),
                                ) {
                                    SimpleText(
                                        text = "delete",
                                        isUppercase = true,
                                        textSize = 18,
                                        isExtraBold = true,
                                        textColor = Color.White,
                                        font = Font(R.font.axiformaheavy),
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                }
                            }
                        }

                        if (order[pos].is_pending == false && order[pos].is_canceled == false && order[pos].is_accepted == false) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    SimpleText(text = "Awaiting order to be processed")
                                    Button(
                                        onClick = {
                                            order[0].id?.let { it1 ->
                                                checkoutOrder(it1)
                                            }
                                        },
                                        modifier = Modifier
                                            .padding(top = 8.dp)
                                            .fillMaxWidth(0.6f)
                                            .height(48.dp),
                                        colors = ButtonDefaults.buttonColors(DropyYellow),
                                        shape = RoundedCornerShape(8.dp),
                                        enabled = false
                                    ) {
                                        SimpleText(
                                            text = "checkout",
                                            isUppercase = true,
                                            textSize = 18,
                                            isExtraBold = true,
                                            font = Font(R.font.axiformaheavy),
                                            fontWeight = FontWeight.ExtraBold
                                        )

                                        CircularProgressIndicator(
                                            color = Color.Black,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                }

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    IconButton(
                                        onClick = { order[pos].id?.let { cancelOrder(it) } },
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .background(Color.Red)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Cancel,
                                            contentDescription = "cancel icon",
                                            tint = Color.White
                                        )
                                    }
                                    SimpleText(
                                        text = "cancel order",
                                        isBold = true,
                                        isUppercase = true
                                    )
                                }
                            }
                        }

                    }
                } else {
                    Button(
                        onClick = { placeOrder() },
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(DropyYellow),
                        shape = RoundedCornerShape(8.dp)

                    ) {
                        SimpleText(
                            text = "place order",
                            isUppercase = true,
                            textSize = 18,
                            isExtraBold = true,
                            font = Font(R.font.axiformaheavy),
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            } else {
                Button(
                    onClick = { placeOrder() },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(DropyYellow),
                    shape = RoundedCornerShape(8.dp)

                ) {
                    SimpleText(
                        text = "place order",
                        isUppercase = true,
                        textSize = 18,
                        isExtraBold = true,
                        font = Font(R.font.axiformaheavy),
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartSegmentPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        val orderDetails = OrderDetailsDataClass(
            cartItems = listOf(),
            orderId = 1,
            shopName = "shop name",
            subTotal = 1234,
            netTotal = 1234,
            isOrderPlaced = true,
            isOrderDeclined = true
        )
        /*CartSegment(
            orderDetails = orderDetails,
            onAddCartItemClicked = { _, _ -> },
            onDeleteCartItemClicked = { _ -> },
            onRemoveCartItemClicked = { _, _ -> },
            checkoutOrder = {},
            placeOrder = {},
            cancelOrder = {},
            deleteOrder = {},
            netTotal = 0,
            subTotal = 0
        )*/
    }
}