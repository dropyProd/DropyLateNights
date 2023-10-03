package com.example.dropy.ui.screens.pin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.pin.PinItem
import com.example.dropy.ui.components.pin.PinItemRider
import com.example.dropy.ui.components.topup.TopInfo
import com.example.dropy.ui.components.topup.YellowInfo
import com.example.dropy.ui.screens.rider.IncomingJobViewModel

@Composable
fun PinScreenContent(
    navController: NavController,
    type: String = "password",
    incomingJobViewModel: IncomingJobViewModel,
    uiState: PinUiState,
    appViewModel: AppViewModel,
    value: String,
    onValueChange: (String) -> Unit,
    pinScreenViewModel: PinScreenViewModel
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.spacedBy(75.dp)
    ) {

        val title = if (type.equals("password")) {
            "CREATE PIN"
        } else "Set QR Pin"

        TopInfo(text = title)

        Column(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(75.dp)

        ) {

            val info = if (type.equals("password")) {
                "Avoid using numbers like bithdays"
            } else "Fill all values"

            YellowInfo(text = info)

            if (type.equals("password")) {
                PinItem(text = "CREATE YOUR 4 DIGIT PIN", value, onValueChange)
                PinItem(text = "RETYPE YOUR PIN AGAIN", value, onValueChange)
            } else {
                PinItemRider(text = "ENTER PIN", value, onValueChange)
            }

            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFFCD313),
                    contentColor = Color.Black,
                ),
                onClick = {
                    if (type.equals("password")) {
                        navController.navigate(AppDestinations.PAYMENT)
                    } else {
                        incomingJobViewModel.verifyQr(uiState.pin, appViewModel, context, pinScreenViewModel = pinScreenViewModel)
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "SUBMIT"
                )

            }
        }
    }
}