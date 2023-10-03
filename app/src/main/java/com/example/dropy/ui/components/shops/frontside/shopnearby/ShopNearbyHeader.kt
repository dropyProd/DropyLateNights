package com.example.dropy.ui.components.shops.frontside.shopnearby

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BadgedBox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.app.AppViewModel

@Composable
fun ShopNearbyHeader(
    modifier: Modifier = Modifier,
    query: String,
    cartSize: Int,
    appViewModel: AppViewModel,
    onCartButtonClicked: ()-> Unit
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White.copy(.3f), Color.White.copy(.97f)
                    )
                )
            )
            .padding(horizontal = 15.dp, vertical = 18.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            //back button
            Row(
                modifier = Modifier
                    .clickable { appViewModel?.onBackButtonClicked() }
                    .background(color = Color.Black.copy(.97f), shape = CircleShape)
                    .size(30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Icon(Icons.Filled.ChevronLeft, contentDescription = "", tint = Color(0xFFFCD313))


            }

            Text(
                text = "DISCOVER",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.ExtraBold
                ),
                letterSpacing = (-0.5).sp,
                fontFamily = FontFamily(Font(R.font.axiformaextrabold))
            )

            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {

                Image(
                    painter = painterResource(id = R.drawable.shop1),
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

         /*       BadgedBox(badge = {
                    Row(
                        modifier = Modifier
                            .size(24.dp)
                            .background(color = Color(0xFFFF0000), shape = CircleShape),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "$cartSize",
                            style = TextStyle(fontSize = 10.sp, color = Color.White)
                        )
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingBasket,
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                }*/
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
                                text = "$cartSize",
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
            }

        }

        Text(
            text = "Finding the nearest $query suppliers",
            style = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily(Font(R.font.axiformamedium))
            )
        )

    }

}