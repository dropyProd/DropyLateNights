package com.example.dropy.ui.components.shops.frontside.searchresults.shopssearchresult

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.screens.shops.frontside.searchresults.shopssearchresult.ShopsSearchResultPageUiState

@Composable
fun ShopsSearchResultPageContent(
    uiState: ShopsSearchResultPageUiState,
    onShopSelected : ()->Unit
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
        ShopsSearchResultList(
            shopsSearchResultList = uiState.shopsSearchResults,
            onShopSelected = {onShopSelected()}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShopsSearchResultPageContentPreview(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ShopsSearchResultPageContent(
            uiState = ShopsSearchResultPageUiState(),
            onShopSelected = {}
        )
    }
}