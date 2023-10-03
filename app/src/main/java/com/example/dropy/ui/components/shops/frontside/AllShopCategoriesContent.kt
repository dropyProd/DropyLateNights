package com.example.dropy.ui.components.shops.frontside

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.shops.frontside.shopcategories.ShopsCategoryGrid
import com.example.dropy.ui.components.shops.shopscommons.billboards.ShopBillboard
import com.example.dropy.ui.screens.shops.frontside.allshopcategories.AllShopCategoriesPageUiState

@Composable
fun AllShopCategoriesContent(
    uiState: AllShopCategoriesPageUiState,
    onCategorySelected: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        ShopBillboard(coverurl = "", logourl = "")
        ShopsCategoryGrid(
            categoryList = uiState.categoryList,
            onCategorySelected = { onCategorySelected() }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun AllShopCategoriesContentPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AllShopCategoriesContent(
            uiState = AllShopCategoriesPageUiState(),
            onCategorySelected = {}
        )
    }
}