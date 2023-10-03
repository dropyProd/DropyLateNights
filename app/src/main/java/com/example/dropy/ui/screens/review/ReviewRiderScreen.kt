package com.example.dropy.ui.screens.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dropy.ui.components.reviews.CommentBody
import com.example.dropy.ui.components.reviews.ReviewHead
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.rider.IncomingJobViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel

@Composable
fun ReviewRiderScreen(
    navController: NavController? = null,
    hasMoreInfo: Boolean,
    cartPageViewModel: CartPageViewModel,
    shopHomePageViewModel: ShopHomePageViewModel,
    appHomePageViewModel: AppHomePageViewModel,
    incomingJobViewModel: IncomingJobViewModel,
    type: String
) {
    val uiState by incomingJobViewModel.pickCustomerUiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {

        /*  Row(
              modifier = Modifier.fillMaxWidth(),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.Center
          ) {

              Text(
                  text = "REVIEW",
                  style = TextStyle(
                      color = Color.Black,
                      fontSize = 28.sp,
                      fontWeight = FontWeight.ExtraBold
                  )
              )
          }*/
        ReviewHead(headtxt = if (type.equals("rider")) "CUSTOMER" else "RIDER")
        CommentBody(
            type,
            navController = navController,
            hasMoreInfo = hasMoreInfo,
            cartPageViewModel = cartPageViewModel,
            shopHomePageViewModel = shopHomePageViewModel,
            appHomePageViewModel = appHomePageViewModel,
            incomingJobViewModel = incomingJobViewModel,
            pickCustomerUistate = uiState
        )
    }
}

@Preview
@Composable
fun demo() {
    ReviewRiderScreen(
        hasMoreInfo = false,
        cartPageViewModel = hiltViewModel(),
        shopHomePageViewModel = hiltViewModel(),
        appHomePageViewModel = hiltViewModel(),
        incomingJobViewModel = hiltViewModel(),
        type = ""
    )
}