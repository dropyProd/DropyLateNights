package com.example.dropy.ui.components.shops.frontside.singleshop.singleproduct

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.shops.frontside.singleshop.shophome.BaseUrL

@Composable
fun ProductImagesBillBoard(
    productImageUrlList: List<String>?
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .aspectRatio(2 / 1f)
            .clip(RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {


        if (productImageUrlList == null || productImageUrlList.isEmpty()) {
            LoadImage(
                imageUrl = "", modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop
            )
            /*     Image(
                     painter = painterResource(id = R.drawable.imageplaceholder),
                     contentDescription = "",
                     modifier = Modifier
                         .fillMaxSize()
                         .clip(RoundedCornerShape(15.dp)),
                     contentScale = ContentScale.Crop
                 )*/
        } else {
            val index = remember { mutableStateOf(0) }
            val currentImage = productImageUrlList[index.value]

            LoadImage(imageUrl = "$currentImage", contentScale = ContentScale.Crop)

            IconButton(
                onClick = {
                    if (index.value <= 0) {
                        index.value = productImageUrlList.lastIndex
                    } else {
                        index.value--
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIos,
                    contentDescription = "back button",
                )
            }
            IconButton(
                onClick = {
                    if (index.value >= productImageUrlList.lastIndex) {
                        index.value = 0
                    } else {
                        index.value++
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowForwardIos,
                    contentDescription = "back button",
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ProductImagesBillBoardPreview() {
    Column(Modifier.fillMaxSize()) {
        ProductImagesBillBoard(
            productImageUrlList = mutableListOf("", "", "")
        )
    }
}