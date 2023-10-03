package com.example.dropy.ui.components.commons.payments.paymentpage

import com.example.dropy.ui.components.commons.payments.paymentpage.chargesheet.ChargeDataClass
import com.example.dropy.ui.components.commons.payments.paymentpage.paymentmethod.PaymentMethodDataClass

data class PaymentPageUiState(
    val selectedPaymentMethod: PaymentMethodDataClass? = null,
    val charges:List<ChargeDataClass> = emptyList(),
    val walletBalance:Int = 0,
    val totalCharge:Int = 0

)
