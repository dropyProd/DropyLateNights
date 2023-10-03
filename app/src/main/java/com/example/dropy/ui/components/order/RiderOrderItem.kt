package com.example.dropy.ui.components.order

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.screens.incoming.RiderOrdersPojo


@Composable
fun RiderOrderItem(show: Boolean, riderOrdersPojo: RiderOrdersPojo) {

    val border = if (show) {
        Color(0x66707070)
    } else Color(0xFFA8A1FF)

    val background = if (show) {
        Color(0xFFFFF9DB)
    } else Color(0xFFFFFFFF)

    Row(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .height(120.dp)
            .border(width = 1.dp, color = border, shape = RoundedCornerShape(13.dp))
            .background(color = background, shape = RoundedCornerShape(13.dp))
            .padding(horizontal = 13.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.imgone),
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(width = 1.dp, color = Color(0xFF584AFF), shape = CircleShape),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            Row() {

                Column(
                    modifier = Modifier.fillMaxWidth(.7f),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {

                    Text(
                        text = riderOrdersPojo.name,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )

                    Text(
                        text = riderOrdersPojo.route,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                }

                Text(
                    text = riderOrdersPojo.price,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    BackgroundedText(
                        background = if (riderOrdersPojo.type.equals("RIDE")) Color.Black else Color(
                            0xFF584AFF
                        ),
                        textColor = Color.White,
                        text = riderOrdersPojo.type
                    )

                    Text(
                        text = riderOrdersPojo.distance,
                        style = TextStyle(
                            color = Color(0xFF505050),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )

                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BackgroundedText(
                        background = Color(0xFFFCD313),
                        textColor = Color.Black,
                        text = "ACCEPT"
                    )

                    BackgroundedText(
                        background = Color(0xFFFCD313),
                        textColor = Color.Black,
                        text = "CANCEL"
                    )

                }
            }
        }
    }
}

@Composable
fun BackgroundedText(
    background: Color,
    textColor: Color,
    borderColor: Color = Color.Transparent,
    text: String,
    modifier: Modifier = Modifier,
    textSize: Int = 12,
    horizontal: Int = 10,
    vertical: Int = 12,
    shape: Int = 20,
    font: Font = Font(R.font.axiformaextrabold),
    fontWeight: FontWeight =FontWeight.ExtraBold
) {
    Row(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .background(background, shape = RoundedCornerShape(shape.dp))
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(shape.dp))
            .padding(horizontal = horizontal.dp, vertical = vertical.dp)
         ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = textColor,
                fontSize = textSize.sp,
                fontWeight = fontWeight,
                fontFamily = FontFamily(font)
            )
        )
    }
}

@Composable
fun BackgroundedImageText(
    background: Color,
    textColor: Color,
    text: String,
    image: Int,
    imageSize: Int = 15,
    horizontal: Int = 10,
    vertical: Int = 3,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .background(background, shape = RoundedCornerShape(10.dp))
            .padding(horizontal = horizontal.dp, vertical = vertical.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            modifier = modifier.size(imageSize.dp),
            colorFilter = ColorFilter.tint(color = Color.Black)
        )

        Text(
            text = text,
            style = TextStyle(
                color = textColor,
                fontSize = 8.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.axiformaextrabold))
            )
        )
    }
}