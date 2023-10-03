package com.example.dropy.ui.screens.review

import android.util.Log
import androidx.compose.animation.core.repeatable
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.dropy.R
import com.example.dropy.network.models.cart.OrderItem
import com.example.dropy.network.models.shopdetails.Product
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.reviews.CommentBody
import com.example.dropy.ui.components.reviews.ReviewHead
import com.example.dropy.ui.components.shops.frontside.singleshop.shophome.BaseUrL
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.rider.IncomingJobViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel

@Composable
fun ReviewShopScreen(
    navController: NavController, cartPageViewModel: CartPageViewModel,
    shopHomePageViewModel: ShopHomePageViewModel,
    appHomePageViewModel: AppHomePageViewModel,
    incomingJobViewModel: IncomingJobViewModel
) {

    val uistate = incomingJobViewModel.pickCustomerUiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF)),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = "REVIEW",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(Font(R.font.axiformaheavy))
                )
            )
        }
        ReviewHead(headtxt = "SHOP")
        CommentBody(
            navController = navController,
            hasMoreInfo = false,
            cartPageViewModel = cartPageViewModel,
            shopHomePageViewModel = shopHomePageViewModel,
            appHomePageViewModel = appHomePageViewModel,
            incomingJobViewModel = incomingJobViewModel,
            shopname = uistate.value.shopName.toString(),
            pickCustomerUistate = uistate.value
        )
        HeaderBadge(text = "ITEMS")



        if (uistate.value.shopName.equals("")) {
            if (cartPageViewModel.checkoutcurrent.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {

                    /*itemsIndexed(items = cartPageViewModel.checkoutcurrent[0].order_items!!) { index, item ->

                        val showBg = if (index % 2 == 0) {
                            true
                        } else false

                        ProductItem(showBg = showBg, rateItemPojo = item)
                    }*/

                }
            }
        }

    }

}

@Composable
fun HeaderBadge(text: String) {
    Row(
        modifier = Modifier
            .width(100.dp)
            .height(40.dp)
            .background(
                Color(0xFFFCD313),
                shape = RoundedCornerShape(topEnd = 7.dp, bottomEnd = 7.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "RATE $text",
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
        )
    }
}

@Composable
fun RatingItem(
    i: Int,
    type: String = "shop",
    size: Int = 20,
    isCLiked: Boolean,
    clicked: () -> Unit
) {
    Column(
        modifier = Modifier.wrapContentWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (type == "shop") {
            Text(
                text = (i).toString(),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                )
            )
        }

        if (isCLiked) {
            Image(
                painter = painterResource(id = R.drawable.fav),
                contentDescription = "",
                modifier = Modifier
                    .size(size.dp)
                    .clickable {
                        clicked()
                    }
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.emptystar),
                contentDescription = "",
                modifier = Modifier
                    .size(size.dp)
                    .clickable {
                        clicked()
                    }
            )
        }

    }
}

@Composable
fun ProductItem(showBg: Boolean, rateItemPojo: OrderItem) {
    Row(
        modifier = Modifier
            .padding(top = 17.dp)
            .background(
                color = if (showBg) Color(0xFFFFF9DB) else Color.Transparent,
                shape = RoundedCornerShape(19.dp)
            )
            .border(
                width = 1.dp, color = Color(0x66707070).copy(.3f),
                shape = RoundedCornerShape(19.dp)
            )
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {


        LoadImage(
            imageUrl = "${BaseUrL}${rateItemPojo.product?.product_image_url}",
            modifier = Modifier
                .border(width = 1.dp, color = Color(0xFF707070), shape = CircleShape)
                .size(56.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Fit
        )
        /*  Image(
              painter = painterResource(id = R.drawable.imgone),
              contentDescription = "",
              modifier = Modifier
                  .border(width = 1.dp, color = Color(0xFF707070), shape = CircleShape)
                  .size(56.dp)
                  .clip(CircleShape),
              contentScale = ContentScale.Crop
          )
  */
        Column(
            modifier = Modifier.width(145.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            rateItemPojo.product?.product_name?.let {
                Text(
                    text = it,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                    )
                )
            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                Row(
                    modifier = Modifier
                        .width(60.dp)
                        .height(30.dp)
                        .background(Color(0xFF584AFF), shape = RoundedCornerShape(20.dp)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "RATE",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                        )
                    )
                }


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    for (i in 0 until 5) {
                        Image(
                            painter = painterResource(id = R.drawable.fav),
                            contentDescription = "",
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }


            }


        }


        Button(
            modifier = Modifier
                .wrapContentWidth()
                .height(30.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF584AFF),
                contentColor = Color.White,
            ),
            onClick = {

            },
        ) {
            Text(
                text = "SUBMIT",
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                )
            )

        }
    }
}

data class RateItemPojo(
    val image: Int,
    val text: String
)