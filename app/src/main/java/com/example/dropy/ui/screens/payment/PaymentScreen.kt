package com.example.dropy.ui.screens.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.topup.AmountItem
import com.example.dropy.ui.components.topup.HeaderAmountItem
import com.example.dropy.ui.components.topup.TopInfo
import com.example.dropy.ui.components.topup.YellowInfo

@Composable
fun PaymentScreen(route: String = "withdraw") {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(75.dp)
    ) {

        val textt = remember {
            mutableStateOf(if (route.equals("topup")) "TOP UP" else "WITHDRAW")
        }

        TopInfo(text = "${textt.value} AMOUNT")

        Column(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(75.dp)

        ) {

            YellowInfo(text = "select one ${textt.value} method below")

            val price = remember {
                mutableStateOf("7340")
            }


            HeaderAmountItem(price = price.value.toInt())

            AmountItem(title = "ENTER ${textt.value} AMOUNT", price = price.value, onPriceChange = {
                price.value = it
            }, addPrice = {

                (price.value.toInt() + 1).also {
                    price.value = it.toString()
                }


            }, minusPrice = {
                (price.value.toInt() - 1).also {
                    price.value = it.toString()
                }
            })
            /*  PinItem(text = "CREATE YOUR 4 DIGIT PIN")
              PinItem(text = "RETYPE YOUR PIN AGAIN")
  */
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White,
                ),
                onClick = {

                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = textt.value
                )

            }
        }
    }
}