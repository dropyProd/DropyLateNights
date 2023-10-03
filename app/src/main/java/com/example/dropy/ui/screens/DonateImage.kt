package com.example.dropy.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.LoadImage

@Composable
fun DonateImage(modifier: Modifier, shopCoverPhotoUrl: String, size: Int) {

        Row(
            modifier = modifier
                .size(size.dp)
                .border(width = 1.dp, color = Color.White, shape = CircleShape),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            LoadImage(
                imageUrl = shopCoverPhotoUrl, modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
       /*     Image(
                painter = painterResource(id = image),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )*/
        }

}