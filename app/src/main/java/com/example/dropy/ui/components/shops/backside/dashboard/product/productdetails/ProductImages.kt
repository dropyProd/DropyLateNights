package com.example.dropy.ui.components.shops.backside.dashboard.product.productdetails

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue

@Composable
fun ProductImages(
    productImages:List<ProductImageDataClass>,
    onEditCoverPhoto:()->Unit,
    onDeleteImage:(imageId:Int)->Unit,
    onAddImage:()->Unit,
){
    val selectedImageIndex = if (productImages.isNotEmpty()){
        remember {
            mutableStateOf(0)
        }
    }else{
        null
    }

    val currentImageUrl = if (selectedImageIndex !=null){
        productImages[selectedImageIndex.value].imageUrl
    }else{
        null
    }


    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2 / 1f)
                .clip(RoundedCornerShape(16.dp))
            ,
            contentAlignment = Alignment.Center
        ){
            LoadImage(imageUrl = currentImageUrl)
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clickable { onEditCoverPhoto() }
            ){
                StyledText(
                    backgroundColor = DropyYellow,
                    textSize = 10,
                    text ="edit cover photo",
                    isUppercase = true
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = { onAddImage() },
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(LightBlue)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = "add icon",
                        tint = Color.White
                    )
                }
                SimpleText(
                    textSize = 10,
                    text = "Add photo",
                    isBold = true,
                    padding = 4
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                productImages.forEachIndexed { index, item ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                    ){
                        Box(
                            modifier = Modifier
                                .clickable { selectedImageIndex?.value = index }
                                .padding(top = 8.dp, end = 8.dp)
                                .width(64.dp)
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(16.dp))
                                .border(2.dp, Color.LightGray, RoundedCornerShape(16.dp))
                            ,
                            contentAlignment = Alignment.Center
                        ){
                            LoadImage(imageUrl = item.imageUrl)
                        }

                        IconButton(
                            onClick = { onDeleteImage(item.imageId) },
                            modifier = Modifier
                                .size(32.dp)
                                .align(Alignment.TopEnd)
                                .clip(CircleShape)
                                .background(Color.Black)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Cancel,
                                contentDescription = "cancel icon",
                                tint = Color.White
                            )
                        }
                    }

                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ProductImagesPreview(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val productImages = remember {
            mutableStateListOf(
                ProductImageDataClass(
                    imageUrl = "",
                    imageId = 1
                ),
                ProductImageDataClass(
                    imageUrl = "",
                    imageId = 1
                ),
                ProductImageDataClass(
                    imageUrl = "",
                    imageId = 1
                )
            )
        }
        ProductImages(
            productImages=productImages,
            onAddImage = {},
            onDeleteImage = {},
            onEditCoverPhoto = {}
        )
    }
}