package com.example.dropy.ui.screens.loginAs

import androidx.lifecycle.ViewModel
import com.example.dropy.di.DropyApp
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.deliveryType.DeliveryTypeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


data class LoginAsDialogUiState(
    val type: String = "",
    val showDialog: Boolean = false,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class LoginAsDialogViewModel @Inject constructor(
    private val app: DropyApp
): ViewModel() {


    val uiState = MutableStateFlow(LoginAsDialogUiState())

    val loginAsDialogUiState: StateFlow<LoginAsDialogUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    fun navigate(text: String){
        if (text.equals("CUSTOMER")){
            uiState.update {
                it.copy(showDialog = false)
            }
            appViewModel?.navigate(AppDestinations.APP_HOME)
        }else{
            uiState.update {
                it.copy(showDialog = false)
            }
            appViewModel?.navigate(AppDestinations.SERVICE_PROVIDERS)
        }
    }


    fun setType(text : String){
        uiState.update {
            it.copy(
                type = text
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