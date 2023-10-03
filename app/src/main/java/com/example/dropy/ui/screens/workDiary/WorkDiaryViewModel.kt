package com.example.dropy.ui.screens.workDiary

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.GetIndividualOrders.AssignedTruck
import com.example.dropy.network.models.GetIndividualOrders.GetIndividualOrdersResItem
import com.example.dropy.network.models.getWaterTrucks.GetTrucksResItem
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


data class WorkDiaryUiState(
    val orders: List<GetIndividualOrdersResItem> = listOf(),
    val truckDetails: AssignedTruck? = null,
    val state: Boolean = false,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class WorkDiaryViewModel @Inject constructor(
    private val app: DropyApp
): ViewModel() {
    val uiState = MutableStateFlow(WorkDiaryUiState())

    val workDiaryUiState: StateFlow<WorkDiaryUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    fun navigateWaterOrderSingle() {
        appViewModel?.navigate(AppDestinations.WATER_ORDER_SINGLE)
    }

    fun getOrders(getTrucksResItem: GetTrucksResItem? = null) {

        val list: MutableList<GetIndividualOrdersResItem> = mutableListOf()
        val truckk: MutableState<AssignedTruck?> = mutableStateOf(null)
        app.individualOrders.forEach {
            it.assigned_trucks.forEach { truck ->
                if (getTrucksResItem != null) {
                    if (truck.id.equals(getTrucksResItem.id)) {
                        if (!list.contains(it)) list.add(it)
                        truckk.value = truck
                    }
                } else {
                    if (truck.id.equals(appViewModel!!.appUiState.value.activeProfile?.id)) {
                        if (!list.contains(it)) list.add(it)
                        truckk.value = truck
                    }
                }
            }
        }

        uiState.update {
            it.copy(truckDetails = truckk.value, orders = list)
        }
        if (getTrucksResItem != null){
            uiState.update {
                it.copy(state = true)
            }
        }
    }


}