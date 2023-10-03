package com.example.dropy.ui.components.shops.frontside.customer.orderprocess.payments

import com.example.dropy.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.commons.Dot
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.screens.payments.mpesapaymentdialog.MpesaPaymentDialogUiState
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.OrderPaymentPageUiState
import com.example.dropy.ui.theme.DropyYellow

@Composable
fun OrderPaymentPageContent(
    uiState: OrderPaymentPageUiState,
    mpesauiState: MpesaPaymentDialogUiState,
    onSelectPaymentMethod: (PaymentMethodDataClass) -> Unit,
    onAddPaymentMethod: (PaymentMethodDataClass) -> Unit,
    onPayClicked: () -> Unit,
    cartPageViewModel: CartPageViewModel
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            SimpleText(
                text = "complete payment",
                textSize = 16,
                isExtraBold = true,
                verticalPadding = 8,
                isUppercase = true,
                font = Font(R.font.axiformablack)
            )
            SimpleText(
                text = "Select payment method",
                textSize = 12,
                font = Font(R.font.axiformaregular)
            )
            ChoosePaymentMethod(
                walletBalance = uiState.walletBalance,
                onSelectPaymentMethod = { onSelectPaymentMethod(it) },
                onAddPaymentMethod = { onAddPaymentMethod(it) }
            )
        }

        OrderChargeSheet(
            selectedMethod = uiState.selectedMethod,
            orderCost = uiState.deliveryCost,
            deliveryCost = 0,
            discount = uiState.discount,
            total = (uiState.deliveryCost + /*cartPageViewModel.checkoutcurrent[0].get_order_total!!*/0)
        )

        TotallyRoundedButton(
            icon = null,
            buttonText = if (mpesauiState.number == 1)"pay" else "proceed",
            textFontSize = 15,
            backgroundColor = Color.Black,
            textColor = Color.White,
            widthFraction = 0.6,
            action = { onPayClicked() }
        )
    }
}


@Composable
fun OrderChargeSheet(
    selectedMethod: PaymentMethodDataClass?,
    orderCost: Int,
    deliveryCost: Int,
    discount: Int,
    total: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp, top = 15.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SimpleText(
                    textSize = 14,
                    text = "Payment Option",
                    textColor = Color.Black,
                    isUppercase = false,
                    isBold = false,
                    font = Font(R.font.axiformamedium),
                    fontWeight = FontWeight.Medium
                )
                Box(modifier = Modifier.weight(1f)) {
                    DotsRow()
                }
                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .aspectRatio(4 / 2f)
                        .clip(RoundedCornerShape(8.dp))
                        .border(2.dp, Color.LightGray, RoundedCornerShape(8.dp)),
                ) {
                    if (selectedMethod != null) {
                        Image(
                            painter = selectedMethod.image,
                            contentDescription = "dropy logo",
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxSize()
                                .clip(shape = RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.FillWidth
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SimpleText(
                    textSize = 14,
                    text = "Order cost",
                    textColor = Color.Black,
                    isUppercase = false,
                    isBold = false,
                    font = Font(R.font.axiformamedium),
                    fontWeight = FontWeight.Medium
                )
                Box(modifier = Modifier.weight(1f)) {
                    DotsRow()
                }
                SimpleText(
                    textSize = 21,
                    text = "$orderCost/-",
                    textColor = Color.Black,
                    isUppercase = true,
                    isExtraBold = true,
                    font = Font(R.font.axiformaheavy),
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SimpleText(
                    textSize = 14,
                    text = "Delivery cost",
                    textColor = Color.Black,
                    isUppercase = false,
                    isBold = false,
                    font = Font(R.font.axiformamedium),
                    fontWeight = FontWeight.Medium
                )
                Box(modifier = Modifier.weight(1f)) {
                    DotsRow()
                }
                SimpleText(
                    textSize = 21,
                    text = "$deliveryCost/-",
                    textColor = Color.Black,
                    isUppercase = true,
                    isExtraBold = true,
                    font = Font(R.font.axiformaheavy),
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SimpleText(
                    textSize = 14,
                    text = "Discount",
                    textColor = Color.Black,
                    isUppercase = false,
                    isBold = false,
                    font = Font(R.font.axiformamedium),
                    fontWeight = FontWeight.Medium
                )
                Box(modifier = Modifier.weight(1f)) {
                    DotsRow()
                }
                SimpleText(
                    textSize = 21,
                    text = "$discount/-",
                    textColor = Color.Black,
                    isUppercase = true,
                    isExtraBold = true,
                    font = Font(R.font.axiformaheavy),
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(bottom = 40.dp, top = 42.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SimpleText(
                textSize = 32,
                text = "Total",
                textColor = Color.Black,
                isUppercase = false,
                isBold = false,
                isExtraBold = true,
                font = Font(R.font.axiformaheavy),
                fontWeight = FontWeight.ExtraBold
            )
            Box(modifier = Modifier.weight(1f)) {
                DotsRow()
            }
            SimpleText(
                textSize = 32,
                text = "$total/-",
                textColor = Color.Black,
                isUppercase = true,
                isBold = false,
                isExtraBold = true,
                font = Font(R.font.axiformaheavy),
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
fun DotsRow() {
    BoxWithConstraints(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxWidth(1f),
    ) {
        val width = this.maxWidth.value
        val dotsNumber = (width / 8).toInt()
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (x in 1..dotsNumber) {
                Dot(color = Color.LightGray)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ChoosePaymentMethodContentPreview() {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        /*     OrderPaymentPageContent(
                 uiState = OrderPaymentPageUiState(),
                 onAddPaymentMethod = {},
                 onPayClicked = {},
                 onSelectPaymentMethod = {},
                 cartPageViewModel = Cart
             )*/
    }
}