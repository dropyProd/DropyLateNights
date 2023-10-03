package com.example.dropy.ui.components.shops.backside.dashboard.orders.orderlist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsBacksideNavigation
import com.example.dropy.ui.components.commons.ProfilePic
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.screens.shops.backside.orderdetails.OrderDetailsViewModel
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue
import kotlinx.coroutines.delay


@Composable
fun OrderListItem(
    customerName: String,
    customerProfilePhotoUrl: String? = null,
    orderNumber: String,
    isProcessed: Boolean,
    numberOfItems: Int,
    status: String,
    cost: Int,
    isPaid: Boolean,
    timeString: String,
    backgroundColor: Color,
    onClick: () -> Unit,
    onOrderStatusClick: () -> Unit,
    orderDetailsViewModel: OrderDetailsViewModel? = null,
    onScanOrderClicked: () -> Unit,
    orderId: Int
) {

    val orderitemtotal = remember {
        mutableStateOf(0)
    }

    LaunchedEffect(key1 = true, block = {
       // orderDetailsViewModel?.setOrderId(orderId, navigate = false)
        orderitemtotal.value = orderDetailsViewModel?.getOrderDetails()?.orderSize!!

        if (orderDetailsViewModel.reset.value.equals(false)) {
            for (i in 0..3) {
                if (i == 2) {
                    orderDetailsViewModel.changereset()
                    orderDetailsViewModel.appViewModel?.navigate(ShopsBacksideNavigation.INCOMING_ORDERS)
                }
                delay(1000)
            }
        }
    })

    Column(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .border(1.dp, Color.LightGray.copy(.4f), RoundedCornerShape(8.dp))
    ) {

        Row(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
            /* .clickable { onClick() }*/,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                ProfilePic(profilePicUrl = customerProfilePhotoUrl, size = 64, show = false)
                SimpleText(
                    textSize = 10,
                    text = customerName.uppercase(),
                    padding = 4,
                    fontWeight = FontWeight.Bold,
                    font = Font(R.font.axiformabold)
                )
            }
            Column(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .weight(1f),
            ) {
                SimpleText(
                    textSize = 10,
                    text = "ORDER#$orderNumber".uppercase(),
                    padding = 4,
                    fontWeight = FontWeight.Black,
                    font = Font(R.font.axiformablack),
                )
                StyledText(
                    textColor = Color.White,
                    backgroundColor = if (isProcessed) {
                        Color.Black
                    } else {
                        Color.LightGray
                    },
                    borderColor = Color.Transparent,
                    textSize = 10,
                    text = if (isProcessed) {
                        "processed"
                    } else {
                        "unprocessed"
                    },
                    isUppercase = true,
                    isBold = true,
                    fontWeight = FontWeight.Black,
                    fontFamily = R.font.axiformablack,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StyledText(
                        textColor = Color.Black,
                        backgroundColor = Color.Transparent,
                        borderColor = LightBlue,
                        textSize = 10,
                        text = "${orderitemtotal.value} items",
                        isUppercase = true,
                        isBold = true,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = R.font.axiformaextrabold,
                    )

                    StyledText(
                        textColor = Color.White,
                        backgroundColor = when (status) {
                            "delivered" -> Color.Green
                            "cancelled" -> Color.Red
                            else -> LightBlue
                        },
                        textSize = 10,
                        text = status,
                        isUppercase = true,
                        isBold = true
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(end = 4.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StyledText(
                    textColor = Color.Black,
                    backgroundColor = Color.Transparent,
                    textSize = 18,
                    text = "${cost}/-",
                    isUppercase = true,
                    isBold = true,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = R.font.axiformaextrabold,
                )
                StyledText(
                    textColor = if (isPaid) {
                        Color.Black
                    } else {
                        Color.White
                    },
                    backgroundColor = if (isPaid) {
                        DropyYellow
                    } else {
                        Color.Red
                    },
                    borderColor = Color.Transparent,
                    textSize = 12,
                    text = if (isPaid) {
                        "paid"
                    } else {
                        "not paid"
                    },
                    isUppercase = true,
                    isBold = true,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = R.font.axiformaextrabold
                )
                SimpleText(
                    textSize = 12,
                    text = timeString,
                    isUppercase = true,
                    isBold = true,
                    textColor = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    font = Font(R.font.axiformablack)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray)
        )

        var expanded by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopStart)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { expanded = true })
                    .height(48.dp)
                    .clip(RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                SimpleText(
                    text = "order details",
                    isUppercase = true,
                    horizontalPadding = 8,
                    fontWeight = FontWeight.ExtraBold,
                    font = Font(R.font.axiformaextrabold)
                )
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "dropdown icon",
                    modifier = Modifier
                        .size(32.dp)
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                if (isProcessed) {
                    DropdownMenuItem(
                        onClick = {
                            expanded = false
                            onOrderStatusClick()
                        }
                    ) {
                        SimpleText(
                            text = "Order status",
                            isUppercase = true,
                            fontWeight = FontWeight.Bold,
                            font = Font(R.font.axiformabold)
                        )
                    }
                }
                /*DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onReceiptClick()
                    }
                ) {
                    SimpleText(
                        text = "receipt",
                        isUppercase = true,
                        fontWeight = FontWeight.Bold,
                        font = Font(R.font.axiformabold)
                    )
                }*/
                if (isProcessed) {
                    DropdownMenuItem(
                        onClick = {
                            expanded = false
                            onScanOrderClicked()
                        }
                    ) {

                        SimpleText(
                            text = "order ready(qr)",
                            isUppercase = true,
                            fontWeight = FontWeight.Bold,
                            font = Font(R.font.axiformabold)
                        )

                    }
                }
                /* if (status != "delivered") {*/
                if (!isProcessed) {
                    DropdownMenuItem(
                        onClick = {
                            expanded = false
                            onClick()
                        }
                    ) {
                        SimpleText(
                            text = "Process order",
                            isUppercase = true,
                            fontWeight = FontWeight.Bold,
                            font = Font(R.font.axiformabold)
                        )
                    }

                }
                /*   DropdownMenuItem(
                       onClick = {
                           expanded = false
                           onScanOrderClicked()
                       }
                   ) {
                       if (type.equals("user")) {
                           SimpleText(
                               text = "order delivered",
                               isUppercase = true,
                               fontWeight = FontWeight.Bold,
                               font = Font(R.font.axiformabold)
                           )
                       } else {
                           SimpleText(
                               text = "order ready",
                               isUppercase = true,
                               fontWeight = FontWeight.Bold,
                               font = Font(R.font.axiformabold)
                           )
                       }
                   }
                   if (!type.equals("user")) {
                       DropdownMenuItem(
                           onClick = {
                               expanded = false
                               showCollectDialog.value = true
                           }
                       ) {

                           SimpleText(
                               text = "order collected",
                               isUppercase = true,
                               fontWeight = FontWeight.Bold,
                               font = Font(R.font.axiformabold)
                           )
                       }

                   }*/


                // }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OrderListItemPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        OrderListItem(
            customerName = "claudius",
            orderNumber = "1342",
            isProcessed = false,
            numberOfItems = 2,
            status = "in progress",
            cost = 234,
            isPaid = false,
            timeString = "13:30",
            backgroundColor = Color.White,
            onClick = {},
            onOrderStatusClick = {},
            orderId = 0,
            onScanOrderClicked = {}
        )
    }
}