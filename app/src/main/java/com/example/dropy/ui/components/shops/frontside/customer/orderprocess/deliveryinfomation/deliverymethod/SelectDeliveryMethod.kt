package com.example.dropy.ui.components.shops.frontside.customer.orderprocess.deliveryinfomation.deliverymethod

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.network.models.deliverymethods.DeliveryMethodResponseItem
import com.example.dropy.network.models.directions.Distance
import kotlin.random.Random

@Composable
fun SelectDeliveryMethod(
    selectedMethod: (DeliveryMethodResponseItem, Int) -> Unit,
    deliveryMethodList: List<DeliveryMethodResponseItem>,
    shopDistance: Int,
    eta: Int,
    modifier: Modifier = Modifier
) {
/*    LaunchedEffect(key1 = true, block ={
        selectedMethod(deliveryMethodList[0])
    } )*/
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            text = "Delivery Options",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth(),
            letterSpacing =  (-0.58).sp,
            fontFamily = FontFamily(Font(R.font.axiformabold)),
            fontSize = 12.sp
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val selectedMethodIndex = remember {
                mutableStateOf(0)
            }
            for (item in deliveryMethodList) {
                val index = deliveryMethodList.indexOf(item)
                Box(

                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(
                            if (index == selectedMethodIndex.value) {
                                Color(255, 249, 219)
                            } else {
                                Color.White
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    val price =(shopDistance/1000) * item.default_charge_per_km!!

                    DeliveryMethod(
                        deliveryMethodIconUrl = item.icon.toString(),
                        type = item.method_name.toString(),
                        etaInMinutes = eta,
                        price = price,
                        onSelectClicked = {
                            selectedMethodIndex.value = index
                            selectedMethod(item, price)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectDeliveryMethodPreview() {
    Column(Modifier.fillMaxSize()) {



        SelectDeliveryMethod(
            selectedMethod = {_, _ ->},
            deliveryMethodList = listOf(),
            shopDistance = 0,
            eta = 0
        )
    }
}