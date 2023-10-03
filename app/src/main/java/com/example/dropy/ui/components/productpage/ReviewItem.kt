package com.example.dropy.ui.components.productpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.example.dropy.R
import com.example.dropy.ui.components.commons.LoadImage

@Composable
fun ReviewItem(reviewItemPojo: ReviewItemPojo, index: Int) {

    Row(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        LoadImage(
            imageUrl = reviewItemPojo.image, modifier = Modifier
                .size(34.dp)
                .clip(
                    CircleShape
                )
                .border(width = 1.dp, color = Color(0xFF707070), shape = CircleShape)
        )
  /*      Image(
            painter = painterResource(id = reviewItemPojo.image),
            contentDescription = "",
            modifier = Modifier
                .size(34.dp)
                .clip(
                    CircleShape
                )
                .border(width = 1.dp, color = Color(0xFF707070), shape = CircleShape),
            contentScale = ContentScale.Crop
        )*/

        Text(
            text = reviewItemPojo.text,
            style = TextStyle(
                color = Color.Black,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily(
                    Font(R.font.axiformamedium)
                )
            ),
            modifier = Modifier.width(215.dp),
            letterSpacing = (-.48).sp,
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.fav),
                contentDescription = "",
                modifier = Modifier.size(13.dp)
            )

            Text(
                text = "4.${index}",
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformasemibold)
                ),
                letterSpacing = (-1).sp,
            )

        }
    }
}


data class ReviewItemPojo(
    val image: String,
    val text: String
)

@Preview(showBackground = true)
@Composable
fun demo() {
    ReviewItem(
        reviewItemPojo = ReviewItemPojo(
            image = "",
            text = "The food was very sweet and yummy. My baby and i loved it alot"
        ), index = 1
    )
}