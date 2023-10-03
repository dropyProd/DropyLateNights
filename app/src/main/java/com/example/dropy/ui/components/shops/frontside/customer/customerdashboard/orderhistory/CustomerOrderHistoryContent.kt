package com.example.dropy.ui.components.shops.frontside.customer.customerdashboard.orderhistory

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.R
import com.example.dropy.network.models.GetIndividualOrders.GetIndividualOrdersResItem
import com.example.dropy.network.models.createIndividualWaterOrder.AssignedTruck
import com.example.dropy.network.models.createIndividualWaterOrder.CreateIndividualWaterOrderRes
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.screens.shops.backside.shopincomingorders.ShopIncomingOrdersUiState
import com.example.dropy.ui.screens.shops.frontside.checkout.trackyourorder.TrackYourOrderViewModel
import com.example.dropy.ui.screens.shops.frontside.dashboard.orderhistory.CustomerOrderHistoryUiState
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import com.google.android.gms.maps.model.LatLng

@Composable
fun CustomerOrderHistoryContent(
    uiState: CustomerOrderHistoryUiState,
    onViewOrderReceipt: (orderId: Int) -> Unit,
    onViewOrderStatus: (orderId: Int) -> Unit,
    onCompleteOrder: (orderId: Int) -> Unit,
    navigate: (GetIndividualOrdersResItem) -> Unit,
    appViewModel: AppViewModel,
    trackYourOrderViewModel: TrackYourOrderViewModel,
    shopHomePageViewModel: ShopHomePageViewModel
) {

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
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
                font = Font(R.font.axiformablack),
                fontWeight = FontWeight.ExtraBold
            )

            Box(
                modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
            ) {
                SimpleText(
                    textSize = 10,
                    text = "${uiState.waterOrderList.size} orders",
                    isExtraBold = true,
                    isUppercase = true,
                    font = Font(R.font.axiformablack),
                    fontWeight = FontWeight.Bold
                )
            }
            LazyColumn(modifier = Modifier
                .padding(top = 22.dp),
                verticalArrangement = Arrangement.spacedBy(11.dp), content = {
                    itemsIndexed(uiState.waterOrderList) { index, item ->
                        val backgroundColor = if (index % 2 == 0)
                            Color.Transparent
                        else Color(0xFFF5F5F5)
                        waterOrderItem(
                            navigate = navigate,
                            color = backgroundColor,
                            getIndividualOrdersResItem = item
                        )
                    }

                })

            /*CustomerOrdersList(
                title = "${uiState.orderList.size} orders",
                orders = uiState.orderList,
                shopnames = uiState.shopnameList,
                onViewOrderReceipt = { onViewOrderReceipt(it) },
                onViewOrderStatus = { orderId, shopLatLng, custLatLng, placename, shopname ->
                    //  trackYourOrderViewModel.setShopLatLng(shopLatLng)
                    //  shopHomePageViewModel.setshoplatLng(shopLatLng)
                    //   trackYourOrderViewModel.getPolylines(appViewModel)
                    shopHomePageViewModel.setShopname(shopname)
                    trackYourOrderViewModel.setorderId(orderId)
                    Log.d(
                        "mihi",
                        "CustomerOrderHistoryContent: $orderId  :: $shopLatLng  :: $custLatLng :: $placename"
                    )
                    shopHomePageViewModel.getRouteDetails(use = true, shopLatLng, placename)
                    onViewOrderStatus(orderId)
                },
                onCompleteOrder = { onCompleteOrder(it) },
                onScanOrderClicked = { orderId, shopLatLng ->
                    trackYourOrderViewModel.setorderId(orderId)
                    trackYourOrderViewModel.getCustomerQr(appViewModel, context)
                    // appViewModel.navigate(ShopsFrontDestination.SCAN_QR_SHOP)
                }
            )*/
        }
    }
}

@Composable
fun waterOrderItem(
    navigate: (GetIndividualOrdersResItem) -> Unit,
    getIndividualOrdersResItem: GetIndividualOrdersResItem,
    color: Color
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .background(color = color, shape = RoundedCornerShape(12.dp))
        .border(width = 1.dp, color = Color(0xFFDEDEDE), shape = RoundedCornerShape(12.dp))
        .clickable {
               if (getIndividualOrdersResItem != null) {
                   navigate(getIndividualOrdersResItem)
               }
        }
    ) {

        Column(modifier = Modifier.padding(top = 24.dp)) {
            LoadImage(
                imageUrl = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .width(50.dp)
                    .height(50.dp)
                    .clip(CircleShape)
            )

         /*   Text(
                text = "ANTONY",
                fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformablack)
                ),
                letterSpacing = (-0.43).sp,
                lineHeight = 17.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 10.dp, start = 22.dp)
            )*/
        }

        Column(modifier = Modifier.padding(top = 16.dp)) {
            Text(
                text = "Order #${getIndividualOrdersResItem?.tracking_id}",
                fontSize = 11.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformablack)
                ),
                letterSpacing = (-0.53).sp,
                lineHeight = 21.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 18.dp)
            )

            Row(
                modifier = Modifier
                    .padding(top = 7.dp, start = 18.dp)
                    .wrapContentWidth()
                    .height(18.dp)
                    .background(
                        color = if (getIndividualOrdersResItem?.delivery_status.equals(
                                "S"
                            )
                        ) Color.Black else Color(0xFF979797),
                        shape = RoundedCornerShape(9.dp)
                    )
                /*.border(
                    width = 1.dp,
                    color = Color(0xFFD1D1D1),
                    shape = RoundedCornerShape(7.dp)
                )*/
            ) {
                Text(
                    text = if (getIndividualOrdersResItem?.delivery_status.equals(
                            "P"
                        )
                    ) "NOT PROCESSED" else if (getIndividualOrdersResItem?.delivery_status.equals(
                            "S"
                        )
                    ) "PROCESSED" else "DELIVERED",
                    fontSize = 7.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    ),
                    letterSpacing = (-0.34).sp,
                    lineHeight = 13.sp,
                    color = Color.White,
                    modifier = Modifier.padding(
                        start = 8.dp,
                        end = 9.dp,
                        top = 5.dp
//                        bottom = 1.dp
                    )
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 8.dp, start = 18.dp)
                    .wrapContentWidth()
                    .height(18.dp)
                    .background(
                        color = Color(0xFFC2F8FF),
                        shape = RoundedCornerShape(9.dp)
                    )
                /*.border(
                    width = 1.dp,
                    color = Color(0xFFD1D1D1),
                    shape = RoundedCornerShape(7.dp)
                )*/
            ) {
                Text(
                    text =
                    if (getIndividualOrdersResItem?.delivery_status.equals(
                            "P"
                        )
                    ) "NOT STARTED" else if (getIndividualOrdersResItem?.delivery_status.equals(
                            "S"
                        )
                    ) "ON THE WAY" else "DELIVERED",
                    fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformaextrabold)
                    ),
                    letterSpacing = (-0.43).sp,
                    lineHeight = 17.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(
                        start = 9.dp,
                        end = 9.dp,
                        top = 5.dp,
                        bottom = 4.dp
                    )
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {

            /*    val date = remember{
                    mutableStateOf("")
                }
                getIndividualOrdersRes.timestamp.forEachIndexed { index, time ->
                    if (index in 0..9){
                        date.value += time
                    }
                }
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val newDate = formatter.parse(date.value)
                val formatterNew = DateTimeFormatter.ofPattern("dd/MMM/yy")
                val formatDate = formatterNew.format(newDate)
    */

            Text(
                text = "formatDate",
                fontSize = 8.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformamedium)
                ),
                letterSpacing = (-0.38).sp,
                lineHeight = 15.sp,
                color = Color(0xFF979797),
                modifier = Modifier.padding(end = 7.dp)
            )
            Text(
                text = getIndividualOrdersResItem?.quantity.toString() + "LT",
                fontSize = 17.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformaheavy)
                ),
                letterSpacing = (-0.82).sp,
                lineHeight = 32.sp,
                color = Color.Black,
                modifier = Modifier.padding(end = 8.dp, top = 35.dp)
            )

          /*  Row(
                modifier = Modifier
                    .padding(top = 8.dp, end = 7.dp)
                    .width(68.dp)
                    .height(18.dp)
                    .background(
                        color = Color(0xFF02CBE3),
                        shape = RoundedCornerShape(12.dp)
                    )
                *//*.border(
                    width = 1.dp,
                    color = Color(0xFFD1D1D1),
                    shape = RoundedCornerShape(7.dp)
                )*//*
            ) {
                Text(
                    text = "ETA 12 HRS",
                    fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformaextrabold)
                    ),
                    letterSpacing = (-0.48).sp,
                    lineHeight = 19.sp,
                    color = Color.White,
                    modifier = Modifier.padding(
                        start = 8.dp,
                        end = 4.dp,
                        top = 4.dp,
                        bottom = 1.dp
                    )
                )
            }*/
        }

    }
}


@Preview(showBackground = true)
@Composable
fun OrderHistoryContentPreview() {
    Column(Modifier.fillMaxSize()) {

        /*val orders = mutableListOf(
            CustomerOrderListItemDataClass(
                orderId = 1,
                customerName = "name",
                orderNumber = "1234",
                isProcessed = true,
                numberOfItems = 4,
                cost = 4321,
                isPaid = true,
                timeString = "13:03",
                status = "picked",
                shopLocale = LatLng(0.0,0.0)
            ),
            CustomerOrderListItemDataClass(
                orderId = 1,
                customerName = "name",
                orderNumber = "1234",
                isProcessed = false,
                numberOfItems = 4,
                cost = 4321,
                isPaid = false,
                timeString = "13:03",
                status = "cancelled",
                shopLocale = LatLng(0.0,0.0)
            )
        )*/

        CustomerOrderHistoryContent(
            uiState = CustomerOrderHistoryUiState(),
            onCompleteOrder = {},
            onViewOrderReceipt = {},
            onViewOrderStatus = {},
            appViewModel = hiltViewModel(),
            trackYourOrderViewModel = hiltViewModel(),
            shopHomePageViewModel = hiltViewModel(),
            navigate = {}
        )
    }
}