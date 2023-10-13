package com.example.dropy.ui.screens.tankerBorehole

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.createIndividualWaterOrder.CreateIndividualWaterOrderRes
import com.example.dropy.network.models.individualWaterOrder.IndividualWaterOrderReq
import com.example.dropy.network.use_case.createCorporateOrder.CreateCorporateOrderUseCase
import com.example.dropy.network.use_case.createIndividualWaterOrder.CreateIndividualWaterOrderUseCase
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.AddressDataClass
import com.example.dropy.ui.screens.deliveryType.DeliveryTypeUiState
import com.example.dropy.ui.screens.deliveryType.DeliveryTypeViewModel
import com.example.dropy.ui.screens.nearestTrucks.NearestTrucksViewmodel
import com.example.dropy.ui.screens.water.waterHome.WaterUiState
import com.example.dropy.ui.screens.waterOrderDetails.WaterOrderDetailsViewModel
import com.example.dropy.ui.utils.Resource
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

data class TankerBoreholeUiState(
    val selectedAddress: AddressDataClass? = null,
    val myAddress: LatLng? = null,
    val createIndividualWaterOrderRes: CreateIndividualWaterOrderRes? = null,
    val volume: String = "",
    val selectedTimeSlot: String = "",
    val selectedDate: String = "",
    val cheque: Uri? = null,
    val slot: List<String> = listOf("Daily", "Monthly", "Weekly"),
    val selctedSlot: String = "Daily",
    val recurring: Boolean = false,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)


@HiltViewModel
class TankerBoreholeViewModel @Inject constructor(
    private val app: DropyApp,
    private val createCorporateOrderUseCase: CreateCorporateOrderUseCase,
    private val createIndividualWaterOrderUseCase: CreateIndividualWaterOrderUseCase
) : ViewModel() {
    val uiState = MutableStateFlow(TankerBoreholeUiState())

    val tankerBoreholeUiState: StateFlow<TankerBoreholeUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null

    fun onVolumeChange(text: String) {
        viewModelScope.launch {
            uiState.update {
                it.copy(volume = text)
            }
        }
    }

    fun onDateSelectedChange(text: String) {
        viewModelScope.launch {
            uiState.update {
                it.copy(selectedDate = text)
            }
        }
    }

    fun onTimeSlotSelectedSelectedChange(text: String) {
        viewModelScope.launch {
            uiState.update {
                it.copy(selectedTimeSlot = text)
            }
        }
    }
    fun onSlotSelectedSelectedChange(text: String) {
        viewModelScope.launch {
            uiState.update {
                it.copy(selctedSlot = text)
            }
        }
    }
    fun onRecurringChange(state: Boolean) {
        viewModelScope.launch {
            uiState.update {
                it.copy(recurring = state)
            }
        }
    }

    fun changeDialogState(deliveryTypeViewModel: DeliveryTypeViewModel, context: Context){
        if (uiState.value.selectedAddress != null &&  uiState.value.volume != ""){
            val rem = mutableStateOf(0)
            val state = mutableStateOf(false)


            if (uiState.value.volume.toInt() <= 5000){
                val rem = uiState.value.volume.toInt() % 5000
            }else{

            }
            deliveryTypeViewModel.changeDialogState(true)

        }else{
            Toast.makeText(context, "Fill all required fields", Toast.LENGTH_SHORT).show()
        }
    }

    fun addAddress(addressDataClass: AddressDataClass) {
        uiState.update {
            it.copy(
                selectedAddress = addressDataClass
            )
        }
    }

    fun setImageCheque(bitmap: Bitmap, uri: Uri, context: Context) {
        viewModelScope.launch {
            uiState.update {
                it.copy(
                    cheque = uri
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createCorporateOrder(
        context: Context,
        waterUiState: WaterUiState,
        deliveryTypeUiState: DeliveryTypeUiState
    ) {
        viewModelScope.launch {

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val date = LocalDateTime.now().format(formatter)


            tankerBoreholeUiState.value.cheque?.let {
                createCorporateOrderUseCase(
                    cheque = it,
                    context = context,
                    delivery_latitude = tankerBoreholeUiState.value.selectedAddress?.latitude.toString(),
                    delivery_longitude = tankerBoreholeUiState.value.selectedAddress?.longitude.toString(),
                    delivery_place_name = tankerBoreholeUiState.value.selectedAddress?.placeName.toString(),
                    delivery_status = "P",
                    status = "P",
                    payment_status = "P",
                    delivery_type = if (deliveryTypeUiState.deliveryType.equals("SCHEDULED")) "S" else if (deliveryTypeUiState.deliveryType.equals(
                            "EXPRESS"
                        )
                    ) "E" else "T",
                    note = "n/a",
                    quantity = tankerBoreholeUiState.value.volume.toInt(),
                    token = app.token.value,
                    total_payment = "40000",
                    tracking_id = "009877",
                    water_type = if (waterUiState.selectedType.equals("CLEAN WATER")) "C" else if (waterUiState.selectedType.equals(
                            "TREATED WATER"
                        )
                    ) "F" else "P",
                    scheduled_time = date
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
                                    uiState.update { it.copy(pageLoading = false) }
                                    Toast.makeText(
                                        context,
                                        "${result.data.name} created success",
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
    }

    fun createIndividualWaterOrder(
        context: Context,
        waterUiState: WaterUiState,
        deliveryTypeUiState: DeliveryTypeUiState,
        nearestTrucksViewmodel: NearestTrucksViewmodel,
        waterOrderDetailsViewModel: WaterOrderDetailsViewModel
    ) {
        viewModelScope.launch {

            val item = if (deliveryTypeUiState.deliveryType.equals("SCHEDULED"))
                IndividualWaterOrderReq(
                    delivery_latitude = tankerBoreholeUiState.value.selectedAddress?.latitude.toString(),
                    delivery_longitude = tankerBoreholeUiState.value.selectedAddress?.longitude.toString(),
                    delivery_type = if (deliveryTypeUiState.deliveryType.equals("SCHEDULED")) "S" else if (deliveryTypeUiState.deliveryType.equals(
                            "EXPRESS"
                        )
                    ) "E" else "T",
                    quantity = tankerBoreholeUiState.value.volume.toInt(),
                    water_type = if (waterUiState.selectedType.equals("CLEAN WATER")) "C" else if (waterUiState.selectedType.equals(
                            "TREATED WATER"
                        )
                    ) "F" else "P",
                    delivery_place_name = tankerBoreholeUiState.value.selectedAddress?.placeName.toString()
//                    scheduled_time = tankerBoreholeUiState.value.selectedDate + tankerBoreholeUiState.value.selectedTimeSlot
                )
            else
                IndividualWaterOrderReq(
                    delivery_latitude = tankerBoreholeUiState.value.selectedAddress?.latitude.toString(),
                    delivery_longitude = tankerBoreholeUiState.value.selectedAddress?.longitude.toString(),
                    delivery_type = if (deliveryTypeUiState.deliveryType.equals("SCHEDULED")) "S" else if (deliveryTypeUiState.deliveryType.equals(
                            "EXPRESS"
                        )
                    ) "E" else "T",
                    quantity = tankerBoreholeUiState.value.volume.toInt(),
                    water_type = if (waterUiState.selectedType.equals("CLEAN WATER")) "C" else if (waterUiState.selectedType.equals(
                            "TREATED WATER"
                        )
                    ) "F" else "P",
                    delivery_place_name = tankerBoreholeUiState.value.selectedAddress?.placeName.toString()
                   /* scheduled_time = ""*/
                )


            Log.d("kop", "createIndividualWaterOrder: $item")

            createIndividualWaterOrderUseCase(
                token = "Token " + app.token.value,
                individualWaterOrderReq = item
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


                                uiState.update {
                                    it.copy(
                                        createIndividualWaterOrderRes = result.data,
                                        pageLoading = false
                                    )
                                }
                                Toast.makeText(
                                    context,
                                    "Order${result.data.tracking_id} created success",
                                    Toast.LENGTH_SHORT
                                ).show()
                                nearestTrucksViewmodel.setSelectedTruck(
                                    result.data.assigned_trucks[0],
                                    waterOrderDetailsViewModel = waterOrderDetailsViewModel,
                                    navigate = false,
                                    tankerBoreholeUiState = tankerBoreholeUiState.value
                                )
                                appViewModel!!.navigate(AppDestinations.NEAREST_TRUCKS)
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