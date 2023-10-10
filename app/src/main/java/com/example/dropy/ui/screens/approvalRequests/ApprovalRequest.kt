package com.example.dropy.ui.screens.approvalRequests

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeViewModel
import com.example.dropy.ui.screens.water.waterHome.WaterHomeContent
import com.example.dropy.ui.screens.water.waterHome.WaterHomeViewModel

@Composable
fun ApprovalRequests(
    cartPageViewModel: CartPageViewModel, approvalRequestViewModel: ApprovalRequestViewModel
) {

    val approvalRequestUiState by approvalRequestViewModel.approvalRequestUiState.collectAsState()

    val appUiState = approvalRequestViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(key1 = true, block = {
        approvalRequestViewModel.getApprovalRequests()
    })

    AppScaffold(
        content = {
            ApprovalRequestContent(uiState = approvalRequestUiState, onClick = { text, item ->
                approvalRequestViewModel.modifyApprovalRequests(
                    text = text,
                    approvalRequestsResItem = item,
                    context = context
                )
            })
        },
        pageLoading = approvalRequestUiState.pageLoading,
        actionLoading = approvalRequestUiState.actionLoading,
        errorList = approvalRequestUiState.errorList,
        messageList = approvalRequestUiState.messageList,
        onBackButtonClicked = { approvalRequestViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { approvalRequestViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { approvalRequestViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { approvalRequestViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { approvalRequestViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = false
    )
}