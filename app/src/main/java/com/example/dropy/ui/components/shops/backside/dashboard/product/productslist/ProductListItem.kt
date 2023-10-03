package com.example.dropy.ui.components.shops.backside.dashboard.product.productslist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.dropy.R
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue
import com.example.dropy.ui.theme.LightContainerBackground

@Composable
fun ProductListItem(
    productImageUrl: String,
    productName: String,
    numberOfUnits: Int,
    price: Int,
    alwaysAvailable: Boolean,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    ConstraintLayout() {

        val (body, line, stock) = createRefs()


        Row(
            modifier = Modifier
                .constrainAs(body) {
                    top.linkTo(parent.top)
                }
                .padding(bottom = if (numberOfUnits <= 0) 18.dp else  8.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(if (numberOfUnits <= 0) backgroundColor.copy(.2f) else backgroundColor)
                .border(
                    1.dp,
                    color =
                    when {
                        alwaysAvailable -> {
                            Color.LightGray.copy(.4f)
                        }
                        numberOfUnits <= 0 -> {
                            Color(170, 0, 0).copy(.3f)
                        }
                        else -> {
                            Color.LightGray.copy(.4f)
                        }
                    },
                    RoundedCornerShape(8.dp)
                )
                .clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            /*Box(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(0.2f)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
         *//*           .border(
                    2.dp,
                    color =
                    when {
                        alwaysAvailable -> {
                            Color.LightGray
                        }
                        numberOfUnits <= 0 -> {
                            Color(170, 0, 0)
                        }
                        else -> {
                            Color.LightGray
                        }
                    },
                    RoundedCornerShape(8.dp)
                )*//*
                .background(Color.Transparent)
        ) {
            LoadImage(imageUrl = productImageUrl, contentScale = ContentScale.Fit)
        }*/
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .size(64.dp)
                    .clip(CircleShape)
                    .alpha(if (numberOfUnits <= 0) .2f else 1f)
            ) {
                LoadImage(imageUrl = productImageUrl, contentScale = ContentScale.FillBounds)
            }
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                SimpleText(
                    textSize = 12,
                    text = productName,
                    isBold = true,
                    font = Font(R.font.axiformaextrabold),
                    fontWeight = FontWeight.ExtraBold,
                    textColor = if (numberOfUnits <= 0) Color.Black.copy(.2f) else Color.Black
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 25.dp)
                ) {

                    if (alwaysAvailable) {
                        SimpleText(
                            textSize = 12,
                            text = "Always in stock",
                            padding = 4,
                            textColor = Color.DarkGray,
                            isBold = true
                        )
                    } else {
                        if (numberOfUnits <= 0) {
                            /*   BackgroundedText(
                                   background = Color.Red,
                                   textColor = Color.Black,
                                   text = "Out stock",
                                   vertical = 3,
                                   modifier = Modifier.offset(x = 240.dp).rotate(-70f)
                               )*/
                            /*    SimpleText(
                                    textSize = 12,
                                    text = "Out stock",
                                    padding = 4,
                                    textColor = Color.DarkGray,
                                    isBold = true,
                                    font = Font(R.font.axiformaextrabold),
                                    fontWeight = FontWeight.ExtraBold
                                )*/
                        } else {
                            Box(
                                modifier = Modifier
                                    .sizeIn(minWidth = 32.dp)
                                    .clip(CircleShape)
                                    .background(LightBlue),
                                contentAlignment = Alignment.Center
                            ) {
                                SimpleText(
                                    textSize = 12,
                                    text = numberOfUnits.toString(),
                                    fontWeight = FontWeight.Black,
                                    padding = 4,
                                    textColor = Color.White,
                                    font = Font(R.font.axiformaextrabold),
                                )
                            }
                            SimpleText(
                                textSize = 12,
                                text = "In stock",
                                padding = 4,
                                textColor = Color.DarkGray,
                                /*    isBold = true,*/
                                font = Font(R.font.axiformaextrabold),
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    }
                }

            }
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .offset((-10).dp),
            ) {
                StyledText(
                    backgroundColor = DropyYellow,
                    textSize = 15,
                    text = "${price}/-",
                    isUppercase = true,
                    isBold = true,
                    textColor = if (numberOfUnits <= 0) Color.Black.copy(.2f) else Color.Black,
                    fontFamily = R.font.axiformaheavy,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }

        if (numberOfUnits <= 0) {

            Row(modifier = Modifier
                .constrainAs(line) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxWidth()
                .height(2.dp)
                .background(
                    color = Color(170, 0, 0).copy(.3f)
                )) {

            }

            BackgroundedText(
                background = Color(170, 0, 0).copy(.3f),
                textColor = Color.White,
                text = ("Out stock").uppercase(),
                vertical = 3,
                horizontal = 5,
                textSize = 8,
                modifier = Modifier
                    .constrainAs(stock) {
                        bottom.linkTo(parent.bottom, 20.dp)
                        end.linkTo(parent.end, -20.dp)
                    }
                    .rotate(-40f)
            )
        }

    }

}


@Preview(showBackground = true)
@Composable
fun ProductListItemPreview() {
    Column(Modifier.fillMaxSize()) {
        ProductListItem(
            productImageUrl = "",
            productName = "Some other very long product name",
            numberOfUnits = 0,
            price = 4321,
            alwaysAvailable = false,
            backgroundColor = LightContainerBackground,
            onClick = {}
        )
    }
}