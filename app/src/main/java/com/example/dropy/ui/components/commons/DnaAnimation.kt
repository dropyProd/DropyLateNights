package com.example.dropy.ui.components.commons

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun DnaAnimation(
    modifier: Modifier
) {
    Box(
        modifier
            .fillMaxSize()
            .rotate(180f), contentAlignment = Alignment.Center) {
        Column() {
            for(i in 0 until 15) {
                Box() {
                    DnaCircle(
                        modifier = modifier,
                        color = Color(255, 170, 0, 255),
                        delay = 500 + i * 100
                    )
                    DnaCircle(
                        modifier = modifier,
                        color = Color(255, 72, 0, 255),
                        delay = 0 + i * 100
                    )
                }
                Spacer(modifier = modifier.size(20.dp))
            }

        }
    }

}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DnaCircle(
    modifier: Modifier,
    color: Color,
    delay: Int
) {
    val infiniteAnimation = rememberInfiniteTransition()
    var shouldGetBigger by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val offset = infiniteAnimation.animateFloat(
        initialValue = -60f,
        targetValue = 60f,
        animationSpec = InfiniteRepeatableSpec(
            initialStartOffset = StartOffset(delay, StartOffsetType.FastForward),
            animation = keyframes {
                this.delayMillis = 0
                this.durationMillis = 500

            }, repeatMode = RepeatMode.Reverse
        )
    )
    coroutineScope.launch {
        val offset1 = offset.value
        delay(50)
        if (offset.value > offset1) {

            if (0 < offset.value && offset.value < 30) {
                shouldGetBigger = true

            } else if (30 < offset.value && offset.value < 60) {
                shouldGetBigger = false

            } else if (-60 < offset.value && offset.value < -30) {
                shouldGetBigger = true
            } else if (-30 < offset.value && offset.value < 0) {
                shouldGetBigger = true
            }
        } else {
            if (0 < offset.value && offset.value < 30) {
                shouldGetBigger = false

            } else if (30 < offset.value && offset.value < 60) {
                shouldGetBigger = false

            } else if (-30 < offset.value && offset.value < 0) {
                shouldGetBigger = false
            } else if (-60 < offset.value && offset.value < -30) {
                shouldGetBigger = true
            }
        }
    }


    val scale = animateFloatAsState(
        targetValue = if (shouldGetBigger) 1f else 0.2f,
        animationSpec = keyframes {
            this.durationMillis = 500
            this.delayMillis = 0
        })

    Box(
        modifier = modifier
            .size(20.dp)
            .offset(x = offset.value.dp)
            .scale(scale.value)
            .alpha(scale.value)
            .clip(CircleShape)
            .background(color)
    )

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    DnaAnimation(Modifier)

}