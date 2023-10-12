package com.example.dropy.ui.components.authentication

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.screens.authentication.AuthenticationUiState
import com.example.dropy.ui.theme.DropyTheme


@Composable
fun PhoneAuthRegisterContent(
    uiState: AuthenticationUiState,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onPhoneNumberChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
    onRegisterButtonClicked: () -> Unit,
    onLogInButtonClicked: () -> Unit,
    onErrorDismissed: (String) -> Unit
) {
    Column(

        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
            .padding(start = 24.dp, end = 24.dp)
            .verticalScroll(rememberScrollState())
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
        Box(modifier = Modifier.fillMaxSize().background(Color.White)){
            Column(
                modifier = Modifier
                    .padding(top = 48.dp)
//                .weight(1f)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 4.dp, end = 4.dp),
                    ) {
                        Text(
                            text = "First Name",
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                                .wrapContentWidth(),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                        )
                        OutlinedTextField(
                            value = uiState.currentFirstNameValue ?: "",
                            onValueChange = { onFirstNameChanged(it) },
                            modifier = Modifier
                                .width(170.dp),
                            textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.axiformaregular))),
                            colors = TextFieldDefaults.textFieldColors(
                                focusedLabelColor = Color.Black,
                                focusedIndicatorColor = Color.Black,
                                leadingIconColor = Color.Black,
                                backgroundColor = Color.White
                            )
                        )

                    }
                    Column(
                        modifier = Modifier
                            .padding(start = 4.dp, end = 4.dp),
                    ) {
                        Text(
                            text = "Last Name",
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                                .fillMaxWidth(),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                        )
                        OutlinedTextField(
                            value = uiState.currentLastNameValue ?: "",
                            onValueChange = { onLastNameChanged(it) },
                            modifier = Modifier
                                .fillMaxWidth(),
                            textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.axiformaregular))),
                            colors = TextFieldDefaults.textFieldColors(
                                focusedLabelColor = Color.Black,
                                focusedIndicatorColor = Color.Black,
                                leadingIconColor = Color.Black,
                                backgroundColor = Color.White
                            )
                        )
                    }
                }
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                Column(
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(4.dp)
//                    ,
//                ) {
//                    Text(
//                        text = "ID Number",
//                        textAlign = TextAlign.Start,
//                        modifier = Modifier
//                            .padding(bottom = 8.dp)
//                            .fillMaxWidth()
//                        ,
//                        fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold
//                    )
//                    OutlinedTextField(
//                        value = "",
//                        onValueChange = {},
//                        modifier = Modifier
//                            .fillMaxWidth()
//                        ,
//                    )
//
//                }
//                Column(
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(4.dp)
//                    ,
//                ) {
//                    Text(
//                        text = "Phone Number",
//                        textAlign = TextAlign.Start,
//                        modifier = Modifier
//                            .padding(bottom = 8.dp)
//                            .fillMaxWidth()
//                        ,
//                        fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold
//                    )
//                    OutlinedTextField(
//                        value = "",
//                        onValueChange = {},
//                        modifier = Modifier
//                            .fillMaxWidth()
//                        ,
//                    )
//                }
//            }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp, end = 4.dp, top = 29.dp),
                ) {
                    Text(
                        text = "Phone number",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth(),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                    )
                    OutlinedTextField(
                        value = uiState.currentPhoneNumberValue,
                        onValueChange = { onPhoneNumberChanged(it) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone
                        ),
                        textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.axiformaregular))),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedLabelColor = Color.Black,
                            focusedIndicatorColor = Color.Black,
                            leadingIconColor = Color.Black,
                            backgroundColor = Color(0xFFF5F5F5)
                        )
                    )

                }
                /*       Column(
                           modifier = Modifier
                               .fillMaxWidth()
                               .padding(4.dp),
                       ) {
                           Text(
                               text = "Email",
                               textAlign = TextAlign.Start,
                               modifier = Modifier
                                   .padding(bottom = 8.dp)
                                   .fillMaxWidth(),
                               fontSize = 10.sp,
                               fontWeight = FontWeight.ExtraBold
                           )
                           OutlinedTextField(
                               value = uiState.currentEmailValue ?: "",
                               onValueChange = { onEmailChanged(it) },
                               modifier = Modifier
                                   .fillMaxWidth(),
                               keyboardOptions = KeyboardOptions(
                                   keyboardType = KeyboardType.Email
                               )
                           )

                       }*/
//no email on the  new dsigns provides

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp, end = 4.dp, top = 34.dp),
                ) {
                    Text(
                        text = "Password",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth(),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                    )
                    var passwordVisible by remember { mutableStateOf(false) }


                    OutlinedTextField(
                        value = uiState.currentPasswordValue ?: "",
                        onValueChange = { onPasswordChanged(it) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val image = if (passwordVisible)
                                Icons.Filled.Visibility
                            else Icons.Filled.VisibilityOff

                            // Please provide localized description for accessibility services
                            val description = if (passwordVisible) "Hide password" else "Show password"

                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(imageVector = image, description)
                            }
                        },
                        textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.axiformaregular))),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedLabelColor = Color.Black,
                            focusedIndicatorColor = Color.Black,
                            leadingIconColor = Color.Black,
                            backgroundColor = Color.White
                        )
                    )

                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp, end = 4.dp, top = 34.dp),
                ) {
                    Text(
                        text = "Confirm Password",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth(),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                    )
                    var passwordVisible by remember { mutableStateOf(false) }


                    OutlinedTextField(
                        value = uiState.confirmPasswordValue ?: "",
                        onValueChange = { onConfirmPasswordChanged(it) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val image = if (passwordVisible)
                                Icons.Filled.Visibility
                            else Icons.Filled.VisibilityOff

                            // Please provide localized description for accessibility services
                            val description = if (passwordVisible) "Hide password" else "Show password"

                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(imageVector = image, description)
                            }
                        },
                        textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.axiformaregular))),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedLabelColor = Color.Black,
                            focusedIndicatorColor = Color.Black,
                            leadingIconColor = Color.Black,
                            backgroundColor = Color(0xFFF5F5F5)
                        )
                    )

                }
                Button(
                    onClick = { onRegisterButtonClicked() },
                    modifier = Modifier
                        .padding(top = 56.dp)
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
                        text = "REGISTER",
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
                    .padding(bottom = 15.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Do you have an account?",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.axiformasemibold))
                )
                Button(
                    onClick = { onLogInButtonClicked() },
                    modifier = Modifier
                        .width(101.dp)
                        .height(24.dp)
                        .clip(RoundedCornerShape(90)),
                    shape = RoundedCornerShape(90),
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    Text(
                        text = "LOG IN",
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        fontSize = 10.sp,
                        fontFamily = FontFamily(Font(R.font.axiformabold))
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
fun PhoneRegisterPreview() {
    DropyTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            PhoneAuthRegisterContent(
                uiState = AuthenticationUiState(),
                onFirstNameChanged = { /*TODO*/ },
                onLastNameChanged = { /*TODO*/ },
                onPhoneNumberChanged = { /*TODO*/ },
                onEmailChanged = { /*TODO*/ },
                onPasswordChanged = { /*TODO*/ },
                onRegisterButtonClicked = { /*TODO*/ },
                onLogInButtonClicked = { /*TODO*/ },
                onErrorDismissed = { /*TODO*/ },
                onConfirmPasswordChanged = {}
            )
        }
    }
}