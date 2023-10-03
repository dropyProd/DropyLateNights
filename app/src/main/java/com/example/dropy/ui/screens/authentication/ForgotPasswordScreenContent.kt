package com.example.dropy.ui.screens.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R

@Composable
fun ForgotPasswordScreenContent(
    onPhoneNumberValueChanged: (String) -> Unit,
    uiState: AuthenticationUiState?,
    onSubmitButtonClicked: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(top = 140.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            contentAlignment = Alignment.TopStart

        ) {
            Image(
                painter = painterResource(id = R.drawable.enterphone),
                contentDescription = "verification",
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Text(
                text = "FORGOT PASSWORD?",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp
            )
            Text(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth(),
                text = "Enter your mobile number\nto get OTP code",
                textAlign = TextAlign.Center,
                lineHeight = 32.sp
            )
        }
        Column(
            modifier = Modifier
                .offset(y = (-70).dp)
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = uiState?.currentPhoneNumberValue.toString(),
                onValueChange = { onPhoneNumberValueChanged(it) },
                label = { Text(text = "Enter Phone Number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                )
            )

            Button(
                onClick = { onSubmitButtonClicked() },
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth(0.7f)
                    .clip(RoundedCornerShape(50)),
                colors = ButtonDefaults.buttonColors(Color.Black, Color.White)
            ) {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "lock",
                    modifier = Modifier
                        .size(16.dp),
                    tint = Color.White
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp),
                    text = "GET OTP",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 10.sp,
                    letterSpacing = (-1).sp,
                    color = Color.White
                )
            }
        }
        /*Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = "Don't have Account?",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = { onRegisterButtonClicked() },
                modifier = Modifier
                    .sizeIn(minHeight = 32.dp, maxHeight = 48.dp)
                    .clip(RoundedCornerShape(50)),
                colors = ButtonDefaults.buttonColors(Color.Black)
            ) {
                Text(
                    text = "REGISTER",
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(252, 211, 19)
                )

            }
        }*/
    }
}

@Preview(showBackground = true)
@Composable
fun demo() {
    ForgotPasswordScreenContent(onPhoneNumberValueChanged = {}, uiState = null) {

    }
}