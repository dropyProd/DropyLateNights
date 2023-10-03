package com.example.dropy.ui.components.shops.shopscommons.billboards

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.LoadImage

@Composable
fun ShopBillboard(
    //billboardItems: List<ShopBillboardDataClass>? = null
    logourl: String,
    coverurl: String
) {

    Log.d("njnjn", "ShopBillboard logo url: $logourl ")
    Log.d("njnjn", "ShopBillboard cover url: $coverurl ")
    Box(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .fillMaxWidth()
            .aspectRatio(2 / 1f)
            .clip(RoundedCornerShape(8.dp)),
    ) {
        LoadImage(
            imageUrl = coverurl, modifier = Modifier
                .fillMaxSize()
        )
        LoadImage(
            imageUrl = logourl, modifier = Modifier
                .padding(8.dp)
                .size(64.dp)
                .clip(CircleShape)
                .align(Alignment.TopStart)
                .border(1.dp, Color.LightGray, CircleShape)
        )
        /*   if (billboardItems == null || billboardItems.isEmpty()) {
               Image(
                   painter = painterResource(id = R.drawable.imgtwo),
                   contentDescription = "cover photo",
                   modifier = Modifier
                       .fillMaxSize()
                       .clip(shape = RoundedCornerShape(16.dp)),
                   contentScale = ContentScale.Crop
               )
               Image(
                   painter = painterResource(id = R.drawable.imgtwo),
                   contentDescription = "logo",
                   modifier = Modifier
                       .padding(8.dp)
                       .size(64.dp)
                       .clip(CircleShape)
                       .align(Alignment.TopStart)
                       .border(1.dp, Color.Black, CircleShape), contentScale = ContentScale.Crop
               )
           } else {
               val index = remember { mutableStateOf(0) }
               val currentBillboard = billboardItems[index.value]
               LoadImage(imageUrl = currentBillboard.coverPhotoUrl)
               Box(
                   modifier = Modifier
                       .padding(8.dp)
                       .size(64.dp)
                       .clip(CircleShape)
                       .align(Alignment.TopStart)
                       .border(1.dp, Color.Black, CircleShape),
                   contentAlignment = Alignment.Center
               ) {
                   LoadImage(imageUrl = currentBillboard.shopLogoUrl)
               }

               IconButton(
                   onClick = {
                       if (index.value <= 0) {
                           index.value = billboardItems.lastIndex
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
                       if (index.value >= billboardItems.lastIndex) {
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
           }*/

    }
}

@Preview(showBackground = true)
@Composable
fun ShopBillboardPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        val billboardItems = mutableListOf(
            ShopBillboardDataClass(
                shopLogoUrl = "",
                coverPhotoUrl = ""
            ),
            ShopBillboardDataClass(
                shopLogoUrl = "",
                coverPhotoUrl = ""
            ),
            ShopBillboardDataClass(
                shopLogoUrl = "",
                coverPhotoUrl = ""
            )
        )

        ShopBillboard(coverurl = "", logourl = "")
    }
}