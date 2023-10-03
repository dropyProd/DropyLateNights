package com.example.dropy.ui.components.shops.frontside.customer.customerdashboard.orderhistory.completeorder

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun ScanQRContent(){
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Scan QR",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "To Complete Order",
                fontSize = 21.sp,
            )
        }
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.7f)
                .aspectRatio(1f)
        ) {
            Image(
                imageVector = Icons.Filled.QrCode,
                contentDescription = "QR code",
                modifier = Modifier
                    .fillMaxSize()
                ,
                contentScale = ContentScale.Fit
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(0.7f)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Let the DELIVERY PERSON scan this code" +
                        "to complete the order you can" +
                        "also give the SMS DELIVERY CODE" +
                        "to complete the order",
                textAlign = TextAlign.Center
            )
            Text(
                text = "thank you".uppercase(),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 21.sp,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ScanQRContentPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        ScanQRContent()
    }
}