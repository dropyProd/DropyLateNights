package com.example.dropymain.presentation.components.parcels.frontside.payments

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.SimpleText

@Composable
fun DropyPayHeader(
    selectPaymentMethod:(PaymentMethodDataClass)->Unit,
    walletBalance:Int
){
    val dropyPay = PaymentMethodDataClass(
        name = "dopypay",
        image = painterResource(id = R.drawable.dropylogo)
    )
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Column(
            modifier = Modifier
                .clickable { selectPaymentMethod(dropyPay) }
                .width(120.dp)
                .aspectRatio(3 / 2f)
                .clip(RoundedCornerShape(8.dp))
                .border(2.dp, Color.LightGray, RoundedCornerShape(8.dp))
            ,
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.dropylogo),
                contentDescription = "dropy logo",
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(8.dp))
                ,
                contentScale = ContentScale.FillWidth
            )
            SimpleText(
                textSize = 21,
                text = "pay",
                textColor = Color.Black,
                isUppercase = true,
                isBold =false,
                padding = 4,
                isExtraBold = true
            )
        }
        Box(
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(8.dp))
                .border(2.dp, Color.LightGray, RoundedCornerShape(8.dp))
            ,
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                ,
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                SimpleText(
                    textSize = 15,
                    text = "wallet balance",
                    textColor = Color.Black,
                    isUppercase = true,
                    isBold =false,
                    padding = 4
                )
                SimpleText(
                    textSize = 26,
                    text = "$walletBalance/-",
                    textColor = Color.Black,
                    isUppercase = true,
                    isBold =false,
                    padding = 4,
                    isExtraBold = true
                )
            }
        }

    }
}
@Preview(showBackground = true)
@Composable
fun DropyPayHeaderPreview(){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        DropyPayHeader(
            selectPaymentMethod = {},
            walletBalance = 23
        )
    }
}