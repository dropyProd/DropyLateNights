package com.example.dropy.ui.components.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.dropy.ui.screens.dropymainmodel.dropyMainHeader
import com.example.dropy.R
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.theme.LightBlue

@Composable
fun DropyPayment(navController: NavController? = null, route: String = "default") {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 14.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        dropyMainHeader()
        payTab()
        paymentOption()
        parcelInsure()
        paymentInfo()
        amountItem()
        Button(
            modifier = Modifier
                .width(133.dp)
                .height(36.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                contentColor = Color.White,
            ),
            onClick = {
                if (route.equals("default")) {
                    navController?.navigate(ParcelDestination.PARCEL_PAYMENTTHANKS){
                        navOptions { // Use the Kotlin DSL for building NavOptions
                            anim {
                                enter = android.R.animator.fade_in
                                exit = android.R.animator.fade_out
                            }
                        }
                    }
                }else   navController?.navigate(AppDestinations.REVIEWRIDER){
                    navOptions { // Use the Kotlin DSL for building NavOptions
                        anim {
                            enter = android.R.animator.fade_in
                            exit = android.R.animator.fade_out
                        }
                    }
                }
            },
            shape = RoundedCornerShape(32.dp)
        ) {
            Text(
                text = "COMPLETE",
                style = TextStyle(color = Color.White, fontWeight = FontWeight.ExtraBold)
            )

        }
    }
}

@Composable
fun paymentInfo() {
    Column(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .fillMaxWidth()
            .background(color = Color.White),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Payment Option",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily(Font(R.font.axiformamedium))
                )
            )


            Text(
                text = "...............................................",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily(Font(R.font.axiformablack))
                )
            )

            ConstraintLayout(
                modifier = Modifier
                    .width(73.dp)
                    .height(41.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFA8A1FF),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(
                        color = Color.Transparent, shape = RoundedCornerShape(5.dp)
                    )
            ) {


                val (logo, txt) = createRefs()
                Image(
                    painter = painterResource(id = R.drawable.dropylogo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(60.dp)
                        .constrainAs(logo) {
                            top.linkTo(parent.top, -7.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                Text(
                    text = "PAY",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.axiformaheavy))
                    ),
                    modifier = Modifier.constrainAs(txt) {
                        top.linkTo(parent.top, 30.dp)
                        end.linkTo(parent.end, 5.dp)
                    }
                )

            }

        }

        infoPayItem(text1 = "Parcel from home to courier", text2 = "2,940/-")
        infoPayItem(text1 = "Parcel from courier to receiver", text2 = "240/-")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "Dropy Insurance",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily(Font(R.font.axiformamedium))
                    )
                )

                Text(
                    text = "1% OF TOTAL",
                    style = TextStyle(
                        color = Color(0xFF584AFF),
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily(Font(R.font.axiformablack))
                    )
                )

            }


            Text(
                text = "............................",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily(Font(R.font.axiformablack))
                )
            )

            Text(
                text = "N/A",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily(Font(R.font.axiformamedium))
                )
            )


        }


    }
}


@Composable
fun amountItem() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Total Amount",
            style = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily(Font(R.font.axiformaheavy))
            )
        )


        Text(
            text = "............................",
            style = TextStyle(
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily(Font(R.font.axiformablack))
            )
        )

        Text(
            text = "2,650/-",
            style = TextStyle(
                color = Color.Black,
                fontSize = 23.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.axiformaheavy))
            )
        )
    }

}

@Composable
fun infoPayItem(text1: String, text2: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text1,
            style = TextStyle(
                color = Color.Black,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily(Font(R.font.axiformamedium))
            )
        )

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {

            Text(
                text = "............................",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily(Font(R.font.axiformablack))
                )
            )

            Text(
                text = text2,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily(Font(R.font.axiformamedium))
                )
            )
        }


    }
}


@Composable
fun parcelInsure() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Text(
                text = "PARCEL INSURE",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily(Font(R.font.axiformablack))
                )
            )

            Text(
                text = "We are safeguarding your parcel with Dropy insure." +
                        "Claim incase of any damages. Insurance on goods" +
                        "value of 10,000/-and below",
                style = TextStyle(
                    color = Color(0xFF584AFF),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily(Font(R.font.axiformamediumitalic))
                ),
                modifier = Modifier.width(218.dp)
            )
        }

        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(9.dp)
        ) {

            Switch(checked = false, onCheckedChange = {})

            Text(
                text = "1% OF TOTAL",
                style = TextStyle(
                    color = Color(0xFFA8A1FF),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily(Font(R.font.axiformablack))
                ),
                modifier = Modifier.padding(top = 2.dp)
            )

            BackgroundedText(
                background = Color(0xFFA8A1FF),
                textColor = Color.White,
                text = "DROPY INSURE",
                vertical = 2,
                textSize = 8
            )

        }

    }
}

@Composable
fun paymentOption() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Other Payment Options",
            style = TextStyle(
                color = Color.Black,
                fontSize = 10.sp,
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily(Font(R.font.axiformablack))
            ),
            modifier = Modifier.align(alignment = Alignment.End)
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            PaymentOptionItem(color = Color(0xAD39B54A), image = R.drawable.mpesaa)
            PaymentOptionItem(color = Color(0xC2F79E1B), image = R.drawable.visa)
            PaymentOptionItem(color = LightBlue, image = R.drawable.visa)

        }


    }
}

@Composable
fun PaymentOptionItem(color: Color, image: Int) {

    ConstraintLayout(modifier = Modifier.wrapContentSize()) {

        val (plus, bdy) = createRefs()

        Row(
            modifier = Modifier
                .constrainAs(plus) {
                    end.linkTo(parent.end, -11.dp)
                    top.linkTo(parent.top, -12.dp)
                }
                .zIndex(.2f)
                .background(color = LightBlue, shape = CircleShape)
                .padding(5.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = "",
                modifier = Modifier
                    .size(14.dp),
                colorFilter = ColorFilter.tint(color = Color.White)
            )
        }

        Row(
            modifier = Modifier
                .zIndex(.1f)
                .constrainAs(bdy) {
                    top.linkTo(parent.top)
                }
                .width(93.dp)
                .height(57.dp)
                .border(width = 1.dp, color = color, shape = RoundedCornerShape(7.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = image),
                contentDescription = "",
                modifier = Modifier
                    .size(70.dp)
            )

        }
    }
}

@Composable
fun payTab() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .width(111.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .width(111.dp)
                    .height(70.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFA8A1FF),
                        shape = RoundedCornerShape(21.dp)
                    )
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFFCD313),
                                Color(0xFFEFE4B3),
                                Color(0xFFFBF7EE),
                                Color(0xF7E3E0D3),
                            )
                        ), shape = RoundedCornerShape(10.dp)
                    )
            ) {


                val (logo, txt) = createRefs()
                Image(
                    painter = painterResource(id = R.drawable.dropylogo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(80.dp)
                        .constrainAs(logo) {
                            top.linkTo(parent.top, -7.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                Text(
                    text = "PAY",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.axiformaheavy))
                    ),
                    modifier = Modifier.constrainAs(txt) {
                        top.linkTo(parent.top, 39.dp)
                        end.linkTo(parent.end, 16.dp)
                    }
                )

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Black, shape = RoundedCornerShape(12.dp))
                    .height(4.dp)
            ) {

            }
        }


        Column(
            modifier = Modifier
                .width(243.dp)
                .height(70.dp)
                .border(width = 1.dp, color = Color(0xFFA8A1FF), shape = RoundedCornerShape(10.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFFCD313),
                            Color(0xFFEFE4B3),
                            Color(0xFFFBF7EE),
                            Color(0xF7E3E0D3),
                        )
                    ), shape = RoundedCornerShape(10.dp)
                )
                .padding(top = 20.dp, start = 17.dp, end = 17.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "PAY",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                ),
                modifier = Modifier.padding(end = 14.dp)
            )

            Text(
                text = "7,340/-",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(Font(R.font.axiformaheavy))
                )
            )
        }
    }
}

@Preview
@Composable
fun screen() {
    DropyPayment()
}


