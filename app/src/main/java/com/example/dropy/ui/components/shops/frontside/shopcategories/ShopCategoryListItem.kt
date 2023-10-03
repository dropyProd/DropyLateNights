package com.example.dropy.ui.components.shops.frontside.shopcategories

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.R

@Composable
fun ShopCategoryListItem(
    categoryImageUrl: String?,
    categoryName: String,
    onCategorySelected: () -> Unit,
    index: Int
) {
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .padding(end = 8.dp)
            .clickable { onCategorySelected() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(65.dp)
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            //LoadImage(imageUrl = categoryImageUrl)

            val list: MutableList<Int> = mutableListOf(
                R.drawable.cloth,
                R.drawable.grocery,
                R.drawable.burger,
                R.drawable.tv,
                R.drawable.cloth,
                R.drawable.grocery,
                R.drawable.burger,
                R.drawable.tv,
                R.drawable.cloth,
                R.drawable.grocery,
                R.drawable.burger,
                R.drawable.tv,
                R.drawable.cloth,
                R.drawable.grocery,
                R.drawable.burger,
                R.drawable.tv,
            )
            Image(
                painter = painterResource(id =list[index]),
                contentDescription = "",
                modifier = Modifier.size(39.dp),
            )

        }
        Box(
            modifier = Modifier
                .height(10.dp)
                .wrapContentWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .wrapContentWidth(),
                text = categoryName.toUpperCase(),
                fontSize = 10.sp,
                softWrap = false,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.axiformasemibold)),
                fontWeight = FontWeight.SemiBold,
                letterSpacing = (-0.38).sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShopCategoryListItemPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ShopCategoryListItem(
            categoryImageUrl = "",
            categoryName = "Category",
            onCategorySelected = {},
            index = 0
        )

    }
}