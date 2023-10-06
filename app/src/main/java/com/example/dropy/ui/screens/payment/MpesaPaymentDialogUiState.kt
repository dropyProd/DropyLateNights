package com.example.dropy.ui.screens.payments.mpesapaymentdialog

data class MpesaPaymentDialogUiState(
    val amount:Int? = 254,
    val phoneNumber:String = "",
    val show:Boolean = false,
    val pageLoading:Boolean = false,
    val number:Int = 1,
//    if transaction successful transactionCode = 0 else = 1 else if no result yet = null
    val transactionResultCode:Int? = null,
    val errorMessages:List<String> = emptyList(),
)
