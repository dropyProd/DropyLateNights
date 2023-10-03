package com.example.dropy.ui.screens.shops.frontside.checkout.trackyourorder

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.jobs.infoPojo
import com.example.dropy.network.repositories.commons.CommonRepository
import com.example.dropy.network.repositories.shop.back.ShopBackendRepository
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.network.use_case.common.DirectionMineUseCase
import com.example.dropy.network.use_case.shops.backside.GetShopQrUseCase
import com.example.dropy.network.use_case.shops.frontside.GetCustomerQrUseCase
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.screens.shops.backside.shopincomingorders.ShopIncomingOrdersViewModel
import com.example.dropy.ui.utils.QrBitmap
import com.example.dropy.ui.utils.Resource
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


data class TrackYourOrderUiState(
    val shopStatus: String = "active",
    val riderStatus: String = "inactive",
    val customerStatus: String = "inactive",
    val shopLatLng: LatLng? = null,
    val seconds: Int = 0,
    val polylines: List<LatLng> = listOf(),
    val shopDistance: String = "",
    val timetaken: Int = 1,
    val orderid: Int? = null,
    val shopDistanceNum: Int = 1,
    val unique_delivery_id: String = "",
    val encoded_customer_id: String = "",
    val encoded_order_number: String = "",
    val qr_bitmap: Bitmap? = null,
    val pageLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
)

@HiltViewModel
class TrackYourOrderViewModel @Inject constructor(
    private val shopBackendRepository: ShopBackendRepository,
    private val shopFrontendRepository: ShopFrontendRepository,
    private val commonRepository: CommonRepository,
    private val getCustomerQrUseCase: GetCustomerQrUseCase,
    private val getShopQrUseCase: GetShopQrUseCase,
    private val directionMineUseCase: DirectionMineUseCase,
    private val app: DropyApp
) : ViewModel() {

    private val uiState = MutableStateFlow(TrackYourOrderUiState())

    val trackYourOrderUiStateUiState: StateFlow<TrackYourOrderUiState> = uiState.asStateFlow()

    fun iterateList(shopIncomingOrdersViewModel: ShopIncomingOrdersViewModel) {
        shopIncomingOrdersViewModel.shopIncomingOrdersUiState.value.completedorderList.forEach {
            if (it.equals(shopIncomingOrdersViewModel.temporaryId.value)) {
                uiState.update { state ->
                    state.copy(
                        riderStatus = if (it.orderPickedbyRider) "active" else "inactive",
                        customerStatus = if (it.customerReceiveOrder) "active" else "inactive"
                    )
                }
            }
        }

    }

    fun setShopLatLng(latLng: LatLng) {
        uiState.update {
            it.copy(
                shopLatLng = latLng
            )
        }
    }


    fun getShopQr(orderId: Int, appViewModel: AppViewModel, context: Context) {
        viewModelScope.launch {

            Log.d("juhuhy", "getShopQr: $orderId")
            getShopQrUseCase.getShopQr(app.token.value, orderId, context).flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                    uiState.update { it.copy(pageLoading = false) }
                }
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: $result")
                    when (result) {
                        is Resource.Success -> {
                            Log.d("uhytr", "getShopQr: $result")

                            if (result.data != null) {


                                if (result.data.unique_delivery_id != null) {
                                    val qrBitmap = QrBitmap()

                                    val qr = qrBitmap.getBitmap(result.data.unique_delivery_id)
                                    uiState.update {
                                        it.copy(
                                            unique_delivery_id = result.data.unique_delivery_id.toString(),
                                            qr_bitmap = qr
                                        )
                                    }
                                    if (result.data.unique_delivery_id != null) {
                                        appViewModel.navigate(ParcelDestination.PARCEL_SCANQR)
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Order has not been accepted by rider",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
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


    fun setorderId(orderid: Int) {
        uiState.update {
            it.copy(
                orderid = orderid
            )
        }
    }

    fun getCustomerQr(appViewModel: AppViewModel, context: Context, show: Boolean = true) {
        viewModelScope.launch {
            trackYourOrderUiStateUiState.value.orderid?.let {
                getCustomerQrUseCase.getCustomerQr(it, context, show, app.token.value).flowOn(Dispatchers.IO)
                    .catch { e ->
                        // handle exception
                        Toast.makeText(context, "not yet assigned to rider", Toast.LENGTH_SHORT)
                            .show()
                    }
                    .collect { result ->
                        // list of users from the network
                        Log.d("uopopi", "getAllShops: $result")
                        when (result) {
                            is Resource.Success -> {
                                Log.d("hfygy", "getCustomerQr: $result")
                                if (result.data != null) {


                                    val qrBitmap = QrBitmap()
                                    val qr =
                                        qrBitmap.getBitmap("(${result.data.encoded_customer_id})(${result.data.encoded_order_number})")
                                    uiState.update {
                                        it.copy(
                                            encoded_customer_id = result.data.encoded_customer_id.toString(),
                                            encoded_order_number = result.data.encoded_order_number.toString(),
                                            qr_bitmap = qr
                                        )
                                    }
                                    appViewModel.navigate(ShopsFrontDestination.SCAN_QR_SHOP)
                                } else {
                                    Toast.makeText(
                                        context,
                                        "not yet assigned to rider",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
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

    fun getPolylines(appViewModel: AppViewModel) {
        viewModelScope.launch {
            val res = uiState.value.shopLatLng?.let {
                appViewModel?.appUiState?.value?.yourlocation?.latitude?.let { it1 ->
                    LatLng(
                        it1,
                        appViewModel?.appUiState?.value?.yourlocation?.longitude!!
                    )
                }?.let { it2 ->
                    directionMineUseCase.directionMine(
                        it,
                        it2
                    ).flowOn(
                        Dispatchers.IO
                    )
                        .catch { e ->
                            // handle exception
                        }
                        .collect { result ->
                            // list of users from the network
                            Log.d("uopopi", "getAllShops: $result")
                            when (result) {
                                is Resource.Success -> {

                                    if (result.data != null) {
                                        val current: MutableList<LatLng> = mutableListOf()




                                        result.data.routes?.forEach {
                                            it.legs?.forEach { leg ->
                                                var total: Int = 0
                                                total = leg.duration?.value!!
                                                uiState.update {
                                                    it.copy(
                                                        timetaken = total
                                                    )
                                                }
                                                leg.distance?.text?.let { it1 ->
                                                    leg.distance?.value?.let { it2 ->
                                                        setDistance(
                                                            it1,
                                                            it2
                                                        )
                                                    }
                                                }
                                                leg.steps?.forEach { step ->
                                                    val start =
                                                        step.start_location?.lat?.let { it1 ->
                                                            step.start_location.lng?.let { it2 ->
                                                                LatLng(
                                                                    it1,
                                                                    it2
                                                                )
                                                            }
                                                        }
                                                    val end = step.end_location?.lat?.let { it1 ->
                                                        step.end_location.lng?.let { it2 ->
                                                            LatLng(
                                                                it1,
                                                                it2
                                                            )
                                                        }
                                                    }

                                                    Log.d("kokoko", "start:$start ")
                                                    Log.d("kokoko", "start:$end ")
                                                    if (start != null) {
                                                        current.add(start)
                                                        if (end != null) {
                                                            current.add(end)
                                                        }
                                                    }
                                                }
                                            }

                                        }


                                        uiState.update {
                                            it.copy(
                                                polylines = current
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
    }

    fun setDistance(num: String, value: Int) {

        Log.d("lokok", "setDistance: $num  $value")
        uiState.update {
            it.copy(
                shopDistance = num,
                shopDistanceNum = value
            )
        }
    }
}