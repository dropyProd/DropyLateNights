package com.example.dropy.ui.components.shops.frontside.shopcategories

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.ui.components.commons.SimpleText

@Composable
fun ShopsCategoryGrid(
    categoryList: List<ShopsCategoryListItemDataClass>,
    onCategorySelected : ()-> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        SimpleText(
            textSize = 12,
            text = "categories",
            isUppercase = true,
            isExtraBold = true,
            padding = 8
        )
        val subLists = mutableListOf<List<ShopsCategoryListItemDataClass>>()

        var start = 0
        var end = 4
        val last : Int = categoryList.size
        while (start != last){
            if (end >= last){ end = last}
            val sublist = categoryList.subList(start, end)
            subLists.add(sublist)
            start = end
            end += 4
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ){
            itemsIndexed(items = subLists,itemContent = {index, item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, start = 8.dp)
                    ,
                ) {
                    for (i in item){
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.CenterStart
                        ){
                            ShopCategoryListItem(
                                categoryImageUrl = i.categoryImageUrl,
                                categoryName = i.categoryName,
                                onCategorySelected = {onCategorySelected()},
                                index = index
                            )
                        }
                    }
                }
            }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShopsCategoryGridPreview(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        val categoryList = mutableListOf(
            ShopsCategoryListItemDataClass(
                categoryName = "category name"
            ),
            ShopsCategoryListItemDataClass(
                categoryName = "category name"
            ),
            ShopsCategoryListItemDataClass(
                categoryName = "category name"
            ),
            ShopsCategoryListItemDataClass(
                categoryName = "category name"
            ),
            ShopsCategoryListItemDataClass(
                categoryName = "category name"
            )
        )

        ShopsCategoryGrid(
            categoryList = categoryList,
            onCategorySelected = {}
        )

    }
}