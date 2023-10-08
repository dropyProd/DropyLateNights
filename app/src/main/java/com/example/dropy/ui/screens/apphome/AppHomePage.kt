package com.example.dropy.ui.screens.apphome


import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.apphome.AppHomePageContent
import com.example.dropy.ui.components.commons.AppScaffold
import com.example.dropy.ui.components.commons.appdrawer.ProfileTypes
import com.example.dropy.ui.screens.rider.IncomingJobViewModel
import com.example.dropy.ui.screens.shops.frontside.cart.CartPageViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun AppHomePage(
    appHomePageViewModel: AppHomePageViewModel,
    shopHomePageViewModel: ShopHomePageViewModel,
    uiState: AppHomePageUiState,
    cartPageViewModel: CartPageViewModel,
    incomingJobViewModel: IncomingJobViewModel,
    appViewModel: AppViewModel
) {
    val appUiState = appHomePageViewModel.appViewModel!!.appUiState.collectAsState()
    val cartUiState = cartPageViewModel.cartPageUiState.collectAsState()
    val context = LocalContext.current
    val checkoutViewModel: CheckoutViewModel = hiltViewModel()
    val shopuiState by shopHomePageViewModel.shopHomePageUiState.collectAsState()


    appHomePageViewModel.appViewModel?.setSystemUiControllerColor(Color(248, 242, 212))
    LaunchedEffect(key1 = true, block = {
        appHomePageViewModel.appViewModel!!.getIndividualOrders()
    })
    LaunchedEffect(key1 = true, block = {
        appHomePageViewModel.appViewModel!!.getIndividualOrders()
    })
    LaunchedEffect(key1 = true, block = {
        appHomePageViewModel.setContext(context)
      //  checkoutViewModel.getmethods(shopuiState)

    })
    LaunchedEffect(key1 = true, block = {
        for (i in 1..3) {
            try {
                if (i == 2) cartPageViewModel.getCustomerCart()
                delay(2000)
            } catch (e: Exception) {
                // Toast.makeText(context, "3", Toast.LENGTH_SHORT).show()

            }

        }

    })

    /*LaunchedEffect(key1 = true, block = {
        for (i in 1..3) {
            try {
                if (i == 2) appUiState.value.firebaseUid?.let { appViewModel.getUserDetails(it) }
                delay(2000)
            } catch (e: Exception) {
                //  Toast.makeText(context, "4", Toast.LENGTH_SHORT).show()

            }

        }

    })*/

    LaunchedEffect(key1 = true, block = {
        for (i in 1..3) {
            try {
                if (i == 2) appHomePageViewModel.getFavouriteShops()
                delay(2000)
            } catch (e: Exception) {
                // Toast.makeText(context, "5", Toast.LENGTH_SHORT).show()

            }

        }

    })

    val scope = rememberCoroutineScope()
    AppScaffold(
        content = {
            //  appUiState.value.activeProfile?.name?.let {
            AppHomePageContent(
                uiState = uiState,
                onShopSelected = { item, shop ->
                    // if (appViewModel.appUiState.value.activeProfile?.type.equals(ProfileTypes.CUSTOMER)) {
                    appHomePageViewModel.onShopSelected(
                        item, shop,
                        shopHomePageViewModel,
                        appHomePageViewModel = appHomePageViewModel
                    )
//                    }
                },
                onGoToShops = {
                    //     if (appViewModel.appUiState.value.activeProfile?.type.equals(ProfileTypes.CUSTOMER)) {
                    appHomePageViewModel.onGoToShops()
                    //   }
                },
                onGoToRides = {
                    scope.launch {
                        //  incomingJobViewModel.getIncomingJobs(3, appViewModel = appViewModel)

                        appHomePageViewModel.onGoToRides()
                    }
                },
                onGoToParcels = appHomePageViewModel::onGoToParcels,
                onShopReview = appHomePageViewModel::onShopReview,
                searchItem = {
                    appHomePageViewModel.onSearchItem(
                        it,
                        shopHomePageViewModel,
                        appHomePageViewModel = appHomePageViewModel
                    )
                },
                customerName = appUiState.value.activeProfile?.name.toString(),
                likeClicked = appHomePageViewModel::onFavouriteClicked,
                onSearchItem = appHomePageViewModel::onGoToSearch,
                onProfilePhotoClicked = appHomePageViewModel::navigateCustomerDashboard,
                onGoToWater = {
                    scope.launch {
                        appHomePageViewModel.onGoToWater()
                    }
                }
            )
            // }
        },
        showBackButton = false,
        showSuperApp = true,
        showLogo = false,
        pageLoading = uiState.pageLoading,
        errorList = uiState.errorList,
        messageList = uiState.messageList,
        onBackButtonClicked = { appHomePageViewModel.appViewModel?.onBackButtonClicked() },
        onDashboardButtonClicked = { appHomePageViewModel.appViewModel?.onDashboardButtonClicked() },
        onCartButtonClicked = { appHomePageViewModel.appViewModel?.onCartButtonClicked() },
        navigateTo = {
            appHomePageViewModel.appViewModel?.navigate(it)
        },
        drawerMenuItems = appUiState.value.drawerMenuItems,
        userProfiles = appUiState.value.userProfiles,
        onSelectProfile = { appHomePageViewModel.appViewModel?.onSelectProfile(it) },
        activeProfile = appUiState.value.activeProfile,
        cartsize = cartUiState.value.orderList.size
    )
}

@Preview(showBackground = true)
@Composable
fun AppHomePagePreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // AppHomePage(appHomePageViewModel = AppHomePageViewModel())
    }
}