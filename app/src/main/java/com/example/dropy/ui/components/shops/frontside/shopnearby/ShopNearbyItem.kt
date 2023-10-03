package com.example.dropy.ui.components.shops.frontside.shopnearby

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.dropy.R
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.appdrawer.ProfileTypes
import com.example.dropy.ui.components.shops.frontside.singleshop.shophome.BaseUrL
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.apphome.ProductSearch
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import kotlinx.coroutines.launch


@Composable
fun ShopNearbyItem(
    showBg: Boolean,
    shopNearbyItem: ProductSearch,
    navController: NavController,
    shopHomePageViewModel: ShopHomePageViewModel,
    cartPageViewModel: CartPageViewModel,
    appViewModel: AppViewModel,
    appHomePageViewModel: AppHomePageViewModel
) {
    val scope = rememberCoroutineScope()
    ConstraintLayout() {

        val (addbtn, body) = createRefs()

        if (shopNearbyItem.productlogourl != null) {
            IconButton(
                onClick = {
                    scope.launch {
                        val res = shopNearbyItem.product?.id?.let {
                            cartPageViewModel.onAddCartItemClicked(
                                0,
                                it
                            )
                        }
                        Log.d("njnlj", "ShopHomePage: $res")
                        if (res?.resultCode?.equals(0) == true) {
                            val list = cartPageViewModel.getCustomerCart()
                            if (list?.isEmpty() == false) {
                                shopHomePageViewModel.onAddToCart()
                            }
                        }
                    }
                },
                modifier = Modifier
                    .constrainAs(addbtn) {
                        end.linkTo(parent.end, (-10).dp)
                        top.linkTo(parent.top, (-5).dp)
                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "add item",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color(253, 211, 19))
                )
            }
        }

        Row(
            modifier = Modifier
                .constrainAs(body) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 20.dp)
                .fillMaxWidth()
                .background(
                    color = if (showBg) Color(0x26FCD313) else Color.Transparent,
                    shape = RoundedCornerShape(24.dp)
                )
                .border(
                    width = 1.dp,
                    color = if (showBg) Color(0x66707070).copy(.28f) else Color.LightGray.copy(.28f),
                    shape = RoundedCornerShape(24.dp)
                )
                .clickable {
                    if (shopNearbyItem.product != null) {
//                        shopNearbyItem.product?.id?.let { shopHomePageViewModel.setId(it) }
                        navController.navigate(ShopsFrontDestination.SINGLE_PRODUCT)
                    } else {
                        if (appViewModel.appUiState.value.activeProfile?.type.equals(ProfileTypes.CUSTOMER)) {
                            shopNearbyItem.shop.id?.let {
                                appHomePageViewModel.onShopSelected(
                                    shopId = it,
                                    shopHomePageViewModel = shopHomePageViewModel,
                                    appHomePageViewModel = appHomePageViewModel,
                                  shopsResponseNewItem =  shopNearbyItem.shop
                                )
                            }
                        }

                    }
                }
                .padding(vertical = 7.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {


            //image cont

            Row(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(5.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                /*   Image(
                    painter = painterResource(id = R.drawable.imgtwo),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
*/
                LoadImage(
                    imageUrl = if (shopNearbyItem.productlogourl != null) "${BaseUrL}" + shopNearbyItem.productlogourl else shopNearbyItem.shoplogourl,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
                Text(
                    text = (if (shopNearbyItem.product != null) shopNearbyItem.product.product_name else shopNearbyItem.shop.shop_name).toString(),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.axiformabold))
                    )
                )



                Row(
                    modifier = Modifier
                        .widthIn(min = 100.dp, max = 150.dp)
                        .background(color = Color(0xFFFCD313), shape = RoundedCornerShape(14.dp))
                        .padding(horizontal = 7.dp, vertical = 4.dp)
                ) {

                    Text(
                        text = if (shopNearbyItem.productlogourl != null) shopNearbyItem.shop.shop_name.toString() else shopNearbyItem.shop.shop_location/*?.place_name*/.toString(),
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = FontFamily(Font(R.font.axiformamedium))
                        )
                    )

                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(13.dp)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.fav),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp)
                        )

                        Text(
                            text = shopNearbyItem.rating.toString(),
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                            )
                        )
                    }

                    Row(
                        modifier = Modifier
                            .wrapContentWidth()
                            .background(
                                color = Color(0xFF584AFF),
                                shape = RoundedCornerShape(14.dp)
                            )
                            .padding(horizontal = 9.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "${shopNearbyItem.distance}KM",
                            style = TextStyle(
                                color = if (showBg) Color.White else Color.Black,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                fontFamily = FontFamily(Font(R.font.axiformamedium))
                            )
                        )
                    }

                    Text(
                        text = "${shopNearbyItem.eta} MINUTES",
                        style = TextStyle(
                            color = Color.LightGray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = FontFamily(Font(R.font.axiformamedium))
                        )
                    )

                }

            }
            if (shopNearbyItem.product != null) {
                Text(
                    text = "${shopNearbyItem.product?.product_price}/-",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                    )
                )
            }
        }
    }

}

data class ShopNearbyItem(
    val image: Int,
    val title: String,
    val desc: String,
    val rating: Double,
    val distance: Double,
    val time: Int,
    val price: String,
)