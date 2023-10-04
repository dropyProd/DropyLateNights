package com.example.dropy.ui.screens.scanQRWater

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.use_case.verifyDeliveryCode.VerifyDeliveryCodeUseCase
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.AuthenticationDestinations
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.commons.appdrawer.ProfileTypes
import com.example.dropy.ui.screens.nearestWaterPoint.NearestWaterPointUiState
import com.example.dropy.ui.screens.truckIncomingWork.TruckIncomingWorkUiState
import com.example.dropy.ui.screens.truckStartTrip.TruckStartTripViewModel
import com.example.dropy.ui.screens.waterTracking.WaterTrackingUiState
import com.example.dropy.ui.utils.Resource
import com.google.android.gms.maps.model.LatLng
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ScanQRWaterUiState(
    val isNormal: Boolean = true,
    val code: String = "",
    val taskId: String = "",
    val bitmap: Bitmap? = null,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class ScanQRWaterViewModel @Inject constructor(
    private val app: DropyApp,
    private val verifyDeliveryCodeUseCase: VerifyDeliveryCodeUseCase
) : ViewModel() {
    val uiState = MutableStateFlow(ScanQRWaterUiState())

    val scanQRWaterUiState: StateFlow<ScanQRWaterUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null

    fun generateQr(text: String, taskId: String) {
        val multiFormatWriter: MultiFormatWriter = MultiFormatWriter()
        try {
            val bitMatrix: BitMatrix =
                multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
            val barcodeEncoder: BarcodeEncoder = BarcodeEncoder();
            val bitmap: Bitmap = barcodeEncoder.createBitmap(bitMatrix);
            uiState.update { it.copy(code = text, bitmap = bitmap, taskId = taskId) }
        } catch (e: WriterException) {
            e.printStackTrace();
        }
    }



    fun navigateOrderComplete(
        truckStartTripViewModel: TruckStartTripViewModel,
        nearestWaterPointUiState: NearestWaterPointUiState,
        truckIncomingWorkUiState: TruckIncomingWorkUiState
    ) {
        if (uiState.value.isNormal) {
            if (appViewModel?.appUiState!!.value.activeProfile!!.type.equals(ProfileTypes.CUSTOMER))
                appViewModel?.navigate(ShopsFrontDestination.ORDER_COMPLETE)
            else if (appViewModel?.appUiState!!.value.activeProfile!!.type.equals(ProfileTypes.WATER_TRUCK))
                appViewModel?.navigate(AppDestinations.TRUCK_ORDER_COMPLETE)
        } else {
            truckStartTripViewModel.getPathWaterpoint(
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
            )
            appViewModel?.navigate(AppDestinations.TRUCK_START_TRIP)
        }
    }

    fun changeState(state: Boolean) {
        uiState.update {
            it.copy(isNormal = state)
        }
    }

    fun navigateOtp() {
        appViewModel?.navigate(AuthenticationDestinations.ENTER_OTP)
    }

    fun navigateTrackOrderComplete() {
        appViewModel?.navigate(AppDestinations.TRUCK_ORDER_COMPLETE)
    }
}