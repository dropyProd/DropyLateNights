package com.example.dropy.ui.components.shops.backside.dashboard.product.productslist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.network.models.AllProductsResItem
import com.example.dropy.network.models.shopdetails.Product
import com.example.dropy.ui.components.commons.EmptyBlock
import com.example.dropy.ui.components.shops.frontside.singleshop.shophome.BaseUrL
import com.example.dropy.ui.theme.LightContainerBackground

@Composable
fun ProductsList(
    productList:List<AllProductsResItem>,
    onProductSelected:(product: AllProductsResItem)->Unit
){

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
      if (productList.isNotEmpty()){
          for (item in productList){
              val index = productList.indexOf(item)
              item.product_name?.let {
                  item.number_in_stack?.let { it1 ->
                      item.product_price?.let { it2 ->
                          item.product_cover_photo?.let { it3 ->


                     /*         val cutname = remember {
                                  mutableStateOf("")
                              }

                              it.forEachIndexed {
                                  index, item ->
                                  if (index in 0..5){
                                      cutname.value += item
                                  }
                                  if (index in 6..8){
                                      cutname.value += "."
                                  }

                              }*/

                              ProductListItem(
                                  productImageUrl = "${BaseUrL}$it3",
                                  productName = it,
                                  numberOfUnits = it1,
                                  price = it2,
                                  alwaysAvailable = false,
                                  backgroundColor = if (index % 2 == 0){
                                      LightContainerBackground
                                  }else{
                                      Color.White
                                  },
                                  onClick = {  onProductSelected(item)  }
                              )
                          }
                      }
                  }
              }
          }
      }else{
          EmptyBlock()
      }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsListPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        val productsList = mutableListOf(
            ProductsListItemDataClass(
                productId = 1,
                productImageUrl = "",
                productName = "Some other very long product name",
                numberOfUnits = 2,
                price = 4321,
                alwaysAvailable = true
            ),
            ProductsListItemDataClass(
                productId = 1,
                productImageUrl = "",
                productName = "Some other very long product name",
                numberOfUnits = 2,
                price = 4321,
                alwaysAvailable = true
            ),
            ProductsListItemDataClass(
                productId = 1,
                productImageUrl = "",
                productName = "Some other very long product name",
                numberOfUnits = 2,
                price = 4321,
                alwaysAvailable = true
            )
        )
        ProductsList(
            onProductSelected = {},
            productList = listOf()
        )
    }
}