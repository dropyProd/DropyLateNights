package com.example.dropy.ui.screens.waterOrderDetails

import androidx.lifecycle.ViewModel
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.createIndividualWaterOrder.AssignedTruck
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeUiState
import com.example.dropy.ui.screens.water.waterHome.WaterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class WaterOrderDetailsUiState(
    val fiveTrucks: List<AssignedTruck> = listOf(),
    val tenTrucks: List<AssignedTruck> = listOf(),
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)


@HiltViewModel
class WaterOrderDetailsViewModel @Inject constructor(
    app: DropyApp
) : ViewModel() {

    val uiState = MutableStateFlow(WaterOrderDetailsUiState())

    val waterOrderDetailsUiState: StateFlow<WaterOrderDetailsUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    fun navigateWaterOrderPay() {
        appViewModel?.navigate(AppDestinations.WATER_ORDER_PAY)
    }

    fun setTrucks(tankerBoreholeUiState: TankerBoreholeUiState) {
        val tennTrucks: MutableList<AssignedTruck> = mutableListOf()
        val fiveeTrucks: MutableList<AssignedTruck> = mutableListOf()
        tankerBoreholeUiState.createIndividualWaterOrderRes!!.assigned_trucks.forEach {
            if (it.capacity.equals(10000)) {
                if (!tennTrucks.contains(it)) tennTrucks.add(it)
            }else{
                if (!fiveeTrucks.contains(it)) fiveeTrucks.add(it)
            }
        }

        uiState.update {
            it.copy(tenTrucks = tennTrucks, fiveTrucks = fiveeTrucks)
        }
    }
}