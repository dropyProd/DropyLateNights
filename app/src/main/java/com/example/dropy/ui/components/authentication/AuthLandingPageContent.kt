package com.example.dropy.ui.components.authentication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.theme.DropyTheme


@Composable
fun AuthLandingPageContent(
    onCreateAccountClicked: () -> Unit,
    onLoginClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(154.dp)
                .height(88.dp),
            contentAlignment = Alignment.Center

        ) {
            Image(
                painter = painterResource(id = R.drawable.logonewblack),
                contentDescription = "Dropy logo",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.4f),
            contentAlignment = Alignment.TopStart

        ) {
            Image(
                painter = painterResource(id = R.drawable.hi),
                contentDescription = "Location icon",
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Column(
            modifier = Modifier
                .padding(top = 43.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                text = "WELCOME TO DROPY ",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.axiformaextrabold)),
                letterSpacing = (-.77).sp
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(

                text = " SUPERAPP ",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.axiformaextrabold)),
                letterSpacing = (-.77).sp
            )
            // Spacer(modifier = Modifier.size(10.dp))
            Text(
                textAlign = TextAlign.Center,
                letterSpacing = (-0.77).sp,
                text = "feel at home.",
                lineHeight = 32.sp,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.amsterdamo)),
                modifier = Modifier
                    .padding(top = 43.dp),
                fontWeight = FontWeight.Thin
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { onCreateAccountClicked() },
                modifier = Modifier
                    .sizeIn(minHeight = 48.dp, maxHeight = 48.dp)
                    .fillMaxWidth(0.7f)
                    .clip(RoundedCornerShape(50))
                    .background(Color.Transparent)
                    .border(width = 2.dp, color = Color.Black, RoundedCornerShape(50)),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Text(
                    text = "CREATE ACCOUNT",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 10.sp,
                    letterSpacing = (-1).sp,
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.axiformaextrabold))
                )

            }
            Spacer(modifier = Modifier.size(24.dp))
            Button(
                onClick = { onLoginClicked() },
                modifier = Modifier
                    .sizeIn(minHeight = 48.dp, maxHeight = 48.dp)
                    .fillMaxWidth(0.7f)
                    .clip(RoundedCornerShape(50)),
                colors = ButtonDefaults.buttonColors(Color.Black),
            ) {
                Text(
                    text = "LOGIN",
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    fontSize = 10.sp,
                    letterSpacing = (-1).sp,
                    fontFamily = FontFamily(Font(R.font.axiformaextrabold))

                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthLandingContentPreview() {
    DropyTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            AuthLandingPageContent(
                onCreateAccountClicked = {},
                onLoginClicked = {}
            )
        }
    }
}