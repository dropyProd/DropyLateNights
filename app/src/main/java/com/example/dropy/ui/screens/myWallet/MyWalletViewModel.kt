package com.example.dropy.ui.screens.myWallet

import androidx.lifecycle.ViewModel
import com.example.dropy.di.DropyApp
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.topUp.TopUpViewModel
import com.example.dropy.ui.screens.water.waterHome.WaterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class MyWalletUiState(
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class MyWalletViewModel @Inject constructor(
    private val app: DropyApp
) : ViewModel() {

    val uiState = MutableStateFlow(MyWalletUiState())

    val myWalletUiState: StateFlow<MyWalletUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null

    fun navigateTopUp(topUpViewModel: TopUpViewModel, text: String) {
        topUpViewModel.setState(text)
        appViewModel!!.navigate(AppDestinations.TOP_UP)
    }

}