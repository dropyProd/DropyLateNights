package com.example.dropy.ui.screens.waterVendorDash

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.GetIndividualOrders.AssignedTruck
import com.example.dropy.network.models.GetIndividualOrders.GetIndividualOrdersResItem
import com.example.dropy.network.models.getWaterTrucks.GetTrucksResItem
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.truckOrders.TruckOrdersViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


data class WaterVendorDashUiState(
    val list: List<GetIndividualOrdersResItem> = listOf(),
    val truckList: List<GetTrucksResItem> = listOf(),
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class WaterVendorDashViewModel @Inject constructor(
    private val app: DropyApp
) : ViewModel() {
    val uiState = MutableStateFlow(WaterVendorDashUiState())

    val waterVendorDashUiState: StateFlow<WaterVendorDashUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null

    fun navigateMyTrucks() {
        appViewModel!!.navigate(AppDestinations.MY_TRUCK)
    }


    fun getWaterTrucks() {
        val list: MutableList<GetTrucksResItem> = mutableListOf()
        app.waterTrucks.forEach {
            Log.d("lopko", "getWaterTrucks: ${it.vendor.id} ===>> ${appViewModel!!.appUiState.value.activeProfile?.id}")
            if (it.vendor.id.equals(appViewModel!!.appUiState.value.activeProfile?.id))
                if (!list.contains(it)) list.add(it)
        }

        Log.d("lopk", "getWaterTrucks: ${app.waterTrucks.size}")
        uiState.update {
            it.copy(
                truckList = list
            )
        }
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

    fun navigateTruckOrders(truckOrdersViewModel: TruckOrdersViewModel, getTrucksResItem: GetTrucksResItem) {
        truckOrdersViewModel.getOrders(getTrucksResItem)
        appViewModel!!.navigate(AppDestinations.TRUCK_ORDERS_HISTORY)
    }


    fun resetValues(){
        uiState.update {
            it.copy(
                list = listOf(),
                truckList = listOf()
            )
        }
    }

}