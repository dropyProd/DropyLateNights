package com.example.dropy.ui.screens.waterpointOrders

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.collectionPointOrder.CollectionPointOrderReq
import com.example.dropy.network.models.getWaterPointOrders.CollectionPoint
import com.example.dropy.network.models.getWaterPointOrders.GetWaterPointOrdersResItem
import com.example.dropy.network.use_case.getCollectionPointOrder.GetCollectionPointOrdersUseCase
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.truckIncomingWork.TruckIncomingWorkUiState
import com.example.dropy.ui.screens.truckRouteWaterPoint.TruckRouteWaterPointViewModel
import com.example.dropy.ui.screens.water.waterHome.WaterUiState
import com.example.dropy.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

data class WaterPointOrderUiState(
    val orders: List<GetWaterPointOrdersResItem> = listOf(),
    val selectedType: String = "",
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)


@HiltViewModel
class WaterpointOrdersViewModel @Inject constructor(
    private val app: DropyApp,
    private val getCollectionPointOrdersUseCase: GetCollectionPointOrdersUseCase
) : ViewModel() {

    val uiState = MutableStateFlow(WaterPointOrderUiState())

    val waterPointOrderUiState: StateFlow<WaterPointOrderUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null

    fun navigateNewOrder(){
        appViewModel!!.navigate(AppDestinations.WATERPOINT_NEW_ORDER)
    }

    fun getWaterpointOrders(

    ) {
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

                                val list: MutableList<GetWaterPointOrdersResItem> = mutableListOf()

                                result.data.forEachIndexed { index, getWaterPointOrdersResItem ->
                                    if (getWaterPointOrdersResItem.collection_point.id.equals(
                                            appViewModel!!.appUiState.value.activeProfile?.id
                                        )
                                    )
                                        list.add(getWaterPointOrdersResItem)
                                }

                                uiState.update { it.copy(orders = list, pageLoading = false) }

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