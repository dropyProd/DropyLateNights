package com.example.dropy.ui.screens.waterOrderSingle

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.loadRefill.LoadRefillDialog
import com.example.dropy.ui.screens.nearestWaterPoint.NearestWaterPointViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.truckIncomingWork.TruckIncomingWorkViewModel
import com.example.dropy.ui.screens.truckRouteCustomer.TruckRouteCustomerViewModel
import com.example.dropy.ui.screens.truckRouteWaterPoint.TruckRouteWaterPointViewModel
import com.example.dropy.ui.screens.waterTracking.WaterTrackingContent
import com.example.dropy.ui.screens.waterTracking.WaterTrackingViewModel
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WaterOrderSingle(
    cartPageViewModel: CartPageViewModel,
    waterOrderSingleViewModel: WaterOrderSingleViewModel,
    truckIncomingWorkViewModel: TruckIncomingWorkViewModel,
    nearestWaterPointViewModel: NearestWaterPointViewModel,
    truckRouteWaterPointViewModel: TruckRouteWaterPointViewModel,
    truckRouteCustomerViewModel: TruckRouteCustomerViewModel
) {

    val waterOrderSingleUiState by waterOrderSingleViewModel.waterOrderSingleUiState.collectAsState()
    val nearestWaterPointUiState by nearestWaterPointViewModel.nearestWaterPointUiState.collectAsState()

    val appUiState = waterOrderSingleViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()
    val truckIncomingWorkUiState by truckIncomingWorkViewModel.truckIncomingWorkUiState.collectAsState()
    val formatDate = remember {
        mutableStateOf("")
    }
    val formatTime = remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = true, block = {
        val date = mutableStateOf("")

        val timee = mutableStateOf("")

        truckIncomingWorkUiState.selectedOrder?.timestamp?.forEachIndexed { index, time ->
            if (index in 0..9) {
                date.value += time
            }
            if (index in 11..15) {
                timee.value += time
            }
        }
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val newDate = formatter.parse(date.value)
        val formatterNew = DateTimeFormatter.ofPattern("dd MMM yyyy")
        formatDate.value = formatterNew.format(newDate)
        Log.d("njkm", "WaterOrderSingle: $formatDate")
        val formatterTime = DateTimeFormatter.ofPattern("HH:mm")
        val newTime = formatterTime.parse(timee.value)
        val formatterNewTime = DateTimeFormatter.ofPattern("hh:mm a")
        formatTime.value = formatterNewTime.format(newTime)
    })

    LaunchedEffect(key1 = true, block = {
        waterOrderSingleViewModel.appViewModel!!.getWaterpoints()
        nearestWaterPointViewModel.getWaterPoints()
    })

    LaunchedEffect(key1 = true, block ={
        waterOrderSingleViewModel.getMyLocale(context)
    } )

    LaunchedEffect(key1 = true, block = {
        waterOrderSingleViewModel.appViewModel!!.getIndividualOrders()
    })

    AppScaffold(
        content =
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                WaterOrderSingleContent(
                    nextClicked = {
                        waterOrderSingleViewModel.changeDialogState(
                            true
                        )
                    },
                    truckIncomingWorkUiState = truckIncomingWorkUiState,
                    date = formatDate.value,
                    time = formatTime.value,
                    waterOrderSingleUiState = waterOrderSingleUiState
                )
            }

            LoadRefillDialog(
                onDismissRequest = { waterOrderSingleViewModel.changeDialogState(false) },
                navigate = {
                    waterOrderSingleViewModel.navigatenearestWaterPoints(
                        it,
                        nearestWaterPointUiState = nearestWaterPointUiState,
                        nearestWaterPointViewModel = nearestWaterPointViewModel,
                        truckIncomingWorkUiState = truckIncomingWorkUiState,
                        truckRouteWaterPointViewModel = truckRouteWaterPointViewModel,
                        truckRouteCustomerViewModel = truckRouteCustomerViewModel
                    )
                },
                show = waterOrderSingleUiState.showDialog
            )
        },
        pageLoading = waterOrderSingleUiState.pageLoading,
        actionLoading = waterOrderSingleUiState.actionLoading,
        errorList = waterOrderSingleUiState.errorList,
        messageList = waterOrderSingleUiState.messageList,
        onBackButtonClicked =
        { waterOrderSingleViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked =
        { waterOrderSingleViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked =
        { waterOrderSingleViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo =
        { waterOrderSingleViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile =
        { waterOrderSingleViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = true
    )
}