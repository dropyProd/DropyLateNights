package com.example.dropy.ui.components.topup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun AmountItem(
    modifier: Modifier = Modifier,
    title: String,
    price: String,
    onPriceChange: (String) -> Unit,
    addPrice: () -> Unit,
    minusPrice: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, color = Color(0x57584AFF), shape = RoundedCornerShape(12.dp))
            .padding(vertical = 45.dp, horizontal = 22.dp),
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {

        Text(
            text = title,
            style = TextStyle(
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(color = Color(0xFFFCD313))
                    .clickable { minusPrice() },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.minus),
                    contentDescription = "",
                    modifier = Modifier.size(15.dp),
                    colorFilter = ColorFilter.tint(color = Color.Black)
                )
            }

            Row(
                modifier = Modifier
                    .widthIn(min = 50.dp, max = 150.dp)
                    .border(1.dp, color = Color(0x57584AFF), shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 17.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                BasicTextField(
                    value = price,
                    onValueChange = onPriceChange,
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center
                    ),
                    cursorBrush = Brush.sweepGradient(
                        listOf(Color.Black.copy(.8f), Color.Black.copy(.9f))
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

            Row(
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(color = Color(0xFFFCD313))
                    .clickable { addPrice() },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = "",
                    modifier = Modifier.size(15.dp),
                    colorFilter = ColorFilter.tint(color = Color.Black)
                )
            }
        }

        val amountList = remember {
            mutableListOf(100, 200, 500, 1000, 3000, 5000)
        }

        FlowRow(
            modifier = Modifier.padding(8.dp),
            mainAxisAlignment = MainAxisAlignment.Center,
            mainAxisSize = SizeMode.Expand,
            crossAxisSpacing = 12.dp,
            mainAxisSpacing = 8.dp
        ) {
            amountList.forEach { item ->
                PriceItem(number = item)
            }
        }

      /*  LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(min = 5.dp, max = 700.dp)
        ) {
            itemsIndexed(items = amountList) { index, item ->
                PriceItem(number = item)
            }
        }*/
    }
}

@Composable
fun PriceItem(number: Int) {
    val clicked = remember {
        mutableStateOf(false)
    }
    val back = remember {
        mutableStateOf(if (clicked.value) Color(0xFFFCD313) else Color.Transparent)
    }
    Row(
        modifier = Modifier
            .padding(10.dp)
            .background(color = back.value)
            .wrapContentSize()
            .border(1.dp, color = Color(0x57584AFF), shape = RoundedCornerShape(7.dp))
            .padding(horizontal = 14.dp, vertical = 20.dp)
            .clickable {
                clicked.value = !clicked.value
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${number}/-",
            style = TextStyle(
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold
            )
        )

    }
}