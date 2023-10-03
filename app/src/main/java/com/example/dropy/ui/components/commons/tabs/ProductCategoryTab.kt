package com.example.dropy.ui.components.commons.tabs

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.dropy.network.models.shopdetails.ProductCategory
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductCategoryTab(
    tabs: List<ProductCategory>,
    pagerState: PagerState,
    tabSelected: (String) -> Unit,
    filtered: String
) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true, block = {
        tabs.forEachIndexed { index, productCategory ->
            if (filtered.equals(productCategory.category_name)){

            }
        }
    })

    // OR ScrollableTabRow()
    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.Transparent,
        contentColor = Color.LightGray,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }) {
        tabs.forEachIndexed { index, tab ->
            // OR Tab()

            Tab(
                text = { tab.category_name?.let { Text(it, fontSize = 10.sp) } },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        tabSelected(tab.category_name!!)

                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}