package com.example.dropy.ui.screens.truckFindJob

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.updateTruckLocationReq.UpdateTruckLocationReq
import com.example.dropy.network.use_case.updateTruckLocation.UpdateTruckLocationUseCase
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.screens.scanQRWater.ScanQRWaterUiState
import com.example.dropy.ui.utils.Resource
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TruckFindJobUiState(
    val myAddress: LatLng? = null,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class TruckFindJobViewModel @Inject constructor(
    private val app: DropyApp,
    private val updateTruckLocationUseCase: UpdateTruckLocationUseCase
) : ViewModel() {

    val uiState = MutableStateFlow(TruckFindJobUiState())

    val truckFindJobUiState: StateFlow<TruckFindJobUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null
    fun getMyLocale(context: Context) {
        app.getCurrentLocation(context)
        uiState.update {
            it.copy(
                myAddress = LatLng(
                    app.currentLocation.value!!.latitude,
                    app.currentLocation.value!!.longitude
                )
            )
        }
        updateTruckLocation(context)
    }
    fun updateTruckLocation(context: Context) {
        viewModelScope.launch {
            val item = UpdateTruckLocationReq(
                truck_id = appViewModel!!.appUiState.value.activeProfile?.id.toString(),
                latitude = app.currentLocation.value!!.latitude,
                longitude = app.currentLocation.value!!.longitude
            )
            updateTruckLocationUseCase(
                token = "Token " + app.token.value,
                updateTruckLocationReq = item
            ).flowOn(Dispatchers.IO)
                /*  .catch { e ->
                      // handle exception
                      uiState.update { it.copy(pageLoading = false) }

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

                                appViewModel!!.getWaterTrucks(false)
                                uiState.update {
                                    it.copy(
                                        pageLoading = false
                                    )
                                }
                                Toast.makeText(
                                    context,
                                    "${result.data}",
                                    Toast.LENGTH_SHORT
                                ).show()

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

    fun navigateIncomingjob() {
        appViewModel?.navigate(AppDestinations.TRUCK_INCOMING_WORK)
    }

    fun navigateWorkDiary() {
        appViewModel?.navigate(AppDestinations.WORK_DIARY)
    }

    fun navigateOrders() {
        appViewModel?.navigate(AppDestinations.TRUCK_ORDERS_HISTORY)
    }

}