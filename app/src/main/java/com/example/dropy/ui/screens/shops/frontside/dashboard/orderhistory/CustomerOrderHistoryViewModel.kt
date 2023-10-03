package com.example.dropy.ui.screens.shops.frontside.dashboard.orderhistory

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.GetIndividualOrders.GetIndividualOrdersResItem
import com.example.dropy.network.models.commondataclasses.shopPojo
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.network.use_case.shops.frontside.GetCustomerOrdersUseCase
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.shops.frontside.customer.customerdashboard.orderhistory.CustomerOrderListItemDataClass
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.shops.backside.shopincomingorders.ShopIncomingOrdersViewModel
import com.example.dropy.ui.screens.waterMyOrder.WaterMyOrderViewModel
import com.example.dropy.ui.utils.Resource
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CustomerOrderHistoryUiState(
    val waterOrderList: List<GetIndividualOrdersResItem> = emptyList(),
    val orderList: List<CustomerOrderListItemDataClass> = emptyList(),
    val shopnameList: List<shopPojo> = emptyList(),
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class CustomerOrderHistoryViewModel @Inject constructor(
    private val shopFrontendRepository: ShopFrontendRepository,
    private val getCustomerOrdersUseCase: GetCustomerOrdersUseCase,
    private val app: DropyApp
) : ViewModel() {

    private val uiState = MutableStateFlow(CustomerOrderHistoryUiState())

    val customerHistoryUiState: StateFlow<CustomerOrderHistoryUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null


    fun clearList() {
        uiState.update { state -> state.copy(orderList = listOf(), pageLoading = true) }

    }

    fun getMyWaterOrders(){
        val list: MutableList<GetIndividualOrdersResItem> = mutableListOf()
        app.individualOrders.forEach {
            if (it.customer.id.equals(appViewModel!!.appUiState.value.activeProfile!!.id)){
                list.add(it)
            }
        }
        uiState.update { it.copy(waterOrderList = list) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getOrders(appHomePageViewModel: AppHomePageViewModel) {
        viewModelScope.launch {

            appViewModel!!.appUiState.value.activeProfile?.id?.let {
                getCustomerOrdersUseCase.getCustomerOrders(app.token.value, it)
                    .flowOn(
                        Dispatchers.IO
                    )
                    .catch { e ->
                        // handle exception
                        uiState.update {
                            it.copy(
                                pageLoading = false
                            )
                        }
                    }
                    .collect {

                            result ->
                        // list of users from the network
                        Log.d("uopopi", "getAllShops: $result")
                        when (result) {
                            is Resource.Success -> {
                                //emptying list each time a new query is being queried

                                val current: MutableList<CustomerOrderListItemDataClass> =
                                    mutableListOf()
                                val currentshopname: MutableList<shopPojo> = mutableListOf()

                                // list of users from the network
                                result.data?.response?.forEach {
                                    val body = it.id?.let { it1 ->

                                        val date = it.date_added.toString().toList()

                                        Log.d("huhuh", "getOrders: $date")

                                        val trimmedDate = mutableStateOf("")
                                        val trimmedTime = mutableStateOf("")


                                        date.forEachIndexed { index: Int, c: Char ->
                                            if (index in 0..9) {
                                                trimmedDate.value += c.toString()
                                            }

                                            if (index in 11..25) {
                                                trimmedTime.value += c.toString()
                                            }

                                        }

                                        /*           val firstApiFormat = DateTimeFormatter.ofPattern("EEE, MMM d, ''yy")
                                                           val formateddate = LocalDate.parse("${trimmedDate.value}" , firstApiFormat)


                                                           Log.d("koioi", "getOrders:${trimmedDate.value} ")
                                                           Log.d("koioi", "getOrders:${trimmedTime.value} ")*/

                                        it.total_price?.let { it2 ->
                                            it.is_paid?.let { it3 ->

                                                it.cust_location?.lat?.let { it4 ->
                                                    it.cust_location.long?.let { it6 ->
                                                        LatLng(
                                                            it4, it6
                                                        )
                                                    }
                                                }.let { it6 ->
                                                    it.number_of_items?.let { it4 ->
                                                        it6?.let { it7 ->
                                                            it.shop?.shop_location?.latitude?.let { it5 ->
                                                                it.shop.shop_location.longitude?.let { it8 ->
                                                                    LatLng(
                                                                        it5, it8
                                                                    )
                                                                }
                                                            }?.let { it8 ->
                                                                CustomerOrderListItemDataClass(
                                                                    orderId = it1,
                                                                    customerName = appViewModel!!.appUiState.value.activeProfile?.name.toString(),
                                                                    orderNumber = it.order_number.toString(),
                                                                    isProcessed = it.order_status.toString(),
                                                                    numberOfItems = it4,
                                                                    cost = it2,
                                                                    isPaid = it3,
                                                                    //  timeString = "${trimmedDate.value}\n${trimmedTime.value}",
                                                                    timeString = "${trimmedDate.value}",
                                                                    orderPackedbyShop = true,
                                                                    orderPickedbyRider = false,
                                                                    customerReceiveOrder = false,
                                                                    status = /*if (it.isProcessed) "picked" else*/ "picked",
                                                                    shopLocale = /*it.shop*/it8,
                                                                    customerLocale = /*it.shop*/it7,
                                                                    customerLocalename = /*it.shop*/it.cust_location?.place_name.toString(),
                                                                    shopid = it.shop.id,
                                                                    shop = it.shop
                                                                )
                                                            }
                                                        }
                                                    }
                                                }

                                            }
                                        }

                                    }

                                    appHomePageViewModel.uiState.value.popularShops.forEach { shop ->
                                        if (shop.id?.equals(it.shop) == true) {
                                            Log.d("juio", "CustomerOrderListItem: ${it.id.toString()}")
                                            val item = shop.shop_name?.let { it1 ->
                                                shop.shop_logo?.let { it2 ->
                                                    shopPojo(
                                                        shopname = it1,
                                                        shoplogo = it2
                                                    )
                                                }
                                            }
                                            item?.let { it1 -> currentshopname.add(it1) }
                                        }
                                    }

                                    if (!current.contains(body)) {
                                        if (body != null) {
                                            current.add(body)
                                        }
                                    }

                                    //when request is successful
                                    uiState.update { state ->
                                        state.copy(
                                            orderList = current,
                                            shopnameList = currentshopname,
                                            pageLoading = false
                                        )
                                    }

                                    Log.d("kokokp", "getOrders: ${uiState.value.orderList}")
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


            //when request is successful anyways


        }

    }


    fun updateOrders() {

    }

    fun onViewOrderReceipt(orderId: Int) {

    }

    fun onViewOrderStatus(orderId: Int, shopIncomingOrdersViewModel: ShopIncomingOrdersViewModel) {
        shopIncomingOrdersViewModel.setTempId(orderId)
        appViewModel?.navigate(ShopsFrontDestination.TRACK_YOUR_ORDER)
    }

    fun navigate(waterMyOrderViewModel: WaterMyOrderViewModel, getIndividualOrdersResItem: GetIndividualOrdersResItem){
        waterMyOrderViewModel.setOrder(getIndividualOrdersResItem)
        appViewModel?.navigate(AppDestinations.WATER_MY_ORDER)
    }

    fun onCompleteOrder(orderId: Int) {

    }

}