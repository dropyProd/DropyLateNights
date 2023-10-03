package com.example.dropy.ui.components.shops.backside.dashboard.orders.orderlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.R
import com.example.dropy.network.models.ShopOrdersResponseItem
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.screens.shops.backside.orderdetails.OrderDetailsViewModel
import com.example.dropy.ui.screens.shops.backside.shopincomingorders.ShopIncomingOrdersUiState
import com.example.dropy.ui.screens.shops.frontside.checkout.trackyourorder.TrackYourOrderViewModel

@Composable
fun PendingOrdersContent(
    uiState: ShopIncomingOrdersUiState,
    onOrderSelected: (orderId: Int, shop: String, customer: String, ShopOrdersResponseItem?) -> Unit,
    onOrderStatusSelected:(OrderListItemDataClass)->Unit,
    orderDetailsViewModel: OrderDetailsViewModel,
    trackYourOrderViewModel: TrackYourOrderViewModel,
    appViewModel: AppViewModel
){

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ClippedHeader(title = "incoming orders")
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            SimpleText(
                text = "customer incoming orders",
                textSize = 14,
                isUppercase = true,
                isBold = false,
                font = Font(R.font.axiformablack),
                fontWeight = FontWeight.ExtraBold
            )

            OrdersList(
                title = "${uiState.orderList.size} pending orders",
                orders = uiState.orderList,
                onOrderSelected = onOrderSelected,
                onOrderStatusSelected = onOrderStatusSelected,
                orderDetailsViewModel = orderDetailsViewModel,
                onScanOrderClicked = { orderId, shopLatLng ->
               //     trackYourOrderViewModel.setShopLatLng(shopLatLng)
                    trackYourOrderViewModel.getPolylines(appViewModel)

                    trackYourOrderViewModel.getShopQr(orderId, appViewModel, context)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PendingOrdersContentPreview(){
    Column(Modifier.fillMaxSize()) {
        PendingOrdersContent(
            uiState = ShopIncomingOrdersUiState(),
            onOrderSelected = {_,_,_,_->},
            onOrderStatusSelected = {},
            orderDetailsViewModel = hiltViewModel(),
            appViewModel = hiltViewModel(),
            trackYourOrderViewModel = hiltViewModel()
        )
    }
}