package com.example.dropy.ui.screens.waterTransactionComplete

import androidx.lifecycle.ViewModel
import com.example.dropy.di.DropyApp
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.waterOrderPay.WaterOrderPayUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class WaterTransactionCompleteUiState(
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class WaterTransactionCompleteViewModel @Inject constructor(
    app: DropyApp
): ViewModel() {
    val uiState = MutableStateFlow(WaterTransactionCompleteUiState())

    val waterTransactionCompleteUiState: StateFlow<WaterTransactionCompleteUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    fun navigateTrackOrder() {
        appViewModel?.navigate(AppDestinations.WATER_MY_ORDER)
    }


}