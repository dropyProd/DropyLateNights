package com.example.dropy.ui.screens.waterTracking

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.scanQRWater.ScanQRWaterViewModel
import com.example.dropy.ui.screens.waterOrderPay.WaterOrderPayUiState
import com.google.android.gms.maps.model.LatLng
import com.google.maps.DirectionsApi
import com.google.maps.GeoApiContext
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WaterTrackingUiState(
    val duration: String = "",
    val distance: String = "",
    val path: MutableList<LatLng> = mutableListOf(),
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class WaterTrackingViewModel @Inject constructor(
    private val app: DropyApp
) : ViewModel() {
    val uiState = MutableStateFlow(WaterTrackingUiState())

    val waterTrackingUiState: StateFlow<WaterTrackingUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    fun navigateScanQrWater(scanQRWaterViewModel: ScanQRWaterViewModel, text: String) {
        scanQRWaterViewModel.changeState(true)
        scanQRWaterViewModel.generateQr(text)
        appViewModel?.navigate(AppDestinations.SCAN_QR_WATER)
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
                    "${truckLatLng.latitude},${truckLatLng.longitude}",
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
        Log.d("kmnb", "setTime: time $value")
        val hours = value.toInt() / 3600;
        val minutes = ((value.toInt()) % 3600) / 60;
        val seconds = value.toInt() % 60;

//      val  timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        uiState.update { it.copy(duration = if (hours > 0) "${hours}HR ${minutes}MIN" else "${minutes}MIN") }
    }

    private fun setDistance(value: Long) {
        Log.d("kmnb", "setDistance: distance $value")
        uiState.update { it.copy(distance = (value.toInt() / 1000).toString()) }

    }

}