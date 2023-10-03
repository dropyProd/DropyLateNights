package com.example.dropy.ui.components.shops.frontside.shoplist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.network.models.ShopsResponseNewItem
import com.example.dropy.network.models.shops.Shop
import com.example.dropy.ui.components.commons.EmptyBlock
import com.example.dropy.ui.components.commons.SimpleText

@Composable
fun FavouriteShopsList(
    title: String = "MY FAVORITES",
    shopList: List<com.example.dropy.network.models.favouriteshop.favouriteShopRes.Shop>,
    onShopSelected: (shopName: String, shopsResponseNewItem:ShopsResponseNewItem?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp)
    ) {
        /*    SimpleText(
                textSize = 12,
                text = title,
                isUppercase = true,
                isExtraBold = true,
                verticalPadding = 8
            )*/

        Text(
            text = title.uppercase(),
            fontSize = 10.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(
                Font(R.font.axiformaheavy)
            ),
            letterSpacing = (-0.58).sp
        )

        if (shopList.isEmpty()) {
            EmptyBlock(modifier = Modifier.padding(end = 14.dp))
        } else {
            LazyRow(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
            ) {
                items(items = shopList, itemContent = { item ->
                    item.shop_name?.let {
                        FavouriteShopListItem(
                            shopLogoUrl = item.shop_logo,
                            shopName = it,
                            onClick = { /*item.id?.let { it1 -> onShopSelected(it1) }*/
                            /*respomnse is missing shop id*/
                                onShopSelected(it, null)
                            }
                        )
                    }
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavouriteShopsListPreview() {
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
                shopId = 1
            ),
            ShopListItemDataClass(
                shopName = "shop 3",
                shopId = 1
            ),
            ShopListItemDataClass(
                shopName = "shop 4",
                shopId = 1
            ),
            ShopListItemDataClass(
                shopName = "shop 1",
                shopId = 1
            ),
        )
        FavouriteShopsList(
            title = "Favourite shops",
            shopList = listOf(),
            onShopSelected = {_,_ ->}
        )
    }
}