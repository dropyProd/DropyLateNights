package com.example.dropy.ui.components.shops.frontside.singleshop.singleproduct

import android.util.Log
import android.widget.Space
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.dropy.R
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.components.productpage.QuantityButton
import com.example.dropy.ui.components.productpage.ReviewItem
import com.example.dropy.ui.components.productpage.ReviewItemPojo
import com.example.dropy.ui.components.shops.frontside.singleshop.shophome.BaseUrL
import com.example.dropy.ui.screens.shops.frontside.productpage.ProductPageUiState
import com.example.dropy.ui.theme.LightBlue
import java.util.*
import kotlin.random.Random

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProductPageContent(
    uiState: ProductPageUiState,
    pricee: Int,
    onAddToCart: () -> Unit
) {
    val counter = remember {
        mutableStateOf(1)
    }
    var price = remember {
        mutableStateOf(pricee)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),

        ) {

        ProductImagesBillBoard(productImageUrlList = uiState.productImageUrls)

        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                Column(modifier = Modifier./*fillMaxWidth(0.7f)*/wrapContentWidth()) {
                    SimpleText(
                        text = uiState.productName.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        },
                        textSize = 21,
                        isExtraBold = true,
                        font = Font(R.font.axiformaextrabold),
                        horizontalPadding = 10,
                        textAlign = TextAlign.Start


                    )

                    ConstraintLayout(modifier = Modifier.padding(start = 12.dp, top = 12.dp)) {

                        val (image, txt) = createRefs()


                        Image(
                            painter = painterResource(id = R.drawable.fav),
                            contentDescription = "",
                            modifier = Modifier
                                .size(28.dp)
                                .constrainAs(image) {
                                    top.linkTo(parent.top)
                                }
                        )

                        androidx.compose.material3.Text(
                            text = "4.7",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 7.sp,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                            ),
                            modifier = Modifier.constrainAs(txt) {
                                top.linkTo(parent.top, 12.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                        )
                    }

                }



                Column(

                    horizontalAlignment = Alignment.End,
                ) {


                    val pricetxt = if (price.value.equals(0)) pricee else price.value
                    SimpleText(
                        text = "$pricetxt /- ",
                        textSize = 24,
                        isExtraBold = true,
                        font = Font(R.font.axiformaheavy)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(top = 4.dp, end = 17.dp)
                                .clip(RoundedCornerShape(50))
                                .border(1.dp, LightBlue, RoundedCornerShape(50)),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            IconButton(
                                onClick = {
                                    if (counter.value in 2..29) {
                                        counter.value -= 1
                                        val price = counter.value * pricetxt
                                        Log.d(
                                            "ETAG",
                                            "CartItem: $pricetxt  ${counter.value} $price ${pricetxt}"
                                        )
//                                        total.value = price
                                    }
                                },
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(16.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Remove,
                                    contentDescription = "subtract icon",
                                )
                            }
                            SimpleText(
                                text = counter.value.toString(), font = Font(R.font.axiformabold),
                                fontWeight = FontWeight.Bold
                            )
                            IconButton(
                                onClick = {
                                    if (counter.value in 1..29) {
                                        counter.value += 1
                                        val price = counter.value * pricetxt
                                        Log.d(
                                            "ETAG",
                                            "CartItem: $pricetxt  ${counter.value} $price "
                                        )
//                                        total.value = price
//                                        onAddClicked(counter.value, total.value)
                                    }
                                },
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(16.dp),

                                ) {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "add icon",
                                )
                            }
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center/*spacedBy(12.dp)*/,
                            modifier = Modifier
                                .padding(end = 10.dp, top = 7.dp)
                                .clickable {
                                    onAddToCart()
                                }
                                .background(
                                    color = Color.Black,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .height(40.dp)
                                .wrapContentWidth()
                                .padding(horizontal = 12.dp)
                        ) {

                            Text(
                                text = "Add to cart",
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = 12.sp,

                                    fontWeight = FontWeight.Medium,
                                    fontFamily = FontFamily(
                                        Font(R.font.axiformaextrabold)
                                    )
                                ),
                                letterSpacing = (-1).sp,
                            )


                            /*   Row(
                                   modifier = Modifier
                                       .fillMaxWidth()
                                       .fillMaxHeight()
                                       .background(color = Color.White)
                                       .border(width = 2.dp, color = LightBlue),
                                   verticalAlignment = Alignment.CenterVertically,
                                   horizontalArrangement = Arrangement.SpaceBetween
                               ) {


                                   AnimatedContent(
                                       targetState = counter.value,
                                       transitionSpec = {
                                           // Compare the incoming number with the previous number.
                                           if (targetState > initialState) {
                                               // If the target number is larger, it slides up and fades in
                                               // while the initial (smaller) number slides up and fades out.
                                               slideInVertically { height -> height } + fadeIn() with
                                                       slideOutVertically { height -> -height } + fadeOut()
                                           } else {
                                               // If the target number is smaller, it slides down and fades in
                                               // while the initial number slides down and fades out.
                                               slideInVertically { height -> -height } + fadeIn() with
                                                       slideOutVertically { height -> height } + fadeOut()
                                           }.using(
                                               // Disable clipping since the faded slide-in/out should
                                               // be displayed out of bounds.
                                               SizeTransform(clip = false)
                                           )
                                       }
                                   ) { targetCount ->

                                       BasicTextField(
                                           value = targetCount.toString(),
                                           onValueChange = {
                                               if (it.toInt() < 100) {
                                                   counter.value += it.toInt()
                                               }

                                           },
                                           keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                           textStyle = TextStyle(
                                               color = Color.Black,
                                               fontSize = 15.sp,
                                               textAlign = TextAlign.Center,
                                               fontFamily = FontFamily(
                                                   Font(R.font.axiformaextrabold)
                                               )
                                           ),
                                           modifier = Modifier.widthIn(min = 10.dp, max = 40.dp),
                                           readOnly = true
                                       )
                                   }


                                   Column(verticalArrangement = Arrangement.SpaceBetween) {
                                       QuantityButton(image = R.drawable.plus, click = {
                                           if (counter.value in 1..29) {
                                               counter.value += 1
                                               val total = counter.value * uiState.productPrice
                                               price.value = total
                                           }
                                       })
                                       QuantityButton(image = R.drawable.minus, click = {
                                           if (counter.value in 2..29) {
                                               counter.value -= 1
                                               val total = counter.value * uiState.productPrice
                                               price.value = total
                                           }
                                       })
                                   }

                               }*/
                        }

                    }


                }
            }
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                SimpleText(
                    text = "Description",
                    font = Font(R.font.axiformaextrabold),
                    textSize = 20,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(10.dp))
                SimpleText(
                    text = uiState.productDescription,
                    font = Font(R.font.axiformaregular)
                )

            }

        }


        Spacer(modifier = Modifier.height(12.dp))

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = "Recent Reviews",
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformaextrabold)
                ),
                letterSpacing = (-.58).sp,
            )


        }
        Spacer(modifier = Modifier.height(12.dp))


//        val extrasList = remember {
//            mutableListOf(
//                ExtratimePojo(text = "Option1", price = 50),
//                ExtratimePojo(text = "Option2", price = 40),
//                ExtratimePojo(text = "Option3", price = 74),
//            )
//        }

        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
//            Text(
//                text = "Extras",
//                fontSize = 12.sp,
//                fontWeight = FontWeight.ExtraBold,
//                fontFamily = FontFamily(
//                    Font(R.font.axiformaextrabold)
//                ),
//                letterSpacing = (-.58).sp,
//            )

//            LazyRow() {
//                itemsIndexed(items = extrasList) { index: Int, item: ExtratimePojo ->
//                    extraItem(extratimePojo = item)
//                }
//            }

        }


        //review images
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            for (i in 0 until 13) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 14.dp)
                        .wrapContentSize(),
                    verticalArrangement = Arrangement.spacedBy(3.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    val currentImage =
                        if (!uiState.productImageUrls.isEmpty()) uiState.productImageUrls[0] else ""

                    LoadImage(
                        imageUrl = "${BaseUrL}$currentImage", modifier = Modifier
                            .size(60.dp)
                            .clip(
                                CircleShape
                            )
                            .border(width = 1.dp, color = Color.Black, shape = CircleShape)
                    )
                    /*   Image(
                           painter = painterResource(id = R.drawable.imgtwo),
                           contentDescription = "",
                           modifier = Modifier
                               .size(60.dp)
                               .clip(
                                   CircleShape
                               )
                               .border(width = 1.dp, color = Color(0xFF584AFF), shape = CircleShape),
                           contentScale = ContentScale.Crop
                       )*/

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.fav),
                            contentDescription = "",
                            modifier = Modifier.size(13.dp)
                        )

                        val num = if (i < 9) {
                            i
                        } else {
                            0
                        }

                        Text(
                            text = "4.${num}",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(
                                Font(R.font.axiformabold)
                            )
                        )

                    }
                }
            }

        }


        //reviews
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "Reviews",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformaextrabold)
                    ),
                    letterSpacing = (-.58).sp,
                )
                Text(
                    text = "(${Random.nextInt(200, 466)})",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformasemibold)
                    ),
                    color = Color.LightGray,
                    letterSpacing = (-.58).sp,
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Text(
                    text = "Average rating",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformasemibold)
                    ),
                    color = Color(0xFF676767),
                    letterSpacing = (-.43).sp,

                    )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.fav),
                        contentDescription = "",
                        modifier = Modifier.size(13.dp)
                    )

                    Text(
                        text = "4.6",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(
                            Font(R.font.axiformaheavy)
                        ),
                        letterSpacing = (-.72).sp
                    )

                }

            }
        }

        val currentImage =
            if (!uiState.productImageUrls.isEmpty()) uiState.productImageUrls[0] else ""


        val reviewlist = remember {
            mutableListOf(
                ReviewItemPojo(
                    image = "${BaseUrL}$currentImage",
                    text = "The food was very sweet and yummy. My baby and i loved it alot"
                ),
                ReviewItemPojo(
                    image = "${BaseUrL}$currentImage",
                    text = "The food was very sweet and yummy. My baby and i loved it alot"
                ),
                ReviewItemPojo(
                    image = "${BaseUrL}$currentImage",
                    text = "The food was very sweet and yummy. My baby and i loved it alot"
                ),
                ReviewItemPojo(
                    image = "${BaseUrL}$currentImage",
                    text = "The food was very sweet and yummy. My baby and i loved it alot"
                ), ReviewItemPojo(
                    image = "${BaseUrL}$currentImage",
                    text = "The food was very sweet and yummy. My baby and i loved it alot"
                ),
                ReviewItemPojo(
                    image = "${BaseUrL}$currentImage",
                    text = "The food was very sweet and yummy. My baby and i loved it alot"
                ),
                ReviewItemPojo(
                    image = "${BaseUrL}$currentImage",
                    text = "The food was very sweet and yummy. My baby and i loved it alot"
                )
            )
        }

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .heightIn(min = 10.dp, max = 300.dp)
        ) {
            itemsIndexed(items = reviewlist) { index: Int, item: ReviewItemPojo ->
                ReviewItem(reviewItemPojo = item, index = index)
            }
        }

    }
}


@Composable
fun extraItem(extratimePojo: ExtratimePojo) {
    Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .size(24.dp)
                .background(color = Color(0xFF584AFF), shape = CircleShape)
                .border(width = 1.dp, color = Color.Black, shape = CircleShape),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = "",
                modifier = Modifier.size(14.dp),
                colorFilter = ColorFilter.tint(color = Color.White)
            )

        }
        Text(
            text = extratimePojo.text,
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(
                Font(R.font.axiformasemibold)
            )
        )

        BackgroundedText(
            background = Color(0xFFFCD313),
            textColor = Color.Black,
            text = "${extratimePojo.price}/-",
            vertical = 2
        )
    }
}

data class ExtratimePojo(
    val text: String,
    val price: Int,
)

@Composable
fun ColorItem() {
    Text(
        text = "",
        fontSize = 12.sp,
        fontWeight = FontWeight.ExtraBold,
        fontFamily = FontFamily(
            Font(R.font.axiformasemibold)
        )
    )


}

@Composable
fun OptionItem(optionPojo: OptionPojo) {
    Column(
        modifier = Modifier
            .padding(7.dp)
            .wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(7.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .size(28.dp, 26.dp)
                .background(color = optionPojo.color, shape = RoundedCornerShape(7.dp))
                .border(width = 1.dp, color = Color(0xFF707070), shape = RoundedCornerShape(7.dp))
        ) {

        }

        Text(
            text = optionPojo.text,
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(
                Font(R.font.axiformasemibold)
            ),
            letterSpacing = (-.48).sp,
        )
    }
}

data class OptionPojo(
    val text: String,
    val color: Color
)

@Preview(showBackground = true)
@Composable
fun ProductPageContentPreview() {
    Column(Modifier.fillMaxSize()) {
        ProductPageContent(
            uiState = ProductPageUiState(),
            onAddToCart = {
            },
            pricee = 0
        )
    }
}