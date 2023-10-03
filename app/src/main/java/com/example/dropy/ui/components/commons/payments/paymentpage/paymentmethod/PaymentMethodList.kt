package com.example.dropy.ui.components.commons.payments.paymentpage.paymentmethod

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.payments.paymentpage.paymentmethod.PaymentMethodDataClass

@Composable
fun PaymentMethodList(
    selectPaymentMethod:(PaymentMethodDataClass)->Unit,
    paymentMethods:List<PaymentMethodDataClass>,
){
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        paymentMethods.forEachIndexed { _, item ->
            Box(
                modifier = Modifier
                    .weight(1f)
                ,
                contentAlignment = Alignment.CenterStart
            ) {
                PaymentMethod(
                    image = item.image,
                    selectMethod = {
                        selectPaymentMethod(item)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentMethodListPreview(){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val paymentMethodList = mutableListOf(
            PaymentMethodDataClass(
                name = "mpesa",
                image = painterResource(id = R.drawable.mpesa)
            ),
        )
        PaymentMethodList(
            paymentMethods = paymentMethodList,
            selectPaymentMethod = {}
        )
    }
}