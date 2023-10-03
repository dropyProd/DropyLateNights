package com.example.dropy.ui.components.riderDash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dropy.R
import com.example.dropy.ui.app.AppDestinations

@Composable
fun BlurredImageItem(image: Int, textOne: String, textTwo: String, navController: NavController) {
    Box(
        modifier = Modifier
            .clickable {
                navController.navigate(AppDestinations.BALANCE)
            }
            .width(166.dp)
            .height(105.dp)
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
            painter = painterResource(id = image),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
                .blur(radius = 20.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 34.dp, start = 13.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = textOne,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = textTwo,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}