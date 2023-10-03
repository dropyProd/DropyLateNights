package com.example.dropy.ui.screens.water.waterHome

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Water
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.screens.btn
import com.example.dropy.ui.screens.item

@Composable
fun WaterHomeContent(appViewModel: AppViewModel? = null, navigateTankBoreHole: (String) -> Unit) {
    appViewModel?.setSystemUiControllerColor(Color.Transparent)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(top = 30.dp)
            .verticalScroll(rememberScrollState())
//            .padding(vertical = 10.dp, horizontal = 16.dp),
        , horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(70.dp)
    ) {

        Row(
            modifier = Modifier
                .padding(horizontal = 26.dp)
                .fillMaxWidth()
                .height(149.dp)
                .background(Color(0xFFF5F5F5), RoundedCornerShape(13.dp))
                .border(width = 1.dp, shape = RoundedCornerShape(13.dp), color = Color(0xFFDEDEDE))
            //.border(width = 1.dp, Color(0xFFDEDEDE), RoundedCornerShape(13.dp))
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(top = 22.dp, start = 28.dp)
            ) {
                Text(
                    text = "WELCOME TO DROPY WATER",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    ),
                    letterSpacing = (-0.5).sp
                )

                Text(
                    text = "WE HELP YOU TRANSPORT WATER",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformasemibold)
                    ),
                    letterSpacing = (-0.5).sp
                )
            }

            Image(
                painter = painterResource(id = R.drawable.truckwater),
//                Icons.Filled.Water,
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 62.dp, end = 34.dp)
                    .width(72.dp)
                    .height(72.dp),
                contentScale = ContentScale.FillWidth
            )

        }

        Row(
            modifier = Modifier
                .padding(horizontal = 46.dp)
                .fillMaxWidth(),
//                .padding(horizontal =),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            itemWater(
                image = R.drawable.truckcopy,
                text = "\nCLEAN WATER",
                color = Color(0xFFAFF5FE), borderColor = Color(0xFFDEDEDE),
                clickable = {
                    navigateTankBoreHole("CLEAN WATER")
                }
            )
            itemWater(
                image = R.drawable.truckwater,
                text = "\nTREATED WATER",
                color = Color(0xFFF5F5F5), borderColor = Color(0xFFDEDEDE),
                clickable = {
                    navigateTankBoreHole("TREATED WATER")
                }
            )
        }

        Row(
            modifier = Modifier
                .padding(start = 46.dp, top = 8.dp, end = 46.dp)
                .fillMaxWidth(),
//                .padding(start = 25.dp)
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            itemWater(
                image = R.drawable.bottle,
                text = "\nDISPENSER REFILL",
                color = Color(0xFFF5F5F5), borderColor = Color(0xFFDEDEDE),
                clickable = {
                    navigateTankBoreHole("DISPENSER WATER")
                }
            )
            itemWater(
                image = R.drawable.bottle,
                text = "\nBOTTLED WATER",
                color = Color(0xFFF5F5F5), borderColor = Color(0xFFDEDEDE),
                clickable = {
                    navigateTankBoreHole("BOTTLED WATER")
                }
            )
        }


//        btn(navController = navController)
    }
}

@Composable
fun itemWater(
    image: Int,
    text: String,
    modifier: Modifier = Modifier,
    color: Color,
    borderColor: Color,
    clickable: () -> Unit
) {
    Column(
        modifier = modifier
            .width(131.dp)
            .height(149.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(13.dp)
            )
            .border(width = 1.dp, shape = RoundedCornerShape(13.dp), color = borderColor)
            .clickable {
                clickable()
            },
        verticalArrangement = Arrangement.spacedBy(46.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.End)
                .width(44.dp)
                .height(44.dp)
                .padding(top = 9.dp, end = 13.dp),
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(
                Font(R.font.axiformablack)
            ),
            letterSpacing = (-0.5).sp,
            lineHeight = 17.sp
        )
    }
}

@Preview
@Composable
fun demo() {
    WaterHomeContent(
        navigateTankBoreHole = {}
    )
}