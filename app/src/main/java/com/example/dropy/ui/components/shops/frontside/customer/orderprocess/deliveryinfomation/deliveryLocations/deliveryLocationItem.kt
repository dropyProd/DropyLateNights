package com.example.dropy.ui.components.shops.frontside.customer.orderprocess.deliveryinfomation

import com.example.dropy.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.EditLocation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.ui.components.commons.SimpleText


@Composable
fun deliveryLocationItem(
    text: String,
    clicked: () -> Unit,
    choose: () -> Unit,
    selected: Boolean = false,
    selectedIndex: Int = 0,
    index: Int = 0,
) {
    Box(
        modifier = Modifier
            .clickable {
                if (text.equals("OTHER")) {
                    clicked()
                } else {
                    choose()
                }
            }
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .background(
                    color = if (selectedIndex.equals(index)) {
                        Color(88, 74, 255, 128)
                    } else Color.Transparent, shape = RoundedCornerShape(28.dp)
                )
                .border(width = 1.dp, color = Color(0xFF584AFF), shape = RoundedCornerShape(28.dp))
                .padding(horizontal = 10.dp, vertical = 7.dp)
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                fontSize = 8.sp,
                fontFamily = FontFamily(Font(R.font.axiformabold))
            )
        }

        Row(
            modifier = Modifier
                .offset(4.dp, (-5).dp)
                .clickable { clicked() }
                .size(12.dp)
                .clip(CircleShape)
                .background(Color(253, 211, 19))
                .align(Alignment.TopEnd)

        ) {
            Icon(
                imageVector = Icons.Outlined.EditLocation,
                contentDescription = "edit location",
                modifier = Modifier
                    .padding(2.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun demo() {
    deliveryLocationItem(text = "", clicked = { /*TODO*/ }, choose = {})
}
