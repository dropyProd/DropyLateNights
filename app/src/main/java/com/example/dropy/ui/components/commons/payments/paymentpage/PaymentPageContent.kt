package com.example.dropy.ui.components.commons.payments.paymentpage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.components.commons.payments.paymentpage.chargesheet.ChargeDataClass
import com.example.dropy.ui.components.commons.payments.paymentpage.chargesheet.ChargeSheet
import com.example.dropy.ui.components.commons.payments.paymentpage.paymentmethod.ChoosePaymentMethod
import com.example.dropy.ui.components.commons.payments.paymentpage.paymentmethod.PaymentMethodDataClass
import com.example.dropy.ui.theme.DropyYellow

@Composable
fun PaymentPageContent(
    uiState: PaymentPageUiState,
    extraSection: (@Composable ()->Unit)? = null,
    selectPaymentMethod: (PaymentMethodDataClass)->Unit,
    onPayClicked:()->Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth()){
            StyledText(
                textColor = Color.Black,
                backgroundColor = DropyYellow,
                borderColor = Color.Transparent,
                textSize = 12,
                text = "complete payment",
                isUppercase = true,
                isBold = true
            )
        }
        ChoosePaymentMethod(
            selectPaymentMethod = {selectPaymentMethod(it)} ,
            walletBalance = uiState.walletBalance
        )
        if (extraSection != null){
            extraSection()
        }
        ChargeSheet(
            paymentMethod = null,
            charges = uiState.charges,
            totalCharge = uiState.totalCharge
        )
        TotallyRoundedButton(
            buttonText = "pay",
            backgroundColor = DropyYellow,
            action = {onPayClicked()},
            widthFraction = 0.6
        )

    }
}

@Preview(showBackground = true)
@Composable
fun PaymentPagePreview(){
    Column(Modifier.fillMaxSize()) {
        PaymentPageContent(
            uiState = PaymentPageUiState(
                charges = listOf(ChargeDataClass("charge name",123)),
                totalCharge = 1234,
                walletBalance = 2345
            ),
            onPayClicked = {},
            selectPaymentMethod = {}
        )
    }
}