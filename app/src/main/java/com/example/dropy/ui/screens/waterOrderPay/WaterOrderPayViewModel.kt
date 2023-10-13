package com.example.dropy.ui.screens.waterOrderPay

import androidx.lifecycle.ViewModel
import com.example.dropy.di.DropyApp
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.screens.waterOrderDetails.WaterOrderDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class WaterOrderPayUiState(
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)


@HiltViewModel
class WaterOrderPayViewModel @Inject constructor(
    app: DropyApp
) : ViewModel() {

    val uiState = MutableStateFlow(WaterOrderPayUiState())

    val waterOrderPayUiState: StateFlow<WaterOrderPayUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    fun navigateAllocatingTruck() {
//        appViewModel?.navigate(AppDestinations.ALLOCATING_TRUCKS)
        appViewModel?.navigate(ShopsFrontDestination.ORDER_PAYMENT)
    }

    fun navigateWaterThankYou(){
        appViewModel?.navigate(AppDestinations.WATER_TRANSACTION_COMPLETE)
    }

}