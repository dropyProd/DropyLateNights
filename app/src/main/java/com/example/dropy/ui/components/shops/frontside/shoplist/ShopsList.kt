package com.example.dropy.ui.components.shops.frontside.shoplist

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.R
import com.example.dropy.network.models.ShopsResponseNewItem
import com.example.dropy.network.models.shops.Shop
import com.example.dropy.ui.components.commons.EmptyBlock
import kotlinx.coroutines.delay


@Composable
fun ShopsList(
    title: String = "",
    shopList: List<ShopsResponseNewItem>,
    favshopList: List<com.example.dropy.network.models.favouriteshop.favouriteShopRes.Shop>,
    onShopReview: () -> Unit,
    onShopSelected: (shopId: String,shopsResponseNewItem:ShopsResponseNewItem) -> Unit,
    likeClicked: (shop: ShopsResponseNewItem, Boolean) -> Unit,
    size: Int = 75,
    start: Int = 8,
    top: Int = 8,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = top.dp, start = start.dp)
    ) {
        /*    SimpleText(
                textSize = 12,
                text = title,
                isUppercase = true,
                isExtraBold = true
            )*/

        Text(
            text = "POPULAR SHOPS",
            fontSize = 10.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(
                Font(R.font.axiformaheavy)
            ),
            letterSpacing = (-0.58).sp
        )


        if (shopList.isEmpty()) {
            EmptyBlock()
        } else {
            LazyRow(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
            ) {
                items(items = shopList, itemContent = { item ->

                    val isLiked: MutableState<Boolean> = remember {
                        mutableStateOf(false)
                    }
                    LaunchedEffect(key1 = true, block = {
                        for (i in 1..4) {

                            if (i == 3) {
                                Log.d("heue", "ShopListItem: $favshopList")
                                favshopList.forEach {
                                    Log.d("heue", "ShopListIteyyym: $it")
                                    Log.d("heue", "ddada: $item")

                                    if (it.shop_name == item!!.shop_name!!) {
                                        isLiked.value = true
                                        Log.d("huhuyu", "ShopListItem: ${isLiked}")
                                        return@LaunchedEffect
                                    }
                                }
                            }
                            delay(2000)
                        }
                    })

                    item.shop_name?.let {
                        ShopListItem(
                            shopLogoUrl = item.shop_logo,
                            shopName = it,
                            onClicked = { item.id?.let { it1 -> onShopSelected(it1, item) } },
                            onShopReview = onShopReview,
                            size = size,
                            likeClicked = {
                                isLiked.value = !isLiked.value
                                likeClicked(item, it)
                            },
                            shop = item,
                            favshops = favshopList,
                            isLiked = isLiked.value
                        )
                    }
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShopsListPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val shopList = mutableListOf(
            ShopListItemDataClass(
                shopName = "shop 1",
                shopId = 1
            ),
            ShopListItemDataClass(
                shopName = "shop 2",
                shopId = 2
            ),
            ShopListItemDataClass(
                shopName = "shop 3",
                shopId = 3
            ),
            ShopListItemDataClass(
                shopName = "shop 4",
                shopId = 4
            ),
            ShopListItemDataClass(
                shopName = "shop 1",
                shopId = 5
            ),
        )
        ShopsList(
            title = "Popular shops",
            shopList = listOf(),
            onShopSelected = {_,_->},
            onShopReview = {},
            likeClicked = { _, _ -> },
            favshopList = listOf()
        )
    }
}