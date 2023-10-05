package com.example.dropy.ui.screens.apphome

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.AfricasTalkingMessaging
import com.example.dropy.network.models.ShopsResponseNewItem
import com.example.dropy.network.models.favouriteshop.FavShopDataClass
import com.example.dropy.network.models.shops.Shop
import com.example.dropy.network.repositories.commons.CommonRepository
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.network.use_case.common.DirectionMineUseCase
import com.example.dropy.network.use_case.shops.frontside.*
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppDestinations.SHOPS_FRONT
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.shops.frontside.shoplist.ShopListItemDataClass
import com.example.dropy.ui.screens.shops.frontside.shopslandingpage.ShopsLandingPageViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import com.example.dropy.ui.utils.Resource
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

val shopListExample = mutableListOf(
    ShopListItemDataClass(
        shopName = "shop 1",
        shopLogoUrl = "https://images.unsplash.com/photo-1561336313-0bd5e0b27ec8?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8c2FtcGxlfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60",
        shopId = 1
    ),
    ShopListItemDataClass(
        shopName = "shop 2",
        shopLogoUrl = "https://images.unsplash.com/photo-1561336313-0bd5e0b27ec8?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8c2FtcGxlfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60",
        shopId = 2
    ),
    ShopListItemDataClass(
        shopName = "shop 3",
        shopId = 3
    ),
    ShopListItemDataClass(
        shopName = "shop 4",
        shopId = 4
    ),
    ShopListItemDataClass(
        shopName = "shop 1",
        shopId = 5
    ),
)

data class AppHomePageUiState(
    val userName: String = "",
    val popularShops: List<ShopsResponseNewItem> = emptyList(),
    val favouriteShops: List<com.example.dropy.network.models.favouriteshop.favouriteShopRes.Shop> = emptyList(),
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

data class SearchUiState(
    val productlist: List<ProductSearch> = listOf(),
    val shoplist: List<ProductSearch> = listOf(),
    val filtersearchlist: List<String> = listOf(),
    val searchName: String = "",
    val filter: String = "shops",
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList(),
)

data class ProductSearch(
    val product: com.example.dropy.network.models.shopdetails.Product? = null,
    val shop: ShopsResponseNewItem,
    val rating: Double,
    val distance: Double,
    val eta: Int,
    val shoplogourl: String? = null,
    val productlogourl: String? = null,
)

@HiltViewModel
class AppHomePageViewModel @Inject constructor(
    private val shopFrontendRepository: ShopFrontendRepository,
    private val commonRepository: CommonRepository,
    private val getAllShopsUseCase: GetAllShopsUseCase,
    // private val getAllProductsUseCase: GetAllProductsUseCase,
    private val favouriteShopUseCase: FavouriteShopUseCase,
    private val getFavouriteShopsUseCase: GetFavouriteShopsUseCase,
    private val directionMineUseCase: DirectionMineUseCase,
    private val app: DropyApp
) : ViewModel() {
    val uiState = MutableStateFlow(AppHomePageUiState())

    val homePageUiState: StateFlow<AppHomePageUiState> = uiState.asStateFlow()

    private val _searchUiState = MutableStateFlow(SearchUiState())

    val searchUiState: StateFlow<SearchUiState> = _searchUiState.asStateFlow()

    var appViewModel: AppViewModel? = null

    val chosenShopId: MutableState<Int?> = mutableStateOf(null)
    val context: MutableState<Context?> = mutableStateOf(null)

    fun message() {
        val message = AfricasTalkingMessaging()
        message.message()
    }


    fun setFilter(name: String) {
        _searchUiState.update {
            it.copy(
                filter = name
            )
        }
    }


    fun setContext(contextt: Context) {
        context.value = contextt
    }

    fun setvalues() {
        val current: MutableList<String> = mutableListOf()
        current.add("shops")
        current.add("products")
        _searchUiState.update {
            it.copy(
                filter = "shops",
                filtersearchlist = current
            )
        }
    }

    fun setShopId(id: Int) {
        chosenShopId.value = id
        Log.d("DDDTAG", "setShopId: ${chosenShopId.value}")
    }

    fun getAllShops() {
        viewModelScope.launch {
            //val res = shopFrontendRepository.getAllShops()
            getAllShopsUseCase(app.token.value).flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                    uiState.update { it.copy(pageLoading = false) }
                }
                .collect { result ->
                    // list of users from the network
                    when (result) {
                        is Resource.Success -> {
                            Log.d("njiol", "getAllShops: ${result.data}")
                            if (result.data != null) {


                                result.data.forEach {
                                    app.addMyShop(it)
                                    getPopularShops(it)
                                    //  getPopularShops(it)
                                }

                                appViewModel?.setUserProfiles()
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

    fun getAllProducts() {
        viewModelScope.launch {
            //val res = shopFrontendRepository.getAllShops()
            shopFrontendRepository.getAllProducts().flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                    uiState.update { it.copy(pageLoading = false) }
                }
                .collect { result ->
                    // list of users from the network
                    when (result) {
                        is Resource.Success -> {
                            Log.d("njiol", "getAllProducts: ${result.data}")
                            if (result.data != null) {


                                result.data.forEach {
                                    // getPopularShops(it)
                                    //  getPopularShops(it)
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


    val current: MutableList<ShopsResponseNewItem> = mutableListOf()
    fun getPopularShops(item: ShopsResponseNewItem) {
        if (!current.contains(item)) {
            current.add(item)

        }
        uiState.update { it.copy(popularShops = current) }
    }


    fun onFavouriteClicked(shop: ShopsResponseNewItem, status: Boolean) {
        viewModelScope.launch {
            if (appViewModel!!.appUiState.value.activeProfile?.id.toString() != "") {

                val item = FavShopDataClass(
                    shop = shop.id!!,
                    ///  appViewModel!!.appUiState.value.activeProfile!!.id
                    user_id = /*app.id.value*/appViewModel!!.appUiState.value.activeProfile?.id.toString()
                )

                favouriteShopUseCase.favouriteShop(app.token.value, item).flowOn(Dispatchers.IO)
                    .catch { e ->
                        // handle exception
                        uiState.update { it.copy(pageLoading = false) }
                        Toast.makeText(
                            context.value,
                            "An error occurred when favouriting shop",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .collect { result ->
                        // list of users from the network
                        Log.d("uopopi", "getAllShops: $result")
                        when (result) {
                            is Resource.Success -> {
                                // list of favourite shops from the network
                                if (result.data?.status?.equals(201) == true) {
                                    Toast.makeText(
                                        context.value,
                                        result.data.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    getFavouriteShops()
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




                Log.d("ugsu", "onFavouriteClicked: ${uiState.value.favouriteShops}")
            }
        }

    }

    fun navigateCustomerDashboard() {
        appViewModel!!.navigate(ShopsFrontDestination.CUSTOMER_DASHBOARD)
    }

    suspend fun getFavouriteShops() {

        appViewModel!!.appUiState.value.activeProfile?.id?.let {
            getFavouriteShopsUseCase.getFavouriteShops(
                app.token.value, it
            ).flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                    uiState.update { it.copy(pageLoading = false) }
                    Toast.makeText(
                        context.value,
                        "Error occured when getting favourite shops",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: $result")
                    when (result) {
                        is Resource.Success -> {

                            if (result.data?.data?.isEmpty() == false) {

                                val current: MutableList<com.example.dropy.network.models.favouriteshop.favouriteShopRes.Shop> =
                                    mutableListOf()

                                result.data.data.forEach {
                                    it.shop?.let { it1 ->
                                        if (!current.contains(it1)) {
                                            current.add(it1)
                                        }
                                    }
                                    uiState.update {
                                        it.copy(
                                            favouriteShops = current,
                                            pageLoading = false
                                        )
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

    fun navigateShop() {
//        appViewModel!!.navigate(ShopsFrontDestination.SINGLE_SHOP)

    }

    fun onShopSelected(
        shopId: String,
        shopsResponseNewItem: ShopsResponseNewItem?,
        shopHomePageViewModel: ShopHomePageViewModel,
        appHomePageViewModel: AppHomePageViewModel? = null,
        shopsLandingPageViewModel: ShopsLandingPageViewModel? = null
    ) {
        viewModelScope.launch {
            /*        uiState.update {
                        it.copy(pageLoading = true)
                    }*/
            Log.d("skod", "onShopSelected: $shopId")
            app.setShopId(shopId)
            if (shopsResponseNewItem != null) {
                app.setShop(shopsResponseNewItem)
            }
//            appViewModel?.navigate(ShopsFrontDestination.SINGLE_SHOP)
//
/*
            val res = shopHomePageViewModel.getGetShopProducts(
                shopId,
                appHomePageViewModel = appHomePageViewModel,
                shopsLandingPageViewModel = shopsLandingPageViewModel,
                navigate = {
                    appViewModel?.navigate(ShopsFrontDestination.SINGLE_SHOP)
                }
            )
            Log.d("jfirn", "onShopSelected: $res")
            if (res != null) {
                    appViewModel?.appUiState?.value?.yourlocation?.let {
                        LatLng(
                            it.latitude,
                            appViewModel?.appUiState?.value?.yourlocation!!.longitude
                        )
                    }?.let {
                        shopHomePageViewModel.shopHomePageUiState.value.shopLatLng?.let { it1 ->
                            directionMineUseCase.directionMine(
                                it1,
                                it
                            ).flowOn(Dispatchers.IO)
                                .catch { e ->
                                    // handle exception
                                }
                                .collect { result ->
                                    // list of users from the network
                                    Log.d("uopopi", "getAllShops: $result")
                                    when (result) {
                                        is Resource.Success -> {

                                            Log.d("huhuh", "onShopSelected: $result")
                                            result.data?.routes?.forEach {
                                                it.legs?.forEach { leg ->
                                                    leg.distance?.text?.let { it1 ->
                                                        leg.distance?.value?.let { it2 ->
                                                            shopHomePageViewModel.setDistance(
                                                                it1,
                                                                it2
                                                            )
                                                        }
                                                    }
                                                }
                                            }

                                            appViewModel?.navigate(ShopsFrontDestination.SINGLE_SHOP)

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




            }*/
            //   delay(500)

            /*        uiState.update {
                        it.copy(pageLoading = false)
                    }*/
        }
        //appViewModel?.navigate(ShopsFrontDestination.SHOPS_MAP)
    }


    suspend fun shopDistance(shopHomePageViewModel: ShopHomePageViewModel) {

        appViewModel?.appUiState?.value?.yourlocation?.let {
            LatLng(
                it.latitude,
                appViewModel?.appUiState?.value?.yourlocation!!.longitude
            )
        }?.let {
            directionMineUseCase.directionMine(
                shopHomePageViewModel.shopHomePageUiState.value.shopLatLng!!,
                it
            ).flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                    uiState.update { it.copy(pageLoading = false) }
                    Toast.makeText(context.value, "An error occurred", Toast.LENGTH_SHORT).show()
                }
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: $result")
                    when (result) {
                        is Resource.Success -> {

                            Log.d("huhuh", "onShopSelected: $result")
                            result.data?.routes?.forEach {
                                it.legs?.forEach { leg ->
                                    leg.distance?.text?.let { it1 ->
                                        leg.distance?.value?.let { it2 ->
                                            shopHomePageViewModel.setDistance(
                                                it1,
                                                it2
                                            )
                                        }
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

    fun onGoToShops() {
//        appViewModel?.navigate(ShopsFrontDestination.SHOPS_HOME)
    }

    fun onGoToWater() {
        appViewModel?.navigate(AppDestinations.WATER_HOME)
    }

    fun onGoToRides() {
        // appViewModel?.navigate(AppDestinations.RIDES)
        // appViewModel?.navigate(RiderDestination.RIDERINCOMINGORDERS)
//        appViewModel?.navigate(AppDestinations.PARCELS)

    }

    fun onGoToParcels() {
      //  appViewModel?.navigate(ParcelDestination.PARCEL_HOME)
        // appViewModel?.navigate(AppDestinations.PARCELS)
    }

    fun onGoToSearch() {
        //appViewModel?.navigate(ParcelDestination.PARCEL_HOME)
        //appViewModel?.navigate(AppDestinations.SEARCH_SCREEN)
    }

    fun onSearchItem(
        text: String,
        shopHomePageViewModel: ShopHomePageViewModel,
        appHomePageViewModel: AppHomePageViewModel? = null,
        shopsLandingPageViewModel: ShopsLandingPageViewModel? = null
    ) {
        viewModelScope.launch {
            Log.d("ytry", "onSearchItem: ${text}")
            val shopcurrent: MutableList<ProductSearch> = mutableListOf()
            val productcurrent: MutableList<ProductSearch> = mutableListOf()

            _searchUiState.update {
                it.copy(
                    searchName = text
                )
            }
            val found: MutableState<Boolean> = mutableStateOf(false)
            uiState.value.popularShops.forEach {

                if (it.shop_name?.toLowerCase(Locale.ROOT)
                        ?.startsWith(text.toLowerCase(Locale.ROOT)) == true
                ) {
                    val item = ProductSearch(
                        shop = it,
                        rating = 3.2,
                        distance = 2.7,
                        eta = 12,
                        shoplogourl = it.shop_logo
                    )

                    if (!shopcurrent.contains(item)) {
                        shopcurrent.add(item)
                    }

                    _searchUiState.update { state ->
                        state.copy(
                            shoplist = shopcurrent
                        )
                    }

                }
                val res = it.id?.let { it1 ->
                    shopHomePageViewModel.getGetShopProducts(
                        it1,
                        appHomePageViewModel = appHomePageViewModel,
                        shopsLandingPageViewModel = shopsLandingPageViewModel
                    )
                }
                res?.products?.forEach { product ->
                    Log.d(
                        "drer",
                        "onSearchItem: ${product.product_name?.toLowerCase(Locale.ROOT)}     ${
                            text.toLowerCase(
                                Locale.ROOT
                            )
                        }"
                    )
                    if (product.product_name?.toLowerCase(Locale.ROOT)?.startsWith(
                            text.toLowerCase(
                                Locale.ROOT
                            )
                        ) == true
                    ) {
                        Log.d("drer", "onSearchItem: found  ${product}")
                        val itemm = ProductSearch(
                            product = product,
                            shop = it,
                            rating = ("${(Random.nextInt(3, 4))}.${
                                (Random.nextInt(
                                    0,
                                    9
                                ))
                            }").toDouble(),
                            distance = ("${(Random.nextInt(1, 4))}.${
                                (Random.nextInt(
                                    0,
                                    9
                                ))
                            }").toDouble(),
                            eta = Random.nextInt(5, 50),
                            productlogourl = product.image_url
                        )


                        if (itemm != null) {
                            if (!productcurrent.contains(itemm)) {
                                productcurrent.add(itemm)
                            }

                            _searchUiState.update {
                                it.copy(
                                    productlist = productcurrent
                                )
                            }
                            Log.d("ytry", "onSearchItem: ${searchUiState.value.shoplist}")
                        }
                    }
                }
                //    delay(1400)
            }

            if (!_searchUiState.value.shoplist.isEmpty() || !_searchUiState.value.productlist.isEmpty()) {
                appViewModel?.navigate(ShopsFrontDestination.SHOPS_MAP)
            }
        }
    }

    fun onShopReview() {
        appViewModel?.navigate(AppDestinations.REVIEWSHOP)
    }
}