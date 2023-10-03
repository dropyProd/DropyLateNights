package com.example.dropy.ui.screens.shops.frontside.productpage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.frontside.customer.orderprocess.customercart.CartItemDataClass
import com.example.dropy.ui.components.shops.frontside.customer.orderprocess.customercart.OrderDetailsDataClass
import com.example.dropy.ui.components.shops.frontside.singleshop.singleproduct.ProductPageContent
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import kotlinx.coroutines.launch

@Composable
fun ProductPage(
    productPageViewModel: ProductPageViewModel,
    cartPageViewModel: CartPageViewModel,
    shopHomePageViewModel: ShopHomePageViewModel
) {

    val appUiState = productPageViewModel.appViewModel!!.appUiState.collectAsState()
    val uiState by productPageViewModel.productPageUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    val scope = rememberCoroutineScope()
    val context = LocalContext.current


    AppScaffold(
        content = {
            ProductPageContent(
                uiState = uiState,
                onAddToCart = {
                    scope.launch {
//                        shopHomePageViewModel.id.value?.let {
                        /*    cartPageViewModel.onAddCartItemClicked(
                                0,
                                it,
                                productPageViewModel = productPageViewModel
                            )*/
                        if (shopHomePageViewModel.cartIdisNull())
                            shopHomePageViewModel.createCart(
                                shopHomePageViewModel.id.value,
                                cartPageViewModel,
                                context = context
                            )
                        else
                            shopHomePageViewModel.addCartNew(
                                shopHomePageViewModel.id.value,
                                cartPageViewModel,
                                context
                            )
//                        }
                    }
                    /*      val cart = CartItemDataClass(
                              productId = 1,
                              productName = name,
                              cartItemsNumber = quantity,
                              cartItemsPrice = price,
                              cartItemTotal = totalprice
                          )

                          productPageViewModel.appViewModel!!.addItemCart(
                              item = cart
                          )*/
                    //productPageViewModel.onAddToCart()
                },
                pricee = uiState.productPrice
            )
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { productPageViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { productPageViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { productPageViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { productPageViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { productPageViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = true
    )

}


@Preview(showBackground = true)
@Composable
fun ProjectPagePreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        /*      val viewModel = ProductPageViewModel()
              viewModel.appViewModel = AppViewModel()
              ProductPage(viewModel)*/
    }
}