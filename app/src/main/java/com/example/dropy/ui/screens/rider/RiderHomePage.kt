package com.example.dropy.ui.screens.rider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.frontside.customer.orderprocess.payments.ParcelInvoiceContent

@Composable
fun RiderHomePage(
    riderHomePageViewModel: RiderHomePageViewModel
){
    val appUiState = riderHomePageViewModel.appViewModel!!.appUiState.collectAsState()
    val uiState by riderHomePageViewModel.riderHomePageUiState.collectAsState()

    AppScaffold(
        content = {
            ParcelInvoiceContent()
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = {riderHomePageViewModel.appViewModel?.onBackButtonClicked()},
        onDashboardButtonClicked = {riderHomePageViewModel.appViewModel?.onDashboardButtonClicked()},
        onCartButtonClicked = {riderHomePageViewModel.appViewModel?.onCartButtonClicked()},
        navigateTo = {riderHomePageViewModel.appViewModel?.navigate(it)},
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = {riderHomePageViewModel.appViewModel?.onSelectProfile(it)},
        activeProfile = appUiState.value.activeProfile,
    )
}

@Preview(showBackground = true)
@Composable
fun RiderHomePagePreview(){
    RiderHomePage(hiltViewModel())
}