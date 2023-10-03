package com.example.dropy.ui.screens.pin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.pin.PinItem
import com.example.dropy.ui.components.pin.PinItemRider
import com.example.dropy.ui.components.topup.AmountItem
import com.example.dropy.ui.components.topup.HeaderAmountItem
import com.example.dropy.ui.components.topup.TopInfo
import com.example.dropy.ui.components.topup.YellowInfo
import com.example.dropy.ui.screens.rider.IncomingJobViewModel

@Composable
fun PinScreen(
    navController: NavController,
    type: String = "password",
    incomingJobViewModel: IncomingJobViewModel,
    pinScreenViewModel: PinScreenViewModel,
    appViewModel: AppViewModel
) {

    val uiState by pinScreenViewModel.pinUiState.collectAsState()
    val appUiState = appViewModel.appUiState.collectAsState()

    AppScaffold(
        content = {
            PinScreenContent(
                navController = navController,
                incomingJobViewModel = incomingJobViewModel,
                uiState = uiState,
                appViewModel = appViewModel,
                type = type,
                value = uiState.pin,
                onValueChange = pinScreenViewModel::onPinChange,
                pinScreenViewModel = pinScreenViewModel
            )
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { appViewModel?.onCartButtonClicked() },
        navigateTo = { appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        showimg = true,
        showCart = false
    )

}