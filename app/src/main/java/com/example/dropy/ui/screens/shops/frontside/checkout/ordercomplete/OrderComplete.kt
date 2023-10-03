package com.example.dropy.ui.screens.shops.frontside.checkout.ordercomplete

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.R
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import com.example.dropy.ui.screens.dropymainmodel.dropyMainHeader
import com.example.dropy.ui.screens.review.RatingItem
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.waterMyOrder.WaterMyOrderUiState
import com.example.dropy.ui.screens.waterMyOrder.WaterMyOrderViewModel
import com.example.dropy.ui.theme.LightBlue

@Composable
fun OrderComplete(
    navigate: () -> Unit,
    cartPageViewModel: CartPageViewModel,
    waterMyOrderViewModel:WaterMyOrderViewModel,
    appViewModel: AppViewModel
) {

    val appUistate by appViewModel.appUiState.collectAsState()

    val waterMyOrderUiState by waterMyOrderViewModel.waterMyOrderUiState.collectAsState()


    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .verticalScroll(
            rememberScrollState()
        ), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        /* dropyMainHeader()*/
        ClippedHeader(title = "ORDER COMPLETE", modifier = Modifier.padding(top = 22.dp))
        /*Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp, start = 32.dp, end = 5.dp),
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Text(
                text = "CONGRATULATIONS!",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                    letterSpacing = (-0.5).sp
                )
            )

            Text(
                text = "${appUistate.activeProfile?.name}",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily(Font(R.font.axiformabook)),
                    letterSpacing = (-0.5).sp
                )
            )
        }*/

        Image(
            painter = painterResource(id = R.drawable.reviewbot),
            contentDescription = "QR code",
            modifier = Modifier
                .fillMaxWidth()
                .height(356.dp),
            contentScale = ContentScale.FillWidth
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp)
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = FontFamily(Font(R.font.axiformabook))
                        )
                    ) {
                        append("ORDER ")
                    }

                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(Font(R.font.axiformaheavy))
                        )
                    ) {
//                        append("#${cartPageViewModel.checkoutcurrent[0].id}")
                        append("#${if (waterMyOrderUiState.createIndividualWaterOrderRes != null) waterMyOrderUiState.createIndividualWaterOrderRes!!.tracking_id else waterMyOrderUiState.getIndividualOrdersResItem?.tracking_id}")
                    }
                }
            )

            Text(
                text = "COMPLETE",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily(Font(R.font.axiformabook))
                )
            )

            reviewItem(modifier = Modifier.padding(top = 12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 33.dp)
            ) {

                Text(
                    text = "HELP",
                    style = TextStyle(
                        color = Color(0xFF059A00),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.axiformasemibold))
                    )
                )
                Image(
                    Icons.Filled.Help,
                    contentDescription = "",
                    modifier = Modifier.size(17.dp),
                    colorFilter = ColorFilter.tint(color = Color(0xFF059A00))
                )
            }

            Button(
                onClick = {
                    navigate()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.width(154.dp)
            ) {
                Text(
                    text = "REVIEW",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                    ),
                    color = Color.White
                )
            }
            /*   BackgroundedText(
                   background = LightBlue,
                   textColor = Color.White,
                   text = "REVIEW",
                   horizontal = 72,
                   vertical = 12,
                   textSize = 14,
                   modifier = Modifier.clickable {
                       navigate()
                   }
               )*/
        }
    }
}

@Preview
@Composable
fun reviewItem(modifier: Modifier = Modifier){
    Row(
        modifier = modifier.wrapContentWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(11.dp)
    ) {

        val isClickedOne = remember {
            mutableStateOf(false)
        }
        val isClickedTwo = remember {
            mutableStateOf(false)
        }
        val isClickedThree = remember {
            mutableStateOf(false)
        }
        val isClickedFour = remember {
            mutableStateOf(false)
        }
        val isClickedFive = remember {
            mutableStateOf(false)
        }


        RatingItem(i = 1, type = "OrderComplete",isCLiked = isClickedOne.value, clicked = {
            isClickedOne.value = !isClickedOne.value

            if (isClickedTwo.value == true || isClickedThree.value == true || isClickedFour.value == true || isClickedFive.value ==
                true
            ) {
                isClickedTwo.value = false
                isClickedThree.value = false
                isClickedFour.value = false
                isClickedFive.value = false
            }

        })
        RatingItem(i = 2, type = "OrderComplete",isCLiked = isClickedTwo.value, clicked = {

            isClickedTwo.value = !isClickedTwo.value

            if (isClickedTwo.value == true) {
                if (isClickedOne.value == false) isClickedOne.value = true

            }
            if (isClickedThree.value == true || isClickedFour.value == true || isClickedFive.value ==
                true
            ) {
                isClickedThree.value = false
                isClickedFour.value = false
                isClickedFive.value = false
            }
        })
        RatingItem(i = 3, type = "OrderComplete",isCLiked = isClickedThree.value, clicked = {

            isClickedThree.value = !isClickedThree.value

            if (isClickedThree.value == true) {
                if (isClickedOne.value == false || isClickedTwo.value == false) {
                    isClickedOne.value = true
                    isClickedTwo.value = true

                }


            }
            if (isClickedFour.value == true || isClickedFive.value ==
                true
            ) {
                isClickedFour.value = false
                isClickedFive.value = false
            }
        })
        RatingItem(i = 4, type = "OrderComplete",isCLiked = isClickedFour.value, clicked = {

            isClickedFour.value = !isClickedFour.value
            if (isClickedFour.value) {
                if (isClickedOne.value == false || isClickedTwo.value == false || isClickedThree.value == false) {
                    isClickedOne.value = true

                    isClickedTwo.value = true

                    isClickedThree.value = true


                }


            }
            if (isClickedFive.value == true
            ) {
                isClickedFive.value = false
            }
        })
        RatingItem(i = 5, type = "OrderComplete",isCLiked = isClickedFive.value, clicked = {

            isClickedFive.value = !isClickedFive.value
            if (isClickedFive.value) {
                if (isClickedOne.value == false || isClickedTwo.value == false || isClickedThree.value == false || isClickedFour.value == false) {
                    isClickedOne.value = true

                    isClickedTwo.value = true

                    isClickedThree.value = true

                    isClickedFour.value = true


                }
            }
        })


    }
}

//@Preview(showBackground = true)
@Composable
fun demo() {
    OrderComplete(
        navigate = {},
        cartPageViewModel = hiltViewModel(),
        waterMyOrderViewModel = hiltViewModel(),
        appViewModel = hiltViewModel()
    )
}