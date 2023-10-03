package com.example.dropy.ui.components.shops.frontside.searchresults.productssearchresult

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.screens.shops.frontside.searchresults.productssearchresult.ProductsSearchResultPageUiState

@Composable
fun ProductsSearchResultPageContent(
    uiState:ProductsSearchResultPageUiState,
    onProductSelected : ()->Unit,
    onAddProductClicked : ()->Unit,
){
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(bottom = 48.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.4f)
        ){
            MapComponent(
                //TO ADD A MAP WITH MARKERS FROM uiState.markerList
            )
        }
        ProductsSearchResultList(
            productsSearchResultList = uiState.productsSearchResults,
            onAddProductClicked = {onAddProductClicked()},
            onProductSelected = {onProductSelected()}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsSearchResultPageContentPreview(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ProductsSearchResultPageContent(
            uiState = ProductsSearchResultPageUiState(),
            onProductSelected = {},
            onAddProductClicked = {}
        )
    }
}