package com.example.dropy.ui.components.shops.backside.dashboard.orders.orderdetails

import androidx.compose.foundation.background
import com.example.dropy.R
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.commons.ProfilePic
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.screens.shops.backside.orderdetails.OrderDetailsUiState
import com.example.dropy.ui.theme.LightBlue
import com.example.dropy.ui.theme.LightContainerBackground
import com.example.dropy.ui.theme.PhoneGreen

@Composable
fun OrderDetailsContent(
    uiState: OrderDetailsUiState,
    markItemAsAvailable: (index: Int) -> Unit,
    markItemAsUnavailable: (index: Int, url: String) -> Unit,
    onCallCustomerClicked: () -> Unit,
    onProcessOrderClicked: (OrderDetailsUiState) -> Unit,
    onCancelOrderClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ClippedHeader(title = "order details")
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            SimpleText(
                text = "customer detailed order",
                textSize = 14,
                isUppercase = true,
                isBold = true,
                font = Font(R.font.axiformabold)
            )

            OrderItemsList(
                orderItemsList = uiState.orderItemList,
                title = "item list",
                markAsAvailable = { markItemAsAvailable(it) },
                markAsUnavailable = markItemAsUnavailable
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                SimpleText(
                    textSize = 10,
                    text = "delivery location",
                    isUppercase = true,
                    isExtraBold = true,
                    font = Font(R.font.axiformaextrabold)
                )
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                        .sizeIn(minHeight = 48.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(LightContainerBackground)
                        .border(1.dp, Color.LightGray.copy(.2f), RoundedCornerShape(8.dp))
                ) {
                    SimpleText(
                        textSize = 10,
                        text = uiState.orderLocation,
                        isUppercase = true,
                        isBold = true,
                        padding = 8,
                        font = Font(R.font.axiformabold)
                    )
                }
            }
            CustomerDetails(
                customerName = uiState.customerName,
                onCallCustomerClicked = { onCallCustomerClicked() }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TotallyRoundedButton(
                    buttonText = "process order",
                    textFontSize = 12,
                    textColor = Color.White,
                    backgroundColor = Color.Black,
                    widthFraction = 0.6,
                    action = { onProcessOrderClicked(uiState) }
                )
                TotallyRoundedButton(
                    buttonText = "cancel",
                    textFontSize = 12,
                    textColor = Color.White,
                    backgroundColor = Color.Red,
                    action = { onCancelOrderClicked() }
                )
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
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfilePic(show = false, size = 70)
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
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderDetailsContentPreview() {
    Column(Modifier.fillMaxSize()) {
        OrderDetailsContent(
            uiState = OrderDetailsUiState(),
            onCallCustomerClicked = {},
            markItemAsAvailable = {},
            markItemAsUnavailable = {_, _->},
            onCancelOrderClicked = {},
            onProcessOrderClicked = {}
        )
    }
}