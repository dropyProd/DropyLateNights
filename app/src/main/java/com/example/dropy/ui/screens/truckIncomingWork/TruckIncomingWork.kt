package com.example.dropy.ui.screens.truckIncomingWork

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.truckDeduction.TruckDeductionContent
import com.example.dropy.ui.screens.truckFindJob.TruckFindJobContent
import com.example.dropy.ui.screens.truckFindJob.TruckFindJobViewModel
import com.example.dropy.ui.screens.waterOrderSingle.WaterOrderSingleViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TruckIncomingWork(
    cartPageViewModel: CartPageViewModel,
    truckIncomingWorkViewModel: TruckIncomingWorkViewModel,
    waterOrderSingleViewModel: WaterOrderSingleViewModel
) {

    val truckIncomingWorkUiState by truckIncomingWorkViewModel.truckIncomingWorkUiState.collectAsState()

    val appUiState = truckIncomingWorkViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )

    val context = LocalContext.current

    LaunchedEffect(key1 = true, block ={
        truckIncomingWorkViewModel.getMyLocale(context)
    } )

    var isSheetFullScreen by remember { mutableStateOf(false) }
    val roundedCornerRadius = if (isSheetFullScreen) 0.dp else 12.dp
    val modifier = if (isSheetFullScreen)
        Modifier
            .background(Color.Transparent)
            .fillMaxSize()
    else
        Modifier.fillMaxWidth()

    //on Backpressed
    BackHandler(modalSheetState.isVisible) {
        coroutineScope.launch { modalSheetState.hide() }
    }

    LaunchedEffect(key1 = true, block = {
        truckIncomingWorkViewModel.appViewModel!!.getIndividualOrders()
        truckIncomingWorkViewModel.getOrders()
    })

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetBackgroundColor = Color.Transparent,
        sheetShape = RoundedCornerShape(
            topStart = roundedCornerRadius,
            topEnd = roundedCornerRadius
        ),
        sheetContent = {
            Column(
                modifier = modifier.background(Color.Transparent),
            ) {

                TruckDeductionContent(navigate = {
                    coroutineScope.launch {
                        if (it.equals("YES"))
                            truckIncomingWorkViewModel.navigateWaterOrderSingle(context)
                        else modalSheetState.hide()

                    }
                })
                /*Button(
                    onClick = {
                        isSheetFullScreen = !isSheetFullScreen
                    }
                ) {
                    Text(text = "Toggle Sheet Fullscreen")
                }

                Button(
                    onClick = {
                        coroutineScope.launch { modalSheetState.hide() }
                    }
                ) {
                    Text(text = "Hide Sheet")
                }*/
            }
        }
    ) {

        AppScaffold(
            content = {
                TruckIncomingWorkContent(
                    uiState = truckIncomingWorkUiState,
                    appViewModel = truckIncomingWorkViewModel.appViewModel!!,
                    buttonClicked = {it, order->
                        coroutineScope.launch {
                            truckIncomingWorkViewModel.setSelectedOrder(order)
                            if (it.equals("Accept")) {
                                truckIncomingWorkViewModel.setAddress(waterOrderSingleViewModel = waterOrderSingleViewModel )
                                truckIncomingWorkViewModel.navigateWaterOrderSingle(context)

                                /*  if (modalSheetState.isVisible)
                                      modalSheetState.hide()
                                  else
                                      modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                         */

                            }
                        }

                    }, appUiState = appUiState.value
                )
            },
            pageLoading = truckIncomingWorkUiState.pageLoading,
            actionLoading = truckIncomingWorkUiState.actionLoading,
            errorList = truckIncomingWorkUiState.errorList,
            messageList = truckIncomingWorkUiState.messageList,
            onBackButtonClicked = { truckIncomingWorkViewModel.appViewModel?.onBackButtonClicked() },
            onDashboardButtonClicked = { truckIncomingWorkViewModel.appViewModel?.onDashboardButtonClicked() },
            onCartButtonClicked = { truckIncomingWorkViewModel.appViewModel?.onCartButtonClicked() },
            navigateTo = { truckIncomingWorkViewModel.appViewModel?.navigate(it) },
            drawerMenuItems = appUiState.value.drawerMenuItems,
            userProfiles = appUiState.value.userProfiles,
            onSelectProfile = { truckIncomingWorkViewModel.appViewModel?.onSelectProfile(it) },
            activeProfile = appUiState.value.activeProfile,
            cartsize = cartUiState.value.orderList.size,
            showLogo = false,
            showImageRight = true,
            showCart = false
        )
    }
}