package com.example.dropy.ui.components.parcel

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.dropy.R
import com.example.dropy.ui.components.order.BackgroundedImageText
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.screens.parcel.rate
import com.example.dropy.ui.theme.LightBlue

@Composable
fun headerInfo(FavoriteRiderPojo: favoriteRiderPojo) {

    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .fillMaxWidth()
    ) {

        val (select, body) = createRefs()

        Selected(modifier = Modifier
            .constrainAs(select) {
                top.linkTo(parent.top, -6.dp)
                start.linkTo(parent.start, 20.dp)
            }
            .zIndex(.4f))

        Row(
            modifier = Modifier
                .zIndex(.3f)
                .constrainAs(body) {
                    top.linkTo(parent.top)
                }
                .background(color = Color.Transparent)
                .fillMaxWidth()
                .wrapContentHeight()
                .border(width = 1.dp, color = Color(0xFFA8A1FF), shape = RoundedCornerShape(14.dp))
                .padding(horizontal = 15.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Image(
                painter = painterResource(id = R.drawable.imgone),
                contentDescription = "",
                modifier = Modifier
                    .size(96.dp)
                    .border(
                        width = 1.dp, color = LightBlue, shape = CircleShape
                    )
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.wrapContentWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = FavoriteRiderPojo.text,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.axiformablack))
                    )
                )

                Text(
                    text = "BLUE YAMAHA 567 SM",
                    style = TextStyle(
                        color = Color(0xFF584AFF),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.axiformablack))
                    )
                )

                Text(
                    text = "213 RIDES",
                    style = TextStyle(
                        color = Color(0xFF584AFF),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.axiformablack))
                    )
                )

                Text(
                    text = "15,466Km travelled",
                    style = TextStyle(
                        color = Color(0xFF74728A),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.axiformasemibolditalic))
                    )
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(7.dp),
                horizontalAlignment = Alignment.End
            ) {

                rate()
                BackgroundedImageText(
                    background = Color(0xFFFCD313),
                    textColor = Color.Black,
                    text = FavoriteRiderPojo.eta.toString() + " min",
                    image = R.drawable.bicycle,
                    modifier = Modifier
                        .wrapContentSize()
                )
                BackgroundedText(
                    background = Color(0xFF584AFF),
                    textColor = Color.White,
                    text = "KDA678T",
                    textSize = 9,
                    vertical = 4
                )
            }

        }
    }

}

@Composable
fun Selected(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .border(width = 1.dp, color = Color(0xFFA8A1FF), shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 10.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier
                .size(7.dp)
                .background(color = Color(0xFF25E900), shape = CircleShape)
                .border(width = 1.dp, color = Color(0xFF707070), shape = CircleShape)
        ) {

        }

        Text(
            text = "SELECTED",
            style = TextStyle(
                color = Color.Black,
                fontSize = 8.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.axiformaextrabold))
            )
        )
    }
}

@Preview
@Composable
fun demo() {
    headerInfo(
        favoriteRiderPojo(
            image = R.drawable.imgone,
            text = "TIMOTHY KARANJA NYORO",
            eta = 7,
            price = 432
        )
    )
}