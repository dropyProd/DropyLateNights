package com.example.dropy.ui.components.commons.maps.selectlocation

import com.example.dropy.ui.components.commons.maps.AddressDataClass
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

data class SelectLocationUiState(
    val selectedAddressName:String = "ssx",
    val address:AddressDataClass? = null,
    val cameraPosition: LatLng? = null,
    val markerPosition: LatLng? = null
)
