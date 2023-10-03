package com.example.dropy.ui.screens.rider.becomerider.riderimage

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.screens.rider.becomerider.AddRiderUiState
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue

@Composable
fun AddRiderImageContent(
    onAddRider: () -> Unit,
    uiState: AddRiderUiState,
    onAddRiderCoverPhoto:()->Unit,
    onAddRiderLogo:()->Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
        ,
    ){
        ClippedHeader(title = "Rider uploads")

        //Log.d("FFTAG", "AddShopImagesContent: ${add.shopLogo.value}  ${addShopViewModel.shopCoverPhoto.value}")

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .fillMaxHeight()
            ,
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            SimpleText(
                text = "lets create a rider",
                textSize = 14,
                isUppercase = true,
                isBold = true,
                verticalPadding = 8,
                font = Font(R.font.axiformaregular)
            )
            Row(
                modifier = Modifier
                    .padding(bottom = 24.dp, start = 8.dp, end = 8.dp)
                    .fillMaxWidth()
                ,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(LightBlue)
                ){
                    if (uiState.riderProfileLogo == null){
                        LoadImage()
                    }else{
                        Image(
                            bitmap = uiState.riderProfileLogo,
                            contentDescription = "image logo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }

                    IconButton(
                        onClick = { onAddRiderLogo() },
                        modifier = Modifier
                            .size(120.dp)
                            .background(Color(255, 255, 255, 77))
                        ,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "add icon",
                            modifier = Modifier
                                .size(24.dp)
                            ,
                            tint = Color.White
                        )
                    }
                }
                SimpleText(
                    textSize = 10,
                    text = "Upload your shop logo",
                    isUppercase = false,
                    isBold = true,
                    padding = 8,
                    font = Font(R.font.axiformabold)
                )
            }
            Column(
                modifier = Modifier
                    .padding(bottom = 24.dp, start = 8.dp, end = 8.dp)
                    .fillMaxWidth()
                ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(LightBlue)
                        .fillMaxWidth()
                        .aspectRatio(2 / 1f)
                ){
                    if (uiState.riderBikeCoverPhoto == null){
                        LoadImage()
                    }else{
                        Image(
                            bitmap = uiState.riderBikeCoverPhoto,
                            contentDescription = "image logo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                    IconButton(
                        onClick = { onAddRiderCoverPhoto() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(2 / 1f)
                            .background(Color(255, 255, 255, 77))
                        ,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "add icon",
                            modifier = Modifier
                                .size(24.dp)
                            ,
                            tint = Color.White
                        )
                    }
                }
                SimpleText(
                    textSize = 10,
                    text = "Upload your shop feature photo",
                    isUppercase = false,
                    isBold = true,
                    padding = 8,
                    font = Font(R.font.axiformabold)
                )
            }
//            Row(
//                modifier = Modifier
//                    .padding(bottom = 24.dp, start = 8.dp, end = 8.dp)
//                    .fillMaxWidth()
//                ,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Box(
//                    modifier = Modifier
//                        .clip(RoundedCornerShape(8.dp))
//                        .background(LightBlue)
//                ){
//                    IconButton(
//                        onClick = {
//
//                        },
//                        modifier = Modifier
//                            .size(120.dp)
//                            .background(Color(255, 255, 255, 77))
//                        ,
//                    ) {
//                        Icon(
//                            imageVector = Icons.Filled.Add,
//                            contentDescription = "add icon",
//                            modifier = Modifier
//                                .size(24.dp)
//                            ,
//                            tint = Color.White
//                        )
//                    }
//                }
//                SimpleText(
//                    textSize = 10,
//                    text = "Upload business certificate",
//                    isUppercase = false,
//                    isBold = true,
//                    padding = 8
//                )
//            }

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
            ,
            contentAlignment = Alignment.Center
        ) {
            TotallyRoundedButton(
                icon = null,
                buttonText = "add rider",
                textFontSize = 15,
                textColor = Color.Black,
                backgroundColor = DropyYellow,
                widthFraction = 0.5,
                action = { onAddRider() }
            )
        }
    }
}