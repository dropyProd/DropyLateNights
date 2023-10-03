package com.example.dropy.ui.components.commons.payments.paymentpage.paymentmethod

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.dropy.R
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.payments.paymentpage.paymentmethod.PaymentMethodDataClass

@Composable
fun ChoosePaymentMethod(
    selectPaymentMethod:(PaymentMethodDataClass)->Unit,
    walletBalance:Int
){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        DropyPayHeader(
            selectPaymentMethod = {selectPaymentMethod(it)},
            walletBalance = walletBalance
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
            ,
            horizontalAlignment = Alignment.End
        ) {
            SimpleText(
                text = "Other payment options",
                textSize = 10,
                isBold = true,
                horizontalPadding = 8
            )
            PaymentMethodList(
                selectPaymentMethod = {selectPaymentMethod(it)},
                paymentMethods = listOf(
                    PaymentMethodDataClass(
                        name = "mpesa",
                        image = painterResource(id = R.drawable.mpesa)
                    )
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChoosePaymentMethodPreview(){
    Column(Modifier.fillMaxSize()) {
        ChoosePaymentMethod(
            selectPaymentMethod = {},
            walletBalance = 1234
        )
    }
}