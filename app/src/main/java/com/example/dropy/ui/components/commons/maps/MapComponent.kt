package com.example.dropy.ui.components.commons.maps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R

@Composable
fun MapComponent(
    float: Float = 0.6f,
    modifier: Modifier = Modifier,
    mapInstance: @Composable() (() -> Unit)? = null
){
    if (mapInstance == null){
        Box(
            modifier = Modifier
                .fillMaxWidth().fillMaxHeight(.6f),
            contentAlignment = Alignment.Center
        ){
            Box(
                modifier = Modifier
                    .width(200.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.mapplaceholder),
                    contentDescription = "map placeholder",
                    modifier = Modifier
                        .fillMaxSize()
                    ,
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }else{
        Box(
            modifier = modifier
                .fillMaxWidth().fillMaxHeight(float),
            contentAlignment = Alignment.Center
        ){
            mapInstance()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MapComponentPreview(){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        MapComponent()
    }
}