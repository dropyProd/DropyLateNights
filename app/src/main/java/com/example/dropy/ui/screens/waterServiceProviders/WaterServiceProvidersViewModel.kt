package com.example.dropy.ui.screens.waterServiceProviders

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.dropy.di.DropyApp
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.appdrawer.ActiveProfileDataClass
import com.example.dropy.ui.components.commons.appdrawer.ProfileTypes
import com.example.dropy.ui.screens.serviceProviders.ServiceProvidersUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class WaterServiceProvidersUiState(
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class WaterServiceProvidersViewModel @Inject constructor(
    private val app: DropyApp
) : ViewModel() {
    val uiState = MutableStateFlow(WaterServiceProvidersUiState())

    val waterServiceProvidersUiState: StateFlow<WaterServiceProvidersUiState> =
        uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    fun navigateWaterPoint() {
        if (app.myWaterpoints.size > 0) {
            val item = ActiveProfileDataClass(
                type = ProfileTypes.WATER_POINT,
                name = app.myWaterpoints[0].name,
                id = app.myWaterpoints[0].id
            )

            appViewModel?.onSelectProfile(item)
//            appViewModel?.navigate(AppDestinations.WATERPOINT_DASH)
        } else
            appViewModel?.navigate(AppDestinations.CREATE_WATERPOINT)
    }

    fun navigateWaterTruck() {
        val item : MutableState<ActiveProfileDataClass?> = mutableStateOf(null)
        app.waterTruckDrivers.forEach {
            if (it.driver.id.equals(appViewModel!!.appUiState.value.activeProfile!!.id))
                item.value = ActiveProfileDataClass(
                type = ProfileTypes.WATER_TRUCK,
                name = it.truck.license_plate,
                id = it.truck.id
            )
        }
        if (item.value != null) {
            appViewModel?.onSelectProfile(item.value!!)
//            appViewModel?.navigate(AppDestinations.WATERPOINT_DASH)
        } else
            appViewModel?.navigate(AppDestinations.ADD_WATER_DRIVER)
    }

    fun navigateWaterVendor() {
        if (app.myWaterVendors.size > 0) {
            val item = ActiveProfileDataClass(
                type = ProfileTypes.WATER_VENDOR,
                name = app.myWaterVendors[0].name,
                id = app.myWaterVendors[0].id
            )

            appViewModel?.onSelectProfile(item)
//            appViewModel?.navigate(AppDestinations.WATERPOINT_DASH)
        } else
            appViewModel?.navigate(AppDestinations.CREATE_WATERVENDOR)
    }
}