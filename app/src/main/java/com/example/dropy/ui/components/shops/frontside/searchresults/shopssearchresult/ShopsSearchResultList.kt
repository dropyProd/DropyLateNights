package com.example.dropy.ui.components.shops.frontside.searchresults.shopssearchresult

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShopsSearchResultList(
    shopsSearchResultList:List<ShopsSearchResultListItemDataClass>,
    onShopSelected : ()->Unit
){

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(8.dp)
    ){

        itemsIndexed(items = shopsSearchResultList, itemContent = {index,item ->

            val modifier = if (index % 2 == 0){
                Modifier
                    .padding(bottom = 8.dp)
                    .background(Color(252, 211, 19, 38))
                    .border(
                        width = 2.dp,
                        color = Color(112, 112, 112, 30),
                        shape = RoundedCornerShape(16.dp)
                    )

            }else{
                Modifier
                    .padding(bottom = 8.dp)
                    .background(Color.White)
            }

            Box(
                modifier = modifier

            ) {
                ShopsSearchResultListItem(
                    shopLogoUrl = item.shopLogoUrl,
                    shopName = item.shopName,
                    shopLocation = item.shopLocation,
                    distanceInMeters =item.distanceInMeters,
                    timeInMinutes = item.timeInMinutes,
                    rating = item.rating,
                    onShopSelected = {onShopSelected()}
                )
            }

        })
    }
}

@Preview(showBackground = true)
@Composable
fun ShopsSearchResultListPreview(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val shopsSearchResultList = listOf(
            ShopsSearchResultListItemDataClass(
                shopLogoUrl = "",
                shopName = "shop name",
                shopLocation = "some shop location",
                timeInMinutes = 67,
                distanceInMeters = 234,
                rating = 4.7
            ),
            ShopsSearchResultListItemDataClass(
                shopLogoUrl = "",
                shopName = "shop name",
                shopLocation = "some shop location",
                timeInMinutes = 67,
                distanceInMeters = 234,
                rating = 4.7
            ),
            ShopsSearchResultListItemDataClass(
                shopLogoUrl = "",
                shopName = "shop name",
                shopLocation = "some shop location",
                timeInMinutes = 67,
                distanceInMeters = 234,
                rating = 4.7
            ),
        )
        ShopsSearchResultList(
            shopsSearchResultList = shopsSearchResultList,
            onShopSelected = {}
        )
    }
}