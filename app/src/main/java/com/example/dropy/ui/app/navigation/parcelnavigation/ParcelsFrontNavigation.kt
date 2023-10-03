package com.example.dropy.ui.app.navigation.parcelsnavigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel


object ParcelsFrontNavigation{
    const val SEND_PARCEL = "sendParcel"
    const val MY_PARCELS = "myParcels"
    const val PARCEL_DETAILS = "parcelDetails/{parcelId}"
}

fun NavGraphBuilder.parcelsFrontNavigation(navController: NavController, appViewModel: AppViewModel){
    navigation(startDestination = ParcelsFrontNavigation.SEND_PARCEL, route = AppDestinations.PARCELS){
        sendParcelNavigation(navController = navController, appViewModel = appViewModel)

        composable(ParcelsFrontNavigation.MY_PARCELS){

        }
        composable(ParcelsFrontNavigation.PARCEL_DETAILS){

        }
    }
}