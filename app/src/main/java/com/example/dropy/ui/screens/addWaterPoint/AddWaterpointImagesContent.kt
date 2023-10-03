package com.example.dropy.ui.screens.addWaterPoint

import com.example.dropy.R
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.screens.addWaterTruck.AddWaterTruckImagesUiState
import com.example.dropy.ui.screens.addWaterTruck.AddWaterTruckViewmodel
import com.example.dropy.ui.screens.shops.backside.addshop.AddShopViewModel
import com.example.dropy.ui.screens.shops.backside.createshop.addshopimages.AddShopImagesUiState
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue

@Composable
fun AddWaterpointImagesContent(
    uiState: AddWaterpointImagesUiState,
    onAddShopLogo:()->Unit,
    onAddShopCoverPhoto:()->Unit,
    onAddShop:()->Unit,
    addWaterpointViewmodel:AddWaterpointViewmodel? = null
){
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
        ,
    ){
        ClippedHeader(title = "Waterpoint uploads")

        Log.d("FFTAG", "AddShopImagesContent: ${addWaterpointViewmodel?.shopLogo?.value}  ${addWaterpointViewmodel?.shopCoverPhoto?.value}")

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .fillMaxHeight()
            ,
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            SimpleText(
                text = "lets create a waterpoint",
                textSize = 14,
                isUppercase = true,
                isBold = true,
                verticalPadding = 8,
                font = Font(R.font.axiformabold)
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
                    if (uiState.shopLogo == null){
                        LoadImage()
                    }else{
                        Image(
                            bitmap = uiState.shopLogo,
                            contentDescription = "image logo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }

                    IconButton(
                        onClick = { onAddShopLogo() },
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
                    text = "Upload your waterpoint logo",
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
                    if (uiState.shopCoverPhoto == null){
                        LoadImage()
                    }else{
                        Image(
                            bitmap = uiState.shopCoverPhoto,
                            contentDescription = "image logo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                    IconButton(
                        onClick = { onAddShopCoverPhoto() },
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
                    text = "Upload your waterpoint licenses",
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
                buttonText = "add waterpoint",
                textFontSize = 15,
                textColor = Color.White,
                backgroundColor = Color.Black,
                widthFraction = 0.65,
                action = { onAddShop() }
            )
        }
    }
}

@Preview
@Composable
fun demo(){
    AddWaterpointImagesContent(
        uiState = AddWaterpointImagesUiState(),
        onAddShopLogo = { /*TODO*/ },
        onAddShopCoverPhoto = { /*TODO*/ },
        onAddShop = { /*TODO*/ }
    )
}


