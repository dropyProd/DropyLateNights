package com.example.dropy.ui.components.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.ui.theme.DropyTheme
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.R

@Composable
fun SimpleText(
    textSize: Int = 12,
    text: String,
    textColor: Color = Color.Black,
    isUppercase: Boolean = false,
    isBold: Boolean = false,
    isExtraBold: Boolean = false,
    padding: Int? = null,
    verticalPadding: Int = 0,
    horizontalPadding: Int = 0,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign = TextAlign.Start,
    font: Font? = null,
    fontStyle: FontStyle = FontStyle.Normal,
    modifier: Modifier = Modifier
) {

    Text(
        text = if (isUppercase) {
            text.uppercase()
        } else {
            text
        },
        color = textColor,
        fontSize = textSize.sp,
        fontWeight = fontWeight
            ?: when {
                isBold -> {
                    FontWeight.Bold
                }
                isExtraBold -> {
                    FontWeight.ExtraBold
                }
                else -> {
                    FontWeight.Normal
                }
            },
        fontFamily = FontFamily(font ?: Font(R.font.axiformaregular)),
        modifier = modifier
            .padding(padding?.dp ?: 0.dp)
            .padding(vertical = verticalPadding.dp)
            .padding(horizontal = horizontalPadding.dp),
        letterSpacing = (-0.58).sp,
        textAlign = textAlign,
        fontStyle = fontStyle

    )
}

@Composable
fun StyledText(
    textColor: Color = Color.Black,
    backgroundColor: Color,
    borderColor: Color? = Color.Transparent,
    textSize: Int = 13,
    text: String,
    isUppercase: Boolean = false,
    isBold: Boolean = false,
    isExtraBold: Boolean = false,
    fontWeight: FontWeight? = null,
    fontFamily: Int = R.font.axiformaregular
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(50))
            .background(color = backgroundColor)
            .border(2.dp, color = borderColor ?: Color.Transparent, shape = RoundedCornerShape(50))
    ) {
        Text(
            text = if (isUppercase) {
                text.uppercase()
            } else {
                text
            },
            color = textColor,
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
            fontSize = textSize.sp,
            fontWeight = fontWeight
                ?: when {
                    isBold -> {
                        FontWeight.Bold
                    }
                    isExtraBold -> {
                        FontWeight.ExtraBold
                    }
                    else -> {
                        FontWeight.Normal
                    }
                },
            fontFamily = FontFamily(Font(fontFamily))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextsPreview() {
    DropyTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            SimpleText(textSize = 16, text = "text")
            StyledText(backgroundColor = DropyYellow, textSize = 16, text = "text")


        }
    }
}