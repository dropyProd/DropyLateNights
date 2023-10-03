package com.example.dropy.ui.screens.pin

import androidx.lifecycle.ViewModel
import com.example.dropy.ui.screens.apphome.AppHomePageUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class PinUiState (
    val pin: String = "",
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
        )

class PinScreenViewModel : ViewModel() {

    val uiState = MutableStateFlow(PinUiState())

    val pinUiState: StateFlow<PinUiState> = uiState.asStateFlow()


    fun onPinChange(text: String){
        uiState.update {
            it.copy(
                pin = text
            )
        }
    }

    fun loading(boolean: Boolean){

            uiState.update {
                it.copy(
                    pageLoading = boolean,
                    actionLoading = boolean
                )
            }

    }

}