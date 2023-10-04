package com.example.dropy.ui.screens.truckIncomingWork

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.GetIndividualOrders.AssignedTruck
import com.example.dropy.network.models.GetIndividualOrders.GetIndividualOrdersResItem
import com.example.dropy.network.models.registerDeviceReq.RegisterDeviceReq
import com.example.dropy.network.models.updateTruckLocationReq.UpdateTruckLocationReq
import com.example.dropy.network.use_case.generateDeliveryCode.GenerateDeliveryCodeUseCase
import com.example.dropy.network.use_case.registerDevice.RegisterDeviceUseCase
import com.example.dropy.network.use_case.updateTruckLocation.UpdateTruckLocationUseCase
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.waterOrderSingle.WaterOrderSingleViewModel
import com.example.dropy.ui.utils.Resource
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


data class TruckIncomingWorkUiState(
    val myAddress: LatLng? = null,
    val orders: List<GetIndividualOrdersResItem> = listOf(),
    val truckDetails: AssignedTruck? = null,
    val selectedOrder: GetIndividualOrdersResItem? = null,
    val hasongoingJob: Boolean = false,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class TruckIncomingWorkViewModel @Inject constructor(
    private val app: DropyApp,
    private val generateDeliveryCodeUseCase: GenerateDeliveryCodeUseCase,
    private val registerDeviceUseCase: RegisterDeviceUseCase,
    private val updateTruckLocationUseCase: UpdateTruckLocationUseCase
) : ViewModel() {
    val uiState = MutableStateFlow(TruckIncomingWorkUiState())

    val truckIncomingWorkUiState: StateFlow<TruckIncomingWorkUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    fun navigateWaterOrderSingle(context: Context) {
//        generateDeliveryCode(context)
//        generateFirebaseToken(context)
        appViewModel!!.navigate(AppDestinations.WATER_ORDER_SINGLE)
    }

    fun generateFirebaseToken(context: Context) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("lop", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            //registerDevice(context = context, firebaseToken = token)
            generateDeliveryCode(context)
            Toast.makeText(context, token, Toast.LENGTH_SHORT).show()
            Log.d("lmkol", "generateFirebaseToken: $token")
        })

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

    fun setAddress(waterOrderSingleViewModel: WaterOrderSingleViewModel){
        waterOrderSingleViewModel.getPath(
            LatLng(
                app.currentLocation.value!!.latitude,
                app.currentLocation.value!!.longitude
            ),
            LatLng(
                truckIncomingWorkUiState.value.selectedOrder?.delivery_latitude.toString().toDouble(),
                truckIncomingWorkUiState.value.selectedOrder?.delivery_longitude.toString().toDouble()
            )
        )
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


    fun registerDevice(
        context: Context,
        firebaseToken: String
    ) {
        viewModelScope.launch {

            val item = RegisterDeviceReq(
                active = true,
                device_id = app.myDeviceId.value,
                name = appViewModel!!.appUiState.value.activeProfile?.name.toString(),
                registration_id = firebaseToken,
                type = "android"
            )

            registerDeviceUseCase(
                token = "Token " + app.token.value,
                registerDeviceReq = item
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
                                        pageLoading = false
                                    )
                                }

                                generateDeliveryCode(context)
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

    fun generateDeliveryCode(
        context: Context
    ) {
        viewModelScope.launch {

            val id = mutableStateOf("")

            uiState.value.selectedOrder?.tasks?.forEach {
                if (it.truck.id.equals(appViewModel!!.appUiState.value.activeProfile?.id)) {
                    id.value = it.id
                }
            }

            Log.d("kolpp", "generateDeliveryCode: ${id.value}")
            Toast.makeText(context, id.value, Toast.LENGTH_SHORT).show()
            generateDeliveryCodeUseCase(
                token = "Token " + app.token.value,
                taskId = id.value
            ).flowOn(Dispatchers.IO)
                  .catch { e ->
                      // handle exception
                      uiState.update { it.copy(pageLoading = false) }
                      appViewModel!!.navigate(AppDestinations.WATER_ORDER_SINGLE)

                  }
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
                                        pageLoading = false
                                    )
                                }
                                appViewModel!!.navigate(AppDestinations.WATER_ORDER_SINGLE)

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

    fun setSelectedOrder(getIndividualOrdersResItem: GetIndividualOrdersResItem) {
        uiState.update {
            it.copy(
                selectedOrder = getIndividualOrdersResItem
            )
        }
    }

    fun getOrders() {

        val list: MutableList<GetIndividualOrdersResItem> = mutableListOf()
        val truckk: MutableState<AssignedTruck?> = mutableStateOf(null)
        app.individualOrders.forEach {
            it.assigned_trucks.forEach { truck ->
                if (truck.id.equals(appViewModel!!.appUiState.value.activeProfile?.id)) {
                    if (!list.contains(it)) list.add(it)
                    truckk.value = truck
                }
            }
        }

        uiState.update {
            it.copy(truckDetails = truckk.value, orders = list)
        }
    }

}