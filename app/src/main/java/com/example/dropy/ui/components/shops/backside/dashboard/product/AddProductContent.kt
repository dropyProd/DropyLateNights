package com.example.dropy.ui.components.shops.backside.dashboard.product


import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.R
import com.example.dropy.network.models.getshopproductcategories.ProductCategory
import com.example.dropy.network.models.shopProductCategories.ShopProductCategoriesResponseItem
import com.example.dropy.ui.components.commons.Dropdown
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.screens.shops.backside.addproduct.AddProductUiState
import com.example.dropy.ui.screens.shops.backside.addproduct.AddProductViewModel
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.InputYellow
import com.example.dropy.ui.theme.LightBlue
import java.nio.file.WatchEvent

@Composable
fun AddProductContent(
    uiState: AddProductUiState,
    route: String,
    onProductNameChanged: (String) -> Unit,
    onAddCoverPhoto: () -> Unit,
    onProductDescriptionChanged: (String) -> Unit,
    onProductCategorySelected: (ProductCategory, Int) -> Unit,
    onProductPriceChanged: (String) -> Unit,
    onProductUnitsChanged: (String) -> Unit,
    onAddProductPhotos: () -> Unit,
    onNumberInStackChanged: (String) -> Unit,
    onDiscountPriceChanged: (String) -> Unit,
    onDiscountPercentageChanged: (String) -> Unit,
    onCouponCodeChanged: (String) -> Unit,
    onAddProduct: (String) -> Unit,
    onDeleteProduct: () -> Unit,
    addProductViewModel: AddProductViewModel
) {
    val unitsList = listOf(
        "Per Item",
        "Per Kg",
        "Per G",
        "Per Ltr",
        "Per Ml"
    )
    LaunchedEffect(key1 = true, block = {
        //   onProductCategorySelected(uiState.productCategories[0], 0)
      if (addProductViewModel.refresh.value) {
          onProductUnitsChanged(unitsList[0])
         if (uiState.productCategories.isNotEmpty()) onProductCategorySelected(uiState.productCategories[0], 0)
      }

    })

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        ClippedHeader(title = if (route.equals("editproduct")) "edit product" else "add product")
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            val state = if (route.equals("editproduct")) "edit" else "add"
            SimpleText(


                text = "lets ${state} a product",
                textSize = 14,
                isUppercase = true,
                isBold = true,
                font = Font(R.font.axiformabold)
            )

            if (route.equals("editproduct")) {
                if (uiState.coverPhotoBitmap != null) {
                    Image(
                        bitmap = uiState.coverPhotoBitmap.asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp),
                        contentScale = ContentScale.Fit
                    )
                } else {
                    LoadImage(
                        imageUrl = uiState.productImageUrl, modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            } else {
                uiState.coverPhotoBitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }


            Row(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SimpleText(

                        fontWeight = FontWeight.Black,
                        textSize = 12,
                        text = "Upload photo",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )
                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp, start = 8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.Black)
                    ) {
                        IconButton(
                            onClick = { onAddCoverPhoto() },
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color.Black),
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "add icon",
                                modifier = Modifier
                                    .size(24.dp),
                                tint = Color.White
                            )
                        }
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                       /* .weight(1f)*/,
                ) {
                    SimpleText(

                        fontWeight = FontWeight.Black,
                        textSize = 12,
                        text = "Product name",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )

                    OutlinedTextField(
                        value = uiState.productName,
                        onValueChange = { onProductNameChanged(it) },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()

                            .height(48.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.Black,
                            focusedBorderColor = Color.Black,

                        ),
                        textStyle = TextStyle(color = Color.Black, fontFamily = FontFamily(Font(R.font.axiformaregular)))
                    )
                }

            }
            Column(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            ) {
                SimpleText(


//                    fontFamily = FontFamily(Font(R.font.axiformablack))

                    fontWeight = FontWeight.Black,
                    textSize = 12,
                    text = "Product description",
                    isExtraBold = true,
                    font = Font(R.font.axiformaextrabold),

                )

                OutlinedTextField(
                    value = uiState.productDescription,
                    onValueChange = { onProductDescriptionChanged(it) },
                    shape = RoundedCornerShape(8.dp),

                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                        .height(70.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Black,
                        focusedBorderColor = Color.Black,
                        backgroundColor = InputYellow,
                    ),
                    textStyle = TextStyle(color = Color.Black, fontFamily = FontFamily(Font(R.font.axiformaregular)))
                )
            }
            Row(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier
                        .weight(1.5f)
                        .padding(end = 8.dp)
                ) {
                    SimpleText(

                        fontWeight = FontWeight.Black,
                        textSize = 12,
                        text = "Product category",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )

                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .height(48.dp)
                    ) {
                        Dropdown(
                            items = uiState.productCategories,
                            onSelect = { name, int ->
                                onProductCategorySelected(name, int)
                            },
                            onShopSelect = { _, _ ->
                            },
                            productCategory = uiState.productCategory,

                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    SimpleText(

                        fontWeight = FontWeight.Black,
                        textSize = 12,
                        text = "Price",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )

                    OutlinedTextField(
                        value = uiState.productPrice,
                        onValueChange = { onProductPriceChanged(it) },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(end = 8.dp, top = 8.dp)
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.Black,
                            focusedBorderColor = Color.Black,
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1.5f)
                ) {

                    SimpleText(

                        fontWeight = FontWeight.Black,
                        textSize = 12,
                        text = "Unit",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )
                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .height(48.dp)
                    ) {
                        Dropdown(
                            onSelect = { _, _ -> },
                            onShopSelect = { _, _ -> },
                            onMeasurementSelect = {
                                onProductUnitsChanged(it)
                            },
                            type = "measurement",
                            productmeasurementitems = unitsList
                        )

                    }


              
                }

            }
            Row(

                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp)

            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)

                ) {
                    SimpleText(

                        fontWeight = FontWeight.Black,
                        textSize = 12,
                        text = "Product Quantity",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )

                    OutlinedTextField(
                        value = uiState.numberInStack,
                        onValueChange = { onNumberInStackChanged(it) },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(end = 8.dp, top = 8.dp)

                            .height(48.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.DarkGray,
                            focusedBorderColor = Color.DarkGray,
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }
                Column(modifier = Modifier
                    .weight(1f)
                    .padding(top = 10.dp)) {
                    val checkedState = remember { mutableStateOf(true) }
                    SimpleText(

                        fontWeight = FontWeight.Black,
                        textSize = 12,
                        text = "Offer",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )

                    Switch(
                        checked = checkedState.value,

                        onCheckedChange = { checkedState.value = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.Black,
                            uncheckedThumbColor = Color.White,
                            checkedTrackColor = DropyYellow,
                            uncheckedTrackColor = DropyYellow,
                            checkedTrackAlpha = 1.0f,
                            uncheckedTrackAlpha = 1.0f   )



                    )
                    
                    
                    Row() {

                        SimpleText(

                            fontWeight = FontWeight.Black,
                            textSize = 10,
                            text = "No",
                            isExtraBold = true,
                            font = Font(R.font.axiformaextrabold)
                        )
                        Spacer(modifier = Modifier.width(10.dp))

                        SimpleText(


                            fontWeight = FontWeight.Black,
                            textSize = 10,
                            text = "Yes",
                            isExtraBold = true,
                            font = Font(R.font.axiformaextrabold)
                        )

                    }

                    

                }

//
//                Column (
//
//                        ){
//                    Switch2()
//
//
//                }

            }
            Row( modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Box(
                    modifier = Modifier.background(
                            shape = RoundedCornerShape(60.dp), color = DropyYellow
                        ),

                ){
                    SimpleText(
                        fontWeight = FontWeight.Black,
                        textSize = 12,
                        text = "Enable Discount",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold),
                        padding = 5
                    )
                }
                Box(modifier = Modifier.background(
                    shape = RoundedCornerShape(60.dp), color = DropyYellow
                )){
                    SimpleText(

                        fontWeight = FontWeight.Black,
                        textSize = 12,
                        text = "Create Coupon Code",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold),
                        padding = 5
                    )
                }


            }

            Row(modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)){
                Column(
                    modifier = Modifier
                        .weight(1.5f)
                ) {
                    SimpleText(
                        fontWeight = FontWeight.Black,
                        textSize = 10,
                        text = "Discount Amount",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )

                    OutlinedTextField(
                        value = uiState.discountPrice,
                        onValueChange = { onDiscountPriceChanged(it) },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(end = 8.dp, top = 8.dp)
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            backgroundColor = InputYellow,
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1.5f)
                ) {
                    SimpleText(

                        fontWeight = FontWeight.Black,
                        textSize = 10,
                        text = "Discount %",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )

                    OutlinedTextField(
                        value = uiState.discountPercentage,
                        onValueChange = { onDiscountPercentageChanged(it) },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(end = 8.dp, top = 8.dp)
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            backgroundColor = InputYellow
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(2f)
                ) {
                    SimpleText(
                        fontWeight = FontWeight.Black,
                        textSize = 10,
                        text = "Enter a Coupon Code",
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold)
                    )

                    OutlinedTextField(
                        value = uiState.couponCode,
                        onValueChange = { onCouponCodeChanged(it) },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(end = 8.dp, top = 8.dp)
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            backgroundColor = InputYellow
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }

            }






        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            /* contentAlignment = Alignment.Center*/
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (route.equals("editproduct")) Arrangement.SpaceBetween else Arrangement.Center
        ) {
            val context = LocalContext.current
            TotallyRoundedButton(
                buttonText = if (route.equals("editproduct")) "edit product" else "add product",
                backgroundColor = DropyYellow,
                action = {
                    if (route.equals("editproduct")) {
                        //     Toast.makeText(context, "coming soon", Toast.LENGTH_SHORT).show()
                        onAddProduct("editproduct")
                    } else {
                        onAddProduct("addproduct")
                    }
                },
                widthFraction = if (route.equals("editproduct")) 0.4 else 0.6
            )

            if (route.equals("editproduct")) {

                TotallyRoundedButton(
                    buttonText = "Delete product",
                    backgroundColor = Color.Red,
                    action = {
                        onDeleteProduct()
                    },
                    widthFraction = if (route.equals("editproduct")) 0.8 else 0.6
                )
            }
        }
    }
}
//@Composable
//fun Switch2(
//    scale: Float = 2f,
//    width: Dp = 36.dp,
//    height: Dp = 20.dp,
//    strokeWidth: Dp = 2.dp,
//    checkedTrackColor: Color = Color(0xFF35898F),
//    uncheckedTrackColor: Color = Color(0xFFe0e0e0),
//    gapBetweenThumbAndTrackEdge: Dp = 4.dp
//) {
//
//    val switchON = remember {
//        mutableStateOf(true) // Initially the switch is ON
//    }
//
//    val thumbRadius = (height / 2) - gapBetweenThumbAndTrackEdge
//
//    // To move thumb, we need to calculate the position (along x axis)
//    val animatePosition = animateFloatAsState(
//        targetValue = if (switchON.value)
//            with(LocalDensity.current) { (width - thumbRadius - gapBetweenThumbAndTrackEdge).toPx() }
//        else
//            with(LocalDensity.current) { (thumbRadius + gapBetweenThumbAndTrackEdge).toPx() }
//    )
//
//    Canvas(
//        modifier = Modifier
//            .size(width = width, height = height)
//            .scale(scale = scale)
//            .pointerInput(Unit) {
//                detectTapGestures(
//                    onTap = {
//                        // This is called when the user taps on the canvas
//                        switchON.value = !switchON.value
//                    }
//                )
//            }
//    ) {
//        // Track
//        drawRoundRect(
//            color = if (switchON.value) checkedTrackColor else uncheckedTrackColor,
//            cornerRadius = CornerRadius(x = 10.dp.toPx(), y = 10.dp.toPx()),
//            style = Stroke(width = strokeWidth.toPx())
//        )
//
//        // Thumb
//        drawCircle(
//            color = if (switchON.value) checkedTrackColor else uncheckedTrackColor,
//            radius = thumbRadius.toPx(),
//            center = Offset(
//                x = animatePosition.value,
//                y = size.height / 2
//            )
//        )
//    }
//
//    Spacer(modifier = Modifier.height(18.dp))
//
//    Text(text = if (switchON.value) "ON" else "OFF")
//}


@Preview(showBackground = true)
@Composable
fun AddProductContentPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        AddProductContent(
            uiState = AddProductUiState(),
            onAddCoverPhoto = {},
            onAddProductPhotos = {},
            onNumberInStackChanged = {},
            onProductCategorySelected = { t, i ->
            },
            onProductDescriptionChanged = {},
            onProductNameChanged = {},
            onProductPriceChanged = {},
            onProductUnitsChanged = {},
            onDiscountPercentageChanged = {},
            onDiscountPriceChanged = {},
            onCouponCodeChanged = {},
            onAddProduct = {},
            route = "editproduct",
            onDeleteProduct = {},
            addProductViewModel = hiltViewModel()
        )
    }
}