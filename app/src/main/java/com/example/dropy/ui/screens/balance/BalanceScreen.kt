package com.example.dropy.ui.screens.balance

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.ui.components.balance.BalanceHead
import com.example.dropy.R
import com.example.dropy.ui.components.balance.Transactions
import com.example.dropy.ui.components.payment.PaymentDialog

@Composable
fun BalanceScreen() {

    val show = remember {
        mutableStateOf(false)
    }


    PaymentDialog(show = show.value, dismissDialog = {
        show.value = !show.value
    })


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        BalanceHead(clicked = {
            show.value = !show.value
        })

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "TRANSACTION HISTORY",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                )
            )

            Row(
                modifier = Modifier
                    .width(65.dp)
                    .height(65.dp)
                    .border(1.dp, color = Color(0xFF584AFF), shape = RoundedCornerShape(15.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.filter),
                    contentDescription = "",
                    modifier = Modifier.size(36.dp),
                    colorFilter = ColorFilter.tint(color = Color(0xFF584AFF))
                )
            }

        }

        Text(
            text = "TUE, MAR 23, 2021",
            style = TextStyle(
                color = Color.LightGray,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.axiformaextrabold))
            )
        )

        Transactions()
    }
}

@Preview(showBackground = true)
@Composable
fun BalacePreview(){
    BalanceScreen()

}