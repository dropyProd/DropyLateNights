package com.example.dropy.ui.components.shops.frontside.shoplist

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.theme.LightBlue

@Composable
fun FavouriteShopListItem(
    shopLogoUrl: String? = null,
    shopName: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .padding(end = 20.dp)
            .clickable { onClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        Box(
            modifier = Modifier
                .width(61.dp)
                .aspectRatio(1f)
                .clip(CircleShape)
                .border(width = 1.dp, color = Color.LightGray.copy(.4f), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
             LoadImage(imageUrl = shopLogoUrl)
      /*      Image(
                painter = painterResource(id = R.drawable.imgthre),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )*/
        }
        Box(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .wrapContentWidth(),
                text = shopName.uppercase(),
                fontSize = 8.sp,
                softWrap = false,
                textAlign = TextAlign.Center,
                letterSpacing = (-0.38).sp,
                fontFamily = FontFamily(Font(R.font.axiformasemibold)),
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavouriteShopListItemPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        FavouriteShopListItem(
            shopName = "Shop Name",
            onClick = {}
        )
    }
}