package com.example.dropy.ui.screens.qr

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.dropy.R
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.screens.dropymainmodel.dropyMainHeader
import com.example.dropy.ui.screens.shops.frontside.checkout.trackyourorder.TrackYourOrderViewModel

@Composable
fun ScanQr(
    navController: NavController? = null,
    changeType: () -> Unit,
    trackYourOrderViewModel: TrackYourOrderViewModel
) {


    val uistate by trackYourOrderViewModel.trackYourOrderUiStateUiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        BackgroundedText(
            background = Color.Transparent,
            textColor = Color.Transparent,
            text = "RIDER HAS ARRIVED",
            textSize = 10,
            vertical = 3,
            modifier = Modifier
                .padding(start = 14.dp)
                .align(Alignment.Start)
        )

        Text(
            text = "SCAN QR BELOW",
            style = TextStyle(
                color = Color.Black,
                fontSize = 21.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.axiformaheavy))
            )
        )


        uistate.qr_bitmap?.asImageBitmap()?.let {
            Image(
                bitmap = it,
                contentDescription = "QR code",
                modifier = Modifier
                    .width(295.dp)
                    .height(250.dp),
                contentScale = ContentScale.Fit
            )
        }

        Text(
            text = "Let the delivery person scan this code to activate tracking. You can also use the SMS TRACKING CODE to activate tracking.",
            style = TextStyle(
                color = Color.Black,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily(Font(R.font.axiformamedium))
            ),
            modifier = Modifier.width(206.dp)
        )

        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF584AFF),
                contentColor = Color.White,
            ),
            onClick = {
                changeType()
                navController?.navigate(/*ParcelDestination.PARCEL_TRACK*/ShopsFrontDestination.TRACK_YOUR_ORDER) {
                    navOptions { // Use the Kotlin DSL for building NavOptions
                        anim {
                            enter = android.R.animator.fade_in
                            exit = android.R.animator.fade_out
                        }
                    }
                }
            },
            modifier = Modifier
                .width(150.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text(
                text = "TRACK ORDER",
                style = TextStyle(fontWeight = FontWeight.ExtraBold, color = Color.White)
            )

        }


    }
}

@Preview
@Composable
fun demo() {
    ScanQr(changeType = {}, trackYourOrderViewModel = hiltViewModel())
}