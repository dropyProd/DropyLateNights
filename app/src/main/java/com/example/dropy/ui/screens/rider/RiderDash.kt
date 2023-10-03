package com.example.dropy.ui.screens.rider

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dropy.ui.components.topup.TopInfo
import com.example.dropy.R
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.RiderDestination
import com.example.dropy.ui.components.riderDash.BlurredImageItem
import com.example.dropy.ui.components.riderDash.FlowRowItem
import com.example.dropy.ui.components.riderDash.FlowRowPojo
import com.example.dropy.ui.components.riderDash.RiderNotificationItem
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun RiderDash(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White), verticalArrangement = Arrangement.SpaceBetween
    ) {

        TopInfo(text = "MY DASHBOARD")

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .clickable {
                    navController.navigate(RiderDestination.RIDERJOBSEARCH)
                }
                .fillMaxWidth()
                .padding(horizontal = 7.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(230.dp)
                    .height(95.dp)
                    .background(
                        brush = Brush.sweepGradient(
                            colors = listOf(
                                Color(0xFF13274F).copy(.9f),
                                Color(0xFF034694).copy(.9f),
                            )
                        ),
                        shape = RoundedCornerShape(15.dp)
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bulb),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(15.dp))
                    // .blur(radius = 2.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 14.dp, start = 13.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Text(
                        text = "TRIPS",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Text(
                        text = "SEARCH WORKS",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            RiderNotificationItem(text = "INBOX")
            RiderNotificationItem(text = "EDIT")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            BlurredImageItem(
                image = R.drawable.imgtwo,
                textOne = "BALANCE",
                textTwo = "21,435",
                navController = navController
            )
            BlurredImageItem(
                image = R.drawable.imgtwo,
                textOne = "EARNINGS",
                textTwo = "1,456,789",
                navController = navController
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            val list = remember {
                mutableListOf(
                    FlowRowPojo(image = R.drawable.imgtwo, textOne = "149", textTwo = ""),
                    FlowRowPojo(image = R.drawable.imgthre, textOne = "39", textTwo = "DISPUTES"),
                    FlowRowPojo(
                        image = R.drawable.imgfrr,
                        textOne = "4.8",
                        textTwo = "CUSTOMER RATINGS"
                    ),
                    FlowRowPojo(
                        image = R.drawable.imgone,
                        textOne = "1,458",
                        textTwo = "CUSTOMERS"
                    ),
                )
            }

            val itemSize: Dp = 210.dp / 2
            FlowRow(
                modifier = Modifier
                    .width(220.dp)
                    .padding(top = 5.dp),
                mainAxisSize = SizeMode.Expand,
                mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly
            ) {
                list.forEachIndexed { index, item ->
                    FlowRowItem(flowRowPojo = item, index = index, size = itemSize)
                }
            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(212.dp)
                    .clickable {
                        navController.navigate(AppDestinations.ADDPAYMENT)
                    }
            ) {

                Image(
                    painter = painterResource(id = R.drawable.bulb),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp))
                        .blur(radius = 4.dp),
                    contentScale = ContentScale.Crop
                )


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(horizontal = 4.dp, vertical = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(30.dp),
                    horizontalAlignment = Alignment.End
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.filter),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp),
                        colorFilter = ColorFilter.tint(color = Color.White)
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(13.dp)) {
                        Text(
                            text = "917",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.End
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = "TRANSACTIONS",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 7.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }


        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "FAV SHOPS",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                favShopItem(image = R.drawable.imgone, text = "G4S PVT LIMITED")
                favShopItem(image = R.drawable.imgthre, text = "BURGER KING")
                favShopItem(image = R.drawable.imgtwo, text = "PIZZERIA")
                favShopItem(image = R.drawable.imgfrr, text = "WHOLE FOODS")
            }
        }

    }
}

@Composable
fun favShopItem(image: Int, text: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            modifier = Modifier
                .size(70.dp)
                .border(width = 1.dp, color = Color(0xFF13274F), shape = CircleShape)
                .clip(
                    CircleShape
                )
        )

        Text(
            text = text,
            style = TextStyle(
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}