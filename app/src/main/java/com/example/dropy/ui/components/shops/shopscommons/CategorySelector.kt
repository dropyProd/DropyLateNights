package com.example.dropy.ui.components.shops.shopscommons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.network.models.shopdetails.ProductCategory
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.theme.LightBlue

@Composable
fun CategorySelector(
    categoryList:List<ProductCategory>,
    onCategorySelected:(ProductCategory)->Unit
){

    val selectedCategory = remember { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ){
        categoryList.forEachIndexed { index, item ->
            Box(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .clickable {
                        selectedCategory.value = index
                        onCategorySelected(categoryList[selectedCategory.value])
                    }
            ) {
                item.category_name?.let {
                    StyledText(
                        textColor = if (selectedCategory.value == index){
                            Color.White}else{
                            Color.Black},
                        backgroundColor = if (selectedCategory.value == index){
                            Color.Black}else{
                            Color.Transparent},
                        borderColor = Color.Transparent,
                        textSize = 10,
                        text = it,
                        isUppercase = true,
                        isBold = true,
                        fontFamily = R.font.axiformabold,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}