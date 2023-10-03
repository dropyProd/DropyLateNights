package com.example.dropy.ui.components.commons.payments

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.screens.payments.mpesapaymentdialog.MpesaPaymentDialogUiState
import com.example.dropy.ui.theme.DropyYellow

@Composable
fun MpesaPaymentDialogContent(
    uiState: MpesaPaymentDialogUiState,
    onPhoneNumberChanged: (String) -> Unit,
    onAmountChanged: (Int) -> Unit,
    onPayClicked: (action: String) -> Unit,
) {
    Dialog(onDismissRequest = { onPayClicked("close") }) {
        if (uiState.transactionResultCode != null) {
            Column() {
                Icon(
                    imageVector = if (uiState.transactionResultCode == 0) {
                        Icons.Filled.CheckCircle
                    } else {
                        Icons.Filled.Cancel
                    },
                    contentDescription = "Success icon",
                    tint = if (uiState.transactionResultCode == 0) {
                        Color.Green
                    } else {
                        Color.Red
                    }
                )
                SimpleText(
                    text = if (uiState.transactionResultCode == 0) {
                        "Payment successful"
                    } else {
                        "Payment failed"
                    },
                    verticalPadding = 8,
                    textSize = 16,
                    isBold = true
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .background(color = Color.White, RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SimpleText(
                    text = "Pay using M-pesa",
                    textSize = 18,
                    isBold = true,
                    verticalPadding = 16
                )
               /* OutlinedTextField(
                    value = uiState.amount.toString(),
                    onValueChange = { onAmountChanged(it.toInt()) },
                    modifier = Modifier
                        .padding(8.dp),
                    singleLine = true,
                    label = {
                        Text(text = "Amount")
                    },
                    readOnly = uiState.amount != null,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )*/
                OutlinedTextField(
                    value = uiState.phoneNumber,
                    onValueChange = { onPhoneNumberChanged(it) },
                    modifier = Modifier
                        .padding(8.dp),
                    singleLine = true,
                    label = {
                        Text(text = "Phone number")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone
                    )
                )
                TotallyRoundedButton(
                    buttonText = "pay",
                    backgroundColor = DropyYellow,
                    action = { onPayClicked("pay") },
                    widthFraction = 0.6
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MpesaPaymentDialogPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        MpesaPaymentDialogContent(
            uiState = MpesaPaymentDialogUiState(),
            onPayClicked = {},
            onAmountChanged = {},
            onPhoneNumberChanged = {}
        )
    }
}
