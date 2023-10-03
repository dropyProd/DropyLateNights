package com.example.dropy.ui.screens.authentication.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.dropy.R
import com.example.dropy.ui.components.authentication.PhoneAuthRegisterContent
import com.example.dropy.ui.screens.authentication.AuthenticationViewModel
import com.example.dropy.ui.screens.loginAs.LoginAsDialog
import com.example.dropy.ui.screens.loginAs.LoginAsDialogViewModel

import com.example.dropy.ui.theme.DropyTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun PhoneAuthRegister(
    authenticationViewModel: AuthenticationViewModel,
    loginAsDialogViewModel: LoginAsDialogViewModel
) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color(248, 242, 212))

    val uiState by authenticationViewModel.authenticationUiState.collectAsState()
    val loginAsDialogUiState by loginAsDialogViewModel.loginAsDialogUiState.collectAsState()

    Scaffold() {
        if (uiState.isLoading) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.background(
                    Color.White
                )
            ) {
                CircularProgressIndicator()
            }
        } else {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "background",
                    )
                    PhoneAuthRegisterContent(
                        uiState = uiState,
                        onFirstNameChanged = { authenticationViewModel.onFirstNameChanged(it) },
                        onLastNameChanged = { authenticationViewModel.onLastNameChanged(it) },
                        onPhoneNumberChanged = { authenticationViewModel.onPhoneNumberChanged(it) },
                        onEmailChanged = { authenticationViewModel.onEmailChanged(it) },
                        onPasswordChanged = { authenticationViewModel.onPasswordChanged(it) },
                        onConfirmPasswordChanged = {
                            authenticationViewModel.onConfirmPasswordChanged(
                                it
                            )
                        },
                        onRegisterButtonClicked = {
                            authenticationViewModel.createNewUser(
                                "",
                                loginAsDialogViewModel = loginAsDialogViewModel
                            )
                            //authenticationViewModel.registerNewUser()
                        },
                        onLogInButtonClicked = { authenticationViewModel.goToLogIn() },
                        onErrorDismissed = { authenticationViewModel.onErrorDismissed(it) }
                    )
                    LoginAsDialog(
                        onDismissRequest = {
                            loginAsDialogViewModel.changeDialogState(
                                false
                            )
                        },
                        navigate = loginAsDialogViewModel::navigate,
                        show = loginAsDialogUiState.showDialog
                    )
                }
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun PhoneAuthRegisterPreview() {
    DropyTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            //  PhoneAuthRegister(authenticationViewModel = AuthenticationViewModel())
        }
    }
}

