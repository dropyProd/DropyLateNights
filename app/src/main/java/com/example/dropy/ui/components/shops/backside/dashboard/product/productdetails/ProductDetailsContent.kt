package com.example.dropy.ui.components.shops.backside.dashboard.product.productdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.screens.shops.backside.productdetails.ProductDetailsUiState
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue

@Composable
fun ProductDetailsContent(
    uiState: ProductDetailsUiState,
    onEditCoverPhoto:()->Unit,
    onDeleteImage:(imageId:Int)->Unit,
    onAddImage:()->Unit,
    onProductNameChanged:(String)->Unit,
    onProductDescriptionChanged:(String)->Unit,
    onProductPriceChanged:(Int)->Unit,
    onProductUnitsChanged:(String)->Unit,
    onAddToStack:()->Unit,
    onSubtractFromStack:()->Unit,
    onChangeAvailability:(Boolean)->Unit,
    onSaveClicked:()->Unit,
    onDeleteClicked:()->Unit,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SimpleText(
            text = "product image",
            textSize = 18,
            isUppercase = true,
            isBold = true
        )
        ProductImages(
            productImages = uiState.productImages,
            onEditCoverPhoto = { onEditCoverPhoto() },
            onDeleteImage = { onDeleteImage(it) },
            onAddImage = { onAddImage() }
        )
        ProductCostAndInventory(
            productPrice = uiState.productPrice,
            onProductPriceChanged = {onProductPriceChanged(it)},
            productUnits = uiState.productUnits,
            onProductUnitsChanged = {onProductUnitsChanged(it)},
            numberInStock = uiState.numberInStock,
            onAddToStack = {onAddToStack()},
            onSubtractFromStack = {onSubtractFromStack()},
            isAvailable = uiState.isAvailable,
            onChangeAvailability = {onChangeAvailability(it)}
        )
        ProductNameAndDescription(
            productName = uiState.productName,
            productDescription = uiState.productDescription,
            onProductNameChanged = {onProductNameChanged(it)},
            onProductDescriptionChanged = {onProductDescriptionChanged(it)}
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TotallyRoundedButton(
                buttonText = "save",
                textFontSize = 12,
                backgroundColor = DropyYellow,
                widthFraction = 0.6,
                action = { onSaveClicked() }
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = { onDeleteClicked() },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.Red)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Cancel,
                        contentDescription = "cancel icon",
                        tint = Color.White
                    )
                }
                SimpleText(
                    text = "delete product",
                    isBold = true,
                    isUppercase = true
                )
            }
        }
    }

}


@Composable
fun ProductCostAndInventory(
    productPrice:Int,
    onProductPriceChanged:(Int)->Unit,
    productUnits:String,
    onProductUnitsChanged:(String)->Unit,
    numberInStock:Int,
    onAddToStack:()->Unit,
    onSubtractFromStack:()->Unit,
    isAvailable:Boolean,
    onChangeAvailability:(Boolean)->Unit
){
    Box(
        modifier = Modifier
            .padding(top = 16.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
    ){
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
                    .height(48.dp)
                ,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SimpleText(
                    textSize = 10,
                    text = "price",
                    isUppercase = true,
                    isBold = true
                )
                OutlinedTextField(
                    value = productPrice.toString(),
                    onValueChange = {onProductPriceChanged(it.toInt())},
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                    ,
                    shape = RoundedCornerShape(8.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
            }
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
                    .height(48.dp)
                ,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SimpleText(
                    textSize = 10,
                    text = "units",
                    isUppercase = true,
                    isBold = true
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    SimpleText(
                        textSize = 10,
                        text = "Price per",
                        isBold = true,
                        horizontalPadding = 8
                    )

                    OutlinedTextField(
                        value = productUnits,
                        onValueChange = {onProductUnitsChanged(it)},
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                        ,
                        shape = RoundedCornerShape(8.dp)
                    )
                }

            }
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
                    .height(32.dp)
                ,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SimpleText(
                    textSize = 10,
                    text = "stock",
                    isUppercase = true,
                    isBold = true
                )
                Row {
                    Row(
                        modifier = Modifier
                        ,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {onSubtractFromStack()},
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(Color.Black)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "add",
                                tint = Color.White
                            )
                        }
                        SimpleText(
                            textSize = 10,
                            text = numberInStock.toString(),
                            isUppercase = true,
                            isExtraBold = true,
                            padding = 4
                        )
                        IconButton(
                            onClick = {onAddToStack()},
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(LightBlue)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "add",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
                    .height(32.dp)
                ,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SimpleText(
                    textSize = 10,
                    text = "availability",
                    isUppercase = true,
                    isBold = true
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    SimpleText(
                        textSize = 10,
                        text = if (isAvailable ){"In stock"}else{"Out of Stock"},
                        textColor = Color.LightGray,
                        isUppercase = false,
                        isBold = true
                    )

                    Switch(
                        checked = isAvailable,
                        onCheckedChange = { onChangeAvailability(it) },
                        modifier = Modifier
                        ,
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = LightBlue,
                            uncheckedThumbColor = LightBlue,
                            uncheckedTrackColor = Color.LightGray
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ProductNameAndDescription(
    productName:String,
    productDescription:String,
    onProductNameChanged:(String)->Unit,
    onProductDescriptionChanged:(String)->Unit
){
    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
            ,
        ) {
            SimpleText(
                textSize = 10,
                text = "edit product name",
                textColor = Color.Black,
                isUppercase = true,
                isBold = false
            )
            OutlinedTextField(
                value = productName,
                onValueChange = {onProductNameChanged(it)},
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .height(48.dp)
                ,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = LightBlue,
                ),
            )
        }
        Column(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
        ) {
            SimpleText(
                textSize = 10,
                text = "edit product description",
                textColor = Color.Black,
                isUppercase = true,
                isBold = false
            )
            OutlinedTextField(
                value = productDescription,
                onValueChange = {onProductDescriptionChanged(it)},
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .height(96.dp)
                ,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = LightBlue,
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailsPreview(){
    Column(Modifier.fillMaxSize()) {
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
        ProductDetailsContent(
            onAddImage = {},
            onDeleteImage = {},
            onEditCoverPhoto = {},
            onChangeAvailability = {},
            onSubtractFromStack = {},
            onAddToStack = {},
            onProductUnitsChanged = {},
            onProductPriceChanged = {},
            onProductDescriptionChanged = {},
            onProductNameChanged = {},
            onSaveClicked = {},
            onDeleteClicked = {},
            uiState = ProductDetailsUiState()
        )
    }
}