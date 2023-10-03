package com.example.dropy.ui.screens.shops.frontside.shopslandingpage

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.frontside.ShopsLandingPageContent
import com.example.dropy.ui.components.shops.frontside.singleshop.shophome.ShopHeader
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.shops.backside.addshop.AddShopViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShopsLandingPage(
    shopsLandingPageViewModel: ShopsLandingPageViewModel,
    appHomePageViewModel: AppHomePageViewModel,
    addShopViewModel: AddShopViewModel,
    cartPageViewModel: CartPageViewModel,
    shopHomePageViewModel: ShopHomePageViewModel
) {

    val appUiState = shopsLandingPageViewModel.appViewModel!!.appUiState.collectAsState()
    val uiState by shopsLandingPageViewModel.shopLandingPageUiState.collectAsState()
    val addshopuiState by addShopViewModel.addShopUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    val homePageUiState by appHomePageViewModel.homePageUiState.collectAsState()
    val shophomePageUiState by shopHomePageViewModel.shopHomePageUiState.collectAsState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(key1 = true, block = {
        /*   val range = if (!homePageUiState.popularShops.isEmpty()){
               if (homePageUiState.popularShops.size > 2){
                   Random.nextInt(0, (homePageUiState.popularShops.size - 1))
               }else {
                   0
               }
           }else {
               0
           }*/
        if (!homePageUiState.popularShops.isEmpty()) {
            if (homePageUiState.popularShops.size >= 2) {
                homePageUiState.popularShops[Random.nextInt(
                    0,
                    homePageUiState.popularShops.size - 1
                )].let {
                    shopHomePageViewModel.setShopId(it.id.toString())
                    shopHomePageViewModel.setShop(it)
                    shopHomePageViewModel.setShopData()
                    shopHomePageViewModel.getShopProducts()
                    /*     shopHomePageViewModel.getGetShopProducts(
                             it,
                             shopsLandingPageViewModel = shopsLandingPageViewModel
                         )*/
                }
            } else {
                homePageUiState.popularShops[0].let {
                    shopHomePageViewModel.setShopId(it.id.toString())
                    shopHomePageViewModel.setShop(it)
                    shopHomePageViewModel.setShopData()
                    shopHomePageViewModel.getShopProducts()
                    /*     shopHomePageViewModel.getGetShopProducts(
                             it,
                             shopsLandingPageViewModel = shopsLandingPageViewModel
                         )*/
                }
            }
        }
    })


    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )

    var isSheetFullScreen by remember { mutableStateOf(false) }
    val roundedCornerRadius = if (isSheetFullScreen) 0.dp else 12.dp
    val modifier = if (isSheetFullScreen)
        Modifier
            .fillMaxSize()
    else
        Modifier.fillMaxWidth()

    val shopHomeuiState by shopHomePageViewModel.shopHomePageUiState.collectAsState()


    //on Backpressed
    BackHandler(modalSheetState.isVisible) {
        coroutineScope.launch { modalSheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(
            topStart = roundedCornerRadius,
            topEnd = roundedCornerRadius
        ),
        sheetContent = {
            Column(
                modifier = modifier.padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                ShopHeader(
                    numberOfFollowers = shopHomeuiState.numberOfFollowers,
                    numberOfOrders = shopHomeuiState.numberOfOrders,
                    shopName = shopHomeuiState.shopName,
                    shopCoverPhotoUrl = shopHomeuiState.shopCoverPhotoUrl,
                    shopLocation = shopHomeuiState.shopLocation,
                    onCallShop = { /*onCallShop()*/ },
                    onEmailShop = { /*onEmailShop()*/ },
                    shopDistance = shopHomeuiState.shopDistance,
                    shopDescription = shopHomeuiState.shopDescription,
                    bottomSheet = true,
                    onGoToShop = {
                        appHomePageViewModel.navigateShop()
                    }
                )
                /*Button(
                    onClick = {
                        isSheetFullScreen = !isSheetFullScreen
                    }
                ) {
                    Text(text = "Toggle Sheet Fullscreen")
                }

                Button(
                    onClick = {
                        coroutineScope.launch { modalSheetState.hide() }
                    }
                ) {
                    Text(text = "Hide Sheet")
                }*/
            }
        }
    ) {

        AppScaffold(
            content = {
                ShopsLandingPageContent(
                    uiState = uiState,
                    appuiState = homePageUiState,
                    onAllCategoriesClicked = { shopsLandingPageViewModel.onAllCategorySelected() },
                    onCategorySelected = { shopsLandingPageViewModel.onCategorySelected() },
                    onShopReview = { shopsLandingPageViewModel.onShopReviewSelected() },
                    onShopSelected = { id, shop ->
                        appHomePageViewModel.onShopSelected(
                            id, shop,
                            shopHomePageViewModel,
                            shopsLandingPageViewModel = shopsLandingPageViewModel
                        )
                        shopHomePageViewModel.setShopData()
                        coroutineScope.launch {
                            if (modalSheetState.isVisible)
                                modalSheetState.hide()
                            else
                                modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                        }
                    },
                    addshopuiState = addshopuiState,
                    searchItem = {
                        appHomePageViewModel.onSearchItem(
                            it,
                            shopHomePageViewModel,
                            shopsLandingPageViewModel = shopsLandingPageViewModel
                        )
                    },
                    likeClicked = appHomePageViewModel::onFavouriteClicked,
                    shopHomeuiState = shophomePageUiState,
                    onAddToCart = { product ->
                        scope.launch {
                            /*val res = cartPageViewModel.onAddCartItemClicked(
                                0,
                                it,
                                shopsLandingPageViewModel = shopsLandingPageViewModel
                            )
                            Log.d("njnlj", "ShopHomePage: $res")
                            if (res?.resultCode?.equals(0) == true) {
                                val list = cartPageViewModel.getCustomerCart()
                                if (list?.isEmpty() == false) {
                                    shopHomePageViewModel.onAddToCart()
                                }
                            }*/
                            if (shopHomePageViewModel.cartIdisNull())
                                shopHomePageViewModel.createCart(product.id, cartPageViewModel, context = context)
                            else
                                shopHomePageViewModel.addCartNew(product.id, cartPageViewModel, context = context)
                        }
                    },
                    navigateCategorySingle = {
                        shopsLandingPageViewModel.appViewModel!!.navigate(ShopsFrontDestination.CATEGORY_SINGLE)
                    },
                    onSearchItem = appHomePageViewModel::onGoToSearch
                )
            },
            pageLoading = uiState.pageLoading,
            actionLoading = uiState.actionLoading,
            errorList = uiState.errorList,
            messageList = uiState.messageList,
            onBackButtonClicked = { shopsLandingPageViewModel.appViewModel?.onBackButtonClicked() },
            onDashboardButtonClicked = { shopsLandingPageViewModel.appViewModel?.onDashboardButtonClicked() },
            onCartButtonClicked = { shopsLandingPageViewModel.appViewModel?.onCartButtonClicked() },
            navigateTo = { shopsLandingPageViewModel.appViewModel?.navigate(it) },
            drawerMenuItems = appUiState.value.drawerMenuItems,
            userProfiles = appUiState.value.userProfiles,
            onSelectProfile = { shopsLandingPageViewModel.appViewModel?.onSelectProfile(it) },
            activeProfile = appUiState.value.activeProfile,
            cartsize = cartUiState.value.orderList.size,
            showLogo = false,
            showImageRight = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShopsLandingPagePreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        //  ShopsLandingPage(shopsLandingPageViewModel = ShopsLandingPageViewModel())
    }
}