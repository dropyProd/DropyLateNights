package com.example.dropy.ui.app

import android.R
import android.location.Location
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.UserDetailsBody
import com.example.dropy.network.models.cart.OrderItem
import com.example.dropy.network.models.getWaterPoints.GetWaterPointsResItem
import com.example.dropy.network.models.getWaterTrucks.GetTrucksResItem
import com.example.dropy.network.models.getWaterVendors.GetWaterVendorsResItem
import com.example.dropy.network.services.AuthenticationApiService
import com.example.dropy.network.use_case.authentication.RegisteruserUseCase
import com.example.dropy.network.use_case.getIndividualOrders.GetIndividualOrdersUseCase
import com.example.dropy.network.use_case.getWaterTrucks.GetWaterTrucksUseCase
import com.example.dropy.network.use_case.getWaterVendors.GetWaterVendorsUseCase
import com.example.dropy.network.use_case.getWaterpoint.GetWaterPointsUseCase
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsBacksideNavigation
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.commons.AddressDataClass
import com.example.dropy.ui.components.commons.appdrawer.ActiveProfileDataClass
import com.example.dropy.ui.components.commons.appdrawer.DrawerMenuItem
import com.example.dropy.ui.components.commons.appdrawer.ProfileTypes
import com.example.dropy.ui.components.shops.frontside.customer.orderprocess.customercart.CartItemDataClass
import com.example.dropy.ui.components.shops.frontside.customer.orderprocess.customercart.OrderDetailsDataClass
import com.example.dropy.ui.utils.Resource
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


val customerMenu = listOf(
    DrawerMenuItem(
        title = "My dashboard",
        route = ShopsFrontDestination.CUSTOMER_DASHBOARD
    ),
    DrawerMenuItem(
        title = "My addresses",
        route = ShopsFrontDestination.CUSTOMER_ADDRESS_BOOK,
    ),
    DrawerMenuItem(
        title = "My orders",
        route = ShopsFrontDestination.CUSTOMER_ORDER_HISTORY,
    ),
    DrawerMenuItem(
        title = "Create shop",
        route = ShopsBacksideNavigation.ADD_SHOP,
    ),
    DrawerMenuItem(
        title = "Become a rider",
        route = RiderBackside.ADDRIDER,
    ),

    )

val waterPointMenu = listOf(
    DrawerMenuItem(
        title = "My dashboard",
        route = AppDestinations.WATERPOINT_DASH
    ),
    DrawerMenuItem(
        title = "New Order",
        route = AppDestinations.WATERPOINT_NEW_ORDER
    ),
    DrawerMenuItem(
        title = "My orders",
        route = AppDestinations.WATERPOINT_ORDERS,
    ),
    DrawerMenuItem(
        title = "Create waterpoint",
        route = AppDestinations.CREATE_WATERPOINT,
    )
)

val waterVendorMenu = listOf(
    DrawerMenuItem(

        title = "My dashboard",
        route = AppDestinations.WATER_VENDOR_DASHBOARD

    ),
    DrawerMenuItem(
        title = "My orders",
        route = ShopsFrontDestination.CUSTOMER_ORDER_HISTORY,
    ),
    DrawerMenuItem(
        title = "Create truck company",
        route = AppDestinations.CREATE_WATERVENDOR,
    ),
    DrawerMenuItem(
        title = "Create watertruck",
        route = AppDestinations.CREATE_WATERTRUCK,
    )
)
val waterTruckMenu = listOf(
    DrawerMenuItem(
        title = "My dashboard",
        route = AppDestinations.TRUCK_DASHBOARD
    ),
    DrawerMenuItem(
        title = "My orders",
        route = AppDestinations.TRUCK_ORDERS_HISTORY,
    ),
    DrawerMenuItem(
        title = "Find Job",
        route = AppDestinations.TRUCK_FIND_JOB,
    )
)


val shopMenu = listOf(
    DrawerMenuItem(
        title = "Shop dashboard",
        route = ShopsBacksideNavigation.SHOP_DASHBOARD,
    ),
    DrawerMenuItem(
        title = "Incoming orders",
        route = ShopsBacksideNavigation.INCOMING_ORDERS,
    ),
    DrawerMenuItem(
        title = "My products",
        route = ShopsBacksideNavigation.SHOP_PRODUCTS,
    ),
)
val riderMenu = listOf(
    DrawerMenuItem(
        title = "Rider dashboard",
        route = RiderDestination.RIDERDASHBOARD,
    ),
    DrawerMenuItem(
        title = "Incoming jobs",
        route = RiderDestination.RIDERINCOMINGORDERS,
    ),
)


data class AppUiState(
    val firebaseUid: String? = null,
    val appLoading: Boolean = false,
    val activeProfile: ActiveProfileDataClass? = null,
    val globalDropyUserId: Int? = null,
    val riderId: Int? = null,
    val isRider: Boolean? = null,
    val myAddress: AddressDataClass? = null,
    val yourlocation: LatLng? = null,
    val userProfiles: List<ActiveProfileDataClass> = emptyList(),
    val drawerMenuItems: List<DrawerMenuItem> = emptyList(),
    val orderList: List<OrderItem> = emptyList(),
    val showBackButton: Boolean = true,
    val showCartButton: Boolean = true,
)

@HiltViewModel
class AppViewModel @Inject constructor(
    private val authenticationApiService: AuthenticationApiService,
    private val app: DropyApp,
    private val registeruserUseCase: RegisteruserUseCase,
    private val getWaterPointsUseCase: GetWaterPointsUseCase,
    private val getWaterVendorsUseCase: GetWaterVendorsUseCase,
    private val getIndividualOrdersUseCase: GetIndividualOrdersUseCase,
    private val getWaterTrucksUseCase: GetWaterTrucksUseCase
) : ViewModel(), LifecycleObserver {

    private val uiState = MutableStateFlow(AppUiState())

    val appUiState: StateFlow<AppUiState> = uiState.asStateFlow()
    var navHostController: NavHostController? = null
    var systemUiController: SystemUiController? = null

    init {
        getLoggedInUser()
    }


    fun setYourLocale(latLng: LatLng) {
        uiState.update {
            it.copy(
                yourlocation = latLng
            )
        }
        Log.d("huuhu", "setYourLocale: $latLng")
    }

    private fun getLoggedInUser() {
        //val user = Firebase.auth.currentUser
        //  if (user != null) {
        //uiState.update { it.copy(firebaseUid = user.uid) }
        getUserDetails()
        //}
    }

    val current = mutableListOf<CartItemDataClass>()
    val currentOrder = mutableListOf<OrderDetailsDataClass>()
    val orderTotal = mutableStateOf(0)

    val location: MutableState<Location?> = mutableStateOf(null)

    fun setLocation(locationn: Location) {
        location.value = locationn
        Log.d("SSSSDG", "setLocation: ${location.value!!.latitude} ${location.value!!.longitude}")
    }

    fun addAddress(addressDataClass: AddressDataClass) {
        uiState.update {
            it.copy(
                myAddress = addressDataClass
            )
        }
        Log.d("uhhuh", "addAddress: ${uiState.value.myAddress}")
    }

    fun getCartItems() {

    }

    fun addItemCart(item: CartItemDataClass) {
        //     dropyRepository.g

        /*      var doesnotContainCurrent = false

              if (current.isEmpty()) {
                  doesnotContainCurrent = true
              }

              current.forEach {
                  if (!it.productName.equals(item.productName)) {
                      doesnotContainCurrent = true
                  }
              }

              if (doesnotContainCurrent) {
                  current.add(item)
              }

              orderTotal.value = 0
              current.forEach {
                  orderTotal.value += it.cartItemTotal
              }

              val orderDetailsDataClass = OrderDetailsDataClass(
                  orderId = 1,
                  shopName = "random",
                  cartItems = current,
                  subTotal = orderTotal.value,
                  netTotal = orderTotal.value
              )
              val isnotthere = mutableStateOf(false)
              if (currentOrder.isEmpty()) {
                  isnotthere.value = true
              }
              currentOrder.forEach {
                  if (!it.shopName.equals(orderDetailsDataClass.shopName)) {
                      isnotthere.value = true
                  }
              }
              if (isnotthere.value) {
                  currentOrder.add(orderDetailsDataClass)
              }
              uiState.update {
                  it.copy(
                      orderList = currentOrder
                  )
              }*/
    }

    fun editCartList(
        currentitem: CartItemDataClass,
        editeditem: CartItemDataClass,
        currentoOrder: OrderDetailsDataClass
    ) {

        if (current.contains(currentitem)) {
            current.remove(currentitem)
            current.add(editeditem)
        }

        val editedorder = currentoOrder.copy(
            cartItems = current
        )
        if (currentOrder.contains(currentoOrder)) {
            currentOrder.remove(currentoOrder)
            currentOrder.add(editedorder)
        }

        /*    uiState.update {
                it.copy(
                    orderList = currentOrder
                )
            }*/

    }

    fun editOrder(currentoOrder: OrderDetailsDataClass, editedOrder: OrderDetailsDataClass) {
        if (!currentOrder.isEmpty()) {
            currentOrder.remove(currentoOrder)
            currentOrder.add(editedOrder)
            /*     uiState.update {
                     it.copy(
                         orderList = currentOrder
                     )
                 }*/
        }
    }

    fun deleteOrder(currentitem: CartItemDataClass, currentoOrder: OrderDetailsDataClass) {

        if (current.contains(currentitem)) {
            current.remove(currentitem)
        }
        val editedorder = currentoOrder.copy(
            cartItems = current
        )
        if (currentOrder.contains(currentoOrder)) {
            currentOrder.remove(currentoOrder)
            currentOrder.add(editedorder)
        }

        /*    uiState.update {
                it.copy(
                    orderList = currentOrder
                )
            }*/

    }

    fun setUserProfiles() {

        val list: MutableList<ActiveProfileDataClass> = mutableListOf()

        appUiState.value.userProfiles.forEach {
            if (!list.contains(it))
                list.add(it)
        }

        app.myShopsList.forEach {
            val item = ActiveProfileDataClass(
                type = ProfileTypes.SHOP,
                name = it.shop_name.toString(),
                id = it.id.toString()
            )

            list.add(item)

            uiState.update {
                it.copy(
                    userProfiles = list
                )
            }
        }
    }

    fun setUserType(type: String) {
        when {
            type.equals("Customer") -> {
                val item = ActiveProfileDataClass(
                    type = ProfileTypes.CUSTOMER,
                )
                uiState.update {
                    it.copy(
                        activeProfile = item
                    )
                }
            }
            type.equals("Logistics Truck Express") -> {
                val item = ActiveProfileDataClass(
                    type = ProfileTypes.LOGISTICSTREXPRESS,
                )
                uiState.update {
                    it.copy(
                        activeProfile = item
                    )
                }
            }
            type.equals("TukTuk Rider") -> {
                val item = ActiveProfileDataClass(
                    type = ProfileTypes.TUKTUKRIDER,
                )
                uiState.update {
                    it.copy(
                        activeProfile = item
                    )
                }
            }
            type.equals("Riders Express") -> {
                val item = ActiveProfileDataClass(
                    type = ProfileTypes.RIDEREXPRESS,
                )
                uiState.update {
                    it.copy(
                        activeProfile = item
                    )
                }
            }
            type.equals("Couriers Express") -> {
                val item = ActiveProfileDataClass(
                    type = ProfileTypes.COURIEREXPRESS,
                )
                uiState.update {
                    it.copy(
                        activeProfile = item
                    )
                }
            }
            type.equals("Logistics Truck Drivers") -> {
                val item = ActiveProfileDataClass(
                    type = ProfileTypes.COURIEREXPRESS,
                )
                uiState.update {
                    it.copy(
                        activeProfile = item
                    )
                }
            }
        }
    }

    fun getUserDetails() {

        val profiles = mutableListOf<ActiveProfileDataClass>()

        appUiState.value.userProfiles.forEach {
            if (!profiles.contains(it))
                profiles.add(it)
        }

        viewModelScope.launch {
            /* val client = HttpClient(CIO)



             val response: HttpResponse = *//*try {*//*
                client.get("https://api.dropy.co.ke/dropyusers/getuserdetails?firebase_uid=${firebaseUid}")*//*{
                    url {
                        parameters.append("firebase_uid", firebaseUid)
                    }
                }*//*
         *//*   } catch (e: Exception) {
                Log.d("TAG", "getAllShopCategories: Error")
                return null
            }*//*

            if (response.status == HttpStatusCode.OK) {
                val raw = response.readText()
                Log.d("TASSG", "onStart: $raw")
                val result = Gson().fromJson(raw, DropyUserDetails::class.java)
                Log.d("TASSG", "onStart: $result")
               *//* res.value = result*//*
            }
*/

            registeruserUseCase.getUserDetails(
                token = "Token " + app.token.value
            ).flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                }
                .collect { result ->
                    // list of users from the network
                    // Log.d("uopopi", "getAllShops: ${result.data}")
                    when (result) {
                        is Resource.Success -> {

                            if (result.data != null) {
                                Log.d("YYYTAG", "createNewUser: ${result.data}")
                                /*uiState.update {
                                    it.copy(activeProfile = )
                                }*/

                                val item = ActiveProfileDataClass(
                                    type = ProfileTypes.CUSTOMER,
                                    name = result.data.first_name.toString() + " " + result.data.last_name.toString(),
                                    id = result.data.pk.toString()
                                )

                                Log.d("jikol", "getUserDetails: ${uiState.value.activeProfile}")

                                profiles.add(item)
                                uiState.update {
                                    it.copy(
                                        userProfiles = profiles,
                                        activeProfile = item
                                    )
                                }
                                app.setMyProfile(result.data)
                                app.setId(result.data.pk.toString())
                                getMenuItems()
                                getWaterpoints()
                                uiState.update { it.copy(appLoading = false) }

                            }


                        }
                        is Resource.Loading -> {
                            uiState.update { it.copy(appLoading = true) }
                        }
                        is Resource.Error -> {
                            result.message?.let { message ->
                                uiState.update {
                                    it.copy(
                                        appLoading = false,
                                        // errorList = listOf(message)
                                    )
                                }
                            }

                        }
                    }

                }

            /*if (response.isSuccessful) {
                val responseBody = response.body()!!
                if (responseBody.resultCode == 0) {

                    Log.d("duhdyg", "getUserDetails: $responseBody")
                    //set global dropy user id
                    uiState.update {
                        it.copy(
                            globalDropyUserId = responseBody.data?.dropy_user_instance?.id
                        )
                    }

                    if (responseBody.data?.dropy_user_instance != null) {
                        uiState.update {
                            it.copy(
                                riderId = responseBody.data?.dropy_user_instance?.rider_id,
                                isRider = responseBody.data?.dropy_user_instance?.is_rider,
                            )
                        }
                    }


                    Log.d("ikukiujk", "getUserDetails: ${uiState.value.riderId}")
                    val customerInstance =
                        responseBody.data?.customer_instance?.customer_name?.let {
                            responseBody.data.customer_instance.id?.let { it1 ->
                                ActiveProfileDataClass(
                                    type = ProfileTypes.CUSTOMER,
                                    name = it,
                                    id = it1,
                                )
                            }
                        }
                    if (customerInstance != null) {
                        profiles.add(customerInstance)
                    }

                    responseBody.data?.shops_owned?.forEach { item ->
                        val shopProfile = item.shop_name?.let {
                            item.id?.let { it1 ->
                                ActiveProfileDataClass(
                                    type = ProfileTypes.SHOP,
                                    name = it,
                                    id = it1
                                )
                            }
                        }
                        if (shopProfile != null) {
                            profiles.add(shopProfile)
                        }
                    }
                }
                Log.d("kolpp", "getUserDetails: ${response.body()}")
                Log.d("kolpp", "getUserDetails: ${firebaseUid}")
                uiState.update {
                    it.copy(
                        userProfiles = profiles,
                        activeProfile = if (set) profiles[0] else null
                    )
                }
                getMenuItems()
            }*/
        }
    }


    fun getWaterpoints() {

        val profiles = mutableListOf<ActiveProfileDataClass>()

        appUiState.value.userProfiles.forEach {
            if (!profiles.contains(it))
                profiles.add(it)
        }

        viewModelScope.launch {
            /* val client = HttpClient(CIO)



             val response: HttpResponse = *//*try {*//*
                client.get("https://api.dropy.co.ke/dropyusers/getuserdetails?firebase_uid=${firebaseUid}")*//*{
                    url {
                        parameters.append("firebase_uid", firebaseUid)
                    }
                }*//*
         *//*   } catch (e: Exception) {
                Log.d("TAG", "getAllShopCategories: Error")
                return null
            }*//*

            if (response.status == HttpStatusCode.OK) {
                val raw = response.readText()
                Log.d("TASSG", "onStart: $raw")
                val result = Gson().fromJson(raw, DropyUserDetails::class.java)
                Log.d("TASSG", "onStart: $result")
               *//* res.value = result*//*
            }
*/

            getWaterPointsUseCase(
                token = "Token " + app.token.value
            ).flowOn(Dispatchers.IO)
                /*  .catch { e ->
                      // handle exception
                  }*/
                .collect { result ->
                    // list of users from the network
                    // Log.d("uopopi", "getAllShops: ${result.data}")
                    when (result) {
                        is Resource.Success -> {

                            if (result.data != null) {
                                Log.d("YYYTAG", "createNewUser: ${result.data}")
                                /*uiState.update {
                                    it.copy(activeProfile = )
                                }*/

                                app.setWaterpoints(result.data)
                                val list: MutableList<GetWaterPointsResItem> = mutableListOf()
                                result.data.forEachIndexed { index, getWaterPointsResItem ->
                                    val item = ActiveProfileDataClass(
                                        type = ProfileTypes.WATER_POINT,
                                        name = getWaterPointsResItem.name,
                                        id = getWaterPointsResItem.id
                                    )

                                    Log.d("jikol", "getUserDetails: ${uiState.value.activeProfile}")
                                    if (getWaterPointsResItem.owner.id.equals(app.id.value)) {
                                        if (!list.contains(getWaterPointsResItem))
                                            list.add(getWaterPointsResItem)
                                        if (!profiles.contains(item))
                                            profiles.add(item)
                                    }
                                }

                                app.setMyWaterpoints(list)

                                uiState.update {
                                    it.copy(
                                        userProfiles = profiles,
//                                        activeProfile = item
                                    )
                                }
                                getMenuItems()

                                uiState.update { it.copy(appLoading = false) }
                                getWaterVendors()

                            }


                        }
                        is Resource.Loading -> {
                            uiState.update { it.copy(appLoading = true) }
                        }
                        is Resource.Error -> {
                            result.message?.let { message ->
                                uiState.update {
                                    it.copy(
                                        appLoading = false,
                                        // errorList = listOf(message)
                                    )
                                }
                            }

                        }
                    }

                }

            /*if (response.isSuccessful) {
                val responseBody = response.body()!!
                if (responseBody.resultCode == 0) {

                    Log.d("duhdyg", "getUserDetails: $responseBody")
                    //set global dropy user id
                    uiState.update {
                        it.copy(
                            globalDropyUserId = responseBody.data?.dropy_user_instance?.id
                        )
                    }

                    if (responseBody.data?.dropy_user_instance != null) {
                        uiState.update {
                            it.copy(
                                riderId = responseBody.data?.dropy_user_instance?.rider_id,
                                isRider = responseBody.data?.dropy_user_instance?.is_rider,
                            )
                        }
                    }


                    Log.d("ikukiujk", "getUserDetails: ${uiState.value.riderId}")
                    val customerInstance =
                        responseBody.data?.customer_instance?.customer_name?.let {
                            responseBody.data.customer_instance.id?.let { it1 ->
                                ActiveProfileDataClass(
                                    type = ProfileTypes.CUSTOMER,
                                    name = it,
                                    id = it1,
                                )
                            }
                        }
                    if (customerInstance != null) {
                        profiles.add(customerInstance)
                    }

                    responseBody.data?.shops_owned?.forEach { item ->
                        val shopProfile = item.shop_name?.let {
                            item.id?.let { it1 ->
                                ActiveProfileDataClass(
                                    type = ProfileTypes.SHOP,
                                    name = it,
                                    id = it1
                                )
                            }
                        }
                        if (shopProfile != null) {
                            profiles.add(shopProfile)
                        }
                    }
                }
                Log.d("kolpp", "getUserDetails: ${response.body()}")
                Log.d("kolpp", "getUserDetails: ${firebaseUid}")
                uiState.update {
                    it.copy(
                        userProfiles = profiles,
                        activeProfile = if (set) profiles[0] else null
                    )
                }
                getMenuItems()
            }*/
        }
    }

    fun getWaterVendors() {

        val profiles = mutableListOf<ActiveProfileDataClass>()

        appUiState.value.userProfiles.forEach {
            if (!profiles.contains(it))
                profiles.add(it)
        }

        viewModelScope.launch {
            /* val client = HttpClient(CIO)



             val response: HttpResponse = *//*try {*//*
                client.get("https://api.dropy.co.ke/dropyusers/getuserdetails?firebase_uid=${firebaseUid}")*//*{
                    url {
                        parameters.append("firebase_uid", firebaseUid)
                    }
                }*//*
         *//*   } catch (e: Exception) {
                Log.d("TAG", "getAllShopCategories: Error")
                return null
            }*//*

            if (response.status == HttpStatusCode.OK) {
                val raw = response.readText()
                Log.d("TASSG", "onStart: $raw")
                val result = Gson().fromJson(raw, DropyUserDetails::class.java)
                Log.d("TASSG", "onStart: $result")
               *//* res.value = result*//*
            }
*/

            getWaterVendorsUseCase(
                token = "Token " + app.token.value
            ).flowOn(Dispatchers.IO)
                /*  .catch { e ->
                      // handle exception
                  }*/
                .collect { result ->
                    // list of users from the network
                    // Log.d("uopopi", "getAllShops: ${result.data}")
                    when (result) {
                        is Resource.Success -> {

                            if (result.data != null) {
                                Log.d("YYYTAG", "createNewUser: ${result.data}")
                                /*uiState.update {
                                    it.copy(activeProfile = )
                                }*/

                                val list: MutableList<GetWaterVendorsResItem> = mutableListOf()

                                result.data.forEachIndexed { index, getWaterPointsResItem ->
                                    val item = ActiveProfileDataClass(
                                        type = ProfileTypes.WATER_VENDOR,
                                        name = getWaterPointsResItem.name,
                                        id = getWaterPointsResItem.id
                                    )

                                    Log.d("jikol", "getUserDetails: ${uiState.value.activeProfile}")
                                    if (getWaterPointsResItem.owner.id.equals(app.id.value)) {
                                        if (!profiles.contains(item))
                                            profiles.add(item)
                                        if (!list.contains(getWaterPointsResItem))
                                            list.add(getWaterPointsResItem)
                                    }
                                }

                                app.setMyWatervendors(list)
                                uiState.update {
                                    it.copy(
                                        userProfiles = profiles,
//                                        activeProfile = item
                                    )
                                }
                                getMenuItems()
                                uiState.update { it.copy(appLoading = false) }
                                getWaterTrucks()

                            }


                        }
                        is Resource.Loading -> {
                            uiState.update { it.copy(appLoading = true) }
                        }
                        is Resource.Error -> {
                            result.message?.let { message ->
                                uiState.update {
                                    it.copy(
                                        appLoading = false,
                                        // errorList = listOf(message)
                                    )
                                }
                            }

                        }
                    }

                }

            /*if (response.isSuccessful) {
                val responseBody = response.body()!!
                if (responseBody.resultCode == 0) {

                    Log.d("duhdyg", "getUserDetails: $responseBody")
                    //set global dropy user id
                    uiState.update {
                        it.copy(
                            globalDropyUserId = responseBody.data?.dropy_user_instance?.id
                        )
                    }

                    if (responseBody.data?.dropy_user_instance != null) {
                        uiState.update {
                            it.copy(
                                riderId = responseBody.data?.dropy_user_instance?.rider_id,
                                isRider = responseBody.data?.dropy_user_instance?.is_rider,
                            )
                        }
                    }


                    Log.d("ikukiujk", "getUserDetails: ${uiState.value.riderId}")
                    val customerInstance =
                        responseBody.data?.customer_instance?.customer_name?.let {
                            responseBody.data.customer_instance.id?.let { it1 ->
                                ActiveProfileDataClass(
                                    type = ProfileTypes.CUSTOMER,
                                    name = it,
                                    id = it1,
                                )
                            }
                        }
                    if (customerInstance != null) {
                        profiles.add(customerInstance)
                    }

                    responseBody.data?.shops_owned?.forEach { item ->
                        val shopProfile = item.shop_name?.let {
                            item.id?.let { it1 ->
                                ActiveProfileDataClass(
                                    type = ProfileTypes.SHOP,
                                    name = it,
                                    id = it1
                                )
                            }
                        }
                        if (shopProfile != null) {
                            profiles.add(shopProfile)
                        }
                    }
                }
                Log.d("kolpp", "getUserDetails: ${response.body()}")
                Log.d("kolpp", "getUserDetails: ${firebaseUid}")
                uiState.update {
                    it.copy(
                        userProfiles = profiles,
                        activeProfile = if (set) profiles[0] else null
                    )
                }
                getMenuItems()
            }*/
        }
    }

    fun getIndividualOrders() {

        viewModelScope.launch {

            getIndividualOrdersUseCase(
                token = "Token " + app.token.value
            ).flowOn(Dispatchers.IO)
                /*  .catch { e ->
                      // handle exception
                  }*/
                .collect { result ->
                    // list of users from the network
                    // Log.d("uopopi", "getAllShops: ${result.data}")
                    when (result) {
                        is Resource.Success -> {

                            if (result.data != null) {
                                Log.d("YYYTAG", "createNewUser: ${result.data}")
                                result.data.forEach {
                                    app.setIndividualOrders(it)
                                }
                                uiState.update { it.copy(appLoading = false) }

                            }


                        }
                        is Resource.Loading -> {
                            uiState.update { it.copy(appLoading = true) }
                        }
                        is Resource.Error -> {
                            result.message?.let { message ->
                                uiState.update {
                                    it.copy(
                                        appLoading = false,
                                        // errorList = listOf(message)
                                    )
                                }
                            }

                        }
                    }

                }

            /*if (response.isSuccessful) {
                val responseBody = response.body()!!
                if (responseBody.resultCode == 0) {

                    Log.d("duhdyg", "getUserDetails: $responseBody")
                    //set global dropy user id
                    uiState.update {
                        it.copy(
                            globalDropyUserId = responseBody.data?.dropy_user_instance?.id
                        )
                    }

                    if (responseBody.data?.dropy_user_instance != null) {
                        uiState.update {
                            it.copy(
                                riderId = responseBody.data?.dropy_user_instance?.rider_id,
                                isRider = responseBody.data?.dropy_user_instance?.is_rider,
                            )
                        }
                    }


                    Log.d("ikukiujk", "getUserDetails: ${uiState.value.riderId}")
                    val customerInstance =
                        responseBody.data?.customer_instance?.customer_name?.let {
                            responseBody.data.customer_instance.id?.let { it1 ->
                                ActiveProfileDataClass(
                                    type = ProfileTypes.CUSTOMER,
                                    name = it,
                                    id = it1,
                                )
                            }
                        }
                    if (customerInstance != null) {
                        profiles.add(customerInstance)
                    }

                    responseBody.data?.shops_owned?.forEach { item ->
                        val shopProfile = item.shop_name?.let {
                            item.id?.let { it1 ->
                                ActiveProfileDataClass(
                                    type = ProfileTypes.SHOP,
                                    name = it,
                                    id = it1
                                )
                            }
                        }
                        if (shopProfile != null) {
                            profiles.add(shopProfile)
                        }
                    }
                }
                Log.d("kolpp", "getUserDetails: ${response.body()}")
                Log.d("kolpp", "getUserDetails: ${firebaseUid}")
                uiState.update {
                    it.copy(
                        userProfiles = profiles,
                        activeProfile = if (set) profiles[0] else null
                    )
                }
                getMenuItems()
            }*/
        }
    }

    fun getWaterTrucks(update: Boolean = true) {

        viewModelScope.launch {
            val profiles = mutableListOf<ActiveProfileDataClass>()

            appUiState.value.userProfiles.forEach {
                if (!profiles.contains(it))
                    profiles.add(it)
            }

            /* val client = HttpClient(CIO)



             val response: HttpResponse = *//*try {*//*
                client.get("https://api.dropy.co.ke/dropyusers/getuserdetails?firebase_uid=${firebaseUid}")*//*{
                    url {
                        parameters.append("firebase_uid", firebaseUid)
                    }
                }*//*
         *//*   } catch (e: Exception) {
                Log.d("TAG", "getAllShopCategories: Error")
                return null
            }*//*

            if (response.status == HttpStatusCode.OK) {
                val raw = response.readText()
                Log.d("TASSG", "onStart: $raw")
                val result = Gson().fromJson(raw, DropyUserDetails::class.java)
                Log.d("TASSG", "onStart: $result")
               *//* res.value = result*//*
            }
*/

            getWaterTrucksUseCase(
                token = "Token " + app.token.value
            ).flowOn(Dispatchers.IO)
                /*  .catch { e ->
                      // handle exception
                  }*/
                .collect { result ->
                    // list of users from the network
                    // Log.d("uopopi", "getAllShops: ${result.data}")
                    when (result) {
                        is Resource.Success -> {

                            if (result.data != null) {
                                Log.d("YYYTAG", "createNewUser: ${result.data}")
                                /*uiState.update {
                                    it.copy(activeProfile = )
                                }*/

                                app.setWatertrucks(result.data)

                                val list: MutableList<GetTrucksResItem> = mutableListOf()

                                result.data.forEachIndexed { index, getWaterPointsResItem ->
                                    val item = ActiveProfileDataClass(
                                        type = ProfileTypes.WATER_TRUCK,
                                        name = getWaterPointsResItem.license_plate,
                                        id = getWaterPointsResItem.id
                                    )

                                    Log.d("jikol", "getUserDetails: ${uiState.value.activeProfile}")

                                    if (getWaterPointsResItem.vendor.owner.equals(app.id.value)) {
                                        if (!profiles.contains(item))
                                            profiles.add(item)
                                        if (!list.contains(getWaterPointsResItem))
                                            list.add(getWaterPointsResItem)
                                    }
                                }

                                app.setMyWatertrucks(list)

                               if (update){
                                   uiState.update {
                                       it.copy(
                                           userProfiles = profiles,
//                                        activeProfile = item
                                       )
                                   }
                                   getMenuItems()
                               }

                                uiState.update { it.copy(appLoading = false) }

                            }


                        }
                        is Resource.Loading -> {
                            uiState.update { it.copy(appLoading = true) }
                        }
                        is Resource.Error -> {
                            result.message?.let { message ->
                                uiState.update {
                                    it.copy(
                                        appLoading = false,
                                        // errorList = listOf(message)
                                    )
                                }
                            }

                        }
                    }

                }

            /*if (response.isSuccessful) {
                val responseBody = response.body()!!
                if (responseBody.resultCode == 0) {

                    Log.d("duhdyg", "getUserDetails: $responseBody")
                    //set global dropy user id
                    uiState.update {
                        it.copy(
                            globalDropyUserId = responseBody.data?.dropy_user_instance?.id
                        )
                    }

                    if (responseBody.data?.dropy_user_instance != null) {
                        uiState.update {
                            it.copy(
                                riderId = responseBody.data?.dropy_user_instance?.rider_id,
                                isRider = responseBody.data?.dropy_user_instance?.is_rider,
                            )
                        }
                    }


                    Log.d("ikukiujk", "getUserDetails: ${uiState.value.riderId}")
                    val customerInstance =
                        responseBody.data?.customer_instance?.customer_name?.let {
                            responseBody.data.customer_instance.id?.let { it1 ->
                                ActiveProfileDataClass(
                                    type = ProfileTypes.CUSTOMER,
                                    name = it,
                                    id = it1,
                                )
                            }
                        }
                    if (customerInstance != null) {
                        profiles.add(customerInstance)
                    }

                    responseBody.data?.shops_owned?.forEach { item ->
                        val shopProfile = item.shop_name?.let {
                            item.id?.let { it1 ->
                                ActiveProfileDataClass(
                                    type = ProfileTypes.SHOP,
                                    name = it,
                                    id = it1
                                )
                            }
                        }
                        if (shopProfile != null) {
                            profiles.add(shopProfile)
                        }
                    }
                }
                Log.d("kolpp", "getUserDetails: ${response.body()}")
                Log.d("kolpp", "getUserDetails: ${firebaseUid}")
                uiState.update {
                    it.copy(
                        userProfiles = profiles,
                        activeProfile = if (set) profiles[0] else null
                    )
                }
                getMenuItems()
            }*/
        }
    }

    /*private*/public fun getMenuItems() {
        val menuItems = when (appUiState.value.activeProfile?.type) {
            ProfileTypes.CUSTOMER -> {
                customerMenu
            }
            ProfileTypes.WATER_TRUCK -> {
                waterTruckMenu
            }
            ProfileTypes.WATER_VENDOR -> {
                waterVendorMenu
            }
            ProfileTypes.WATER_POINT -> {
                waterPointMenu
            }
            ProfileTypes.SHOP -> {
                shopMenu
            }
//            ProfileTypes.DELIVERY_PERSON -> {}
            ProfileTypes.RIDER -> {
                riderMenu
            }
            else -> {
                customerMenu
            }
        }
        uiState.update { it.copy(drawerMenuItems = menuItems) }
    }


    fun setSystemUiControllerColor(color: Color) {
        systemUiController?.setStatusBarColor(color = color)
    }

    fun setFirebaseUid(firebaseUid: String?) {
        uiState.update { it.copy(firebaseUid = firebaseUid) }
        if (firebaseUid != null) {
            // getUserDetails()
        }
    }

    fun hideBackButton(firebaseUid: String) {
        uiState.update { it.copy(showBackButton = false) }
    }

    fun hideCartButton(firebaseUid: String) {
        uiState.update { it.copy(showCartButton = false) }
    }

    fun navigate(route: String) {
        if (route == "signOut") {
            Firebase.auth.signOut()
            setFirebaseUid(null)
        } else {
            if (route == AppDestinations.APP_HOME ||
                route == AppDestinations.SHOPS_FRONT ||
                route == AppDestinations.RIDES ||
                route == AppDestinations.PARCELS
            ) {
                navHostController?.popBackStack()
            }
            navHostController?.navigate(route = route) {
                navOptions { // Use the Kotlin DSL for building NavOptions
                    anim {
                        enter = R.animator.fade_in
                        exit = R.animator.fade_out
                    }
                }
            }
        }
    }

    fun onBackButtonClicked() {
        navHostController?.navigateUp()
    }

    fun onDashboardButtonClicked() {
        when (appUiState.value.activeProfile?.type) {
            ProfileTypes.CUSTOMER -> {
                navigate(ShopsFrontDestination.CUSTOMER_DASHBOARD)
            }
            ProfileTypes.SHOP -> {
                navigate(ShopsBacksideNavigation.SHOP_DASHBOARD)
            }
            ProfileTypes.DELIVERY_PERSON -> {}
            ProfileTypes.RIDER -> {}
            else -> {}
        }
    }

    fun onCartButtonClicked() {
        navigate(ShopsFrontDestination.CART)
    }

    fun onSelectProfile(profile: ActiveProfileDataClass) {
        uiState.update { it.copy(activeProfile = profile) }
        when {
            profile.type.equals(ProfileTypes.WATER_POINT) -> navigate(AppDestinations.WATERPOINT_DASH)
            profile.type.equals(ProfileTypes.WATER_TRUCK) -> navigate(AppDestinations.TRUCK_DASHBOARD)
            profile.type.equals(ProfileTypes.WATER_VENDOR) -> navigate(AppDestinations.WATER_VENDOR_DASHBOARD)
            profile.type.equals(ProfileTypes.CUSTOMER) -> navigate(ShopsFrontDestination.CUSTOMER_DASHBOARD)
        }
        getMenuItems()
    }

}