package com.example.dropy.ui.screens.water.waterHome

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.dropy.di.DropyApp
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.apphome.AppHomePageUiState
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class WaterUiState(
    val selectedType: String = "",
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class WaterHomeViewModel @Inject constructor(
    app: DropyApp
) : ViewModel() {

    val uiState = MutableStateFlow(WaterUiState())

    val waterHomeUiState: StateFlow<WaterUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    fun navigateBoreHoleWater() {
        //if (waterHomeUiState.value.selectedType.equals("\nCLEAN WATER") && waterHomeUiState.value.selectedType.equals("\nTREATED WATER"))
        appViewModel?.navigate(AppDestinations.TANKER_BOREHOLE)
//        appViewModel?.navigate(AppDestinations.TRUCK_FIND_JOB)
    }

    fun setSelectedType(type: String) {
        uiState.update {
            it.copy(selectedType = type)
        }
        if (uiState.value.selectedType.equals("CLEAN WATER") || uiState.value.selectedType.equals(
                "TREATED WATER"
            )
        )
            navigateBoreHoleWater()
    }



}