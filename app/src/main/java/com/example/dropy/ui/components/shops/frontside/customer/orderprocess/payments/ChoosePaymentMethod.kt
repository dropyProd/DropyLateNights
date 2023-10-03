package com.example.dropy.ui.components.shops.frontside.customer.orderprocess.payments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.SimpleText

@Composable
fun ChoosePaymentMethod(
    walletBalance:Int,
    onSelectPaymentMethod:(PaymentMethodDataClass)->Unit,
    onAddPaymentMethod:(PaymentMethodDataClass)->Unit
    ){
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
        ,
        horizontalAlignment = Alignment.End
    ) {
        DropyPayHeader(
            selectPaymentMethod = {onSelectPaymentMethod(it)},
            walletBalance = walletBalance
        )
        SimpleText(
            textSize = 14,
            text = "Payment Options",
            textColor = Color.Black,
            isUppercase = false,
            isBold = true,
            font = Font(R.font.axiformablack)
        )
        PaymentMethodList(
            selectPaymentMethod = {onSelectPaymentMethod(it)},
            onAddPaymentMethod = { onAddPaymentMethod(it) }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ChoosePaymentMethodPreview(){
    Column(Modifier.fillMaxSize()) {
        ChoosePaymentMethod(
            walletBalance = 1234,
            onSelectPaymentMethod = {},
            onAddPaymentMethod = {}
        )
    }
}
