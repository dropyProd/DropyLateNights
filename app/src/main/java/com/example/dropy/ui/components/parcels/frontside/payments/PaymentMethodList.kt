package com.example.dropymain.presentation.components.parcels.frontside.payments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R

@Composable
fun PaymentMethodList(
    selectPaymentMethod:(PaymentMethodDataClass)->Unit
){
    @Suppress("RemoveExplicitTypeArguments") val paymentMethodList = mutableListOf<PaymentMethodDataClass>(
        PaymentMethodDataClass(
            name = "mpesa",
            image = painterResource(id = R.drawable.mpesa)
        ),
    )

    val selectedMethodIndex = remember {
        mutableStateOf(-1)
    }

    fun addMethod(index: Int){
        val selected = paymentMethodList[index]
        //navigate to the page used to add payment method eg add mastercard
    }

    @Suppress("RemoveExplicitTypeArguments")

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        paymentMethodList.forEachIndexed { index, item ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        selectedMethodIndex.value = index
                        selectPaymentMethod(paymentMethodList[index])
                    }
                ,
                contentAlignment = Alignment.CenterStart
            ) {
                PaymentMethod(
                    image = item.image,
                    myIndex = index,
                    add = {addMethod(it)}
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
            selectPaymentMethod = {}
        )
    }
}