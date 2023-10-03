package com.example.dropy.ui.screens.shops.frontside.singleshop

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.*
import com.example.dropy.network.models.shopdetails.Product
import com.example.dropy.network.models.shopdetails.ProductCategory
import com.example.dropy.network.models.shopdetails.ShopDetailsResponse
import com.example.dropy.network.repositories.commons.CommonRepository
import com.example.dropy.network.repositories.shop.ShopRepository
import com.example.dropy.network.repositories.shop.front.ShopFrontendRepository
import com.example.dropy.network.use_case.cart.GetCartItemsNewUseCase
import com.example.dropy.network.use_case.cart.PostCartItemsUseCase
import com.example.dropy.network.use_case.common.DirectionUseCase
import com.example.dropy.network.use_case.shops.frontside.CreateCartUseCase
import com.example.dropy.network.use_case.shops.frontside.GetShopDetailsUseCase
import com.example.dropy.network.use_case.shops.frontside.GetShopProductsUseCase
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.shopslandingpage.ShopsLandingPageViewModel
import com.example.dropy.ui.utils.Resource
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


data class ShopHomePageUiState(
    val shopProducts: List<AllProductsResItem> = emptyList(),
    val productCategories: List<ProductCategory> = emptyList(),
    val numberOfFollowers: Int = 0,
    val numberOfOrders: Int = 0,
    val shopDistance: String = "",
    val timetaken: Int = 1,
    val shopDistanceNum: Int = 1,
    val shopName: String = "Shop name",
    val shopPhone: String = "Shop phone",
    val shopDescription: String = "Shop description",
    val shopCoverPhotoUrl: String = "",
    val shopLogoUrl: String = "",
    val filteredCategory: String = "All items",
    val shopLocation: String = "Shop location",
    val shopLatLng: LatLng? = /*null*/LatLng(1.2921, 36.8219),
    val polylines: List<LatLng> = listOf(),
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList(),
    val selectedCategory: String = "",
    val filteredList: MutableList<AllProductsResItem> =
        mutableListOf(),
    val exist: Boolean = false

)

@HiltViewModel
class ShopHomePageViewModel @Inject constructor(
    private val commonRepository: CommonRepository,
    private val shopFrontendRepository: ShopFrontendRepository,
    private val getShopDetailsUseCase: GetShopDetailsUseCase,
    private val getShopProductsUseCase: GetShopProductsUseCase,
    private val directionUseCase: DirectionUseCase,
    private val app: DropyApp,
    private val postCartItemsUseCase: com.example.dropy.network.use_case.PostCartItemsUseCase,
    private val createCartUseCase: CreateCartUseCase,
    private val getCartItemsNewUseCase: GetCartItemsNewUseCase
) : ViewModel() {

    val uiState = MutableStateFlow(ShopHomePageUiState())

    val shopHomePageUiState: StateFlow<ShopHomePageUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null

    val product: MutableState<AllProductsResItem?> = mutableStateOf(null)
    val id: MutableState<Int?> = mutableStateOf(null)
    val tempshopid: MutableState<String?> = mutableStateOf(null)

    fun setId(idd: Int) {
        id.value = idd
    }

    fun settempshopId(shopid: String) {
        tempshopid.value = shopid
    }


    fun clearPolyLine() {
        uiState.update {
            it.copy(polylines = listOf())
        }
    }

    fun setShopname(name: String) {
        uiState.update {
            it.copy(shopName = name)
        }
    }


    fun getRouteDetails(use: Boolean = false, shoplatlng: LatLng? = null, shopName: String = "") {
        viewModelScope.launch {
            val res = shopHomePageUiState.value.shopLatLng?.let {
                appViewModel?.appUiState?.value?.myAddress?.placeName?.let { it1 ->
                    (if (use) shoplatlng else it)!!.let { it2 ->
                        getDirectionRes(
                            it2,
                            destination = if (use) shopName else it1
                        )
                    }
                } ?: if (use) {
                    getDirectionRes(
                        shoplatlng!!,
                        destination = shopName
                    )
                } else {
                    getDirectionRes(
                        it,
                        destination = shopName
                    )
                }
            }


        }
    }

    suspend fun getDirectionRes(shoplatlng: LatLng, destination: String = "") {

        directionUseCase.direction(
            shoplatlng,
            destination = destination
        ).flowOn(Dispatchers.IO)
            .catch { e ->
                // handle exception
            }
            .collect { result ->
                // list of users from the network
                Log.d("uopopi", "getAllShops: $result")
                when (result) {
                    is Resource.Success -> {

                        if (result.data != null) {
                            var total: Int = 0
                            val current: MutableList<LatLng> = mutableListOf()
                            result.data.routes?.forEach {
                                it.legs?.forEach { leg ->

                                    Log.d("erwer", "getRouteDetails: ${leg.duration?.value!!}")
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
                                        val start = step.start_location?.lat?.let { it1 ->
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
                                                Log.d("kokoko", "current:$current ")
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

    fun setDistance(num: String, value: Int) {

        Log.d("lokok", "setDistance: $num  $value")
        uiState.update {
            it.copy(
                shopDistance = num,
                shopDistanceNum = value
            )
        }
    }

    fun setFilteredCategory(text: String) {
        Log.d("lmook", "setFilteredCategory: ${uiState.value.shopProducts},,,,,$text")
        uiState.update {
            it.copy(
                filteredCategory = text,
                exist = false
            )
        }
        val current: MutableList<AllProductsResItem> = mutableListOf()
        val isthere: MutableState<Boolean> = mutableStateOf(false)

        uiState.value.shopProducts.forEachIndexed { index, item ->
            when {
                item.product_category?.equals(text) == true -> {
                    isthere.value = true
                    Log.d("uiui", "setFilteredCategory: $text")
                    if (!uiState.value.filteredList.contains(item)) {
                        current.add(item)
                        Log.d("uiui", "setFilteredCategory: $current")
                        uiState.update {
                            it.copy(
                                filteredList = current,
                                exist = isthere.value
                            )
                        }
                        Log.d("ugyutug", "setFilteredCategory: ${uiState.value.filteredList}")
                    }
                    // return
                }
                text.equals("All items") -> {

                    /*if (!uiState.value.filteredList.contains(item)) {
                        current.add(item)
                        Log.d("uiui", "setFilteredCategory: $current")
                        uiState.update {
                            it.copy(
                                filteredList = current
                            )
                        }
                    }*/
                    //  return
                }
                else -> {
                    //     Log.d("ugyutug", "ProductsGridnot: ${selectedCategory.value}")
                    /* uiState.update {
                         it.copy(
                             filteredList = mutableListOf()
                         )
                     }*/
                    Log.d("ugyutug", "setFilteredCategory: ${uiState.value.filteredList}")
                }

            }
        }
        /*       uiState.update {
                   it.copy(
                       filteredCategory = text
                   )
               }
       */
        //   appViewModel?.navigate(ShopsFrontDestination.SINGLE_SHOP)
    }

    fun cartIdisNull(): Boolean {
        if (app.cartId.value != null) {
            return false
        } else return true
    }

    fun addCartNew(
        product: Int?,
        cartPageViewModel: CartPageViewModel,
        context: Context
    ) {

        val item = AddCartReq(
            cart = app.cartId.value,
            product = product,
            quantity = 1,
        )

        Log.d("lmkio", "addCartNew: $item")
        viewModelScope.launch {
            postCartItemsUseCase(
                token = "Token " + app.token.value,
                addCartReq = item
            ).flowOn(Dispatchers.IO)
                /*.catch { e ->
                    // handle exception
                    uiState.update { it.copy(pageLoading = false) }
                }*/
                .collect { result ->
                    // list of users from the network
                    when (result) {
                        is Resource.Success -> {
                            Log.d("njiol", "getAllProducts: ${result.data}")
                            if (result.data != null) {
                                val list: MutableList<AllProductsResItem> = mutableListOf()

/*                                result.data.forEach {
                                    // getPopularShops(it)
                                    //  getPopularShops(it)
                                    list.add(it)
                                    uiState.update {
                                        it.copy(
                                            shopProducts = list
                                        )
                                    }
                                }*/
                                getCartItemNew(cartPageViewModel, context = context)

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

    fun getCartItemNew(
        cartPageViewModel: CartPageViewModel,
        context: Context
    ) {

        viewModelScope.launch {
            app.cartId.value?.let {
                getCartItemsNewUseCase(
                    token = "Token " + app.token.value,
                    cartId = it
                ).flowOn(Dispatchers.IO)
                    .catch { e ->
                        // handle exception
                        uiState.update { it.copy(pageLoading = false) }
                        Toast.makeText(
                            context,
                            "Error encountered when getting cart items",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .collect { result ->
                        // list of users from the network
                        when (result) {
                            is Resource.Success -> {
                                Log.d("njiol", "getAllProducts: ${result.data}")
                                if (result.data != null) {
                                    val list: MutableList<GetCartResItem> = mutableListOf()
                                    result.data.forEach {
                                        list.add(it)
                                    }
                                    app.setCartList(list)
                                    cartPageViewModel.setCartList()
                                    //cartPageViewModel.setOrderList(list)
                                    /*                                result.data.forEach {
                                                                        // getPopularShops(it)
                                                                        //  getPopularShops(it)
                                                                        list.add(it)
                                                                        uiState.update {
                                                                            it.copy(
                                                                                shopProducts = list
                                                                            )
                                                                        }
                                                                    }*/

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

    fun createCart(
        product: Int?,
        cartPageViewModel: CartPageViewModel,
        context: Context
    ) {

        val item = CreateCartReq(
            shop = app.shop.value!!.id!!,
            user = app.id.value
        )
        viewModelScope.launch {
            createCartUseCase(
                token = "Token " + app.token.value,
                createCartReq = item
            ).flowOn(Dispatchers.IO)
                /*.catch { e ->
                    // handle exception
                    uiState.update { it.copy(pageLoading = false) }
                }*/
                .collect { result ->
                    // list of users from the network
                    when (result) {
                        is Resource.Success -> {
                            Log.d("njiol", "createCartRes: ${result.data}")
                            if (result.data != null) {
                                val list: MutableList<AllProductsResItem> = mutableListOf()

/*                                result.data.forEach {
                                    // getPopularShops(it)
                                    //  getPopularShops(it)
                                    list.add(it)
                                    uiState.update {
                                        it.copy(
                                            shopProducts = list
                                        )
                                    }
                                }*/

                                uiState.update { it.copy(pageLoading = false) }
                                result.data.id?.let { app.setCartId(it) }
                                addCartNew(
                                    product = product,
                                    cartPageViewModel = cartPageViewModel,
                                    context = context
                                )
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

    fun setShopId(id: String) {
        app.setShopId(id)
    }

    fun setShop(shopsResponseNewItem: ShopsResponseNewItem) {
        app.setShop(shopsResponseNewItem)
    }

    fun setShopData() {
        uiState.update {
            it.copy(
                shopName = app.shop.value?.shop_name.toString(),
                shopLogoUrl = app.shop.value?.shop_logo.toString(),
                shopCoverPhotoUrl = app.shop.value?.shop_cover_photo.toString(),
                shopPhone = app.shop.value?.phone_number.toString()
            )
        }
    }

    fun getShopProducts() {
        viewModelScope.launch {
            //val res = shopFrontendRepository.getAllShops()
            shopFrontendRepository.getShopProducts(shop = app.shopId.value).flowOn(Dispatchers.IO)
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
                                val list: MutableList<AllProductsResItem> = mutableListOf()

                                result.data.forEach {
                                    // getPopularShops(it)
                                    //  getPopularShops(it)
                                    list.add(it)
                                    uiState.update {
                                        it.copy(
                                            shopProducts = list
                                        )
                                    }
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


    suspend fun getGetShopProducts(
        shopId: String,
        appHomePageViewModel: AppHomePageViewModel? = null,
        shopsLandingPageViewModel: ShopsLandingPageViewModel? = null,
        navigate: (() -> Unit)? = null
    ): ShopDetailsResponse? {
        uiState.update {
            it.copy(shopProducts = listOf())
        }
        when {
            appHomePageViewModel != null -> {
                appHomePageViewModel.uiState.update { it.copy(pageLoading = true) }

            }
            shopsLandingPageViewModel != null -> {
                shopsLandingPageViewModel._uiState.update { it.copy(pageLoading = true) }

            }
            else -> {
                uiState.update { it.copy(pageLoading = true) }
            }
        }
        val shopdetailRes: MutableState<ShopDetailsResponse?> = mutableStateOf(null)

        Log.d("DFFFFTAG", "getGetShopProducts: $shopId")
        getShopDetailsUseCase.getShopDetails(token = app.token.value, shopId = shopId)
            .flowOn(Dispatchers.IO)
            .catch { e ->
                // handle exception

                when {
                    appHomePageViewModel != null -> {
                        appHomePageViewModel.uiState.update { it.copy(pageLoading = false) }

                    }
                    shopsLandingPageViewModel != null -> {
                        shopsLandingPageViewModel._uiState.update { it.copy(pageLoading = false) }

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

                        // list of users from the network
                        Log.d("uopopi", "getAllShops: ${result.data}")

                        shopdetailRes.value = result.data
                        val current: MutableList<ProductCategory> =
                            mutableListOf()

                        current.clear()

                        if (result.data?.products?.isEmpty() == false) {
                            if (result.data.product_categories?.isEmpty() == false) {

                                current.add(ProductCategory("All items", 6))
                                result.data.product_categories.forEach { ite ->
                                    current.add(ite)
                                }

                                uiState.update {
                                    it.copy(
                                        //  shopProducts = result.data.products,
                                        productCategories = current,
                                        shopName = result.data.shop_name.toString(),
                                        numberOfFollowers = result.data.number_0f_followers!!,
                                        numberOfOrders = result.data.number_of_orders!!.toInt(),
                                        shopCoverPhotoUrl = result.data.shop_cover_photo_url.toString(),
                                        shopLogoUrl = result.data.shop_logo_url.toString(),
                                        shopLocation = result.data.shop_location?.place_name.toString(),
                                        shopLatLng = LatLng(
                                            result.data.shop_location?.latitude!!,
                                            result.data.shop_location.longitude!!
                                        ),
                                        shopPhone = result.data.phone_number!!,
                                        selectedCategory = result.data.product_categories[0].category_name
                                            ?: "",
                                        filteredCategory = current[0].category_name.toString()
                                    )
                                }

                                setFilteredCategory(current[0].category_name.toString())
                            } else {

                                uiState.update {
                                    it.copy(
                                        shopProducts = listOf(),
                                        productCategories = current,
                                    )
                                }
                            }
                            Log.d("loiouoiu", "getGetShopProducts: ${result.data?.products}")
                            when {
                                appHomePageViewModel != null -> {
                                    appHomePageViewModel.uiState.update { it.copy(pageLoading = false) }

                                }
                                shopsLandingPageViewModel != null -> {
                                    shopsLandingPageViewModel._uiState.update { it.copy(pageLoading = false) }

                                }
                                else -> {
                                    uiState.update { it.copy(pageLoading = false) }
                                }
                            }

                        } else {
                            uiState.update {
                                it.copy(
                                    shopProducts = listOf(),
                                    productCategories = current,
                                )
                            }

                            when {
                                appHomePageViewModel != null -> {
                                    appHomePageViewModel.uiState.update { it.copy(pageLoading = false) }

                                }
                                shopsLandingPageViewModel != null -> {
                                    shopsLandingPageViewModel._uiState.update { it.copy(pageLoading = false) }

                                }
                                else -> {
                                    uiState.update { it.copy(pageLoading = false) }
                                }
                            }
                        }
                        Log.d("msinie", "getGetShopProducts: ${result.data}")

                        if (navigate != null) {
                            navigate()
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

        return shopdetailRes.value

    }

    fun onCallShop(context: Context) {
        val dialIntent = Intent(Intent.ACTION_CALL)
        dialIntent.data = Uri.parse("tel:" + "${shopHomePageUiState.value.shopPhone}")
        context.startActivity(dialIntent)
    }

    fun onEmailShop(context: Context) {
        val dialIntent = Intent(Intent.ACTION_VIEW)
        dialIntent.data = Uri.parse("sms:" + "${shopHomePageUiState.value.shopPhone}")
        context.startActivity(dialIntent)
    }

    fun setshoplatLng(shoplatlng: LatLng) {
        uiState.update {
            it.copy(
                shopLatLng = shoplatlng
            )
        }
    }

    fun onFilterByCategory(categoryName: String) {

    }

    fun onProductSelected(product: Int) {
        setId(product)
        appViewModel?.navigate(ShopsFrontDestination.SINGLE_PRODUCT)
    }

    fun onAddToCart() {

        //  appViewModel?.navigate(AppDestinations.PIN)
        appViewModel?.navigate(ShopsFrontDestination.CART)
    }
}