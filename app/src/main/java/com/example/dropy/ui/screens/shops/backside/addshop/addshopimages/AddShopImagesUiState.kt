package com.example.dropy.ui.screens.shops.backside.createshop.addshopimages

import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap

data class AddShopImagesUiState(
    val shopLogo: ImageBitmap? = null,
    val shopLogoUri: Uri? = null,
    val shopCoverPhoto: ImageBitmap? = null,
    val shopCoverPhotoUri: Uri? = null,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)
