package com.example.dropy.ui.screens.shops.frontside.checkout.payment

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.frontside.customer.orderprocess.payments.OrderPaymentPageContent
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.payments.mpesapaymentdialog.MpesaPaymentDialog
import com.example.dropy.ui.screens.payments.mpesapaymentdialog.MpesaPaymentDialogViewModel
import com.example.dropy.ui.screens.rider.IncomingJobViewModel
import com.example.dropy.ui.screens.shops.backside.orderdetails.OrderDetailsViewModel
import com.example.dropy.ui.screens.shops.backside.shopincomingorders.ShopIncomingOrdersViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import kotlinx.coroutines.launch

@Composable
fun OrderPaymentPage(
    checkoutViewModel: CheckoutViewModel,
    navController: NavController? = null,
    shopIncomingOrdersViewModel: ShopIncomingOrdersViewModel,
    appViewModel: AppViewModel,
    orderDetailsViewModel: OrderDetailsViewModel,
    incomingJobViewModel: IncomingJobViewModel,
    cartPageViewModel: CartPageViewModel,
    shopHomePageViewModel: ShopHomePageViewModel,
    appHomePageViewModel: AppHomePageViewModel
) {
    val appUiState = checkoutViewModel.appViewModel!!.appUiState.collectAsState()
    val uiState by checkoutViewModel.orderPaymentPageUiState.collectAsState()
    val context = LocalContext.current
    val cartuiState by cartPageViewModel.cartPageUiState.collectAsState()


    val mpesaPaymentDialogViewModel: MpesaPaymentDialogViewModel = hiltViewModel()
    val mpesauiState by mpesaPaymentDialogViewModel.mpesaPaymentDialogUiState.collectAsState()
    val homeuiState by shopHomePageViewModel.shopHomePageUiState.collectAsState()
    val orderuiState by checkoutViewModel.orderPaymentPageUiState.collectAsState()


    var press = remember {
        1
    }

    val scope = rememberCoroutineScope()
    AppScaffold(
        content = {
            OrderPaymentPageContent(
                uiState = uiState,
                onSelectPaymentMethod = { checkoutViewModel.onSelectPaymentMethod(it) },
                onAddPaymentMethod = { checkoutViewModel.onAddPaymentMethod(it) },
                onPayClicked = {
                    scope.launch {
                        if (navController != null) {
                            mpesaPaymentDialogViewModel.navigateAllocatingTruck(navController)
                        }
                        /*mpesaPaymentDialogViewModel.onPayClicked(
                            appViewModel = appViewModel,
                            shopLatitude = 0.0,
                            shopLongitude = 0.0,
                            deliveryMethodDataClass = orderuiState.selectedDeliveryMethod,
                            cartPageViewModel = cartPageViewModel,
                            paymentPageUiState = orderuiState,
                            checkoutViewModel = checkoutViewModel,
                            navController = navController!!,
                            context = context
                        )*/

                        /*if (mpesauiState.number == 1) {
                            mpesaPaymentDialogViewModel.openDialog()
                        } else {
                            if (mpesauiState.phoneNumber.startsWith("254")) {
                                if (mpesauiState.phoneNumber.length < 13) {

                                    if (navController != null) {
                                        *//*  checkoutViewModel.pay(
                                              firebaseUid = appViewModel.appUiState.value.firebaseUid.toString(),
                                              phoneNumber = mpesaPaymentDialogViewModel.mpesaPaymentDialogUiState.value.phoneNumber,
                                              mpesaPaymentDialogViewModel = mpesaPaymentDialogViewModel,
                                              navController = navController
                                          )*//*
                                        checkoutViewModel.createVirtualOrder(
                                            shopIncomingOrdersViewModel = shopIncomingOrdersViewModel,
                                            checkoutViewModel = checkoutViewModel,
                                            appViewModel = appViewModel,
                                            orderDetailsViewModel = orderDetailsViewModel,
                                            incomingJobViewModel = incomingJobViewModel
                                        )
                                        var shopLatitude = 0.0
                                        var shopLongitude = 0.0
                                        if (homeuiState.shopLatLng != null) {
                                            shopLongitude =
                                                homeuiState.shopLatLng?.longitude!!
                                            shopLatitude = homeuiState.shopLatLng?.latitude!!
                                        } else {

                                            cartPageViewModel.checkoutcurrent.forEach {
                                             *//*   it.shop?.id?.let { it1 ->
                                                    val res =
                                                        shopHomePageViewModel.getGetShopProducts(
                                                            it1.toString()
                                                        )

                                                    if (res != null) {
                                                        shopLatitude = res.shop_location?.latitude!!
                                                        shopLongitude =
                                                            res.shop_location.longitude!!
                                                    }
                                                }*//*
                                            }

                                        }


                                        mpesaPaymentDialogViewModel.dismissDialog()
                                        Log.d(
                                            "TAGFF",
                                            "MpesaPaymentDialog: $shopLongitude  $shopLatitude"
                                        )
                                        uiState.selectedDeliveryMethod?.let { it2 ->
                                            mpesaPaymentDialogViewModel.onPayClicked(
                                                appViewModel = appViewModel,
                                                shopLatitude = shopLatitude,
                                                shopLongitude = shopLongitude,
                                                deliveryMethodDataClass = it2,
                                                cartPageViewModel = cartPageViewModel,
                                                paymentPageUiState = uiState,
                                                checkoutViewModel = checkoutViewModel,
                                                navController = navController,
                                                context = context
                                            )
                                        }

                                        // navController.navigate(ShopsFrontDestination.PAYMENT_COMPLETE_ETA_DELIVERY)
                                    }

                                } else {
                                    Toast.makeText(context, "phone number long", Toast.LENGTH_SHORT)
                                        .show()
                                }
                                //delay(6000)

                            } else {
                                Toast.makeText(
                                    context,
                                    "phone number should start with 254***",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }*/
                    }
                },
                cartPageViewModel = cartPageViewModel,
                mpesauiState = mpesauiState
            )
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { checkoutViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { checkoutViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { checkoutViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { checkoutViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { checkoutViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        showCart = false,
        showImageRight = true
    )
    MpesaPaymentDialog(
        mpesaPaymentDialogViewModel = mpesaPaymentDialogViewModel,
        navController = navController,
        shopIncomingOrdersViewModel = shopIncomingOrdersViewModel,
        appViewModel = appViewModel,
        checkoutViewModel = checkoutViewModel,
        orderDetailsViewModel = orderDetailsViewModel,
        incomingJobViewModel = incomingJobViewModel,
        shopHomePageViewModel = shopHomePageViewModel,
        cartPageViewModel = cartPageViewModel,
        appHomePageViewModel = appHomePageViewModel
    )

}

@Preview(showBackground = true)
@Composable
fun OrderPaymentPagePreview() {
    Column(Modifier.fillMaxSize()) {
        //   OrderPaymentPage(checkoutViewModel = CheckoutViewModel())
    }
}