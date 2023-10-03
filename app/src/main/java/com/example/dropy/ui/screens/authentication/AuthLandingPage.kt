package com.example.dropy.ui.screens.authentication

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.authentication.AuthLandingPageContent
import com.example.dropy.ui.screens.servicesDialog.ServicesDialog
import com.example.dropy.ui.theme.DropyTheme

@Composable
fun AuthLandingPage(
    authenticationViewModel: AuthenticationViewModel,
    appViewModel: AppViewModel
){
    authenticationViewModel.appViewModel?.setSystemUiControllerColor(Color(248, 242, 212))
    val uiState by authenticationViewModel.authenticationUiState.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AuthLandingPageContent(
            onCreateAccountClicked = {                     authenticationViewModel.goToRegister()},
            onLoginClicked = {
                authenticationViewModel.setLoggedInState(false)
                authenticationViewModel.goToLogIn() }
        )

        if (uiState.dialogShow){
            ServicesDialog(
                onDismissDialog = authenticationViewModel::onDialogShow,
                onClicked = {
                    appViewModel.setUserType(it)
                }
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun AuthLandingPreview() {
    DropyTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
//            AuthLandingPage(
//                authenticationViewModel = AuthenticationViewModel()
//            )
        }
    }
}