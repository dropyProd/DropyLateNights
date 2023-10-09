package com.example.dropy.ui.components.onboarding

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.theme.DropyTheme
import com.example.dropy.ui.theme.DropyYellow

@Composable
fun OnBoardingContent(
    onOnBoardingFinished: () -> Unit
) {
    val onBoardingContent = mutableListOf(
        OnBoardingContentDataClass(
            content = { OnBoardingScreenThree() },
            background = Color.White,
            statusBarColor = Color(0xFFF5F5F5)

        ),
        OnBoardingContentDataClass(
            content = { OnboardingScanToPay() },
            background = Color.White,
            statusBarColor = Color(0xFFF5F5F5)
        ),
        OnBoardingContentDataClass(
            content = { OnBoardingScreenOne() },
            background = Color.Transparent,
            statusBarColor = Color(0xFFF5F5F5)
        ),
        OnBoardingContentDataClass(
            content = { OnBoardingScreenTwo() },
            background = Color.White,
            statusBarColor = Color(0xFFF5F5F5)
        ),
        OnBoardingContentDataClass(
            content = { OnBoardingScreenWater() },
            background = Color.White,
            statusBarColor = Color(0xFFF5F5F5)
        ),
        OnBoardingContentDataClass(
            content = { OnBoardingScreenLogistics() },
            background = Color.White,
            statusBarColor = Color(0xFFF5F5F5)
        ),
        OnBoardingContentDataClass(
            content = { OnBoardingScreenEmergencies() },
            background = Color.Black.copy(alpha = 0.7f),
            statusBarColor = Color(0xFFF5F5F5)
        ),
        /*  OnBoardingContentDataClass(
              content = { OnBoardingScreenFour() },
              background = Color.Black.copy(alpha = 0.7f),
              statusBarColor = Color(26, 38, 55)
          ),*/
        OnBoardingContentDataClass(
            content = { OnBoardingScreenFive() },
            background = Color.White,
            statusBarColor = Color(26, 38, 55)
        )/*,
        OnBoardingContentDataClass(
            content = { OnBoardingScreenSix() },
            background = Color.Black.copy(alpha = 0.7f),
            statusBarColor = Color(26, 38, 55)
        ),*/
    )

    val activeContent = remember {
        mutableStateOf(0)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val img = if (activeContent.value.equals(6)){
            painterResource(id = R.drawable.robot)
        }else painterResource(id = R.drawable.robot)

        Image(
            painter = img,
            contentDescription = "background image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(onBoardingContent[activeContent.value].background)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                onBoardingContent[activeContent.value].content()
            }
            OnBoardingNav(
                onBoardingContent = onBoardingContent,
                nextContent = { activeContent.value++ },
                activeIndex = activeContent.value,
                setContent = { activeContent.value = it },
                onOnBoardingFinished = { onOnBoardingFinished() }
            )
        }
    }
}


@Composable
fun OnBoardingNav(
    onBoardingContent: List<OnBoardingContentDataClass>,
    nextContent: () -> Unit,
    activeIndex: Int,
    setContent: (Int) -> Unit,
    onOnBoardingFinished: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(bottom = 4.dp, start = 40.dp, end = 33.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
        ) {
            onBoardingContent.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(13.dp)
                        .clickable { setContent(index) }
                        .clip(CircleShape)
                        .border(1.dp, Color.Black, CircleShape)
                        .background(
                            if (activeIndex == index) {
                                Color.Black
                            } else {
                                Color.LightGray
                            }
                        )
                )
            }
        }
        Button(
            onClick = {
                if (activeIndex == onBoardingContent.lastIndex) {
                    onOnBoardingFinished()
                } else {
                    nextContent()
                }
            },
            modifier = Modifier
                .padding(start = 8.dp, bottom = 13.dp)
                .size(68.dp)
                .clip(CircleShape),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black
            )
        ) {
            SimpleText(
                textSize = 16,
                text = "next",
                fontWeight = FontWeight.W900,
                textColor = Color.White

            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OnBoardingContentPreview() {
    DropyTheme {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            OnBoardingContent(
                onOnBoardingFinished = {}
            )
        }
    }
}