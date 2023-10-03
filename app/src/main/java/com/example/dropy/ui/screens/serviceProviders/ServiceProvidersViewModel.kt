package com.example.dropy.ui.screens.serviceProviders

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.dropy.di.DropyApp
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.deliveryType.DeliveryTypeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class ServiceProvidersUiState(
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class ServiceProvidersViewModel @Inject constructor(
    private val app: DropyApp
) : ViewModel() {
    val uiState = MutableStateFlow(ServiceProvidersUiState())

    val serviceProvidersUiState: StateFlow<ServiceProvidersUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    fun navigateWaterServiceProviders(text: String, context: Context) {
        if (text.equals("WATER"))
            appViewModel?.navigate(AppDestinations.WATER_SERVICE_PROVIDERS)
        else
            Toast.makeText(context, "Coming soon....", Toast.LENGTH_LONG).show()
    }
}