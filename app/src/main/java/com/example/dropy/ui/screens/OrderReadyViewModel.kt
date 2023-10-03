package com.example.dropy.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.ui.screens.authentication.AuthenticationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class OrderReadyUiState(
    val minutes: String? = "",
    val currentMinutes: String? = "",
    val slotOne: String? = "",
    val slotTwo: String? = "",
    val slotThree: String? = "",
    val slotFour: String? = "",
    val errorMessages: List<String> = emptyList(),
    val messages: List<String> = emptyList(),
    val isLoading: Boolean = false
)

@HiltViewModel
class OrderReadyViewModel @Inject constructor(
    private val app: DropyApp
) : ViewModel() {
    private val uiState = MutableStateFlow(OrderReadyUiState())

    val orderReadyUiState: StateFlow<OrderReadyUiState> = uiState.asStateFlow()

    fun startTimer() {
        viewModelScope.launch {


            for (i in uiState.value.minutes.toString().toInt() downTo 0) {
                for (j in 60 downTo 0) {
                    uiState.update {
                        it.copy(currentMinutes = "${i.toString()}:${j.toString()}")
                    }
                    if (i == 0) {
                        uiState.update {
                            it.copy(
                                currentMinutes = "${i.toString()}:${j.toString()}",
                                minutes = "${i.toString()}:${j.toString()}"
                            )
                        }
                    }
                    if (i in 1..9) {
                        uiState.update {
                            it.copy(slotOne = "0", slotTwo = i.toString())
                        }
                    } else {
                        for (k in 0 until i.toString().length) {
                            if (k == 0) {
                                uiState.update {
                                    it.copy(slotOne = k.toString())
                                }
                            }
                            if (k == 1) {
                                uiState.update {
                                    it.copy(slotTwo = i.toString())
                                }
                            }
                        }
                    }
                    if (j in 1..9) {
                        uiState.update {
                            it.copy(slotThree = "0", slotFour = j.toString())
                        }
                    } else {
                        for (k in 0 until j.toString().length) {
                            if (k == 0) {
                                uiState.update {
                                    it.copy(slotThree = j.toString())
                                }
                            }
                            if (k == 1) {
                                uiState.update {
                                    it.copy(slotFour = j.toString())
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    fun setMinutes(num: String){
        uiState.update {
            it.copy(minutes = num, currentMinutes = num)
        }
    }
}