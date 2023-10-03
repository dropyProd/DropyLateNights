package com.example.dropy.ui.components.commons.tabs

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dropy.network.models.shopdetails.Product
import com.example.dropy.network.models.shopdetails.ProductCategory
import com.example.dropy.ui.components.shops.frontside.singleshop.shophome.ProductListItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductCategoryTabContent(
    pagerState: PagerState,
    productCategoryList: List<ProductCategory>,
    subLists: List<Product?>,
    onAddToCart: ( Int) -> Unit,
    onProductSelected: (productId: Int) -> Unit,
    categoryName: String
) {
    HorizontalPager(state = pagerState) { page ->

        val filteredList: MutableList<Product?> = remember {
            mutableListOf(null)
        }

        LaunchedEffect(key1 = true, block = {
            filteredList.clear()
            Log.d("caategoryname", "ProductCategoryTabContent: ${page}")

            Log.d("caategoryname", "ProductCategoryTabContent: ${categoryName}")
            Log.d("caategoryname", "ProductCategoryTabContent: ${subLists}")
            subLists.forEachIndexed { index, item ->
                when {
                    item?.product_category?.category_name.equals(categoryName) -> {
                        filteredList.add(item)
                    }
                    categoryName.equals("") -> {
                        filteredList.add(item)
                    }
                }
            }

        })


    }
}