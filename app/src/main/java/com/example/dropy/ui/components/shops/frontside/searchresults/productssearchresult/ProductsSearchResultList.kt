package com.example.dropy.ui.components.shops.frontside.searchresults.productssearchresult

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
fun ProductsSearchResultList(
    productsSearchResultList:List<ProductsSearchResultListItemDataClass>,
    onProductSelected : ()->Unit,
    onAddProductClicked : ()->Unit,
){

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(8.dp)
    ){
        itemsIndexed(
            items = productsSearchResultList,
            itemContent = {index,item ->

                val modifier = if (index % 2 == 0){
                    Modifier
                        .background(Color(252, 211, 19, 38))
                        .border(
                            width = 2.dp,
                            color = Color(112, 112, 112, 30),
                            shape = RoundedCornerShape(16.dp)
                        )

                }else{
                    Modifier
                        .background(Color.White)
                }

                ProductsSearchResultListItem(
                    productPhotoUrl = item.productPhotoUrl,
                    productName = item.productName,
                    shopName = item.shopName,
                    distanceInMeters =item.distanceInMeters,
                    timeInMinutes = item.timeInMinutes,
                    rating = item.rating,
                    price = item.price,
                    onProductSelected = { onProductSelected() },
                    onAddProductClicked = { onAddProductClicked() },
                    modifier = modifier
                )
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ProductsSearchResultListPreview(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val productsSearchResultList = listOf(
            ProductsSearchResultListItemDataClass(
                productPhotoUrl = "",
                productName = "mama otis fish ",
                shopName = "mama otis kiosk",
                timeInMinutes = 67,
                distanceInMeters = 234,
                rating = 4.7,
                price = 1293,
            ),
            ProductsSearchResultListItemDataClass(
                productPhotoUrl = "",
                productName = "mama otis fish ",
                shopName = "mama otis kiosk",
                timeInMinutes = 67,
                distanceInMeters = 234,
                rating = 4.7,
                price = 1293,
            ),
            ProductsSearchResultListItemDataClass(
                productPhotoUrl = "",
                productName = "mama otis fish ",
                shopName = "mama otis kiosk",
                timeInMinutes = 67,
                distanceInMeters = 234,
                rating = 4.7,
                price = 1293,
            ),
        )
        ProductsSearchResultList(
            productsSearchResultList = productsSearchResultList,
            onProductSelected = {},
            onAddProductClicked = {}
        )
    }
}