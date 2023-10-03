package com.example.dropy.ui.components.shops.frontside.customer.customerdashboard.orderhistory

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
import androidx.compose.ui.window.Dialog
import com.example.dropy.R
import com.example.dropy.network.models.customerorders.Shop
import com.example.dropy.ui.components.commons.ProfilePic
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue


@Composable
fun CustomerOrderListItem(
    customerName: String,
    customerProfilePhotoUrl: String? = null,
    orderNumber: String,
    isProcessed: String,
    numberOfItems: Int,
    status: String,
    orderPackedbyShop: Boolean = false,
    orderPickedbyRider: Boolean = false,
    customerReceiveOrder: Boolean = false,
    cost: Int,
    isPaid: Boolean,
    timeString: String,
    backgroundColor: Color,
    onReceiptClick: () -> Unit,
    onOrderStatusClick: () -> Unit,
    onCompleteOrderClick: () -> Unit,
    onScanOrderClicked: () -> Unit,
    type: String = "user",
    shopname: String? = null,
    shop: Shop? = null
) {

    val showCollectDialog = remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(5.dp)) {

        SimpleText(
            textSize = 12,
            text = timeString,
            isUppercase = true,
            isBold = true,
            textColor = Color.DarkGray,
            fontWeight = FontWeight.Bold,
            font = Font(R.font.axiformablack)
        )

        Column(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .background(backgroundColor)
                .border(1.dp, Color.LightGray.copy(.4f), RoundedCornerShape(8.dp))

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    ProfilePic(profilePicUrl = customerProfilePhotoUrl, size = 64, show = false)
                    if (!type.equals("user")) {
                        SimpleText(
                            textSize = 10,
                            text = customerName.uppercase(),
                            fontWeight = FontWeight.Bold,
                            padding = 4,
                            font = Font(R.font.axiformabold)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .weight(1f),
                ) {
                    SimpleText(
                        textSize = 10,
                        text = if (!type.equals("user")) "ORDER#$orderNumber".uppercase() else shopname.toString(),
                        padding = 4,
                        fontWeight = FontWeight.Black,
                        font = Font(R.font.axiformablack),
                    )
                    StyledText(
                        textColor = Color.White,
                        backgroundColor = if (isProcessed.equals("Processed")) {
                            Color.Black
                        } else {
                            Color.DarkGray
                        },
                        borderColor = Color.Transparent,
                        textSize = 10,
                        text = if (isPaid) {
                            "processed"
                        } else {
                            if (isProcessed.equals("Processed"))  {
                                "processed"
                            } else {
                                "unprocessed"
                            }
                        },
                        isUppercase = true,
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
                            text = "$numberOfItems items",
                            isUppercase = true,
                            isExtraBold = true,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = R.font.axiformaextrabold,
                        )
                        StyledText(
                            textColor = Color.Black,
                            backgroundColor = when {
                                orderPackedbyShop.equals(true) -> Color.Green
                                orderPickedbyRider.equals(true) -> Color.LightGray
                                customerReceiveOrder.equals(true) -> Color.Green
                                status == "delivered" -> Color.Green
                                status == "cancelled" -> Color.Red
                                else -> LightBlue
                            },
                            textSize = 10,
                            text = when {
                                orderPackedbyShop.equals(true) -> "collected"
                                orderPickedbyRider.equals(true) -> "in transit"
                                customerReceiveOrder.equals(true) -> "delivered"
                                status == "delivered" -> "delivered"
                                status == "cancelled" -> "cancelled"
                                else -> ""
                            },
                            isUppercase = true,
                            isExtraBold = true,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = R.font.axiformaextrabold,
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(end = 4.dp, top = 16.dp),
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
                    DropdownMenuItem(
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
                    }
                    if (status != "delivered") {
                        if (!type.equals("user")) {
                            DropdownMenuItem(
                                onClick = {
                                    expanded = false
                                    onCompleteOrderClick()
                                }
                            ) {
                                SimpleText(
                                    text = "complete delivery",
                                    isUppercase = true,
                                    fontWeight = FontWeight.Bold,
                                    font = Font(R.font.axiformabold)
                                )
                            }
                        }


                        DropdownMenuItem(
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
                                    text = "order ready(qr)",
                                    isUppercase = true,
                                    fontWeight = FontWeight.Bold,
                                    font = Font(R.font.axiformabold)
                                )
                            }
                        }
                        /*   if (!type.equals("user")) {
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

                           }
       */

                    }
                }
            }

        }

        orderCollectDialog(show = showCollectDialog.value, clicked = {
            showCollectDialog.value = false
        }, dismiss = {

            showCollectDialog.value = false
        })

    }


}


@Composable
fun orderCollectDialog(show: Boolean, clicked: (String) -> Unit, dismiss: (Boolean) -> Unit) {

    if (show) {
        Dialog(onDismissRequest = { dismiss(true) }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(start = 8.dp)
            ) {
                SimpleText(
                    text = "Choose option",
                    textSize = 18,
                    isBold = true,
                    verticalPadding = 16,
                    font = Font(R.font.axiformabold)
                )

               /* optionItem(text = "Yes", navigate = {
                    clicked("Yes")
                })
                optionItem(text = "No", navigate = {
                    clicked("No")
                })*/
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
        CustomerOrderListItem(
            customerName = "claudius",
            orderNumber = "1342",
            isProcessed = false.toString(),
            numberOfItems = 2,
            status = "in progress",
            cost = 234,
            isPaid = false,
            timeString = "13:30",
            backgroundColor = Color.White,
            onCompleteOrderClick = {},
            onOrderStatusClick = {},
            onReceiptClick = {},
            onScanOrderClicked = {},
            orderPackedbyShop = true,
            orderPickedbyRider = true
        )
    }
}