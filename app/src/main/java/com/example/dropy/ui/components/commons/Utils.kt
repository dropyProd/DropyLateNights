package com.example.dropy.ui.components.commons

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R

@Composable
fun Liked(liked: Boolean, isliked: (Boolean) -> Unit) {
    //  val isLiked = remember { mutableStateOf(liked) }
    Log.d("eddewed", "ddada: ${liked}")

    IconButton(
        onClick = {
            //   isLiked.value = !isLiked.value
            isliked(liked)
        },
        modifier = Modifier
            .size(17.dp)
    ) {
        if (liked) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "liked",
                tint = Color.Red
            )
        } else {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "not liked",
                tint = Color.White
            )
        }
    }
}

@Composable
fun Likes(liked: Boolean, likes: Int) {

    val likeString: String = if (likes >= 1000) {
        "${likes / 1000.toFloat()}K"
    } else {
        "$likes"
    }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Liked(liked = liked, isliked = {})
        Text(
            text = likeString,
            fontSize = 9.sp,
            fontFamily = FontFamily(Font(R.font.axiformaheavy)),
            fontWeight = FontWeight.Black
        )
    }
}

@Composable
fun DistanceWithIcon(distanceInMeters: Int) {
    val distanceString: String = if (distanceInMeters >= 1000) {
        "${distanceInMeters / 1000.toFloat()}KM"
    } else {
        "${distanceInMeters}M"
    }

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(Color.Black),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.LocationOn,
            contentDescription = "location",
            modifier = Modifier
                .size(16.dp),
            tint = Color.White
        )
        SimpleText(
            textSize = 9,
            text = distanceString,
            isUppercase = true,
            textAlign = TextAlign.Center,
            isBold = true,
            font = Font(R.font.axiformabold),
            textColor = Color.White
        )
    }
}

@Composable
fun Rating(rating: Double) {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Image(
            imageVector = Icons.Filled.StarRate,
            contentDescription = "rating star",
            colorFilter = ColorFilter.tint(Color(253, 211, 19)),
            modifier = Modifier.size(32.dp)
        )
        Text(
            text = rating.toString(),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.axiformabold))
        )
    }
}

@Composable
fun VerticalRating(rating: Double) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Filled.StarRate,
            contentDescription = "rating star",
            colorFilter = ColorFilter.tint(Color(253, 211, 19)),
            modifier = Modifier.size(32.dp)
        )
        Text(
            text = rating.toString(),
            fontSize = 15.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(Font(R.font.axiformaextrabold))
        )
    }
}

@Composable
fun Time(timeInMin: String) {

    Text(
        text = timeInMin.uppercase(),
        fontWeight = FontWeight.Light,
        fontSize = 9.sp,
        fontFamily = FontFamily(Font(R.font.axiformalight))
    )
}

@Composable
fun TimeWithIcon(
    timeInMin: Int,
    fontWeight: FontWeight = FontWeight.Light,
    font: Font = Font(R.font.axiformalight)
) {

    Log.d("tftyf", "TimeWithIcon: $timeInMin")

    val timeString: String = if (timeInMin >= 3600) {
        val hrs: Int = timeInMin / 3600
        val min: Int = timeInMin % 3600 / 60
       // val seconds: Int = input % 3600 % 60
       // val hrs = timeInMin / 60
      //  val min = timeInMin % 60
        "$hrs HRS $min MIN"
    } else {
        val min = timeInMin / 60
        "$min MIN"
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(Color(253, 211, 19))
    ) {
        Row(
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Timer,
                contentDescription = "time",
                modifier = Modifier
                    .size(10.dp)
            )
            Text(
                text = timeString,
                fontWeight = fontWeight,
                fontSize = 8.sp,
                modifier = Modifier
                    .padding(start = 4.dp),
                fontFamily = FontFamily(font)
            )
        }

    }

}



@Composable
fun Distance(distanceInMeters: Int) {
    val distanceString: String = if (distanceInMeters >= 1000) {
        "${distanceInMeters / 1000.toFloat()}KM"
    } else {
        "${distanceInMeters}M"
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(Color.Black)
            .sizeIn(minWidth = 48.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = distanceString,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(start = 6.dp, end = 6.dp, top = 2.dp, bottom = 2.dp),
        )
    }
}