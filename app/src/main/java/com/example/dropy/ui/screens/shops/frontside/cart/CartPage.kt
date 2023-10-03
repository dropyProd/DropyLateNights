package com.example.dropy.ui.screens.shops.frontside.cart

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.frontside.customer.orderprocess.customercart.CartContent
import com.example.dropy.ui.screens.shops.backside.shopincomingorders.ShopIncomingOrdersViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.trackyourorder.TrackYourOrderViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CartPage(
    cartPageViewModel: CartPageViewModel,
    shopHomePageViewModel: ShopHomePageViewModel,
    trackYourOrderViewModel: TrackYourOrderViewModel,
    checkoutViewModel: CheckoutViewModel
) {
    val context = LocalContext.current
    val appUiState = cartPageViewModel.appViewModel!!.appUiState.collectAsState()
    val uiState by cartPageViewModel.cartPageUiState.collectAsState()
    val shopIncomingOrdersViewModel: ShopIncomingOrdersViewModel = hiltViewModel()

    val scope = rememberCoroutineScope()


    LaunchedEffect(key1 = true, block = {
        /*  cartPageViewModel.getCustomerCart()
          cartPageViewModel.getCustomerCart()
          cartPageViewModel.getCustomerCart()*/
        cartPageViewModel.setCartList()
        shopIncomingOrdersViewModel.getPendingOrders(context = context)
        for (i in 0..4) {
            if (i == 3) {
                cartPageViewModel.sortOrders(
                    shopHomePageViewModel,
                    cartPageViewModel,
                    context = context
                )
            }
            Log.d("delay", "CartPage: delay $i")
            delay(2000)
        }

    })

    AppScaffold(
        content = {
            /*      if (uiState.pageLoading){
                      CircularProgressIndicator()
                  }else{*/
            CartContent(
                uiState = uiState,
                onAddCartItemClicked = { price ->
                    scope.launch {
                        cartPageViewModel.onAddCartItemClicked(0, price)
                    }
                    // cartPageViewModel.appViewModel!!.navigate(ShopsFrontDestination.CART)
                },
                onDeleteCartItemClicked = { price ->
                    cartPageViewModel.onDeleteCartItemClicked(
                        0, price
                    )
                    // cartPageViewModel.appViewModel!!.navigate(ShopsFrontDestination.CART)

                },
                onRemoveCartItemClicked = { price ->
                    cartPageViewModel.onRemoveCartItemClicked(0, price)

                },
                placeOrder = { orderId ->
                    /* cartPageViewModel.appViewModel!!.editOrder(
                         currentoOrder = currentOrder,
                         editedOrder = editedOrder
                     )*/
                    cartPageViewModel.placeOrder(
                        orderId,
                        appViewModel = cartPageViewModel.appViewModel!!,
                        context
                    )
                },
                checkoutOrder = {
                    Log.d("TAG", "CartPage: ${it.id}")
                    /*cartPageViewModel.checkoutOrder(
                        it,
                        shopHomePageViewModel = shopHomePageViewModel,
                        trackYourOrderViewModel = trackYourOrderViewModel, context
                    )*/
                    cartPageViewModel.checkoutOrderNew(context = context)
                },
                collectOrder = {
                    Log.d("TAG", "CartPage: ${it.id}")
                    checkoutViewModel.setDeliveryPrice(0)

                    /*cartPageViewModel.checkoutOrder(
                        it,
                        shopHomePageViewModel = shopHomePageViewModel,
                        trackYourOrderViewModel = trackYourOrderViewModel, context,
                        type = "collect"
                    )*/
                    cartPageViewModel.checkoutOrderNew(type = "collect", context = context)

                },
                cancelOrder = {
                    cartPageViewModel.cancelOrder(
                        it,
                        cartPageViewModel.appViewModel!!,
                        context
                    )
                },
                deleteOrder = { cartPageViewModel.deleteOrder(it, context = context) },
                refreshPage = {
                    cartPageViewModel.appViewModel!!.navigate(ShopsFrontDestination.CART)
                }
            )
            //}
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { cartPageViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { cartPageViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { cartPageViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { cartPageViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { cartPageViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = uiState.orderList.size,
        showLogo = false
    )

}

@Preview(showBackground = true)
@Composable
fun CartPagePreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        // CartPage(cartPageViewModel = CartPageViewModel())
    }
}