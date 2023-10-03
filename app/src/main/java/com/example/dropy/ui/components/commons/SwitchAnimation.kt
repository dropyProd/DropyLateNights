package com.example.dropy.ui.components.commons

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.SolidColor

import androidx.compose.ui.graphics.vector.Group
import androidx.compose.ui.graphics.vector.Path
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.data.Group
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

object SwitchAnimationPaths {
    val sunPath = PathParser().parsePathString(
        "M98.73,50.32m-42.29,15.39a45,45 0,1 0,84.57 -30.78a45,45 0,1 0,-84.57 30.78"
    ).toNodes()

    val moon1Path1 = PathParser().parsePathString(
        "M59.32,51.27m15.39,42.29a45,45 0,1 0,-30.78 -84.57a45,45 0,1 0,30.78 84.57"
    ).toNodes()

    val moon1Path2 = PathParser().parsePathString(
        "M59.32,51.27m15.39,42.29a45,45 0,1 0,-30.78 -84.57a45,45 0,1 0,30.78 84.57"
    ).toNodes()

    val moon1Path3 = PathParser().parsePathString(
        "M101.5,36C98.46,36 96,38.46 96,41.5C96,44.54 98.46,47 101.5,47C102.43,47 103.3,46.77 104.06,46.37C103.68,42.9 102.89,39.43 101.65,36C101.6,36 101.55,36 101.5,36ZM87.98,16.56C87.99,16.71 88,16.85 88,17C88,19.21 86.21,21 84,21C81.79,21 80,19.21 80,17C80,15.08 81.35,13.48 83.15,13.09C84.83,14.14 86.44,15.3 87.98,16.56ZM32.21,15.33C33.85,16.01 35,17.62 35,19.5C35,21.99 32.99,24 30.5,24C28.47,24 26.75,22.65 26.19,20.8C28.01,18.81 30.02,16.98 32.21,15.33ZM14.46,54.91C15.15,54.34 16.03,54 17,54C19.21,54 21,55.79 21,58C21,60.21 19.21,62 17,62C16.49,62 16,61.9 15.55,61.73C15,59.46 14.64,57.18 14.46,54.91ZM39,41.5C39,38.46 41.46,36 44.5,36C47.54,36 50,38.46 50,41.5C50,44.54 47.54,47 44.5,47C41.46,47 39,44.54 39,41.5ZM50,18.5C50,13.81 53.81,10 58.5,10C63.19,10 67,13.81 67,18.5C67,23.19 63.19,27 58.5,27C53.81,27 50,23.19 50,18.5Z"
    ).toNodes()

    val moon1ClipPath = PathParser().parsePathString(
        "M85.55,95.29m10.26,28.19a30,30 116.5,1 0,-20.52 -56.38a30,30 116.5,1 0,20.52 56.38"
    ).toNodes()

    val cloud1Path1 = PathParser().parsePathString(
        "M13.445,18.583C14.176,18.186 14.739,17.535 15.028,16.756C18.656,6.97 28.031,0 39.026,0C48.908,0 57.482,5.631 61.75,13.877C62.507,15.338 64.204,16.103 65.822,15.802C66.885,15.604 67.982,15.5 69.103,15.5C78.987,15.5 87,23.559 87,33.5C87,43.441 78.987,51.5 69.103,51.5C64.799,51.5 60.85,49.972 57.763,47.426C56.491,46.377 54.63,46.233 53.263,47.155C52.592,47.607 51.898,48.029 51.183,48.417C50.453,48.814 49.889,49.465 49.6,50.244C45.973,60.03 36.597,67 25.603,67C11.463,67 0,55.471 0,41.25C0,31.454 5.439,22.936 13.445,18.583Z"
    ).toNodes()

    val cloud1Path2 = PathParser().parsePathString(
        "M14.257,24.561C14.983,24.155 15.548,23.507 15.85,22.731C18.758,15.28 26.013,10 34.503,10C41.995,10 48.526,14.112 51.955,20.2C52.76,21.628 54.45,22.387 56.075,22.163C56.708,22.075 57.354,22.03 58.011,22.03C65.737,22.03 72,28.285 72,36C72,43.715 65.737,49.97 58.011,49.97C54.877,49.97 51.983,48.94 49.65,47.201C48.335,46.22 46.489,46.074 45.097,46.943C44.822,47.115 44.542,47.28 44.257,47.439C43.531,47.845 42.966,48.493 42.664,49.269C39.756,56.72 32.501,62 24.011,62C12.959,62 4,53.052 4,42.015C4,34.515 8.137,27.979 14.257,24.561Z"
    ).toNodes()

    val cloud2Path1 = PathParser().parsePathString(
        "M62.831,25.377C61.808,25.377 60.81,25.498 59.848,25.726C59.398,25.833 58.924,25.649 58.679,25.256C57.672,23.638 56.514,22.143 55.228,20.798C55.113,20.677 55.028,20.529 54.982,20.368C51.73,9.132 42.233,1 31.024,1C22.486,1 14.941,5.719 10.393,12.933C10.173,13.283 9.766,13.47 9.357,13.412C8.309,13.265 7.241,13.189 6.157,13.189C-7.737,13.189 -19,25.683 -19,41.094C-19,56.506 -7.737,69 6.157,69C11.877,69 17.152,66.882 21.378,63.314C21.771,62.982 22.349,62.982 22.742,63.314C26.968,66.882 32.243,69 37.964,69C46.555,69 54.14,64.223 58.679,56.932C58.924,56.54 59.398,56.355 59.848,56.462C60.81,56.691 61.808,56.811 62.831,56.811C70.657,56.811 77,49.775 77,41.094C77,32.414 70.657,25.377 62.831,25.377Z"
    ).toNodes()

    val cloud2Path2 = PathParser().parsePathString(
        "M61.75,30C60.884,30 60.038,30.09 59.223,30.261C58.797,30.35 58.354,30.183 58.105,29.826C57.258,28.611 56.29,27.487 55.219,26.47C55.092,26.35 54.997,26.199 54.943,26.032C52.112,17.308 43.918,11 34.25,11C26.9,11 20.401,14.646 16.464,20.228C16.239,20.547 15.856,20.716 15.468,20.668C14.578,20.557 13.67,20.5 12.75,20.5C0.738,20.5 -9,30.238 -9,42.25C-9,54.262 0.738,64 12.75,64C17.672,64 22.213,62.365 25.858,59.608C26.237,59.321 26.763,59.321 27.142,59.608C30.787,62.365 35.328,64 40.25,64C47.643,64 54.174,60.311 58.105,54.674C58.354,54.317 58.797,54.15 59.223,54.239C60.038,54.41 60.884,54.5 61.75,54.5C68.516,54.5 74,49.015 74,42.25C74,35.485 68.516,30 61.75,30Z"
    ).toNodes()

    val backgroundPath =
        PathParser().parsePathString("M201,9L61,9A50,50 0,0 0,11 59L11,59A50,50 0,0 0,61 109L201,109A50,50 0,0 0,251 59L251,59A50,50 0,0 0,201 9z")
            .toNodes()

    val star1 =
        PathParser().parsePathString("M90.4,36.8C90.68,35.92 91.93,35.92 92.21,36.8C92.34,37.19 92.71,37.45 93.12,37.45C94.04,37.45 94.43,38.64 93.68,39.18C93.35,39.43 93.21,39.86 93.33,40.25C93.62,41.13 92.61,41.86 91.86,41.32C91.53,41.07 91.08,41.07 90.74,41.32C89.99,41.86 88.99,41.13 89.27,40.25C89.4,39.86 89.26,39.43 88.93,39.18C88.18,38.64 88.56,37.45 89.49,37.45C89.9,37.45 90.27,37.19 90.4,36.8Z")
            .toNodes()

    val star2 =
        PathParser().parsePathString("M2.94,33.81C2.8,34.24 2.18,34.23 2.05,33.79C1.99,33.59 1.81,33.46 1.6,33.46C1.15,33.45 0.97,32.86 1.34,32.6C1.51,32.48 1.58,32.27 1.52,32.07C1.39,31.64 1.89,31.28 2.26,31.56C2.42,31.68 2.65,31.69 2.81,31.57C3.19,31.31 3.68,31.68 3.53,32.11C3.46,32.3 3.53,32.52 3.69,32.64C4.06,32.92 3.86,33.5 3.4,33.49C3.19,33.48 3.01,33.61 2.94,33.81Z")
            .toNodes()
    val star3 =
        PathParser().parsePathString("M74.32,73.28C74.02,74.14 72.79,74.12 72.53,73.25C72.41,72.86 72.05,72.59 71.64,72.58C70.73,72.56 70.37,71.39 71.12,70.86C71.45,70.63 71.6,70.2 71.48,69.81C71.22,68.94 72.22,68.23 72.95,68.79C73.28,69.03 73.73,69.04 74.06,68.81C74.81,68.28 75.79,69.02 75.5,69.89C75.36,70.27 75.49,70.7 75.82,70.95C76.55,71.5 76.15,72.66 75.23,72.65C74.82,72.64 74.46,72.89 74.32,73.28Z")
            .toNodes()
    val star4 =
        PathParser().parsePathString("M116.39,76.8C116.68,75.92 117.93,75.92 118.21,76.8C118.34,77.19 118.71,77.45 119.12,77.45C120.04,77.45 120.43,78.64 119.68,79.18C119.35,79.43 119.21,79.86 119.33,80.25C119.62,81.13 118.61,81.86 117.86,81.32C117.53,81.07 117.08,81.07 116.74,81.32C115.99,81.86 114.99,81.13 115.27,80.25C115.4,79.86 115.26,79.43 114.93,79.18C114.18,78.64 114.56,77.45 115.49,77.45C115.9,77.45 116.27,77.19 116.39,76.8Z")
            .toNodes()
    val star5 =
        PathParser().parsePathString("M26.92,73.79C26.77,74.22 26.15,74.21 26.02,73.77C25.96,73.57 25.78,73.44 25.58,73.43C25.12,73.43 24.94,72.84 25.31,72.58C25.48,72.46 25.55,72.25 25.49,72.05C25.36,71.61 25.87,71.26 26.23,71.54C26.39,71.66 26.62,71.67 26.79,71.55C27.16,71.29 27.65,71.66 27.5,72.09C27.43,72.28 27.5,72.5 27.66,72.62C28.03,72.89 27.83,73.48 27.37,73.47C27.17,73.46 26.98,73.59 26.92,73.79Z")
            .toNodes()
    val star6 =
        PathParser().parsePathString("M50.35,33.3C50.05,34.17 48.82,34.14 48.56,33.27C48.44,32.88 48.08,32.61 47.67,32.6C46.76,32.58 46.4,31.41 47.15,30.88C47.48,30.65 47.63,30.23 47.51,29.83C47.24,28.96 48.25,28.25 48.98,28.81C49.31,29.05 49.76,29.06 50.09,28.83C50.84,28.3 51.82,29.04 51.52,29.91C51.39,30.29 51.52,30.72 51.85,30.97C52.58,31.52 52.18,32.68 51.26,32.67C50.85,32.66 50.49,32.91 50.35,33.3Z")
            .toNodes()
    val star7 =
        PathParser().parsePathString("M92.88,3.84C93.37,2.52 95.24,2.52 95.73,3.84L95.76,3.92C95.96,4.47 96.49,4.84 97.08,4.84H97.4C98.71,4.84 99.21,6.55 98.11,7.26C97.58,7.59 97.37,8.25 97.58,8.83C98.04,10.06 96.69,11.19 95.58,10.49L95.13,10.2C94.63,9.88 93.98,9.88 93.48,10.2L93.02,10.49C91.92,11.19 90.57,10.06 91.02,8.83C91.24,8.25 91.02,7.59 90.5,7.26C89.39,6.55 89.89,4.84 91.21,4.84H91.53C92.12,4.84 92.64,4.47 92.85,3.92L92.88,3.84Z")
            .toNodes()
    val star8 =
        PathParser().parsePathString("M92.88,3.84C93.37,2.52 95.24,2.52 95.73,3.84L95.76,3.92C95.96,4.47 96.49,4.84 97.08,4.84H97.4C98.71,4.84 99.21,6.55 98.11,7.26C97.58,7.59 97.37,8.25 97.58,8.83C98.04,10.06 96.69,11.19 95.58,10.49L95.13,10.2C94.63,9.88 93.98,9.88 93.48,10.2L93.02,10.49C91.92,11.19 90.57,10.06 91.02,8.83C91.24,8.25 91.02,7.59 90.5,7.26C89.39,6.55 89.89,4.84 91.21,4.84H91.53C92.12,4.84 92.64,4.47 92.85,3.92L92.88,3.84Z")
            .toNodes()
    val star9 =
        PathParser().parsePathString("M118.44,43.91C118.89,42.63 120.72,42.63 121.16,43.91C121.36,44.46 121.88,44.84 122.46,44.84H122.63C123.93,44.84 124.45,46.52 123.37,47.26C122.87,47.59 122.67,48.22 122.86,48.79C123.29,50.02 121.91,51.1 120.83,50.37L120.61,50.22C120.12,49.89 119.48,49.89 119,50.22L118.77,50.37C117.7,51.1 116.32,50.02 116.74,48.79C116.94,48.22 116.73,47.59 116.24,47.26C115.16,46.52 115.68,44.84 116.98,44.84H117.14C117.73,44.84 118.25,44.46 118.44,43.91Z")
            .toNodes()
    val star10 =
        PathParser().parsePathString("M47.72,66.47C47.27,67.76 45.42,67.73 45.02,66.42C44.85,65.83 44.31,65.42 43.7,65.41C42.33,65.39 41.79,63.62 42.91,62.84C43.41,62.49 43.63,61.85 43.46,61.26C43.06,59.95 44.57,58.9 45.66,59.72C46.15,60.09 46.82,60.1 47.33,59.75C48.45,58.97 49.92,60.08 49.47,61.38C49.27,61.96 49.47,62.6 49.96,62.97C51.05,63.79 50.45,65.54 49.08,65.51C48.47,65.5 47.92,65.89 47.72,66.47Z")
            .toNodes()
}

@Preview
@Composable
fun SwitchVectorAnimation() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        var clicked = false

        val transitionXCloud1 = remember {
            Animatable(36.dp.value)
        }

        val transitionYCloud1 = remember {
            Animatable(56.dp.value)
        }

        val transitionXCloud2 = remember {
            Animatable(0f)
        }

        val transitionYCloud2 = remember {
            Animatable(40.dp.value)
        }

        val alphaNightAnimation = remember {
            Animatable(0f)
        }

        val transitionXSun = remember {
            Animatable(89f)
        }

        val transitionXMoon = remember {
            Animatable(124f)
        }

        val alphaSun = remember {
            Animatable(1f)
        }

        val alpha1MoonAnimation = remember {
            Animatable(0f)
        }
        val alpha2MoonAnimation = remember {
            Animatable(0f)
        }

        val alpha3MoonAnimation = remember {
            Animatable(0f)
        }

        val tweenSpec = tween<Float>(900, easing = LinearEasing)

        suspend fun clickedAnimation(clicked: Boolean) {
            coroutineScope {
                launch {
                    transitionXCloud1.animateTo(
                        if (clicked) 36.dp.value else 66.dp.value,
                        tweenSpec
                    )
                }
                launch {
                    transitionYCloud1.animateTo(
                        if (clicked) 56.dp.value else 99.dp.value,
                        tweenSpec
                    )
                }
                launch {
                    transitionXCloud2.animateTo(
                        if (clicked) 0.dp.value else -36.dp.value,
                        tweenSpec
                    )
                }
                launch {
                    transitionYCloud2.animateTo(
                        if (clicked) 40.dp.value else 80.dp.value,
                        tweenSpec
                    )
                }
                launch {
                    transitionXSun.animateTo(
                        if (clicked) 89.dp.value else -48.dp.value,
                        tweenSpec
                    )
                }
                launch {
                    transitionXMoon.animateTo(
                        if (clicked) 124.dp.value else -7.dp.value,
                        tweenSpec
                    )
                }
                launch { alphaSun.animateTo(if (clicked) 1f else 0f, tweenSpec) }
                launch { alphaNightAnimation.animateTo(if (clicked) 0f else 1f, tweenSpec) }
                launch { alpha1MoonAnimation.animateTo(if (clicked) 0f else 1f, tweenSpec) }
                launch { alpha2MoonAnimation.animateTo(if (clicked) 0f else 0.1f, tweenSpec) }
                launch { alpha3MoonAnimation.animateTo(if (clicked) 0f else 0.3f, tweenSpec) }
            }
        }

        val vectorPainter = rememberVectorPainter(
            defaultWidth = 240.dp,
            defaultHeight = 100.dp,
            viewportWidth = 240f,
            viewportHeight = 100f,
            autoMirror = true,
        ) { viewPortWidth, viewPortHeight ->

            DayBackgroundGroup()

            NightBackgroundGroup(alphaNightAnimation)

            CloudsGroup(transitionXCloud1, transitionYCloud1, transitionXCloud2, transitionYCloud2)

            Group("sun", translationX = transitionXSun.value) {
                Path(
                    fillAlpha = alphaSun.value,
                    pathData = SwitchAnimationPaths.sunPath,
                    fill = SolidColor(Color(0xFFF6D35A))
                )
            }

            MoonGroup(
                transitionXMoon,
                alpha1MoonAnimation,
                alpha2MoonAnimation,
                alpha3MoonAnimation
            )

            StarsGroup(viewPortWidth, viewPortHeight, alpha1MoonAnimation)
        }

        val coroutineScope = rememberCoroutineScope()
        Image(
            vectorPainter,
            contentDescription = "SwitchDayNight",
            modifier = Modifier
                .height(100.dp)
                .width(240.dp)
                .clip(RoundedCornerShape(50.dp))
                .border(3.dp, Color.Gray, CircleShape)
                .pointerInput(Unit) {
                    detectTapGestures {
                        coroutineScope.launch {
                            clicked = if (clicked) {
                                clickedAnimation(true)
                                !clicked
                            } else {
                                clickedAnimation(false)
                                !clicked
                            }
                        }
                    }
                }
        )
    }

}

@Composable
fun StarsGroup(
    viewPortWidth: Float,
    viewPortHeight: Float,
    alpha1MoonAnimation: Animatable<Float, AnimationVector1D>
) {
    Group(
        name = "stars",
        translationX = 86.dp.value,
        scaleX = alpha1MoonAnimation.value,
        scaleY = alpha1MoonAnimation.value,
        pivotX = viewPortWidth / 2,
        pivotY = viewPortHeight / 2
    ) {
        Path(
            fillAlpha = alpha1MoonAnimation.value,
            pathData = SwitchAnimationPaths.star1,
            strokeAlpha = 0.75f,
            fill = SolidColor(Color(0xFFF4FEFF))
        )
        Path(
            fillAlpha = alpha1MoonAnimation.value,
            pathData = SwitchAnimationPaths.star2,
            strokeAlpha = 0.75f,
            fill = SolidColor(Color(0xFFF4FEFF))
        )
        Path(
            fillAlpha = alpha1MoonAnimation.value,
            pathData = SwitchAnimationPaths.star3,
            strokeAlpha = 0.75f,
            fill = SolidColor(Color(0xFFF4FEFF))
        )
        Path(
            fillAlpha = alpha1MoonAnimation.value,
            pathData = SwitchAnimationPaths.star4,
            strokeAlpha = 0.75f,
            fill = SolidColor(Color(0xFFF4FEFF))
        )

        Path(
            fillAlpha = alpha1MoonAnimation.value,
            pathData = SwitchAnimationPaths.star5,
            strokeAlpha = 0.75f,
            fill = SolidColor(Color(0xFFF4FEFF))
        )
        Path(
            fillAlpha = alpha1MoonAnimation.value,
            pathData = SwitchAnimationPaths.star6,
            strokeAlpha = 0.75f,
            fill = SolidColor(Color(0xFFF4FEFF))
        )
        Path(
            fillAlpha = alpha1MoonAnimation.value,
            pathData = SwitchAnimationPaths.star7,
            strokeAlpha = 0.75f,
            fill = SolidColor(Color(0xFFF4FEFF))
        )
        Path(
            fillAlpha = alpha1MoonAnimation.value,
            pathData = SwitchAnimationPaths.star8,
            strokeAlpha = 0.75f,
            fill = SolidColor(Color(0xFFF4FEFF))
        )

        Path(
            fillAlpha = alpha1MoonAnimation.value,
            pathData = SwitchAnimationPaths.star9,
            strokeAlpha = 0.75f,
            fill = SolidColor(Color(0xFFF4FEFF))
        )
        Path(
            fillAlpha = alpha1MoonAnimation.value,
            pathData = SwitchAnimationPaths.star10,
            strokeAlpha = 0.75f,
            fill = SolidColor(Color(0xFFF4FEFF))
        )

    }
}

@Composable
fun MoonGroup(
    transitionXMoon: Animatable<Float, AnimationVector1D>,
    alpha1MoonAnimation: Animatable<Float, AnimationVector1D>,
    alpha2MoonAnimation: Animatable<Float, AnimationVector1D>,
    alpha3MoonAnimation: Animatable<Float, AnimationVector1D>
) {
    Group(
        "moon1",
        translationX = transitionXMoon.value, translationY = -1.dp.value
    ) {
        Path(
            fillAlpha = alpha1MoonAnimation.value,
            pathData = SwitchAnimationPaths.moon1Path1,
            fill = SolidColor(Color(0xFFDDDDDD))
        )
        Path(
            fillAlpha = alpha2MoonAnimation.value,
            pathData = SwitchAnimationPaths.moon1Path2,
            fill = SolidColor(Color(0xFFF6D35A)),
        )
        Path(
            pathData = SwitchAnimationPaths.moon1Path3,
            fill = SolidColor(Color(0xFF999999)),
            fillAlpha = alpha3MoonAnimation.value,
            strokeAlpha = 0.6f
        )
    }
}

@Composable
fun CloudsGroup(
    transitionXCloud1: Animatable<Float, AnimationVector1D>,
    transitionYCloud1: Animatable<Float, AnimationVector1D>,
    transitionXCloud2: Animatable<Float, AnimationVector1D>,
    transitionYCloud2: Animatable<Float, AnimationVector1D>
) {
    Group(
        "cloud1",
        translationY = transitionYCloud1.value,
        translationX = transitionXCloud1.value
    ) {
        Path(
            pathData = SwitchAnimationPaths.cloud1Path1,
            fill = SolidColor(Color(0xFFD0E7F6))
        )
        Path(
            pathData = SwitchAnimationPaths.cloud1Path2,
            fill = SolidColor(Color(0xFFE3F1F5))
        )
    }

    Group(
        "cloud2",
        translationY = transitionYCloud2.value,
        translationX = transitionXCloud2.value
    ) {
        Path(
            pathData = SwitchAnimationPaths.cloud2Path1,
            fill = SolidColor(Color(0xFFD0E7F6))
        )
        Path(
            pathData = SwitchAnimationPaths.cloud2Path2,
            fill = SolidColor(Color(0xFFE3F1F5))
        )
    }
}

@Composable
fun NightBackgroundGroup(alphaNightAnimation: Animatable<Float, AnimationVector1D>) {
    Group(
        "nightBackground",
        translationY = -9.dp.value,
        translationX = -11.dp.value
    ) {
        Path(
            fillAlpha = alphaNightAnimation.value,
            pathData = SwitchAnimationPaths.backgroundPath,
            fill = SolidColor(Color(0xFF0F405B))
        )
    }
}

@Composable
fun DayBackgroundGroup() {
    Group(
        "dayBackground",
        translationY = -9.dp.value,
        translationX = -11.dp.value
    ) {
        Path(
            pathData = SwitchAnimationPaths.backgroundPath,
            fill = SolidColor(Color(0xFF5BA9D3))
        )
    }
}