package com.example.dropy.ui.components.payment

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
import com.example.dropy.ui.components.parcel.Selected
import com.example.dropy.ui.screens.dropymainmodel.DropyMainPojo
import com.example.dropy.ui.screens.dropymainmodel.dropyCategoryItem
import com.example.dropy.ui.screens.parcel.rate
import com.example.dropy.ui.theme.LightBlue


@Composable
fun PaymentDetails() {
    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .fillMaxWidth()
    ) {

        val (select, body, price) = createRefs()


        Selected(modifier = Modifier
            .constrainAs(select) {
                top.linkTo(parent.top, -6.dp)
                start.linkTo(parent.start, 20.dp)
            }
            .zIndex(.4f))

        PriceItem(modifier = Modifier.constrainAs(price){
            bottom.linkTo(parent.bottom, 20.dp)
            end.linkTo(parent.end, -22.dp)
        }.zIndex(.5f))

        Column(
            modifier = Modifier
                .zIndex(.3f)
                .constrainAs(body) {
                    top.linkTo(parent.top)
                }
                .background(color = Color.White)
                .fillMaxWidth()
                .wrapContentHeight()
                .border(width = 1.dp, color = Color(0xFFA8A1FF), shape = RoundedCornerShape(14.dp))
                .padding(horizontal = 15.dp, vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(17.dp)
        ) {

            parceltype()

            underline()

            deliveryDetails()

            underline(size = .5f)

            costDetails()

            Text(
                text = "TOTAL",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.axiformablack))
                ),
                modifier = Modifier
                    .padding(top = 30.dp)
                    .align(alignment = Alignment.Start)
            )

        }
    }
}


@Composable
fun PriceItem(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .width(107.dp)
            .height(43.dp)
            .background(
                Color.Black,
                shape = RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp)
            )
            .padding(horizontal = 14.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "921/-",
            style = TextStyle(
                color = Color.White,
                fontSize = 19.sp,
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily(Font(R.font.axiformablack))
            )
        )
    }
}

@Composable
fun costDetails() {
    Column(
        modifier = Modifier
            .background(color = Color.Transparent)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text(
            text = "COST",
            style = TextStyle(
                color = Color.Black,
                fontSize = 9.sp,
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily(Font(R.font.axiformablack))
            )
        )

        textItem(text1 = "INSURANCE", text2 = "55/-")
        textItem(text1 = "DELIVERY COST", text2 = "887/-")
    }
}

@Composable
fun deliveryDetails() {
    Column(
        modifier = Modifier
            .background(color = Color.Transparent)
            .fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        textItem(text1 = "DISTANCE", text2 = "79 KILOMETERS")
        deliveryTextItem()
        textItem(text1 = "VEHICLE DETAILS", text2 = "YAMAHA 567SM")
        roundedtextItem()

    }
}

@Composable
fun deliveryTextItem() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "PLATE NUMBER",
            style = TextStyle(
                color = Color.Black,
                fontSize = 9.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.axiformablack))
            )
        )


        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackgroundedText(
                background = Color(0xFFFCD313),
                textColor = Color.Black,
                text = "RAYMOND KASTEMIL",
                vertical = 5
            )

            Image(
                painter = painterResource(id = R.drawable.imgthre),
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .border(
                        width = 1.dp, color = LightBlue, shape = CircleShape
                    )
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun roundedtextItem() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "PLATE NUMBER",
            style = TextStyle(
                color = Color.Black,
                fontSize = 9.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.axiformablack))
            )
        )


        BackgroundedText(
            background = Color.Black,
            textColor = Color.White,
            text = "KDA678T",
            vertical = 3
        )
    }
}

@Composable
fun textItem(text1: String, text2: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text1,
            style = TextStyle(
                color = Color.Black,
                fontSize = 9.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.axiformablack))
            )
        )


        Text(
            text = text2,
            style = TextStyle(
                color = Color(0xFF74728A),
                fontSize = 9.sp,
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily(Font(R.font.axiformablack))
            )
        )
    }
}


@Composable
fun parcelDetails() {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "PARCEL DETAILS",
            style = TextStyle(
                color = Color.Black,
                fontSize = 9.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.axiformablack))
            )
        )

        destinationItem(
            type = "FROM",
            name = "MAXWEL OKOTH",
            location = "Westlands, Kivulini Lane, RD26"
        )
        destinationItem(
            type = "TO",
            name = "CLAUDIUS MAGJORI",
            location = "Kisumu Dala, Moi Avenue Street",
            modifier = Modifier.padding(top = 17.dp)
        )

        underline()

    }

}


@Composable
fun underline(size: Float = 1f) {
    Row(
        modifier = Modifier
            .background(color = Color(0xFFA8A1FF))
            .fillMaxWidth(size)
            .height(1.dp)
    ) {

    }
}

@Composable
fun destinationItem(
    type: String,
    name: String,
    location: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = type,
            style = TextStyle(
                color = Color.Black,
                fontSize = 9.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.axiformablack))
            )
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(3.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = name,
                style = TextStyle(
                    color = Color(0xFF74728A),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily(Font(R.font.axiformablack))
                )
            )

            Text(
                text = location,
                style = TextStyle(
                    color = Color.Black.copy(.72f),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily(Font(R.font.axiformamedium))
                )
            )
        }

    }
}

@Composable
fun parceltype() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {

        dropyCategoryItem(
            dropyMainPojo = DropyMainPojo(
                image = R.drawable.parcel,
                text = "SEND PACKAGE",
                color = Color(0xFFFCD313),
                colorOne = Color(0xFFFEF8DB)
            ),
            showtext = false,
            width = 102,
            height = 96,
            click = {

            }
        )

        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            typeItem(text1 = "PARCEL TYPE", text2 = "LARGE 3KG - 10KG PACKAGE")
            typeItem(text1 = "DELIVERY MODE", text2 = "EXPRESS RIDER")
        }


    }
}

@Composable
fun typeItem(text1: String, text2: String) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            text = text1,
            style = TextStyle(
                color = Color.Black,
                fontSize = 9.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.axiformablack))
            )
        )

        Text(
            text = text2,
            style = TextStyle(
                color = Color(0xFF74728A),
                fontSize = 9.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.axiformablack))
            )
        )
    }
}

@Preview
@Composable
fun demo() {
    PaymentDetails()
}