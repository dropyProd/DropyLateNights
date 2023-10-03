package com.example.dropy.ui.screens.cancelling

import androidx.lifecycle.ViewModel
import com.example.dropy.ui.screens.rider.RiderDetailsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ReasonForCancellingDialogUiState(
    val outOfStockState: Boolean = false,
    val toReStockLaterState: Boolean = false,
    val productImageUrl: String = ""
)

class ReasonForCancellingDialogViewModel : ViewModel() {

    val uiState = MutableStateFlow(ReasonForCancellingDialogUiState())

    val reasonForCancellingDialogUiState: StateFlow<ReasonForCancellingDialogUiState> =
        uiState.asStateFlow()


    fun setProductImageUrl(text: String){
        uiState.update {
            it.copy(
                productImageUrl = text
            )
        }
    }

    fun outOfStockStateChange(state: Boolean) {
        uiState.update {
            it.copy(
                outOfStockState = state
            )
        }
    }

    fun toReStockLaterStateChange(state: Boolean) {
        uiState.update {
            it.copy(
                toReStockLaterState = state
            )
        }
    }

}