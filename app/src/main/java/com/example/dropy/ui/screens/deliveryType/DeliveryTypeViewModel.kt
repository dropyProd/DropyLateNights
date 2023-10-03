package com.example.dropy.ui.screens.deliveryType

import androidx.lifecycle.ViewModel
import com.example.dropy.di.DropyApp
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.water.waterHome.WaterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class DeliveryTypeUiState(
    val deliveryType: String = "",
    val showDialog: Boolean = false,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class DeliveryTypeViewModel @Inject constructor(
    app: DropyApp
): ViewModel() {


    val uiState = MutableStateFlow(DeliveryTypeUiState())

    val deliveryTypeUiState: StateFlow<DeliveryTypeUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    fun navigateWaterOrderDetails(){
        appViewModel?.navigate(AppDestinations.WATER_ORDER_DETAILS)
    }


    fun setDeliveryType(text : String){
        uiState.update {
            it.copy(
                deliveryType = text
            )
        }
    }

    fun changeDialogState(state: Boolean){
        uiState.update {
            it.copy(
                showDialog = state
            )
        }
    }


}