package com.example.dropy.ui.components.shops.backside.dashboard.product.productslist

import androidx.compose.ui.graphics.painter.Painter

data class ProductsListItemDataClass(
    val productId:Int,
    val productImageUrl: String,
    val productName:String,
    val numberOfUnits:Int,
    val price:Int,
    val alwaysAvailable:Boolean,
)