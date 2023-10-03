package com.example.dropy.ui.components.shops.frontside.singleshop.shophome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.dropy.ui.components.commons.*
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.PhoneGreen
import java.util.*
import com.example.dropy.R
import com.example.dropy.ui.components.order.BackgroundedText
import com.example.dropy.ui.screens.DonateImage
import kotlin.random.Random

@Composable
fun ShopHeader(
    shopName: String,
    shopDescription: String = "",
    shopCoverPhotoUrl: String,
    shopDistance: String = "",
    shopLocation: String,
    numberOfFollowers: Int,
    numberOfOrders: Int,
    bottomSheet: Boolean = false,
    onCallShop: () -> Unit,
    onEmailShop: () -> Unit,
    onGoToShop: (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
        // .fillMaxHeight(.54f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2 / 1f)
        ) {
            /*   LoadImage(imageUrl = shopCoverPhotoUrl)*/
            LoadImage(
                imageUrl = shopCoverPhotoUrl, modifier = Modifier
                    .fillMaxSize()
            )
            /*   Image(
                   painter = painterResource(id = R.drawable.imgtwo),
                   contentDescription = "",
                   modifier = Modifier.fillMaxWidth(),
                   contentScale = ContentScale.Crop
               )*/



            Column(
                verticalArrangement = Arrangement.spacedBy(13.dp), modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {

                if (bottomSheet) {
                    ConstraintLayout(modifier = Modifier.padding(start = 12.dp)) {

                        val (image, txt) = createRefs()


                        Image(
                            painter = painterResource(id = R.drawable.fav),
                            contentDescription = "",
                            modifier = Modifier
                                .size(28.dp)
                                .constrainAs(image) {
                                    top.linkTo(parent.top)
                                }
                        )

                        androidx.compose.material3.Text(
                            text = "4.7",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 7.sp,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                            ),
                            modifier = Modifier.constrainAs(txt) {
                                top.linkTo(parent.top, 12.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                        )
                    }

                } else {

                    Row(
                        Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        /*BackgroundedText(
                            background = Color.Black,
                            textColor = Color.White,
                            text = shopDistance
                        )*/

                        /*ConstraintLayout(modifier = Modifier.padding(start = 12.dp)) {

                            val (image, txt) = createRefs()


                            Image(
                                painter = painterResource(id = R.drawable.fav),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(28.dp)
                                    .constrainAs(image) {
                                        top.linkTo(parent.top)
                                    }
                            )

                            androidx.compose.material3.Text(
                                text = "4.7",
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 7.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                                ),
                                modifier = Modifier.constrainAs(txt) {
                                    top.linkTo(parent.top, 12.dp)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                            )
                        }*/

                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(33.dp))
                                .background(Color.White)
                                .padding(horizontal = 10.dp, vertical = 3.dp),
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.fav),
                                contentDescription = "",
                                modifier = Modifier.size(11.dp),

                                )
                            Text(
                                text = "4.6",
                                fontSize = 9.sp,
                                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                                color = Color.Black
                            )
                        }


                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(33.dp))
                                .background(Color.White)
                                .padding(horizontal = 10.dp, vertical = 3.dp),
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.heart),
                                contentDescription = "",
                                modifier = Modifier.size(11.dp),

                                )
                            Text(
                                text = "522 K",
                                fontSize = 9.sp,
                                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                                color = Color.Black
                            )
                        }

                    }
                    /*Row(
                        modifier = Modifier
                            .size(23.dp)
                            .background(color = Color(0xFF1BFC13), shape = CircleShape),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            Icons.Filled.VideoCall,
                            contentDescription = "",
                            modifier = Modifier.size(19.dp),
                            colorFilter = ColorFilter.tint(color = Color.Black)
                        )
                    }*/

                }
            }


//            Box(
//                modifier = Modifier
//                    .align(Alignment.TopStart)
//                    .padding(16.dp)
//            ) {
//                Distance(distanceInMeters = 1234)
//            }
            if (!bottomSheet) {
                /*Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color(255, 255, 255, 179)),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Likes(liked = false, likes = numberOfFollowers)
                    SimpleText(
                        text = "FOLLOWERS",
                        textSize = 9,
                        padding = 4,
                        isUppercase = true,
                        isBold = true,
                        font = Font(R.font.axiformaheavy)
                    )
                }*/
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    ConstraintLayout(
                        modifier = Modifier
                            .padding(start = 2.dp)
                    ) {

                        val (imgOne, imgTwo, imgThr, imgfr, imgfv) = createRefs()


                        DonateImage(modifier = Modifier.constrainAs(imgOne) {
                            start.linkTo(parent.start)
                        }, shopCoverPhotoUrl = shopCoverPhotoUrl, size = 22)

                        DonateImage(modifier = Modifier.constrainAs(imgTwo) {
                            start.linkTo(imgOne.start, 17.dp)
                        }, shopCoverPhotoUrl = shopCoverPhotoUrl, size = 22)

                        DonateImage(modifier = Modifier.constrainAs(imgThr) {
                            start.linkTo(imgTwo.start, 17.dp)
                        }, shopCoverPhotoUrl = shopCoverPhotoUrl, size = 22)
                        DonateImage(modifier = Modifier.constrainAs(imgfr) {
                            start.linkTo(imgThr.start, 17.dp)
                        }, shopCoverPhotoUrl = shopCoverPhotoUrl, size = 22)
                        DonateImage(modifier = Modifier.constrainAs(imgfv) {
                            start.linkTo(imgfr.start, 17.dp)
                        }, shopCoverPhotoUrl = shopCoverPhotoUrl, size = 22)
                    }

                    BackgroundedText(
                        background = Color.Black,
                        textColor = Color.White,
                        text = "${Random.nextInt(200, 980)}+",
                        vertical = 2
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomEnd),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(13.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .clickable { onCallShop() }
                            .wrapContentSize()
                            .clip(RoundedCornerShape(4.dp))
                            .background(PhoneGreen)
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Phone,
                            contentDescription = "phone icon",
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .clickable { onEmailShop() }
                            .wrapContentSize()
                            .clip(RoundedCornerShape(4.dp))
                            .background(Black)
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Icon(
                            imageVector = Icons.Filled.Email,
                            contentDescription = "email icon",
                            modifier = Modifier.size(18.dp),
                            tint = White
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val num = if (bottomSheet) 0 else 12

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = num.dp)
            ) {
                SimpleText(
                    text = shopName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                    isBold = true,
                    textSize = 18
                )


                Row(
                    horizontalArrangement = Arrangement.spacedBy(13.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    StyledText(
                        backgroundColor = Black,
                        textSize = 9,
                        text = shopLocation,
                        textColor = Color.White
                    )

                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(33.dp))
                            .background(Color.Transparent)
                            .border(2.dp, Black, RoundedCornerShape(33.dp))
                            .padding(horizontal = 10.dp, vertical = 3.dp),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Image(
                            Icons.Filled.Send,
                            contentDescription = "",
                            modifier = Modifier.size(11.dp),
                            colorFilter = ColorFilter.tint(color = Black)
                        )
                        Text(
                            text = "${num}KM",
                            fontSize = 9.sp,
                            fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                            color = Color.Black
                        )
                    }
                }

                if (bottomSheet) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        ConstraintLayout(
                            modifier = Modifier
                                .padding(start = 2.dp)
                        ) {

                            val (imgOne, imgTwo, imgThr, imgfr, imgfv) = createRefs()


                            DonateImage(modifier = Modifier.constrainAs(imgOne) {
                                start.linkTo(parent.start)
                            }, shopCoverPhotoUrl = shopCoverPhotoUrl, size = 22)

                            DonateImage(modifier = Modifier.constrainAs(imgTwo) {
                                start.linkTo(imgOne.start, 17.dp)
                            }, shopCoverPhotoUrl = shopCoverPhotoUrl, size = 22)

                            DonateImage(modifier = Modifier.constrainAs(imgThr) {
                                start.linkTo(imgTwo.start, 17.dp)
                            }, shopCoverPhotoUrl = shopCoverPhotoUrl, size = 22)
                            DonateImage(modifier = Modifier.constrainAs(imgfr) {
                                start.linkTo(imgThr.start, 17.dp)
                            }, shopCoverPhotoUrl = shopCoverPhotoUrl, size = 22)
                            DonateImage(modifier = Modifier.constrainAs(imgfv) {
                                start.linkTo(imgfr.start, 17.dp)
                            }, shopCoverPhotoUrl = shopCoverPhotoUrl, size = 22)
                        }

                        BackgroundedText(
                            background = Color(0xFFFCD313),
                            textColor = Color.Black,
                            text = "${Random.nextInt(200, 980)}+",
                            vertical = 2
                        )
                    }
                }
                //images


            }
            Column(
                modifier = Modifier
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                NumberOfOrders(numberOfOrders = numberOfOrders)
                if (bottomSheet) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(253, 211, 19)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "orders".uppercase(),
                            fontSize = 10.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(start = 4.dp, end = 4.dp, top = 1.dp, bottom = 1.dp),
                            fontFamily = FontFamily(Font(R.font.axiformaheavy))
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(33.dp))
                        .background(Color.Black)
                        .padding(horizontal = 10.dp, vertical = 3.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "follow".lowercase(),
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.axiformabold)),
                        color = Color.White
                    )
                    Image(
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = "",
                        modifier = Modifier.size(11.dp),

                        )
                }

            }
        }

        if (bottomSheet) {
            Text(
                text = "Shop Details",
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.axiformaextrabold)),
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Text(
                text = shopDescription,
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.axiformamedium)),
                color = Black,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            )


            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = {
                        if (onGoToShop != null) {
                            onGoToShop()
                        }
                    },
                    modifier = Modifier
                        .padding(top = 44.dp)
                        .width(165.dp)
                        .height(35.dp)
                        .clip(RoundedCornerShape(50)),
                    colors = ButtonDefaults.buttonColors(Black),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "GO TO SHOP",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 10.sp,
                        letterSpacing = (-1).sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.size(24.dp))
                    Icon(
                        Icons.Filled.ArrowRight,
                        contentDescription = "register icon",
                        modifier = Modifier
                            .size(16.dp),
                        tint = Color.White

                    )

                }
            }
        }
    }
}

@Composable
fun NumberOfOrders(numberOfOrders: Int) {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Icon(
            painter = painterResource(id = R.drawable.cartt),
            contentDescription = "",
            tint = Color(88, 74, 255),
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = "$numberOfOrders +",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShopHeaderPreview() {
    Column(Modifier.fillMaxSize()) {
        ShopHeader(
            numberOfFollowers = 2,
            numberOfOrders = 3,
            shopName = "shop name",
            shopCoverPhotoUrl = "",
            shopLocation = "some shop location",
            onCallShop = {},
            onEmailShop = {},
            bottomSheet = false,
            shopDescription = "some shop description"
        )
    }
}