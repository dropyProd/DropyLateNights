package com.example.dropy.ui.app.navigation.parcelnavigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.screens.shops.frontside.shopslandingpage.ShopsLandingPage
import com.example.dropy.ui.screens.shops.frontside.shopslandingpage.ShopsLandingPageViewModel


object ParcelDestination {
    const val PARCEL_HOME = "parcelHome"
    const val PARCEL_MAINMODEL = "parcelMainModels"
    const val PARCEL_RECIEVE = "parcelReciever"
    const val PARCEL_EXPRESS = "parcelExpressDelivery"
    const val PARCEL_OPTION = "parcelOption"
    const val PARCEL_NEARME = "parcelNearme"
    const val PARCEL_EXPRESS_SINGLE = "parcelExpressRiderSingle"
    const val PARCEL_PAY = "parcelPay"
    const val PARCEL_DROPY_PAY = "parcelDRopyPay"
    const val PARCEL_PAYMENTTHANKS = "paymentThankYou"
    const val PARCEL_LOCATION_EXPRESS_RIDER = "parcelLocationExpressRider"
    const val PARCEL_SCANQR = "parcelScanQr"
    const val PARCEL_TRACK = "trackParcel"

}

@Composable
fun NavGraphBuilder.parcelNavigation(navController: NavController) {
    /* navigation(
         startDestination = ParcelDestination.PARCEL_HOME,
         route = ParcelDestination.PARCEL_HOME
     ) {
         composable(ParcelDestination.PARCEL_HOME) {
             val shopsLandingPageViewModel: ShopsLandingPageViewModel = hiltViewModel()

         }
     }*/
}