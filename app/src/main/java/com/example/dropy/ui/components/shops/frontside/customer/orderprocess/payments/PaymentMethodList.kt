package com.example.dropy.ui.components.shops.frontside.customer.orderprocess.payments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.dropy.R

@Composable
fun PaymentMethodList(
    selectPaymentMethod:(PaymentMethodDataClass)->Unit,
    onAddPaymentMethod:(PaymentMethodDataClass)->Unit
){
    @Suppress("RemoveExplicitTypeArguments") val paymentMethodList = mutableListOf<PaymentMethodDataClass>(
        PaymentMethodDataClass(
            name = "mpesa",
            image = painterResource(id = R.drawable.mpesa)
        ),
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        paymentMethodList.forEachIndexed { index, item ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        selectPaymentMethod(paymentMethodList[index])
                    }
                ,
                contentAlignment = Alignment.CenterStart
            ) {
                PaymentMethod(
                    image = item.image,
                    onAddClicked = { onAddPaymentMethod(item) }
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
        PaymentMethodList(
            selectPaymentMethod = {},
            onAddPaymentMethod = {}
        )
    }
}