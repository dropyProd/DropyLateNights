package com.example.dropy.ui.screens.waterOrderPay

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.screens.deliveryType.DeliveryTypeDialog
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutViewModel
import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeContent
import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeViewModel
import com.example.dropy.ui.screens.waterOrderDetails.WaterOrderDetailsViewModel

@Composable
fun WaterOrderPay(
    cartPageViewModel: CartPageViewModel,
    waterOrderPayViewModel: WaterOrderPayViewModel,
    tankerBoreholeViewModel: TankerBoreholeViewModel,
    waterOrderDetailsViewModel: WaterOrderDetailsViewModel,
    checkoutViewModel: CheckoutViewModel
) {

    val waterOrderPayUiState by waterOrderPayViewModel.waterOrderPayUiState.collectAsState()
    val tankerBoreholeUiState by tankerBoreholeViewModel.tankerBoreholeUiState.collectAsState()
    val waterOrderDetailsUiState by waterOrderDetailsViewModel.waterOrderDetailsUiState.collectAsState()

    val appUiState = waterOrderPayViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()

    AppScaffold(
        content = {
            WaterOrderPayContent(
                payClicked = {
                    checkoutViewModel.setDeliveryPrice((waterOrderDetailsUiState.tenTrucks.size * 4000) + (waterOrderDetailsUiState.fiveTrucks.size * 2000))
                    waterOrderPayViewModel.navigateWaterThankYou()
                },
                tankerBoreholeUiState = tankerBoreholeUiState,
                waterOrderDetailsUiState = waterOrderDetailsUiState
            )
        },
        pageLoading = waterOrderPayUiState.pageLoading,
        actionLoading = waterOrderPayUiState.actionLoading,
        errorList = waterOrderPayUiState.errorList,
        messageList = waterOrderPayUiState.messageList,
        onBackButtonClicked = { waterOrderPayViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { waterOrderPayViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { waterOrderPayViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { waterOrderPayViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { waterOrderPayViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size,
        showLogo = false,
        showImageRight = true,
        showCart = false
    )

}