package com.example.dropy.ui.components.shops.frontside.shopcategories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.network.models.shopCategories.ShopCategoriesResponseItem
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.theme.LightBlue


@Composable
fun ShopsCategoryList(
    categoryList: List<ShopCategoriesResponseItem>,
    onCategorySelected: () -> Unit,
    onAllCategoriesClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            /*          SimpleText(
                          textSize = 12,
                          text = "categories",
                          isUppercase = true,
                          isExtraBold = true
                      )*/
            Text(
                text = "CATEGORIES",
                fontSize = 10.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformaheavy)
                ),
                letterSpacing = (-0.5).sp
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .size(20.dp)
                        .background(Color.Black, CircleShape)
                        .clip(CircleShape),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add", tint = Color.White)
                }

                Text(
                    text = "MORE\nCATEGORIES",
                    fontSize = 7.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformaheavy)
                    ),
                    letterSpacing = (-0.5).sp
                )
            }


        }


        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            itemsIndexed(items = categoryList, itemContent = { index, item ->
                item.category_name?.let {
                    ShopCategoryListItem(
                        categoryImageUrl = item.category_icon_url,
                        categoryName = it,
                        onCategorySelected = { onCategorySelected() },
                        index = index
                    )
                }
            }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShopsCategoryListPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        val categoryList = mutableListOf(
            ShopsCategoryListItemDataClass(
                categoryName = "category name"
            ),
            ShopsCategoryListItemDataClass(
                categoryName = "category name"
            ),
            ShopsCategoryListItemDataClass(
                categoryName = "category name"
            ),
            ShopsCategoryListItemDataClass(
                categoryName = "category name"
            ),
            ShopsCategoryListItemDataClass(
                categoryName = "category name"
            )
        )

        ShopsCategoryList(
            categoryList = listOf(),
            onCategorySelected = {},
            onAllCategoriesClicked = {}
        )
    }
}
