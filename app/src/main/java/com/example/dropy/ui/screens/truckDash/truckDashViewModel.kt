package com.example.dropy.ui.screens.truckDash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.GetIndividualOrders.AssignedTruck
import com.example.dropy.network.models.GetIndividualOrders.GetIndividualOrdersResItem
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


data class TruckDashUiState(
    val list: List<GetIndividualOrdersResItem> = listOf(),
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class TruckDashViewModel @Inject constructor(
    private val app: DropyApp
) : ViewModel() {
    val uiState = MutableStateFlow(TruckDashUiState())

    val truckDashUiState: StateFlow<TruckDashUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null

    fun navigateFindJobs() {
        appViewModel!!.navigate(AppDestinations.TRUCK_FIND_JOB)
    }

    fun getCustomers() {
        val list: MutableList<GetIndividualOrdersResItem> = mutableListOf()
        val truckk: MutableState<AssignedTruck?> = mutableStateOf(null)
        app.individualOrders.forEach {
            it.assigned_trucks.forEach { truck ->
                if (truck.id.equals(appViewModel!!.appUiState.value.activeProfile?.id)) {
                    if (!list.contains(it)) list.add(it)
                    truckk.value = truck
                }
            }
        }

        val state: MutableState<String> = mutableStateOf("")

        val filterList: MutableList<GetIndividualOrdersResItem> = mutableListOf()

        list.forEach {
            if (!state.equals(it.customer.id)) if (!filterList.contains(it)) filterList.add(it)
            state.value = it.customer.id
        }

        uiState.update {
            it.copy(
               list = filterList
            )
        }
    }

}