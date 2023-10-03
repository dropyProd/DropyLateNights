package com.example.dropy.ui.screens.shops.backside.shopincomingorders

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.repositories.shop.back.ShopBackendRepository
import com.example.dropy.network.use_case.shops.backside.GetShopCompletedOrdersUseCase
import com.example.dropy.network.use_case.shops.backside.GetShopPendingOrdersUseCase
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsBacksideNavigation
import com.example.dropy.ui.components.shops.backside.dashboard.orders.orderlist.OrderListItemDataClass
import com.example.dropy.ui.utils.Resource
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

data class ShopIncomingOrdersUiState(
    val orderList: List<OrderListItemDataClass> = emptyList(),
    val completedorderList: List<OrderListItemDataClass> = emptyList(),
    val ordersTotal: Int = 0,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList(),

    )

@HiltViewModel
class ShopIncomingOrdersViewModel @Inject constructor(
    private val shopBackendRepository: ShopBackendRepository,
    private val getShopPendingOrdersUseCase: GetShopPendingOrdersUseCase,
    private val getShopCompletedOrdersUseCase: GetShopCompletedOrdersUseCase,
    private val app: DropyApp
) : ViewModel() {

    private val uiState = MutableStateFlow(ShopIncomingOrdersUiState())

    val shopIncomingOrdersUiState: StateFlow<ShopIncomingOrdersUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null

    /*   init {
           getPendingOrders()
       }*/

    val list = listOf("noman", "kama", "roy", "kings", "pol", "mary", "jane")
    val changelist = listOf("DELIVERY", "TO COLLECT")

    val name = Random.nextInt(0, 6)
    val id = Random.nextInt(0, 50)

    val add: MutableState<OrderListItemDataClass?> = mutableStateOf(null)

    fun getPendingOrders(
        context: Context
    ) {
        viewModelScope.launch {
            uiState.update { it.copy(pageLoading = true) }
            val orders: MutableList<OrderListItemDataClass> = mutableListOf()

            val total = mutableStateOf(0)
            Log.d(
                "noiuo",
                "getPendingOrders: ${appViewModel?.appUiState?.value?.activeProfile?.id}"
            )
            getShopPendingOrdersUseCase.getShopPendingOrders(
                app.token.value,
                appViewModel?.appUiState?.value?.activeProfile?.id.toString()
            )
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                    uiState.update { it.copy(pageLoading = false) }
                    Toast.makeText(
                        context,
                        "An error occured when getting customer pending orders",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: $result")
                    when (result) {
                        is Resource.Success -> {
                            orders.clear()
                            result.data?.forEach {
                                Log.d("joiuy", "getPendingOrders: ${it}")
                            }
                            if (result.data != null) {
                                app.clearShopOrder()
                                result.data.forEach { item ->
                                    /*val order = item.id?.let {
                                        item.order_number?.let { it1 ->
                                            item.pending_processing?.let { it2 ->
                                                item.order_status?.let { it3 ->
                                                    item.order_total?.let { it4 ->
                                                        item.is_paid?.let { it5 ->
                                                            item.shop_lat?.let { it6 ->
                                                                item.shop_lon?.let { it7 ->
                                                                    LatLng(
                                                                        it6,
                                                                        it7
                                                                    )
                                                                }
                                                            }
                                                                ?.let { it7 ->

                                                                    OrderListItemDataClass(
                                                                        orderId = it,
                                                                        orderNumber = it1,
                                                                        customerName = item.customer_name.toString(),
                                                                        isProcessed = (!it2),
                                                                        numberOfItems = 2,
                                                                        status = it3,
                                                                        cost = it4,
                                                                        isPaid = it5,
                                                                        shopLocale = it7,
                                                                        deliveryLocale = if (item.delivery_location?.equals(
                                                                                null
                                                                            ) == true
                                                                        ) {
                                                                            item.delivery_location?.lat?.let { it6 ->
                                                                                item.delivery_location?.long?.let { it8 ->
                                                                                    LatLng(
                                                                                        it6, it8
                                                                                    )
                                                                                }
                                                                            }
                                                                        } else null,
                                                                        deliveryplacename = item.delivery_location?.place_name.toString()
                                                                    )

                                                                }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }*/

                                    app.setShopOrder(item)

                                    val order = item.order_number?.let {
                                        item.id?.let { it1 ->
                                            OrderListItemDataClass(
                                                orderNumber = it,
                                                cost = 100,
                                                customerName = "lo",
                                                numberOfItems = Random.nextInt(5, 13),
                                                orderId = it1,
                                                status = "Processing",
                                                shopId = item.shop.toString(),
                                                customerId = item.customer.toString(),
                                                shopOrdersResponseItem = item,
                                                isProcessed = item.is_accepted!!
                                            )
                                        }
                                    }
                                    if (!orders.contains(order)) {
                                        if (order != null) {
                                            orders.add(order)
                                        }
                                        total.value += /*item.order_total!!*/1
                                    }
                                }
                            }
                            uiState.update {
                                it.copy(
                                    orderList = orders,
                                    ordersTotal = total.value
                                )
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


        /*     val simpleDate = SimpleDateFormat("hh:mm")
             val currentDate = simpleDate.format(Date())
             add.value = OrderListItemDataClass(
                 orderNumber = (id).toString(),
                 orderId = Random.nextInt(0, 20),
                 customerName = list[name],
                 cost = checkoutViewModel._orderPaymentPageUiState.value.orderCost,
                 numberOfItems = appViewModel.appUiState.value.orderList.size,
                 status = changelist[Random.nextInt(0, 1)],
                 timeString = currentDate
             )
             orders.clear()
             orders.add(add.value!!)
             uiState.update { it.copy(orderList = orders) }

             orderDetailsViewModel.getOrderItems(appViewModel, checkoutViewModel, list[name])
             incomingJobViewModel.setCustomerName(list[name])
             incomingJobViewModel.setOrderId(id = id)*/

    }

    fun getCompletedOrders(
        shopid: String
    ) {
        viewModelScope.launch {
            uiState.update { it.copy(pageLoading = true) }
            val total = mutableStateOf(0)
            val orders: MutableList<OrderListItemDataClass> = mutableListOf()

            Log.d("noiuo", "getPendingOrders: ${shopid}")
            getShopCompletedOrdersUseCase.getShopCompletedOrders(app.token.value, shopid)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                }
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: $result")
                    when (result) {
                        is Resource.Success -> {
                            orders.clear()
                            Log.d("joiuy", "getPendingOrders: $result")
                            if (result.data != null) {
                                result.data.done_orders?.forEach { item ->
                                    val order = item.id?.let {
                                        item.order_number?.let { it1 ->
                                            item.pending_processing?.let { it2 ->
                                                item.order_status?.let { it3 ->
                                                    item.order_total?.let { it4 ->
                                                        item.is_paid?.let { it5 ->
                                                            item.shop_lat?.let { it6 ->
                                                                item.shop_lon?.let { it7 ->
                                                                    LatLng(
                                                                        it6,
                                                                        it7
                                                                    )
                                                                }
                                                            }
                                                                ?.let { it7 ->

                                                                    OrderListItemDataClass(
                                                                        orderId = it,
                                                                        orderNumber = it1,
                                                                        customerName = /*item.customer_name.toString()*/"",
                                                                        isProcessed = (!it2),
                                                                        numberOfItems = 2,
                                                                        status = it3,
                                                                        cost = it4,
                                                                        isPaid = it5,
                                                                        shopLocale = it7,
                                                                        deliveryLocale = /*if (item.delivery_location?.equals(
                                                                null
                                                            ) == true
                                                        ) {
                                                            item.delivery_location?.lat?.let { it6 ->
                                                                item.delivery_location?.long?.let { it8 ->
                                                                    LatLng(
                                                                        it6, it8
                                                                    )
                                                                }
                                                            }
                                                        }else*/ null,
                                                                        deliveryplacename = /*item.delivery_location?.place_name.toString()*/""
                                                                    )

                                                                }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (!orders.contains(order)) {
                                        if (order != null) {
                                            orders.add(order)
                                        }
                                        total.value += item.order_total!!
                                    }
                                }
                            }
                            uiState.update {
                                it.copy(
                                    completedorderList = orders,
                                    ordersTotal = total.value
                                )
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


        /*     val simpleDate = SimpleDateFormat("hh:mm")
             val currentDate = simpleDate.format(Date())
             add.value = OrderListItemDataClass(
                 orderNumber = (id).toString(),
                 orderId = Random.nextInt(0, 20),
                 customerName = list[name],
                 cost = checkoutViewModel._orderPaymentPageUiState.value.orderCost,
                 numberOfItems = appViewModel.appUiState.value.orderList.size,
                 status = changelist[Random.nextInt(0, 1)],
                 timeString = currentDate
             )
             orders.clear()
             orders.add(add.value!!)
             uiState.update { it.copy(orderList = orders) }

             orderDetailsViewModel.getOrderItems(appViewModel, checkoutViewModel, list[name])
             incomingJobViewModel.setCustomerName(list[name])
             incomingJobViewModel.setOrderId(id = id)*/

    }


    val temporaryId = mutableStateOf(0)

    fun onOrderSelected(orderId: Int) {
        setTempId(orderId)
        appViewModel?.navigate(ShopsBacksideNavigation.ORDER_DETAILS)
    }

    fun setTempId(orderId: Int) {
        temporaryId.value = orderId
    }

    fun editOrder() {
        val orders: MutableList<OrderListItemDataClass> = mutableListOf()

        val edit = add.value?.copy(isProcessed = true, orderPackedbyShop = true)
        orders.forEach {
            if (it.orderId.equals(temporaryId)) {
                if (orders.contains(it)) {
                    orders.remove(it)
                }
                uiState.update { it.copy(orderList = orders) }
            }
        }
        if (!orders.contains(edit)) {
            orders.add(edit!!)
        }
        uiState.update { it.copy(orderList = orders) }
    }

    fun orderAcceptedRider(orderId: Int) {
        val orders: MutableList<OrderListItemDataClass> = mutableListOf()

        val edit = add.value?.copy(orderPickedbyRider = true)
        orders.forEach {
            if (it.orderId.equals(orderId)) {
                if (orders.contains(it)) {
                    orders.remove(it)
                }
                uiState.update { it.copy(orderList = orders) }
            }
        }
        if (!orders.contains(edit)) {
            orders.add(edit!!)
        }
        uiState.update { it.copy(orderList = orders) }

    }

}