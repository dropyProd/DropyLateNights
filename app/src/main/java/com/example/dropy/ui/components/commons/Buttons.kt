package com.example.dropy.ui.components.commons

import androidx.compose.foundation.border
import com.example.dropy.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.ui.theme.DropyTheme
import com.example.dropy.ui.theme.DropyYellow

@Composable
fun TotallyRoundedButton(
    icon: ImageVector? = null,
    buttonText:String,
    textFontSize:Int = 12,
    textColor: Color = Color.Black,
    backgroundColor: Color,
    widthFraction: Double? = null,
    action:()->Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = {action()},
        modifier = modifier
            .padding(8.dp)
            .height(48.dp)
            .clip(RoundedCornerShape(50))
            .fillMaxWidth(widthFraction?.toFloat() ?: 1f)
        ,
        colors = ButtonDefaults.buttonColors(backgroundColor)
    ) {
        if (icon!=null){
            Icon(imageVector = icon, contentDescription = "button icon")
        }
        Text(
            text = buttonText.uppercase(),
            fontSize = textFontSize.sp,
            fontWeight = FontWeight.W900,
            color = textColor,
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Center,
            letterSpacing = (-0.5).sp,
            fontFamily = FontFamily(Font(R.font.axiformaextrabold))
        )
    }
}

@Composable
fun TotallyRoundedButtonBorder(
    icon: ImageVector? = null,
    buttonText:String,
    textFontSize:Int = 12,
    textColor: Color = Color.Black,
    borderColor: Color = Color.Black,
    borderwidth:Int = 2,
    backgroundColor: Color,
    widthFraction: Double? = null,
    action:()->Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = {action()},
        modifier = modifier
            .padding(8.dp)
            .height(48.dp)
            .clip(RoundedCornerShape(50))
            .fillMaxWidth(widthFraction?.toFloat() ?: 1f)
            .border(width = borderwidth.dp, color = borderColor, shape = RoundedCornerShape(50))
        ,
        colors = ButtonDefaults.buttonColors(backgroundColor)
    ) {
        if (icon!=null){
            Icon(imageVector = icon, contentDescription = "button icon")
        }
        Text(
            text = buttonText.uppercase(),
            fontSize = textFontSize.sp,
            fontWeight = FontWeight.W900,
            color = textColor,
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Center,
            letterSpacing = (-0.5).sp,
            fontFamily = FontFamily(Font(R.font.axiformaextrabold))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonsPreview(){
    DropyTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TotallyRoundedButton(
                buttonText = "text",
                textFontSize = 16,
                textColor = Color.Black,
                backgroundColor = DropyYellow,
                action = {}
            )
        }
    }
}