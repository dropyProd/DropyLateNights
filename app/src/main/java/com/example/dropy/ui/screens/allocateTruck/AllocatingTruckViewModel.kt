package com.example.dropy.ui.screens.allocateTruck

import androidx.lifecycle.ViewModel
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.getTruckDrivers.GetTruckDriversResItem
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.waterOrderPay.WaterOrderPayUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


data class AllocatingTruckUiState(
    val truckDriverList: List<GetTruckDriversResItem> = listOf(),
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class AllocatingTruckViewModel @Inject constructor(
   private val app: DropyApp
): ViewModel() {
    val uiState = MutableStateFlow(AllocatingTruckUiState())

    val allocatingTruckUiState: StateFlow<AllocatingTruckUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    fun navigateWaterThankYou(){
        appViewModel?.navigate(AppDestinations.WATER_TRANSACTION_COMPLETE)
    }

    fun getTruckDrivers(){
        uiState.update {
            it.copy(truckDriverList = app.waterTruckDrivers)
        }
    }

}