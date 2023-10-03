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
import com.example.dropy.R
import com.example.dropy.ui.components.authentication.EnterOtpContent
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.authentication.AuthenticationViewModel
import com.example.dropy.ui.screens.nearestWaterPoint.NearestWaterPointViewModel
import com.example.dropy.ui.screens.scanQRWater.ScanQRWaterViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.truckIncomingWork.TruckIncomingWorkViewModel
import com.example.dropy.ui.screens.truckStartTrip.TruckStartTripViewModel
import com.example.dropy.ui.screens.water.waterHome.WaterHomeContent
import com.example.dropy.ui.theme.DropyTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun EnterOtp(
    cartPageViewModel: CartPageViewModel,
    authenticationViewModel: AuthenticationViewModel,
    scanQRWaterViewModel: ScanQRWaterViewModel,
    nearestWaterPointViewModel: NearestWaterPointViewModel,
    truckStartTripViewModel: TruckStartTripViewModel,
    truckIncomingWorkViewModel: TruckIncomingWorkViewModel
) {

    val uiState by authenticationViewModel.authenticationUiState.collectAsState()
    val scanQRWaterUiState by scanQRWaterViewModel.scanQRWaterUiState.collectAsState()
    val nearestWaterPointUiState by nearestWaterPointViewModel.nearestWaterPointUiState.collectAsState()
    val truckIncomingWorkUiState by truckIncomingWorkViewModel.truckIncomingWorkUiState.collectAsState()
    val appUiState = authenticationViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    if (!uiState.isLoggedIn) {
        val systemUiController = rememberSystemUiController()
        systemUiController.setStatusBarColor(Color(248, 242, 212))
    }

    if (uiState.isLoggedIn) {
        AppScaffold(
            content = {
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
                    EnterOtpContent(
                        uiState = uiState,
                        onOtpValueChanged = { authenticationViewModel.onOtpValueChanged(it) },
                        onVerifyButtonClicked = {
                            authenticationViewModel.checkIfCodeMatches(
                                scanQRWaterUiState, scanQRWaterViewModel = scanQRWaterViewModel,
                                truckStartTripViewModel = truckStartTripViewModel,
                                nearestWaterPointUiState = nearestWaterPointUiState,
                                truckIncomingWorkUiState = truckIncomingWorkUiState
                            )
                        },
                        onResendOtpButtonClicked = { /*authenticationViewModel.onResendOtpButtonClicked()*/ },
                        onErrorDismissed = { authenticationViewModel.onErrorDismissed(it) }
                    )
                }
            },
            pageLoading = uiState.isLoading,
            actionLoading = uiState.isLoading,
            errorList = listOf(),
            messageList = listOf(),
            onBackButtonClicked = { authenticationViewModel.appViewModel?.onBackButtonClicked() },
            onDashboardButtonClicked = { authenticationViewModel.appViewModel?.onDashboardButtonClicked() },
            onCartButtonClicked = { authenticationViewModel.appViewModel?.onCartButtonClicked() },
            navigateTo = { authenticationViewModel.appViewModel?.navigate(it) },
            drawerMenuItems = appUiState.value.drawerMenuItems,
            userProfiles = appUiState.value.userProfiles,
            onSelectProfile = { authenticationViewModel.appViewModel?.onSelectProfile(it) },
            activeProfile = appUiState.value.activeProfile,
            cartsize = cartUiState.value.orderList.size,
            showLogo = false,
            showImageRight = true,
            showCart = false
        )
    } else {
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
                    EnterOtpContent(
                        uiState = uiState,
                        onOtpValueChanged = { authenticationViewModel.onOtpValueChanged(it) },
                        onVerifyButtonClicked = { authenticationViewModel.onVerifyOtp() },
                        onResendOtpButtonClicked = { authenticationViewModel.onResendOtpButtonClicked() },
                        onErrorDismissed = { authenticationViewModel.onErrorDismissed(it) }
                    )
                }
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun EnterOtpPreview() {
    DropyTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            //  EnterOtp(authenticationViewModel = AuthenticationViewModel(app: Droopy))
        }
    }
}