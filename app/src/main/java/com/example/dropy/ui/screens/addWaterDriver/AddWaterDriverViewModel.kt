package com.example.dropy.ui.screens.addWaterDriver

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.addWaterDriver.AddWaterDriverReq
import com.example.dropy.network.models.addWaterTruckRes.AddWaterTruckRes
import com.example.dropy.network.use_case.createDriver.CreateDriverUseCase
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.appdrawer.ActiveProfileDataClass
import com.example.dropy.ui.components.commons.appdrawer.ProfileTypes
import com.example.dropy.ui.screens.water.waterHome.WaterUiState
import com.example.dropy.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TruckDriverUiState(
    val licensePlate: String = "",
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class AddWaterDriverViewModel @Inject constructor(
    private val app: DropyApp,
    private val createWaterDriverUseCase: CreateDriverUseCase,
): ViewModel() {
    val uiState = MutableStateFlow(TruckDriverUiState())

    val truckDriverUiState: StateFlow<TruckDriverUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    fun navigateTruckDashboard(){
        //if (waterHomeUiState.value.selectedType.equals("\nCLEAN WATER") && waterHomeUiState.value.selectedType.equals("\nTREATED WATER"))
        appViewModel?.navigate(AppDestinations.TRUCK_DASHBOARD)
//        appViewModel?.navigate(AppDestinations.TRUCK_FIND_JOB)
    }
    fun setLicensePlate(text: String){
        uiState.update {
            it.copy(licensePlate =text)
        }
    }



    fun addWaterDriver(context: Context) {
        viewModelScope.launch {

            val truckId = mutableStateOf("")

            app.waterTrucks.forEach {
                if (it.license_plate.equals(truckDriverUiState.value.licensePlate))
                    truckId.value = it.id
            }

            val item = AddWaterDriverReq(
                driver = app.id.value.toString(),
                is_active = true,
                is_approved = false,
                is_seen_by_vendor = false,
                license_number = truckDriverUiState.value.licensePlate,
                truck = truckId.value
            )

            Log.d("jnmkl", "addWaterDriver: $item")

            createWaterDriverUseCase(
                token = "Token " + app.token.value,
                addWaterDriverReq = item
            ).flowOn(Dispatchers.IO)
                /*    .catch { e ->
                        // handle exception
                        _addWaterTruckImagesUiState.update { it.copy(pageLoading = false) }

                    }*/
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: $result")
                    when (result) {
                        is Resource.Success -> {

                            Log.d("KKTAG", "onAddShop: $result")
                            if (result.data != null) {
                                //  if (result.data?.resultCode?.equals(0) == true) {
//                                _addShopImagesUiState.update { it.copy(pageLoading = false) }
//                                moveAddProductCategory()
                                // }
                                val item = ActiveProfileDataClass(
                                    type = ProfileTypes.WATER_TRUCK,
                                    name = uiState.value.licensePlate,
                                    id = truckId.value
                                )
                                appViewModel!!.getWaterTrucks()
                                uiState.update { it.copy(pageLoading = false) }

                                Toast.makeText(
                                    context,
                                    "driver created success",
                                    Toast.LENGTH_SHORT
                                ).show()
                                appViewModel?.onSelectProfile(item)
//                                appViewModel?.navigate(AppDestinations.WATER_VENDOR_DASHBOARD)
                            }
//                            _addShopImagesUiState.update { it.copy(pageLoading = false) }


                        }
                        is Resource.Loading -> {
                            uiState.update { it.copy(pageLoading = true) }
                        }
                        is Resource.Error -> {
//                            result.message?.let { message ->
                            uiState.update {
                                it.copy(
                                    pageLoading = false,
                                    errorList = listOf()
                                )
                            }
//                            }

                        }
                    }

                }
        }
    }

}