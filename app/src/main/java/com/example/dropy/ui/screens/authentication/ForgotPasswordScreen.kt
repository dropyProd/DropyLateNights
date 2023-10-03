package com.example.dropy.ui.screens.authentication

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
import com.example.dropy.R
import com.example.dropy.ui.components.authentication.EnterPhoneNumberContent

@Composable
fun ForgotPasswordScreen(authenticationViewModel: AuthenticationViewModel) {
    authenticationViewModel.appViewModel?.setSystemUiControllerColor(Color(248, 242, 212))

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
                ForgotPasswordScreenContent(
                    onPhoneNumberValueChanged = authenticationViewModel::onPhoneNumberChanged,
                    uiState = uiState,
                    onSubmitButtonClicked = authenticationViewModel::forgotPassword
                )
            }
        }
    }

}