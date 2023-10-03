package com.example.dropy.ui.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.dropy.R
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.screens.authentication.AuthenticationViewModel
import com.example.dropy.ui.screens.loginAs.LoginAsDialog
import com.example.dropy.ui.screens.loginAs.LoginAsDialogViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController? = null,
    authenticationViewModel: AuthenticationViewModel,
    loginAsDialogViewModel: LoginAsDialogViewModel
) {

    val scale = remember {
        Animatable(0f)
    }

    val loginAsDialogUiState by loginAsDialogViewModel.loginAsDialogUiState.collectAsState()

    // Animation
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            // tween Animation
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        // Customize the delay time
        delay(3000L)
        authenticationViewModel.getLoginInfo(loginAsDialogViewModel)

    }

    val context = LocalContext.current
    LaunchedEffect(key1 = true, block = {
        authenticationViewModel.getLocale(context = context)
    })
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = R.drawable.fulllogoremake),
            modifier = Modifier
                .fillMaxWidth()
                .height(227.dp)
                .scale(scale.value),
            contentDescription = ""
        )
    }

    LoginAsDialog(
        onDismissRequest = { loginAsDialogViewModel.changeDialogState(false) },
        navigate = loginAsDialogViewModel::navigate,
        show = loginAsDialogUiState.showDialog
    )

}