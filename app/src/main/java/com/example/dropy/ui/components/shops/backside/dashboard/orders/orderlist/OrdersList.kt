package com.example.dropy.ui.components.shops.backside.dashboard.orders.orderlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.R
import com.example.dropy.network.models.ShopOrdersResponseItem
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.screens.shops.backside.orderdetails.OrderDetailsViewModel
import com.example.dropy.ui.theme.LightContainerBackground
import com.google.android.gms.maps.model.LatLng

@Composable
fun OrdersList(
    title: String,
    orders: List<OrderListItemDataClass>,
    onOrderSelected: (orderId: Int, shop: String, customer: String, ShopOrdersResponseItem?) -> Unit,
    onOrderStatusSelected: (OrderListItemDataClass) -> Unit,
    orderDetailsViewModel: OrderDetailsViewModel,
    onScanOrderClicked: (orderId: Int, LatLng?) -> Unit,
    ) {
    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            SimpleText(
                textSize = 10,
                text = title,
                isExtraBold = true,
                isUppercase = true,
                font = Font(R.font.axiformablack),
                fontWeight = FontWeight.Bold
            )
        }
        LazyColumn(content = {
            itemsIndexed(items = orders) { index, item ->
         /*    if (!item.isProcessed){*/
                 OrderListItem(
                     customerName = item.customerName,
                     customerProfilePhotoUrl = item.customerProfilePhotoUrl,
                     orderNumber = item.orderNumber,
                     isProcessed = item.isProcessed,
                     numberOfItems = item.numberOfItems,
                     status = /*item.status*/ if (item.isProcessed) "collected" else "uncollected",
                     cost = item.cost,
                     isPaid = item.isPaid,
                     timeString = item.timeString,
                     backgroundColor = if (index % 2 == 0) {
                         LightContainerBackground
                     } else {
                         Color.White
                     },
                     onClick = {
                         if (item.isProcessed == false) {
                             onOrderSelected(item.orderId, item.shopId, item.customerId, item.shopOrdersResponseItem)
                         }
                     },
                     onOrderStatusClick = {
                         onOrderStatusSelected(item)
                     },
                     orderDetailsViewModel = orderDetailsViewModel,
                     orderId = item.orderId,
                     onScanOrderClicked = {
                         onScanOrderClicked(item.orderId, item.shopLocale)
                     }
                 )
             //}

            }
        })
    }
}


@Preview(showBackground = true)
@Composable
fun OrderListsPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
       /* val orders = remember {
            mutableListOf(
                OrderListItemDataClass(
                    orderId = 1,
                    customerName = "name",
                    orderNumber = "1234",
                    isProcessed = false,
                    numberOfItems = 4,
                    cost = 4321,
                    isPaid = false,
                    timeString = "in progress",
                    status = "in progress",
                    shopLocale = LatLng(0.0, 0.0)
                ),
                OrderListItemDataClass(
                    orderId = 1,
                    customerName = "name",
                    orderNumber = "1234",
                    isProcessed = false,
                    numberOfItems = 4,
                    cost = 4321,
                    isPaid = false,
                    timeString = "in progress",
                    status = "in progress",
                    shopLocale = LatLng(0.0, 0.0)
                )
            )
        }*/
        OrdersList(
            title = "pending orders",
            orders = listOf(),
            onOrderSelected = {_,_,_,_ ->},
            onOrderStatusSelected = {},
            orderDetailsViewModel = hiltViewModel(),
            onScanOrderClicked = {_,_ ->}
        )
    }
}