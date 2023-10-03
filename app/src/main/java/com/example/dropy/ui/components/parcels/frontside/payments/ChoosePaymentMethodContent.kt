package com.example.dropymain.presentation.components.parcels.frontside.payments

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.commons.Dot
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue

@Composable
fun ChoosePaymentMethodContent(){
    Column(
        modifier = Modifier
            .fillMaxSize()
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        val selectedPaymentMethod:MutableState<PaymentMethodDataClass?> = remember {
            mutableStateOf(null)
        }

        val addInsurance = remember {
            mutableStateOf(true)
        }
        val homeToCourier = remember {
            mutableStateOf(50)
        }
        val courierToDestination = remember {
            mutableStateOf(100)
        }
        val dropyInsurance:MutableState<Int> = remember {
            mutableStateOf(if (addInsurance.value){
                (1/100) * (homeToCourier.value + courierToDestination.value)
            }else{
                0
            })
        }
        Box(modifier = Modifier.fillMaxWidth()){
            StyledText(
                textColor = Color.Black,
                backgroundColor = DropyYellow,
                borderColor = Color.Transparent,
                textSize = 12,
                text = "complete payment",
                isUppercase = true,
                isBold = true
            )
        }

        DropyPayHeader(selectPaymentMethod = {selectedPaymentMethod.value = it}, walletBalance = 3452)
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(end = 16.dp, top = 8.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ){
                SimpleText(
                    textSize = 15,
                    text = "Payment Options",
                    textColor = Color.Black,
                    isUppercase = false,
                    isBold = true
                )
            }
            PaymentMethodList(selectPaymentMethod = {selectedPaymentMethod.value = it})
        }
        DropyInsurance(
            insured = addInsurance.value,
            changeInsuranceStatus = {addInsurance.value = it}
        )
        Totals(
            selectedMethod = selectedPaymentMethod.value,
            homeToCourier = homeToCourier.value,
            courierToDestination = courierToDestination.value,
            insurance = dropyInsurance.value
        )
        TotallyRoundedButton(
            icon = null,
            buttonText = "pay",
            textFontSize = 15,
            backgroundColor = DropyYellow,
            widthFraction = 0.5,
            action = {}
        )
    }
}

@Composable
fun DropyInsurance(
    insured:Boolean,
    changeInsuranceStatus:(Boolean)->Unit
){
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            SimpleText(
                textSize = 15,
                text = "parcel insurance",
                textColor = Color.Black,
                isUppercase = true,
                isBold =true,
                padding = 4
            )
            SimpleText(
                textSize = 15,
                text = "parcel insurance",
                textColor = LightBlue,
                isUppercase = false,
                isBold =false,
                padding = 4
            )
        }
        Column(
            modifier = Modifier
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Switch(
                checked = insured,
                onCheckedChange = { changeInsuranceStatus(it) },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = LightBlue,
                    uncheckedThumbColor = LightBlue,
                    uncheckedTrackColor = Color.LightGray
                )
            )
            SimpleText(
                textSize = 15,
                text = "1% of total",
                textColor = LightBlue,
                isUppercase = true,
                isBold =false,
                padding = 4
            )
            StyledText(
                textColor = Color.White,
                backgroundColor = LightBlue,
                textSize = 12,
                text = "dropy insurance",
                isUppercase = true,
                isBold = true
            )
        }
    }
}

@Composable
fun Totals(
    selectedMethod:PaymentMethodDataClass?,
    homeToCourier:Int,
    courierToDestination:Int,
    insurance:Int
){
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
                ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SimpleText(
                    textSize = 15,
                    text = "Payment Option",
                    textColor = Color.Black,
                    isUppercase = false,
                    isBold =false
                )
                Box(modifier = Modifier.weight(1f)){
                    DotsRow()
                }
                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .aspectRatio(4 / 2f)
                        .clip(RoundedCornerShape(8.dp))
                        .border(2.dp, Color.LightGray, RoundedCornerShape(8.dp))
                    ,
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
                    .fillMaxWidth()
                ,
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SimpleText(
                    textSize = 15,
                    text = "Price from home to courier",
                    textColor = Color.Black,
                    isUppercase = false,
                    isBold =false
                )
                Box(modifier = Modifier.weight(1f)){
                    DotsRow()
                }
                SimpleText(
                    textSize = 21,
                    text = "$homeToCourier/-",
                    textColor = Color.Black,
                    isUppercase = true,
                    isBold =true
                )
            }
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
                ,
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SimpleText(
                    textSize = 15,
                    text = "Price from courier to receiver",
                    textColor = Color.Black,
                    isUppercase = false,
                    isBold =false
                )
                Box(modifier = Modifier.weight(1f)){
                    DotsRow()
                }
                SimpleText(
                    textSize = 21,
                    text = "$courierToDestination/-",
                    textColor = Color.Black,
                    isUppercase = true,
                    isBold =true
                )
            }
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
                ,
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SimpleText(
                    textSize = 15,
                    text = "Dropy insurance",
                    textColor = Color.Black,
                    isUppercase = false,
                    isBold =false
                )
                Box(modifier = Modifier.weight(1f)){
                    DotsRow()
                }
                SimpleText(
                    textSize = 21,
                    text = "$insurance/-",
                    textColor = Color.Black,
                    isUppercase = true,
                    isBold =true
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
            ,
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SimpleText(
                textSize = 32,
                text = "Total",
                textColor = Color.Black,
                isUppercase = false,
                isBold =false,
                isExtraBold = true
            )
            Box(modifier = Modifier.weight(1f)){
                DotsRow()
            }
            SimpleText(
                textSize = 32,
                text = "${homeToCourier+courierToDestination+insurance}/-",
                textColor = Color.Black,
                isUppercase = true,
                isBold =false,
                isExtraBold = true
            )
        }
    }
}

@Composable
fun DotsRow(){
    BoxWithConstraints(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxWidth(1f)
        ,
    ) {
        val width = this.maxWidth.value
        val dotsNumber = (width/8).toInt()
        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (x in 1..dotsNumber){
                Dot(color = Color.LightGray)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ChoosePaymentMethodContentPreview(){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ChoosePaymentMethodContent()
    }
}