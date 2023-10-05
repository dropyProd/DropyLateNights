package com.example.dropy.ui.components.shops.frontside.singleshop.shophome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.dropy.network.models.AllProductsResItem

import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageUiState


//public const val BaseUrL: String = "http://4.168.233.233:8000"
//public const val BaseUrL: String = "http://20.164.41.50:8000"
public const val BaseUrL: String = "https://api.dropy.ke"
//public const val BaseUrL: String = "https://www.youtube.com"

@Composable
fun ShopHomePageContent(
    uiState: ShopHomePageUiState,
    onCallShop: () -> Unit,
    onEmailShop: () -> Unit,
    onProductSelected: (Int) -> Unit,
    onAddToCart: (AllProductsResItem) -> Unit,
    onFilterByCategory: (categoryName: String) -> Unit,
    categorySelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        ShopHeader(
            numberOfFollowers = uiState.numberOfFollowers,
            numberOfOrders = uiState.numberOfOrders,
            shopName = uiState.shopName,
            shopCoverPhotoUrl = uiState.shopCoverPhotoUrl,
            shopLocation = uiState.shopLocation,
            onCallShop = { onCallShop() },
            onEmailShop = { onEmailShop() },
            shopDistance = uiState.shopDistance
        )
        ProductsGrid(
            productList =
                if (uiState.filteredCategory.equals("All items")) uiState.shopProducts else{
                    if (uiState.exist) {uiState.filteredList}else listOf()
                }
             ,
            onProductSelected = { onProductSelected(it.id!!) },
            onAddToCart = onAddToCart,
            productCategoryList = uiState.productCategories,
            onProductCategorySelected = { onFilterByCategory(it) },
            filteredCategory = uiState.filteredCategory,
            categorySelected = categorySelected
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShopHomePageContentPreview() {
    Column(Modifier.fillMaxSize()) {
        ShopHomePageContent(
            ShopHomePageUiState(),
            onEmailShop = {},
            onCallShop = {},
            onAddToCart = {
            },
            onProductSelected = {},
            onFilterByCategory = {},
            categorySelected = {}
        )
    }
}

//@Composable
//fun TY(){
//    Slider(
//        value = 100,
//        valueRange = 100..5000,
//        onValueChange = { newValue ->
//            // Update the value of the slider with the new value
//        }, Modifier.fillMaxWidth()
//    )
//
//}