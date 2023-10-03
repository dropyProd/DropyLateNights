package com.example.dropy.ui.screens.shops.frontside.singleshop

import android.Manifest
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.commons.EmptyBlock
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.shops.frontside.singleshop.shophome.ShopHomePageContent
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.shopslandingpage.ShopsLandingPageViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ShopHomePage(
    shopHomePageViewModel: ShopHomePageViewModel,
    cartPageViewModel: CartPageViewModel,
    appHomePageViewModel: AppHomePageViewModel,
    shopsLandingPageViewModel: ShopsLandingPageViewModel
) {

    val appUiState = shopHomePageViewModel.appViewModel!!.appUiState.collectAsState()
    val uiState by shopHomePageViewModel.shopHomePageUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()


    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val smspermission = rememberPermissionState(
        permission = Manifest.permission.CALL_PHONE
    )
    val callpermissionStates = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.SEND_SMS
        )
    )

    LaunchedEffect(key1 = true, block = {
        shopHomePageViewModel.setShopData()
        shopHomePageViewModel.getShopProducts()
    })

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner, effect = {
        val eventObserver = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    smspermission.launchPermissionRequest()

                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(eventObserver)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(eventObserver)
        }
    })
    AppScaffold(
        content = {
            if (uiState.shopProducts.isEmpty()) {
                EmptyBlock()
            } else {
                Box() {
                    GoogleMapWrapper() { _, _, _ ->
                    }
                    ShopHomePageContent(
                        uiState = uiState,
                        onCallShop = {
                            when {
                                smspermission.hasPermission -> {
                                    shopHomePageViewModel.onCallShop(context = context)
                                }
                                smspermission.shouldShowRationale -> {
                                    Toast.makeText(
                                        context,
                                        "Phone permission is required by this app",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    smspermission.launchPermissionRequest()
                                }
                                !smspermission.hasPermission && !smspermission.shouldShowRationale -> {
                                    Toast.makeText(
                                        context,
                                        "Permission fully denied. Go to settings to enable",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }


                        },
                        onEmailShop = { shopHomePageViewModel.onEmailShop(context = context) },
                        onFilterByCategory = { shopHomePageViewModel.onFilterByCategory(it) },
                        onProductSelected = {/* it.id?.let { it1 ->*/
                            shopHomePageViewModel.onProductSelected(
                                it
                            )
                            /*}*/
                        },
                        onAddToCart = { product ->
                            /*val cart = CartItemDataClass(
                                productId = 1,
                                productName = name,
                                cartItemsNumber = quantity,
                                cartItemsPrice = price,
                                cartItemTotal = price
                            )

                            shopHomePageViewModel.appViewModel!!.addItemCart(
                                item = cart
                            )*/
                            scope.launch {
                                /*                val res = cartPageViewModel.onAddCartItemClicked(
                                                    0,
                                                    id,
                                                    shopHomePageViewModel = shopHomePageViewModel
                                                )
                                                Log.d("njnlj", "ShopHomePage: $res")
                                                if (res?.resultCode?.equals(0) == true) {
                                                    val list = cartPageViewModel.getCustomerCart()
                                                    if (list?.isEmpty() == false) {
                                                        //    shopHomePageViewModel.onAddToCart()
                                                        Toast.makeText(
                                                            context,
                                                            "item added to cart",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    }
                                                }*/
                                if (shopHomePageViewModel.cartIdisNull())
                                    shopHomePageViewModel.createCart(
                                        product.id,
                                        cartPageViewModel,
                                        context = context
                                    )
                                else
                                    shopHomePageViewModel.addCartNew(
                                        product.id,
                                        cartPageViewModel,
                                        context = context
                                    )
                            }
                        },
                        categorySelected = {
                            shopHomePageViewModel.setFilteredCategory(it)

                        }
                    )
                }
            }
        },

        pageLoading = uiState.pageLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { shopHomePageViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { shopHomePageViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { shopHomePageViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { shopHomePageViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { shopHomePageViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showCart = true,
        showimg = false,
        showImageRight = true,
        showSearchBar = true,
        onSearchItem = appHomePageViewModel::onGoToSearch,
        searchItem = {
            appHomePageViewModel.onSearchItem(
                it,
                shopHomePageViewModel,
                shopsLandingPageViewModel = shopsLandingPageViewModel
            )
        },
        showLogo = false
    )

}


@Preview(showBackground = true)
@Composable
fun ShopHomePagePreview() {

    Column(modifier = Modifier.fillMaxSize()) {
        // ShopHomePage(shopHomePageViewModel = ShopHomePageViewModel())
    }
}