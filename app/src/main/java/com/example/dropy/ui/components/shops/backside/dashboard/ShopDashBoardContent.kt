package com.example.dropy.ui.components.shops.backside.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.screens.shops.backside.shopdashboard.ShopDashboardUiState
import com.example.dropy.ui.screens.shops.backside.shopincomingorders.ShopIncomingOrdersUiState
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageUiState
import com.example.dropy.ui.theme.*

@Composable
fun ShopDashboardContent(
    uiState: ShopDashboardUiState,
    incomingorderuiState: ShopIncomingOrdersUiState,
    shopHomePageUiState: ShopHomePageUiState,
    goToIncomingOrders:()->Unit,
    goToEarnings:()->Unit,
    goToOrderHistory:()->Unit,
    goToMessages:()->Unit,
    goToWallet:()->Unit,
    goToProducts:()->Unit,
    goToCustomers:()->Unit,
    goToDisputes:()->Unit,
    goToCampaigns:()->Unit,
    goToDeliveries:()->Unit,
    onCheckedChange:(Boolean)->Unit,

){
    Column(
        modifier = Modifier
            .padding(bottom = 5.dp, top = 5.dp)
            .fillMaxHeight()
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
        ,
    ){
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            ClippedHeader(title = "my shop")

            Row(
                horizontalArrangement = Arrangement.spacedBy(1.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 15.dp).offset(y = -9.dp)

            ) {
              SimpleText(
                  text = "closed",
                  isUppercase = true,
                  isExtraBold = true,
                  font = Font(R.font.axiformablack),
                  fontWeight = FontWeight.Black,
                  textSize = 8,
                  textColor = if (uiState.shopOpen) Color.LightGray else Color.Black
              )
                Switch(checked = uiState.shopOpen, onCheckedChange =onCheckedChange, colors = SwitchDefaults.colors(
                    checkedTrackColor = LightBlue,
                    checkedThumbColor = Color.White
                ))

                SimpleText(
                    text = "open",
                    isUppercase = true,
                    isExtraBold = true,
                    font = Font(R.font.axiformablack),
                    fontWeight = FontWeight.Black,
                    textSize = 8,
                    textColor = if (uiState.shopOpen) Color.Black else Color.LightGray
                )
            }
        }

        ShopStats(
            incomingOrders = uiState.incomingOrders,
            totalEarnings = uiState.totalEarnings,
            totalOrders = uiState.totalOrders,
            totalMessages = uiState.totalMessages,
            totalBalance = uiState.totalBalance,
            totalProducts = uiState.totalProducts,
            averageRating = uiState.averageRating,
            totalCustomers = uiState.totalCustomers,
            disputes = uiState.disputes,
            activeCampaigns = uiState.activeCampaigns,
            totalDeliveries = uiState.totalDeliveries,
            goToIncomingOrders = {goToIncomingOrders()},
            goToEarnings = {goToEarnings()},
            goToOrderHistory = {goToOrderHistory()},
            goToMessages = {goToMessages()},
            goToWallet = {goToWallet()},
            goToProducts = {goToProducts()},
            goToCustomers = {goToCustomers()},
            goToDisputes = {goToDisputes()},
            goToCampaigns = {goToCampaigns()},
            goToDeliveries = {goToDeliveries()},
            incomingorderuiState = incomingorderuiState,
            shopHomePageUiState = shopHomePageUiState
        )
    }
}



@Composable
fun ShopStats(
    incomingOrders: Int,
    totalEarnings: Int,
    totalOrders: Int,
    totalMessages: Int,
    totalBalance: Int,
    totalProducts: Int,
    averageRating: Double,
    totalCustomers: Int,
    disputes: Int,
    activeCampaigns: Int,
    totalDeliveries: Int,
    goToIncomingOrders:()->Unit,
    goToEarnings:()->Unit,
    goToOrderHistory:()->Unit,
    goToMessages:()->Unit,
    goToWallet:()->Unit,
    goToProducts:()->Unit,
    goToCustomers:()->Unit,
    goToDisputes:()->Unit,
    goToCampaigns:()->Unit,
    goToDeliveries:()->Unit,
    incomingorderuiState: ShopIncomingOrdersUiState,
    shopHomePageUiState: ShopHomePageUiState
){
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
        ) {
            val colorYellow = DropyYellow
            val coloBlack = Color.Black

            // Creating a Horizontal Gradient Color
            val yellowBlack = Brush.horizontalGradient(0f to colorYellow, 1000f to coloBlack)
            Box(
                modifier = Modifier
                    .padding( 8.dp)
                    .weight(1f)

                    .background(yellowBlack, shape = RoundedCornerShape(8.dp))

            ){



                DashboardStatsContainer(
                    titleTextSize = 15,
                    titleText = "incoming orders",
                    titleTextColor = Color.White,
                    titleIsUppercase = true,
                    titleIsBold = false,
                    titleIsExtraBold = false,
                    titlePadding = null,
                    valueTextSize =26,
                    valueText =(incomingorderuiState.orderList.size).toString(),
                    valueTextColor = Color.White,
                    valueIsUppercase = true,
                    valueIsBold =false,
                    valueIsExtraBold = true,
                    valuePadding = null,
                    titleStarts = false,
                    alignStart = true,
                    backgroundColor = LightBlue,
                    onClick = {goToIncomingOrders()},


                )
            }
            val colorBlack = Color.Black
            val colorWhite = Color.White

            // Creating a Horizontal Gradient Color
            val blackWhite = Brush.horizontalGradient(0f to colorBlack, 1000f to colorWhite)
            Box(
                modifier = Modifier
                    .padding( 2.dp)
                    .weight(1f)
                    .background(blackWhite, shape = RoundedCornerShape(8.dp))

            ){
                DashboardStatsContainer(
                    titleTextSize = 15,
                    titleText = "total earnings",
                    titleTextColor = Color.White,
                    titleIsUppercase = true,
                    titleIsBold = false,
                    titleIsExtraBold = false,
                    titlePadding = null,
                    valueTextSize =26,
                    valueText =incomingorderuiState.ordersTotal.toString(),
                    valueTextColor = Color.White,
                    valueIsUppercase = true,
                    valueIsBold =false,
                    valueIsExtraBold = true,
                    valuePadding = null,
                    titleStarts = true,
                    alignStart = true,
                    backgroundColor = LightBlue,
                    onClick = {goToEarnings()},

                )
            }
        }
        Row(
            modifier = Modifier
                .padding(bottom = 2.dp)
                .fillMaxWidth()
        ) {

            val colourBlack = Color.Black
            val colourYellow = DropyYellow

            // Creating a Horizontal Gradient Color
            val blackYellow = Brush.horizontalGradient(0f to colourBlack, 1000f to colourYellow)



            Box(
                modifier = Modifier
                    .padding( 5.dp)
                    .weight(1f)

                    .background(blackYellow, shape = RoundedCornerShape(8.dp))

            ){
                DashboardStatsContainer(
                    titleTextSize = 15,
                    titleText = "orders",
                    titleTextColor = Color.White,
                    titleIsUppercase = true,
                    titleIsBold = false,
                    titleIsExtraBold = false,
                    titlePadding = null,
                    valueTextSize =26,
                    valueText =(incomingorderuiState.completedorderList.size).toString(),
                    valueTextColor = Color.White,
                    valueIsUppercase = true,
                    valueIsBold =false,
                    valueIsExtraBold = true,
                    valuePadding = null,
                    titleStarts = false,
                    alignStart = true,
                    backgroundColor = LightBlue,
                    onClick = {goToOrderHistory()},

                )
            }

            val colorBlack = Color.Black
            val colorWhite = Color.White

            // Creating a Horizontal Gradient Color
            val blackWhite = Brush.horizontalGradient(0f to colorBlack, 1000f to colorWhite)


            Box(
                modifier = Modifier
                    .padding( 5.dp)
                    .weight(1f)

                    .background(blackWhite, shape = RoundedCornerShape(8.dp))

            ){
                DashboardStatsContainer(
                    titleTextSize = 15,
                    titleText = "messages",
                    titleTextColor = Color.White,
                    titleIsUppercase = true,
                    titleIsBold = false,
                    titleIsExtraBold = false,
                    titlePadding = null,
                    valueTextSize =26,
                    valueText =totalMessages.toString(),
                    valueTextColor = Color.White,
                    valueIsUppercase = true,
                    valueIsBold =false,
                    valueIsExtraBold = true,
                    valuePadding = null,
                    titleStarts = false,
                    alignStart = true,
                    backgroundColor = LightBlue,
                    onClick = {goToMessages()},

                )
            }
            val colorYellow = DropyYellow
            val coloBlack = Color.Black

            // Creating a Horizontal Gradient Color
            val yellowBlack = Brush.horizontalGradient(0f to coloBlack, 1000f to colorYellow)

            Box(
                modifier = Modifier
                    .padding( 5.dp)
                    .weight(1f)

                    .background(yellowBlack, shape = RoundedCornerShape(8.dp))
            ){
                DashboardStatsContainer(
                    titleTextSize = 15,
                    titleText = "balance",
                    titleTextColor = Color.White,
                    titleIsUppercase = true,
                    titleIsBold = false,
                    titleIsExtraBold = false,
                    titlePadding = null,
                    valueTextSize =26,
                    valueText =totalBalance.toString(),
                    valueTextColor = Color.White,
                    valueIsUppercase = true,
                    valueIsBold =false,
                    valueIsExtraBold = true,
                    valuePadding = null,
                    titleStarts = true,
                    alignStart = true,
                    backgroundColor = LightBlue,
                    onClick = {goToWallet()},

                )
            }
        }
        Row(
            modifier = Modifier
                .padding(bottom = 2.dp)
                .fillMaxWidth()
        ) {

            val colorYellow = DropyYellow
            val colourBlack = Color.Black

            // Creating a Horizontal Gradient Color
            val yellowBlack = Brush.horizontalGradient(0f to colorYellow, 1000f to colourBlack)

            Box(
                modifier = Modifier
                    .padding( 5.dp)
                    .weight(1f)

                    .background(yellowBlack, shape = RoundedCornerShape(8.dp))
            ){
                DashboardStatsContainer(
                    titleTextSize = 15,
                    titleText = "products",
                    titleTextColor = Color.White,
                    titleIsUppercase = true,
                    titleIsBold = false,
                    titleIsExtraBold = false,
                    titlePadding = null,
                    valueTextSize =40,
                    valueText =(shopHomePageUiState.shopProducts.size).toString(),
                    valueTextColor = Color.White,
                    valueIsUppercase = true,
                    valueIsBold =false,
                    valueIsExtraBold = true,
                    valuePadding = null,
                    titleStarts = false,
                    alignStart = true,
                    backgroundColor = LightBlue,
                    onClick = {goToProducts()},

                )
            }

            val colorBlack = Color.Black
            val colorWhite = Color.White

            // Creating a Horizontal Gradient Color
            val blackWhite = Brush.horizontalGradient(0f to colorWhite, 1000f to colorBlack)


            Box(
                modifier = Modifier
                    .padding( 5.dp)
                    .weight(1f)
                    .background(blackWhite, shape = RoundedCornerShape(8.dp))

            ){
                DashboardStatsContainer(
                    titleTextSize = 15,
                    titleText = "customer rating",
                    titleTextColor = Color.White,
                    titleIsUppercase = true,
                    titleIsBold = false,
                    titleIsExtraBold = false,
                    titlePadding = null,
                    valueTextSize =40,
                    valueText =averageRating.toString(),
                    valueTextColor = Color.White,
                    valueIsUppercase = true,
                    valueIsBold =false,
                    valueIsExtraBold = true,
                    valuePadding = null,
                    titleStarts = false,
                    alignStart = false,
                    backgroundColor = LightBlue,
                    onClick = {},

                )
            }
        }
        Row(
            modifier = Modifier
                .padding(bottom = 2.dp)
                .fillMaxWidth()
        ) {

            val colourBlack = Color.Black
            val colourWhite = Color.White

            // Creating a Horizontal Gradient Color
            val blackAndWhite = Brush.horizontalGradient(0f to colourBlack, 1000f to colourWhite)



            Box(
                modifier = Modifier
                    .padding( 5.dp)
                    .weight(1f)

                    .background(blackAndWhite, shape = RoundedCornerShape(8.dp))
            ){
                DashboardStatsContainer(
                    titleTextSize = 15,
                    titleText = "customers",
                    titleTextColor = Color.White,
                    titleIsUppercase = true,
                    titleIsBold = false,
                    titleIsExtraBold = false,
                    titlePadding = null,
                    valueTextSize =40,
                    valueText =totalCustomers.toString(),
                    valueTextColor = Color.White,
                    valueIsUppercase = true,
                    valueIsBold =false,
                    valueIsExtraBold = true,
                    valuePadding = null,
                    titleStarts = false,
                    alignStart = true,
                    backgroundColor = LightBlue,
                    onClick = {goToCustomers()},

                )
            }


            val colorYellow = DropyYellow
            val coloBlack = Color.Black

            // Creating a Horizontal Gradient Color
            val yellowBlack = Brush.horizontalGradient(0f to coloBlack, 1000f to colorYellow)



            Box(
                modifier = Modifier
                    .padding( 5.dp)
                    .weight(1f)

                    .background(yellowBlack, shape = RoundedCornerShape(8.dp))
            ){
                DashboardStatsContainer(
                    titleTextSize = 15,
                    titleText = "disputes",
                    titleTextColor = Color.White,
                    titleIsUppercase = true,
                    titleIsBold = false,
                    titleIsExtraBold = false,
                    titlePadding = null,
                    valueTextSize =40,
                    valueText =disputes.toString(),
                    valueTextColor = Color.White,
                    valueIsUppercase = true,
                    valueIsBold =false,
                    valueIsExtraBold = true,
                    valuePadding = null,
                    titleStarts = false,
                    alignStart = true,
                    backgroundColor = LightBlue,
                    onClick = {goToDisputes()},

                )
            }
        }
        Row(
            modifier = Modifier
                .padding(bottom = 2.dp)
                .fillMaxWidth()
        ) {
            val colorYellow = DropyYellow
            val coloBlack = Color.Black

            // Creating a Horizontal Gradient Color
            val yellowBlack = Brush.horizontalGradient(0f to colorYellow, 1000f to coloBlack)

            Box(
                modifier = Modifier
                    .padding( 5.dp)
                    .weight(1f)

                    .background(yellowBlack, shape = RoundedCornerShape(8.dp))
            ){
                DashboardStatsContainer(
                    titleTextSize = 15,
                    titleText = "campaigns",
                    titleTextColor = Color.White,
                    titleIsUppercase = true,
                    titleIsBold = false,
                    titleIsExtraBold = false,
                    titlePadding = null,
                    valueTextSize =40,
                    valueText =activeCampaigns.toString(),
                    valueTextColor = Color.White,
                    valueIsUppercase = true,
                    valueIsBold =false,
                    valueIsExtraBold = true,
                    valuePadding = null,
                    titleStarts = false,
                    alignStart = true,
                    backgroundColor = DropyYellow,
                    onClick = {goToCampaigns()},

                )
            }
            val colourBlack = Color.Black
            val colourWhite = Color.White

            // Creating a Horizontal Gradient Color
            val blackAndWhite = Brush.horizontalGradient(0f to colourBlack, 1000f to colourWhite)



            Box(
                modifier = Modifier
                    .padding( 5.dp)
                    .weight(1f)

                    .background(blackAndWhite, shape = RoundedCornerShape(8.dp))

            ){
                DashboardStatsContainer(
                    titleTextSize = 15,
                    titleText = "shop",
                    titleTextColor = Color.White,
                    titleIsUppercase = true,
                    titleIsBold = false,
                    titleIsExtraBold = false,
                    titlePadding = null,
                    valueTextSize =40,
                    valueText =totalDeliveries.toString(),
                    valueTextColor = Color.White,
                    valueIsUppercase = true,
                    valueIsBold =false,
                    valueIsExtraBold = true,
                    valuePadding = null,
                    titleStarts = false,
                    alignStart = true,
                    backgroundColor = LightBlue,
                    onClick = {goToDeliveries()},

                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShopDashboardContentPreview(){
    Column(modifier = Modifier.fillMaxSize()) {
        ShopDashboardContent(
            uiState = ShopDashboardUiState(),
            goToIncomingOrders = {},
            goToEarnings = {},
            goToOrderHistory = {},
            goToMessages = {},
            goToWallet = {},
            goToProducts = {},
            goToCustomers = {},
            goToDisputes = {},
            goToCampaigns = {},
            goToDeliveries = {},
            incomingorderuiState = ShopIncomingOrdersUiState(),
            shopHomePageUiState = ShopHomePageUiState(),
            onCheckedChange = {}
        )
    }
}