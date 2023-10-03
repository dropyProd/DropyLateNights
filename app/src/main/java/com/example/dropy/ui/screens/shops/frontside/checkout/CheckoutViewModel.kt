package com.example.dropy.ui.screens.shops.frontside.checkout

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.deliverymethods.DeliveryMethodResponseItem
import com.example.dropy.network.repositories.commons.CommonRepository
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.network.use_case.common.GetDeliveryMethodsUseCase
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.commons.AddressDataClass
import com.example.dropy.ui.components.shops.frontside.customer.orderprocess.payments.PaymentMethodDataClass
import com.example.dropy.ui.screens.payments.mpesapaymentdialog.MpesaPaymentDialogViewModel
import com.example.dropy.ui.screens.rider.IncomingJobViewModel
import com.example.dropy.ui.screens.shops.backside.orderdetails.OrderDetailsViewModel
import com.example.dropy.ui.screens.shops.backside.shopincomingorders.ShopIncomingOrdersViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageUiState
import com.example.dropy.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DeliveryInformationUiState(
    val currentAddress: AddressDataClass? = null,
    val deliveryMethodList: List<DeliveryMethodResponseItem> = emptyList(),
    val taggedAddresses: List<AddressDataClass> = emptyList(),
    val autocompleteAddresses: List<AddressDataClass> = emptyList(),
    val deliveryPrice: Int = 0,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList(),
    val hasmoreInfo: Boolean = false
)

data class OrderPaymentPageUiState(
    val selectedMethod: PaymentMethodDataClass? = null,
    val selectedDeliveryMethod: DeliveryMethodResponseItem? = null,
    val walletBalance: Int = 0,
    val orderCost: Int = 0,
    val deliveryCost: Int = 0,
    val discount: Int = 0,
    val total: Int = 0,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val commonRepository: CommonRepository,
    private val shopFrontendRepository: ShopFrontendRepository,
    private val getDeliveryMethodsUseCase: GetDeliveryMethodsUseCase,
    private val app: DropyApp
) : ViewModel() {

    private val _deliveryInformationUiState = MutableStateFlow(DeliveryInformationUiState())
    val deliveryInformationUiState: StateFlow<DeliveryInformationUiState> =
        _deliveryInformationUiState.asStateFlow()

    val _orderPaymentPageUiState = MutableStateFlow(OrderPaymentPageUiState())
    val orderPaymentPageUiState: StateFlow<OrderPaymentPageUiState> =
        _orderPaymentPageUiState.asStateFlow()

    var appViewModel: AppViewModel? = null

    val transactionId = mutableStateOf(0)


    fun getmethods(shopHomePageUiState: ShopHomePageUiState) {
        getDeliveryMethods(shopHomePageUiState)
    }

    fun setPageRoute(info: Boolean) {
        _deliveryInformationUiState.update {
            it.copy(
                hasmoreInfo = info
            )
        }
        Log.d("pPPO", "setPageRoute: ${_deliveryInformationUiState.value.hasmoreInfo}")
    }

    fun setDeliveryPrice(price: Int) {
        _deliveryInformationUiState.update {
            it.copy(
                deliveryPrice = price
            )
        }
        _orderPaymentPageUiState.update {
            it.copy(deliveryCost = price)
        }
    }

    fun getDeliveryMethods(shopHomePageUiState: ShopHomePageUiState) {
        viewModelScope.launch {
            getDeliveryMethodsUseCase.getDeliveryMethods("Token " + app.token.value)
                .flowOn(Dispatchers.IO)
                /*.catch { e ->
                    // handle exception
                }*/
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: ${result.data}")
                    when (result) {
                        is Resource.Success -> {

                            Log.d("huhuh", "onShopSelected: ${result.data}")
                            if (result.data != null) {

                                _deliveryInformationUiState.update { it.copy(deliveryMethodList = result.data) }
                                for (i in 0..3) {
                                    if (i == 2) {
                                        if (!result.data.isEmpty()) {
                                            val price =
                                                (shopHomePageUiState.shopDistanceNum.toInt() / 1000) * result.data[0].default_charge_per_km!!
                                            onSelectMethod(result.data[0])
                                            setDeliveryPrice(price)
                                        }


                                    }

                                    delay(3000)
                                }
                            }

                        }
                        is Resource.Loading -> {
                            _deliveryInformationUiState.update { it.copy(pageLoading = true) }
                        }
                        is Resource.Error -> {
                            result.message?.let { message ->
                                _deliveryInformationUiState.update {
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

    fun setTransactionId(id: Int) {
        transactionId.value = id
    }

    fun selectTaggedAddress(address: AddressDataClass) {

    }

    fun editTaggedLocation(address: AddressDataClass) {

    }

    fun onMyLocationClicked() {

    }

    fun addLocationSuggests(list: List<AddressDataClass>) {
        _deliveryInformationUiState.update {
            it.copy(
                autocompleteAddresses = list
            )
        }
        Log.d(
            "TAGGG",
            "addLocationSuggests: ${_deliveryInformationUiState.value.autocompleteAddresses}"
        )
    }

    fun onLocationNameChanged(name: String) {
        _deliveryInformationUiState.update {
            it.copy(
                currentAddress = AddressDataClass(placeName = name)
            )
        }
    }

    fun onEditDeliveryLocationClicked() {
        appViewModel?.navigate(ShopsFrontDestination.SINGLE_ADDRESS)
    }

    fun onSelectMethod(deliveryMethod: DeliveryMethodResponseItem) {

        _orderPaymentPageUiState.update {
            it.copy(selectedDeliveryMethod = deliveryMethod)
        }
    }

    fun onNextClicked() {

        val total = mutableStateOf(0)
        total.value =
            orderPaymentPageUiState.value.total + orderPaymentPageUiState.value.discount + orderPaymentPageUiState.value.deliveryCost

        _orderPaymentPageUiState.update {
            it.copy(
                orderCost = total.value
            )
        }
        appViewModel?.navigate(ShopsFrontDestination.ORDER_PAYMENT)
    }


    fun createVirtualOrder(
        shopIncomingOrdersViewModel: ShopIncomingOrdersViewModel,
        checkoutViewModel: CheckoutViewModel,
        appViewModel: AppViewModel,
        orderDetailsViewModel: OrderDetailsViewModel,
        incomingJobViewModel: IncomingJobViewModel
    ) {

    }

    fun onPayClicked() {
        appViewModel?.navigate(ShopsFrontDestination.PAYMENT_COMPLETE_ETA_DELIVERY)
    }

    fun onSelectPaymentMethod(paymentMethod: PaymentMethodDataClass) {
        _orderPaymentPageUiState.update { it.copy(selectedMethod = paymentMethod) }
    }

    fun onAddPaymentMethod(paymentMethod: PaymentMethodDataClass) {

    }


    fun pay(
        firebaseUid: String,
        phoneNumber: String,
        mpesaPaymentDialogViewModel: MpesaPaymentDialogViewModel,
        navController: NavController
    ) {
        val format = mutableStateOf("")
        phoneNumber.toList().forEachIndexed { index, char ->
            if (index.equals(0)) {
                format.value += "254"
            } else {
                format.value += char.toString()
            }
        }
        Log.d("TAG", "pay:${format.value} ")
        viewModelScope.launch {
            val res = shopFrontendRepository.pay(
                firebase_uid = firebaseUid,//"12345678",
                amount = 1,
                phone_number = format.value
            )

            if (res != null) {
                // onPayClicked()
                mpesaPaymentDialogViewModel.dismissDialog()
                navController.navigate(ShopsFrontDestination.PAYMENT_COMPLETE_ETA_DELIVERY)
            }
        }

    }
}