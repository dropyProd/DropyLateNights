package com.example.dropy.ui.components.shops.backside.dashboard.orders.orderlist

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.R
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.shops.frontside.customer.customerdashboard.orderhistory.CustomerOrdersList
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.screens.shops.backside.shopincomingorders.ShopIncomingOrdersUiState
import com.example.dropy.ui.screens.shops.backside.shoporderhistory.ShopOrderHistoryUiState
import com.example.dropy.ui.screens.shops.frontside.checkout.trackyourorder.TrackYourOrderViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel

@Composable
fun OrderHistoryContent(
    uiState: ShopOrderHistoryUiState,
    orderuiState: ShopIncomingOrdersUiState,
    onOrderSelected: (orderId: Int) -> Unit,
    appViewModel: AppViewModel,
    changeType: () -> Unit,
    trackYourOrderViewModel: TrackYourOrderViewModel,
    shopHomePageViewModel: ShopHomePageViewModel
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ClippedHeader(title = "order history")
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            SimpleText(
                text = "your previous orders",
                textSize = 14,
                isUppercase = true,
                isBold = true,
                font = Font(R.font.axiformablack),
                fontWeight = FontWeight.ExtraBold
            )

            CustomerOrdersList(
                title = "${orderuiState.completedorderList.size} completed orders",
                shoporders = orderuiState.completedorderList,
                onViewOrderReceipt = {},
                onViewOrderStatus = {orderId, shopLatLng, custLatLng, placename,shopname  ->
                    changeType()
                 /*   trackYourOrderViewModel.setShopLatLng(shopLatLng)
                    trackYourOrderViewModel.getPolylines(appViewModel)
                    appViewModel.navigate(ShopsFrontDestination.TRACK_YOUR_ORDER)*/
               //     trackYourOrderViewModel.setShopLatLng(shopLatLng)
                //    shopHomePageViewModel.setshoplatLng(shopLatLng)
                    //   trackYourOrderViewModel.getPolylines(appViewModel)
                    shopHomePageViewModel.setShopname(shopname)
                    Log.d("mihi", "CustomerOrderHistoryContent: $orderId  :: $shopLatLng  :: $custLatLng :: $placename")
                    shopHomePageViewModel.getRouteDetails(use = true, shopLatLng, placename)
                  //  onViewOrderStatus(orderId)
                    appViewModel?.navigate(ShopsFrontDestination.TRACK_YOUR_ORDER)
                                    },
                onCompleteOrder = { onOrderSelected(it) },
                onScanOrderClicked = { orderId, shopLatLng ->
              //      trackYourOrderViewModel.setShopLatLng(shopLatLng)
                    trackYourOrderViewModel.getPolylines(appViewModel)
                    trackYourOrderViewModel.getShopQr(orderId, appViewModel, context)

                }
            )
            /*OrdersList(
                title = "${orderuiState.orderList.size} pending orders",
                orders = cc,
                onOrderSelected = {onOrderSelected(it)}
            )*/
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderHistoryContentPreview() {
    Column(Modifier.fillMaxSize()) {
        OrderHistoryContent(
            uiState = ShopOrderHistoryUiState(),
            onOrderSelected = {},
            orderuiState = ShopIncomingOrdersUiState(),
            appViewModel = hiltViewModel(),
            changeType = {},
            trackYourOrderViewModel = hiltViewModel(),
            shopHomePageViewModel = hiltViewModel()
        )
    }
}