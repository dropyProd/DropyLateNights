package com.example.dropy.ui.components.apphome

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.network.models.RoutesPojo
import com.example.dropy.network.models.ShopsResponseNewItem
import com.example.dropy.network.models.shops.Shop
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.components.shops.frontside.shoplist.FavouriteShopsList
import com.example.dropy.ui.components.shops.frontside.shoplist.ShopsList
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.components.shops.shopscommons.ProductAndShopSearchBar
import com.example.dropy.ui.screens.apphome.AppHomePageUiState
import com.example.dropy.ui.theme.DropyYellow

@Composable
fun AppHomePageContent(
    uiState: AppHomePageUiState,
    customerName: String,
    onShopSelected: (shopId: String, shopsResponseNewItem: ShopsResponseNewItem?) -> Unit,
    onGoToShops: () -> Unit,
    onGoToRides: () -> Unit,
    onGoToWater: () -> Unit,
    onGoToParcels: () -> Unit,
    onShopReview: () -> Unit,
    onSearchItem: () -> Unit,
    onProfilePhotoClicked: () -> Unit,
    searchItem: (String) -> Unit,
    likeClicked: (shop: ShopsResponseNewItem, Boolean) -> Unit

) {
    val context = LocalContext.current

    Column(

        modifier = Modifier
            .padding(start = 10.dp, top = 10.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        /*  if (uiState.pageLoading) {
              CircularProgressIndicator()
          } else {*/
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 15.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 24.dp)
            ) {
                ClippedHeader(title = "welcome to dropy", modifier = Modifier.offset(x = (-18).dp))
                Spacer(modifier = Modifier.height(20.dp))

                SimpleText(
                    textSize = 18,
                    text = "Hello ${customerName},"
                )
                Spacer(modifier = Modifier.height(10.dp))
                SimpleText(
                    textSize = 18,
                    text = "Need anything?",
                    isBold = true
                )
            }

            Image(
                painter = painterResource(id = R.drawable.shop1),
                contentDescription = "",
                modifier = Modifier
                    .size(70.dp)
                    .offset(y = (10).dp)
                    .clip(
                        CircleShape
                    )
                    .clickable {
                        onProfilePhotoClicked(

                        )
                    },
                contentScale = ContentScale.Crop

            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BackgroundedText(
                background = Color.Black,
                textColor = Color.White,
                text = "SHOPPING",
                vertical = 3,
                horizontal = 12,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 12.dp)
            )
            ProductAndShopSearchBar(searchItem = searchItem, isClicked = {
                onSearchItem()
            })

        }

        val listOne = remember {
            mutableListOf(
                RoutesPojo(
                    image = R.drawable.homeshops,
                    name = "shops",
                    route = {
                        onGoToShops()
                    }
                ), RoutesPojo(
                    image = R.drawable.homeparcels,
                    name = "parcels",
                    route = {
                        onGoToParcels()
                    }
                ), RoutesPojo(
                    image = R.drawable.homerides,
                    name = "rides",
                    route = {
                        onGoToRides()
                    }
                )/*,  RoutesPojo(
                    image = R.drawable.homeshops,
                    name = "logistics",
                    route = {
                        onGoToShops()
                    }
                )*//*, RoutesPojo(
                    image = R.drawable.homeparcels,
                    name = "travel",
                    route = {
                        onGoToParcels()
                    }
                ), RoutesPojo(
                    image = R.drawable.homerides,
                    name = "garbage",
                    route = {
                        onGoToRides()
                    }
                ), RoutesPojo(
                    image = R.drawable.homerides,
                    name = "bookings",
                    route = {
                        onGoToRides()
                    }
                ), RoutesPojo(
                    image = R.drawable.homerides,
                    name = "works",
                    route = {
                        onGoToRides()
                    }
                ), RoutesPojo(
                    image = R.drawable.homerides,
                    name = "medical",
                    route = {
                        onGoToRides()
                    }
                )*/
            )
        }

        val listTwo = remember {
            mutableListOf(
                RoutesPojo(
                    image = R.drawable.watertruck,
                    name = "water",
                    route = {
                        onGoToWater()
                    }
                ), RoutesPojo(
                    image = R.drawable.scan,
                    name = "business",
                    route = {
                        onGoToRides()
                    }
                ),
                RoutesPojo(
                    image = R.drawable.ammbu,
                    name = "emergencies",
                    route = {
                        onGoToShops()
                    }
                )
            )
        }

        Row(
            modifier = Modifier.padding(top = 25.dp).wrapContentWidth(),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            listOne.forEach {
                Box(
                    modifier = Modifier
                        .height(128.dp)
                        .width(100.dp)
                    //.weight(1f)
                ) {
                    Location(
                        image = painterResource(id = it.image),
                        destinationName = it.name,
                        onDestinationSelected = it.route
                    )
                }
            }

        }

        Row(
            modifier = Modifier.padding(top = 4.dp).wrapContentWidth(),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            listTwo.forEach {
                Box(
                    modifier = Modifier
                        .height(128.dp)
                        .width(100.dp)
                    //.weight(1f)
                ) {
                    Location(
                        image = painterResource(id = it.image),
                        destinationName = it.name,
                        onDestinationSelected = it.route
                    )
                }
            }

        }


        Box(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 4.dp, start = 26.dp)
                .height(128.dp)
                .width(100.dp)
            //.weight(1f)
        ) {
            Location(
                image = painterResource(id = R.drawable.truck),
                destinationName = "logistics",
                onDestinationSelected = {
                    onGoToWater()
                }
            )
        }

        /*LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(*//*top = 24.dp, bottom = 24.dp,*//* start = 20.dp,
                end = 20.dp
            ),
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 24.dp, bottom = 24.dp)
                .fillMaxWidth()
                .height(400.dp)
        ) {
            items(items = listOne, itemContent = { item ->
                Box(
                    modifier = Modifier
                        .height(128.dp)
                        .width(100.dp)
                    //.weight(1f)
                ) {
                    Location(
                        image = painterResource(id = item.image),
                        destinationName = item.name,
                        onDestinationSelected = item.route
                    )
                }
            })
        }*/
//        Row(
//            modifier = Modifier
//                .padding(top = 24.dp, bottom = 24.dp)
//                .fillMaxWidth()
//                .horizontalScroll(rememberScrollState()),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically,
//            content = {
//                itemsIndexed(ite)
//            }
//        ) {
//            Box(
//                modifier = Modifier
//                    .height(120.dp)
//                    .width(90.dp)
//                //.weight(1f)
//            ) {
//                Location(
//                    image = painterResource(id = R.drawable.homeshops),
//                    destinationName = "shops",
//                    onDestinationSelected = { onGoToShops() }
//                )
//            }
//
//
//
////                Box(
////                    modifier = Modifier
////                        .height(160.dp)
////                        .width(125.dp)
////                    //.weight(1f)
////                ) {
////                    Location(
////                        image = painterResource(id = R.drawable.workss),
////                        destinationName = "works",
////                        onDestinationSelected = {
////                            //Toast.makeText(context, "Coming soon :)", Toast.LENGTH_SHORT).show()
////                            onGoToParcels()
////                        }
////                    )
////                }
//
////                Box(
////                    modifier = Modifier
////                        .height(160.dp)
////                        .width(125.dp)
////                    //.weight(1f)
////                ) {
////                    Location(
////                        image = painterResource(id = R.drawable./*garbage*/garbagee),
////                        destinationName = "garbage",
////                        onDestinationSelected = {
////                            /*   Toast.makeText(
////                                   context,
////                                   "Coming soon :)",
////                                   Toast.LENGTH_SHORT
////                               ).show()*/
////                            onGoToParcels()
////                        }
////                    )
////                }
//
////                Box(
////                    modifier = Modifier
////                        .height(155.dp)
////                        .width(140.dp)
////                    // .weight(1f)
////                ) {
////                    Location(
////                        image = painterResource(id = R.drawable./*ambulance*/emergencyyy),
////                        destinationName = "emergencies",
////                        onDestinationSelected = {
////                            onGoToParcels()
////                        }
////                    )
////                }
//
//            /*  Box(
//                  modifier = Modifier
//                      .height(120.dp)
//                      .width(100.dp)
//                      //.weight(1f)
//              ) {
//                  Location(
//                      image = painterResource(id = R.drawable.gas),
//                      destinationName = "Xgas",
//                      onDestinationSelected = {
//                          Toast.makeText(
//                              context,
//                              "Coming soon :)",
//                              Toast.LENGTH_SHORT
//                          ).show()
//                      }
//                  )
//              }*/
//
//        }

        Log.d("frefreff", "AppHomePageContent: ${uiState.popularShops}")
        /*ShopsList(
            title = "popular shops",
            shopList = uiState.popularShops,
            onShopSelected = { item, shop ->
                onShopSelected(item, shop)
            },
            onShopReview = onShopReview,
            likeClicked = likeClicked,
            favshopList = uiState.favouriteShops
        )
        FavouriteShopsList(
            title = "my favourites",
            shopList = uiState.favouriteShops,
            onShopSelected = { name, shop ->
                uiState.popularShops.forEach { shop ->
                    if (name.equals(shop.shop_name)) {
                        shop.id?.let { it1 -> onShopSelected(it1, null) }
                    }
                }
            }
        )*/
        // }

    }
}

@Composable
fun Location(
    image: Painter,
    destinationName: String,
    onDestinationSelected: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            //  .aspectRatio(3 / 4f)
            .fillMaxHeight()
//            .width(100.dp)
            .clip(RoundedCornerShape(14.dp))
            .clickable { onDestinationSelected() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = image,
            contentDescription = destinationName,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        StyledText(
            backgroundColor = /*DropyYellow*/Color.Black,
            textColor = Color.White,
            textSize = 7,
            text = destinationName,
            isUppercase = true,
            isExtraBold = true,
            fontFamily = R.font.axiformaheavy
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AppHomePageContentPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AppHomePageContent(
            uiState = AppHomePageUiState(),
            onShopSelected = { _, _ -> },
            onGoToParcels = {},
            onGoToRides = {},
            onGoToShops = {},
            onShopReview = {},
            searchItem = {},
            onGoToWater = {},
            customerName = "",
            likeClicked = { _, _ -> },
            onSearchItem = {},
            onProfilePhotoClicked = {}
        )
    }
}