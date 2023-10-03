package com.example.dropy.ui.components.commons.maps.selectpickupanddestinationlocations

import androidx.lifecycle.ViewModel
import com.example.dropy.ui.components.commons.maps.AddressDataClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SelectPickupAndDestinationViewModel:ViewModel(){
    val uiState = MutableStateFlow(SelectPickupAndDestinationUiState())
    val selectPickupAndDestinationUiState:StateFlow<SelectPickupAndDestinationUiState> = uiState.asStateFlow()


    fun setPickupLocation(address: AddressDataClass){
        uiState.update {
            it.copy(
                pickupLocation = address
            )
        }
    }
    fun setDestinationLocation(address: AddressDataClass){
        uiState.update {
            it.copy(
                destinationLocation = address
            )
        }
    }
}
