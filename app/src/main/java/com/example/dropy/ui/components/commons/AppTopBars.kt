package com.example.dropy.ui.components.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.components.shops.frontside.customer.orderprocess.customercart.OrderDetailsDataClass
import com.example.dropy.ui.components.shops.shopscommons.ProductAndShopSearchBar
import com.example.dropy.ui.theme.DropyTheme
import com.example.dropy.ui.theme.DropyYellow

@Composable
fun AppTopBar(
    showSuperApp: Boolean = false,
    showLogo: Boolean = true,
    showImageRight: Boolean = false,
    showSearchBar: Boolean = false,
    showCartButton: Boolean = true,
    showBackButton: Boolean = true,
    onBackButtonClicked: () -> Unit,
    onDashboardButtonClicked: () -> Unit,
    onCartButtonClicked: () -> Unit,
    onSearchItem: (() -> Unit)? = null,
    searchItem: ((String) -> Unit)? = null,
    showImage: Boolean = true,
    cartsize: Int = 0
) {
    TopAppBar(
        backgroundColor = Color.White,
        elevation = 0.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
//                    .weight(1f)
                    .wrapContentSize()
            ) {
                if (showSuperApp) {
                    BackgroundedText(
                        background = Color.Transparent,
                        textColor = Color.Black,
                        text = "SUPER APP",
                        borderColor = Color.Black,
                        vertical = 3
                    )
                }

                if (showBackButton) {
                    Row(
                        modifier = Modifier
                            .padding(start = 24.dp)
                            .size(30.dp)
                            .clickable { onBackButtonClicked() }
                            .clip(CircleShape)
                            .background(Color.Black),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Icon(
                            imageVector = Icons.Filled.ChevronLeft,
                            contentDescription = "back button",
                            tint = Color.White,
                            modifier = Modifier
                                .size(15.dp)
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .weight(2f),
                contentAlignment = Alignment.Center
            ) {
                if (showLogo) {
                    Image(
                        painter = painterResource(id = R.drawable.dropylogocropped),
                        contentDescription = "Dropy logo",
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                    )
                }

                if (showSearchBar) {
                    ProductAndShopSearchBar(isClicked = onSearchItem, width = .99f, searchItem)
                }

            }
            Row(
                modifier = Modifier
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (showImage) {
                    Image(
                        painter = painterResource(id = R.drawable.shop1),
                        contentDescription = "profile pic",
                        modifier = Modifier
                            .size(46.dp)
                            .clickable {
                                onDashboardButtonClicked()
                            }
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }


                if (showCartButton) {
                    Column() {
                        /*    IconButton(
                                onClick = { onCartButtonClicked() }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ShoppingBasket,
                                    contentDescription = "shopping cart",
                                    modifier = Modifier
                                        .size(32.dp)
                                )
                            }
    */
                        BadgedBox(
                            modifier = Modifier.padding(top = 1.dp),
                            badge = {
                                Row(
                                    modifier = Modifier
                                        .size(18.dp)
                                        .background(color = Color(0xFFFF0000), shape = CircleShape),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "$cartsize",
                                        style = TextStyle(fontSize = 10.sp, color = Color.White)
                                    )
                                }
                            }) {
                            Icon(
                                painter = painterResource(id = R.drawable.cartt),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        onCartButtonClicked()
                                    },
                                tint = Color.Black
                            )
                        }

                        /* if (cartsize > 0) {
                             BadgedBox(badge = {
                                 Row(
                                     modifier = Modifier.offset(50.dp, (-20).dp)
                                         .wrapContentSize()
                                         .background(color = Color.Black),
                                     verticalAlignment = Alignment.CenterVertically,
                                     horizontalArrangement = Arrangement.Center
                                 ) {

                                 }
                             }) {

                                 Text(
                                     text = "${cartsize}", style = TextStyle(
                                         color = Color.Black,
                                         fontSize = 12.sp,
                                         fontFamily = FontFamily(
                                             Font(R.font.axiformaregular)
                                         )
                                     )
                                 )
                             }
                         }*/
                    }

                }

                if (showImageRight) {
                    Image(
                        painter = painterResource(id = R.drawable.shop1),
                        contentDescription = "profile pic",
                        modifier = Modifier
                            .padding(start = 15.dp)
                            .size(46.dp)
                            .clickable {
                                onDashboardButtonClicked()
                            }
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}


@Composable
fun AppBackSideTopBar(
    showLogoBackside: Boolean = true,
    showCartBackside: Boolean = true,
    onBackButtonClicked: () -> Unit,
    onDashboardButtonClicked: () -> Unit,
) {
    TopAppBar(
        backgroundColor = Color.White,
        elevation = 0.dp,
        modifier = Modifier
            .height(64.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
            ) {

//                this values are actual values from xd (mdpi)
                Row(
                    modifier = Modifier
                        .padding(start = 24.dp)
                        .width(30.dp)
                        .height(30.dp)
                        .background(color = Color.Black, shape = CircleShape)
                        .clickable {
                            onBackButtonClicked()
                        }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ChevronLeft,
                        contentDescription = "back button",
                        tint = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    )
                }

                /* IconButton(
                     onClick = { onBackButtonClicked() },
                     modifier = Modifier
                         .padding(start = 24.dp)
                         .size(20.dp)
                         .clip(CircleShape)
                         .background(Color.Black)

                 ) {

                 }*/
            }
            /*   if (showLogoBackside) {

                   Box(
                       modifier = Modifier
                           .weight(2f),
                       contentAlignment = Alignment.Center
                   ) {
                       Image(
                           painter = painterResource(id = R.drawable.dropylogo),
                           contentDescription = "Dropy logo",
                           modifier = Modifier
                               .fillMaxWidth(0.8f)
                       )
                   }
               }*/
            Row(
                modifier = Modifier
                    .padding(6.dp)
                    .clip(CircleShape),
                horizontalArrangement = Arrangement.Center

            ) {
                Image(

                    painter = painterResource(id = R.drawable.shop1),
                    contentDescription = "profile pic",
                    modifier = Modifier
                        .size(56.dp)
                        .clickable {
                            onDashboardButtonClicked()
                        }
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop

                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppTopBarPreview() {
    DropyTheme {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            /*       AppTopBar(
                       showBackButton = true,
                       onBackButtonClicked = { *//*TODO*//* },
                onDashboardButtonClicked = { *//*TODO*//* },
                onCartButtonClicked = { *//*TODO*//* },
                appViewModel = AppViewModel()
            )*/
            AppBackSideTopBar(
                onBackButtonClicked = { /*TODO*/ },
                onDashboardButtonClicked = { /*TODO*/ }
            )
        }
    }
}
