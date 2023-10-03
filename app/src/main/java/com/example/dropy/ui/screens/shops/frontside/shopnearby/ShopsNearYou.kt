package com.example.dropy.ui.screens.shops.frontside.shopnearby

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dropy.R
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.Dropdown
import com.example.dropy.ui.components.shops.frontside.shopnearby.ShopNearbyItem
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.apphome.SearchUiState
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel

@Composable
fun ShopsNearYou(
    modifier: Modifier,
    navController: NavController,
    searchUiState: SearchUiState,
    shopHomePageViewModel: ShopHomePageViewModel,
    cartPageViewModel: CartPageViewModel,
    appViewModel: AppViewModel,
    appHomePageViewModel: AppHomePageViewModel
) {

    LaunchedEffect(key1 = true, block = {
        appHomePageViewModel.setvalues()
    })

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(color = Color.White)
            .padding(vertical = 6.dp, horizontal = 8.dp)
        /*.verticalScroll(rememberScrollState())*/,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            Text(
                text = "${(if (searchUiState.filter.equals("shops")) searchUiState.shoplist.size else searchUiState.productlist.size)} ${searchUiState.searchName} SUPPLIERS NEAR YOU",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(49.dp)) {
                Dropdown(
                    filteredsearchitems = searchUiState.filtersearchlist,
                    onSelect = { name, int ->

                    },
                    onShopSelect = { _, _ ->
                    },
                    onSearchFilterSelect = {
                        appHomePageViewModel.setFilter(it)
                    },
                    type = "searchitems"
                )
            }

        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()

        ) {
            itemsIndexed(items = if (searchUiState.filter.equals("shops")) searchUiState.shoplist else searchUiState.productlist) { index, item ->

                val show = if (index % 2 == 0) {
                    true
                } else false

                ShopNearbyItem(
                    showBg = show,
                    shopNearbyItem = item,
                    navController = navController,
                    shopHomePageViewModel = shopHomePageViewModel,
                    cartPageViewModel = cartPageViewModel,
                    appViewModel = appViewModel,
                    appHomePageViewModel = appHomePageViewModel
                )
            }
        }


    }
}