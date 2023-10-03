package com.example.dropymain.presentation.components.parcels.frontside.payments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.dropy.R
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue

@Composable
fun PaymentCompleteContent(){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        StyledText(
            textColor = Color.Black,
            backgroundColor = DropyYellow,
            borderColor = Color.Transparent,
            textSize = 15,
            text = "transaction complete",
            isUppercase = true,
            isBold =true
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SimpleText(
                textSize = 26,
                text = "thank you",
                textColor = Color.Black,
                isUppercase = true,
                isBold = false,
                isExtraBold = true,
                padding = 16
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                ,
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.rides),
                    contentDescription = "parcels icon",
                    modifier = Modifier
                        .fillMaxSize()
                    ,
                    contentScale = ContentScale.FillWidth
                )
            }
            TotallyRoundedButton(
                icon = null,
                buttonText = "track",
                textFontSize = 15,
                textColor = Color.White,
                backgroundColor = LightBlue,
                widthFraction = 0.5,
                action = {}
            )
            TotallyRoundedButton(
                icon = null,
                buttonText = "receipt",
                textFontSize = 15,
                textColor = Color.White,
                backgroundColor = LightBlue,
                widthFraction = 0.5,
                action = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentCompleteContentPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        PaymentCompleteContent()
    }
}