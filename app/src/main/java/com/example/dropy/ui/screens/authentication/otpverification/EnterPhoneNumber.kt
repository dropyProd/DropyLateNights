package com.example.dropy.ui.screens.authentication.otpverification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dropy.R
import com.example.dropy.ui.components.authentication.EnterPhoneNumberContent
import com.example.dropy.ui.screens.authentication.AuthenticationViewModel
import com.example.dropy.ui.screens.loginAs.LoginAsDialog
import com.example.dropy.ui.screens.loginAs.LoginAsDialogViewModel
import com.example.dropy.ui.theme.DropyTheme


@Composable
fun EnterPhoneNumber(
    authenticationViewModel: AuthenticationViewModel,
    loginAsDialogViewModel: LoginAsDialogViewModel
) {
    authenticationViewModel.appViewModel?.setSystemUiControllerColor(Color(248, 242, 212))
    val loginAsDialogUiState by loginAsDialogViewModel.loginAsDialogUiState.collectAsState()

    val uiState by authenticationViewModel.authenticationUiState.collectAsState()
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
                EnterPhoneNumberContent(
                    uiState = uiState,
                    onPhoneNumberValueChanged = authenticationViewModel::onPhoneNumberChanged,
                    onPasswordValueChanged = authenticationViewModel::onPasswordChanged,
                    onSubmitButtonClicked = {
                        // authent
                        // icationViewModel.onGetOtp()
                        authenticationViewModel.apiLogin(loginAsDialogViewModel = loginAsDialogViewModel)
                    },
                    onRegisterButtonClicked = authenticationViewModel::goToRegister,
                    onErrorDismissed = authenticationViewModel::onErrorDismissed,
                    onForgotPasswordClicked = authenticationViewModel::goToForgotPssword
                )
                LoginAsDialog(
                    onDismissRequest = { loginAsDialogViewModel.changeDialogState(false) },
                    navigate = loginAsDialogViewModel::navigate,
                    show = loginAsDialogUiState.showDialog
                )

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun EnterPhoneNumberPreview() {
    DropyTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            EnterPhoneNumber(viewModel(), viewModel())
        }
    }
}