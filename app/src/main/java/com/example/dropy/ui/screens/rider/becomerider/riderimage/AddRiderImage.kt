package com.example.dropy.ui.screens.rider.becomerider.riderimage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.rider.AddRiderContent
import com.example.dropy.ui.screens.rider.becomerider.AddRiderViewModel

@Composable
fun AddRiderImage(
    appViewModel: AppViewModel,
    addRiderViewModel: AddRiderViewModel,
    choosePhoto: (String) -> Unit,
) {
    val appUiState = appViewModel.appUiState.collectAsState()
    val uiState by addRiderViewModel.addRiderUiState.collectAsState()

    val context = LocalContext.current

    AppScaffold(
        content = {
            AddRiderImageContent(
                onAddRider = { /*TODO*/ },
                uiState = uiState,
                onAddRiderCoverPhoto = { choosePhoto("cover") },
                onAddRiderLogo = {
                    choosePhoto("logo")
                })
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { appViewModel?.onCartButtonClicked() },
        navigateTo = { appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        showimg = true,
        showCart = false
    )


}