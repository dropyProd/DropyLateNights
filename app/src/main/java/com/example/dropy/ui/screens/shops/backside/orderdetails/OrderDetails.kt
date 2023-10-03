package com.example.dropy.ui.screens.shops.backside.orderdetails

import android.Manifest
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.backside.dashboard.orders.orderdetails.OrderDetailsContent
import com.example.dropy.ui.screens.cancelling.ReasonForCancellingDialog
import com.example.dropy.ui.screens.cancelling.ReasonForCancellingDialogViewModel
import com.example.dropy.ui.screens.shops.backside.shopincomingorders.ShopIncomingOrdersViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun OrderDetails(
    orderDetailsViewModel: OrderDetailsViewModel,
    shopIncomingOrdersViewModel: ShopIncomingOrdersViewModel
) {

    val reasonForCancellingDialogViewModel: ReasonForCancellingDialogViewModel = hiltViewModel()

    val appUiState = orderDetailsViewModel.appViewModel!!.appUiState.collectAsState()

    val uiState by orderDetailsViewModel.orderDetailsUiState.collectAsState()
    val context = LocalContext.current


    val smspermission = rememberPermissionState(
        permission = Manifest.permission.CALL_PHONE
    )
    val callpermissionStates = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.SEND_SMS
        )
    )

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner, effect = {
        val eventObserver = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    smspermission.launchPermissionRequest()
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(eventObserver)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(eventObserver)
        }
    })

    AppScaffold(
        content = {

            ReasonForCancellingDialog(
                reasonForCancellingDialogViewModel = reasonForCancellingDialogViewModel,
                show = uiState.showReasonForCancelling,
                onDismisssDialog = {
                    orderDetailsViewModel.reasonForCancellingChange(false)
                })
            OrderDetailsContent(
                uiState = uiState,
                markItemAsAvailable = {
                    /*orderDetailsViewModel.markItemAsAvailable(it) */
                },
                markItemAsUnavailable = { num, url ->
                    reasonForCancellingDialogViewModel.outOfStockStateChange(false)
                    reasonForCancellingDialogViewModel.toReStockLaterStateChange(false)
                    reasonForCancellingDialogViewModel.setProductImageUrl(url)
                    orderDetailsViewModel.reasonForCancellingChange(true)

                    /* orderDetailsViewModel.markItemAsUnavailable(it) */
                },
                onCallCustomerClicked = {
                    when {
                        smspermission.hasPermission -> {
                            orderDetailsViewModel.onCallCustomerClicked(context = context)
                        }
                        smspermission.shouldShowRationale -> {
                            Toast.makeText(
                                context,
                                "Phone permission is required by this app",
                                Toast.LENGTH_SHORT
                            ).show()
                            smspermission.launchPermissionRequest()
                        }
                        !smspermission.hasPermission && !smspermission.shouldShowRationale -> {
                            Toast.makeText(
                                context,
                                "Permission fully denied. Go to settings to enable",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                },
                onProcessOrderClicked = {
                    orderDetailsViewModel.onProcessOrderClicked(context)
                },
                onCancelOrderClicked = { orderDetailsViewModel.onCancelOrderClicked(context) }
            )
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { orderDetailsViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { orderDetailsViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { orderDetailsViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { orderDetailsViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { orderDetailsViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile
    )
}

@Preview(showBackground = true)
@Composable
fun OrderDetailsPreview() {
    Column(Modifier.fillMaxSize()) {
        //OrderDetails(orderDetailsViewModel = hiltViewModel())
    }
}