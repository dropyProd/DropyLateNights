package com.example.dropy.ui.screens.waterpointNewOrder

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.collectionPointOrder.CollectionPointOrderReq
import com.example.dropy.network.models.getWaterTrucks.GetTrucksResItem
import com.example.dropy.network.use_case.createCollectionPointOrder.CreateCollectionPointOrderUseCase
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

data class WaterPointNewOrderUiState(
    val truckCapacity: String = "",
    val licensePlate: String = "",
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class WaterpointNewOrderViewModel @Inject constructor(
    private val app: DropyApp,
    private val createCollectionPointOrderUseCase: CreateCollectionPointOrderUseCase
): ViewModel() {
    val uiState = MutableStateFlow(WaterPointNewOrderUiState())

    val waterPointNewOrderUiState: StateFlow<WaterPointNewOrderUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    fun setCapacity(capacity: String){
        uiState.update {
            it.copy(
                truckCapacity = capacity
            )
        }
    }

    fun onLicensePlateChange(licensePlate: String){
        uiState.update {
            it.copy(
                licensePlate = licensePlate
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createOrder(context: Context){
        viewModelScope.launch {

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

            val truckItem: MutableState<GetTrucksResItem?> = mutableStateOf(null)

            app.waterTrucks.forEachIndexed { index, getTrucksResItem ->

                if (uiState.value.licensePlate.equals(getTrucksResItem.license_plate))
                    truckItem.value = getTrucksResItem
            }

            val item = CollectionPointOrderReq(
                collection_point = appViewModel!!.appUiState.value.activeProfile?.id.toString(),
                quantity = truckItem.value?.capacity!!.toInt(),
                total_cost = "2000",
                truck = truckItem.value?.id.toString(),
                water_type = "C",
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
                                uiState.update { it.copy(pageLoading = false) }
                                Toast.makeText(context, "order created success", Toast.LENGTH_SHORT).show()

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