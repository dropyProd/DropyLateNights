package com.example.dropy.ui.components.commons.payments.paymentpage.chargesheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.commons.DotsRow
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.payments.paymentpage.paymentmethod.PaymentMethodDataClass
import java.util.*

@Composable
fun ChargeSheet(
    paymentMethod: PaymentMethodDataClass? = null,
    charges:List<ChargeDataClass>,
    totalCharge:Int
){
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ){
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
                DotsRow(Color.LightGray)
            }
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .aspectRatio(4 / 2f)
                    .clip(RoundedCornerShape(8.dp))
                    .border(2.dp, Color.LightGray, RoundedCornerShape(8.dp))
                ,
            ) {
                if (paymentMethod != null) {
                    Image(
                        painter = paymentMethod.image,
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
        for (item in charges){
            Charge(chargeName = item.chargeName, chargeAmount = item.chargeAmount)
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
                textSize = 24,
                text = "Total",
                isExtraBold = true
            )
            Box(modifier = Modifier.weight(1f)){
                DotsRow(Color.LightGray)
            }
            SimpleText(
                textSize = 24,
                text = "$totalCharge/-",
                isExtraBold = true
            )
        }
    }
}

@Composable
fun Charge(
    chargeName:String,
    chargeAmount:Int
){
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
            text = chargeName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
            textColor = Color.Black,
            isUppercase = false,
            isBold =false
        )
        Box(modifier = Modifier.weight(1f)){
            DotsRow(Color.LightGray)
        }
        SimpleText(
            textSize = 21,
            text = "$chargeAmount/-",
            textColor = Color.Black,
            isUppercase = true,
            isBold =true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChargeSheetPreview(){
    Column(Modifier.fillMaxSize()) {
        ChargeSheet(
            charges = listOf(
                ChargeDataClass(
                    chargeName = "charge name",
                    chargeAmount = 1234
                ),
                ChargeDataClass(
                    chargeName = "charge name",
                    chargeAmount = 1234
                )
            ),
            totalCharge = 1234
        )
    }
}