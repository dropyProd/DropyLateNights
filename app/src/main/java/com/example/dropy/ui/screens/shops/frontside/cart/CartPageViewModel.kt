package com.example.dropy.ui.screens.shops.frontside.cart

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.*
import com.example.dropy.network.models.PostCartResponse.PostCartResponse
import com.example.dropy.network.models.cart.GetCartResponse
import com.example.dropy.network.models.cart.GetCartResponseItem
import com.example.dropy.network.models.cart.cartequest.CartRequest
import com.example.dropy.network.repositories.cart.CartRepository
import com.example.dropy.network.use_case.cart.*
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsBacksideNavigation
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.commons.AddressDataClass
import com.example.dropy.ui.screens.notification.Constants
import com.example.dropy.ui.screens.notification.FcmMessageService
import com.example.dropy.ui.screens.shops.frontside.checkout.trackyourorder.TrackYourOrderViewModel
import com.example.dropy.ui.screens.shops.frontside.productpage.ProductPageViewModel
import com.example.dropy.ui.screens.shops.frontside.shopslandingpage.ShopsLandingPageViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import com.example.dropy.ui.screens.workers.SendNotificationWorker
import com.example.dropy.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


data class CartPageUiState(
    val orderList: List<GetCartResItem> = emptyList(),
    val myOrders: MutableList<ShopOrdersResponseItem> = mutableListOf(),
    val total: Int = 0,
    val temporderId: Int? = null,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class CartPageViewModel @Inject constructor(
    private val cartRepository: CartRepository,
//    private val dropyRepository: DropyRepository
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val postCartItemsUseCase: PostCartItemsUseCase,
    private val placeOrderUseCase: PlaceOrderUseCase,
    private val deleteOrderUseCase: DeleteOrderUseCase,
    private val app: DropyApp,
    private val processOrderUseCase: ProcessOrderUseCase
) : ViewModel() {

    private val uiState = MutableStateFlow(CartPageUiState())

    val cartPageUiState: StateFlow<CartPageUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null
    val current: MutableList<GetCartResItem> = mutableListOf()
    val reloadPage = mutableStateOf(true)
    val movedpage = mutableStateOf(false)
    val myOrdersC: MutableList<ShopOrdersResponseItem> = mutableListOf()

    fun setCartList() {
        val num = mutableStateOf(0)
        app.cartList.forEach {
            num.value += it.product?.product_price!!
        }

        uiState.update { state ->
            state.copy(
                orderList = app.cartList,
                total = num.value
            )
        }
    }


    suspend fun getCustomerCart(): GetCartResponse? {

        val state: MutableState<GetCartResponse?> = mutableStateOf(null)

//        appViewModel!!.appUiState.value.activeProfile?.id?.let {
//            val item = CartRequest(number_of_order_items = 5)
//            getCartItemsUseCase.getCartItems(it, item, appViewModel!!).flowOn(Dispatchers.IO)
//                .catch { e ->
//                    // handle exception
//                }
//                .collect { result ->
//                    // list of users from the network
//                    Log.d("uopopi", "getAllShops: $result")
//                    when (result) {
//                        is Resource.Success -> {
//
//                            if (result.data?.isEmpty() == false) {
//                                current.clear()
//                                result.data.forEachIndexed { index, getCartResponseItem ->
////                                    if (!current.contains(getCartResponseItem)) {
////                                        current.add(getCartResponseItem)
////
////                                        uiState.update { state ->
////                                            state.copy(
////                                                orderList = current
////                                            )
////                                        }
////
////                                        Log.d(
////                                            "TAAAG",
////                                            "getCustomerCart: uiLIst ${uiState.value.orderList.size}    templist ${current.size}"
////                                        )
////                                        Log.d("rrty", "getCustomerCart: $result")
////                                        /*       if (reloadPage.value) {
////                                                   reloadPage.value = false*/
////                                        //}
////                                    }
//                                }
//                                state.value = result.data
//                                uiState.update {
//                                    it.copy(pageLoading = false)
//                                }
//                            } else {
//                                uiState.update {
//                                    it.copy(
//                                        pageLoading = false,
//                                        orderList = listOf()
//                                    )
//                                }
//                            }
//
//
//                        }
//                        is Resource.Loading -> {
//                            uiState.update { it.copy(pageLoading = true) }
//                        }
//                        is Resource.Error -> {
//                            result.message?.let { message ->
//                                uiState.update {
//                                    it.copy(
//                                        pageLoading = false,
//                                        errorList = listOf(message)
//                                    )
//                                }
//                            }
//
//                        }
//                    }
//
//                }
//
//            Log.d("kolpp", "getCustomerCart: $it")
//
//
//        }
        return state.value
    }


    suspend fun onAddCartItemClicked(
        orderId: Int,
        productId: Int,
        shopHomePageViewModel: ShopHomePageViewModel? = null,
        shopsLandingPageViewModel: ShopsLandingPageViewModel? = null,
        productPageViewModel: ProductPageViewModel? = null
    ): PostCartResponse? {
        val state: MutableState<PostCartResponse?> = mutableStateOf(null)
        when {
            shopHomePageViewModel != null -> {
                shopHomePageViewModel.uiState.update { it.copy(pageLoading = true) }

            }
            shopsLandingPageViewModel != null -> {
                shopsLandingPageViewModel._uiState.update { it.copy(pageLoading = true) }

            }
            productPageViewModel != null -> {
                productPageViewModel.uiState.update { it.copy(pageLoading = true) }

            }
            else -> {
                uiState.update { it.copy(pageLoading = true) }
            }
        }


        reloadPage.value = true
//        appViewModel!!.appUiState.value.activeProfile?.id?.let {
        current.clear()
        postCartItemsUseCase.postCartItems(
            customerId = app.id.value.toString(),
            productId = productId,
            action = "add",
            appViewModel = appViewModel!!
        ).flowOn(Dispatchers.IO)
            .catch { e ->
                // handle exception
                when {
                    shopHomePageViewModel != null -> {
                        shopHomePageViewModel.uiState.update { it.copy(pageLoading = false) }

                    }
                    shopsLandingPageViewModel != null -> {
                        shopsLandingPageViewModel._uiState.update {
                            it.copy(
                                pageLoading = false
                            )
                        }

                    }
                    productPageViewModel != null -> {
                        productPageViewModel.uiState.update { it.copy(pageLoading = false) }

                    }
                    else -> {
                        uiState.update { it.copy(pageLoading = false) }
                    }
                }
            }
            .collect { result ->
                // list of users from the network
                Log.d("uopopi", "getAllShops: $result")
                when (result) {
                    is Resource.Success -> {

                        state.value = result.data

                        if (result.data?.resultCode?.equals(0) == true) {
                            val list = getCustomerCart()
                            /*          if (list?.isEmpty() == false) {
                                          appViewModel?.navigate(ShopsFrontDestination.CART)
                                      }*/
                            when {
                                shopHomePageViewModel != null -> {
                                    shopHomePageViewModel.uiState.update { it.copy(pageLoading = false) }

                                }
                                shopsLandingPageViewModel != null -> {
                                    shopsLandingPageViewModel._uiState.update {
                                        it.copy(
                                            pageLoading = false
                                        )
                                    }

                                }
                                productPageViewModel != null -> {
                                    productPageViewModel.uiState.update { it.copy(pageLoading = false) }

                                }
                                else -> {
                                    uiState.update { it.copy(pageLoading = false) }
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

//        }


        return state.value
    }

    fun onRemoveCartItemClicked(orderId: Int, productId: Int) {
        viewModelScope.launch {
            reloadPage.value = true
            uiState.update { it.copy(pageLoading = true) }

//            appViewModel!!.appUiState.value.activeProfile?.id?.let {
            current.clear()

//                //Remove deleted product by product id
//                current.remove(GetCartResponseItem(id = it))
//                current.remove()

            postCartItemsUseCase.postCartItems(
                customerId = app.id.value,
                productId = productId,
                action = "remove",
                appViewModel = appViewModel!!
            ).flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                    uiState.update { it.copy(pageLoading = false) }
                }
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: $result")
                    when (result) {
                        is Resource.Success -> {

                            if (result.data?.resultCode?.equals(0) == true) {
                                val list = getCustomerCart()
                                if (list?.isEmpty() == false) {
                                    appViewModel?.navigate(ShopsFrontDestination.CART)
                                }
                                uiState.update { it.copy(pageLoading = false) }
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

            //}
        }
    }

    fun onDeleteCartItemClicked(orderId: Int, productId: Int) {
        uiState.update { it.copy(pageLoading = true) }

        viewModelScope.launch {
            reloadPage.value = true
//            appViewModel!!.appUiState.value.activeProfile?.id?.let {
            postCartItemsUseCase.postCartItems(
                customerId = app.id.value,
                productId = productId,
                action = "delete",
                appViewModel = appViewModel!!
            ).flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                    uiState.update { it.copy(pageLoading = false) }
                }
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: $result")
                    when (result) {
                        is Resource.Success -> {

                            if (result.data?.resultCode?.equals(0) == true) {
                                val list = getCustomerCart()
                                if (list?.isEmpty() == false) {
                                    appViewModel?.navigate(ShopsFrontDestination.CART)
                                }
                                uiState.update { it.copy(pageLoading = false) }
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

//            }
        }
    }

    fun sortOrders(
        shopHomePageViewModel: ShopHomePageViewModel,
        cartPageViewModel: CartPageViewModel,
        context: Context
    ) {
        myOrdersC.clear()
        app.shopOrders.forEach {
            Log.d("ncbcd", "sortOrders: $it")

            if (it.customer.toString().equals(app.id.value)) {
                if (!myOrdersC.contains(it))
                    myOrdersC.add(it)
                it.order_number?.let { it1 -> app.setCartId(it1.toInt()) }
                uiState.update {
                    it.copy(
                        myOrders = myOrdersC
                    )
                }
            }
        }
        Log.d("ncbcd", "sortOrders: $myOrdersC")

        shopHomePageViewModel.getCartItemNew(cartPageViewModel, context = context)
    }

    fun placeOrder(orderId: Int, appViewModel: AppViewModel, context: Context) {
        viewModelScope.launch {
            if (appViewModel.appUiState.value.activeProfile?.id.toString() != "") {
                uiState.update { it.copy(pageLoading = true) }

                val item = PlaceOrderReq(
                    order_number = app.cartId.value.toString(),//cart id is order id which is linked to cart
                    shop = app.shopId.value,
                    customer = /*app.id.value*/appViewModel.appUiState.value.activeProfile?.id
                )

                placeOrderUseCase(placeOrderReq = item, appViewModel = appViewModel)
                    .flowOn(Dispatchers.IO)
                    .catch { e ->
                        // handle exception
                        uiState.update { it.copy(pageLoading = false) }
                        Toast.makeText(
                            context,
                            "Error encountered when placing order",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .collect { result ->
                        // list of users from the network
                        Log.d("uopopi", "getAllShops: ${result.data}")
                        when (result) {
                            is Resource.Success -> {

                                if (result.data != null) {
                                    val list = getCustomerCart()
                                    if (list?.isEmpty() == false) {
                                        appViewModel?.navigate(ShopsFrontDestination.CART)
                                        Log.d("ty", "placeOrder: $list ")
                                    }
                                    uiState.update { it.copy(pageLoading = false) }
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

    val checkoutcurrent: MutableList<GetCartResItem> = mutableListOf()

    fun checkoutOrderNew(
        type: String = "deliver",
        context: Context
    ) {
        viewModelScope.launch {
            if (uiState.value.myOrders.isNotEmpty()) {
                val item = ProcessOrderReq(
                    shop = uiState.value.myOrders[0].shop,
                    customer = app.id.value,
                    is_accepted = true,
                    is_canceled = false,
                    is_checked_out = true,
                    is_completed = false,
                    is_deleted = false,
                    is_pending = false,
                    reason_for_cancelling = "String"
                )

                val selectedOrder = PaymentReq(
                    user_id = app.id.value,
                    shop_id = uiState.value.myOrders[0].shop.toString(),
                    order_id = uiState.value.myOrders[0].id.toString(),
                    cart_id = uiState.value.myOrders[0].order_number.toString(),
                    amount = 1
                )

                app.setSelectedOrder(selectedOrder)

                Log.d("xcvqw", "onProcessOrderClicked: $item")
                processOrderUseCase.processOrder(
                    item,
                    uiState.value.myOrders[0].id!!
                )
                    .flowOn(Dispatchers.IO)
                    .catch { e ->
                        // handle exception
                        uiState.update { it.copy(pageLoading = false) }
                        Toast.makeText(
                            context,
                            "Error encountered when checking out order",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .collect { result ->
                        // list of users from the network
                        Log.d("uopopi", "getAllShops: ${result.data}")
                        when (result) {
                            is Resource.Success -> {
                                if (type.equals("deliver")) {
                                    appViewModel?.navigate(ShopsFrontDestination.ADD_DELIVERY_INFO)
                                } else {
//                    val item = AddressDataClass(
//                        latitude = res.shop_location?.latitude!!,
//                        longitude = res.shop_location?.longitude!!,
//                        placeName = res.shop_location?.place_name!!,
//                        placeId = res.shop_location?.place_id!!,
//                    )
//                    appViewModel!!.addAddress(item)
                                    appViewModel?.navigate(ShopsFrontDestination.ORDER_PAYMENT)
                                }
                                var TOPIC = "gogo"
                                if (appViewModel!!.appUiState.value.activeProfile != null) {
                                    SendNotificationWorker.topic = TOPIC
                                    SendNotificationWorker.userName =
                                        appViewModel!!.appUiState.value.activeProfile!!.name



                                    FcmMessageService.name =
                                        appViewModel!!.appUiState.value.activeProfile!!.name
                                    val title = "Process order"
                                    val message =
                                        "on friday the contribution amount is ${20}Ksh"

/*                                FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
                                    .addOnCompleteListener { task ->
                                        var msg = "not sent"
                                        if (task.isSuccessful) {
                                            msg = "sent"
                                            // The topic name can be optionally prefixed with "/topics/".
                                            // The topic name can be optionally prefixed with "/topics/".
                                            FirebaseMessaging.getInstance().token.addOnCompleteListener(
                                                OnCompleteListener { task ->
                                                    if (!task.isSuccessful) {
                                                        Log.w(
                                                            "koko",
                                                            "Fetching FCM registration token failed",
                                                            task.exception
                                                        )
                                                        return@OnCompleteListener
                                                    }
                                                    Log.w("koko", "${task.result}", task.exception)

                                                    // Get new FCM registration token
                                                    val token = task.result

                                                    val items = mutableStateOf("")
                                                    uiState.value.orderItemList.forEachIndexed { index, orderItemListItemDataClass ->
                                                        items.value += "${(index + 1)}   ${orderItemListItemDataClass.productName}   ${orderItemListItemDataClass.numberOfUnits}  ${orderItemListItemDataClass.price}\n"
                                                    }


                                                    sendPushNotification(
                                                        token = token,
                                                        authtoken = Constants.SERVER_KEY,
                                                        title = "Processed Order",
                                                        body = "Order ${id.value} has been processed successfully"
                                                    )
                                                })


                                        }
                                        Toast.makeText(context, "notification $msg", Toast.LENGTH_SHORT)
                                            .show()

                                        //  Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                    }*/
                                    /*.also {
                            PushNotification(
                                NotificationData(title = title, message = message),
                                TOPIC //limits which users to get the notification
                            ).also {
                                sendNotification(it)
                            }
                        }*/
                                }
                                /*    val uploadWorkRequest: WorkRequest =
                                        OneTimeWorkRequestBuilder<SendNotificationWorker>().setBackoffCriteria(
                                            BackoffPolicy.LINEAR,
                                            OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                                            TimeUnit.MILLISECONDS
                                        ).addTag("notification")
                                            *//* .setInputData(
                             workDataOf(
                                 "user" to user,
                                 "type" to type,
                             )
                         )*//*  //sending data in hashmap in key value pairs
                        .build()


                WorkManager
                    .getInstance(context)
                    .enqueue(uploadWorkRequest)*/

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


    fun checkoutOrder(
        item: GetCartResItem,
        shopHomePageViewModel: ShopHomePageViewModel,
        trackYourOrderViewModel: TrackYourOrderViewModel,
        context: Context,
        type: String = "deliver"
    ) {
        /*  checkoutViewModel._orderPaymentPageUiState.update {
              it.copy(
                  total = orderId
              )
          }*/

        uiState.update { it.copy(pageLoading = true) }
        item.id?.let { trackYourOrderViewModel.setorderId(it) }

        checkoutcurrent.clear()
        checkoutcurrent.add(item)



        viewModelScope.launch {
            movedpage.value = true
            //  dropyRepository.checkoutOrder()

            val res =
                /*item.shop?.id?.let { shopHomePageViewModel.getGetShopProducts(it.toString()) }*/
                null
            if (res != null) {
                shopHomePageViewModel.clearPolyLine()

                //ommiting reedundancy
//                trackYourOrderViewModel.setShopLatLng(
//                    LatLng(
//                        res.shop_location?.latitude!!,
//                        res.shop_location?.longitude!!
//                    )
//                )

                trackYourOrderViewModel.getPolylines(appViewModel!!)

                if (type.equals("deliver")) {
                    appViewModel?.navigate(ShopsFrontDestination.ADD_DELIVERY_INFO)
                } else {
//                    val item = AddressDataClass(
//                        latitude = res.shop_location?.latitude!!,
//                        longitude = res.shop_location?.longitude!!,
//                        placeName = res.shop_location?.place_name!!,
//                        placeId = res.shop_location?.place_id!!,
//                    )
//                    appViewModel!!.addAddress(item)
                    appViewModel?.navigate(ShopsFrontDestination.ORDER_PAYMENT)
                }
                uiState.update { it.copy(pageLoading = false) }
            }

        }
    }


    fun cancelOrder(orderId: Int, appViewModel: AppViewModel, context: Context) {
        viewModelScope.launch {
            //    uiState.update { it.copy(pageLoading = true) }
            cartRepository.cancelOrder(
                orderId = orderId,
                appViewModel = appViewModel,
                reasonForCanceling = ""
            ).flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                    uiState.update { it.copy(pageLoading = false) }
                    Toast.makeText(
                        context,
                        "Error encountered when cancelling order",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: $result")
                    when (result) {
                        is Resource.Success -> {

                            if (result.data?.resultCode?.equals(0) == true) {
                                val list = getCustomerCart()
                                if (list?.isEmpty() == false) {
                                    appViewModel?.navigate(ShopsFrontDestination.CART)
                                }
                                uiState.update { it.copy(pageLoading = false) }
                                Log.d("tftf", "cancelOrder: $result ")
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

    fun deleteOrder(orderId: Int, context: Context) {
        viewModelScope.launch {
            uiState.update { it.copy(pageLoading = true) }

            deleteOrderUseCase.deleteOrder(orderId = orderId, appViewModel!!).flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                    Toast.makeText(
                        context,
                        "Error encountered when deleting order",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: $result")
                    when (result) {
                        is Resource.Success -> {

                            if (result.data?.resultCode?.equals(0) == true) {
                                val list = getCustomerCart()
                                if (list?.isEmpty() == false) {
                                    appViewModel?.navigate(ShopsFrontDestination.CART)
                                }
                                uiState.update { it.copy(pageLoading = false) }
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