package com.example.dropy.ui.screens.shops.frontside.checkout.deliveryinformation

import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dropy.network.models.deliverymethods.DeliveryMethodResponseItem
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.commons.AddressDataClass
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.shops.frontside.customer.orderprocess.deliveryinfomation.DeliveryInformationContent
import com.example.dropy.ui.components.shops.frontside.customer.orderprocess.deliveryinfomation.deliverymethod.DeliveryMethodDataClass
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.rider.IncomingJobViewModel
import com.example.dropy.ui.screens.rider.RiderDetailsDialog
import com.example.dropy.ui.screens.rider.RiderDetailsViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.trackyourorder.TrackYourOrderViewModel
import com.example.dropy.ui.screens.shops.frontside.dashboard.addresses.singleaddress.SingleAddressViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DeliverInformation(
    checkoutViewModel: CheckoutViewModel,
    onPlaceNameChanged: (String) -> Unit,
    openSearchmapdialog: () -> Unit,
    suggestedLocales: List<AddressDataClass>,
    latLng: LatLng? = null,
    incomingJobViewModel: IncomingJobViewModel,
    shopHomePageViewModel: ShopHomePageViewModel,
    singleAddressViewModel: SingleAddressViewModel,
    cartPageViewModel: CartPageViewModel,
    fetchLocaleDetails: (String) -> Unit,
    chooseLocation: () -> Unit,
    appHomePageViewModel: AppHomePageViewModel,
    trackYourOrderViewModel: TrackYourOrderViewModel
) {

    val riderDetailsViewModel: RiderDetailsViewModel = hiltViewModel()

    val appUiState by checkoutViewModel.appViewModel!!.appUiState.collectAsState()

    val uiState by checkoutViewModel.deliveryInformationUiState.collectAsState()
    val shopuiState by shopHomePageViewModel.shopHomePageUiState.collectAsState()
    val singleaddressuiState by singleAddressViewModel.singleAddressUiState.collectAsState()
    val cartuiState by cartPageViewModel.cartPageUiState.collectAsState()
    val riderDetailsUiState by riderDetailsViewModel.riderDetailsUiState.collectAsState()


    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    /* val temporary: MutableState<DeliveryMethodResponseItem?> = remember {
         mutableStateOf(null)
     }*/

    LaunchedEffect(key1 = true, block = {
        for (i in 0..4) {
            if (i == 2) {
                appHomePageViewModel.shopDistance(shopHomePageViewModel = shopHomePageViewModel)
            }
            delay(1000)
        }
    })
    LaunchedEffect(key1 = true, block = {
        if (!appUiState.myAddress?.placeName.equals("Place name")) {
            shopHomePageViewModel.getRouteDetails()
        }
    })

    AppScaffold(
        content = {
            DeliveryInformationContent(
                uiState = uiState,
                selectTaggedAddress = { checkoutViewModel.selectTaggedAddress(it) },
                editTaggedLocation = { checkoutViewModel.onEditDeliveryLocationClicked() },
                onMyLocationClicked = { checkoutViewModel.onMyLocationClicked() },
                onEditDeliveryLocationClicked = { /*checkoutViewModel.onEditDeliveryLocationClicked()*/chooseLocation() },
                onSelectMethod = { item, price ->
                    //  temporary.value = item

                    checkoutViewModel.onSelectMethod(item)
                    checkoutViewModel.setDeliveryPrice(price)

                    // incomingJobViewModel.setEta(eta = temporary.value!!.etaInMinutes)

                },

                onNextClicked = {
/*                    incomingJobViewModel.setIncomingJobs(
                        checkoutViewModel = checkoutViewModel,
                        eta = temporary.value!!.etaInMinutes
                    )
                    val id: MutableState<String?> = mutableStateOf(null)
                    suggestedLocales.forEach {
                        if (it.placeName.equals(uiState.currentAddress?.placeName)) id.value =
                            it.placeId
                    }
                    id.value?.let { fetchLocaleDetails(it) }*/
                    //  uiState.currentAddress?.placeName?.let { fetchLocaleDetails(it) }
                    /*   if (temporary.value != null) {*/
                    if (!appUiState.myAddress?.placeName.equals("Place name")) {
                        checkoutViewModel.onNextClicked()
                    }
                    /*     } else {
                             Toast.makeText(context, "choose delivery method", Toast.LENGTH_SHORT).show()
                         }*/
                },
                onPlaceNameChanged = {
                    scope.launch {


                        //checkoutViewModel.onLocationNameChanged(it)

                        /*   onPlaceNameChanged(it)
                           delay(2500)*/
                        // STOPSHIP: disabled navigaion
                        checkoutViewModel.appViewModel!!.navigate(ShopsFrontDestination.SINGLE_ADDRESS)


                    }
                },
                openSearchmapdialog = openSearchmapdialog,
                suggestedLocales = suggestedLocales,
                latLng = shopuiState.shopLatLng,
                updatePrice = { string, int, eta, state ->

                    riderDetailsViewModel.changeRiderDialogState(state)
                    //  val data = temporary.value?.copy(etaInMinutes = eta)
                    //   temporary.value = data
                    incomingJobViewModel.setRiderName(string)

                        riderDetailsViewModel.setRiderValues(
                            riderName = string,
                            placeName = appUiState.myAddress?.placeName?: "default"
                        )

                    incomingJobViewModel.setEta(eta = eta)
                    if (state.equals(false)) {
                        checkoutViewModel.setDeliveryPrice(int)
                    }
                },
                shopname = shopuiState.shopName,
                distance = shopuiState.shopDistance,
                singleAddressUiState = singleaddressuiState,
                appUiState = appUiState,
                shopHomePageUiState = shopuiState,
                appViewModel = checkoutViewModel.appViewModel!!,
                shopHomePageViewModel = shopHomePageViewModel,
                trackYourOrderViewModel = trackYourOrderViewModel
            )

            RiderDetailsDialog(
                show = riderDetailsUiState.dialogState,
                riderDetailsViewModel = riderDetailsViewModel,
                onDismisssDialog = {
                    riderDetailsViewModel.changeRiderDialogState(false)
                })
        },
        pageLoading = uiState.pageLoading,
        actionLoading = uiState.actionLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { checkoutViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { checkoutViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { checkoutViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = { checkoutViewModel.appViewModel?.navigate(it) },
        drawerMenuItems = appUiState.drawerMenuItems,
        userProfiles = appUiState.userProfiles,
        onSelectProfile = { checkoutViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.activeProfile,
        cartsize = cartuiState.orderList.size
    )

}

@Preview(showBackground = true)
@Composable
fun DeliverInformationPreview() {
    val viewModel: CheckoutViewModel = viewModel()
    // viewModel.appViewModel = AppViewModel()
    /*  DeliverInformation(
          checkoutViewModel = viewModel,
          onPlaceNameChanged = {},
          openSearchmapdialog = {}, suggestedLocales = listOf()
      )*/
}