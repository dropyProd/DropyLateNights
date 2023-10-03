package com.example.dropy.ui.components.shops.frontside.singleshop.shophome

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.network.models.AllProductsResItem
import com.example.dropy.network.models.shopdetails.Product
import com.example.dropy.network.models.shopdetails.ProductCategory
import com.example.dropy.ui.components.commons.EmptyBlock
import com.example.dropy.ui.components.commons.tabs.ProductCategoryTab
import com.example.dropy.ui.components.commons.tabs.ProductCategoryTabContent
import com.example.dropy.ui.components.shops.shopscommons.CategorySelector
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductsGrid(
    productList: List<AllProductsResItem>,
    productCategoryList: List<ProductCategory>,
    onProductCategorySelected: (categoryName: String) -> Unit,
    onAddToCart: (AllProductsResItem) -> Unit,
    onProductSelected: (AllProductsResItem) -> Unit,
    filteredCategory: String,
    categorySelected: (String) -> Unit
) {

    //val subLists = mutableListOf<List<Product?>>()

    val pagerState = rememberPagerState(pageCount = productCategoryList.size)

    /*   fun subdivide() {
           var start = 0
           var end = 3
           val last : Int = productList.size
           while (start != last){
               if (end >= last){ end = last}
               val subList = productList.subList(start, end)
               subLists.add(subList)
               start = end
               end += 3
           }
       }
       subdivide()*/


/*
    LaunchedEffect(key1 = true, block = {
        Log.d("ugyutug", "ProductsGriiid: $productList")
        categorySelected("All items")

    })*/

    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
    ) {
        /*     Column(
                 modifier = Modifier
                     .fillMaxWidth()
                     .padding(bottom = 8.dp)
             ) {
                 Row(
                     modifier = Modifier
                         .fillMaxWidth()
                 ){
                     productCategoryList.forEachIndexed { index, item ->
                         Box(
                             modifier = Modifier
                                 .padding(4.dp)
                                 .clip(RoundedCornerShape(16.dp))
                                 .background(
                                     if (selectedCategory.value == index) {
                                         Color(88, 74, 255)
                                     } else {
                                         Color.Transparent
                                     }
                                 )
                                 .clickable {
                                     selectedCategory.value = index
                                     onProductCategorySelected(productCategoryList[selectedCategory.value])
                                 }
                         ) {
                             Text(
                                 text = item.uppercase(),
                                 fontSize= 9.sp,
                                 fontWeight = FontWeight.Bold,
                                 color =
                                 if (selectedCategory.value == index){
                                     Color.White
                                 }else{
                                     Color.Black
                                 }
                                 ,
                                 modifier = Modifier
                                     .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
                                 ,
                             )
                         }
                     }
                 }
             }*/




        CategorySelector(
            categoryList = productCategoryList,
            onCategorySelected = {
                categorySelected(it.category_name!!)
            }
        )

        if (productList.isNotEmpty()) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                columns = GridCells.Fixed(3)
            ) {
                items(productList) { i ->
                    Box(
                        /*   modifier = Modifier
                               .weight(1f, fill = true),*/
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (i != null) {
                            i.product_price?.let {
                                i.product_name?.let { it1 ->
                                    i.product_cover_photo?.let { it2 ->
                                        Log.d("jidjd", "ProductsGrid: $it2")
                                        /*        val cutname = remember {
                                                    mutableStateOf("")
                                                }

                                                it1.forEachIndexed {
                                                        index, item ->
                                                    if (index in 0..5){
                                                        cutname.value += item
                                                    }
                                                    if (index in 6..8){
                                                        cutname.value += "."
                                                    }

                                                }*/

                                        ProductListItem(
                                            productImageUrl = "$it2",


                                            productName = it1/*cutname.value*/,
                                            price = it,
                                            onAddToCart = {
                                              /*  i.id?.let { it2 ->*/ onAddToCart(i) /*}*/
                                            },
                                            onProductSelected = {
//                                           i.id?.let { it2 ->
                                                onProductSelected(
                                                    i
                                                )
//                                           }
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            EmptyBlock()
        }
        /*     ProductCategoryTab(tabs = productCategoryList, pagerState =pagerState, tabSelected = {
                 Log.d("dyigydisg", "ProductCategoryTabContent: ${it}")
                 tabSelected(it)
             }, filtered = filteredCategory)*/
        /*      ProductCategoryTabContent(
                  pagerState = pagerState,
                  productCategoryList = productCategoryList,
                  subLists = productList,
                  onAddToCart = onAddToCart,
                  onProductSelected = onProductSelected,
                  categoryName = filteredCategory
              )*/
        /* LazyColumn(
             modifier = Modifier
                 .fillMaxWidth()
                 .fillMaxHeight()
         ){
             items(items = subLists,itemContent = { item ->
                 Row(
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(bottom = 16.dp)
                     ,
                     horizontalArrangement = Arrangement.Start
                 ) {
                     for (i in item){
                         Box(
                             modifier = Modifier
                                 .weight(1f, fill = true)
                             ,
                             contentAlignment = Alignment.CenterStart
                         ) {
                             if (i != null) {
                                 i.product_price?.let {
                                     i.product_name?.let { it1 ->
                                         ProductListItem(
                                             productImageUrl = "",
                                             productName = it1,
                                             price = it,
                                             onAddToCart = onAddToCart,
                                             onProductSelected = { i.id?.let { it2 ->
                                                 onProductSelected(
                                                     it2.toInt())
                                             } }
                                         )
                                     }
                                 }
                             }
                         }

                     }
                 }
             }
             )
         }*/
    }
}


@Preview(showBackground = true)
@Composable
fun ProductsGridPreview() {
    Column(Modifier.fillMaxSize()) {

        ProductsGrid(
            productList = listOf(),
            onProductSelected = {},
            onAddToCart = {

            },
            productCategoryList = mutableListOf(ProductCategory("", 0)),
            onProductCategorySelected = {},
            filteredCategory = "",
            categorySelected = {}
        )
    }
}