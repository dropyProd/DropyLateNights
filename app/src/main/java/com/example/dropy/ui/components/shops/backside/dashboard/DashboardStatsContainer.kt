package com.example.dropy.ui.components.shops.backside.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.theme.DropyYellow


@Composable
fun DashboardStatsContainer(
    backgroundColor: Color,
    titleTextSize: Int,
    titleText: String,
    titleTextColor: Color,
    titleIsUppercase: Boolean,
    titleIsBold: Boolean = true,
    titleIsExtraBold: Boolean = false,
    titlePadding: Int? = 0,
    valueTextSize: Int,
    valueText: String,
    valueTextColor: Color,
    valueIsUppercase: Boolean,
    valueIsBold: Boolean = false,
    valueIsExtraBold: Boolean = true,
    valuePadding: Int? = 0,
    titleStarts: Boolean,
    alignStart: Boolean,
    onClick: () -> Unit,
    image: Int? = 0
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Transparent, shape = RoundedCornerShape(8.dp))
            .clickable { onClick() },



    ) {


        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(80.dp)
                .clip(RoundedCornerShape(8.dp)).padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = if (alignStart) {
                Alignment.Start
            } else {
                Alignment.End
            }
        ) {
            if (titleStarts) {
                SimpleText(
                    textSize = titleTextSize,
                    text = titleText,
                    textColor = titleTextColor,
                    isUppercase = titleIsUppercase,
                    isBold = titleIsBold,
                    isExtraBold = titleIsExtraBold,
                    padding = titlePadding,
                    font = Font(R.font.axiformasemibold),
                    fontWeight = FontWeight.SemiBold
                )
                SimpleText(
                    textSize = valueTextSize,
                    text = valueText,
                    textColor = valueTextColor,
                    isUppercase = valueIsUppercase,
                    isBold = valueIsBold,
                    isExtraBold = valueIsExtraBold,
                    padding = valuePadding,
                    font = Font(R.font.axiformaextrabold),
                    fontWeight = FontWeight.ExtraBold
                )
            } else {
                SimpleText(
                    textSize = valueTextSize,
                    text = valueText,
                    textColor = valueTextColor,
                    isUppercase = valueIsUppercase,
                    isBold = valueIsBold,
                    isExtraBold = valueIsExtraBold,
                    padding = valuePadding,
                    font = Font(R.font.axiformaextrabold),
                    fontWeight = FontWeight.ExtraBold
                )
                SimpleText(
                    textSize = titleTextSize,
                    text = titleText,
                    textColor = titleTextColor,
                    isUppercase = titleIsUppercase,
                    isBold = titleIsBold,
                    isExtraBold = titleIsExtraBold,
                    padding = titlePadding,
                    font = Font(R.font.axiformasemibold),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardStatsContainerPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        DashboardStatsContainer(
            titleTextSize = 15,
            titleText = "incoming orders",
            titleTextColor = Color.White,
            titleIsUppercase = true,
            titleIsBold = false,
            titleIsExtraBold = false,
            titlePadding = null,
            valueTextSize = 22,
            valueText = "3",
            valueTextColor = Color.White,
            valueIsUppercase = true,
            valueIsBold = false,
            valueIsExtraBold = true,
            valuePadding = null,
            titleStarts = false,
            alignStart = false,
            backgroundColor = DropyYellow,
            onClick = {}
        )
    }
}