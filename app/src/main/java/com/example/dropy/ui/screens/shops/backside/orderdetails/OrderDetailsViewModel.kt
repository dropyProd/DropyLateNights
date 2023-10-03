package com.example.dropy.ui.screens.shops.backside.orderdetails

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.PlaceOrderReq
import com.example.dropy.network.models.ProcessOrderReq
import com.example.dropy.network.models.ShopOrdersResponseItem
import com.example.dropy.network.repositories.cart.CartRepository
import com.example.dropy.network.use_case.cart.CancelOrderUseCase
import com.example.dropy.network.use_case.cart.OrderDetailsUseCase
import com.example.dropy.network.use_case.cart.PlaceOrderUseCase
import com.example.dropy.network.use_case.cart.ProcessOrderUseCase
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsBacksideNavigation
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.shops.backside.dashboard.orders.orderdetails.OrderItemListItemDataClass
import com.example.dropy.ui.screens.notification.*
import com.example.dropy.ui.screens.workers.SendNotificationWorker
import com.example.dropy.ui.utils.Resource
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okio.IOException
import org.json.JSONObject
import javax.inject.Inject

data class OrderDetailsUiState(
    val orderItemList: List<OrderItemListItemDataClass> = emptyList(),
    val showReasonForCancelling: Boolean = false,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList(),
    val orderLocation: String = "",
    val customerName: String = "",
    val customerPhone: String? = null,
)

@HiltViewModel
class OrderDetailsViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val cancelOrderUseCase: CancelOrderUseCase,
    private val orderDetailsUseCase: OrderDetailsUseCase,
    private val processOrderUseCase: ProcessOrderUseCase,
    private val dropyApp: DropyApp,
    private val placeOrderUseCase: PlaceOrderUseCase
//    private val dropyRepository: DropyRepository
) : ViewModel() {

    private val uiState = MutableStateFlow(OrderDetailsUiState())
    val orderDetailsUiState: StateFlow<OrderDetailsUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    val orderItemsList: MutableList<OrderItemListItemDataClass> = mutableListOf()

    val id = mutableStateOf(0)
    val reset = mutableStateOf(false)

    fun setOrderId(orderId: Int, shopId:String,customerId: String,shopOrdersResponseItem: ShopOrdersResponseItem,navigate: Boolean = true) {
        id.value = orderId
        dropyApp.setCartId(shopOrdersResponseItem.order_number!!.toInt())
        dropyApp.setIncomingOrderId(orderId)
        dropyApp.setIncomingShopId(shopId)
        dropyApp.setIncomingCustomerId(customerId)
        dropyApp.setSelectedIncomingOrder(shopOrdersResponseItem)
        Log.d("huu", "setOrderId: ${id.value}")
        if (navigate) {
            appViewModel?.navigate(ShopsBacksideNavigation.ORDER_DETAILS)
        }
    }

    fun reasonForCancellingChange(state: Boolean) {
        uiState.update {
            it.copy(
                showReasonForCancelling = state
            )
        }
    }

    fun changereset() {
        reset.value = true
    }

    fun setIncomingCartItems() {
        viewModelScope.launch {
            val list: MutableList<OrderItemListItemDataClass> = mutableListOf()
            dropyApp.cartList.forEach { item ->
                val obj = item.quantity?.toInt()?.let {
                    item.product?.product_price?.let { it1 ->
                        OrderItemListItemDataClass(
                            productImageUrl = item.product?.product_cover_photo.toString(),
                            isAvailable = true,
                            numberOfUnits = it,
                            price = it1,
                            productName = item.product?.product_name.toString()
                        )
                    }
                }

                if (obj != null) {
                    list.add(obj)
                }
            }
            uiState.update {
                it.copy(
                    orderItemList = list
                )
            }
        }
    }

    fun getOrderDetails(): orderRes {
        /*   appViewModel.appUiState.value.orderList.forEach {
               it.cartItems.forEach { cart ->
                   val item = OrderItemListItemDataClass(
                       productImageUrl = "",
                       productName = cart.productName,
                       numberOfUnits = cart.cartItemsNumber,
                       price = cart.cartItemTotal,
                       isAvailable = true
                   )

                   orderItemsList.add(item)
                   uiState.update { state -> state.copy(orderItemList = orderItemsList) }

               }
           }

           uiState.update {
               it.copy(
                   orderLocation = checkoutViewModel.deliveryInformationUiState.value.currentAddress?.placeName.toString(),
                   customerName = name
               )
           }
   */
        val total = mutableStateOf(0)
        viewModelScope.launch {

            uiState.update { it.copy(pageLoading = true) }
            Log.d("gytre", "getOrderDetails: ${id.value}")

            orderDetailsUseCase.orderDetails(id.value).flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                }
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: $result")
                    when (result) {
                        is Resource.Success -> {

                            val orderItems = mutableListOf<OrderItemListItemDataClass>()

                            if (result.data != null) {
                                for (item in result.data.order_details.order_items) {
                                    val orderItem = OrderItemListItemDataClass(
                                        productImageUrl = item.product.product_image_url,
                                        productName = item.product.product_name,
                                        price = item.product.product_price,
                                        numberOfUnits = item.quantity,
                                        isAvailable = item.availability
                                    )
                                    orderItems.add(orderItem)
                                }

                                uiState.value.orderItemList.forEach {
                                    total.value += it.price
                                }

                                uiState.update {
                                    it.copy(
                                        orderItemList = orderItems,
                                        customerName = result.data.order_details.customer.customer_name,
                                        customerPhone = result.data.order_details.customer.customer_phone
                                    )
                                }
                            }
                            uiState.update { it.copy(pageLoading = false) }


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



        return orderRes(
            orderSize = uiState.value.orderItemList.size,
            orderTotal = total.value
        )
    }

    fun markItemAsAvailable(index: Int) {

    }

    fun markItemAsUnavailable(index: Int) {

    }

    fun onCallCustomerClicked(context: Context) {
        val dialIntent = Intent(Intent.ACTION_CALL)
        dialIntent.data = Uri.parse("tel:" + "0712345678")
        context.startActivity(dialIntent)
    }

    val processcurrent: MutableList<OrderItemListItemDataClass> = mutableListOf()


    fun onProcessOrderClicked(context: Context) {
        viewModelScope.launch {
            val item = ProcessOrderReq(
                shop = dropyApp.incomingShopId.value,
                customer = dropyApp.incomingCustomerId.value,
                is_accepted = true,
                is_canceled = false,
                is_checked_out = false,
                is_completed = false,
                is_deleted = false,
                is_pending = false,
                reason_for_cancelling = "String"
            )

            Log.d("xcvqw", "onProcessOrderClicked: $item")
            processOrderUseCase.processOrder(item,dropyApp.incomingOrderId.value!!.toInt())
                .flowOn(Dispatchers.IO)
                /*.catch { e ->
                    // handle exception
                }*/
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: ${result.data}")
                    when (result) {
                        is Resource.Success -> {
                            appViewModel!!.navigate(ShopsBacksideNavigation.INCOMING_ORDERS)
                            var TOPIC = "gogo"
                            if(appViewModel!!.appUiState.value.activeProfile != null){
                                SendNotificationWorker.topic = TOPIC
                                SendNotificationWorker.userName =
                                    appViewModel!!.appUiState.value.activeProfile!!.name



                                FcmMessageService.name =
                                    appViewModel!!.appUiState.value.activeProfile!!.name
                                val title = "Process order"
                                val message =
                                    "on friday the contribution amount is ${20}Ksh"

                                FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
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
                                    }
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

    fun sendPushNotification(
        token: String,
        authtoken: String,
        title: String = "kook",
        subtitle: String = "ijjoiji",
        body: String = "okojij",
        data: Map<String, String> = emptyMap()
    ) {
        val url = "https://fcm.googleapis.com/fcm/send"

        val bodyJson = JSONObject()
        bodyJson.put("to", token)
        bodyJson.put("notification",
            JSONObject().also {
                it.put("title", title)
                it.put("subtitle", subtitle)
                it.put("body", body)
                it.put("sound", "social_notification_sound.wav")
            }
        )
        // bodyJson.put("data", JSONObject(data))

        val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "key=$authtoken")
            .post(
                bodyJson.toString().toRequestBody("application/json; charset=utf-8".toMediaType())
            )
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(
            object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    //println("Received data: ${response.body?.string()}")

                    Log.d("pllp", "onResponse: ${response.body?.string()}")
                }

                override fun onFailure(call: Call, e: IOException) {
                    println(e.message.toString())
                }
            }
        )
    }

    fun onCancelOrderClicked(context: Context) {
        viewModelScope.launch {
            uiState.update { it.copy(pageLoading = true) }

            val item = ProcessOrderReq(
                shop = dropyApp.incomingShopId.value,
                customer = dropyApp.incomingCustomerId.value,
                is_accepted = false,
                is_canceled = true,
                is_checked_out = false,
                is_completed = false,
                is_deleted = false,
                is_pending = false,
                reason_for_cancelling = "String"
            )

            Log.d("xcvqw", "onProcessOrderClicked: $item")
            processOrderUseCase.processOrder(item,dropyApp.incomingOrderId.value!!.toInt())
                .flowOn(Dispatchers.IO)
                /*.catch { e ->
                    // handle exception
                }*/
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: ${result.data}")
                    when (result) {
                        is Resource.Success -> {
                            appViewModel!!.navigate(ShopsBacksideNavigation.INCOMING_ORDERS)
                            var TOPIC = "gogo"
                            if(appViewModel!!.appUiState.value.activeProfile != null){
                                SendNotificationWorker.topic = TOPIC
                                SendNotificationWorker.userName =
                                    appViewModel!!.appUiState.value.activeProfile!!.name



                                FcmMessageService.name =
                                    appViewModel!!.appUiState.value.activeProfile!!.name
                                val title = "Process order"
                                val message =
                                    "on friday the contribution amount is ${20}Ksh"

                                FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
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
                                    }
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

    fun sendNotification(notification: PushNotification) =
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val response = RetrofitInstance.api.postNotification(notification = notification)

                if (response.isSuccessful) {
                    Log.d("TAG", "sendNOtification: ${Gson().toJson(response)}")
                } else {
                    Log.d("TAG", "sendNOtification: ${response.errorBody().toString()}")
                }

            } catch (e: Exception) {
                Log.e("TAG", "sendNOtification: ${e.toString()} ")
            }
        }


}

data class orderRes(
    val orderSize: Int,
    val orderTotal: Int,
)