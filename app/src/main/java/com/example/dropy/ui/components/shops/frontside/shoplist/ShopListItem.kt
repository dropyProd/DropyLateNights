package com.example.dropy.ui.components.shops.frontside.shoplist

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.network.models.ShopsResponseNewItem
import com.example.dropy.network.models.shops.Shop
import com.example.dropy.ui.components.commons.DistanceWithIcon
import com.example.dropy.ui.components.commons.Liked
import com.example.dropy.ui.components.commons.LoadImage
import kotlinx.coroutines.delay

@Composable
fun ShopListItem(
    shopLogoUrl: String? = null,
    shopName: String,
    distanceInMeters: Int? = null,
    onShopReview: () -> Unit,
    onClicked: () -> Unit,
    size: Int = 100,
    likeClicked: (Boolean) -> Unit,
    favshops: List<com.example.dropy.network.models.favouriteshop.favouriteShopRes.Shop> = listOf(),
    shop: ShopsResponseNewItem? = null,
    isLiked: Boolean
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .widthIn(size.dp, 700.dp)
            .padding(end = 20.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { /* Called when the gesture starts */ },
                    onDoubleTap = {

                        likeClicked(isLiked)
                        /*  Toast
                              .makeText(context, "onDoubleTap", Toast.LENGTH_SHORT)
                              .show()*/
                    },
                    onLongPress = { /*onShopReview() */

                        likeClicked(isLiked)
                        /*         Toast
                                     .makeText(context, "onLongPress", Toast.LENGTH_SHORT)
                                     .show()*/
                    },
                    onTap = {
                        onClicked()
                        /*    Toast
                                .makeText(context, "onTap", Toast.LENGTH_SHORT)
                                .show()*/
                    }
                )
            },
        // .clickable { }
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .width(size.dp)
                .aspectRatio(1f)
                .clip(shape = RoundedCornerShape(16.dp))
                .border(
                    width = 1.dp,
                    color = Color.LightGray.copy(.4f),
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {

            LoadImage(imageUrl = shopLogoUrl)
            /*     Image(
                     painter = painterResource(id = R.drawable.imgfrr),
                     contentDescription = "",
                     modifier = Modifier
                         .fillMaxWidth()
                         .clip(RoundedCornerShape(13.dp)),
                     contentScale = ContentScale.Crop
                 )
     */
            Log.d("shopImgCheck", "ShopListItem:$shopLogoUrl ")


            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 5.dp, top = 5.dp)
            ) {
                Liked(liked = isLiked, isliked = {

                    likeClicked(isLiked)
                })
            }
        }
        Box(
            modifier = Modifier
                .height(20.dp)
                .wrapContentWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .wrapContentWidth(),
                text = shopName.uppercase(),
                fontSize = 8.sp,
                softWrap = false,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.axiformasemibold)),
                fontWeight = FontWeight.SemiBold,
                letterSpacing = (-0.38).sp
            )
        }
        if (distanceInMeters != null) {
            DistanceWithIcon(distanceInMeters = distanceInMeters)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShopListItemPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ShopListItem(
            shopName = "Shop Name",
            onClicked = {},
            onShopReview = {},
            likeClicked = {},
            isLiked = true
        )

        ShopListItem(
            shopName = "Shop Name",
            distanceInMeters = 1234,
            onClicked = {},
            onShopReview = {},
            likeClicked = {},
            isLiked = false
        )
    }
}