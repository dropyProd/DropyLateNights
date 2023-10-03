package com.example.dropy.ui.screens.shops.frontside.checkout.scanQr

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dropy.R
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.screens.dropymainmodel.dropyMainHeader
import com.example.dropy.ui.screens.shops.frontside.checkout.trackyourorder.TrackYourOrderUiState

@Composable
fun ScanQrShop(navController: NavController? = null, trackYourOrderUiState: TrackYourOrderUiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {

    /*    dropyMainHeader()*/
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(start = 12.dp, end = 7.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(50.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                Text(
                    text = "SCAN QR",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.axiformaheavy))
                    )
                )

                Text(
                    text = "To Complete Order",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily(Font(R.font.axiformabook))
                    )
                )
            }

            val cont = LocalContext.current


            IconButton(
                onClick = {
                    navController?.navigate(ShopsFrontDestination.ORDER_COMPLETE)
                }, modifier = Modifier
                    .fillMaxWidth()
                    .height(276.dp)
            ) {
                trackYourOrderUiState.qr_bitmap?.asImageBitmap()?.let {
                    Image(
                        bitmap = it,
                        contentDescription = "QR code",
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Text(
                text = "Let the DELIVERY PERSON scan this code to complete the order. You can also give the SMS DELIVERY CODE to complete the order.",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily(Font(R.font.axiformamedium))
                ),
                modifier = Modifier.width(206.dp)
            )

            Text(
                text = "THANK YOU",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(Font(R.font.axiformaheavy))
                )
            )

        }
    }
}

@Preview
@Composable
fun demo() {
    ScanQrShop(trackYourOrderUiState = TrackYourOrderUiState())
}