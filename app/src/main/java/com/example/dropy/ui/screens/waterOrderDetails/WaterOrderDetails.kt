package com.example.dropy.ui.screens.waterOrderDetails

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.deliveryType.DeliveryTypeDialog
import com.example.dropy.ui.screens.deliveryType.DeliveryTypeViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeContent
import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeUiState
import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeViewModel
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WaterOrderDetails(
    cartPageViewModel: CartPageViewModel,
    waterOrderDetailsViewModel: WaterOrderDetailsViewModel,
    tankerBoreholeViewModel: TankerBoreholeViewModel
) {

    val waterOrderDetailsUiState by waterOrderDetailsViewModel.waterOrderDetailsUiState.collectAsState()
    val tankerBoreholeUiState by tankerBoreholeViewModel.tankerBoreholeUiState.collectAsState()

    val appUiState = waterOrderDetailsViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    val formatDate = remember {
        mutableStateOf("")
    }
    val formatTime = remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = true, block = {
        val date = mutableStateOf("")

        val timee = mutableStateOf("")

        Log.d("lopk", "WaterOrderDetails: ${tankerBoreholeUiState.createIndividualWaterOrderRes}")

        tankerBoreholeUiState.createIndividualWaterOrderRes!!.timestamp!!.forEachIndexed { index, time ->
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


    AppScaffold(
        content = {
            WaterOrderDetailsContent(
                nextClicked = waterOrderDetailsViewModel::navigateWaterOrderPay,
                tankerBoreholeUiState = tankerBoreholeUiState,
                time = formatTime.value,
                date = formatDate.value,
                waterOrderDetailsUiState = waterOrderDetailsUiState
            )
        },
        pageLoading = waterOrderDetailsUiState.pageLoading,
        actionLoading = waterOrderDetailsUiState.actionLoading,
        errorList = waterOrderDetailsUiState.errorList,
        messageList = waterOrderDetailsUiState.messageList,
        onBackButtonClicked = { waterOrderDetailsViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { waterOrderDetailsViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { waterOrderDetailsViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { waterOrderDetailsViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { waterOrderDetailsViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = false
    )
}