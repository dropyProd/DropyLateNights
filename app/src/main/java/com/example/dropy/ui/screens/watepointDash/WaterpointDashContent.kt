package com.example.dropy.ui.screens.watepointDash

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.network.models.getWaterPointOrders.Truck
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.shops.frontside.customer.customerdashboard.boxWithBadge
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.screens.shops.frontside.dashboard.orderhistory.CustomerOrderHistoryUiState
import com.example.dropy.ui.theme.LightBlue

@Composable
fun WaterPointDashContent(waterPointDashUiState: WaterPointDashUiState, navigateOrders: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ClippedHeader(title = "MY DASHBOARD", modifier = Modifier.padding(top = 22.dp))
        Column(modifier = Modifier
            .padding(start = 14.dp, end = 15.dp, top = 40.dp)
            .fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .weight(1f).clickable { },
                    contentAlignment = Alignment.CenterStart
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cassh),
                        contentDescription = "",
                        modifier = Modifier
                            /* .padding(8.dp)*/
                            .fillMaxWidth()
                            .height(93.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .blur(12.dp),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {

                        SimpleText(
                            text = "SALES",
                            textColor = Color.White,
                            textSize = 14,
                            isUppercase = true,
                            fontWeight = FontWeight.SemiBold,
                            font = Font(R.font.axiformasemibold)
                        )

                        SimpleText(
                            text = "0",
                            padding = 4,
                            textColor = Color.White,
                            textSize = 14,
                            fontWeight = FontWeight.Black,
                            font = Font(R.font.axiformablack)
                        )

                    }
                }

                boxWithBadge(text = "MESSAGES", show = true, modifier = Modifier.padding(end = 8.dp).weight(1f))
                boxWithBadge(text = "SETTINGS", show = false)
            }
            Row(
                modifier = Modifier.padding(top = 30.dp, bottom = 31.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .weight(1f)
                        .clickable { },
                    contentAlignment = Alignment.CenterStart
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.watertruck),
                        contentDescription = "",
                        modifier = Modifier
                            /* .padding(8.dp)*/
                            .fillMaxWidth()
                            .height(93.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .blur(12.dp),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {

                        SimpleText(
                            text = "WATER VOLUME",
                            textColor = Color.White,
                            textSize = 14,
                            isUppercase = true,
                            fontWeight = FontWeight.SemiBold,
                            font = Font(R.font.axiformasemibold)
                        )

                        SimpleText(
                            text = "${if (waterPointDashUiState.waterpoint != null) waterPointDashUiState.waterpoint?.capacity else waterPointDashUiState.waterpointO?.capacity }LT",
                            padding = 4,
                            textColor = Color.White,
                            textSize = 14,
                            fontWeight = FontWeight.Black,
                            font = Font(R.font.axiformablack)
                        )

                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .weight(1f)
                        .clickable { },
                    contentAlignment = Alignment.CenterStart
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.regi),
                        contentDescription = "",
                        modifier = Modifier
                            /* .padding(8.dp)*/
                            .fillMaxWidth()
                            .height(93.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .blur(12.dp),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {

                        SimpleText(
                            text = "SALES",
                            textColor = Color.White,
                            textSize = 14,
                            isUppercase = true,
                            fontWeight = FontWeight.SemiBold,
                            font = Font(R.font.axiformasemibold)
                        )

                        SimpleText(
                            text = "0",
                            padding = 4,
                            textColor = Color.White,
                            textSize = 14,
                            fontWeight = FontWeight.Black,
                            font = Font(R.font.axiformablack)
                        )

                    }
                }
            }
            DashboardStats(
                noOfRides = 0,
                noOfParcels = 0,
                noOfOrders = 0,
                noOffavs = 0,
                noOfTransactions = 0,
                navigateOrders = { navigateOrders()},
                onParcelsHistoryClicked = { /*TODO*/ },
                onRidesHistoryClicked = { /*TODO*/ },
                onMyFavsClicked = { /*TODO*/ },
                onTransactionHistoryClicked = { /*TODO*/ },
                waterPointDashUiState = waterPointDashUiState
            )

            Text(
                text = "RECENT TRUCKS",
                fontSize = 10.sp,
//                            fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformaheavy)
                ),
                letterSpacing = (-0.48).sp,
                lineHeight = 19.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 30.dp)
            )


            LazyRow(
                modifier = Modifier
                    .padding(top = 13.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(33.dp)
            ) {
                itemsIndexed(waterPointDashUiState.trucks){
                    index, item ->
                    imageText(truck = item)
                }
            }


        }
    }

}

@Composable
fun imageText(truck: Truck) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LoadImage(
            imageUrl = truck.image,
            modifier = Modifier
                .size(71.dp)
                .clip(
                    CircleShape
                )
        )
        Text(
            text = truck.license_plate.toString().uppercase(),
            fontSize = 9.sp,
//                            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(
                Font(R.font.axiformaextrabold)
            ),
            letterSpacing = (-0.43).sp,
            lineHeight = 17.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 13.dp)
        )
    }
}

@Composable
fun DashboardStats(
    noOfRides: Int,
    noOfParcels: Int,
    noOfOrders: Int,
    noOffavs: Int,
    noOfTransactions: Int,
    navigateOrders: () -> Unit,
    onParcelsHistoryClicked: () -> Unit,
    onRidesHistoryClicked: () -> Unit,
    onMyFavsClicked: () -> Unit,
    onTransactionHistoryClicked: () -> Unit,
    waterPointDashUiState: WaterPointDashUiState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Row(
            modifier = Modifier
                .weight(2f)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                DashboardStatContainer(
                    title = "orders",
                    stat =waterPointDashUiState.list.size,
                    onClick = { navigateOrders() },
                    image = R.drawable.shop1
                )
                DashboardStatContainer(
                    title = "parcels",
                    stat = noOfParcels,
                    onClick = { onParcelsHistoryClicked() },
                    image = R.drawable.workss
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                DashboardStatContainer(
                    title = "rides taken",
                    stat = noOfRides,
                    onClick = { onRidesHistoryClicked() },
                    image = R.drawable.emergencyyy
                )
                DashboardStatContainer(
                    title = "fav shops",
                    stat = noOffavs,
                    onClick = { onMyFavsClicked() },
                    image = R.drawable.garbagee
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onTransactionHistoryClicked() },
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.workss),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .height(93.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .blur(12.dp),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .padding(8.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(35.dp)
                ) {
                    SimpleText(
                        text = noOfTransactions.toString(),
                        padding = 4,
                        textColor = Color.White,
                        textSize = 13,
                        fontWeight = FontWeight.Black,
                        font = Font(R.font.axiformablack)
                    )
                    SimpleText(
                        text = "transactions",
                        textColor = Color.White,
                        textSize = 9,
                        isUppercase = true,
                        fontWeight = FontWeight.SemiBold,
                        font = Font(R.font.axiformasemibold)
                    )
                }
            }
        }
    }
}

@Composable
fun DashboardStatContainer(
    onClick: () -> Unit,
    stat: Int,
    title: String,
    image: Int
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(4 / 3f)
            .clip(RoundedCornerShape(8.dp))
            .background(LightBlue)
            .clickable { onClick() },
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            painter = painterResource(id = image), contentDescription = "", modifier = Modifier
                /* .padding(8.dp)*/
                .fillMaxWidth()
                .height(93.dp)
                .clip(RoundedCornerShape(8.dp))
                .blur(12.dp), contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            SimpleText(
                text = stat.toString(),
                padding = 4,
                textColor = Color.White,
                textSize = 14,
                fontWeight = FontWeight.Black,
                font = Font(R.font.axiformablack)
            )
            SimpleText(
                text = title,
                textColor = Color.White,
                textSize = 9,
                isUppercase = true,
                fontWeight = FontWeight.SemiBold,
                font = Font(R.font.axiformasemibold)
            )
        }
    }
}

@Preview
@Composable
fun demo() {
    WaterPointDashContent(waterPointDashUiState = WaterPointDashUiState(), navigateOrders = {})
}