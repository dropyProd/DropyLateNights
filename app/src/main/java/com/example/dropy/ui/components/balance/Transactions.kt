package com.example.dropy.ui.components.balance

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.theme.DropyYellow

@Composable
fun Transactions() {

    val transactionList = remember {
        mutableListOf(
            TransactionPojo(
                type = "WITHDRAW",
                date = "TUE, MAR 23, 2021 9:45PM",
                price = "1,320/-"
            ),
            TransactionPojo(type = "TOP UP", date = "TUE, MAR 23, 2021 9:45PM", price = "500/-"),
            TransactionPojo(type = "WITHDRAW", date = "TUE, MAR 23, 2021 9:45PM", price = "720/-"),
            TransactionPojo(type = "TOP UP", date = "TUE, MAR 23, 2021 9:45PM", price = "1,920/-"),
            TransactionPojo(
                type = "WITHDRAW",
                date = "TUE, MAR 23, 2021 9:45PM",
                price = "1,500/-"
            ),
            TransactionPojo(type = "TOP UP", date = "TUE, MAR 23, 2021 9:45PM", price = "1,300/-"),
            TransactionPojo(
                type = "WITHDRAW",
                date = "TUE, MAR 23, 2021 9:45PM",
                price = "1,920/-"
            ),
            TransactionPojo(type = "TOP UP", date = "TUE, MAR 23, 2021 9:45PM", price = "1,329/-"),
        )
    }

    LazyColumn(modifier = Modifier.padding(top = 9.dp)) {
        itemsIndexed(items = transactionList) { index: Int, item ->

            val showLine = if (index == transactionList.size - 1) {
                false
            } else true

            TransactionItem(transactionPojo = item, showLine = showLine)
        }
    }

}

@Composable
fun TransactionItem(transactionPojo: TransactionPojo, showLine: Boolean) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 9.dp),
        verticalArrangement = Arrangement.spacedBy(9.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            val color = remember {
                mutableStateOf(
                    if (transactionPojo.type.equals("WITHDRAW")) DropyYellow else Color.Black
                )
            }

            val textcolor = remember {
                mutableStateOf(
                    if (transactionPojo.type.equals("WITHDRAW")) Color.Black else Color.White
                )
            }

            Row(
                modifier = Modifier
                    .width(100.dp)
                    .height(height = 27.dp)
                    .background(color = color.value, shape = RoundedCornerShape(17.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = transactionPojo.type,
                    style = TextStyle(
                        color = textcolor.value,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                    )
                )
            }


            Text(
                text = transactionPojo.date,
                style = TextStyle(
                    color = Color.LightGray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                )
            )

            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                if (transactionPojo.type.equals("WITHDRAW")) {
                    Image(
                        painter = painterResource(id = R.drawable.down),
                        contentDescription = "",
                        modifier = Modifier.size(14.dp),
                        colorFilter = ColorFilter.tint(color = Color(0xFF8A0E00))
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.up),
                        contentDescription = "",
                        modifier = Modifier.size(14.dp)
                    )
                }


                Text(
                    text = transactionPojo.price,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                    )
                )
            }
        }

        if (showLine) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = Color.LightGray)
            ) {

            }
        }
    }
}

data class TransactionPojo(
    val type: String,
    val date: String,
    val price: String
)