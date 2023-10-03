package com.example.dropy.ui.screens.rider.becomerider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.rider.AddRiderContent

@Composable
fun AddRider(
    appViewModel: AppViewModel,
    addRiderViewModel: AddRiderViewModel,
    becomeRiderViewModel: BecomeRiderViewModel,
    choosePlace: () -> Unit
) {
    val appUiState = appViewModel.appUiState.collectAsState()
    val uiState by becomeRiderViewModel.uiState.collectAsState()

    val context = LocalContext.current

    AppScaffold(
        content = {
            /*    AddRiderContent(
                    uiState = uiState,
                    onRiderNameChanged = addRiderViewModel::setRiderName,
                    onRiderPhoneChanged = addRiderViewModel::setRiderPhone,
                    onDeliveryMethodSelect =addRiderViewModel::setRiderMethod ,
                    onAddRiderLocation = {
                        addRiderViewModel.setAppViewModel(appViewModel)
                        addRiderViewModel.navigateAddriderLocation(context)
                    },
                    onRiderIdChanged = addRiderViewModel::setRiderId
                )*/

            AddRiderContent(
                uiState = uiState,
                appuiState = appUiState.value,
                onPlaceNameChanged = {
                    choosePlace()
                },
                onBikeModelChanged = becomeRiderViewModel::updateBikeModel,
                onRiderPhoneChanged = {},
                onPlateNumberChanged = becomeRiderViewModel::updatePlatenumber,
                onCountyQrCodeChanged = becomeRiderViewModel::updateCountyQrIdNumber,
                onNationalIdChanged = becomeRiderViewModel::updatenationalid,
                onDescriptionChanged = becomeRiderViewModel::updateDescription,
                onDeliveryMethodSelect = becomeRiderViewModel::updateDeliverymethodId,
                onPoolsSelect = becomeRiderViewModel::updatepoolid
            ) {
                becomeRiderViewModel.createRider(appViewModel = appViewModel, context)
            }
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