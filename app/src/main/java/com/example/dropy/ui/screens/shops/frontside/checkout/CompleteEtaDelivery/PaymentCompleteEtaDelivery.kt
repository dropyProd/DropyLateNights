package com.example.dropy.ui.screens.shops.frontside.checkout.CompleteEtaDelivery

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dropy.R
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination.PAYMENT_COMPLETE_ETA_DELIVERY
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.components.shops.frontside.dropdownRounded
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutViewModel
import com.example.dropy.ui.theme.LightBlue

@Composable
fun PaymentCompleteEtaDelivery(
    navController: NavController? = null,
    appViewModel: AppViewModel? = null,
    cartPageViewModel: CartPageViewModel,
    changeType: () -> Unit
) {

    val appUiState = appViewModel!!.appUiState.collectAsState()
    val cartUiState by cartPageViewModel.cartPageUiState.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(start = 21.dp, end = 10.dp, top = 12.dp, bottom = 20.dp)
            .verticalScroll(
                rememberScrollState()
            )
    ) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "THANK YOU FOR YOUR\nSERVICE, ${appUiState.value.activeProfile?.name}\t\uD83D\uDC4B",
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.axiformasemibold)),
                letterSpacing = (-0.5).sp,
                fontSize = 16.sp
            )

            Image(
                painter = painterResource(id = R.drawable.thankyou),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .clip(
                        CircleShape
                    ),
                contentScale = ContentScale.Crop
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            androidx.compose.material3.Text(
                text = "ORDER",
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformaheavy)
                ),
                letterSpacing = (-0.5).sp
            )

            dropdownRounded(
                text = "{cartPageViewModel.checkoutcurrent[0].shop?.shop_name}",
                color = Color(0xFFFCD313),
                contentColor = Color.Black
            )
        }

        Text(
            text = "Your orders are being processed",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily(
                Font(R.font.axiformamedium)
            ),
            letterSpacing = (-0.5).sp,
            modifier = Modifier.fillMaxWidth()
        )

        Image(
            painter = painterResource(id = R.drawable./*cuate*/shop1),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(348.dp),
            contentScale = ContentScale.Crop
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    Log.d("TsswdAG", "PaymentCompleteEtaDelivery: dhisehjdmklx")
                    navController?.navigate(ShopsFrontDestination.RECEIPT)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                androidx.compose.material3.Text(
                    text = "RECEIPT",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                    ),
                    color = Color.White
                )
            }
            /*  BackgroundedText(
                  background = LightBlue,
                  textColor = Color.White,
                  text = "RECEIPT",
                  vertical = 8
              )*/

            Button(
                onClick = {
                    Log.d("TsswdAG", "PaymentCompleteEtaDelivery: dhisehjdmklx")
                    changeType()
                    navController?.navigate(ShopsFrontDestination.TRACK_YOUR_ORDER)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(width = 2.dp, color = Color.Black)
            ) {
                androidx.compose.material3.Text(
                    text = "TRACK ORDER",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                    )
                )
            }

        }
        /* BackgroundedText(
             background = Color(0xFFFCD313),
             textColor = Color.Black,
             text = "TRAvvgCK ORDER",
             vertical = 8,
             modifier = Modifier.clickable {
                 Log.d("TsswdAG", "PaymentCompleteEtaDelivery: dhisehjdmklx")
                 navController?.navigate(ShopsFrontDestination.TRACK_YOUR_ORDER)
             }
         )*/
    }
}

@Preview(showBackground = true)
@Composable
fun demoh() {
    PaymentCompleteEtaDelivery(cartPageViewModel = hiltViewModel(), changeType = {})
}