package com.example.dropy.ui.screens.tankerBorehole

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.deliveryType.DeliveryTypeDialog
import com.example.dropy.ui.screens.deliveryType.DeliveryTypeViewModel
import com.example.dropy.ui.screens.nearestTrucks.NearestTrucksViewmodel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.truckDeduction.TruckDeductionContent
import com.example.dropy.ui.screens.truckIncomingWork.TruckIncomingWorkContent
import com.example.dropy.ui.screens.water.waterHome.WaterHomeContent
import com.example.dropy.ui.screens.water.waterHome.WaterHomeViewModel
import com.example.dropy.ui.screens.waterOrderDetails.WaterOrderDetailsViewModel
import com.example.dropy.ui.screens.waterSelectDate.WaterSelectDateContent
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TankerBorehole(
    cartPageViewModel: CartPageViewModel,
    deliveryTypeViewModel: DeliveryTypeViewModel,
    tankerBoreholeViewModel: TankerBoreholeViewModel,
    waterHomeViewModel: WaterHomeViewModel,
    onLocationChanged: () -> Unit,
    pickImage: () -> Unit,
    nearestTrucksViewmodel: NearestTrucksViewmodel,
    waterOrderDetailsViewModel: WaterOrderDetailsViewModel
) {

    val deliveryTypeUiState by deliveryTypeViewModel.deliveryTypeUiState.collectAsState()
    val tankerBoreholeUiState by tankerBoreholeViewModel.tankerBoreholeUiState.collectAsState()
    val waterHomeUiState by waterHomeViewModel.waterHomeUiState.collectAsState()

    val appUiState = deliveryTypeViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )

    var isSheetFullScreen by remember { mutableStateOf(false) }
    val roundedCornerRadius = if (isSheetFullScreen) 0.dp else 12.dp
    val modifier = if (isSheetFullScreen)
        Modifier
            .background(Color.Transparent)
            .fillMaxSize()
    else
        Modifier.fillMaxWidth()

    LaunchedEffect(key1 = true, block = {
        tankerBoreholeViewModel.getMyLocale(context)
    })

    //on Backpressed
    BackHandler(modalSheetState.isVisible) {
        coroutineScope.launch {
            modalSheetState.hide()
            deliveryTypeViewModel.changeDialogState(true)
        }
    }

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

                WaterSelectDateContent(
                    submitClicked = {
                        coroutineScope.launch {
                            modalSheetState.hide()
//                        deliveryTypeViewModel.navigateWaterOrderDetails()
                            tankerBoreholeViewModel.createIndividualWaterOrder(
                                context,
                                waterUiState = waterHomeUiState,
                                deliveryTypeUiState = deliveryTypeUiState,
                                nearestTrucksViewmodel = nearestTrucksViewmodel,
                                waterOrderDetailsViewModel = waterOrderDetailsViewModel
                            )
                        }
                    },
                    selectedDate = tankerBoreholeViewModel::onDateSelectedChange,
                    selectedTimeSlot = tankerBoreholeViewModel::onTimeSlotSelectedSelectedChange,
                    tankerBoreholeUiState = tankerBoreholeUiState,
                    recurringClicked = tankerBoreholeViewModel::onRecurringChange,
                    selectedSlot = tankerBoreholeViewModel::onSlotSelectedSelectedChange
                )
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
                TankerBoreholeContent(
                    continueClicked = { tankerBoreholeViewModel.changeDialogState(deliveryTypeViewModel, context = context) },
                    tankerBoreholeUiState = tankerBoreholeUiState,
                    onVolumeChanged = tankerBoreholeViewModel::onVolumeChange,
                    onLocationChanged = onLocationChanged,
                    pickImage = pickImage,
                    waterUiState = waterHomeUiState
                )

                DeliveryTypeDialog(
                    onDismissRequest = { deliveryTypeViewModel.changeDialogState(false) },
                    navigate = {
                        deliveryTypeViewModel.setDeliveryType(it)

                        if (it.equals("SCHEDULED")) {
                            coroutineScope.launch {
                                deliveryTypeViewModel.changeDialogState(false)
                                if (modalSheetState.isVisible)
                                    modalSheetState.hide()
                                else
                                    modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                            }
                        } else
//                            deliveryTypeViewModel.navigateWaterOrderDetails()

//                        tankerBoreholeViewModel.createCorporateOrder(context = context, deliveryTypeUiState = deliveryTypeUiState, waterUiState = waterHomeUiState )
                            tankerBoreholeViewModel.createIndividualWaterOrder(
                                context = context,
                                waterOrderDetailsViewModel = waterOrderDetailsViewModel,
                                deliveryTypeUiState = deliveryTypeUiState,
                                waterUiState = waterHomeUiState,
                                nearestTrucksViewmodel = nearestTrucksViewmodel
                            )
                    },
                    show = deliveryTypeUiState.showDialog
                )
            },
            pageLoading = deliveryTypeUiState.pageLoading,
            actionLoading = deliveryTypeUiState.actionLoading,
            errorList = deliveryTypeUiState.errorList,
            messageList = deliveryTypeUiState.messageList,
            onBackButtonClicked = { deliveryTypeViewModel.appViewModel?.onBackButtonClicked() },
            onDashboardButtonClicked = { deliveryTypeViewModel.appViewModel?.onDashboardButtonClicked() },
            onCartButtonClicked = { deliveryTypeViewModel.appViewModel?.onCartButtonClicked() },
            navigateTo = { deliveryTypeViewModel.appViewModel?.navigate(it) },
            drawerMenuItems = appUiState.value.drawerMenuItems,
            userProfiles = appUiState.value.userProfiles,
            onSelectProfile = { deliveryTypeViewModel.appViewModel?.onSelectProfile(it) },
            activeProfile = appUiState.value.activeProfile,
            cartsize = cartUiState.value.orderList.size,
            showLogo = false,
            showImageRight = true,
            showCart = false
        )
    }

}