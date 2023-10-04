package com.example.dropy.ui.screens.nearestTrucks

import androidx.lifecycle.ViewModel
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.createIndividualWaterOrder.AssignedTruck
import com.example.dropy.network.models.getWaterPoints.GetWaterPointsResItem
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.nearestWaterPoint.NearestWaterPointUiState
import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeUiState
import com.example.dropy.ui.screens.waterOrderDetails.WaterOrderDetailsViewModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class NearestTrucksUiState(
    val selectedTruck: AssignedTruck? = null,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)


@HiltViewModel
class NearestTrucksViewmodel @Inject constructor(
    private val app: DropyApp
) : ViewModel() {
    val uiState = MutableStateFlow(NearestTrucksUiState())

    val nearestTrucksUiState: StateFlow<NearestTrucksUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    fun navigateOrderDetails(
        waterOrderDetailsViewModel: WaterOrderDetailsViewModel,
        tankerBoreholeUiState: TankerBoreholeUiState
    ) {
        waterOrderDetailsViewModel.getPath(
            LatLng(
                uiState.value.selectedTruck?.current_latitude.toString().toDouble(),
                uiState.value.selectedTruck?.current_longitude.toString().toDouble()
            ),
            LatLng(
                tankerBoreholeUiState.selectedAddress?.latitude.toString().toDouble(),
                tankerBoreholeUiState.selectedAddress?.longitude.toString().toDouble()
            )
        )
        appViewModel?.navigate(AppDestinations.WATER_ORDER_DETAILS)
    }

    fun setSelectedTruck(
        assignedTruck: AssignedTruck,
        waterOrderDetailsViewModel: WaterOrderDetailsViewModel,
        tankerBoreholeUiState: TankerBoreholeUiState,
        navigate: Boolean = true
    ) {
        uiState.update {
            it.copy(
                selectedTruck = assignedTruck
            )
        }
        waterOrderDetailsViewModel.setTrucks(tankerBoreholeUiState)
        if (navigate)
            navigateOrderDetails(
                waterOrderDetailsViewModel = waterOrderDetailsViewModel,
                tankerBoreholeUiState = tankerBoreholeUiState
            )
    }

}