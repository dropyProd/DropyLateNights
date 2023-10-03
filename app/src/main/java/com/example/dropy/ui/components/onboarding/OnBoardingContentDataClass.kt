package com.example.dropy.ui.components.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class OnBoardingContentDataClass(
    val content :@Composable ()->Unit,
    val background :Color,
    val statusBarColor :Color,
    )