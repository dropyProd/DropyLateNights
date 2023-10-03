package com.example.dropy.ui.screens.shops.backside.shopproducts

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.network.models.AllProductsResItem
import com.example.dropy.network.models.shopdetails.Product
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.commons.appdrawer.ProfileTypes
import com.example.dropy.ui.components.shops.backside.dashboard.product.productslist.ShopProductsContent
import com.example.dropy.ui.screens.shops.backside.addproduct.AddProductViewModel
import com.example.dropy.ui.screens.shops.backside.addshop.AddShopViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel

@Composable
fun ShopProducts(
    shopProductsViewModel: ShopProductsViewModel,
    shopHomePageViewModel: ShopHomePageViewModel,
    addProductViewModel: AddProductViewModel,
    appViewModel: AppViewModel,
    addShopViewModel: AddShopViewModel,
    moveAddProduct: (product: AllProductsResItem) -> Unit,
    cartPageViewModel: CartPageViewModel
) {
    val appUiState = shopProductsViewModel.appViewModel!!.appUiState.collectAsState()
    val uiState by shopProductsViewModel.shopProductsUiState.collectAsState()
    val shopHomePageUiState by shopHomePageViewModel.shopHomePageUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()
    val context = LocalContext.current
    //val appuistate = appViewModel
   // Toast.makeText(context, "frrrtt", Toast.LENGTH_SHORT).show()
  //  Toast.makeText(context, "${appUiState.value.activeProfile?.type}", Toast.LENGTH_SHORT).show()
   // Log.d("fffg", "ShopProducts:${appUiState.value.activeProfile?.type} ")

    AppScaffold(
        content = {
            ShopProductsContent(
                uiState = uiState,
                shopHomePageUiState = shopHomePageUiState,
                onSearchParameterChanged = { shopProductsViewModel.onSearchParameterChanged(it) },
                onAddProductClicked = {
                  if (appViewModel.appUiState.value.activeProfile?.type.equals(ProfileTypes.SHOP)){
                      appViewModel.appUiState.value.activeProfile?.id?.let {
                          addProductViewModel.setId(
                              it.toString()
                          )
                      }
                  }

                    addShopViewModel.showoptionsDialog()
                },
                onSearchButtonClicked = { shopProductsViewModel.onSearchButtonClicked() },
                onCategorySelected = { shopHomePageViewModel.setFilteredCategory(it) },
                onProductSelected = { /*shopProductsViewModel.onProductSelected(it)*/
                    moveAddProduct(it)
                }
            )
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { shopProductsViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { shopProductsViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { shopProductsViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { shopProductsViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { shopProductsViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size
    )
}

@Preview(showBackground = true)
@Composable
fun ShopProductsPreview() {
    Column(Modifier.fillMaxSize()) {
        //ShopProducts(shopProductsViewModel = hiltViewModel())
    }
}