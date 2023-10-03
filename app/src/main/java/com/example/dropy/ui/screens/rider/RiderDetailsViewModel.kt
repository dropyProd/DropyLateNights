package com.example.dropy.ui.screens.rider

import androidx.lifecycle.ViewModel
import com.example.dropy.ui.screens.apphome.AppHomePageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class RiderDetailsUiState(
    val riderName: String = "",
    val bikeModel: String = "",
    val totalRides: String = "",
    val placeName: String = "",
    val plateNumber: String = "",
    val dialogState: Boolean = false
)


class RiderDetailsViewModel : ViewModel() {

    val uiState = MutableStateFlow(RiderDetailsUiState())

    val riderDetailsUiState: StateFlow<RiderDetailsUiState> = uiState.asStateFlow()


    fun setRiderValues(
        riderName: String,
        placeName: String,
        plateNumber: String = "",
        totalRides: String = "",
        bikeModel: String = ""
    ) {

        uiState.update { state ->
            state.copy(
                riderName = riderName,
                placeName = placeName,
                plateNumber = plateNumber,
                totalRides = totalRides,
                bikeModel = bikeModel
            )
        }
    }

    fun changeRiderDialogState(state: Boolean){

        uiState.update {
            it.copy(
                dialogState = state
            )
        }

    }

}