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
import com.example.dropy.ui.screens.authentication.AuthenticationUiState
import com.example.dropy.ui.theme.DropyTheme

@Composable
fun EnterPhoneNumberContent(
    uiState: AuthenticationUiState,
    onPhoneNumberValueChanged: (String) -> Unit,
    onPasswordValueChanged: (String) -> Unit,
    onSubmitButtonClicked: () -> Unit,
    onRegisterButtonClicked: () -> Unit,
    onForgotPasswordClicked: () -> Unit,
    onErrorDismissed: (String) -> Unit
) {
/*    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(24.dp)
    ){
        Box(
            modifier = Modifier
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
                .fillMaxWidth()
            ,
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Text(
                text = "PHONE NUMBER",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp
            )
            Text(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth(),
                text = "Enter your phone number to get OTP\ncode",
                textAlign = TextAlign.Center,
                lineHeight = 32.sp
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            OutlinedTextField(
                value = uiState.currentPhoneNumberValue,
                onValueChange ={ onPhoneNumberValueChanged(it) },
                label = { Text(text = "Enter Phone Number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                ,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                )
            )

            Button(
                onClick = {onSubmitButtonClicked()},
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth(0.7f)
                    .clip(RoundedCornerShape(50))
                ,
                colors = ButtonDefaults.buttonColors(Color(252, 211, 19))
            ) {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "lock",
                    modifier = Modifier
                        .size(16.dp)
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp)
                    ,
                    text = "GET OTP",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 10.sp,
                    letterSpacing = (-1).sp
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = "Don't have Account?",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = {onRegisterButtonClicked()},
                modifier = Modifier
                    .sizeIn(minHeight = 32.dp, maxHeight = 48.dp)
                    .clip(RoundedCornerShape(50))
                ,
                colors = ButtonDefaults.buttonColors(Color.Black)
            ) {
                Text(
                    text = "REGISTER",
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(252, 211, 19)
                )

            }
        }
    }*/


    Column(

        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
            .padding(start = 24.dp, end = 24.dp),
//        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center,

            ) {
            Image(
                painter = painterResource(id = R.drawable.logonewblack),
                contentDescription = "Dropy logo",
                modifier = Modifier
                    .width(154.dp)
                    .height(88.dp)
            )
        }
       Box(modifier =Modifier.fillMaxSize().background(Color.White) ){
           Column(
               modifier = Modifier
                   .padding(top = 50.dp)
//                .weight(1f)
                   .fillMaxWidth(),
               horizontalAlignment = Alignment.CenterHorizontally,
           ) {
               Column(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(4.dp),
               ) {
                   Text(
                       text = "Enter Phone No",
                       textAlign = TextAlign.Start,
                       modifier = Modifier
                           .padding(bottom = 8.dp)
                           .fillMaxWidth(),
                       fontSize = 10.sp,
                       fontWeight = FontWeight.ExtraBold,
                       fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                   )
                   OutlinedTextField(
                       value = uiState.currentPhoneNumberValue ?: "",
                       onValueChange = onPhoneNumberValueChanged,
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(bottom = 3.dp),
                       keyboardOptions = KeyboardOptions(
                           keyboardType = KeyboardType.Phone
                       ),
                       textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.axiformaregular))),
                       colors = TextFieldDefaults.textFieldColors(
                           focusedLabelColor = Color.Black,
                           focusedIndicatorColor = Color.Black,
                           leadingIconColor = Color.Black,
                           backgroundColor = Color.White
                       )
                   )
                   Text(
                       text = "forgot username ?",
                       textAlign = TextAlign.End,
                       modifier = Modifier
                           .padding(bottom = 8.dp)
                           .fillMaxWidth()
                           .clickable { onForgotPasswordClicked() },
                       fontSize = 9.sp,
                       fontWeight = FontWeight.SemiBold,
                       color = Color(0xFF828282)
                   )
               }

               Column(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(start = 4.dp, end = 4.dp, top = 34.dp),
               ) {
                   Text(
                       text = "Enter your pin",
                       textAlign = TextAlign.Start,
                       modifier = Modifier
                           .padding(bottom = 8.dp)
                           .fillMaxWidth(),
                       fontSize = 10.sp,
                       fontWeight = FontWeight.ExtraBold,
                       fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                   )
                   OutlinedTextField(
                       value = uiState.currentPasswordValue ?: "",
                       onValueChange = onPasswordValueChanged,
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(bottom = 3.dp)/*,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    )*/, textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.axiformaregular))),
                       colors = TextFieldDefaults.textFieldColors(
                           focusedLabelColor = Color.Black,
                           focusedIndicatorColor = Color.Black,
                           leadingIconColor = Color.Black,
                           backgroundColor = Color(0xFFF5F5F5)
                       )
                   )
                   Text(
                       text = "forgot password ?",
                       textAlign = TextAlign.End,
                       modifier = Modifier
                           .padding(bottom = 8.dp)
                           .fillMaxWidth()
                           .clickable { onForgotPasswordClicked() },
                       fontSize = 9.sp,
                       fontWeight = FontWeight.SemiBold,
                       color = Color(0xFF828282)
                   )

               }

               Button(
                   onClick = { onSubmitButtonClicked() },
                   modifier = Modifier
                       .padding(top = 87.dp)
                       .sizeIn(minHeight = 48.dp, maxHeight = 48.dp)
                       .fillMaxWidth(0.7f)
                       .clip(RoundedCornerShape(50)),
                   colors = ButtonDefaults.buttonColors(Color.Black),
               ) {
                   Icon(
                       painter = painterResource(id = R.drawable.registericon),
                       contentDescription = "register icon",
                       modifier = Modifier
                           .size(16.dp),
                       tint = Color.White

                   )
                   Spacer(modifier = Modifier.size(24.dp))
                   Text(
                       text = "LOGIN",
                       fontWeight = FontWeight.ExtraBold,
                       fontSize = 10.sp,
                       letterSpacing = (-1).sp,
                       color = Color.White,
                       fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                   )
               }
           }
           Row(
               modifier = Modifier
                   .align(Alignment.BottomCenter)
                   .padding(bottom = 25.dp)
                   .fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment = Alignment.CenterVertically,
           ) {
               Text(
                   text = "If you have no account,",
                   fontSize = 10.sp,
                   fontWeight = FontWeight.Bold,
                   fontFamily = FontFamily(Font(R.font.axiformabold))
               )
               Button(
                   onClick = { onRegisterButtonClicked() },
                   modifier = Modifier
                       .width(101.dp)
                       .height(24.dp)
//                    .sizeIn(minHeight = 32.dp, maxHeight = 48.dp)
                       .clip(RoundedCornerShape(90)),
                   colors = ButtonDefaults.buttonColors(Color.Black),
                   shape = RoundedCornerShape(90)
               ) {
                   Text(
                       text = "REGISTER",
                       fontWeight = FontWeight.ExtraBold,
                       color = Color.White,
                       fontSize = 10.sp,
                       fontFamily = FontFamily(Font(R.font.axiformablack))
                   )

               }
           }

       }
    }

    if (uiState.errorMessages.isNotEmpty()) {
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
fun EnterPhonePreview() {
    DropyTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            EnterPhoneNumberContent(
                uiState = AuthenticationUiState(),
                onPhoneNumberValueChanged = {/*TODO*/ },
                onPasswordValueChanged = {/*TODO*/ },
                onSubmitButtonClicked = {/*TODO*/ },
                onForgotPasswordClicked = {/*TODO*/ },
                onRegisterButtonClicked = {/*TODO*/ },
                onErrorDismissed = {/*TODO*/ }
            )
        }
    }
}