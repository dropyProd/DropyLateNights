package com.example.dropy.ui.components.shops.frontside

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.network.models.ShopsResponseNewItem
import com.example.dropy.network.models.shops.Shop
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.shops.frontside.shoplist.ShopsList
import com.example.dropy.ui.components.shops.shopscommons.ProductAndShopSearchBar
import com.example.dropy.ui.screens.shops.frontside.shopsbycategory.ShopsByCategoryPageUiState

@Composable
fun ShopsByCategoryContent(
    uiState: ShopsByCategoryPageUiState,
    onShopSelected: (shopId: String, ShopsResponseNewItem) -> Unit,
    onShopReview: () -> Unit,
    likeClicked: (shop: ShopsResponseNewItem, Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            MapComponent()
//            ProductAndShopSearchBar()
        }
        Box(
            modifier = Modifier
                .padding(16.dp)
        ) {
            ShopsList(
                title = "${uiState.categoryName} shops near you",
                shopList = uiState.shopList,
                onShopSelected = { id, shop->
                    onShopSelected(id, shop) },
                onShopReview = { onShopReview() },
                likeClicked = likeClicked,
                favshopList = listOf()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShopsByCategoryContentPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ShopsByCategoryContent(
            uiState = ShopsByCategoryPageUiState(),
            onShopSelected = {_, _->},
            onShopReview = {},
            likeClicked = {_,_ -> })
    }
}