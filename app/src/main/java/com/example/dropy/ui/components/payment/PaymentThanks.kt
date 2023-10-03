package com.example.dropy.ui.components.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.screens.dropymainmodel.dropyMainHeader
import com.example.dropy.R
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.example.dropy.ui.theme.LightBlue

@Composable
fun PaymentThanks(navController: NavController? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        dropyMainHeader()
        BackgroundedText(
            background = Color(0xFFFCD313),
            textColor = Color.Black,
            text = "TRANSACTION COMPLETED",
            vertical = 2
        )

        Image(
            painter = painterResource(id = R.drawable.bicycle),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(348.dp),
            contentScale = ContentScale.Crop
        )

        BackgroundedText(
            background = LightBlue,
            textColor = Color.White,
            text = "RECEIPT",
            vertical = 8
        )

        BackgroundedText(
            background = LightBlue,
            textColor = Color.White,
            text = "TRACK PARCEL",
            vertical = 8,
            modifier = Modifier.clickable {
                navController?.navigate(ParcelDestination.PARCEL_LOCATION_EXPRESS_RIDER){
                    navOptions { // Use the Kotlin DSL for building NavOptions
                        anim {
                            enter = android.R.animator.fade_in
                            exit = android.R.animator.fade_out
                        }
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun sccreen() {
    PaymentThanks()
}