package com.example.dropy.ui.components.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun PaymentDialog(show: Boolean, dismissDialog: () -> Unit) {

    val phone = remember {
        mutableStateOf("0794700294")
    }
    val amount = remember {
        mutableStateOf("10")
    }

    if (show) {
        Dialog(onDismissRequest = { dismissDialog() }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RoundedCornerShape(13.dp))
                    .padding(vertical = 12.dp, horizontal = 15.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "PAYMENT",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )

                val phoneLimit = remember {
                    mutableStateOf(10)
                }

                TextField(
                    value = phone.value,
                    onValueChange = {
                        if (it.length < phoneLimit.value) {
                            phone.value = it
                        }
                    },
                    modifier = Modifier.fillMaxWidth(.7f),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.LightGray.copy(.7f),
                        backgroundColor = Color.Transparent
                    ),
                    label = {
                        Text(text = "Enter phone number")
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                val amountLimit = remember {
                    mutableStateOf(100)
                }

// STOPSHIP: limit chars in textfields

                TextField(
                    value = amount.value,
                    onValueChange = {
                        if (!it.isEmpty()){
                            if (it.toInt() < amountLimit.value) {
                                amount.value = it
                            }
                        }else {
                            amount.value = it
                        }
                       // amount.value = it
                    },
                    modifier = Modifier.fillMaxWidth(.7f),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.LightGray.copy(.7f),
                        backgroundColor = Color.Transparent
                    ),
                    label = {
                        Text(text = "Enter amount")
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Button(
                    modifier = Modifier
                        .width(70.dp)
                        .height(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF584AFF),
                        contentColor = Color.White,
                    ),
                    onClick = {

                    },
                ) {
                    Text(
                        text = "PAY",
                        style = TextStyle(color = Color.White, fontWeight = FontWeight.ExtraBold)
                    )

                }
            }
        }
    }
}