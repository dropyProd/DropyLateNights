package com.example.dropy.ui.screens.serviceProviders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun ServiceProviders(serviceProvidersViewModel: ServiceProvidersViewModel) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        ServiceProvidersContent(navigate = { serviceProvidersViewModel.navigateWaterServiceProviders(it, context) })
    }
}