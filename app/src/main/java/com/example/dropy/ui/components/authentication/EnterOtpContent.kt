package com.example.dropy.ui.components.authentication

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.screens.authentication.AuthenticationUiState
import com.example.dropy.ui.theme.DropyTheme

@Composable
fun EnterOtpContent(
    uiState: AuthenticationUiState,
    onOtpValueChanged: (String)->Unit,
    onVerifyButtonClicked: ()->Unit,
    onResendOtpButtonClicked: ()->Unit,
    onErrorDismissed: (String)->Unit
){


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(24.dp)
    ){

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .background(Color.White),
            contentAlignment = Alignment.TopStart

        ) {
            Image(
                painter = painterResource(id = R.drawable.otpscreen),
                contentDescription = "verification",
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Text(
                text = "OTP VERIFICATION",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp
            )
            Text(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth(),
                text = "Enter the OTP you have received\non your phone",
                textAlign = TextAlign.Center,
                lineHeight = 32.sp
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            OutlinedTextField(
                value = uiState.currentOtpValue,
                onValueChange ={onOtpValueChanged(it)},
                label = { Text(text = "Enter OTP") },
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .fillMaxWidth()
                ,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            Button(
                onClick = {onVerifyButtonClicked()},
                modifier = Modifier
                    .sizeIn(minHeight = 48.dp, maxHeight = 48.dp)
                    .fillMaxWidth(0.7f)
                    .clip(RoundedCornerShape(50))
                ,
                colors = ButtonDefaults.buttonColors(Color(252, 211, 19))
            ) {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "lock",
                    modifier = Modifier
                        .size(12.dp)
                )
                Text(
                    text = "VERIFY",
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(start = 8.dp)
                    ,
                    fontSize = 10.sp,
                )
            }
            Text(
                text = "Didn't get OTP? Resend code.",
                modifier = Modifier
                    .padding(top = 24.dp)
                    .clickable { onResendOtpButtonClicked() }
            )
        }
    }
    if (uiState.errorMessages.isNotEmpty()){
        val context = LocalContext.current

        uiState.errorMessages.forEachIndexed { _, err ->
            Toast.makeText(
                context,
                err,
                Toast.LENGTH_SHORT
            ).show()
            onErrorDismissed(err)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EnterOtpContentPreview() {
    DropyTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            EnterOtpContent(
                uiState = AuthenticationUiState(),
                onOtpValueChanged = { /*TODO*/ },
                onVerifyButtonClicked = { /*TODO*/ },
                onResendOtpButtonClicked = { /*TODO*/ },
                onErrorDismissed = { /*TODO*/ }
            )
        }
    }
}