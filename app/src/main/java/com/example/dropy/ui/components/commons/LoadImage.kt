package com.example.dropy.ui.components.commons

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dropy.R

@Composable
fun LoadImage(
    imageUrl:String? = null,
    imageDescription:String = "image",
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop

){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.imageplaceholder),
        error = painterResource(R.drawable.imageplaceholder),
        contentDescription = imageDescription,
        contentScale = contentScale,
        modifier = modifier
            .fillMaxSize()
    )
}