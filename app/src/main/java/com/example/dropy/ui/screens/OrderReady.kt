package com.example.dropy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.ProfilePic
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.components.shops.backside.dashboard.orders.orderlist.OrdersList
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.PhoneGreen

@Composable
fun OrderReady(
    startClicked: () -> Unit,
    onMinutesChanged: (String) -> Unit,
    orderReadyViewModel: OrderReadyViewModel
) {
    val context = LocalContext.current
    val uiState by orderReadyViewModel.orderReadyUiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ClippedHeader(title = "order #126789")
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            SimpleText(
                text = "preparing customer order",
                textSize = 14,
                isUppercase = true,
                isBold = false,
                font = Font(R.font.axiformablack),
                fontWeight = FontWeight.ExtraBold
            )
            timer(
                startClicked = startClicked,
                onMinutesChanged = onMinutesChanged, uiState = uiState
            )
            CustomerDetails(customerName = "nyamilton") {

            }

        }
    }
}

@Composable
fun CustomerDetails(
    customerName: String,
    onCallCustomerClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 8.dp)
        ) {
            SimpleText(
                textSize = 10,
                text = "customer",
                isUppercase = true,
                isExtraBold = true,
                font = Font(R.font.axiformaextrabold)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(color = Color(0xfff5f5f5), shape = RoundedCornerShape(50.dp))
                .padding(vertical = 7.dp, horizontal = 7.dp)
        ) {
            ProfilePic(show = false, size = 52)
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                SimpleText(
                    textSize = 12,
                    text = customerName,
                    isUppercase = true,
                    isExtraBold = true,
                    font = Font(R.font.axiformaextrabold)
                )
            }
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = { onCallCustomerClicked() },
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(PhoneGreen)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Phone,
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                }

                IconButton(
                    onClick = { onCallCustomerClicked() },
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(DropyYellow)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Message,
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun timer(
    startClicked: () -> Unit,
    onMinutesChanged: (String) -> Unit,
    uiState: OrderReadyUiState?
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .padding(bottom = 8.dp)
        ) {
            SimpleText(
                textSize = 10,
                text = "TIME TO PREPRARE",
                isUppercase = true,
                isExtraBold = true,
                font = Font(R.font.axiformaextrabold)
            )
        }
        Row(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            SimpleText(
                textSize = 9,
                text = "ENTER TIME IN MINUTES",
                isUppercase = true,
                isExtraBold = true,
                font = Font(R.font.axiformaextrabold),
                textColor = Color(0xff979797)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                timeItem(text = uiState?.slotOne.toString())
                timeItem(text = uiState?.slotTwo.toString())
                SimpleText(
                    textSize = 15,
                    text = ":",
                    isUppercase = true,
                    isExtraBold = true,
                    font = Font(R.font.axiformaextrabold),
                    textColor = Color.Black
                )
                timeItem(
                    text = uiState?.slotThree.toString(),
                    backgroundColor = Color.Black,
                    textColor = Color.White
                )
                timeItem(
                    text = uiState?.slotFour.toString(),
                    backgroundColor = Color.Black,
                    textColor = Color.White
                )
            }


        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Row(
                    modifier = Modifier
                        .size(30.dp)
                        .background(color = Color.Transparent, shape = RoundedCornerShape(6.dp))
                        .border(
                            width = 1.dp,
                            color = Color(0xffdedede),
                            shape = RoundedCornerShape(6.dp)
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    uiState?.minutes?.let {
                        TextField(
                            value = it,
                            onValueChange = onMinutesChanged
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(start = 9.dp)
                        .background(Color.Black, RoundedCornerShape(9.dp))
                        .width(54.dp)
                        .height(18.dp)
                        .clickable { startClicked() },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SimpleText(
                        textSize = 10,
                        text = "start",
                        isUppercase = true,
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold),
                        textColor = Color.White
                    )
                }


            }
            SimpleText(
                textSize = 10,
                text = "TIME REMAINING",
                isUppercase = true,
                isExtraBold = true,
                font = Font(R.font.axiformablack),
                textColor = Color.Black
            )
        }

    }
}


@Composable
fun timeItem(
    backgroundColor: Color = Color.White,
    textColor: Color = Color.Black,
    text: String
) {
    Row(
        modifier = Modifier
            .size(30.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(6.dp))
            .border(
                width = 1.dp,
                color = Color(0xffdedede),
                shape = RoundedCornerShape(6.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        SimpleText(
            textSize = 15,
            text = text,
            isUppercase = true,
            isExtraBold = true,
            font = Font(R.font.axiformablack),
            textColor = textColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun demoo() {
//    OrderReady()
    timer(onMinutesChanged = {}, startClicked = {}, uiState = null)
}