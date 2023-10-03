package com.example.dropy.ui.components.reviews

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.dropy.R
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.review.RatingItem
import com.example.dropy.ui.screens.rider.IncomingJobViewModel
import com.example.dropy.ui.screens.rider.PickCustomerUiState
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel

@Composable
fun CommentBody(
    route: String = "shop",
    navController: NavController? = null,
    hasMoreInfo: Boolean,
    cartPageViewModel: CartPageViewModel,
    shopHomePageViewModel: ShopHomePageViewModel,
    appHomePageViewModel: AppHomePageViewModel,
    incomingJobViewModel: IncomingJobViewModel,
    shopname: String = "",
    pickCustomerUistate: PickCustomerUiState
) {

    val shopHomeUiState by shopHomePageViewModel.shopHomePageUiState.collectAsState()
    val incomingJobUiState by incomingJobViewModel.incomingJobsUiState.collectAsState()
    val pickCustomerUiState by incomingJobViewModel.pickCustomerUiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if (route.equals("rider") || route.equals("customer")) {
            Arrangement.spacedBy(
                25.dp
            )
        } else
            Arrangement.spacedBy(5.dp)

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            val state by appHomePageViewModel.uiState.collectAsState()
            val imageUrl = remember {
                mutableStateOf("")
            }

            if (cartPageViewModel.checkoutcurrent.isNotEmpty()) {
                state.popularShops.forEach {
                    if (!cartPageViewModel.checkoutcurrent.isEmpty()) {
//                        if (it.shop_name.equals(cartPageViewModel.checkoutcurrent[0].shop?.shop_name)) {
//                            imageUrl.value = it.shop_logo.toString()
//                        }
                    }
                }
            } else {
                imageUrl.value = pickCustomerUistate.shopprofilePic.toString()
            }



            LoadImage(
                imageUrl = if (route.equals("rider") || route.equals("customer")) {
                    ""
                } else {
                    imageUrl.value

                }, modifier = Modifier
                    .size(76.dp)
                    .clip(CircleShape)
                    .border(width = 1.dp, color = Color(0xFF584AFF).copy(.3f), shape = CircleShape)
            )
            /*      Image(
                      painter = painterResource(id = R.drawable.imgone),
                      contentDescription = "",
                      modifier = Modifier
                          .size(76.dp)
                          .clip(CircleShape)
                          .border(width = 1.dp, color = Color(0xFF584AFF), shape = CircleShape),
                      contentScale = ContentScale.Crop
                  )*/

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = when {
                        !shopname.equals("") -> shopname
//                        route.equals("shop") -> cartPageViewModel.checkoutcurrent[0].shop?.shop_name!!
                        route.equals("customer") -> pickCustomerUiState.riderName
                        else -> pickCustomerUiState.riderName
                    },
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                    )
                )

                if (route.equals("rider") || route.equals("customer")) {
                    Row(
                        modifier = Modifier.fillMaxWidth(.7f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                                .background(Color(0xFFFCD313), shape = RoundedCornerShape(20.dp))
                                .padding(horizontal = 4.dp, vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "3,667 Orders",
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                                )
                            )
                        }

                        Row(
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                                .background(Color(0xFFFCD313), shape = RoundedCornerShape(20.dp))
                                .padding(horizontal = 4.dp, vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "15K customers",
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                                )
                            )
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .background(Color(0xFFFCD313), shape = RoundedCornerShape(20.dp))
                            .padding(horizontal = 4.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = shopHomeUiState.shopLocation,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                            )
                        )
                    }

                }

            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(7.dp),
                modifier = Modifier.width(220.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.wrapContentWidth(),
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


                    RatingItem(i = 1, isCLiked = isClickedOne.value, clicked = {
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
                    RatingItem(i = 2, isCLiked = isClickedTwo.value, clicked = {

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
                    RatingItem(i = 3, isCLiked = isClickedThree.value, clicked = {

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
                    RatingItem(i = 4, isCLiked = isClickedFour.value, clicked = {

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
                    RatingItem(i = 5, isCLiked = isClickedFive.value, clicked = {

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

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .background(
                                Color(0xFFFCD313),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(horizontal = 9.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "POOR",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                            )
                        )
                    }

                    Row(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .background(
                                Color(0xFFFCD313),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(horizontal = 9.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = "EXCELLENT",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                            )
                        )
                    }
                }
            }


        }

        val comment = remember {
            mutableStateOf(if (route.equals("shop")) "type something here...." else "${pickCustomerUiState.riderName} is such a wonderful rider and I believe he's among the best around Westlands.")
        }

        BasicTextField(
            value = comment.value,
            onValueChange = {
                comment.value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(color = Color(0xFFFFF9DB), shape = RoundedCornerShape(14.dp))
                .padding(horizontal = 14.dp, vertical = 10.dp),
            maxLines = 3,
            textStyle = TextStyle(
                fontFamily = FontFamily(Font(R.font.axiformaregular)),
                fontWeight = FontWeight.Normal
            )
        )

        if (!route.equals("shop")) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .background(
                            Color(0xFFFCD313),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(horizontal = 9.dp, vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "RATE",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                        )
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(verticalArrangement = Arrangement.spacedBy(13.dp)) {
                    RiderRateItem(text = "Time Keeping")
                    RiderRateItem(text = "Handling")
                    RiderRateItem(text = "Professionalism")
                }
                textRate(modifier = Modifier.padding(start = 12.dp))
            }
            Button(
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White,
                ),
                onClick = {
                    if (hasMoreInfo) {
                        navController?.navigate(AppDestinations.REVIEWSHOP) {
                            navOptions { // Use the Kotlin DSL for building NavOptions
                                anim {
                                    enter = android.R.animator.fade_in
                                    exit = android.R.animator.fade_out
                                }
                            }
                        }
                    } else {
                        navController?.navigate(AppDestinations.REVIEWTHANKS) {
                            navOptions { // Use the Kotlin DSL for building NavOptions
                                anim {
                                    enter = android.R.animator.fade_in
                                    exit = android.R.animator.fade_out
                                }
                            }
                        }
                    }
                },
            ) {
                Text(
                    text = "SUBMIT",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                    )
                )

            }
        } else {
            Button(
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White,
                ),
                onClick = {
                    navController?.navigate(AppDestinations.REVIEWTHANKS) {
                        navOptions { // Use the Kotlin DSL for building NavOptions
                            anim {
                                enter = android.R.animator.fade_in
                                exit = android.R.animator.fade_out
                            }
                        }
                    }
                },
            ) {
                Text(
                    text = "SUBMIT",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                    )
                )

            }
        }
    }
}

@Composable
fun textRate(
    modifier: Modifier = Modifier,
    starSize: Int = 87,
    textSize: Int = 19,
    topPadding: Int = 35
){
    ConstraintLayout(modifier = modifier) {

        val (image, txt) = createRefs()


        Image(
            painter = painterResource(id = R.drawable.fav),
            contentDescription = "",
            modifier = Modifier
                .size(starSize.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                }
        )

        Text(
            text = "4.7",
            style = TextStyle(
                color = Color.Black,
                fontSize = textSize.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.axiformaextrabold))
            ),
            modifier = Modifier.constrainAs(txt) {
                top.linkTo(parent.top, topPadding.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}