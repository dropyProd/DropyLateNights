package com.example.dropy.ui.screens.truckRouteWaterPoint

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.updateTruckLocationReq.UpdateTruckLocationReq
import com.example.dropy.network.use_case.updateTruckLocation.UpdateTruckLocationUseCase
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.nearestWaterPoint.NearestWaterPointUiState
import com.example.dropy.ui.screens.truckIncomingWork.TruckIncomingWorkUiState
import com.example.dropy.ui.screens.truckStartTrip.TruckStartTripViewModel
import com.example.dropy.ui.utils.Resource
import com.google.android.gms.maps.model.LatLng
import com.google.maps.DirectionsApi
import com.google.maps.GeoApiContext
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


data class TruckRouteWaterPointUiState(
    val currentTime: String = "",
    val duration: String = "",
    val distance: String = "",
    val myAddress: LatLng? = null,
    val path: MutableList<LatLng> = mutableListOf(),
    val isCustomerRoute: Boolean = false,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class TruckRouteWaterPointViewModel @Inject constructor(
    private val app: DropyApp,
    private val updateTruckLocationUseCase: UpdateTruckLocationUseCase
) : ViewModel() {
    val uiState = MutableStateFlow(TruckRouteWaterPointUiState())

    val truckRouteWaterPointUiState: StateFlow<TruckRouteWaterPointUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    fun navigateTruckStartTrip(
        truckStartTripViewModel: TruckStartTripViewModel,
        nearestWaterPointUiState: NearestWaterPointUiState,
        truckIncomingWorkUiState: TruckIncomingWorkUiState
    ) {
        viewModelScope.launch {
            /* truckStartTripViewModel.getPathWaterpoint(
                 truckLatLng = LatLng(
                     app.currentLocation.value!!.latitude.toString().toDouble(),
                     app.currentLocation.value!!.longitude.toString().toDouble()
                 ),
                 customerLatLng = LatLng(
                     nearestWaterPointUiState.selectedWaterPoint?.latitude?.toString()!!.toDouble(),
                     nearestWaterPointUiState.selectedWaterPoint?.longitude?.toString()!!.toDouble()
                 )
             )
             truckStartTripViewModel.getPathCustomer(
                 truckLatLng = LatLng(
                     nearestWaterPointUiState.selectedWaterPoint?.latitude?.toString()!!.toDouble(),
                     nearestWaterPointUiState.selectedWaterPoint?.longitude?.toString()!!.toDouble()
                 ),
                 customerLatLng = LatLng(
                     truckIncomingWorkUiState.selectedOrder?.delivery_latitude.toString().toDouble(),
                     truckIncomingWorkUiState.selectedOrder?.delivery_longitude.toString().toDouble()
                 )
             )*/
            appViewModel?.navigate(AppDestinations.SCAN_QR_WATER)
        }

    }

    fun setPageState(isCustomer: Boolean) {
        uiState.update {
            it.copy(isCustomerRoute = isCustomer)
        }
    }

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

    fun getPath(truckLatLng: LatLng, customerLatLng: LatLng) {
        viewModelScope.launch {

            Log.d("molp", "getPath: $truckLatLng")
            Log.d("kolp", "getPath: $customerLatLng")
            //Define list to get all latlng for the route
            var path: MutableList<LatLng> = mutableListOf()
            //Execute Directions API request
            //Execute Directions API request
            val context = GeoApiContext.Builder()
                .apiKey("AIzaSyCnrF8f8v1z1s3NVpPp7Dw-dksvOlQs9XI")
                .build()
            val req =
                DirectionsApi.getDirections(
                    context,
                    "${app.currentLocation.value!!.latitude},${app.currentLocation.value!!.longitude}",
                    "${customerLatLng.latitude},${customerLatLng.longitude}"
                )
//            try {
            val res = req.await()

            //Loop through legs and steps to get encoded polylines of each step
            if (res.routes != null && res.routes.size > 0) {
                val duration = mutableStateOf(0L)
                val distance = mutableStateOf(0L)
                val route = res.routes[0]
                if (route.legs != null) {
                    for (i in route.legs.indices) {
                        val leg = route.legs[i]
                        duration.value += leg.duration.inSeconds
                        distance.value += leg.distance.inMeters
                        if (leg.steps != null) {
                            for (j in leg.steps.indices) {
                                val step = leg.steps[j]
                                if (step.steps != null && step.steps.size > 0) {
                                    for (k in step.steps.indices) {
                                        val step1 = step.steps[k]
                                        val points1 = step1.polyline
                                        if (points1 != null) {
                                            //Decode polyline and add points to list of route coordinates
                                            val coords1 = points1.decodePath()
                                            for (coord1 in coords1) {
                                                path.add(LatLng(coord1.lat, coord1.lng))
                                            }
                                        }
                                    }
                                } else {
                                    val points = step.polyline
                                    if (points != null) {
                                        //Decode polyline and add points to list of route coordinates
                                        val coords = points.decodePath()
                                        for (coord in coords) {
                                            path.add(LatLng(coord.lat, coord.lng))
                                        }
                                    }
                                }
                            }
                        }
                    }
                    setDistance(distance.value)
                    setTime(duration.value)

                }
            }
            /*        } catch (ex: Exception) {
                        Log.e("TAG", ex.localizedMessage)
                    }
        */
            uiState.update {
                it.copy(path = path)
            }
        }
    }

    private fun setTime(value: Long) {

        /*   val formatterTime = DateTimeFormatter.ofPattern("HH:mm")
           val newTime = formatterTime.parse(timee.value)*/
        /*      val formatterNewTime = DateTimeFormatter.ofPattern("hh:mm a")
               val formatTime = formatterNewTime.format(newTime)*/

        val sdf = SimpleDateFormat("hh:mm a")
//        val currentDate = sdf.format(Date())


        Log.d("kmnb", "setTime: time $value")
        val hours = value.toInt() / 3600;
        val minutes = ((value.toInt()) % 3600) / 60;
        val seconds = value.toInt() % 60;

        if (hours > 0) {
            val currentTimeNow = Calendar.getInstance()
            System.out.println("Current time now : " + currentTimeNow.time)
            currentTimeNow.add(Calendar.HOUR, hours)
            currentTimeNow.add(Calendar.MINUTE, minutes)
            val MinsAddedFromNow = currentTimeNow.time
            val currentTime = sdf.format(MinsAddedFromNow)
            uiState.update { it.copy(currentTime = currentTime) }
        } else {
            val currentTimeNow = Calendar.getInstance()
            System.out.println("Current time now : " + currentTimeNow.time)
            currentTimeNow.add(Calendar.MINUTE, minutes)
            val MinsAddedFromNow = currentTimeNow.time
            val currentTime = sdf.format(MinsAddedFromNow)
            uiState.update { it.copy(currentTime = currentTime) }
        }

//      val  timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        uiState.update { it.copy(duration = if (hours > 0) "${hours}hr ${minutes}min" else "${minutes}min") }
    }

    private fun setDistance(value: Long) {
        Log.d("kmnb", "setDistance: distance $value")
        uiState.update { it.copy(distance = (value.toInt() / 1000).toString()) }

    }
}