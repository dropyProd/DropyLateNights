package com.example.dropy.ui.components.riderDash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R

@Composable
fun FlowRowItem(
    flowRowPojo: FlowRowPojo,
    index: Int,
    size: Dp
) {
    Box(
        modifier = Modifier.padding(top = 4.dp)
            .size(size)
            .background(
                brush = Brush.sweepGradient(
                    colors = listOf(
                        Color(0xFF13274F).copy(.9f),
                        Color(0xFF034694).copy(.9f),
                    )
                ),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Image(
            painter = painterResource(id = flowRowPojo.image),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
                .blur(radius = 2.dp)
        )

        val align = if (index.equals(1) || index.equals(3)) {
            Alignment.TopStart
        } else if (index.equals(0)) {
            Alignment.BottomStart
        } else {
            Alignment.TopEnd
        }

        Column(
            modifier = Modifier
                .wrapContentSize(align = align)
                .padding(top = 34.dp, start = 13.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            if (index == 0) {
                Text(
                    text = flowRowPojo.textOne,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            } else if (index == 2) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.fav),
                        contentDescription = "",
                        modifier = Modifier.size(25.dp)
                    )

                    Text(
                        text = flowRowPojo.textOne,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Text(
                    text = flowRowPojo.textTwo,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 7.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            } else {
                Text(
                    text = flowRowPojo.textOne,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Text(
                    text = flowRowPojo.textTwo,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 7.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

data class FlowRowPojo(
    val image: Int,
    val textOne: String,
    val textTwo: String,
    val imageTwo: Int = R.drawable.fav,
)