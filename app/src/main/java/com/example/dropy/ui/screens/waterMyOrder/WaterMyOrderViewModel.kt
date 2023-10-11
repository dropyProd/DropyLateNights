package com.example.dropy.ui.screens.waterMyOrder

import androidx.lifecycle.ViewModel
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.GetIndividualOrders.GetIndividualOrdersResItem
import com.example.dropy.network.models.createIndividualWaterOrder.AssignedTruck
import com.example.dropy.network.models.createIndividualWaterOrder.CreateIndividualWaterOrderRes
import com.example.dropy.network.models.getTruckDrivers.GetTruckDriversResItem
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.waterTracking.WaterTrackingViewModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class WaterMyOrderUiState(
    val truckDriverList: List<GetTruckDriversResItem> = listOf(),
    val getIndividualOrdersResItem: GetIndividualOrdersResItem? = null,
    val createIndividualWaterOrderRes: CreateIndividualWaterOrderRes? = null,
    val selectedTruck: AssignedTruck? = null,
    val selectedTruckO: com.example.dropy.network.models.GetIndividualOrders.AssignedTruck? = null,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class WaterMyOrderViewModel @Inject constructor(
    private val app: DropyApp
) : ViewModel() {

    val uiState = MutableStateFlow(WaterMyOrderUiState())

    val waterMyOrderUiState: StateFlow<WaterMyOrderUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    fun navigateWaterTracking(
        waterTrackingViewModel: WaterTrackingViewModel,
        assignedTruck: AssignedTruck
    ) {

        uiState.update {
            it.copy(selectedTruck = assignedTruck, selectedTruckO = null)
        }

        if (uiState.value.createIndividualWaterOrderRes != null) {
            waterTrackingViewModel.getPath(
                LatLng(
                    assignedTruck.current_latitude.toDouble(),
                    assignedTruck.current_longitude.toDouble()
                ),
                LatLng(
                    uiState.value.createIndividualWaterOrderRes!!.delivery_latitude.toString()
                        .toDouble(),
                    uiState.value.createIndividualWaterOrderRes!!.delivery_longitude.toString()
                        .toDouble()
                )
            )
        }
        if (uiState.value.getIndividualOrdersResItem != null) {
            waterTrackingViewModel.getPath(
                LatLng(
                    assignedTruck.current_latitude.toDouble(),
                    assignedTruck.current_longitude.toDouble()
                ),
                LatLng(
                    uiState.value.getIndividualOrdersResItem!!.delivery_latitude.toString()
                        .toDouble(),
                    uiState.value.getIndividualOrdersResItem!!.delivery_longitude.toString()
                        .toDouble()
                )
            )
        }
        appViewModel?.navigate(AppDestinations.WATER_TRACKING)
    }
    fun getTruckDrivers(){
        uiState.update {
            it.copy(truckDriverList = app.waterTruckDrivers)
        }
    }


    fun navigateWaterTracking(
        waterTrackingViewModel: WaterTrackingViewModel,
        assignedTruck: com.example.dropy.network.models.GetIndividualOrders.AssignedTruck
    ) {

        uiState.update {
            it.copy(selectedTruckO = assignedTruck, selectedTruck = null)
        }

        if (uiState.value.createIndividualWaterOrderRes != null) {
            waterTrackingViewModel.getPath(
                LatLng(
                    assignedTruck.current_latitude.toDouble(),
                    assignedTruck.current_longitude.toDouble()
                ),
                LatLng(
                    uiState.value.getIndividualOrdersResItem!!.delivery_latitude.toString()
                        .toDouble(),
                    uiState.value.getIndividualOrdersResItem!!.delivery_longitude.toString()
                        .toDouble()
                )
            )
        }
        if (uiState.value.getIndividualOrdersResItem != null) {
            waterTrackingViewModel.getPath(
                LatLng(
                    assignedTruck.current_latitude.toDouble(),
                    assignedTruck.current_longitude.toDouble()
                ),
                LatLng(
                    uiState.value.getIndividualOrdersResItem!!.delivery_latitude.toString()
                        .toDouble(),
                    uiState.value.getIndividualOrdersResItem!!.delivery_longitude.toString()
                        .toDouble()
                )
            )
        }
        appViewModel?.navigate(AppDestinations.WATER_TRACKING)
    }

    fun setOrder(getIndividualOrdersResItem: GetIndividualOrdersResItem?) {
        uiState.update {
            it.copy(
                getIndividualOrdersResItem = getIndividualOrdersResItem,
                createIndividualWaterOrderRes = null
            )
        }

    }

    fun setOrder(createIndividualWaterOrderRes: CreateIndividualWaterOrderRes) {
        uiState.update {
            it.copy(
                createIndividualWaterOrderRes = createIndividualWaterOrderRes,
                getIndividualOrdersResItem = null
            )
        }
    }
}