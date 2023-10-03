package com.example.dropy.ui.screens.shops.backside.createshop.addshoplocation

import com.example.dropy.ui.components.commons.maps.AddressDataClass

data class AddShopLocationUiState(
    val shopAddress: com.example.dropy.ui.components.commons.AddressDataClass? = null,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

