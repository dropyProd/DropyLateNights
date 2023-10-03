package com.example.dropy.ui.components.shops.frontside.customer.orderprocess.customercart

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.network.models.GetCartResItem
import com.example.dropy.network.models.ShopOrdersResponseItem
import com.example.dropy.network.models.cart.GetCartResponseItem
import com.example.dropy.ui.app.AppUiState
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageUiState
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun CartContent(
    uiState: CartPageUiState,
    onAddCartItemClicked: (productId: Int) -> Unit,
    onRemoveCartItemClicked: (productId: Int) -> Unit,
    onDeleteCartItemClicked: (productId: Int) -> Unit,
    placeOrder: (orderId: Int) -> Unit,
    checkoutOrder: (GetCartResItem) -> Unit,
    collectOrder: (GetCartResItem) -> Unit,
    cancelOrder: (orderId: Int) -> Unit,
    deleteOrder: (orderId: Int) -> Unit,
    refreshPage: () -> Unit
    ) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        val added = remember {
            mutableStateOf(false)
        }
        val total = remember {
            mutableStateOf(0)
        }

        /*if (uiState.orderList.isNotEmpty()){
            uiState.orderList.forEach {
                total.value += it.product?.product_price!!
            }
        }*/
        val sizelist = uiState.orderList.size
       /* Text(
            text = "CART",
            fontSize = 19.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(
                Font(R.font.axiformaextrabold)
            ),
            letterSpacing = (-0.91).sp,
            modifier = Modifier.padding(start = 16.dp)
        )*/
        ClippedHeader(title = "shop cart")


//
//        uiState.orderList.forEachIndexed { index, order ->
//            Log.d("TFFFAG", "CartContent: $index")
//            var ordertotal = remember {
//                mutableStateOf(0)
//            }

            Log.d("size", "CartContent: ${uiState.orderList.size}")
           // order.get_order_total?.let {
//                if (order.isNotEmpty() == true) {
                  if (uiState.orderList.isNotEmpty()){
                      CartSegment(
                          orderDetails = uiState.orderList[0],
                          onAddCartItemClicked = { price ->
                              onAddCartItemClicked(price)
                          },
                          onDeleteCartItemClicked = { price ->
                              //  onDeleteCartItemClicked(item, order)
                              onDeleteCartItemClicked(price)
                          },
                          onRemoveCartItemClicked = { price ->
                              //  onRemoveCartItemClicked(currentitem, editeditem, order)
                              onRemoveCartItemClicked(price)
                          },
                          checkoutOrder = { checkoutOrder(uiState.orderList[0]) },
                          collectOrder = { collectOrder(uiState.orderList[0]) },
                          placeOrder = {
                              uiState.orderList[0].id?.let { it1 -> placeOrder(it1) }
                              /*           scope.launch {
                                          for (i in 0 until 4) {
                                              when {
                                                  i.equals(1) -> {
                                                      val editedOrder = order.copy(isOrderPlaced = true, isOrderAccepted = true)
                                                      placeOrder(order, editedOrder)

                                                      refreshPage()
                                                  }
                                                *//*  i.equals(2) -> {
                                            val editedOrder = order.copy(isOrderPlaced = true)
                                            placeOrder(order, editedOrder)


                                        }
                                        i.equals(3) -> {
                                            val editedOrder = order.copy(
                                                isOrderPlaced = true,
                                                isOrderAccepted = false,
                                                isOrderDeclined = true
                                            )
                                            placeOrder(order, editedOrder)


                                        }
                                        i.equals(4) -> {
                                            val editedOrder = order.copy(
                                                isOrderPlaced = false,
                                                isOrderAccepted = false,
                                                isOrderDeclined = false
                                            )
                                            placeOrder(order, editedOrder)

                                        }*//*
                                    }
                                }
                            }
        */
                          },
                          cancelOrder = { uiState.orderList[0].id?.let { it1 -> cancelOrder(it1) } },
                          deleteOrder = { uiState.orderList[0].id?.let { it1 -> deleteOrder(it1) } },
                          subTotal = /*it*/uiState.total,
                          netTotal = /*order.get_order_total*/uiState.total,
                          ordersList = uiState.orderList,
                          order = uiState.myOrders
                      )
                  }
               // }
           // }
            if ((sizelist - 1).equals(0)) {
                added.value = true
            }
       // }
    }
}


@Preview(showBackground = true)
@Composable
fun CartContentPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CartContent(
            uiState = CartPageUiState(),
            onAddCartItemClicked = { _ -> },
            onDeleteCartItemClicked = { _ -> },
            onRemoveCartItemClicked = { _ -> },
            checkoutOrder = {},
            placeOrder = { _ ->
            },
            cancelOrder = {},
            deleteOrder = {},
            refreshPage = {},
            collectOrder = {}
        )
    }
}