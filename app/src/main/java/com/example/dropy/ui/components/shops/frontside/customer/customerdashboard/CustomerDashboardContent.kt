package com.example.dropy.ui.components.shops.frontside.customer.customerdashboard

import androidx.compose.foundation.*
import com.example.dropy.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.network.models.ShopsResponseNewItem
import com.example.dropy.ui.components.commons.ProfilePic
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.shops.frontside.shoplist.FavouriteShopsList
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.shops.backside.shopincomingorders.ShopIncomingOrdersUiState
import com.example.dropy.ui.screens.shops.frontside.dashboard.customerdashboard.CustomerDashboardUiState
import com.example.dropy.ui.screens.shops.frontside.dashboard.orderhistory.CustomerOrderHistoryUiState
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue


@Composable
fun CustomerDashboardContent(
    uiState: CustomerDashboardUiState,
    customerOrdersHistoryUiState: CustomerOrderHistoryUiState,
    shopIncomingOrderUistate: ShopIncomingOrdersUiState,
    customerName: String,
    onOrderHistoryClicked: () -> Unit,
    onParcelsHistoryClicked: () -> Unit,
    onRidesHistoryClicked: () -> Unit,
    onMyAddressesClicked: () -> Unit,
    onTransactionHistoryClicked: () -> Unit,
    appHomePageViewModel: AppHomePageViewModel? = null,
    onShopSelected: (shopId: String, ShopsResponseNewItem?) -> Unit,
    onAddProfileLogo: () -> Unit

) {
    val appuiState by appHomePageViewModel?.homePageUiState!!.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        DashboardHeader(
            customerName = customerName,
            onAddProfileLogo = onAddProfileLogo,
            uiState = uiState
        )
        DashboardStats(
            noOffavs = appuiState.favouriteShops.size,
            noOfOrders = shopIncomingOrderUistate.orderList.size,
            noOfParcels = uiState.noOfParcels,
            noOfRides = uiState.noOfRides,
            noOfTransactions = uiState.noOfTransactions,
            onOrderHistoryClicked = { onOrderHistoryClicked() },
            onRidesHistoryClicked = { onRidesHistoryClicked() },
            onParcelsHistoryClicked = { onParcelsHistoryClicked() },
            onMyFavsClicked = { onMyAddressesClicked() },
            onTransactionHistoryClicked = { onTransactionHistoryClicked() },
            customerOrdersHistoryUiState = customerOrdersHistoryUiState

        )
        FavouriteShopsList(
            title = "favourite Riders",
            shopList = listOf(),
            onShopSelected = {_, _ ->}
        )
        FavouriteShopsList(
            title = "favourite shops",
            shopList = appuiState.favouriteShops,
            onShopSelected = {name, shop ->
                appuiState.popularShops.forEach { shop ->
                    if (name.equals(shop.shop_name)) {
                        shop.id?.let { it1 -> onShopSelected(it1,shop) }
                    }
                }
            }
        )
    }
}

@Composable
fun DashboardHeader(
    customerName: String,
    onAddProfileLogo: () -> Unit,
    uiState: CustomerDashboardUiState,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        boxWithBadge(text = "INBOX", show = true)

        Column(
            modifier = Modifier
                .weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ProfilePic(onAddProfileLogo = onAddProfileLogo, bitmap = uiState.coverPhoto )
            SimpleText(
                text = customerName,
                isUppercase = true,
                textSize = 21,
                textAlign = TextAlign.Center,
                isBold = true,
            )
        }

        boxWithBadge(text = "WALLET", modifier = Modifier.offset(y = 10.dp))
    }
}

@Composable
fun boxWithBadge(text: String, show: Boolean = false, modifier: Modifier = Modifier) {
    Box() {


        Column(
            modifier = modifier
                .width(43.dp)
                .height(56.dp)
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(7.dp)),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // INBOX

            Row(
                modifier = Modifier
                    .size(43.dp)
                    .background(color = Color.Black, shape = RoundedCornerShape(7.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Image(
                    Icons.Filled.Chat,
                    contentDescription = "",
                    modifier = Modifier.size(21.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )

            }

            Text(

                text = text,
                fontFamily = FontFamily(Font(R.font.axiformaextrabold)),
                fontWeight = FontWeight.Black,
                fontSize = 6.sp
            )

        }

        if (show) {
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 5.dp, y = (-5).dp)
                    .size(15.dp)
                    .background(DropyYellow, CircleShape),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "4",
                    fontFamily = FontFamily(Font(R.font.axiformaextrabold)),
                    fontWeight = FontWeight.Black,
                    fontSize = 6.sp
                )
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

@Composable
fun DashboardStats(
    noOfRides: Int,
    noOfParcels: Int,
    noOfOrders: Int,
    noOffavs: Int,
    noOfTransactions: Int,
    onOrderHistoryClicked: () -> Unit,
    onParcelsHistoryClicked: () -> Unit,
    onRidesHistoryClicked: () -> Unit,
    onMyFavsClicked: () -> Unit,
    onTransactionHistoryClicked: () -> Unit,
    customerOrdersHistoryUiState: CustomerOrderHistoryUiState
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
                    stat = customerOrdersHistoryUiState.orderList.size,
                    onClick = { onOrderHistoryClicked() },
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
                    .background(LightBlue)
                    .clickable { onTransactionHistoryClicked() },
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.workss), contentDescription = "", modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .height(93.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .blur(12.dp), contentScale = ContentScale.Crop
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


@Preview
@Composable
fun CustomerDashboardContentPreview() {
//     Column(modifier = Modifier.fillMaxSize()) {
         CustomerDashboardContent(
             uiState = CustomerDashboardUiState(),
             shopIncomingOrderUistate = ShopIncomingOrdersUiState(),
             onOrderHistoryClicked = {},
             onRidesHistoryClicked = {},
             onParcelsHistoryClicked = {},
             onMyAddressesClicked = {},
             onTransactionHistoryClicked = {},
             customerName = "",
             customerOrdersHistoryUiState = CustomerOrderHistoryUiState(),
             onAddProfileLogo = {},
             onShopSelected = {_, _ ->}
         )
//     }
//    DashboardHeader("mosh try", onAddProfileLogo = {}, uiState = CustomerDashboardUiState())
}