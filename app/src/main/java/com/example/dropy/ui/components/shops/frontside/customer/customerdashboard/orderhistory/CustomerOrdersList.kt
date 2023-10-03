package com.example.dropy.ui.components.shops.frontside.customer.customerdashboard.orderhistory

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.network.models.commondataclasses.shopPojo
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.shops.backside.dashboard.orders.orderlist.OrderListItemDataClass
import com.example.dropy.ui.theme.LightContainerBackground
import com.google.android.gms.maps.model.LatLng

@Composable
fun CustomerOrdersList(
    title: String,
    orders: List<CustomerOrderListItemDataClass> = listOf(),
    shopnames: List<shopPojo> = listOf(),
    shoporders: List<OrderListItemDataClass> = listOf(),
    onViewOrderReceipt: (orderId: Int) -> Unit,
    onViewOrderStatus: (orderId: Int,shoplocale: LatLng?, customerlocale: LatLng, placename: String, shopname: String) -> Unit,
    onCompleteOrder: (orderId: Int) -> Unit,
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

        if (!orders.isEmpty()) {
            orders.forEachIndexed { index, item ->
                Log.d("hytt", "CustomerOrdersList: $item")
                CustomerOrderListItem(
                    customerName = item.customerName,
                    customerProfilePhotoUrl = "https://api.dropy.co.ke/"+item.shop?.shop_logo,
                    orderNumber = item.orderNumber,
                    isProcessed = item.isProcessed,
                    numberOfItems = item.numberOfItems,
                    status = /*item.status*/item.isProcessed,
                    cost = item.cost,
                    isPaid = item.isPaid,
                    timeString = item.timeString,
                    backgroundColor = if (index % 2 == 0) {
                        LightContainerBackground
                    } else {
                        Color.White
                    },
                    onReceiptClick = { onViewOrderReceipt(item.orderId) },
                    onOrderStatusClick = { onViewOrderStatus(item.orderId, item.shopLocale, item.customerLocale, item.customerLocalename, item.shop?.shop_name.toString()) },
                    onCompleteOrderClick = { onCompleteOrder(item.orderId) },
                    onScanOrderClicked = { onScanOrderClicked(item.orderId, item.shopLocale) },
                    orderPackedbyShop = item.orderPackedbyShop,
                    orderPickedbyRider = item.orderPickedbyRider,
                    customerReceiveOrder = item.customerReceiveOrder,
                    shopname = item.shop?.shop_name,
                    shop = item.shop
                )
            }
        }else {
            shoporders.forEachIndexed { index, item ->

                Log.d("hytt", "CustomerOrdersList: $item")
               /*if (!item.isProcessed){*/
                   CustomerOrderListItem(
                       customerName = item.customerName,
                       customerProfilePhotoUrl = item.customerProfilePhotoUrl,
                       orderNumber = item.orderNumber,
                       isProcessed = item.isProcessed.toString(),
                       numberOfItems = item.numberOfItems,
                       status = /*item.status*/ if (item.isProcessed)"collected" else "uncollected",
                       cost = item.cost,
                       isPaid = item.isPaid,
                       timeString = item.timeString,
                       backgroundColor = if (index % 2 == 0) {
                           LightContainerBackground
                       } else {
                           Color.White
                       },
                       onReceiptClick = { onViewOrderReceipt(item.orderId) },
                       onOrderStatusClick = { item.deliveryLocale?.let {
                           onViewOrderStatus(item.orderId, item.shopLocale,
                               it, item.deliveryplacename,item.customerName)
                       } },
                       onCompleteOrderClick = { onCompleteOrder(item.orderId) },
                       type = "admin",
                       onScanOrderClicked = { onScanOrderClicked(item.orderId, item.shopLocale) },
                       orderPackedbyShop = item.orderPackedbyShop,
                       orderPickedbyRider = item.orderPickedbyRider,
                       customerReceiveOrder = item.customerReceiveOrder,
                       //shop = item.sho
                   )
              // }
            }
        }
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

        CustomerOrdersList(
            title = "pending orders",
            orders = listOf(),
            onCompleteOrder = {},
            onViewOrderReceipt = {},
            onViewOrderStatus = {_,_ ,_,_, _->},
            onScanOrderClicked = {_, _ ->}
        )
    }
}