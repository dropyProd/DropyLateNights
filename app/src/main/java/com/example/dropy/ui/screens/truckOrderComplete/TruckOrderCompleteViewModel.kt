package com.example.dropy.ui.screens.truckOrderComplete

import androidx.lifecycle.ViewModel
import com.example.dropy.di.DropyApp
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.truckRouteWaterPoint.TruckRouteWaterPointUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class TruckOrderCompleteUiState(
    val isCustomerRoute: Boolean = false,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class TruckOrderCompleteViewModel @Inject constructor(
    app: DropyApp
): ViewModel() {
    val uiState = MutableStateFlow(TruckOrderCompleteUiState())

    val truckOrderCompleteUiState: StateFlow<TruckOrderCompleteUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    fun navigateFindMoreWork() {
        appViewModel?.navigate(AppDestinations.TRUCK_FIND_JOB)
    }


}