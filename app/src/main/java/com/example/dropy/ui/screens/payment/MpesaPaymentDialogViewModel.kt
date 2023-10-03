package com.example.dropy.ui.screens.payments.mpesapaymentdialog

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.dropy.di.DropyApp
import com.example.dropy.domain.addErrorMessage
import com.example.dropy.domain.addMessage
import com.example.dropy.domain.gerResponseError
import com.example.dropy.network.models.PaymentReq
import com.example.dropy.network.models.ProcessOrderReq
import com.example.dropy.network.models.checkoutPojo.ChargesPojo
import com.example.dropy.network.models.checkoutPojo.DeliveryLocation
import com.example.dropy.network.models.deliverymethods.DeliveryMethodResponseItem
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.network.services.cart.CartService
import com.example.dropy.network.use_case.TransactionStatusUseCase
import com.example.dropy.network.use_case.cart.ProcessOrderUseCase
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsBacksideNavigation
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.screens.notification.Constants
import com.example.dropy.ui.screens.notification.FcmMessageService
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutDataClass
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.OrderPaymentPageUiState
import com.example.dropy.ui.screens.workers.SendNotificationWorker
import com.example.dropy.ui.utils.Resource
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


private object PageErrors {
    const val BLANK_PHONE_NUMBER_FIELD = "Please enter your phone number"
    const val PHONE_NUMBER_TOO_SHORT = "Phone number entered was too short"
}


@HiltViewModel
class MpesaPaymentDialogViewModel @Inject constructor(
    private val cartService: CartService,
    private val app: DropyApp,
    private val transactionStatusUseCase: TransactionStatusUseCase,
    private val processOrderUseCase: ProcessOrderUseCase
) :
    ViewModel() {

    private val uiState = MutableStateFlow(MpesaPaymentDialogUiState())
    val mpesaPaymentDialogUiState: StateFlow<MpesaPaymentDialogUiState> = uiState.asStateFlow()

    fun onAmountChange(amount: Int) {
        uiState.update {
            it.copy(
                amount = amount
            )
        }
    }

    fun onPhoneNumberChange(phoneNumber: String) {
        uiState.update {
            it.copy(
                phoneNumber = phoneNumber
            )
        }
    }

    fun onPayClicked(
        appViewModel: AppViewModel,
        shopLatitude: Double,
        shopLongitude: Double,
        //deliveryMethodDataClass: DeliveryMethodDataClass,
        deliveryMethodDataClass: DeliveryMethodResponseItem?,
        cartPageViewModel: CartPageViewModel,
        paymentPageUiState: OrderPaymentPageUiState,
        checkoutViewModel: CheckoutViewModel,
        navController: NavController,
        context: Context
    ) {
        //  if (validatePhoneNumber()){
//            initiate mpesa stk push
//            set loading to true
//            delay for four secs
//            check status of transaction and update uiState
//            if transaction result is null wait another 4 sec and check again and update uiState
//            if still no result consider transaction failed and update uiState
//            after all processing loading = false
        viewModelScope.launch {

//            try {
            val deliveryLocation = appViewModel.appUiState.value.myAddress?.let {
                appViewModel.appUiState.value.myAddress?.longitude?.let { it1 ->
                    appViewModel.appUiState.value.myAddress?.latitude?.let { it2 ->
                        appViewModel.appUiState.value.myAddress?.placeId?.let { it3 ->
                            DeliveryLocation(
                                addressName = it.placeName,
                                apiLongitude = shopLongitude,
                                apiLatitude = shopLatitude,
                                userLongitude = it1,
                                userLatitude = it2,
                                placeId = it3,
                                locationTag = appViewModel.appUiState.value.myAddress?.locationTag.toString(),
                            )
                        }
                    }
                }
            }

            checkoutViewModel._orderPaymentPageUiState.update {
                it.copy(pageLoading = true)
            }

            val list: MutableList<ChargesPojo> = mutableListOf()
            val price = mutableStateOf(0)
            val id = mutableStateOf(0)
            cartPageViewModel.checkoutcurrent.forEach {
                /*if (it.get_order_total != null) {
                    price.value += it.get_order_total
                }*/
                if (it.id != null) {
                    id.value = it.id
                }
            }
            val itemCharge = paymentPageUiState.selectedMethod?.name?.let {
                ChargesPojo(
                    chargeName = it,
                    chargeAmount = /*(price.value + deliveryMethodDataClass.price)*/1
                )
            }

            if (itemCharge != null) {
                list.add(itemCharge)
            }
            Log.d("ytytyt", "onPayClicked: ${uiState.value.phoneNumber}")

            //remmove this line
            //  navController.navigate(ShopsFrontDestination.PAYMENT_COMPLETE_ETA_DELIVERY)
            val checkout = deliveryLocation?.let {
                paymentPageUiState.selectedMethod?.name?.let { it1 ->
                    CheckoutDataClass(
                        deliveryLocation = it,
                        deliveryMethod = deliveryMethodDataClass?.method_name.toString(),
                        charges = list,
                        paymentMethod = it1,
                        paymentPhoneNumber = uiState.value.phoneNumber,/*"254794700294"*/
                        // delivery_person = 1
                    )
                }
            }

            /*  val res =
                dropyRepository.orderCheckOut(orderId = id.value, checkOut = checkout!!)

              if(res != null){
                  checkoutViewModel._orderPaymentPageUiState.update {
                      it.copy(pageLoading = false)
                  }
                  Log.d("ytytyt", "onPayClicked: ${res}")
              }else {
                  checkoutViewModel._orderPaymentPageUiState.update {
                      it.copy(pageLoading = false)
                  }
              }*/

            Log.d("mnyu", "onPayClicked: selected order->   ${app.selectedOrder.value}")

            if (uiState.value.number == 1) {
//                    try {
                val number = 3
                uiState.update {
                    it.copy(
                        number = number
                    )
                }


                val response = /*try {*/
//                    checkout?.let {
                    cartService.orderCheckOut(
                        paymentReq = app.selectedOrder.value!!
                    )
//                    }
                /*} catch (e: Exception) {
                    checkoutViewModel._orderPaymentPageUiState.update {
                        it.copy(
                            pageLoading = false
                        )
                    }
                    return@launch
                }*/

                Log.d(
                    "urhurhur",
                    "onPayClicked: Order id ->${id.value} order body -> ${checkout}"
                )
//                        try {
                val jsonResponse = Gson().toJson(response?.body())
                Log.d("TRIAL", jsonResponse)

                /*} catch (e: Exception) {
                    Log.d("TRIAL", "ERROR")
                }*/

                Log.d("hyhhyd", "onPayClicked: ${response?.body()}")

                if (response != null) {
                    if (response.isSuccessful) {
                        Log.d("hyhhyd", "onPayClicked: ${response.body()}")
                        checkoutViewModel._orderPaymentPageUiState.update {
                            it.copy(pageLoading = false)
                        }

                        for (i in 0..10){
                            Log.d("kolop", "onPayClicked: $i")
                            if (i == 9){
                                response.body()?.transactionId?.let {
                                    checkTransactionStatus(
                                        it,
                                        context,
                                        checkoutViewModel
                                    )
                                }
                            }
                            delay(1000)
                        }


                        /*                 checkoutViewModel._orderPaymentPageUiState.update {
                                             it.copy(
                                                 messageList = addMessage(
                                                     messageList = checkoutViewModel.orderPaymentPageUiState.value.messageList,
                                                     message = response.body()!!.message
                                                 ),
                                                 pageLoading = false
                                             )
                                         }*/
                        //  navController.navigate(ShopsFrontDestination.PAYMENT_COMPLETE_ETA_DELIVERY)
                        /*             Toast.makeText(
                                         context,
                                         "${response.body()!!.message}",
                                         Toast.LENGTH_SHORT
                                     ).show()*/
                    } else {
                        val errorMsg = response.errorBody()!!.string()
                        response.errorBody()?.close()
                        val errorResponse = gerResponseError(errorMsg)
                        // navController.navigate(ShopsFrontDestination.PAYMENT_COMPLETE_ETA_DELIVERY)

                        Log.d("hyhhyd", "onPayClicked: $errorResponse")
                        checkoutViewModel._orderPaymentPageUiState.update {
                            it.copy(pageLoading = false)
                        }
                        var errorMessages = emptyList<String>()
                        for (err in errorResponse.errors) {
                            errorMessages =
                                addErrorMessage(
                                    errorList = errorMessages,
                                    errorMessage = err
                                )
                        }
                        /*           checkoutViewModel._orderPaymentPageUiState.update {
                                       it.copy(
                                           errorList = errorMessages,
                                       )
                                   }
                                   checkoutViewModel._orderPaymentPageUiState.update {
                                       it.copy(
                                           pageLoading = false
                                       )
                                   }
                                   Toast.makeText(
                                       context,
                                       "${errorResponse}",
                                       Toast.LENGTH_SHORT
                                   ).show()*/
                        checkoutViewModel._orderPaymentPageUiState.update {
                            it.copy(
                                pageLoading = false
                            )
                        }

                    }
                } else {
                    checkoutViewModel._orderPaymentPageUiState.update {
                        it.copy(
                            pageLoading = false
                        )
                    }

                }
                /*} catch (e: Exception) {
                    checkoutViewModel._orderPaymentPageUiState.update {
                        it.copy(
                            pageLoading = false
                        )
                    }

                }*/
            }


            /*} catch (e: Exception) {
                checkoutViewModel._orderPaymentPageUiState.update {
                    it.copy(pageLoading = false)
                }

            }*/
            // Log.d("nihuoh", "onPayClicked: $res")
        }
        //}
    }

    fun checkTransactionStatus(
        transactionId: Int,
        context: Context,
        checkoutViewModel: CheckoutViewModel
    ) {
        viewModelScope.launch {


            transactionStatusUseCase(transactionId = transactionId)
                .flowOn(Dispatchers.IO)
                /*.catch { e ->
                    // handle exception
                }*/
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: ${result.data}")
                    when (result) {
                        is Resource.Success -> {

                            checkoutViewModel._orderPaymentPageUiState.update {
                                it.copy(
                                    pageLoading = false
                                )
                            }

                            if (result.data?.transaction_result_description.equals("The service request is processed successfully."))
                                completeOrder(
                                    context = context,
                                    checkoutViewModel = checkoutViewModel
                                )
                        }
                        is Resource.Loading -> {
                            checkoutViewModel._orderPaymentPageUiState.update {
                                it.copy(
                                    pageLoading = true
                                )
                            }
                        }
                        is Resource.Error -> {
                            checkoutViewModel._orderPaymentPageUiState.update {
                                it.copy(
                                    pageLoading = false
                                )
                            }

                        }
                    }

                }


        }


    }


    fun completeOrder(context: Context, checkoutViewModel: CheckoutViewModel) {
        viewModelScope.launch {

            val pos = app.shopOrders.size - 1

            val item = ProcessOrderReq(
                shop = app.shopOrders[pos].shop,
                customer = app.id.value,
                is_accepted = true,
                is_canceled = false,
                is_checked_out = true,
                is_completed = true,
                is_deleted = false,
                is_pending = false,
                reason_for_cancelling = "String"
            )

            Log.d("xcvqw", "onProcessOrderClicked: $item")
            processOrderUseCase.processOrder(
                item,
                app.shopOrders[0].id!!.toInt()
            )
                .flowOn(Dispatchers.IO)
                /*.catch { e ->
                    // handle exception
                }*/
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: ${result.data}")
                    when (result) {
                        is Resource.Success -> {
                            checkoutViewModel._orderPaymentPageUiState.update {
                                it.copy(
                                    pageLoading = false
                                )
                            }
                        }
                        is Resource.Loading -> {
                            checkoutViewModel._orderPaymentPageUiState.update {
                                it.copy(
                                    pageLoading = true
                                )
                            }
                        }
                        is Resource.Error -> {
                            checkoutViewModel._orderPaymentPageUiState.update {
                                it.copy(
                                    pageLoading = false
                                )
                            }

                        }
                    }

                }


        }


    }

    fun navigateAllocatingTruck(navController: NavController){
        viewModelScope.launch {
            navController.navigate(AppDestinations.ALLOCATING_TRUCKS)
        }
    }

    fun dismissDialog() {
        uiState.update {
            it.copy(
                show = false
            )
        }
    }

    fun openDialog() {
        uiState.update {
            it.copy(
                show = true
            )
        }
    }

    fun initiateStkPush() {
        TODO()
    }

    fun checkTransactionStatus() {
        TODO()
    }

    private fun validatePhoneNumber(): Boolean {
        var valid = true
        if (mpesaPaymentDialogUiState.value.phoneNumber === "") {
            valid = false
            val errorMessages = uiState.value.errorMessages + PageErrors.BLANK_PHONE_NUMBER_FIELD
            uiState.update { it.copy(errorMessages = errorMessages) }
        } else {
            if (mpesaPaymentDialogUiState.value.phoneNumber.length < 10) {
                valid = false
                val errorMessages = uiState.value.errorMessages + PageErrors.PHONE_NUMBER_TOO_SHORT
                uiState.update { it.copy(errorMessages = errorMessages) }
            } else {
                val enteredPhoneNumber = mpesaPaymentDialogUiState.value.phoneNumber
                val sliced =
                    enteredPhoneNumber.slice(enteredPhoneNumber.length - 9..enteredPhoneNumber.lastIndex)
                val phoneNumber = "254$sliced"
                uiState.update { it.copy(phoneNumber = phoneNumber) }
            }
        }
        return valid
    }
}