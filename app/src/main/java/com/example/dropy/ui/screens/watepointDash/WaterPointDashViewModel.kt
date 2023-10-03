package com.example.dropy.ui.screens.watepointDash

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.CreateWaterPointRes
import com.example.dropy.network.models.getWaterPointOrders.GetWaterPointOrdersResItem
import com.example.dropy.network.models.getWaterPointOrders.Truck
import com.example.dropy.network.models.getWaterPoints.GetWaterPointsResItem
import com.example.dropy.network.use_case.getCollectionPointOrder.GetCollectionPointOrdersUseCase
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WaterPointDashUiState(
    val waterpoint: GetWaterPointsResItem? = null,
    val waterpointO: CreateWaterPointRes? = null,
    val list: List<GetWaterPointOrdersResItem> = listOf(),
    val trucks: List<Truck> = listOf(),
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class WaterPointDashViewModel @Inject constructor(
    private val app: DropyApp,
    private val getCollectionPointOrdersUseCase: GetCollectionPointOrdersUseCase
): ViewModel() {

    val uiState = MutableStateFlow(WaterPointDashUiState())

    val waterPointDashUiState: StateFlow<WaterPointDashUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null

    fun navigateOrders() {
        appViewModel!!.navigate(AppDestinations.WATERPOINT_ORDERS)
    }

    fun getWaterpoint(){
        val item: MutableState<GetWaterPointsResItem?> = mutableStateOf(null)
        app.waterpoints.forEachIndexed { index, getWaterPointsResItem ->
            if (getWaterPointsResItem.id.equals(appViewModel!!.appUiState.value.activeProfile?.id)) {

                item.value = getWaterPointsResItem
            }
        }

       uiState.update {
           it.copy(
               waterpoint = item.value
           )
       }
    }

    fun setWaterPoint(addWaterpointRes: CreateWaterPointRes){
        uiState.update {
            it.copy(
                waterpointO = addWaterpointRes
            )
        }
    }
    fun getWaterpointOrders() {
        viewModelScope.launch {

            getCollectionPointOrdersUseCase(
                token = "Token " + app.token.value,
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

                                val list: MutableList<Truck> = mutableListOf()
                                val listt: MutableList<GetWaterPointOrdersResItem> = mutableListOf()

                                result.data.forEachIndexed { index, getWaterPointOrdersResItem ->
                                    if (getWaterPointOrdersResItem.collection_point.id.equals(
                                            appViewModel!!.appUiState.value.activeProfile?.id
                                        )
                                    ) {
                                        list.add(getWaterPointOrdersResItem.truck)
                                        listt.add(getWaterPointOrdersResItem)
                                    }
                                }

                                uiState.update { it.copy(trucks = list, list = listt,pageLoading = false) }

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