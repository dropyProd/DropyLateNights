package com.example.dropy.ui.components.shops.frontside

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.R
import com.example.dropy.network.models.AllProductsResItem
import com.example.dropy.network.models.ShopsResponseNewItem
import com.example.dropy.network.models.shopCategories.ShopCategoriesResponseItem
import com.example.dropy.network.models.shopdetails.Product
import com.example.dropy.network.models.shops.Shop
import com.example.dropy.ui.components.commons.EmptyBlock
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.components.payment.roundedtextItem
import com.example.dropy.ui.components.shops.frontside.shopcategories.ShopsCategoryList
import com.example.dropy.ui.components.shops.frontside.shoplist.FavouriteShopsList
import com.example.dropy.ui.components.shops.frontside.shoplist.ShopsList
import com.example.dropy.ui.components.shops.frontside.singleshop.shophome.BaseUrL
import com.example.dropy.ui.components.shops.shopscommons.ProductAndShopSearchBar
import com.example.dropy.ui.components.shops.shopscommons.billboards.ShopBillboard
import com.example.dropy.ui.screens.apphome.AppHomePageUiState
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.shops.backside.addshop.AddShopUiState
import com.example.dropy.ui.screens.shops.frontside.shopslandingpage.ShopsLandingPageUiState
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageUiState
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlin.random.Random

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShopsLandingPageContent(
    uiState: ShopsLandingPageUiState,
    addshopuiState: AddShopUiState,
    appuiState: AppHomePageUiState,
    shopHomeuiState: ShopHomePageUiState,
    onCategorySelected: () -> Unit,
    onAllCategoriesClicked: () -> Unit,
    onShopReview: () -> Unit,
    onShopSelected: (shopId: String, ShopsResponseNewItem?) -> Unit,
    searchItem: (String) -> Unit,
    likeClicked: (shop: ShopsResponseNewItem, Boolean) -> Unit,
    onAddToCart: (AllProductsResItem) -> Unit,
    onSearchItem: () -> Unit,
    navigateCategorySingle: () -> Unit
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        ProductAndShopSearchBar(isClicked = onSearchItem, width = .83f, searchItem)
        Spacer(modifier = Modifier.height(15.dp))
        ShopBillboard(
            coverurl = shopHomeuiState.shopCoverPhotoUrl,
            logourl = shopHomeuiState.shopLogoUrl
        )
        ShopsCategoryList(
            categoryList = addshopuiState.shopCategoryList,
            onCategorySelected = { onCategorySelected() },
            onAllCategoriesClicked = { onAllCategoriesClicked() }
        )
        Spacer(modifier = Modifier.height(10.dp))
        ShopsList(
            title = "Popular Shops",
            shopList = appuiState.popularShops,
            onShopSelected = { id, shop ->
                onShopSelected(id, shop)
            },
            onShopReview = onShopReview,
            likeClicked = likeClicked,
            favshopList = appuiState.favouriteShops,
            top = 10, start = 10
            //  size = 72
        )

        FavouriteShopsList(
            title = "my favourites",
            shopList = appuiState.favouriteShops,
            onShopSelected = { name, shop ->
                uiState.popularShops.forEach { shop ->
                    if (name.equals(shop.shop_name)) {
                        shop.id?.let { it1 -> onShopSelected(it1.toString(), null) }
                    }
                }
            }
        )

        todaysOffers(
            list = addshopuiState.shopCategoryList,
            productList = shopHomeuiState.shopProducts
        )

        Trending(
            productList = shopHomeuiState.shopProducts,
            onAddToCart = onAddToCart,
            navigateCategorySingle = navigateCategorySingle
        )

        featuredShops(
            list = addshopuiState.shopCategoryList,
            shopsList = appuiState.popularShops,
            onShopSelected = {
                onShopSelected(it, null)
            }
        )


    }
}

@Composable
fun Trending(
    productList: List<AllProductsResItem>,
    onAddToCart: (AllProductsResItem) -> Unit,
    navigateCategorySingle: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "TRENDING ITEMS",
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(
                Font(R.font.axiformaheavy)
            ),
            letterSpacing = (-0.5).sp
        )

        if (productList.isEmpty()) {
            EmptyBlock(modifier = Modifier.padding(end = 12.dp))
        } else {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(22.dp)
            ) {
                itemsIndexed(productList) { index, it ->
                    trendingItem(
                        product = it,
                        onAddToCart = onAddToCart,
                        navigateCategorySingle = navigateCategorySingle
                    )
                }
            }
        }
    }

}


@Composable
fun trendingItem(
    product: AllProductsResItem,
    onAddToCart: (AllProductsResItem) -> Unit,
    navigateCategorySingle: () -> Unit
) {
    ConstraintLayout(modifier = Modifier
        .height(61.dp)
        .clickable { navigateCategorySingle() }) {
        val (img, bdy) = createRefs()
        LoadImage(
            imageUrl = "${product.product_cover_photo}", modifier = Modifier
                .size(61.dp)
                .constrainAs(img) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .clip(RoundedCornerShape(6.dp))
        )
        /*   Image(
               painter = painterResource(id = R.drawable.broccoli),
               contentDescription = "",
               modifier = Modifier
                   .size(61.dp)
                   .constrainAs(img) {
                       start.linkTo(parent.start)
                       end.linkTo(parent.end)
                   }
           )*/

        Column(modifier = Modifier
            .constrainAs(bdy) {
                end.linkTo(parent.end, (-10).dp)
                top.linkTo(parent.top, (-4).dp)
            }
            .fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
            IconButton(
                onClick = { onAddToCart(product) },
                modifier = Modifier
                    .padding(end = 3.dp, bottom = 3.dp)
                    .size(14.dp)
                    .align(Alignment.End)

            ) {
                androidx.compose.material.Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "add item",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(Color(253, 211, 19))
                )
            }

            BackgroundedText(
                background = Color.Black,
                textColor = Color.White,
                text = "${product.product_price.toString()}/-",
                textSize = 9,
                vertical = 2,
                modifier = Modifier.padding(top = 4.dp)
            )

        }

    }
}

@Composable
fun featuredShops(
    list: List<ShopCategoriesResponseItem>,
    shopsList: List<ShopsResponseNewItem>,
    onShopSelected: (shopId: String) -> Unit,
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "FEATURED SHOPS",
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformaheavy)
                ),
                letterSpacing = (-0.5).sp
            )

            var mExpanded by remember { mutableStateOf(false) }
            var text by remember {
                mutableStateOf("")
            }

            if (!list.isEmpty())
                text = list[0].category_name.toString()
            else text = ""

            dropdownRounded(text = text!!, clicked = {
                mExpanded = true
            })
            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier
                    .wrapContentWidth()
                    .height(200.dp)
                /*   .verticalScroll(rememberScrollState())*/
            ) {
                list.forEach { label ->
                    DropdownMenuItem(
                        onClick = {
                            text = label.category_name.toString()
                            mExpanded = false
                        }, modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                    ) {
                        label.category_name?.let {
                            Text(
                                text = (it).toUpperCase(),
                                fontSize = 9.sp,
                                modifier = Modifier.padding(bottom = 7.dp),
                                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                                fontWeight = FontWeight.Black
                            )
                        }
                    }
                }
            }
        }
        val range = if (!shopsList.isEmpty()) {
            if (!shopsList.isEmpty() && shopsList.size > 2) shopsList.size - 1 else 0
        } else {
            0
        }
        if (!shopsList.isEmpty()) {
            featuredImage(
                show = true,
                size = 131,
                shop = if (range > 1) shopsList[(Random.nextInt(0, range))] else shopsList[0]
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()


                .heightIn(min = 10.dp, max = 900.dp)
        ) {
            items(shopsList) {
                featuredItem(it, onShopSelected = onShopSelected)
            }
        }
    }
}

@Composable
fun featuredImage(show: Boolean = false, size: Int = 140, shop: ShopsResponseNewItem) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(size.dp)
    ) {

        val (img, body) = createRefs()

        //get shop response lacking cover photo url

        LoadImage(
            imageUrl = shop.shop_cover_photo, modifier = Modifier
                .fillMaxSize()
                .constrainAs(img) {
                    top.linkTo(parent.top)
                }
                .clip(RoundedCornerShape(7.dp))
                .border(
                    width = 1.dp,
                    color = Color.LightGray.copy(.4f),
                    shape = RoundedCornerShape(7.dp)
                )
        )
        /*      Image(
                  painter = painterResource(id = R.drawable.imgtwo),
                  contentDescription = "", modifier = Modifier
                      .fillMaxSize()
                      .constrainAs(img) {
                          top.linkTo(parent.top)
                      }
                      .clip(RoundedCornerShape(7.dp)),
                  contentScale = ContentScale.Crop
              )
      */
        Column(modifier = Modifier
            .fillMaxSize()
            .constrainAs(body) {
                top.linkTo(parent.top)
            }
            .padding(vertical = 5.dp, horizontal = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
//                LoadImage(
//                    imageUrl = shop.shop_logo_url, modifier = Modifier
//                        .size(30.dp)
//                        .clip(shape = CircleShape)
//                        .border(width = 1.dp, color = Color.LightGray, shape = CircleShape)
//                )
                /*      Image(
                          painter = painterResource(id = R.drawable.imgtwo),
                          contentDescription = "",
                          modifier = Modifier
                              .size(30.dp)
                              .clip(shape = CircleShape),
                          contentScale = ContentScale.Crop
                      )*/


            }

            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (show) {
                    Row(
                        modifier = Modifier
                            //    .align(Alignment.Center)
                            .background(
                                color = Color(0xFFFCD313), shape = RoundedCornerShape(28.dp)
                            )
                            // .border(width = 1.dp, color = Color(0xFF584AFF), shape = RoundedCornerShape(28.dp))
                            .padding(horizontal = 10.dp, vertical = 7.dp)
                    ) {
                        shop.shop_name?.let {
                            androidx.compose.material.Text(
                                text = it.uppercase(),
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 8.sp,
                                fontFamily = FontFamily(Font(R.font.axiformaextrabold)),
                                color = Color.Black
                            )
                        }
                    }
/*                    shop.shop_name?.let {
                        BackgroundedText(
                            background = Color(0xFFFCD313),
                            textColor = Color.Black,
                            text = it.uppercase(),
                            vertical = 2,
                            horizontal = 5
                        )
                    }*/

                    imageRounded(
                        text = "${(Random.nextInt(0, 50))}K",
                        image = R.drawable.heart,
                        color = Color.White,
                        contentColor = Color.Black
                    )


                } else {
                    imageRounded(
                        text = "Kitengela",
                        imageVector = Icons.Filled.PinDrop,
                        color = Color.Black,
                        contentColor = Color.White,
                        colorFilter = ColorFilter.tint(Color.White),
                        roundedShape = 10
                    )
                }
            }
        }
    }

}

@Composable
fun featuredItem(
    shop: ShopsResponseNewItem, onShopSelected: (shopId: String) -> Unit,
) {

    Column(
        modifier = Modifier
            .padding(vertical = 15.dp)
            .clickable {
                shop.id?.let { onShopSelected(it) }
            }
            .width(150.dp)
            .padding(horizontal = 10.dp)
    ) {
        featuredImage(shop = shop)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            shop.shop_name?.let {
                Text(
                    text = it.uppercase(),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformaextrabold)
                    ),
                    letterSpacing = (-0.5).sp,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(0.7f)
                )
            }
            rateStar()
//            Text(
//                text = "4.${Random.nextInt(0, 9)}",
//                style = TextStyle(
//                    color = Color.Black,
//                    fontSize = 12.sp,
//                    fontWeight = FontWeight.ExtraBold,
//                    letterSpacing = (-0.5).sp,
//                    fontFamily = FontFamily(Font(R.font.axiformaextrabold))
//                ))
//                modifier = Modifier.constrainAs(txt) {
//                    top.linkTo(parent.top, 9.dp)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                }
//            )

//            imageRounded(text = "${(Random.nextInt(0, 50))}K", image = R.drawable.heart)

        }
    }

}

@Composable
fun rateStar() {
    Row(modifier = Modifier.padding(top = 5.dp)) {

        Text(
            text = "4.${Random.nextInt(0, 9)}",
            style = TextStyle(
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = (-0.5).sp,
                fontFamily = FontFamily(Font(R.font.axiformaextrabold)),

                ), modifier = Modifier.padding(top = 2.dp)
        )


        Image(
            painter = painterResource(id = R.drawable.fav),
            contentDescription = "",
            modifier = Modifier
                .size(15.dp)
                .align(Alignment.CenterVertically)
        )

    }

}

@Composable
fun todaysOffers(
    list: List<ShopCategoriesResponseItem>,
    productList: List<AllProductsResItem>,
    isThere: Boolean = true
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "TODAY'S OFFERS",
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformaheavy)
                ),
                letterSpacing = (-0.5).sp
            )

            var mExpanded by remember { mutableStateOf(false) }
            var text by remember {
                mutableStateOf(if (!list.isEmpty()) list[0].category_name else "")
            }

            if (isThere) {
                dropdownRounded(text = text!!, clicked = {
                    mExpanded = true
                })
            }
            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier
                    .wrapContentWidth()
            ) {
                list.forEach { label ->
                    DropdownMenuItem(onClick = {
                        text = label.category_name
                        mExpanded = false
                    }) {
                        label.category_name?.let {
                            Text(
                                text = it, fontSize = 9.sp, fontFamily = FontFamily(
                                    Font(R.font.axiformaheavy)
                                ), fontWeight = FontWeight.Black
                            )
                        }
                    }
                }
            }
        }
    }


    if (productList.isEmpty()) {
        EmptyBlock(modifier = Modifier.padding(vertical = 10.dp))
    } else {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            itemsIndexed(productList) { index, it ->
                todayOfferItem(it)
            }
        }
    }


}


@Composable
fun todayOfferItem(product: AllProductsResItem) {
    ConstraintLayout() {

        val (body, offer) = createRefs()

        Row(
            modifier = Modifier
                .zIndex(.2f)
                .constrainAs(offer) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end, (-10).dp)
                }
                .width(40.dp)
                .background(
                    color = Color.Black,
                    shape = RoundedCornerShape(3.dp)
                )
                .padding(/*horizontal = 5.dp,*/ vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${Random.nextInt(10, 50)}%\nOFF",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(
                    Font(R.font.axiformabold)
                ),
                color = Color.White,
                letterSpacing = (-0.5).sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier
                .zIndex(.1f)
                .height(140.dp)
                .width(176.dp)
                .background(
                    color = Color(0xFFFCD313),
                    shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)
                )
                .constrainAs(body) {
                    top.linkTo(parent.top, 15.dp)
                }
                .padding(bottom = 8.dp), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            LoadImage(
                imageUrl = product.product_cover_photo, modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .clip(shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))
            )
            /*      Image(
                      painter = painterResource(id = R.drawable.imgtwo),
                      contentDescription = "",
                      modifier = Modifier
                          .fillMaxWidth()
                          .height(90.dp)
                          .clip(shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)),
                      contentScale = ContentScale.Crop
                  )*/

            Text(
                text = "${product.product_name}",
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(
                    Font(R.font.axiformabold)
                ),
                letterSpacing = (-0.38).sp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    imageRounded(text = "${Random.nextInt(0, 50)}K", image = R.drawable.heart)
                    imageRounded(
                        text = "1.${Random.nextInt(0, 9)}KM",
                        image = R.drawable.fav,
                        color = Color(0xFF5CE2FE)
                    )
                }

                Row(
                    modifier = Modifier
                        .background(
                            color = Color.Black,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(horizontal = 7.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${product.product_price}/-",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(
                            Font(R.font.axiformabold)
                        ),
                        color = Color.White,
                        letterSpacing = (-0.5).sp

                    )
                }

            }
        }
    }
}

@Composable
fun imageRounded(
    color: Color = Color.Transparent,
    text: String,
    contentColor: Color = Color.Black,
    image: Int? = null,
    imageVector: ImageVector? = null,
    colorFilter: ColorFilter? = null,
    roundedShape: Int = 4
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(roundedShape.dp),
        modifier = Modifier
            .background(color = color, shape = RoundedCornerShape(roundedShape.dp))
            .wrapContentWidth()
            .padding(horizontal = 4.dp, vertical = 2.dp)
    ) {

        if (image != null) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "",
                modifier = Modifier.size(11.dp),
                colorFilter = colorFilter
            )
        } else {
            if (imageVector != null) {
                Image(
                    imageVector,
                    contentDescription = "",
                    modifier = Modifier.size(11.dp),
                    colorFilter = colorFilter
                )
            }
        }

        Text(
            text = text,
            fontSize = 7.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(
                Font(R.font.axiformasemibold)
            ),
            color = contentColor,
            letterSpacing = (-0.5).sp
        )


    }
}

@Composable
fun dropdownRounded(
    color: Color = Color.Black,
    bordercolor: Color = Color.Transparent,
    text: String,
    spacearound: Int = 3,
    modifier: Modifier = Modifier,
    contentColor: Color = Color.White,
    clicked: (() -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacearound.dp),
        modifier = modifier
            .clickable { clicked?.let { it() } }
            .wrapContentWidth()
            .background(color = color, shape = RoundedCornerShape(19.dp))
            .border(width = 1.dp, color = bordercolor, shape = RoundedCornerShape(19.dp))
            .padding(vertical = 3.dp, horizontal = 12.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(
                Font(R.font.axiformasemibold)
            ),
            color = contentColor,
            letterSpacing = (-0.38).sp
        )

        Icon(
            Icons.Filled.ArrowDropDown,
            contentDescription = "",
            tint = contentColor,
            modifier = Modifier.size(19.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShopsLandingPageContentPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        /* ShopsLandingPageContent(
             uiState = ShopsLandingPageUiState(),
             onCategorySelected = {},
             onAllCategoriesClicked = {},
             onShopSelected = {},
             onShopReview = {}
         )*/
        featuredShops(listOf(), listOf()) {}
    }
}