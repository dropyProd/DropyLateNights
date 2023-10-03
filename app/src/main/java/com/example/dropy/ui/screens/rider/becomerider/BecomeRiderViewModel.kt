package com.example.dropy.ui.screens.rider.becomerider

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.createrider.CreateRiderRequest
import com.example.dropy.network.models.deliverymethods.DeliveryMethodResponseItem
import com.example.dropy.network.models.pools.RiderPoolsResponseItem
import com.example.dropy.network.repositories.commons.CommonRepository
import com.example.dropy.network.repositories.rider.RiderRepository
import com.example.dropy.network.use_case.common.GetDeliveryMethodsUseCase
import com.example.dropy.network.use_case.rider.CreateRiderUseCase
import com.example.dropy.network.use_case.rider.GetAllRiderPoolsUseCase
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


data class BecomeRiderUiState(
    val availablePools: List<RiderPoolsResponseItem> = listOf(),
    val deliveryMethods: List<DeliveryMethodResponseItem> = listOf(),
    val plate_number: String = "",
    val bike_model: String = "",
    val county_qr_id_number: String = "",
    val description: String = "",
    val dropyuser_id: Int = 0,
    val delivery_method_id: Int = 0,
    val pool_id: String = "",
    val national_id: String = "",
    val place_name: String = "",
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

//constructor injection
@HiltViewModel
class BecomeRiderViewModel @Inject constructor(
    private val riderRepository: RiderRepository,
    private val commonRepository: CommonRepository,
    private val getDeliveryMethodsUseCase: GetDeliveryMethodsUseCase,
    private val getAllRiderPoolsUseCase: GetAllRiderPoolsUseCase,
    private val createRiderUseCase: CreateRiderUseCase,
    private val app: DropyApp
) : ViewModel() {
    val uiState = MutableStateFlow(BecomeRiderUiState())

    val shopHomePageUiState: StateFlow<BecomeRiderUiState> = uiState.asStateFlow()


    init {
        getAlLRiderPools()
        //getDeliveryMethods()
    }


    fun updatePlatenumber(text: String) {
        uiState.update {
            it.copy(
                plate_number = text
            )
        }
    }

    fun updateBikeModel(text: String) {
        uiState.update {
            it.copy(
                bike_model = text
            )
        }
    }

    fun updateCountyQrIdNumber(text: String) {
        uiState.update {
            it.copy(
                county_qr_id_number = text
            )
        }
    }

    fun updateDescription(text: String) {
        uiState.update {
            it.copy(
                description = text
            )
        }
    }

    fun updateDeliverymethodId(text: DeliveryMethodResponseItem) {
        uiState.update {
            it.copy(
                delivery_method_id = text.id!!
            )
        }
    }

    fun updatepoolid(text: RiderPoolsResponseItem) {
        uiState.update {
            it.copy(
                pool_id = text.id!!
            )
        }
    }

    fun updatenationalid(text: String) {
        uiState.update {
            it.copy(
                national_id = text
            )
        }
    }

    fun updateplacename(text: String) {
        uiState.update {
            it.copy(
                place_name = text
            )
        }
    }
/*    fun updatebikename(text: String) {
        uiState.update {
            it.copy(
                bike_model = text
            )
        }
    }*/


    fun createRider(appViewModel: AppViewModel, context: Context) {
        viewModelScope.launch {

            try {
                uiState.update {
                    it.copy(
                        pageLoading = true
                    )
                }
                val item = appViewModel.appUiState.value.myAddress?.placeName?.let {
                    appViewModel.appUiState.value.globalDropyUserId?.let { it1 ->
                        CreateRiderRequest(
                            plate_number = uiState.value.plate_number,
                            bike_model = uiState.value.bike_model,
                            county_qr_id_number = uiState.value.county_qr_id_number.toInt(),
                            description = uiState.value.description,
                            dropyuser_id = it1,
                            delivery_method_id = uiState.value.delivery_method_id,
                            pool_id = uiState.value.pool_id,
                            national_id = uiState.value.national_id.toInt(),
                            place_name = it
                        )
                    }
                }

                item?.let {
                    createRiderUseCase.createRider(it, context).flowOn(Dispatchers.IO)
                        .catch { e ->
                            // handle exception
                        }
                        .collect { result ->
                            // list of users from the network
                            Log.d("uopopi", "getAllShops: $result")
                            when (result) {
                                is Resource.Success -> {

                                    if (result.data != null) {
                                        Log.d("ijih", "createRider: $result")
                                        uiState.update {
                                            it.copy(
                                                pageLoading = false
                                            )
                                        }
                                        appViewModel.navigate(AppDestinations.APP_HOME)
                                    } else {
                                        uiState.update {
                                            it.copy(
                                                pageLoading = false
                                            )
                                        }
                                    }

                                }
                                is Resource.Loading -> {
                                    uiState.update { it.copy(pageLoading = true) }
                                }
                                is Resource.Error -> {
                                    result.message?.let { message ->
                                        uiState.update {
                                            it.copy(
                                                pageLoading = false,
                                                errorList = listOf(message)
                                            )
                                        }
                                    }

                                }
                            }

                        }
                }


            } catch (e: Exception) {
                uiState.update {
                    it.copy(
                        pageLoading = false
                    )
                }
            }

        }
    }

    fun getAlLRiderPools() {
        viewModelScope.launch {
            getAllRiderPoolsUseCase.getAllRiderPools().flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                }
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: $result")
                    when (result) {
                        is Resource.Success -> {

                            Log.d("jijij", "getAlLRiderPools: $result")

                            if (result.data != null) {

                                uiState.update {
                                    it.copy(
                                        availablePools = result.data
                                    )
                                }
                            }

                        }
                        is Resource.Loading -> {
                            uiState.update { it.copy(pageLoading = true) }
                        }
                        is Resource.Error -> {
                            result.message?.let { message ->
                                uiState.update {
                                    it.copy(
                                        pageLoading = false,
                                        errorList = listOf(message)
                                    )
                                }
                            }

                        }
                    }

                }


        }

    }

    fun getDeliveryMethods() {
        viewModelScope.launch {
            getDeliveryMethodsUseCase.getDeliveryMethods("Token "+app.token.value).flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                }
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getDeliveryMethods: ${result.data}")
                    when (result) {
                        is Resource.Success -> {

                            Log.d("huhuh", "getDeliveryMethods: ${result.data}")
                            if (result.data != null) {

                                uiState.update {
                                    it.copy(
                                        deliveryMethods = result.data
                                    )
                                }
                            }

                        }
                        is Resource.Loading -> {
                            uiState.update { it.copy(pageLoading = true) }
                        }
                        is Resource.Error -> {
                            result.message?.let { message ->
                                uiState.update {
                                    it.copy(
                                        pageLoading = false,
                                        errorList = listOf(message)
                                    )
                                }
                            }

                        }
                    }

                }


        }

    }


}