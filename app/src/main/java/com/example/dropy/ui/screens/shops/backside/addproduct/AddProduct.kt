package com.example.dropy.ui.screens.shops.backside.addproduct

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.backside.dashboard.product.AddProductContent
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.shops.backside.addshop.AddShopViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddProduct(
    addProductViewModel: AddProductViewModel,
    choosePhoto: (String) -> Unit,
    shopHomePageViewModel: ShopHomePageViewModel,
    route: String,
    addShopViewModel: AddShopViewModel,
    appHomePageViewModel: AppHomePageViewModel,
    clearImages: () -> Unit,
) {
    val appUiState = addProductViewModel.appViewModel!!.appUiState.collectAsState()

    val uiState by addProductViewModel.addProductUiState.collectAsState()
    val addshopuiState by addShopViewModel.addShopUiState.collectAsState()

    val context = LocalContext.current

    val scope = rememberCoroutineScope()
    val move = remember {
        mutableStateOf(0)
    }


    AppScaffold(
        content = {
            AddProductContent(
                uiState = uiState,
                onProductNameChanged = { addProductViewModel.onProductNameChanged(it) },
                onAddCoverPhoto = { choosePhoto("cover") },
                onProductDescriptionChanged = { addProductViewModel.onProductDescriptionChanged(it) },
                onProductCategorySelected = { name, indeex ->
                    addProductViewModel.onProductCategorySelected(
                        productCategory = name,
                        index = indeex
                    )
                },
                onProductPriceChanged = { addProductViewModel.onProductPriceChanged(it) },

                onProductUnitsChanged = { addProductViewModel.onProductUnitsChanged(it) },
                onAddProductPhotos = { choosePhoto("multiple") },
                onNumberInStackChanged = { addProductViewModel.onNumberInStackChanged(it) },
                onAddProduct = {
                    scope.launch {
                        addProductViewModel.onAddProduct(
                            context = context,
                            type = it,
                            shopHomePageViewModel = shopHomePageViewModel,
                            clearImages = clearImages,
                            appHomePageViewModel = appHomePageViewModel,
                            addShopUiState = addshopuiState,
                            currentList =   appHomePageViewModel.homePageUiState.value.popularShops.toMutableList(),
                            name = addShopViewModel.addShopUiState.value.shopName.toString()
                        )
                    }
                },
                route = route,
                onDeleteProduct = {
                    addProductViewModel.deleteProduct()
                },
                addProductViewModel = addProductViewModel,
                onDiscountPriceChanged = addProductViewModel::setDiscountPrice,
                onCouponCodeChanged = addProductViewModel::setCouponCode,
                onDiscountPercentageChanged = addProductViewModel::setDiscountPercentage
            )
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { addProductViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { addProductViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { addProductViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { addProductViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { addProductViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        showCart = false
    )
}


@Preview(showBackground = true)
@Composable
fun AddProductPreview() {
    // AddProduct(addProductViewModel = hiltViewModel(), choosePhoto = {})
}