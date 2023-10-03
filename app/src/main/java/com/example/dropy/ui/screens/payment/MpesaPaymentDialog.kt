package com.example.dropy.ui.screens.payments.mpesapaymentdialog

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.commons.payments.MpesaPaymentDialogContent
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.rider.IncomingJobViewModel
import com.example.dropy.ui.screens.shops.backside.orderdetails.OrderDetailsViewModel
import com.example.dropy.ui.screens.shops.backside.shopincomingorders.ShopIncomingOrdersViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun MpesaPaymentDialog(
    mpesaPaymentDialogViewModel: MpesaPaymentDialogViewModel,
    navController: NavController? = null,
    shopIncomingOrdersViewModel: ShopIncomingOrdersViewModel,
    checkoutViewModel: CheckoutViewModel,
    appViewModel: AppViewModel,
    orderDetailsViewModel: OrderDetailsViewModel,
    incomingJobViewModel: IncomingJobViewModel,
    shopHomePageViewModel: ShopHomePageViewModel,
    cartPageViewModel: CartPageViewModel,
    appHomePageViewModel: AppHomePageViewModel
) {
    val uiState by mpesaPaymentDialogViewModel.mpesaPaymentDialogUiState.collectAsState()
    val orderuiState by checkoutViewModel.orderPaymentPageUiState.collectAsState()
    val homeuiState by shopHomePageViewModel.shopHomePageUiState.collectAsState()
    val apphomeuiState by appHomePageViewModel.homePageUiState.collectAsState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .sizeIn(minHeight = 120.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (uiState.show) {

            MpesaPaymentDialogContent(
                uiState = uiState,
                onPhoneNumberChanged = { mpesaPaymentDialogViewModel.onPhoneNumberChange(it) },
                onAmountChanged = { mpesaPaymentDialogViewModel.onAmountChange(it) },
                onPayClicked = {
                    scope.launch {
                        if (it.equals("pay")) {
                            //    mpesaPaymentDialogViewModel.onPayClicked()
                            if (uiState.phoneNumber.startsWith("254")) {
                                if (uiState.phoneNumber.length < 13) {

                                    if (navController != null) {
                                        /*  checkoutViewModel.pay(
                                              firebaseUid = appViewModel.appUiState.value.firebaseUid.toString(),
                                              phoneNumber = mpesaPaymentDialogViewModel.mpesaPaymentDialogUiState.value.phoneNumber,
                                              mpesaPaymentDialogViewModel = mpesaPaymentDialogViewModel,
                                              navController = navController
                                          )*/
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
                                            /*    it.shop?.id?.let { it1 ->
                                                    val res =
                                                        shopHomePageViewModel.getGetShopProducts(
                                                            it1.toString()
                                                        )

                                                    if (res != null) {
                                                        shopLatitude = res.shop_location?.latitude!!
                                                        shopLongitude =
                                                            res.shop_location.longitude!!
                                                    }
                                                }*/
                                            }

                                        }


                                        mpesaPaymentDialogViewModel.dismissDialog()
                                        Log.d(
                                            "TAGFF",
                                            "MpesaPaymentDialog: $shopLongitude  $shopLatitude"
                                        )
//                                        orderuiState.selectedDeliveryMethod?.let { it2 ->
                                            mpesaPaymentDialogViewModel.onPayClicked(
                                                appViewModel = appViewModel,
                                                shopLatitude = shopLatitude,
                                                shopLongitude = shopLongitude,
                                                deliveryMethodDataClass = orderuiState.selectedDeliveryMethod,
                                                cartPageViewModel = cartPageViewModel,
                                                paymentPageUiState = orderuiState,
                                                checkoutViewModel = checkoutViewModel,
                                                navController = navController,
                                                context = context
                                            )
//                                        }

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
                        } else mpesaPaymentDialogViewModel.dismissDialog()
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MpesaPaymentDialogPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // MpesaPaymentDialog(mpesaPaymentDialogViewModel = hiltViewModel(), )
    }
}