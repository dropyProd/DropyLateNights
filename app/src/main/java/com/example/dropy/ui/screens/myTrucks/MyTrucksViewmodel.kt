package com.example.dropy.ui.screens.myTrucks

import androidx.lifecycle.ViewModel
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.GetIndividualOrders.GetIndividualOrdersResItem
import com.example.dropy.network.models.getTruckDrivers.GetTruckDriversResItem
import com.example.dropy.network.models.getWaterTrucks.GetTrucksResItem
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.myTruckEditDetails.MyTruckEditDetailsViewModel
import com.example.dropy.ui.screens.truckOrders.TruckOrdersViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class MyTrucksUiState(
    val list: List<GetIndividualOrdersResItem> = listOf(),
    val truckDriverList: List<GetTruckDriversResItem> = listOf(),
    val truckList: List<GetTrucksResItem> = listOf(),
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class MyTrucksViewmodel @Inject constructor(
    private val app: DropyApp
): ViewModel() {
    val uiState = MutableStateFlow(MyTrucksUiState())

    val myTrucksUiState: StateFlow<MyTrucksUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null

    fun navigateTruckOrders(truckOrdersViewModel: TruckOrdersViewModel, getTrucksResItem: GetTrucksResItem) {
        truckOrdersViewModel.getOrders(getTrucksResItem)
        appViewModel!!.navigate(AppDestinations.TRUCK_ORDERS_HISTORY)
    }

    fun navigateTruckEdit(myTruckEditDetailsViewModel: MyTruckEditDetailsViewModel, getTrucksResItem: GetTrucksResItem){
        myTruckEditDetailsViewModel.setSelectedTruckId(getTrucksResItem)
        appViewModel!!.navigate(AppDestinations.MYTRUCK_EDITDETAILS)
    }

    fun getTruckDrivers(){
        uiState.update {
            it.copy(truckDriverList = app.waterTruckDrivers)
        }
    }
}