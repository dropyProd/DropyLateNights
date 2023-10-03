package com.example.dropy.ui.screens.nearestWaterPoint

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.collectionPointOrder.CollectionPointOrderReq
import com.example.dropy.network.models.collectionPointOrderRes.CollectionPointOrderRes
import com.example.dropy.network.models.getWaterPoints.GetWaterPointsRes
import com.example.dropy.network.models.getWaterPoints.GetWaterPointsResItem
import com.example.dropy.network.models.updateTruckLocationReq.UpdateTruckLocationReq
import com.example.dropy.network.use_case.createCollectionPointOrder.CreateCollectionPointOrderUseCase
import com.example.dropy.network.use_case.updateTruckLocation.UpdateTruckLocationUseCase
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.allocateTruck.AllocatingTruckUiState
import com.example.dropy.ui.screens.truckIncomingWork.TruckIncomingWorkUiState
import com.example.dropy.ui.screens.truckRouteCustomer.TruckRouteCustomerViewModel
import com.example.dropy.ui.screens.truckRouteWaterPoint.TruckRouteWaterPointViewModel
import com.example.dropy.ui.utils.Resource
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

data class NearestWaterPointUiState(
    val allWaterPoints: List<GetWaterPointsResItem> = listOf(),
    val selectedWaterPoint: GetWaterPointsResItem? = null,
    val collectionPointOrderRes: CollectionPointOrderRes? = null,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)


@HiltViewModel
class NearestWaterPointViewModel @Inject constructor(
    private val app: DropyApp,
    private val createCollectionPointOrderUseCase: CreateCollectionPointOrderUseCase,
    private val updateTruckLocationUseCase: UpdateTruckLocationUseCase
) : ViewModel() {
    val uiState = MutableStateFlow(NearestWaterPointUiState())

    val nearestWaterPointUiState: StateFlow<NearestWaterPointUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    fun getMyLocale(context: Context) {
        app.getCurrentLocation(context)
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

    fun navigateTruckRouteWaterPoint(
        truckIncomingWorkUiState: TruckIncomingWorkUiState,
        truckRouteWaterPointViewModel: TruckRouteWaterPointViewModel
    ) {
        truckRouteWaterPointViewModel.getPath(
            truckLatLng = LatLng(
                app.currentLocation.value!!.latitude.toString().toDouble(),
                app.currentLocation.value!!.longitude.toString().toDouble()
            ),
            customerLatLng = LatLng(
                nearestWaterPointUiState.value.selectedWaterPoint?.latitude?.toString()!!
                    .toDouble(),
                nearestWaterPointUiState.value.selectedWaterPoint?.longitude?.toString()!!
                    .toDouble()
            )
        )
        appViewModel?.navigate(AppDestinations.TRUCK_ROUTE_WATERPOINT)
    }

    fun setSelectedWaterPoint(getWaterPointsResItem: GetWaterPointsResItem) {
        Log.d("kolopo", "setSelectedWaterPoint: $getWaterPointsResItem")
        uiState.update {
            it.copy(
                selectedWaterPoint = getWaterPointsResItem
            )
        }
    }

    fun getWaterPoints() {
        uiState.update {
            it.copy(
                allWaterPoints = app.waterpoints
            )
        }
        Log.d("kioppop", "getWaterPoints: ${uiState.value.allWaterPoints.size}")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createWaterpointOrder(
        truckIncomingWorkUiState: TruckIncomingWorkUiState,
        context: Context,
        truckRouteWaterPointViewModel: TruckRouteWaterPointViewModel
    ) {
        viewModelScope.launch {

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

            val item = CollectionPointOrderReq(
                collection_point = uiState.value.selectedWaterPoint?.id.toString(),
                quantity = truckIncomingWorkUiState.truckDetails?.capacity!!.toInt(),
                total_cost = truckIncomingWorkUiState.selectedOrder?.total_payment.toString(),
                truck = truckIncomingWorkUiState.truckDetails.id,
                water_type = truckIncomingWorkUiState.selectedOrder?.water_type.toString(),
                completed_timestamp = LocalDateTime.now().format(formatter)
            )

            Log.d("vfcd", "createWaterpointOrder: $item")

            createCollectionPointOrderUseCase(
                token = "Token " + app.token.value,
                collectionPointOrderReq = item
            ).flowOn(Dispatchers.IO)
                /*  .catch { e ->
                      // handle exception
                      _addWaterpointImagesUiState.update { it.copy(pageLoading = false) }

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
                                uiState.update {
                                    it.copy(
                                        pageLoading = false,
                                        collectionPointOrderRes = result.data
                                    )
                                }
                                Toast.makeText(context, "order created success", Toast.LENGTH_SHORT)
                                    .show()
                                navigateTruckRouteWaterPoint(
                                    truckIncomingWorkUiState = truckIncomingWorkUiState,
                                    truckRouteWaterPointViewModel = truckRouteWaterPointViewModel
                                )
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