package com.example.dropy.ui.components.commons

import android.graphics.Bitmap
import com.example.dropy.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ProfilePic(profilePicUrl: String? = null, bitmap: ImageBitmap? = null,size: Int = 97, show: Boolean = true, onAddProfileLogo: (()-> Unit)? = null) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        if (profilePicUrl == null || profilePicUrl == "") {
            Image(
                painter = painterResource(id = R.drawable.shop1),
                contentDescription = "profile photo",
                modifier = Modifier
                    .size(size = size.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            if (bitmap != null){
                Image(
                    bitmap = bitmap,
                    contentDescription = "profile photo",
                    modifier = Modifier
                        .size(size = size.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }else {
                LoadImage(
                    imageUrl = profilePicUrl, modifier = Modifier
                        .size(size = size.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }
        if (onAddProfileLogo !=null){
            IconButton(
                onClick = { onAddProfileLogo() },
                modifier = Modifier
                    .size(size.dp)
                    .background(Color(255, 255, 255, 77))
                ,
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "add icon",
                    modifier = Modifier
                        .size(24.dp)
                    ,
                    tint = Color.White
                )
            }
        }

        if (show) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = 9.dp)
                    .background(color = Color.Green, CircleShape)
                    .size(20.dp)
            ) {

            }
        }
    }
}