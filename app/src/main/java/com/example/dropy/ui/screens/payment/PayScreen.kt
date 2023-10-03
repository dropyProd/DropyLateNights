package com.example.dropy.ui.screens.payment

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.example.dropy.ui.components.payment.PaymentDetails
import com.example.dropy.ui.screens.dropymainmodel.dropyMainHeader

@Composable
fun PayScreen(navController: NavController? = null) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        dropyMainHeader(
            modifier = Modifier
                .zIndex(.3f)
        )
        PaymentDetails()

        Button(
            modifier = Modifier
                .width(133.dp)
                .height(36.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                contentColor = Color.White,
            ),
            onClick = {
                 navController?.navigate(ParcelDestination.PARCEL_DROPY_PAY){
                     navOptions { // Use the Kotlin DSL for building NavOptions
                         anim {
                             enter = R.animator.fade_in
                             exit = R.animator.fade_out
                         }
                     }
                 }
            },
        ) {
            Text(
                text = "PAY",
                style = TextStyle(
                    color = Color.White
                )
            )





        }

    }
}

@Preview
@Composable
fun demo() {
    PayScreen()
}