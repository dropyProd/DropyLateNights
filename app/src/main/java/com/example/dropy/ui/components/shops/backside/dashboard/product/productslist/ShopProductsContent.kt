package com.example.dropy.ui.components.shops.backside.dashboard.product.productslist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.network.models.AllProductsResItem
import com.example.dropy.network.models.shopdetails.Product
import com.example.dropy.ui.components.commons.EmptyBlock
import com.example.dropy.ui.components.shops.shopscommons.CategorySelector
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.screens.shops.backside.shopproducts.ShopProductsUiState
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageUiState
import com.example.dropy.ui.theme.LightBlue

@Composable
fun ShopProductsContent(
    uiState: ShopProductsUiState,
    shopHomePageUiState: ShopHomePageUiState,
    onSearchParameterChanged: (String) -> Unit,
    onAddProductClicked: () -> Unit,
    onSearchButtonClicked: () -> Unit,
    onCategorySelected: (categoryName: String) -> Unit,
    onProductSelected: (product: AllProductsResItem) -> Unit
) {
    Column(

        modifier = Modifier
            .fillMaxSize()
    ) {

        Spacer(modifier = Modifier.height(10.dp))
        ClippedHeader(title = "products")
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            SearchOrAddProduct(
                searchParameter = uiState.searchParameter,
                onAddProductClicked = { onAddProductClicked() },
                onSearchButtonClicked = { onSearchButtonClicked() },
                onSearchParameterChanged = { onSearchParameterChanged(it) }
            )
            CategorySelector(
                categoryList = shopHomePageUiState.productCategories,
                onCategorySelected = { onCategorySelected(it.category_name!!) }
            )

                ProductsList(
                    onProductSelected = { onProductSelected(it) },
                    productList = if (shopHomePageUiState.filteredCategory.equals("All items")) shopHomePageUiState.shopProducts else {
                        if (shopHomePageUiState.exist) {
                            shopHomePageUiState.filteredList
                        } else listOf()
                    }
                )

        }
    }
}


@Composable
fun SearchOrAddProduct(
    searchParameter: String,
    onSearchParameterChanged: (String) -> Unit,
    onAddProductClicked: () -> Unit,
    onSearchButtonClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        OutlinedTextField(
            value = searchParameter,
            onValueChange = { onSearchParameterChanged(it) },
            shape = RoundedCornerShape(50),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = LightBlue.copy(.2f)
            ),
            modifier = Modifier
                .height(48.dp)
                .weight(1f),
            placeholder = {
                Text(
                    text = "search product",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.axiformaregular))
                )
            },
            trailingIcon = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(
                        modifier = Modifier
                            .width(2.dp)
                            .height(32.dp)
                            .background(Color.LightGray),
                    )

                    IconButton(
                        onClick = { onSearchButtonClicked() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "search icon",
                            modifier = Modifier.padding(8.dp),
                            tint = Color.LightGray
                        )
                    }
                }
            }
        )
        IconButton(
            onClick = { onAddProductClicked() },
            modifier = Modifier
                .padding(start = 8.dp)
                .size(32.dp)
                .clip(CircleShape)
                .background(Color.Black)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "add icon",
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShopProductsContentPreview() {
    Column(Modifier.fillMaxSize()) {
        ShopProductsContent(
            uiState = ShopProductsUiState(),
            shopHomePageUiState = ShopHomePageUiState(),
            onProductSelected = {},
            onSearchParameterChanged = {},
            onSearchButtonClicked = {},
            onAddProductClicked = {},
            onCategorySelected = {}
        )
    }
}