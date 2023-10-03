package com.example.dropy.ui.components.shops.frontside.searchresults

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.maps.MapComponent

@Composable
fun SearchPageHeader(
    mapInstance: @Composable (()->Unit)? = null,
    pageTitle:String,
    subTitle:String = "Search Results"
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
    ) {
        MapComponent(mapInstance = mapInstance)
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .sizeIn(minHeight = 64.dp)
                .background(Color(255, 255, 255, 153))
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            SimpleText(
                textSize = 15,
                text = pageTitle.uppercase(),
                isBold = true
            )
            SimpleText(
                textSize = 12,
                text = subTitle,
                isBold = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchPageHeaderPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SearchPageHeader(
            pageTitle = "page title"
        )
    }
}