

package com.example.dropy.ui.components.parcels.frontside.expressdeliverymethod

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.theme.DropyYellow

@Composable
fun ExpressDeliveryMethodList(
    selectDeliveryMode:(ExpressDeliveryMethodListItemDataClass)->Unit
){

    @Suppress("RemoveExplicitTypeArguments")val deliveryOptions = mutableListOf<ExpressDeliveryMethodListItemDataClass>(
        ExpressDeliveryMethodListItemDataClass(image = painterResource(id = R.drawable.bicycle),type = "on foot", etaInMinutes = 50, price = 50),
        ExpressDeliveryMethodListItemDataClass(image = painterResource(id = R.drawable.bicycle),type = "cyclist", etaInMinutes = 30, price = 70),
        ExpressDeliveryMethodListItemDataClass(image = painterResource(id = R.drawable.bicycle),type = "rider", etaInMinutes = 20, price = 100),
    )


    val selectedMethod = remember {
        mutableStateOf(0)
    }

    fun selectedDeliveryMode(index: Int){
        val deliveryMode = deliveryOptions[index]
        selectDeliveryMode(deliveryMode)
        selectedMethod.value = index
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
            ,
        ) {
            Box(
                modifier = Modifier
                    .border(2.dp, Color.LightGray, RoundedCornerShape(16.dp))
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    deliveryOptions.forEachIndexed { index, item ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(24.dp))
                                .background(
                                    if (index == selectedMethod.value) {
                                        Color(255, 249, 219)
                                    } else {
                                        Color.White
                                    }
                                )
                            ,
                            contentAlignment = Alignment.Center
                        ) {
                            ExpressDeliveryMethodListItem(
                                image =item.image,
                                type = item.type,
                                etaInMinutes =item.etaInMinutes,
                                price = item.price,
                                myIndex = index,
                                select = { selectedDeliveryMode(it) }
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .offset(y = (-16).dp, x = 16.dp)
            ){
                StyledText(
                    backgroundColor = DropyYellow,
                    textSize = 12,
                    text = "delivery mode",
                    isUppercase = true,
                    isBold = false,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeliveryModeListPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        ExpressDeliveryMethodList(selectDeliveryMode = {})
    }
}