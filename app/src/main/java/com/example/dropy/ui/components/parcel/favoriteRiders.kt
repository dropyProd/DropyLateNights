package com.example.dropy.ui.components.parcel

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.dropy.R
import com.example.dropy.ui.app.RiderDestination
import com.example.dropy.ui.app.navigation.parcelnavigation.ParcelDestination
import com.example.dropy.ui.components.order.BackgroundedImageText
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.theme.LightBlue


@Composable
fun favoriteRiders(navController: NavController? = null, route: String, returnItem: (favoriteRiderPojo) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .background(color = Color.Transparent)
            .padding(start = 14.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            BackgroundedText(
                background = Color(0xFFFCD313),
                textColor = Color.Black,
                text = "FAVORITE RIDERS",
                textSize = 10
            )

            Text(
                text = "VIP feature Additional charges when you choose your favorite riders",
                style = TextStyle(
                    color = Color(0xFF584AFF),
                    fontSize = 7.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(Font(R.font.axiformamediumitalic))
                )
            )
        }


        val ridersLIst = remember {
            mutableListOf(
                favoriteRiderPojo(image = R.drawable.imgone, text = "RAY", eta = 12, price = 200),
                favoriteRiderPojo(image = R.drawable.imgfrr, text = "KAMA", eta = 5, price = 2534),
                favoriteRiderPojo(image = R.drawable.imgone, text = "TOM", eta = 9, price = 534),
                favoriteRiderPojo(image = R.drawable.imgthre, text = "RAY", eta = 16, price = 3242),
                favoriteRiderPojo(image = R.drawable.imgtwo, text = "KAMA", eta = 5, price = 876),
                favoriteRiderPojo(image = R.drawable.imgone, text = "TOM", eta = 4, price = 654),
                favoriteRiderPojo(image = R.drawable.imgfrr, text = "KAMA", eta = 7, price = 542),
                favoriteRiderPojo(image = R.drawable.imgthre, text = "TOM", eta = 11, price = 970),
                favoriteRiderPojo(image = R.drawable.imgone, text = "RAY", eta = 14, price = 586),
            )
        }

        val indexSelected = remember {
            mutableStateOf(0)
        }

        LazyRow(modifier = Modifier.fillMaxWidth()) {

            itemsIndexed(items = ridersLIst) { index, item ->

                favoriteItem(item, selectedindex = indexSelected.value, index = index, click = {
                    returnItem(item)
                    indexSelected.value = index
                })
            }
        }

        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                contentColor = Color.White,
            ),
            onClick = {
                if (route.equals("default")) {
                    navController?.navigate(ParcelDestination.PARCEL_PAY){
                        navOptions { // Use the Kotlin DSL for building NavOptions
                            anim {
                                enter = android.R.animator.fade_in
                                exit = android.R.animator.fade_out
                            }
                        }
                    }
                }else {
                    navController?.navigate(RiderDestination.RIDERPICKUP){
                        navOptions { // Use the Kotlin DSL for building NavOptions
                            anim {
                                enter = android.R.animator.fade_in
                                exit = android.R.animator.fade_out
                            }
                        }
                    }
                }
            },
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text(
                text = "CONTINUE",
                style = TextStyle(fontWeight = FontWeight.ExtraBold, color = Color.White)
            )

        }


    }
}

@Composable
fun favoriteItem(
    FavoriteRiderPojo: favoriteRiderPojo,
    selectedindex: Int,
    index: Int,
    size: Int = 46,
    horizontal: Int = 13,
    click: (Int) -> Unit
) {

    val show = if (selectedindex.equals(index)) {
        true
    } else false

    Column(
        modifier = Modifier
            .clickable { click(index) }
            .padding(horizontal = horizontal.dp)
            .wrapContentSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(
                    width = 3.dp,
                    shape = CircleShape,
                    color = if (show) Color(0xFFA8A1FF) else Color.Transparent
                )
                .wrapContentSize()
                .padding(4.dp)
        ) {
            Image(
                painter = painterResource(id = FavoriteRiderPojo.image),
                contentDescription = "",
                modifier = Modifier
                    .size(size.dp)
                    .border(
                        width = 1.dp, color = LightBlue, shape = CircleShape
                    )
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }


        Text(
            text = FavoriteRiderPojo.text,
            style = TextStyle(
                color = Color.Black,
                fontSize = 9.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.axiformablack))
            )
        )
        BackgroundedImageText(
            background = Color(0xFFFCD313),
            textColor = Color.Black,
            text = "${FavoriteRiderPojo.eta} min",
            image = R.drawable.bicycle,
            imageSize = 10
        )
    }
}

data class favoriteRiderPojo(
    val image: Int,
    val text: String,
    val eta: Int,
    val price: Int
)

@Preview(showBackground = true)
@Composable
fun screen() {
    favoriteRiders(route = "", returnItem = {})

}