package com.example.dropy.ui.screens.topUp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.components.shops.frontside.customer.orderprocess.payments.PaymentMethod
import com.example.dropy.ui.components.shops.frontside.customer.orderprocess.payments.PaymentMethodDataClass
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.screens.locale.BackgroundedImage
import com.example.dropy.ui.theme.DropyYellow

@Composable
fun TopUpContent(
    topUpUiState: TopUpUiState,
    onAmountChange: (String) -> Unit,
    topUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        ClippedHeader(title = "${if (topUpUiState.state.equals("TOP UP")) "TOP UP" else "WITHDRAW"} AMOUNT")

        BackgroundedText(
            background = DropyYellow,
            textColor = Color.Black,
            text = "select one top up method below",
            modifier = Modifier.padding(top = 18.dp, start = 12.dp)
        )

        Row(
            modifier = Modifier
                .padding(top = 23.dp, start = 32.dp, end = 25.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            @Suppress("RemoveExplicitTypeArguments") val paymentMethodList =
                mutableListOf<PaymentMethodDataClass>(
                    PaymentMethodDataClass(
                        name = "mpesa",
                        image = painterResource(id = R.drawable.mpesa)
                    ),
                )

            paymentMethodList.forEachIndexed { index, item ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
//                            selectPaymentMethod(paymentMethodList[index])
                        },
                    contentAlignment = Alignment.CenterStart
                ) {
                    PaymentMethod(
                        image = item.image,
                        onAddClicked = { /*onAddPaymentMethod(item)*/ }
                    )
                }
            }
            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(2.dp, Color.LightGray, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    SimpleText(
                        textSize = 15,
                        text = "wallet balance",
                        textColor = Color.Black,
                        isUppercase = true,
                        isBold = false,
                        padding = 4,
                        isExtraBold = true,
                        fontWeight = FontWeight.ExtraBold,
                        font = Font(R.font.axiformaextrabold)
                    )
                    SimpleText(
                        textSize = 26,
                        text = "0/-",
                        textColor = Color.Black,
                        isUppercase = true,
                        isBold = false,
                        padding = 4,
                        isExtraBold = true,
                        fontWeight = FontWeight.ExtraBold,
                        font = Font(R.font.axiformaextrabold)
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .padding(top = 33.dp, start = 13.dp, end = 12.dp)
                .fillMaxWidth()
                .height(346.dp)
                .background(Color.White, RoundedCornerShape(8.dp))
                .border(1.dp, Color(0xFFDEDEDE), RoundedCornerShape(8.dp))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "ENTER WITHDRAW AMOUNT",
                    fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformaheavy)
                    ),
                    letterSpacing = (-0.43).sp,
                    lineHeight = 17.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 51.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(30.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BackgroundedImage(
                            background = Color.Transparent,
                            border = Color.Black,
                            shape = CircleShape,
                            image = R.drawable.minus
                        )
                        Row(
                            modifier = Modifier
                                .width(186.dp)
                                .wrapContentHeight()
                                .background(color = Color.White, RoundedCornerShape(8.dp))
                                .border(
                                    width = 1.dp,
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0xFFDEDEDE)
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            TextField(
                                value = topUpUiState.amount,
                                onValueChange = onAmountChange,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.White,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    cursorColor = Color.Black
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                textStyle = TextStyle(
                                    color = Color.Black,
                                    fontSize = 30.sp,
//                        fontWeight = FontWeight.SemiBold,
                                    fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                                    letterSpacing = (-1.44).sp,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }

                        BackgroundedImage(
                            background = Color.Black,
                            border = Color.Transparent,
                            shape = CircleShape,
                            image = R.drawable.plus,
                            imageColor = Color.White
                        )
                    }
                }

                val listOne = remember {
                    mutableListOf(
                        "100", "250", "500"
                    )
                }
                val listTwo = remember {
                    mutableListOf(
                        "1000", "2000", "5000"
                    )
                }

                LazyRow(
                    modifier = Modifier.padding(top = 61.dp),
                    horizontalArrangement = Arrangement.spacedBy(42.dp),
                    content = {
                        itemsIndexed(listOne) { index, item ->
                            BackgroundedText(
                                background = Color.Transparent,
                                textColor = Color.Black,
                                text = "$item/-",
                                shape = 6,
                                borderColor = Color(0xFF979797)
                            )

                        }
                    })
                LazyRow(
                    modifier = Modifier.padding(top = 22.dp),
                    horizontalArrangement = Arrangement.spacedBy(42.dp),
                    content = {
                        itemsIndexed(listTwo) { index, item ->
                            BackgroundedText(
                                background = Color.Transparent,
                                textColor = Color.Black,
                                text = "$item/-",
                                shape = 6,
                                borderColor = Color(0xFF979797)
                            )

                        }
                    })

            }
        }

        Column(
            modifier = Modifier
                .padding(top = 25.dp)
                .fillMaxSize()
                .padding(bottom = 15.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BackgroundedText(
                background = Color.Black,
                textColor = Color.White,
                text = if (topUpUiState.state.equals("TOP UP")) "TOP UP" else "WITHDRAW",
                vertical = 8,
                textSize = 10,
                modifier = Modifier.clickable { topUp() }
            )
        }
    }
}

@Preview
@Composable
fun demo() {
    TopUpContent(topUpUiState = TopUpUiState(), topUp = {}, onAmountChange = {})
}
