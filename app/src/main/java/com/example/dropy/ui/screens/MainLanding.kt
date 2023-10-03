package com.example.dropy.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.dropy.R
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.order.BackgroundedImageText

@Composable
fun MainLanding(navController: NavController? = null, appViewModel: AppViewModel) {
    appViewModel.setSystemUiControllerColor(Color.Transparent)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .verticalScroll(rememberScrollState())
            .padding(vertical = 20.dp, horizontal = 4.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.dropylogo),
            contentDescription = "",
            modifier = Modifier
                .width(209.dp)
                .height(174.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            item(
                image = R.drawable.ic_shoppingapp,
                text = "SHOPS",
                modifier = Modifier
                    .width(172.dp)
                    .height(188.dp)
            )
            item(
                image = R.drawable.ic_logistics,
                text = "PARCELS",
                modifier = Modifier
                    .width(172.dp)
                    .height(188.dp)
            )
        }

        item(
            image = R.drawable.ic_ontheway,
            text = "RIDES",
            modifier = Modifier
                .fillMaxWidth()
                .height(199.dp),
            width = 260
        )

        btn(navController = navController)
    }
}

@Composable
fun btn(navController: NavController?) {
    Row(
        modifier = Modifier
            .clickable {
                navController?.navigate(AppDestinations.ON_BOARDING) {
                    navOptions { // Use the Kotlin DSL for building NavOptions
                        anim {
                            enter = android.R.animator.fade_in
                            exit = android.R.animator.fade_out
                        }
                    }
                }
            }
            .width(232.dp)
            .height(49.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF1E1E1E),
                        Color(0xFF212122),
                        Color(0xFF83838D)
                    )
                ), shape = RoundedCornerShape(33.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = "WELCOME",
                style = TextStyle(
                    color = Color(0xFFFFD200),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(Font(R.font.axiformablack))
                )
            )

            Image(
                Icons.Filled.ChevronRight,
                contentDescription = "",
                modifier = Modifier
                    .width(7.dp)
                    .height(11.dp),
                colorFilter = ColorFilter.tint(color = Color(0xFFFFD200))
            )

        }


    }
}

@Composable
fun item(image: Int, text: String, modifier: Modifier, width: Int = 150) {
    Column(
        modifier = modifier
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(10.dp)
            )
            .border(width = 1.dp, shape = RoundedCornerShape(10.dp), color = Color(0xFFE3E2D9)),
        verticalArrangement = Arrangement.spacedBy(13.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            modifier = Modifier
                .width(width.dp)
                .height(150.dp)
                .padding(top = 40.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(
                Font(R.font.axiformablack)
            ),
            letterSpacing = (-0.5).sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun demo() {
    MainLanding(appViewModel = hiltViewModel())
}