package com.example.dropy.ui.screens.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R

@Composable
fun AddPayment() {
    val cardNumber = remember {
        mutableStateOf("1356  2025  4352  6354")
    }

    val cardHolderName = remember {
        mutableStateOf("Chirag Parmar")
    }

    val month = remember {
        mutableStateOf("01")
    }

    val year = remember {
        mutableStateOf("2025")
    }
    val cardholderCCV = remember {
        mutableStateOf("220")
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(13.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier.padding(horizontal = 17.dp)
        ) {
            Text(
                text = "ADD VISA CARD",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = "Add visa debit or credit card details below to use the visa card for your payment",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier.fillMaxWidth(.7f)
            )
        }

        CreditCardLayout(
            month = month.value,
            year = year.value,
            cardNumber = cardNumber.value,
            name = cardHolderName.value
        )

        Column(verticalArrangement = Arrangement.spacedBy(17.dp), modifier = Modifier.padding(horizontal = 17.dp)) {
            TextField(
                value = cardNumber.value,
                onValueChange = {
                    cardNumber.value = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = TextStyle(
                    color = colorResource(id = R.color.black), // below line is use to change font family.
                    fontFamily = FontFamily(Font(R.font.axiformasemibold)),
                    fontSize = 16.sp
                ),
                label = {
                    Text(text = "Card Number")
                },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = colorResource(id = R.color.black),
                    placeholderColor = colorResource(id = R.color.black).copy(alpha = 0.4f),
                    focusedIndicatorColor = colorResource(id = R.color.black),
                    unfocusedIndicatorColor = colorResource(id = R.color.black).copy(alpha = 0.4f),
                    focusedLabelColor = colorResource(id = R.color.black),
                    unfocusedLabelColor = colorResource(id = R.color.black).copy(alpha = 0.4f),
                    backgroundColor = Color.Transparent
                ),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            TextField(
                value = cardHolderName.value,
                onValueChange = {
                    cardHolderName.value = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = TextStyle(
                    color = colorResource(id = R.color.black), // below line is use to change font family.
                    fontFamily = FontFamily(Font(R.font.axiformasemibold)),
                    fontSize = 16.sp
                ),
                label = {
                    Text(text = "Cardholder Name")
                },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = colorResource(id = R.color.black),
                    placeholderColor = colorResource(id = R.color.black).copy(alpha = 0.4f),
                    focusedIndicatorColor = colorResource(id = R.color.black),
                    unfocusedIndicatorColor = colorResource(id = R.color.black).copy(alpha = 0.4f),
                    focusedLabelColor = colorResource(id = R.color.black),
                    unfocusedLabelColor = colorResource(id = R.color.black).copy(alpha = 0.4f),
                    backgroundColor = Color.Transparent
                ),
                maxLines = 1,
            )


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = month.value,
                    onValueChange = {
                        month.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(.34f),
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.black), // below line is use to change font family.
                        fontFamily = FontFamily(Font(R.font.axiformasemibold)),
                        fontSize = 16.sp
                    ),
                    label = {
                        Text(text = "Month")
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = colorResource(id = R.color.black),
                        placeholderColor = colorResource(id = R.color.black).copy(alpha = 0.4f),
                        focusedIndicatorColor = colorResource(id = R.color.black),
                        unfocusedIndicatorColor = colorResource(id = R.color.black).copy(alpha = 0.4f),
                        focusedLabelColor = colorResource(id = R.color.black),
                        unfocusedLabelColor = colorResource(id = R.color.black).copy(alpha = 0.4f),
                        backgroundColor = Color.Transparent
                    ),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    trailingIcon = {
                        Icon(Icons.Filled.ChevronRight, contentDescription = "")
                    }
                )


                TextField(
                    value = year.value,
                    onValueChange = {
                        year.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(.55f),
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.black), // below line is use to change font family.
                        fontFamily = FontFamily(Font(R.font.axiformasemibold)),
                        fontSize = 16.sp
                    ),
                    label = {
                        Text(text = "Year")
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = colorResource(id = R.color.black),
                        placeholderColor = colorResource(id = R.color.black).copy(alpha = 0.4f),
                        focusedIndicatorColor = colorResource(id = R.color.black),
                        unfocusedIndicatorColor = colorResource(id = R.color.black).copy(alpha = 0.4f),
                        focusedLabelColor = colorResource(id = R.color.black),
                        unfocusedLabelColor = colorResource(id = R.color.black).copy(alpha = 0.4f),
                        backgroundColor = Color.Transparent
                    ),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    trailingIcon = {
                        Icon(Icons.Filled.ChevronRight, contentDescription = "")
                    }
                )
            }

            TextField(
                value = cardholderCCV.value,
                onValueChange = {
                    cardholderCCV.value = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = TextStyle(
                    color = colorResource(id = R.color.black), // below line is use to change font family.
                    fontFamily = FontFamily(Font(R.font.axiformasemibold)),
                    fontSize = 16.sp
                ),
                label = {
                    Text(text = "Cardholder CCV")
                },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = colorResource(id = R.color.black),
                    placeholderColor = colorResource(id = R.color.black).copy(alpha = 0.4f),
                    focusedIndicatorColor = colorResource(id = R.color.black),
                    unfocusedIndicatorColor = colorResource(id = R.color.black).copy(alpha = 0.4f),
                    focusedLabelColor = colorResource(id = R.color.black),
                    unfocusedLabelColor = colorResource(id = R.color.black).copy(alpha = 0.4f),
                    backgroundColor = Color.Transparent
                ),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                trailingIcon = {
                    Icon(Icons.Filled.ChevronRight, contentDescription = "")
                }
            )
        }

    }
}

@Composable
fun CreditCardLayout(month: String, year: String, cardNumber: String, name: String) {
    Column(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF2B1FD2), Color(0xFF584AFF)
                    )
                ),
                shape = RoundedCornerShape(14.dp)
            )
            .padding(top = 44.dp, start = 12.dp, end = 12.dp, bottom = 25.dp),
        verticalArrangement = Arrangement.spacedBy(33.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.chip),
                contentDescription = "",
                modifier = Modifier.size(32.dp)
            )

            Text(
                text = "$month/$year",
                style = TextStyle(
                    color = Color(0xFFF7F7F7).copy(.45f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily(
                        Font(R.font.montserratregular)
                    )
                )
            )
        }

        Text(
            text = cardNumber,
            style = TextStyle(
                color = Color(0xFFFFFFFF),
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(
                    Font(R.font.ocrastd)
                )
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                Text(
                    text = "CARD HOLDER",
                    style = TextStyle(
                        color = Color(0xFFF7F7F7).copy(.45f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily(
                            Font(R.font.montserratregular)
                        )
                    )
                )

                Text(
                    text = name,
                    style = TextStyle(
                        color = Color(0xFFF7F7F7),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily(
                            Font(R.font.montserratregular)
                        )
                    )
                )
            }

            Image(
                painter = painterResource(id = R.drawable.visa),
                contentDescription = "",
                modifier = Modifier.size(32.dp)
            )
        }

    }
}