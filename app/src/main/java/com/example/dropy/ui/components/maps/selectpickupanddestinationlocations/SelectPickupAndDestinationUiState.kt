package com.example.dropy.ui.components.commons.maps.selectpickupanddestinationlocations

import com.example.dropy.ui.components.commons.maps.AddressDataClass

data class SelectPickupAndDestinationUiState(
    val pickupLocation:AddressDataClass? = null,
    val destinationLocation:AddressDataClass? = null,
)
