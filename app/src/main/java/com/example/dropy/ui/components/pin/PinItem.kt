package com.example.dropy.ui.components.pin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PinItem(
    text: String,
    value: String,
    onValueChange: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color(0xFFA8A1FF), shape = RoundedCornerShape(13.dp))
            .padding(horizontal = 12.dp, vertical = 35.dp),
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {

        Text(
            text = text,
            style = TextStyle(color = Black, fontWeight = FontWeight.ExtraBold, fontSize = 15.sp),
            modifier = Modifier.padding(start = 15.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth(.9f)
                .align(Alignment.CenterHorizontally)
        ) {

            OtpChat(value = value, onValueChange = onValueChange)
            OtpChat(value = value, onValueChange = onValueChange)
            OtpChat(value = value, onValueChange = onValueChange)
            OtpChat(value = value, onValueChange = onValueChange)
        }

    }
}

@Composable
fun PinItemRider(
    text: String,
    value: String,
    onValueChange: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color(0xFFA8A1FF), shape = RoundedCornerShape(13.dp))
            .padding(horizontal = 12.dp, vertical = 35.dp),
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {

        Text(
            text = text,
            style = TextStyle(color = Black, fontWeight = FontWeight.ExtraBold, fontSize = 15.sp),
            modifier = Modifier.padding(start = 15.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth(.9f)
                .horizontalScroll(rememberScrollState())
                .align(Alignment.CenterHorizontally)
        ) {

            OtpChat(value = value, onValueChange = onValueChange)
            OtpChat(value = value, onValueChange = onValueChange)
            OtpChat(value = value, onValueChange = onValueChange)
            OtpChat(value = value, onValueChange = onValueChange)
            OtpChat(value = value, onValueChange = onValueChange)
            OtpChat(value = value, onValueChange = onValueChange)
            OtpChat(value = value, onValueChange = onValueChange)
            OtpChat(value = value, onValueChange = onValueChange)
            OtpChat(value = value, onValueChange = onValueChange)
            OtpChat(value = value, onValueChange = onValueChange)
            OtpChat(value = value, onValueChange = onValueChange)

        }

    }
}

@Composable
fun OtpChat(
    value: String,
    onValueChange: (String) -> Unit
) {
    //   var text by remember { mutableStateOf("1") }
    val maxChar = 1

    Column(
        Modifier
            .background(Color.White)
            .border(width = 1.dp, color = Color(0xFFA8A1FF), shape = RoundedCornerShape(16.dp))
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = value,
            onValueChange = {
                if (it.length <= maxChar) {
                    onValueChange(it)
                }
            },
            modifier = Modifier.width(50.dp),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            ),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                backgroundColor = Transparent,
                unfocusedIndicatorColor = Transparent,
                focusedIndicatorColor = Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        /*       Divider(
                   Modifier
                       .width(28.dp)
                       .padding(bottom = 2.dp)
                       .offset(y = -10.dp),
                   color = White,
                   thickness = 1.dp
               )*/
    }
}