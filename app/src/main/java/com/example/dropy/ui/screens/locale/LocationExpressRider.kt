package com.example.dropy.ui.screens.locale

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.screens.dropymainmodel.dropyMainHeader
import com.example.dropy.R
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.google.android.gms.maps.model.LatLng

@Composable
fun LocationExpressRider(navController: NavController? = null, startLocale: LatLng? = null, appViewModel: AppViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.7f)
        ) {

            val (map, head) = createRefs()
         /*   if (startLocale != null) {
                MapsScreen(modifier = Modifier.constrainAs(head) {
                    top.linkTo(parent.top)
                }, start = startLocale)
            }*/
            dropyMainHeader(modifier = Modifier.constrainAs(map) {
                top.linkTo(parent.top)
            })
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            BackgroundedText(
                background = Color(0xFFFCD313),
                textColor = Color.Black,
                text = "RIDER ON THE WAY",
                vertical = 3
            )

            personItem(appViewModel = appViewModel)
            Text(
                text = "The parcel delivery person is on the way to collect the parcel",
                style = TextStyle(
                    color = Color(0xFF584AFF),
                    fontSize = 7.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily(Font(R.font.axiformamediumitalic))
                ),
                modifier = Modifier.align(Alignment.End)
            )

            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF584AFF),
                    contentColor = Color.White,
                ),
                onClick = {
                    navController?.navigate(ParcelDestination.PARCEL_SCANQR){
                        navOptions { // Use the Kotlin DSL for building NavOptions
                            anim {
                                enter = android.R.animator.fade_in
                                exit = android.R.animator.fade_out
                            }
                        }
                    }
                },
                modifier = Modifier
                    .width(150.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(
                    text = "CONTINUE",
                    style = TextStyle(fontWeight = FontWeight.ExtraBold, color = Color.White)
                )

            }

        }


    }
}

@Composable
fun personItem(clicked: (() -> Unit)? = null, appViewModel: AppViewModel) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .background(
                color = colorResource(id = R.color.fadedorange),
                shape = RoundedCornerShape(32.dp)
            )
            .border(width = 1.dp, color = Color(0xFFCCC9FC), shape = RoundedCornerShape(32.dp))
            .padding(horizontal = 17.dp)
            .clickable {
                clicked?.let { it() }
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.imgone),
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(width = 1.dp, color = Color(0xFF584AFF), shape = CircleShape),
                contentScale = ContentScale.Crop
            )

            val state by appViewModel.appUiState.collectAsState()

            Column(modifier = Modifier.wrapContentWidth()) {
                Text(
                    text = state.activeProfile!!.name,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily(Font(R.font.axiformablack))
                    )
                )
                Text(
                    text = "Delivery Person",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.axiformasemibold))
                    )
                )

            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            BackgroundedImage(background = Color(0xFF1BFC13), image = R.drawable.phone)
            BackgroundedImage(background = Color(0xFFFCD313), image = R.drawable.email)
        }
    }
}

@Composable
fun BackgroundedImage(
    background: Color,
    border: Color = Color.Transparent,
    imageColor: Color = Color.Black,
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    image: Int,
    size: Int = 40,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .size(size.dp)
            .background(background, shape = shape)
            .border(1.dp, border, shape)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            modifier = Modifier.size(20.dp),
            colorFilter = ColorFilter.tint(
                imageColor
            )
        )
    }
}

@Preview
@Composable
fun screen() {
   LocationExpressRider(appViewModel = hiltViewModel())
}


