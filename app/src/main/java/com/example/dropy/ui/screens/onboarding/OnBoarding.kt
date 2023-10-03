package com.example.dropy.ui.screens.onboarding

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dropy.ui.components.onboarding.OnBoardingContent

import com.example.dropy.ui.theme.DropyTheme
@Composable
fun OnBoarding(
    onBoardingViewModel: OnBoardingViewModel = viewModel()
){
    onBoardingViewModel.appViewModel?.setSystemUiControllerColor(Color(0xFFF5F5F5))
    OnBoardingContent(
        onOnBoardingFinished = { onBoardingViewModel.onOnBoardingFinished()}
    )
}


@Preview(showBackground = true)
@Composable
fun OnBoardingPreview() {
    DropyTheme{
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
         OnBoarding(onBoardingViewModel = OnBoardingViewModel())
        }
    }
}