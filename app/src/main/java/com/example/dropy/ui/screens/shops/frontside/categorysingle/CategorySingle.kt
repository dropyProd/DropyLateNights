package com.example.dropy.ui.screens.shops.frontside.categorysingle

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.network.models.AllProductsResItem
import com.example.dropy.network.models.shopdetails.Product
import com.example.dropy.ui.components.commons.maps.GoogleMapWrapper
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.components.commons.maps.selectlocation.GoogleMapSelectLocation
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.components.shops.frontside.shoplist.FavouriteShopsList
import com.example.dropy.ui.components.shops.frontside.shoplist.ShopsList
import com.example.dropy.ui.components.shops.frontside.todaysOffers
import com.example.dropy.ui.components.shops.shopscommons.ProductAndShopSearchBar
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.shopslandingpage.ShopsLandingPageViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import com.example.dropy.ui.theme.LightBlue
import kotlinx.coroutines.launch

@Composable

fun CategorySingle(
    appHomePageViewModel: AppHomePageViewModel,
    shopHomePageViewModel: ShopHomePageViewModel,
    cartPageViewModel: CartPageViewModel,
    shopsLandingPageViewModel: ShopsLandingPageViewModel
) {
    val uiState by appHomePageViewModel.homePageUiState.collectAsState()
    val shophomePageUiState by shopHomePageViewModel.shopHomePageUiState.collectAsState()
    val shoplandingPageUiState by shopsLandingPageViewModel.shopLandingPageUiState.collectAsState()
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        if (uiState.pageLoading || shoplandingPageUiState.pageLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.7f)
            ) {
                if (shophomePageUiState.shopLatLng != null) {
                    MapComponent(float = 1f) {
                        GoogleMapWrapper(
                            cameraPosition = shophomePageUiState.shopLatLng
                        ) { mapUiSettings, mapProperties, cameraPositionState ->

                            GoogleMapSelectLocation(
                                cameraPositionState = cameraPositionState,
                                mapUiSettings = mapUiSettings,
                                mapProperties = mapProperties,
                                locationSelected = {/* selectLocationViewModel.setUserLatLong(it)*/ },
                                markerPosition = shophomePageUiState.shopLatLng,
                                markerInfoWindowClicked = {/*selectLocationViewModel.deleteMarker()*/ },
                                title = shophomePageUiState.shopName
                            )

                        }
                    }
                }
                ProductAndShopSearchBar(searchItem = {
                    appHomePageViewModel.onSearchItem(
                        it,
                        shopHomePageViewModel,
                        appHomePageViewModel = appHomePageViewModel
                    )
                })

            }

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {

                ShopsList(
                    shopList = uiState.popularShops,
                    favshopList = listOf(),
                    onShopReview = { shopsLandingPageViewModel.onShopReviewSelected() },
                    onShopSelected = {id, shop ->
                        appHomePageViewModel.onShopSelected(
                            id,shop,
                            shopHomePageViewModel,
                            appHomePageViewModel = appHomePageViewModel
                        )
                    },
                    likeClicked = appHomePageViewModel::onFavouriteClicked
                )

                todaysOffers(
                    list = listOf(),
                    productList = shophomePageUiState.shopProducts,
                    isThere = false
                )

                FavouriteShopsList(
                    title = "my favourite shops",
                    shopList = uiState.favouriteShops,
                    onShopSelected = { _,_ ->/*onShopSelected(it) */ }
                )

                QuickBuy(list = shophomePageUiState.shopProducts, onAddToCart = {
                    scope.launch {
                        val res = cartPageViewModel.onAddCartItemClicked(
                            0,
                            it,
                            shopsLandingPageViewModel = shopsLandingPageViewModel
                        )
                        Log.d("njnlj", "ShopHomePage: $res")
                        if (res?.resultCode?.equals(0) == true) {
                            val list = cartPageViewModel.getCustomerCart()
                            if (list?.isEmpty() == false) {
                                shopHomePageViewModel.onAddToCart()
                            }
                        }
                    }
                }, onProductSelected = {
//                    shopHomePageViewModel.onProductSelected(it)
                })
            }
        }
    }
}

@Composable
fun QuickBuy(list: List<AllProductsResItem>, onAddToCart: (Int) -> Unit, onProductSelected: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp)
    ) {


        androidx.compose.material3.Text(
            text = "QUICK BUY",
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(
                Font(R.font.axiformaheavy)
            ),
            letterSpacing = (-0.5).sp
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            itemsIndexed(list) { index, it ->
                quickBuyItem(
                    product = it,
                    onAddToCart = onAddToCart,
                    onProductSelected = onProductSelected
                )
            }
        }
    }
}

@Composable
fun quickBuyItem(product: AllProductsResItem, onAddToCart: (Int) -> Unit, onProductSelected: (Int) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.clickable { onProductSelected(product.id!!) }) {
        Box(modifier = Modifier.size(89.dp)) {
            Image(
                painter = painterResource(id = R.drawable.broccoli),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()

            )
            IconButton(
                onClick = { onAddToCart(product.id!!) },
                modifier = Modifier
                    .padding(end = 6.dp, top = 8.dp)
                    .size(14.dp)
                    .align(Alignment.TopEnd)

            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "add item",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(Color(253, 211, 19))
                )
            }
        }

        product.product_name?.uppercase()?.let {
            Text(
                modifier = Modifier
                    .wrapContentWidth(),
                text = it,
                fontSize = 10.sp,
                softWrap = false,
                textAlign = TextAlign.Center,
                letterSpacing = (-0.5).sp,
                fontFamily = FontFamily(Font(R.font.axiformabold)),
                fontWeight = FontWeight.Bold,
            )
        }

        Text(
            modifier = Modifier
                .wrapContentWidth(),
            text = "per kilogram",
            fontSize = 8.sp,
            softWrap = false,
            textAlign = TextAlign.Center,
            letterSpacing = (-0.5).sp,
            fontFamily = FontFamily(Font(R.font.axiformabold)),
            fontWeight = FontWeight.Bold,
            color = Color(0xFF8B8B8B)
        )

        BackgroundedText(
            background = LightBlue,
            textColor = Color.White,
            text = "${product.product_price.toString()}/-",
            textSize = 9,
            vertical = 2
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CartegorySignlePreview(){


}