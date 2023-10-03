package com.example.dropy.ui.screens.waterServiceProviders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WaterServiceProviders(waterServiceProvidersViewModel: WaterServiceProvidersViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        WaterServiceProvidersContent(navigate = {
            when {
                it.equals("WATER\nPOINT") -> {
                    waterServiceProvidersViewModel.navigateWaterPoint()
                }
                it.equals("TRUCK\nCOMPANY") -> {
                    waterServiceProvidersViewModel.navigateWaterVendor()
                }
                it.equals("TRUCK\nDRIVER") -> {
                    waterServiceProvidersViewModel.navigateWaterTruck()
                }
            }
        })
    }
}